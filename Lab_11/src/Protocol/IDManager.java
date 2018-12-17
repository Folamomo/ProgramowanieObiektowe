package Protocol;

import java.util.ArrayList;
import java.util.Random;

public class IDManager {
    private ArrayList IDs= new ArrayList<Long>();
    private Random random = new Random();

    public String getId() {
        String ID = null;
        do {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 10; ++i) {
                builder.append(random.nextInt(9));
            }
            ID = builder.toString();
        } while (IDs.contains(ID));
        IDs.add(ID);

        return ID;
    }

    public boolean removeID(String ID){
        return IDs.remove(ID);
    }

    public boolean checkID(String ID){
        return IDs.contains(ID);
    }
}
