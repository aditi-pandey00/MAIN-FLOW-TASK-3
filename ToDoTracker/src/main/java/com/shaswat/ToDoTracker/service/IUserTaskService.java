package com.shaswat.ToDoTracker.service;


import com.shaswat.ToDoTracker.domain.Task;
import com.shaswat.ToDoTracker.domain.User;
import com.shaswat.ToDoTracker.exception.TaskNotFoundException;
import com.shaswat.ToDoTracker.exception.UserAlreadyExistsException;
import com.shaswat.ToDoTracker.exception.UserNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IUserTaskService {
    User saveUser (User user) throws UserAlreadyExistsException;
    User loginCheck(String emailId, String password) throws UserNotFoundException;
    User updateUser (User user) throws  UserNotFoundException;
    List<User> getAllUsers ();
    User getUserByEmailId (String emailId) throws UserNotFoundException;
    boolean deleteAllUser ();
    boolean deleteUserById (String emailId) throws UserNotFoundException;
    Task addTask (String emailId, Task task);
    Task updateTask (Task task,String emailId);
    List<Task> getAllTasksOfUser (String emailId);
    Task getTaskByTaskId (String emailId, int taskId) throws TaskNotFoundException;
    boolean deleteTaskByTaskId (String emailId, int taskId) throws TaskNotFoundException;

}
