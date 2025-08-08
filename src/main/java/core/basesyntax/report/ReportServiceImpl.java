package core.basesyntax.report;

import java.util.Map;

public class ReportServiceImpl implements ReportService {
    private static final String REPORT = "fruit,quantity" + System.lineSeparator();

    @Override
    public String generateReport(Map<String, Integer> fruitStorage) {
        StringBuilder builder = new StringBuilder(REPORT);
        fruitStorage.entrySet().stream()
                .map(entry -> entry.getKey() + "," + entry.getValue())
                .forEach(b -> builder.append(b)
                        .append(System.lineSeparator()));

        return builder.toString();
    }
}
