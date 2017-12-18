import java.util.HashMap;
import java.util.Map;

public class CountMapImpl<T> implements CountMap<T> {

    private Map<T, Integer> map = new HashMap<>();

    @Override
    public void add(T object) {
        if (!map.containsKey(object))
            map.put(object, 1);
        else map.put(object, map.get(object) + 1);
    }

    @Override
    public int getCount(T object) {
        return map.get(object);
    }

    @Override
    public int remove(T object) {
        return map.remove(object);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void addAll(CountMap<? extends T> source) {
        try {
            Map<T, Integer> data = (Map<T, Integer>) source.toMap();  // преобразование типа может вызвать ClassCastException
            for (Map.Entry<T, Integer> pair : data.entrySet()) {  // entrySet() возвращает массив Объектов содержащих пары ключ:значение
                T object = pair.getKey();
                Integer count = pair.getValue();
                if (!map.containsKey(object))
                    map.put(object, count);
                else map.put(object, map.get(object) + count);
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map toMap() {
        return map;
    }

    @Override
    public void toMap(Map destination) {
        destination = map;
    }

    public static void main(String[] args) {
        CountMap<Integer> map = new CountMapImpl<>();
        map.add(10); // 10 - object
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);
        int count1 = map.getCount(5); // 2
        int count2 = map.getCount(6); // 1
        int count3 = map.getCount(10); // 3
        System.out.println(count1);
        System.out.println(count2);
        System.out.println(count3);
    }
}
