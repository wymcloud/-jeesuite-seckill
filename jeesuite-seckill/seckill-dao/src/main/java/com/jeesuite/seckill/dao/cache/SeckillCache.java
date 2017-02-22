package com.jeesuite.seckill.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.jeesuite.seckill.dao.entity.SeckillEntity;
import com.jeesuite.seckill.dto.Seckill;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author YUMING
 * @create 2017年02月17日 13:29
 */
public class SeckillCache {

    private JedisPool jedisPool;

    //创建schema模式
    private RuntimeSchema<SeckillEntity> schema = RuntimeSchema.createFrom(SeckillEntity.class);

    public SeckillEntity getSeckill (long seckillId){
        try {
            Jedis jedis = jedisPool.getResource();
            try{
                String key = "seckill:"+seckillId;
                byte [] bytes = jedis.get(key.getBytes());
                if(bytes != null){
                    SeckillEntity seckill = schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes,seckill,schema);
                    return seckill;
                }
            }finally {
                jedis.close();
            }
        }catch (Exception e){

        }
        return null;
    }
    public String putSeckill(SeckillEntity seckill){
        try {
            Jedis jedis = jedisPool.getResource();
            try{
                String key = "seckill:"+seckill.getSeckillId();
                byte [] bytes = ProtostuffIOUtil.toByteArray(seckill,schema,
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                int timeOut = 60*60;
                String result = jedis.setex(key.getBytes(),timeOut,bytes);
                return result;
            }finally {
                jedis.close();
            }
        }catch (Exception e){

        }

        return null;
    }
}
