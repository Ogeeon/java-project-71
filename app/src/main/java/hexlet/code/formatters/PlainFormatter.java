package hexlet.code.formatters;

import hexlet.code.DiffElement;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PlainFormatter implements DiffFormatter {
    public String format(List<DiffElement> differences) {
        return differences.stream()
                .map(this::getDiffLine)
                .filter(Objects::nonNull)
                .collect(Collectors.joining("\n"));
    }

    private String getDiffLine(DiffElement e) {
        if (e.isUnchanged()) {
            return null;
        }
        var sb = new StringBuilder("Property '");
        sb.append(e.getKey());
        sb.append("' was ");
        if (e.isValue1Present() && e.isValue2Present()) {
            sb.append("updated. ")
                    .append("From ")
                    .append(getValueRepresentation(e.getValue1()))
                    .append(" to ")
                    .append(getValueRepresentation(e.getValue2()));
        } else {
            if (e.isValue1Present()) {
                sb.append("removed");
            } else {
                sb.append("added with value: ");
                sb.append(getValueRepresentation(e.getValue2()));
            }
        }
        return sb.toString();
    }

    private String getValueRepresentation(Object value) {
        if (isSimpleValue(value)) {
            if (value == null) {
                return "null";
            }
            if (value instanceof String) {
                return "'" + value + "'";
            }
            return value.toString();
        } else {
            return "[complex value]";
        }
    }

    private boolean isSimpleValue(Object value) {
        return value == null
                || value instanceof Number
                || value instanceof String
                || value instanceof Boolean;
    }
}
