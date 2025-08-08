package core.basesyntax.service;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.operation.OperationHandler;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FruitCountingImpl implements FruitCounting {
    private OperationStrategy operationStrategy =
            new OperationStrategyImpl(new OperationHandlerRegistry().getOperation());

    @Override
    public Map<String, Integer> fruitCounting() {
        Map<String, Integer> fruitStorage = new HashMap<>();
        DataConverter dataConverter = new DataConverterImpl();
        List<FruitTransaction> fruitTransactions = dataConverter.get();

        for (FruitTransaction fruit : fruitTransactions) {
            OperationHandler operationHandler = operationStrategy.get(fruit.getOperation());
            operationHandler.apply(fruit, fruitStorage);
        }

        return fruitStorage;
    }
}
