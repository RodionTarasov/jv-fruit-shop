package core.basesyntax.service;

import core.basesyntax.model.FruitTransaction;
import java.util.List;
import java.util.Map;

public interface FruitCounting {
    Map<String, Integer> fruitCounting(List<FruitTransaction> fruitTransactions);
}
