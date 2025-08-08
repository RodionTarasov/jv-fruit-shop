package core.basesyntax.report;

import core.basesyntax.service.FruitCounting;
import core.basesyntax.service.FruitCountingImpl;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

public class FileWriterServiceImpl implements FileWriterService {

    @Override
    public void write() {
        File file = new File("report.csv");

        FruitCounting fruitCounting = new FruitCountingImpl();
        Map<String, Integer> fruit = fruitCounting.fruitCounting();

        ReportService reportService = new ReportServiceImpl();
        String report = reportService.generateReport(fruit);

        try {
            Files.write(file.toPath(), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
