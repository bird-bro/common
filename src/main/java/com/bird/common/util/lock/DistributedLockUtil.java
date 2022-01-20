package com.bird.common.util.lock;

import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author bird
 * @date 2021-7-22 15:53
 **/
public class DistributedLockUtil {

    public static DistributedLock getDistributedLock(StringRedisTemplate redisTemplate, String lockKey) {
        lockKey = assembleKey(lockKey);
        JedisLock lock = new JedisLock(redisTemplate, lockKey);
        return lock;
    }

    private static String assembleKey(String lockKey) {
        return String.format("lock_%s", lockKey);
    }

    public static DistributedLock getDistributedLock(StringRedisTemplate redisTemplate, String lockKey, int timeoutMsecs) {
        lockKey = assembleKey(lockKey);
        JedisLock lock = new JedisLock(redisTemplate, lockKey, timeoutMsecs);
        return lock;
    }

    public static DistributedLock getDistributedLock(StringRedisTemplate redisTemplate, String lockKey, int timeoutMsecs, int expireMsecs) {
        lockKey = assembleKey(lockKey);
        JedisLock lock = new JedisLock(redisTemplate, lockKey, timeoutMsecs, expireMsecs);
        return lock;
    }
}
