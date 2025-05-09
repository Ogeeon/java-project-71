package hexlet.code.formatters;

import hexlet.code.DiffElement;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class StylishFormatter implements DiffFormatter {
    private static final int IDENT_SPACES_COUNT = 2;

    public String format(List<DiffElement> differences) {
        return differences.stream()
                .flatMap(this::getDiffLines)
                .collect(Collectors.joining("\n", "{\n", "\n}"));
    }

    private Stream<String> getDiffLines(DiffElement e) {
        var sb = new StringBuilder();
        if (e.isUnchanged()) {
            sb.append(" ".repeat(IDENT_SPACES_COUNT))
                    .append("  ")
                    .append(e.getKey())
                    .append(": ")
                    .append(e.getValue1());
            return Stream.of(sb.toString());
        }
        if (e.isValue1Present()) {
            sb.append(" ".repeat(IDENT_SPACES_COUNT))
                    .append("- ")
                    .append(e.getKey())
                    .append(": ")
                    .append(e.getValue1());
        }
        if (e.isValue2Present()) {
            sb.append(sb.isEmpty() ? "" : "\n")
                    .append(" ".repeat(IDENT_SPACES_COUNT))
                    .append("+ ")
                    .append(e.getKey())
                    .append(": ")
                    .append(e.getValue2());
        }
        return Stream.of(sb.toString());
    }
}
