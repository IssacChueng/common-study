package cn.vivian.cloudstudyredislock;

public interface AquiredLockWorker<T> {
    T invokeAfterLockAquire() throws Exception;
}
