package dessy.part_one.security;

import dessy.part_one.model.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class CurrentUser {
    private String id;
    private String username;
    private String role;

    public CurrentUser() {
    }

    public String getId() {
        return id;
    }

    public CurrentUser setId(String id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public CurrentUser setRole(String role) {
        this.role = role;
        return this;
    }

    public CurrentUser setUsername(String username) {
        this.username = username;
        return this;
    }
    public  boolean isAnonymos(){
        return this.username==null;
    }
    public boolean isAdmin(){
        return this.role.equals("ADMIN");
    }
}
