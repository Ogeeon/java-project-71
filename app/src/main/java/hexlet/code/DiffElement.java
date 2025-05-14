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
    public enum Status {
        UNCHANGED,
        UPDATED,
        REMOVED,
        ADDED
    }

    private boolean isUnchanged() {
        return (isValue1Present && isValue2Present)
                && ((value1 == null && value2 == null)
                || (value1 != null && value1.equals(value2)));
    }

    public Status getStatus() {
        if (isUnchanged()) {
            return Status.UNCHANGED;
        }
        if (isValue1Present()) {
            if (isValue2Present()) {
                return Status.UPDATED;
            } else {
                return Status.REMOVED;
            }
        } else {
            return Status.ADDED;
        }
    }
}
