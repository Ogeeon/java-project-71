package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AppTest {
    @Test
    public void testAppInstantiation() {
        App app = new App();
        assertNotNull(app);
    }

    @Test
    public void testCallMethod() throws Exception {
        App app = new App();
        // No parameters in test, so method will throw an exception
        assertThrows(Exception.class, app::call);
    }
}
