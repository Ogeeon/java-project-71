package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public final class Parser {
    private Parser() {
        throw new IllegalStateException("Utility class");
    }

    public static Map<String, String> getFileContentMap(String fileName) throws IOException {
        Path path = Paths.get(fileName).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new IOException("File '" + path + "' does not exist");
        }
        ObjectMapper objectMapper = getSuitableMapper(detectFileType(fileName));
        return objectMapper.readValue(Files.readString(path), new TypeReference<>() { });
    }

    private static String detectFileType(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
    }

    private static ObjectMapper getSuitableMapper(String fileType) {
        return switch (fileType) {
            case "json" -> new ObjectMapper();
            case "yml" ->  new ObjectMapper(new YAMLFactory());
            default -> throw new UnsupportedOperationException("Unsupported input format: " + fileType);
        };
    }
}
