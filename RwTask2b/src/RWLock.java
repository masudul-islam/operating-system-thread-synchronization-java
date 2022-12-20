import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWLock {
    private final Lock r;
    private final Lock w;

    public RWLock() {
        ReentrantReadWriteLock rwl = new ReentrantReadWriteLock(true);
        r = rwl.readLock();
        w = rwl.writeLock();
    }

    public void acquireRead() {
        r.lock();
    }

    public void acquireWrite() {
        w.lock();
    }

    public void releaseRead() {
        r.unlock();
    }

    public void releaseWrite() {
        w.unlock();
    }

}
