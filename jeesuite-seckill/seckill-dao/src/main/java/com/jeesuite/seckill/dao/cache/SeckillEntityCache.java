package com.jeesuite.seckill.dao.cache;

import com.jeesuite.cache.command.RedisObject;

/**
 * @author YUMING
 * @create 2017年02月17日 15:21
 */
public class SeckillEntityCache extends RedisObject {


    public SeckillEntityCache(String key) {
        super(key);
    }

    public SeckillEntityCache(String key, String groupName) {
        super(key, groupName);

    }
}
