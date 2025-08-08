package core.basesyntax.service;

import core.basesyntax.file.MyFileReader;
import core.basesyntax.file.MyFileReaderImpl;
import core.basesyntax.model.FruitTransaction;
import java.util.List;

public class DataConverterImpl implements DataConverter {
    private static final String SEPARATOR = ",";

    @Override
    public List<FruitTransaction> get() {
        MyFileReader reader = new MyFileReaderImpl();
        List<String> transactions = reader.read();

        return transactions.stream()
                .skip(1)
                .map(this::getFromCsv)
                .toList();
    }

    public FruitTransaction getFromCsv(String line) {
        FruitTransaction fruitTransaction = new FruitTransaction();
        String[] words = line.split(SEPARATOR);
        fruitTransaction.setOperation(fromCode(words[0]));
        fruitTransaction.setFruit(words[1]);
        fruitTransaction.setQuantity(Integer.parseInt(words[2]));
        return fruitTransaction;
    }

    public FruitTransaction.Operation fromCode(String code) {
        for (FruitTransaction.Operation operation : FruitTransaction.Operation.values()) {
            if (operation.getCode().equals(code)) {
                return operation;
            }
        }
        throw new IllegalArgumentException("Unknown operation code: " + code);
    }
}
