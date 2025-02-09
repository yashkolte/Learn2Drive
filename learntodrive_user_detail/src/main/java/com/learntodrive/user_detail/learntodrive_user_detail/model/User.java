package com.learntodrive.user_detail.learntodrive_user_detail.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "users")  // MongoDB Collection Name
public class User {
    @Id
    @Indexed(unique = true)
    private String id;
    private String name;
    @Indexed(unique = true)
    private String email;
    private String password;
    private String role = "USER";
}
