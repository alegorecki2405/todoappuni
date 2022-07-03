package olek.gorecki.demo.proxyRepositories;

import olek.gorecki.demo.entities.User;

import java.util.List;

public interface ProxyUserRepository {
    User save(User userEntity);

    List<User> findAll();
}
