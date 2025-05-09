package hexlet.code;

import hexlet.code.formatters.Formatter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class Differ {
    private Differ() {
        throw new AssertionError("Utility class should not be instantiated");
    }

    public static String generate(String filePath1, String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws IOException {
        if (filePath1 == null || filePath2 == null) {
            throw new IOException("Neither of file paths can be null");
        }
        var formatter = Formatter.get(format);
        Map<String, Object> map1 = Parser.getFileContentMap(filePath1);
        Map<String, Object> map2 = Parser.getFileContentMap(filePath2);
        Set<String> joinedKeySet = new HashSet<>(map1.keySet());
        joinedKeySet.addAll(map2.keySet());
        var difference = joinedKeySet.stream()
                .sorted()
                .map(key -> new DiffElement(key, map1.containsKey(key), map1.get(key),
                        map2.containsKey(key), map2.get(key)))
                .toList();
        return formatter.format(difference);
    }
}
