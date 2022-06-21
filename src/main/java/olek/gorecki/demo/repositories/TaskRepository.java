package olek.gorecki.demo.repositories;

import olek.gorecki.demo.entities.Task;
import olek.gorecki.demo.proxyRepositories.ProxyTaskRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends ProxyTaskRepository, JpaRepository<Task, Long> {
}
