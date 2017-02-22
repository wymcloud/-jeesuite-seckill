#!/bin/sh
# 无容器APP启动脚本
# vakinge 
# 2016.10
#

#JDK所在路径
#JAVA_HOME="/data/apps/deps/jdk1.8"
 
#执行程序启动所使用的系统用户
RUNNING_USER=vakinge
 
#应用名
APP_NAME=demo-app


APP_HOME=`pwd`
dirname $0|grep "^/" >/dev/null
if [ $? -eq 0 ];then
   APP_HOME=`dirname $0`
else
    dirname $0|grep "^\." >/dev/null
    retval=$?
    if [ $retval -eq 0 ];then
        APP_HOME=`dirname $0|sed "s#^.#$APP_HOME#"`
    else
        APP_HOME=`dirname $0|sed "s#^#$APP_HOME/#"`
    fi
fi

#目录定义
LOGDIR=$APP_HOME/logs
CONFDIR=$APP_HOME/conf

if [ ! -d "$LOGDIR" ];then
  mkdir $LOGDIR
fi

#需要启动的Java主程序（main方法类）
APP_MAINCLASS=AppServer
 
#拼凑完整的classpath参数，包括指定lib目录下所有的jar
CLASSPATH=$APP_HOME/conf
for i in $APP_HOME/lib/*.jar; do
   CLASSPATH="$CLASSPATH":"$i"
done


#
ADATE=`date +%Y%m%d%H%M%S`
 
#远程调试才需用到
DEBUG="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=9997"
#JMX监控需用到
JMX="-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=1091 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false"
#JVM参数
GCOPTS="-Dname=$APP_NAME -Duser.timezone=Asia/Shanghai -Dlog.home=$LOGDIR -Xms512M -Xmx1024M -XX:PermSize=256M -XX:MaxPermSize=512M -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDateStamps -Xloggc:$LOGDIR/gc-$APP_NAME-$ADATE.log -XX:+PrintGCDetails -XX:NewRatio=1 -XX:SurvivorRatio=30 -XX:+UseParallelGC -XX:+UseParallelOldGC"

 
###################################
#(函数)判断程序是否已启动
#
#说明：
#使用JDK自带的JPS命令及grep命令组合，准确查找pid
#jps 加 l 参数，表示显示java的完整包路径
#使用awk，分割出pid ($1部分)，及Java程序名称($2部分)
###################################
#初始化psid变量（全局）
psid=0
 
checkpid() {
   javaps=`$JAVA_HOME/bin/jps -l | grep $APP_MAINCLASS`
 
   if [ -n "$javaps" ]; then
      psid=`echo $javaps | awk '{print $1}'`
   else
      psid=0
   fi
}
 
###################################
#(函数)启动程序
###################################
start() {
   checkpid
 
   if [ $psid -ne 0 ]; then
      echo "================================"
      echo "warn: $APP_NAME already started! (pid=$psid)"
      echo "================================"
   else
      echo "Starting $APP_NAME ..."
      JAVA_CMD="nohup java -server $GCOPTS -cp $CLASSPATH $APP_MAINCLASS $CONFDIR > $LOGDIR/nohup-$APP_NAME.out 2>&1 &"
      su - $RUNNING_USER -c "$JAVA_CMD"
      checkpid
      if [ $psid -ne 0 ]; then
         echo $psid > $LOGDIR/runing
         echo "(pid=$psid) [OK]"
         echo "＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝"
         echo "＝＝＝启动完成后请按CTRL+C退出控制台即可＝＝＝"
         echo "＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝"
         sleep 2s
         tail -f $LOGDIR/nohup-$APP_NAME.out
      else
         echo "[Failed]"
      fi
   fi
}

 
###################################
#(函数)停止程序
###################################
stop() {
   checkpid
 
   if [ $psid -ne 0 ]; then
      rm -f $LOGDIR/runing
      echo "已发送关闭指令到应用，等待应用关闭确认..."
      sleep 3s
      a=1
      while true
      do
       if [ -f "$LOGDIR/stoping" ] || [ $a -gt 5 ];then
         echo -n "Stopping $APP_MAINCLASS ...(pid=$psid) "
         su - $RUNNING_USER -c "kill -9 $psid"
         if [ $? -eq 0 ]; then
            rm -f $LOGDIR/stoping
            echo "[OK]"
         else
            echo "[Failed]"
         fi 
        break
      fi
      sleep 1s
      let a++
     done
      
      checkpid
      if [ $psid -ne 0 ]; then
         echo "Please retry..."
      fi
   else
      echo "================================"
      echo "warn: $APP_NAME is not running"
      echo "================================"
   fi
}
 
###################################
#(函数)检查程序运行状态
###################################
status() {
   checkpid
 
   if [ $psid -ne 0 ];  then
      echo "$APP_NAME is running! (pid=$psid)"
   else
      echo "$APP_NAME is not running"
   fi
}
 
###################################
#(函数)打印系统环境参数
###################################
info() {
   echo "System Information:"
   echo "****************************"
   echo `uname -a`
   echo
   echo "JAVA_HOME=$JAVA_HOME"
   echo `$JAVA_HOME/bin/java -version`
   echo
   echo "APP_NAME=$APP_NAME"
   echo "APP_HOME=$APP_HOME"
   echo "APP_MAINCLASS=$APP_MAINCLASS"
   echo "****************************"
}
 
###################################
#参数取值范围：{start|stop|restart|status|info}
###################################
case "$1" in
   'start')
      start
      ;;
   'stop')
     stop
     ;;
   'restart')
     stop
     start
     ;;
   'status')
     status
     ;;
   'info')
     info
     ;;
   *)
     echo "Usage: $0 {start|stop|restart|status|info|run}"
esac
exit 0

