package com.learntodrive.user_detail.learntodrive_user_detail.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "user_details")  // MongoDB Collection Name
public class User {
    @Id
    private String id;
    private String email;   // Used to connect with Auth Service
    private String dob;
    private String aadharNo;
    private String licenceNo;
}