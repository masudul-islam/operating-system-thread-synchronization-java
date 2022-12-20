import java.util.logging.Level;
import java.util.logging.Logger;

//A reader reads the content of a message
//In the run method 10 readings are performed before exit
//Each reader gets an id which can be used to track the running of individual threads
public class Reader implements Runnable {
    private int id;
    private Data myData;

    public Reader(int nID, Data inData) {
        id = nID;
        myData = inData;
    }

    @Override
    public void run() {
        for (int i = 0; i < 4; i++) {
            try {
                Thread.sleep((int) (Math.random() * 100));
                myData.read(id);
            } catch (InterruptedException ex) {
                Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
