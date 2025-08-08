package core.basesyntax;

import core.basesyntax.report.FileWriterService;
import core.basesyntax.report.FileWriterServiceImpl;

public class Main {
    public static void main(String[] args) {
        FileWriterService fileWriterService = new FileWriterServiceImpl();
        fileWriterService.write();

    }
}
