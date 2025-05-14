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
        var expected = readFixture("stylish-compare-result.txt");
        var actual = Differ.generate("./src/test/resources/json-compare-src1.json",
                "./src/test/resources/json-compare-src2.json");
        assertEquals(expected, actual);
    }

    @Test
    void testJsonCompareWithStylishFormatter() throws Exception {
        var expected = readFixture("stylish-compare-result.txt");
        var actual = Differ.generate("./src/test/resources/json-compare-src1.json",
                "./src/test/resources/json-compare-src2.json", "stylish");
        assertEquals(expected, actual);
    }

    @Test
    void testJsonCompareWithPlainFormatter() throws Exception {
        var expected = readFixture("plain-compare-result.txt");
        var actual = Differ.generate("./src/test/resources/json-compare-src1.json",
                "./src/test/resources/json-compare-src2.json", "plain");
        assertEquals(expected, actual);
    }

    @Test
    void testJsonCompareWithJsonFormatter() throws Exception {
        var expected = readFixture("json-compare-result.json");
        var actual = Differ.generate("./src/test/resources/json-compare-src1.json",
                "./src/test/resources/json-compare-src2.json", "json");
        assertEquals(expected, actual);
    }

    @Test
    void testYmlCompare() throws Exception {
        var expected = readFixture("yml-compare-result.txt");
        var actual = Differ.generate("./src/test/resources/yml-compare-src1.yml",
                "./src/test/resources/yml-compare-src2.yml", "stylish");
        assertEquals(expected, actual);
    }

    @Test
    void testPrivateConstructor() throws Exception {
        Constructor<Differ> constructor = Differ.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Exception exception = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertEquals(UnsupportedOperationException.class, exception.getCause().getClass());
    }

    @Test
    void testNullPath1() {
        assertThrows(IOException.class, () -> Differ.generate(null,
                "./src/test/resources/json-compare-src2.json", "stylish"));
    }

    @Test
    void testNullPath2() {
        assertThrows(IOException.class, () -> Differ.generate("./src/test/resources/json-compare-src1.json",
                null, "stylish"));
    }

    @Test
    void testWrongPath1() {
        assertThrows(IOException.class, () -> Differ.generate("nonexistentfile",
                "./src/test/resources/json-compare-src2.json", "stylish"));
    }

    @Test
    void testWrongPath2() {
        assertThrows(IOException.class, () -> Differ.generate("./src/test/resources/json-compare-src1.json",
                "nonexistentfile", "stylish"));
    }
}
