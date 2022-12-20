import java.util.logging.Level;
import java.util.logging.Logger;

public class Philosopher implements Runnable {
    private int id;
    private Table myTable;
    private boolean hasLeft;
    private boolean hasRight;
    private int givesUp;
    private final int MAX_PATIENCE;

    public Philosopher(int pid, Table tab) {
        id = pid;
        myTable = tab;
        hasLeft = false;
        hasRight = false;
        givesUp = 0;
        MAX_PATIENCE = 5;
    }

    @Override
    public void run() {
        int patience;

        for (int i = 0; i < 100; i++) {
            try {
                patience = 0;
                do {
                    //think
                    System.out.println("? Philosopher " + id + " thinks. Iteration " + i);
                    Thread.sleep((int) (Math.random() * 100 * (1 + i) / (1 + patience)));
                    patience++;
                    //pick up left chopstick
                    if (!hasLeft) {
                        hasLeft = myTable.getLeft(id);
                        System.out.println("| Philosopher " + id + " pick up left " + hasLeft);
                        if (hasLeft) {
                            patience = 0;
                        }
                    }
                    Thread.sleep((int) (Math.random() * 100));
                    //pick up right chopstick
                    if (!hasRight) {
                        hasRight = myTable.getRight(id);
                        System.out.println("| Philosopher " + id + " pick up right " + hasRight);
                        if (hasRight) {
                            patience = 0;
                        }
                    }
                    if (patience == MAX_PATIENCE) {
                        System.out.println("X Philosopher " + id + " gives up");
                        givesUp++;
                        patience = 0;
                        if (hasLeft) {
                            myTable.releaseLeft(id);
                            hasLeft = false;
                            System.out.println("- Philosopher " + id + " drop left");
                        }
                        if (hasRight) {
                            myTable.releaseRight(id);
                            hasRight = false;
                            System.out.println("- Philosopher " + id + " drop right");
                        }
                    }
                } while (!hasLeft || !hasRight);
                //eat
                System.out.println("+ Philosopher " + id + " eats. Iteration " + i);
                Thread.sleep((int) (Math.random() * 100));
                //release chopsticks
                myTable.releaseLeft(id);
                hasLeft = false;
                System.out.println("- Philosopher " + id + " drop left");
                Thread.sleep((int) (Math.random() * 10));
                myTable.releaseRight(id);
                hasRight = false;
                System.out.println("- Philosopher " + id + " drop right");
            } catch (InterruptedException ex) {
                Logger.getLogger(Philosopher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("= Philosopher " + id + " gave up " + givesUp + " times.");
    }
}
