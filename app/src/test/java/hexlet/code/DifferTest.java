package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
    void testJsonCompare() throws Exception {
        var expected = readFixture("json-compare-result.txt");
        var actual = Differ.generate("./src/test/resources/json-compare-src1.json",
                "./src/test/resources/json-compare-src2.json",
                new StylishFormatter());
        assertEquals(expected, actual);
    }

    @Test
    void testFlatYmlCompare() throws Exception {
        var expected = readFixture("flat-yml-compare-result.txt");
        var actual = Differ.generate("./src/test/resources/flat-yml-compare-src1.yml",
                "./src/test/resources/flat-yml-compare-src2.yml",
                new StylishFormatter());
        assertEquals(expected, actual);
    }

    @Test
    void testPrivateConstructor() throws Exception {
        Constructor<Differ> constructor = Differ.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Exception exception = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertEquals(AssertionError.class, exception.getCause().getClass());
    }

    @Test
    void testNullPath1() {
        assertThrows(IOException.class, () -> Differ.generate(null,
                "./src/test/resources/json-compare-src2.json",
                new StylishFormatter()));
    }

    @Test
    void testNullPath2() {
        assertThrows(IOException.class, () -> Differ.generate("./src/test/resources/json-compare-src1.json",
                null, new StylishFormatter()));
    }

    @Test
    void testWrongPath1() {
        assertThrows(IOException.class, () -> Differ.generate("nonexistentfile",
                "./src/test/resources/json-compare-src2.json",
                new StylishFormatter()));
    }

    @Test
    void testWrongPath2() {
        assertThrows(IOException.class, () -> Differ.generate("./src/test/resources/json-compare-src1.json",
                "nonexistentfile", new StylishFormatter()));
    }

    @Test
    void testNullFormatter() {
        assertThrows(IllegalArgumentException.class,
                () -> Differ.generate("./src/test/resources/json-compare-src1.json",
                "./src/test/resources/json-compare-src2.json", null));
    }
}
