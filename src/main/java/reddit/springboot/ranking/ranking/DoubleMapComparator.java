package reddit.springboot.ranking.ranking;

import java.util.Comparator;
import java.util.Map;

public class DoubleMapComparator implements Comparator<String> {

    private Map<String, Integer> map;
    
    public DoubleMapComparator(Map<String, Integer> map) {
        this.map = map;
    }

    @Override
    public int compare(String key1, String key2) {
        Integer val1 = this.map.get(key1);
        Integer val2 = this.map.get(key2);
        if (val1 != null && val2 != null) {
            return (val1 >= val2 ? - 1 : 1);
        }
        return 0;
    }
}
