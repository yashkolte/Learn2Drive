package com.learntodrive.user_detail.learntodrive_user_detail.repo;

import com.learntodrive.user_detail.learntodrive_user_detail.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}
