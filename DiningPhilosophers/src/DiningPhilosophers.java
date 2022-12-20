public class DiningPhilosophers {

    public static void main(String[] args) {
        int size = 5; //number of philosophers and chopsticks
        Table table = new Table(size);
        for (int i = 0; i < size; i++) {
            Thread th = new Thread(new Philosopher(i, table));
            th.start();
        }
    }
}
