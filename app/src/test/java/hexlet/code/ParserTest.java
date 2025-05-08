package hexlet.code;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    void testPrivateConstructor() throws Exception {
        Constructor<Parser> constructor = Parser.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Exception exception = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertEquals(AssertionError.class, exception.getCause().getClass());
    }

    @Test
    void testUnsupportedFileType() {
        assertThrows(IllegalArgumentException.class,
                () -> Parser.getFileContentMap("./src/test/resources/wrong-type-file.csv"));
    }
}
