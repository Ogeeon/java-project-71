package hexlet.code;

import hexlet.code.formatters.Formatter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class Differ {
    private Differ() {
        // Использование AssertionError было продиктовано тем, что разработчику, столкнувшемуся с ним,
        // мы передаём сообщение: "Твоё представление о том, как работать с этим классом - ошибочно".
        // Я натолкнулся на такой совет в интернете, когда расширял покрытие кода тестами по отчёту JaCoCo.
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    public static String generate(String filePath1, String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws IOException {
        if (filePath1 == null || filePath2 == null) {
            throw new IOException("Neither of file paths can be null");
        }
        var formatter = Formatter.get(format);
        Map<String, Object> map1 = Parser.getContentMap(getFileContent(filePath1), detectFileType(filePath1));
        Map<String, Object> map2 = Parser.getContentMap(getFileContent(filePath2), detectFileType(filePath2));
        Set<String> joinedKeySet = new HashSet<>(map1.keySet());
        joinedKeySet.addAll(map2.keySet());
        var difference = joinedKeySet.stream()
                .sorted()
                .map(key -> new DiffElement(key, map1.containsKey(key), map1.get(key),
                        map2.containsKey(key), map2.get(key)))
                .toList();
        return formatter.format(difference);
    }

    public static String getFileContent(String fileName) throws IOException {
        Path path = Paths.get(fileName).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new IOException("File '" + path + "' does not exist");
        }
        return Files.readString(path);
    }

    private static String detectFileType(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
    }
}
