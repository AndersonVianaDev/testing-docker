package com.anderson.testing_docker.config.beans;

import com.anderson.testing_docker.core.user.dataprovider.UserRepository;
import com.anderson.testing_docker.core.user.usecases.impl.*;
import com.anderson.testing_docker.core.user.usecases.ports.*;
import com.anderson.testing_docker.dataprovider.user.repositories.impl.UserRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserBeans {

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryImpl();
    }

    @Bean
    public RegisterUserUseCasePort registerUserUseCasePort(UserRepository repository) {
        return new RegisterUserUseCaseImpl(repository);
    }

    @Bean
    public FindUserByIdUseCasePort findUserByIdUseCasePort(UserRepository repository) {
        return new FindUserByIdUseCaseImpl(repository);
    }

    @Bean
    public FindAllUsersUseCasePort findAllUsersUseCasePort(UserRepository repository) {
        return new FindAllUsersUseCaseImpl(repository);
    }

    @Bean
    public DeleteUserUseCasePort deleteUserUseCasePort(UserRepository repository, FindUserByIdUseCasePort findUserById) {
        return new DeleteUserUseCaseImpl(repository, findUserById);
    }

    @Bean
    public UpdateUserUseCasePort updateUserUseCasePort(UserRepository repository, FindUserByIdUseCasePort findUserById) {
        return new UpdateUserUseCaseImpl(repository, findUserById);
    }

}
