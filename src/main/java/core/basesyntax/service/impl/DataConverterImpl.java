package core.basesyntax.service.impl;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.DataConverter;
import java.util.List;

public class DataConverterImpl implements DataConverter {
    private static final String SEPARATOR = ",";
    private static final int ARRAY_LENGTH = 3;

    @Override
    public List<FruitTransaction> convertToTransaction(List<String> transactions) {
        return transactions.stream()
                .skip(1)
                .map(this::getFromCsv)
                .toList();
    }

    private FruitTransaction getFromCsv(String line) {
        String[] words = line.split(SEPARATOR);
        if (words.length < ARRAY_LENGTH) {
            throw new IllegalArgumentException("invalid array length");
        }
        int quantity = Integer.parseInt(words[2]);
        if (quantity < 0) {
            throw new IllegalArgumentException(
                    "Quantity cannot be negative. Found: " + quantity + " in line: " + line);
        }
        return new FruitTransaction(fromCode(words[0]), words[1], quantity);
    }

    private FruitTransaction.Operation fromCode(String code) {
        for (FruitTransaction.Operation operation : FruitTransaction.Operation.values()) {
            if (operation.getCode().equals(code)) {
                return operation;
            }
        }
        throw new IllegalArgumentException("Unknown operation code: " + code);
    }
}
