package com.cr.zshop.common.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author ：cr
 * @date ：Created in 2019/10/21 15:24
 * @description：$
 * @modified By：cr
 * @version: $
 */
public class RedisUtils {
    private static JedisPool jedisPool = null;

    static {
        jedisPool = (JedisPool) SpringBeanHolder.getBean("jedisPool");
    }

    public static void set(String key, String value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key,value);
        }
        catch(Exception e){
            e.printStackTrace();
        }finally {
            if(null != jedisPool){
                jedis.close();
            }
        }
    }
    /**
     * create by: cr
     * description: 设置，带过期时间
     * create time: 2019/10/21 15:33
     * params: [key, value, expireTime]
     * @return void
     */
    public static void set(String key, String value,int expireTime){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key,value);
            jedis.expire(key,expireTime);
        }
        catch(Exception e){
            e.printStackTrace();
        }finally {
            if(null != jedisPool){
                jedis.close();
            }
        }
    }
    public static String get(String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            return jedis.get(key);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null != jedis){
                jedis.close();
            }
        }
        return null;
    }
}
