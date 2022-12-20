public class ReaderWriter {

    public static void main(String[] args) {
        System.out.println("By the end of the program the writers usually haven't finished writing all their values.\n" +
                           "That is because the readers continue reading without allowing the writer to update the\n" +
                           "value in the data object.");
        System.out.println();

        Data data = new Data();
        //create writers
        Thread rt;
        rt = new Thread(new Writer(0, data));
        rt.start();
        rt = new Thread(new Writer(1, data));
        rt.start();
        //create readers
        Thread th;
        for (int r = 0; r < 5; r++) {
            th = new Thread(new Reader(r, data));
            th.start();
        }
    }
}
