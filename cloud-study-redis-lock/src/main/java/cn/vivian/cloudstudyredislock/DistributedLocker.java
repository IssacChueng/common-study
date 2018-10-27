package cn.vivian.cloudstudyredislock;

public interface DistributedLocker {
    <T> T lock(String resourceName, AquiredLockWorker<T> worker) throws Exception;

    <T> T lock(String resourceName, AquiredLockWorker<T> worker, int lockTime) throws Exception;
}
