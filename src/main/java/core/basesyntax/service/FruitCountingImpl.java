package core.basesyntax.service;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.operation.OperationHandler;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FruitCountingImpl implements FruitCounting {
    private final OperationStrategy operationStrategy;

    public FruitCountingImpl(OperationStrategy operationStrategy) {
        this.operationStrategy = operationStrategy;
    }

    @Override
    public Map<String, Integer> fruitCounting(List<FruitTransaction> fruitTransactions) {
        Map<String, Integer> fruitStorage = new HashMap<>();
        for (FruitTransaction fruit : fruitTransactions) {
            OperationHandler operationHandler = operationStrategy.get(fruit.getOperation());
            operationHandler.apply(fruit, fruitStorage);
        }
        return fruitStorage;
    }
}
