public class RWLock {
    private int reading;
    private int writing;

    public RWLock() {
        this.reading = 0;
        this.writing = 0;
    }

    public synchronized void acquireRead() {
        while (writing > 0) {
            try {
                wait();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
        reading++;
    }

    public synchronized void acquireWrite() {
        while (writing > 0 || reading > 0) {
            try {
                wait();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
        writing++;
    }

    public synchronized void releaseRead() {
        reading--;

        notifyAll();
    }

    public synchronized void releaseWrite() {
        writing--;

        notifyAll();
    }

}
