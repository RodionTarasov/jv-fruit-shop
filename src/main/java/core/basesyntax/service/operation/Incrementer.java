package core.basesyntax.service.operation;

import java.util.Map;

public class Incrementer {
    public static void add(Map<String, Integer> storage, String fruit, int quantity) {
        int current = storage.getOrDefault(fruit, 0);
        storage.put(fruit, current + quantity);
    }
}
