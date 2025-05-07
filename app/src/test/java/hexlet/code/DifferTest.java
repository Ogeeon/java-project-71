package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DifferTest {
    private Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", fileName)
                .toAbsolutePath().normalize();
    }

    private String readFixture(String fileName) throws Exception {
        var path = getFixturePath(fileName);
        return Files.readString(path).trim();
    }

    @Test
    void testFlatJsonCompare() throws Exception {
        var expected = readFixture("flat-json-compare-result.txt");
        var actual = Differ.generate("./src/test/resources/flat-json-compare-src1.json",
                "./src/test/resources/flat-json-compare-src2.json");
        assertEquals(expected, actual);
    }

    @Test
    void testWrongPath1() {
        assertThrows(IOException.class, () -> Differ.generate("nonexistentfile",
                "./src/test/resources/flat-json-compare-src2.json"));
    }

    @Test
    void testWrongPath2() {
        assertThrows(IOException.class, () -> Differ.generate("./src/test/resources/flat-json-compare-src1.json",
                "nonexistentfile"));
    }
}
