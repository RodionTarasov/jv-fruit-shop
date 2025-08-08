package core.basesyntax.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MyFileReaderImpl implements MyFileReader {
    private static final String FILE_NAME = "reportToRead.csv";

    @Override
    public List<String> read() {
        try {
            return Files.readAllLines(Path.of(FILE_NAME));
        } catch (IOException e) {
            throw new RuntimeException("Can't read file",e);
        }
    }
}
