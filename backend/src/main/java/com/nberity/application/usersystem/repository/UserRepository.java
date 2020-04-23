package com.nberity.application.usersystem.repository;

import com.nberity.application.usersystem.entity.RepositoryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<RepositoryUser, Long> {

    RepositoryUser findByUsername(String username);
}
