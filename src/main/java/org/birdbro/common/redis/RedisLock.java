package org.birdbro.common.redis;

import org.birdbro.common.redis.lock.DistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author birdbro
 * @date 14:48 2022-12-12
 **/
@Slf4j
@Component
public class RedisLock implements DistributedLock {

    private StringRedisTemplate redisTemplate;

    String lockKey;
    int expireMsecs = 60000;
    int timeoutMsecs = 10000;
    boolean locked = false;


    public RedisLock(StringRedisTemplate redisTemplate, String lockKey) {
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey;
    }

    public RedisLock(StringRedisTemplate redisTemplate, String lockKey, int timeoutMsecs) {
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey;
        this.timeoutMsecs = timeoutMsecs;
    }

    public RedisLock(StringRedisTemplate redisTemplate, String lockKey, int timeoutMsecs, int expireMsecs) {
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey;
        this.timeoutMsecs = timeoutMsecs;
        this.expireMsecs = expireMsecs;
    }

    public String getLockKey() {
        return this.lockKey;
    }

    @Override
    public synchronized boolean acquire() {
        int timeout = this.timeoutMsecs;

        try {
            while(timeout >= 0) {
                long expires = System.currentTimeMillis() + (long)this.expireMsecs + 1L;
                String expiresStr = String.valueOf(expires);
                if (this.redisTemplate.opsForValue().setIfAbsent(this.lockKey, expiresStr)) {
                    this.locked = true;
                    return true;
                }

                String currentValueStr = (String)this.redisTemplate.opsForValue().get(this.lockKey);
                if (currentValueStr != null && Long.parseLong(currentValueStr) < System.currentTimeMillis()) {
                    String oldValueStr = (String)this.redisTemplate.opsForValue().getAndSet(this.lockKey, expiresStr);
                    if (oldValueStr != null && oldValueStr.equals(currentValueStr)) {
                        this.locked = true;
                        return true;
                    }
                }

                timeout -= 100;
                Thread.sleep(100L);
            }
        } catch (Exception var7) {
            log.error("release lock due to error", var7);
        }

        return false;
    }

    @Override
    public synchronized void release() {
        try {
            if (this.locked) {
                String currentValueStr = (String)this.redisTemplate.opsForValue().get(this.lockKey);
                if (currentValueStr != null && Long.parseLong(currentValueStr) > System.currentTimeMillis()) {
                    this.redisTemplate.delete(this.lockKey);
                    this.locked = false;
                }
            }
        } catch (Exception var2) {
            log.error("release lock due to error", var2);
        }

    }
}
