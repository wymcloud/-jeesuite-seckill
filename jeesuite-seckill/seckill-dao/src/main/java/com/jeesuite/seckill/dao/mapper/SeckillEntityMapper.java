package com.jeesuite.seckill.dao.mapper;

import com.jeesuite.seckill.dao.entity.SeckillEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by codingBoy on 16/11/26.
 */
public interface SeckillEntityMapper{

    /**
     * 减库存
     * @param seckillId
     * @param killTime
     * @return 如果影响行数>1，表示更新库存的记录行数
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

   /**
     * 根据id查询秒杀的商品信息
     * @param seckillId
     * @return
     */
    SeckillEntity queryById(long seckillId);

    /**
     * 根据偏移量查询秒杀商品列表
     * @param offset
     * @param limit
     * @return
     */
    List<SeckillEntity> queryAll(@Param("offset") int offset, @Param("limit") int limit);

}
