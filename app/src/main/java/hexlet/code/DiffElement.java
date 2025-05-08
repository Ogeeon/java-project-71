package hexlet.code;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class DiffElement {
    private String diffType;
    private String key;
    private Object value;
}
