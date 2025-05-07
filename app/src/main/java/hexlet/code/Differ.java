package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public final class Differ {
    private Differ() {
        throw new IllegalStateException("Utility class");
    }

    public static String generate(String filePath1, String filePath2) throws IOException {
        if (filePath1 == null || filePath2 == null) {
            throw new IOException("Neither of file paths can be null");
        }
        Map<String, String> map1 = getFileContent(filePath1);
        Map<String, String> map2 = getFileContent(filePath2);
        Set<String> joinedKeySet = new HashSet<>(map1.keySet());
        joinedKeySet.addAll(map2.keySet());
        return joinedKeySet.stream()
                .sorted()
                .map(key -> getDifferenceString(key, map1.get(key), map2.get(key)))
                .collect(Collectors.joining("\n", "{\n", "\n}"));
    }

    private static Map<String, String> getFileContent(String fileName) throws IOException {
        Path path = Paths.get(fileName).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new IOException("File '" + path + "' does not exist");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(Files.readString(path), new TypeReference<>() { });
    }

    private static String getDifferenceString(String key, String value1, String value2) {
        var result = new StringBuilder();
        if (value1 != null && value1.equals(value2)) {
            result.append("    ").append(key).append(": ").append(value1);
        } else {
            if (value1 != null) {
                result.append("  - ").append(key).append(": ").append(value1);
            }
            if (value2 != null) {
                result.append(result.isEmpty() ? "" : "\n")
                        .append("  + ").append(key).append(": ").append(value2);
            }
        }
        return result.toString();
    }
}
