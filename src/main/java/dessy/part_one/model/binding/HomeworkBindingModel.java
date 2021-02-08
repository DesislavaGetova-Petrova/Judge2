package dessy.part_one.model.binding;

import javax.validation.constraints.Pattern;

public class HomeworkBindingModel {
    private String exercise;
    private String gitAddress;

    public HomeworkBindingModel() {
    }

    public String getExercise() {
        return exercise;
    }

    public HomeworkBindingModel setExercise(String exercise) {
        this.exercise = exercise;
        return this;
    }
    @Pattern(regexp ="https:\\/\\/github.com\\/\\/.+\\/.+",message = "Enter valid git address")
    public String getGitAddress() {
        return gitAddress;
    }

    public HomeworkBindingModel setGitAddress(String gitAddress) {
        this.gitAddress = gitAddress;
        return this;
    }
}
