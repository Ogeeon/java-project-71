package hexlet.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public final class DiffElement {
    private String key;
    private boolean isValue1Present;
    private Object value1;
    private boolean isValue2Present;
    private Object value2;

    public boolean isUnchanged() {
        return (isValue1Present && isValue2Present)
                && ((value1 == null && value2 == null)
                || (value1 != null && value1.equals(value2)));
    }
}
