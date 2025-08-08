package core.basesyntax.service.operation;

import core.basesyntax.model.FruitTransaction;
import java.util.Map;

public class PurchaseHandler implements OperationHandler {
    @Override
    public void apply(FruitTransaction transaction, Map<String, Integer> storage) {
        int current = storage.getOrDefault(transaction.getFruit(), 0);
        if (current < transaction.getQuantity()) {
            System.out.println("Not enough product: " + transaction.getFruit());
            return;
        }
        storage.put(transaction.getFruit(), current - transaction.getQuantity());
    }
}
