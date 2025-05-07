package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AppTest {
    @Test
    void testAppInstantiation() {
        App app = new App();
        assertNotNull(app);
    }

    @Test
    void testCallMethod() {
        App app = new App();
        // No parameters in test, so method will throw an exception
        assertThrows(IOException.class, app::call);
    }
}
