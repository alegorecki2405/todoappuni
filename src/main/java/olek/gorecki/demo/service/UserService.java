package olek.gorecki.demo.service;

import olek.gorecki.demo.proxyRepositories.ProxyUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final ProxyUserRepository userRepository;

    public UserService(ProxyUserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
