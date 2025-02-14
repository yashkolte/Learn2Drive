package com.learntodrive.user_detail.learntodrive_user_detail.repo;

import com.learntodrive.user_detail.learntodrive_user_detail.model.UserDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends MongoRepository<UserDetails, String> {
    Optional<UserDetails> findByUserEmail(String userEmail);
}
