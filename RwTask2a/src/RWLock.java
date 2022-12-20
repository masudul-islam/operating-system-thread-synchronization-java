public class RWLock {
    private int reading;
    private int writing;
    private int waitingWriter;

    public RWLock() {
        this.reading = 0;
        this.writing = 0;
        this.waitingWriter = 0;
    }

    public synchronized void acquireRead() {
        while (writing > 0 || waitingWriter > 0) {
            try {
                wait();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
        reading++;
    }

    public synchronized void acquireWrite() {
        waitingWriter++;
        while (writing > 0 || reading > 0) {
            try {
                wait();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
        writing++;
        waitingWriter--;
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
