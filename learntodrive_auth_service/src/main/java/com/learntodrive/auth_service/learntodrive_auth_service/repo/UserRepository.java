package com.learntodrive.auth_service.learntodrive_auth_service.repo;
import com.learntodrive.auth_service.learntodrive_auth_service.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
