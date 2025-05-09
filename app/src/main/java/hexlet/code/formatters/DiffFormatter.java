package hexlet.code.formatters;

import hexlet.code.DiffElement;

import java.util.List;

public interface DiffFormatter {
    String format(List<DiffElement> differences);
}
