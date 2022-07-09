package olek.gorecki.demo.service;

import olek.gorecki.demo.entities.Role;
import olek.gorecki.demo.entities.Task;
import olek.gorecki.demo.entities.User;
import olek.gorecki.demo.models.TaskWriteModel;
import olek.gorecki.demo.repositories.TaskRepository;
import olek.gorecki.demo.repositories.UserRepository;
import olek.gorecki.demo.security.Auth;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final Auth auth;
    private final UserRepository userRepository;
    private final Long ONE_DAY_TO_MILLISECONDS = 86400000l;

    public TaskService(TaskRepository taskRepository, Auth auth, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.auth = auth;
        this.userRepository = userRepository;
    }

    public List<Task> findAllTasks() {
        if(auth.getAuth().getAuthorities().contains(Role.ADMIN)) {
            return taskRepository.findAll();
        }
        return taskRepository.findAllByUser_Email(auth.getAuth().getName());
    }

    public Task createTask(TaskWriteModel taskWriteModel) {
        Task task = new Task();
        task.setTitle(taskWriteModel.getTitle());
        task.setDescription(taskWriteModel.getDescription());
        task.setCreationDate(new Date());
        task.setDeadline(taskWriteModel.getDeadline());
        User user = userRepository.findByEmail(auth.getAuth().getName()).orElseThrow();
        user.getTasks().add(task);
        task.setUser(user);
        return taskRepository.save(task);
    }

    public void toggleTask(Long id) {
        if(!taskRepository.existsById(id)) {
            throw new IllegalArgumentException("Task with given id not found");
        }
        Task task =  taskRepository.findById(id).orElse(null);
        if(task!=null) {
            task.toggle();
            taskRepository.save(task);
        }
    }

    public List<Task> findAllUndoneTasksWithDeadlineWithinDay() {
        Date today = new Date();
        Long now = today.getTime();
        return taskRepository.findAll().stream()
                .filter(task -> !task.isDone() && task.getDeadline().getTime()-now<ONE_DAY_TO_MILLISECONDS )
                .collect(Collectors.toList());
    }
}
