package com.shaswat.ToDoTracker.service;


import com.shaswat.ToDoTracker.domain.Task;
import com.shaswat.ToDoTracker.domain.User;
import com.shaswat.ToDoTracker.exception.TaskNotFoundException;
import com.shaswat.ToDoTracker.exception.UserAlreadyExistsException;
import com.shaswat.ToDoTracker.exception.UserNotFoundException;
import com.shaswat.ToDoTracker.repository.UserTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicInteger;

import java.util.*;



@Service
public class UserTaskServiceImpl implements IUserTaskService{
    private UserTaskRepository userTaskRepository;
    private AtomicInteger itemCounter = new AtomicInteger(0);

//    public int getNextItemNumber() {
//        return itemCounter.incrementAndGet();
//    }
public int generateId() {
    Random random = new Random();
    int otp = 0;

    for (int i = 0; i < 6; i++) {
        otp = otp * 10 + random.nextInt(10);
    }

    return otp;
}
    @Autowired
    public UserTaskServiceImpl(UserTaskRepository userTaskRepository) {
        this.userTaskRepository = userTaskRepository;
    }


//User Work

    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        User user1 = userTaskRepository.findByEmailId(user.getEmailId());

        if (user1 == null) {
            return userTaskRepository.save(user);

        }
        else {
            System.out.println("already exist");
            throw new UserAlreadyExistsException();
        }

    }

    @Override
    public User loginCheck(String emailId, String password) throws UserNotFoundException {
        User user = userTaskRepository.findByEmailIdAndPassword(emailId,password);
        if(user==null){
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public User updateUser(User newUser) throws UserNotFoundException {
        User user =null;
        User oldUser = userTaskRepository.findByEmailId(newUser.getEmailId());
        if(oldUser == null){
            throw  new UserNotFoundException();
        }
        else{
            if (oldUser.getEmailId().equalsIgnoreCase(newUser.getEmailId())){
                user = oldUser;
                user.setPassword(newUser.getPassword());
                userTaskRepository.save(user);
            }
            else {
                return new User();
            }
        }
        return user;
    }
    @Override
    public List<User> getAllUsers() {
        return userTaskRepository.findAll();
    }

    @Override
    public User getUserByEmailId(String emailId) throws UserNotFoundException {
        if (userTaskRepository.findByEmailId(emailId) == null){
            throw new UserNotFoundException();
        }
        return userTaskRepository.findByEmailId(emailId);
    }


    @Override
    public boolean deleteAllUser() {
        userTaskRepository.deleteAll();
        return true;
    }

    @Override
    public boolean deleteUserById(String emailId) throws UserNotFoundException{
        if (userTaskRepository.findByEmailId(emailId) == null){
            throw new UserNotFoundException();
        }
        userTaskRepository.deleteByEmailId(emailId);
        return true;
    }












    @Override
    public Task addTask(String emailId, Task task) {

        User user1 = userTaskRepository.findByEmailId(emailId);
        List<Task> tasks = user1.getTasks();
        if(tasks == null){
            tasks = new ArrayList<>();
        }
        task.setTaskId(generateId());
        tasks.add(task);
        user1.setTasks(tasks);
        userTaskRepository.save(user1);
        return task;
    }

    @Override
    public Task updateTask( Task task,String emailId) {
        User user1 = userTaskRepository.findByEmailId(emailId);
        List<Task> tasks = user1.getTasks();
        for (Task taskToUpdate: tasks) {
            if (taskToUpdate.getTaskId() == task.getTaskId()){
                taskToUpdate.setTaskName(task.getTaskName());
                taskToUpdate.setTaskContent(task.getTaskContent());
            }
        }
        userTaskRepository.save(user1);
        return task;

    }



    @Override
    public List<Task> getAllTasksOfUser(String emailId) {
        User user1 = userTaskRepository.findByEmailId(emailId);
        List<Task> tasks = user1.getTasks();
        return tasks;
    }

    @Override
    public Task getTaskByTaskId(String emailId, int taskId) throws TaskNotFoundException {
        User user1 = userTaskRepository.findByEmailId(emailId);
        List<Task> tasks = user1.getTasks();
        Task task = tasks.stream()
                            .filter(obj -> taskId==(obj.getTaskId()))
                            .findAny().orElse(null);
        if(tasks == null || !tasks.contains(task)){
            throw new TaskNotFoundException();
        }
        userTaskRepository.save(user1);

        return task;
    }



    @Override
    public boolean deleteTaskByTaskId(String emailId, int taskId) throws TaskNotFoundException {
        User user = userTaskRepository.findByEmailId(emailId);
        List<Task> tasks = user.getTasks();
        Task task = tasks.stream()
                .filter(obj -> taskId==(obj.getTaskId()))
                .findAny().orElse(null);
        if(tasks == null || !tasks.contains(task)){
            throw new TaskNotFoundException();
        }
        tasks.remove(task);
        user.setTasks(tasks);
        userTaskRepository.save(user);

        return true;
    }
}
