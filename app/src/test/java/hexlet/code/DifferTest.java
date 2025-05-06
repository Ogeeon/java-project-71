package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DifferTest {
    private Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", fileName)
                .toAbsolutePath().normalize();
    }

    private String readFixture(String fileName) throws Exception {
        var path = getFixturePath(fileName);
        return Files.readString(path).trim();
    }

    @Test
    public void testFlatJsonCompare() throws Exception {
        var expected = readFixture("flat-json-compare-result.txt");
        var actual = Differ.generate("./src/test/resources/flat-json-compare-src1.json",
                "./src/test/resources/flat-json-compare-src2.json");
        assertEquals(expected, actual);
    }

    @Test
    public void testWrongPath1() {
        assertThrows(Exception.class, () -> Differ.generate("nonexistentfile",
                "./src/test/resources/flat-json-compare-src2.json"));
    }

    @Test
    public void testWrongPath2() {
        assertThrows(Exception.class, () -> Differ.generate("./src/test/resources/flat-json-compare-src1.json",
                "nonexistentfile"));
    }
}
