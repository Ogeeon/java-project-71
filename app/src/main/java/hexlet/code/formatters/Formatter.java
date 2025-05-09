package hexlet.code.formatters;

public final class Formatter {
    private Formatter() {
        throw new AssertionError("Utility class should not be instantiated");
    }

    public static DiffFormatter get(String format) {
        return switch (format) {
            case "stylish" -> new StylishFormatter();
            case "plain" -> new PlainFormatter();
            default -> throw new IllegalArgumentException("Unsupported format name: " + format);
        };
    }
}
