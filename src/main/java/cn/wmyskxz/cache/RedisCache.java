package cn.wmyskxz.cache;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.wmyskxz.util.ConfigUtils;
import cn.wmyskxz.util.JedisUtils;

public class RedisCache implements Cache {

    private static Logger logger = LoggerFactory.getLogger(RedisCache.class);

    private String cacheId;
    /**
     * 读写锁：分为读锁和写锁，多个读锁不互斥，读锁与写锁互斥，这是由jvm自己控制的，你只要上好相应的锁即可。如果你的代码只读数据，可以很多人
     * 同时读，但不能同时写，那就上读锁；如果你的代码修改数据，只能有一个人在写，且不能同时读取，那就上写锁。总之，读的时候上读锁，写的时候上
     * 写锁！
     */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
    private final Lock read = readWriteLock.readLock();
    private final Lock write = readWriteLock.writeLock();

    public RedisCache(String cacheId) {
        if (cacheId == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.cacheId = ConfigUtils.key + "." + cacheId;
        logger.info("查询结果存入缓存对应的缓存空间生成的名字cacheId: " + this.cacheId);

        if (ConfigUtils.redisSwitch) {
            JedisUtils.getInstence();
        }
    }

    @Override
    public String getId() {
        return cacheId;
    }

    @Override
    public void putObject(Object key, Object value) {
        // TODO 从缓存中写数据，用写锁锁定，不允许读
        logger.info("NTSRedisCache putObject=" + cacheId);
        if (ConfigUtils.redisSwitch) {
            write.lock();
            try {
                JedisUtils.put(cacheId, key, value);
            } finally {
                write.unlock();
            }

        }
    }

    @Override
    public Object getObject(Object key) {
        // TODO 从缓存中读数据，用读锁锁定，不允许写
        logger.info("从缓存cacheId="+cacheId+"中拿数据key="+key+"对应的value");
        if (ConfigUtils.redisSwitch) {
            read.lock();
            try {
                return JedisUtils.get(cacheId, key);
            } finally {
                read.unlock();
            }
        }
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        // TODO 从缓存中改动数据，用写锁锁定，不允许读，改动结束后释放写锁。
        logger.info("NTSRedisCache clear =" + cacheId);
        if (ConfigUtils.redisSwitch) {
            write.lock();
            try {
                return JedisUtils.remove(cacheId, key);
            } finally {
                write.unlock();
            }
        }
        return null;
    }

    @Override
    public void clear() {
        // TODO  从缓存中改动数据，用写锁锁定，不允许读，改动结束后释放写锁。
        logger.info("NTSRedisCache clear =" + cacheId);
        if (ConfigUtils.redisSwitch) {
            write.lock();
            try {
                JedisUtils.removeAll(cacheId);
            } finally {
                write.unlock();
            }
        }
    }

    @Override
    public int getSize() {
        // TODO Auto-generated method stub
        logger.info("NTSRedisCache clear =" + cacheId);
        if (ConfigUtils.redisSwitch) {
            read.lock();
            try {
                return JedisUtils.getSize(cacheId);
            } finally {
                read.unlock();
            }
        }
        return -1;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

}
