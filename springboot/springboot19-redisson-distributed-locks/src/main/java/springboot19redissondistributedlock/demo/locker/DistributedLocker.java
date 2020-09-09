package springboot19redissondistributedlock.demo.locker;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * @author aodeng-低调小熊猫
 * @since 20-9-9
 */
public interface DistributedLocker {

    RLock lock(String lockKey);

    RLock lock(String lockKey, int timeout);

    RLock lock(String lockKey, TimeUnit unit, int timeout);

    boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime);

    void unlock(String lockKey);

    void unlock(RLock lock);
}
