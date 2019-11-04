package ll.mrli.lixf.common.entity;

import java.util.HashMap;

/**
 * @author Lixf
 */
public class LixfResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = -7671383711834096775L;

    public LixfResponse message(String messages) {
        this.put("message", messages);
        return this;
    }

    public LixfResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    @Override
    public LixfResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public String getMessage() {
        return String.valueOf(get("message"));
    }

    public Object getData() {
        return get("data");
    }

}
