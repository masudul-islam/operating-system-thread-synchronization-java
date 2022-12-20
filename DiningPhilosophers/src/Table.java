import java.util.concurrent.Semaphore;
public class Table {

    private int nbrOfChopsticks;
    private Semaphore[] chopsticks;

    public Table(int nbrOfSticks) {
        nbrOfChopsticks = nbrOfSticks;
        chopsticks = new Semaphore[nbrOfChopsticks];
        for (int i = 0; i < nbrOfChopsticks; i++) {
            chopsticks[i] = new Semaphore(2);
        }
    }

    public boolean getLeft(int n) {
        //philosopher n picks up its left chopstick
        return chopsticks[n].tryAcquire();
    }

    public boolean getRight(int n) {
        //philosopher n picks up its right chopstick
        int pos = n + 1;
        if (pos == nbrOfChopsticks) {
            pos = 0;
        }
        return chopsticks[pos].tryAcquire();
    }

    public void releaseLeft(int n) {
        //philosopher n puts down its left chopstick
        chopsticks[n].release();
    }

    public void releaseRight(int n) {
        //philosopher n puts down its right chopstick
        int pos = n + 1;
        if (pos == nbrOfChopsticks) {
            pos = 0;
        }
        chopsticks[pos].release();
    }
}

