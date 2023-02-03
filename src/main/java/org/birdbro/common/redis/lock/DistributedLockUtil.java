package org.birdbro.common.redis.lock;

import org.birdbro.common.redis.RedisLock;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author birdbro
 * @date 14:49 2022-12-12
 **/
public class DistributedLockUtil {

    public static DistributedLock getDistributedLock(StringRedisTemplate redisTemplate, String lockKey) {
        lockKey = assembleKey(lockKey);
        RedisLock lock = new RedisLock(redisTemplate, lockKey);
        return lock;
    }

    private static String assembleKey(String lockKey) {
        return String.format("lock_%s", lockKey);
    }

    public static DistributedLock getDistributedLock(StringRedisTemplate redisTemplate, String lockKey, int timeoutMsecs) {
        lockKey = assembleKey(lockKey);
        RedisLock lock = new RedisLock(redisTemplate, lockKey, timeoutMsecs);
        return lock;
    }

    public static DistributedLock getDistributedLock(StringRedisTemplate redisTemplate, String lockKey, int timeoutMsecs, int expireMsecs) {
        lockKey = assembleKey(lockKey);
        RedisLock lock = new RedisLock(redisTemplate, lockKey, timeoutMsecs, expireMsecs);
        return lock;
    }

}
