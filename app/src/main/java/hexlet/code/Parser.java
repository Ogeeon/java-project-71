package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;

public final class Parser {
    private Parser() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    public static Map<String, Object> getContentMap(String content, String type) throws IOException {
        ObjectMapper objectMapper = getSuitableMapper(type);
        return objectMapper.readValue(content, new TypeReference<>() { });
    }

    private static ObjectMapper getSuitableMapper(String type) {
        return switch (type) {
            case "json" -> new ObjectMapper();
            case "yml" -> new ObjectMapper(new YAMLFactory());
            default -> throw new IllegalArgumentException("Unsupported input format: " + type);
        };
    }
}
