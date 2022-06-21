package olek.gorecki.demo.repositories;

import olek.gorecki.demo.entities.User;
import olek.gorecki.demo.proxyRepositories.ProxyUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ProxyUserRepository, JpaRepository<User, Long> {
}
