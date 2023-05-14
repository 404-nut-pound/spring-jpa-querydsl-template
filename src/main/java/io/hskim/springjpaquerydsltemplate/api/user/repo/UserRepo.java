package io.hskim.springjpaquerydsltemplate.api.user.repo;

import io.hskim.springjpaquerydsltemplate.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, String> {}
