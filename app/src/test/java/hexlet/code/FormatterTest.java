package hexlet.code;

import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FormatterTest {
    @Test
    void testStylishOption() {
        var actual = Formatter.get("stylish");
        assertInstanceOf(StylishFormatter.class, actual);
    }

    @Test
    void testPlainOption() {
        var actual = Formatter.get("plain");
        assertInstanceOf(PlainFormatter.class, actual);
    }

    @Test
    void testUnknownOption() {
        assertThrows(IllegalArgumentException.class, () -> Formatter.get("wrongformatname"));
    }

    @Test
    void testPrivateConstructor() throws Exception {
        Constructor<Formatter> constructor = Formatter.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Exception exception = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertEquals(AssertionError.class, exception.getCause().getClass());
    }
}
