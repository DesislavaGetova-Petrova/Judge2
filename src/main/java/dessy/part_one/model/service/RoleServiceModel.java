package dessy.part_one.model.service;

import dessy.part_one.model.entity.Role;

public class RoleServiceModel extends BaseServiceModel {
    private String name;

    public RoleServiceModel() {
    }

    public String getName() {
        return name;
    }

    public RoleServiceModel setName(String name) {
        this.name = name;
        return this;
    }
}
