package cn.wmyskxz.util;

import org.mybatis.caches.redis.SerializeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {
    private static Logger logger=LoggerFactory.getLogger(JedisUtils.class);

    private static JedisPool JEDISPOOL;

    public static void getInstence(){
        if(JEDISPOOL == null){
            logger.info("JeidsUtils getInstence...");
            try {
                JedisPoolConfig conf=new JedisPoolConfig();
                conf.setMaxIdle(ConfigUtils.maxIdle);
                conf.setTestOnBorrow(ConfigUtils.testOnBorrow);
                //当配置中配置有password时，则创建带密码的缓存池
                if(ConfigUtils.password !=null && !"".equals(ConfigUtils.password)){
                    JEDISPOOL=new JedisPool(conf,ConfigUtils.ip,ConfigUtils.port,ConfigUtils.timeout,ConfigUtils.password);
                }else{
                //没有配置则用无密码的缓存池。
                    JEDISPOOL=new JedisPool(conf,ConfigUtils.ip,ConfigUtils.port,ConfigUtils.timeout);
                }
            } catch (Exception e) {
                logger.error("加载【jedis.properties】异常,异常信息为："+e.getMessage());
            }
        }
    }

    public static Jedis getJedis(){
        try {
            return JEDISPOOL.getResource();
        } catch (Exception e) {
            return null;
        }
    }

    public static void closeJedis(Jedis jedis){
        if(jedis !=null){
            jedis.quit();
        }
    }

    public static void closeJedisPool(){
        if(JEDISPOOL !=null){
            JEDISPOOL.close();
        }
    }
    //redis 序列化存储Object
    public static void put(String id,Object key,Object value){
        Jedis jedis=getJedis();
        logger.info("redis put ... key =["+key+"]");
        try {
            jedis.hset(SerializeUtil.serialize(id), SerializeUtil.serialize(key), SerializeUtil.serialize(value));
            ConfigUtils.setSucc();
        } catch (Exception e) {
            ConfigUtils.setFail();
            logger.error("redis执行异常1【"+e.getMessage()+"】");
        }finally{
            closeJedis(jedis);
        }
    }


    public static Object get(String id,Object key){
        Jedis jedis=getJedis();
        try {
            Object object = SerializeUtil.unserialize(jedis.hget(SerializeUtil.serialize(id), SerializeUtil.serialize(key)));
            logger.info("redis get ... key=["+key+"],value=["+object+"]");
            ConfigUtils.setSucc();
            return object;
        } catch (Exception e) {
            ConfigUtils.setFail();
            logger.error("Redis执行异常2【"+e.getMessage()+"】");
        }finally{
            closeJedis(jedis);
        }

        return null;
    }


    public static Long remove(String id,Object key){
        Jedis jedis=getJedis();
        try {
            Long num = jedis.hdel(id.toString(), key.toString());
            ConfigUtils.setSucc();
            return num;
        } catch (Exception e) {
            ConfigUtils.setFail();
            logger.error("Redis执行异常，异常信息："+e.getMessage());
        }finally{
            closeJedis(jedis);
        }

        return 0l;
    }

    public static void removeAll(String id){
        Jedis jedis=getJedis();
        try {
            jedis.del(id.toString());
            ConfigUtils.setSucc();
        } catch (Exception e) {
            ConfigUtils.setFail();
            logger.error("Redis执行异常3【"+e.getMessage()+"】");
        }finally{
            closeJedis(jedis);
        }
    }


    public static int getSize(String id){
        Jedis jedis=getJedis();

        try {
            return  jedis.hgetAll(SerializeUtil.serialize(id)).size();
        } catch (Exception e) {
            ConfigUtils.setFail();
            logger.error("Redis执行异常4【"+e.getMessage()+"】");
        }finally{
            closeJedis(jedis);
        }
        return -1;
    }
}

