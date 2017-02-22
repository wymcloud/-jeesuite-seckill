
-- 存储过程
DELIMITER $$
-- 参数 in 输入参数，out输出参数
-- row_count 返回上一条修改类型sql 的影响行数
-- row_count  = 0 : 未修改数据 <0 :sql 错误
CREATE  PROCEDURE  'seckill'.'execute_seckill'
    (in v_seckill_id bigint,in v_phone bigint,
      in v_kill_time TIMESTAMP ,out r_result int)
  BEGIN
    DECLARE  insert_count int DEFAULT 0 ;
    START TRANSACTION
      INSERT  ignore into success_killed
      (seckill_id,user_phone,create_time)
      VALUES (v_seckill_id,v_phone,v_kill_time)
    SELECT ROW_COUNT () into insert_count;
    IF (insert_count = 0 ) THEN
      ROLLBACK ;
      SET r_result = -1;
    ELSE IF(insert_count < 0) THEN
      ROLLBACK ;
      SET r_result = -2;
    ELSE
      UPDATE seckill
      set number = number -1
      where seckill_id = v_seckill_id
        and end_time > v_kill_time
        and start_time <v_kill_time
        and number > 0;
        SELECT  row_count into insert_count;
        IF (insert_count = 0) THEN
          ROLLBACK ;
          set r_result = 0;
        ELSE if(insert_count < 0 ) THEN
          ROLLBACK ;
          set r_result = -2;
        ELSE
          COMMIT ;
          SET r_result = 1;
        END IF ;
      END IF;
    END;
$$
-- 存储过程定义结束

DELIMITER ;
set @r_result = -3;
CAll  EXECUTE_seckill(1003,13502178891,now(),@r_result);
SELECT  @r_result;

