package core.basesyntax.service.impl;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.FruitService;
import core.basesyntax.strategy.OperationStrategy;
import core.basesyntax.strategy.operation.OperationHandler;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FruitServiceImpl implements FruitService {
    private final OperationStrategy operationStrategy;

    public FruitServiceImpl(OperationStrategy operationStrategy) {
        this.operationStrategy = operationStrategy;
    }

    @Override
    public Map<String, Integer> process(List<FruitTransaction> fruitTransactions) {
        Map<String, Integer> fruitStorage = new HashMap<>();
        for (FruitTransaction transaction : fruitTransactions) {
            OperationHandler operationHandler = operationStrategy.get(transaction.getOperation());
            operationHandler.apply(transaction, fruitStorage);
        }
        return fruitStorage;
    }

}
