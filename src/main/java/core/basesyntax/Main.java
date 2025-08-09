package core.basesyntax;

import core.basesyntax.db.FileReader;
import core.basesyntax.db.FileReaderImpl;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.DataConverter;
import core.basesyntax.service.FileWriterService;
import core.basesyntax.service.FruitService;
import core.basesyntax.service.ReportService;
import core.basesyntax.service.impl.DataConverterImpl;
import core.basesyntax.service.impl.FileWriterServiceImpl;
import core.basesyntax.service.impl.FruitServiceImpl;
import core.basesyntax.service.impl.ReportServiceImpl;
import core.basesyntax.strategy.OperationStrategy;
import core.basesyntax.strategy.OperationStrategyImpl;
import core.basesyntax.strategy.operation.BalanceHandler;
import core.basesyntax.strategy.operation.OperationHandler;
import core.basesyntax.strategy.operation.PurchaseHandler;
import core.basesyntax.strategy.operation.ReturnHandler;
import core.basesyntax.strategy.operation.SupplyHandler;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        FileReader reader = new FileReaderImpl();
        List<String> readFile = reader.read("reportToRead.csv");

        DataConverter dataConverter = new DataConverterImpl();
        List<FruitTransaction> fruitTransactions = dataConverter.convertToTransaction(readFile);

        Map<FruitTransaction.Operation, OperationHandler> operationHandlerMap = new HashMap<>();
        operationHandlerMap.put(FruitTransaction.Operation.BALANCE, new BalanceHandler());
        operationHandlerMap.put(FruitTransaction.Operation.SUPPLY, new SupplyHandler());
        operationHandlerMap.put(FruitTransaction.Operation.PURCHASE, new PurchaseHandler());
        operationHandlerMap.put(FruitTransaction.Operation.RETURN, new ReturnHandler());

        OperationStrategy strategy = new OperationStrategyImpl(operationHandlerMap);
        FruitService fruitCounting = new FruitServiceImpl(strategy);
        Map<String, Integer> finalFruitCounts = fruitCounting.process(fruitTransactions);

        ReportService reportService = new ReportServiceImpl();
        String report = reportService.generateReport(finalFruitCounts);

        FileWriterService writerService = new FileWriterServiceImpl();
        writerService.write(report, "finalReport.csv");
    }
}
