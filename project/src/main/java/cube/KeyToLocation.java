package cube;

import java.util.HashMap;

public class KeyToLocation {
    private static HashMap<String, Location> map = new HashMap<>();
    static {
        map.put("U", new Location(0, 2, 2));
        map.put("I", new Location(1, 2, 2));
        map.put("O", new Location(2, 2, 2));

        map.put("J", new Location(0, 1, 2));
        map.put("K", new Location(1, 1, 2));
        map.put("L", new Location(2, 1, 2));

        map.put("M", new Location(0, 0, 2));
        map.put(",", new Location(1, 0, 2));
        map.put(".", new Location(2, 0, 2));

        map.put("P", new Location(2, 2, 1));
        map.put("[", new Location(2, 2, 0));
    }
    static Location get(String s){
        Location result = map.get(s);
        if (result == null) result = new Location(-1, 0 ,0);
        return result;
    }
}
