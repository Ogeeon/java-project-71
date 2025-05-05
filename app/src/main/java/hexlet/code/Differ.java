package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        Map<String, String> map1 = getFileContent(filePath1);
        Map<String, String> map2 = getFileContent(filePath2);
        var m1ks = map1.keySet();
        var m2ks = map2.keySet();
        Set<String> union = new HashSet<>(m1ks);
        union.addAll(m2ks);
        var output = union.stream()
                .sorted()
                .map(k -> {
                    var v1 = map1.get(k);
                    var v2 = map2.get(k);
                    var result = new StringBuilder();
                    if (v1 != null && v1.equals(v2)) {
                        result.append("    ").append(k).append(": ").append(v1);
                    } else {
                        if (v1 != null) {
                            result.append("  - ").append(k).append(": ").append(v1);
                        }
                        if (v2 != null) {
                            result.append(result.isEmpty() ? "" : "\n").append("  + ").append(k).append(": ").append(v2);
                        }
                    }
                    return result.toString();
                })
                .collect(Collectors.joining("\n", "{\n", "\n}"));
        return output;
    }

    private static Map<String, String> getFileContent(String fileName) throws Exception {
        Path path = Paths.get(fileName).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(Files.readString(path), new TypeReference<>() {});
    }
}
