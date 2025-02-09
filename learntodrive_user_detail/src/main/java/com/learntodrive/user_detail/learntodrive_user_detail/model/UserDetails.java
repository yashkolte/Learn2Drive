package com.learntodrive.user_detail.learntodrive_user_detail.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "user_details")  // MongoDB Collection Name
public class UserDetails {
    @Id
    private String id;
    private String userEmail;  // Linked with auth-service
    private String dob;
    private String aadharNo;
    private String licenceNo;
}