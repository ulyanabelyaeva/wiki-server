package wiki.dto.tag;

import javax.validation.constraints.NotNull;

public class NewTagDto {

    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NewTagDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
