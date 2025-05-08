package hexlet.code;

import java.util.List;
import java.util.stream.Collectors;

public class StylishFormatter implements DiffFormatter {
    private static final int IDENT_SPACES_COUNT = 2;

    public String format(List<DiffElement> differences) {
        return differences.stream()
                .map(element -> " ".repeat(IDENT_SPACES_COUNT)
                        + element.getDiffType() + " "
                        + element.getKey() + ": "
                        + element.getValue())
                .collect(Collectors.joining("\n", "{\n", "\n}"));
    }
}
