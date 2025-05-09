package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class JsonFormatter implements DiffFormatter {
    @Override
    public String format(List<DiffElement> differences) {
        return differences.stream()
                .map(this::getDiffJson)
                .filter(Objects::nonNull)
                .collect(Collectors.joining(",\n", "{", "}"));
    }

    private String getDiffJson(DiffElement e) {
        if (e.isUnchanged()) {
            return null;
        }
        var sb = new StringBuilder();
        sb.append("\"").append(e.getKey()).append("\":");
        String status;
        if (e.isValue1Present() && e.isValue2Present()) {
            status = "updated";
        } else {
            if (e.isValue1Present()) {
                status = "removed";
            } else {
                status = "added";
            }
        }
        var item = new HelperPOJO(status, e.getValue1(), e.getValue2());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            sb.append(objectMapper.writeValueAsString(item));
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
        return sb.toString();
    }
}

@Getter
@Setter
@AllArgsConstructor
@ToString
class HelperPOJO {
    private String status;
    private Object oldValue;
    private Object newValue;
}
