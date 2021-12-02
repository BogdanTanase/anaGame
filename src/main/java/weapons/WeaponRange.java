package weapons;

import java.util.HashMap;
import java.util.Map;

public class WeaponRange {
    public static Map<String , Integer> longRange = new HashMap<>();
    public static Map<String, Integer> closeRange = new HashMap<>();

    static {
        longRange.put("sniper", 3);
        longRange.put("kalasnikov",2);
        longRange.put("knife", 1);

        closeRange.put("kalasnikov", 3);
        closeRange.put("sniper", 2);
        closeRange.put("knife", 1);
    }
}
