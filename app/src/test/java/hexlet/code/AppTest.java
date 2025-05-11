package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AppTest {
    @Test
    void testAppInstantiation() {
        App app = new App();
        assertNotNull(app);
    }

    @Test
    void testRunMethod() {
        App app = new App();
        // No parameters in test, so method will throw an exception
        assertThrows(RuntimeException.class, app::run);
    }
}
