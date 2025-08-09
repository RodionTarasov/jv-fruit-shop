package core.basesyntax;

import core.basesyntax.file.MyFileReader;
import core.basesyntax.file.MyFileReaderImpl;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.report.FileWriterService;
import core.basesyntax.report.FileWriterServiceImpl;
import core.basesyntax.report.ReportService;
import core.basesyntax.report.ReportServiceImpl;
import core.basesyntax.service.DataConverter;
import core.basesyntax.service.DataConverterImpl;
import core.basesyntax.service.FruitCounting;
import core.basesyntax.service.FruitCountingImpl;
import core.basesyntax.service.OperationStrategy;
import core.basesyntax.service.OperationStrategyImpl;
import core.basesyntax.service.operation.BalanceHandler;
import core.basesyntax.service.operation.OperationHandler;
import core.basesyntax.service.operation.PurchaseHandler;
import core.basesyntax.service.operation.ReturnHandler;
import core.basesyntax.service.operation.SupplyHandler;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        MyFileReader reader = new MyFileReaderImpl();
        List<String> readFile = reader.read("reportToRead.csv");

        DataConverter dataConverter = new DataConverterImpl();
        List<FruitTransaction> fruitTransactions = dataConverter.get(readFile);

        Map<FruitTransaction.Operation, OperationHandler> operationHandlerMap = new HashMap<>();
        operationHandlerMap.put(FruitTransaction.Operation.BALANCE, new BalanceHandler());
        operationHandlerMap.put(FruitTransaction.Operation.SUPPLY, new SupplyHandler());
        operationHandlerMap.put(FruitTransaction.Operation.PURCHASE, new PurchaseHandler());
        operationHandlerMap.put(FruitTransaction.Operation.RETURN, new ReturnHandler());

        OperationStrategy strategy = new OperationStrategyImpl(operationHandlerMap);
        FruitCounting fruitCounting = new FruitCountingImpl(strategy);
        Map<String, Integer> stringIntegerMap = fruitCounting.fruitCounting(fruitTransactions);

        ReportService reportService = new ReportServiceImpl();
        String report = reportService.generateReport(stringIntegerMap);

        FileWriterService writerService = new FileWriterServiceImpl();
        writerService.write(report, "finalReport.csv");
    }
}
