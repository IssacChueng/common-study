package cn.jeff.cloudstudyredislock;

public interface AquiredLockWorker<T> {
    T invokeAfterLockAquire() throws Exception;
}
