package com.anderson.testing_docker.dataprovider.user.repositories.port;

import com.anderson.testing_docker.dataprovider.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringUserRepository extends JpaRepository<UserEntity, UUID> {
}
