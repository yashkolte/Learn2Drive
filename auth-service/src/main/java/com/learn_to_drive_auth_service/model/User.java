package com.learn_to_drive_auth_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;
import java.util.Collection;

@Setter
@Getter
@Document(collection = "users") // Specify the MongoDB collection
public class User implements UserDetails {

    @Id
    private String id; // Unique identifier for MongoDB
    private String username;
    private String email;
    private String role;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    // Add other fields as needed (e.g., email, roles, etc.)

    public User(String username, String password, Collection<? extends GrantedAuthority> authorities,String role,String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Implement your logic if needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Implement your logic if needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Implement your logic if needed
    }

    @Override
    public boolean isEnabled() {
        return true; // Implement your logic if needed
    }

    // Add getters and setters for other fields
}
