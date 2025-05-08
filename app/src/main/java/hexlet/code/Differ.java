package hexlet.code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public final class Differ {
    private Differ() {
        throw new AssertionError("Utility class should not be instantiated");
    }

    public static String generate(String filePath1, String filePath2, DiffFormatter formatter) throws IOException {
        if (filePath1 == null || filePath2 == null) {
            throw new IOException("Neither of file paths can be null");
        }
        if (formatter == null) {
            throw new IllegalArgumentException("Formatter cannot be null");
        }
        Map<String, Object> map1 = Parser.getFileContentMap(filePath1);
        Map<String, Object> map2 = Parser.getFileContentMap(filePath2);
        Set<String> joinedKeySet = new HashSet<>(map1.keySet());
        joinedKeySet.addAll(map2.keySet());
        var difference = joinedKeySet.stream()
                .sorted()
                .flatMap(key -> getDifference(key, map1, map2))
                .toList();
        return formatter.format(difference);
    }

    private static Stream<DiffElement> getDifference(String key, Map<String, Object> map1, Map<String, Object> map2) {
        var result = new ArrayList<DiffElement>();
        var value1 = map1.get(key);
        var value2 = map2.get(key);
        var isEqual = map1.containsKey(key) && map2.containsKey(key)
                && ((value1 == null && value2 == null)
                    || (value1 != null && value1.equals(value2)));
        if (map1.containsKey(key)) {
            result.add(new DiffElement(isEqual ? " " : "-", key, value1));
        }
        if (map2.containsKey(key)) {
            result.add(new DiffElement(isEqual ? " " : "+", key, value2));
        }
        return result.stream().distinct();
    }
}
