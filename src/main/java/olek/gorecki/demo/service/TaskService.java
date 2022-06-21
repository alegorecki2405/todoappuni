package olek.gorecki.demo.service;

import olek.gorecki.demo.proxyRepositories.ProxyTaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final ProxyTaskRepository taskRepository;

    public TaskService(ProxyTaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
}
