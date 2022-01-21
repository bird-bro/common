package com.bird.common.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author bird
 * @date 2021-7-21 14:33
 **/
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ValueOperations<String, String> valueOperations;
    @Autowired
    private HashOperations<String, String, Object> hashOperations;
    @Autowired
    private ListOperations<String, Object> listOperations;
    @Autowired
    private SetOperations<String, Object> setOperations;
    @Autowired
    private ZSetOperations<String, Object> zSetOperations;
    public static final long DEFAULT_EXPIRE = 86400L;
    public static final long NOT_EXPIRE = -1L;
    public static final long ONEHOUR_EXPIRE = 3600L;
    public static final long CAPTCHA_EXPIRE = 60L;
    public static final long SMS_CODE_EXPIRE = 300L;


    public void set(String key, Object value, long expire) {
        this.valueOperations.set(key, this.toJson(value));
        if (expire != -1L) {
            this.redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }


    public void set(String key, Object value) {
        this.set(key, value, 86400L);
    }

    public <T> T get(String key, Class<T> clazz, long expire) {
        String value = (String)this.valueOperations.get(key);
        if (expire != -1L) {
            this.redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }

        return value == null ? null : this.fromJson(value, clazz);
    }

    public <T> List<T> getList(String key, Class<T> clazz, long expire) {
        String value = (String)this.valueOperations.get(key);
        if (expire != -1L) {
            this.redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }

        return value == null ? null : this.fromListJson(value, clazz);
    }

    public <T> T get(String key, Class<T> clazz) {
        return this.get(key, clazz, -1L);
    }

    public <T> List<T> getList(String key, Class<T> clazz) {
        return this.getList(key, clazz, -1L);
    }

    public String get(String key, long expire) {
        String value = (String)this.valueOperations.get(key);
        if (expire != -1L) {
            this.redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }

        return value;
    }

    public String get(String key) {
        return this.get(key, -1L);
    }

    public void delete(String key) {
        this.redisTemplate.delete(key);
    }

    private String toJson(Object object) {
        return !(object instanceof Integer) && !(object instanceof Long) && !(object instanceof Float) && !(object instanceof Double) && !(object instanceof Boolean) && !(object instanceof String) ? JSON.toJSONString(object) : String.valueOf(object);
    }

    private <T> T fromJson(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    private <T> List<T> fromListJson(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }








}
