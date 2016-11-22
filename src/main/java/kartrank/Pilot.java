package kartrank;

/**
 * Simple POJO representing one pilot
 */
public class Pilot {

    private Integer code;

    private String name;

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
