### jeesuite-seckill

基于jeesuite构建的一个秒杀场景的demo。

### 依赖说明（如果出现包找不到或者某些class没找到）

*   SNAPSHOT结尾的依赖包请自行下载[jeesuite-libs](http://git.oschina.net/vakinge/jeesuite-libs) 项目本地构建。
*   release版的依赖请配置最高的版本号，版本号请看：[sonatype](https://oss.sonatype.org/content/repositories/releases/com/jeesuite/)
    ### 基于[jeesuite-libs](http://git.oschina.net/vakinge/jeesuite-libs)

### 模块说明

*   seckill-api 定义接口
*   seckill-dao 数据访问接口
*   seckill-service 服务实现
*   seckill-app 无容器启动服务发布（这里是把service发布成dubbo服务）
*   seckill-web 基于springMVC传统web
*   seckill-ui 前端 未开发

### 如何部署？

#### 启动app模块appServer类，提供了脚本启动BAT和SHELL.

#### 部署web模块到服务器启动，部署'seckill-web`下war包即可。

访问地址如：[http://localhost:8080/seckil/list](http://localhost:8080/seckil/list) (加上你自己的contextpath)

下一步将前后端分离，有兴趣的联系我。

QQ:793717561
