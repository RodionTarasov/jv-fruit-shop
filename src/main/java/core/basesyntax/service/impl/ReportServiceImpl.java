package core.basesyntax.service.impl;

import core.basesyntax.service.ReportService;
import java.util.Map;

public class ReportServiceImpl implements ReportService {
    private static final String REPORT_HEADER = "fruit,quantity" + System.lineSeparator();

    @Override
    public String generateReport(Map<String, Integer> fruitStorage) {
        StringBuilder builder = new StringBuilder(REPORT_HEADER);
        fruitStorage.entrySet().stream()
                .map(entry -> entry.getKey() + "," + entry.getValue())
                .forEach(line -> builder.append(line)
                        .append(System.lineSeparator()));

        return builder.toString();
    }
}
