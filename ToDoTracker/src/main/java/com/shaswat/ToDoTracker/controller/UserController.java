package com.shaswat.ToDoTracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.shaswat.ToDoTracker.domain.LoginResponse;
import com.shaswat.ToDoTracker.domain.Task;
import com.shaswat.ToDoTracker.domain.User;
import com.shaswat.ToDoTracker.exception.TaskNotFoundException;
import com.shaswat.ToDoTracker.exception.UserAlreadyExistsException;
import com.shaswat.ToDoTracker.exception.UserNotFoundException;
import com.shaswat.ToDoTracker.service.SecurityTokenGenerator;
import com.shaswat.ToDoTracker.service.UserTaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserTaskServiceImpl userTaskService;
    private SecurityTokenGenerator securityTokenGenerator;
    @Autowired
    public UserController(UserTaskServiceImpl userTaskService, SecurityTokenGenerator securityTokenGenerator) {
        this.userTaskService = userTaskService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @PostMapping("/save")
    public ResponseEntity<?> registerUser (@RequestBody User user) throws UserAlreadyExistsException{
        try{
//            User user1=userTaskService.saveUser(user);
            return new ResponseEntity<>(userTaskService.saveUser(user), HttpStatus.CREATED);
        }catch(UserAlreadyExistsException e){
            throw new UserAlreadyExistsException();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginFun(@RequestBody User user) throws UserNotFoundException {
        try {
            System.out.println("Before Mapping");
            User user1 = userTaskService.loginCheck(user.getEmailId(), user.getPassword());
            System.out.println("After Mapping");
            Map<String, String> secretKey = new HashMap<>();
            secretKey = securityTokenGenerator.generateToken(user);
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setUser(user1);
            loginResponse.setSecretKeyToken(secretKey);
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (Exception e) {
            return new ResponseEntity<>("Network Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> userUpdate (@RequestBody User user) throws UserNotFoundException{
        try{
            return new ResponseEntity<>(userTaskService.updateUser(user), HttpStatus.OK);
        }catch(UserNotFoundException e){
            throw new UserNotFoundException();
        }catch(Exception e){
            return new ResponseEntity<>("Server, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/task/getAllUsers")
    public ResponseEntity<?> getAllUsers (){
        return new ResponseEntity<>(userTaskService.getAllUsers(), HttpStatus.OK);
    }
    @GetMapping("/task/getUser/{emailId}")
    public ResponseEntity<?> getUserByEmailId (@PathVariable String emailId) throws UserNotFoundException{
        try{
            return new ResponseEntity<>(userTaskService.getUserByEmailId(emailId), HttpStatus.OK);
        }catch(UserNotFoundException e){
            throw new UserNotFoundException();
        }catch(Exception e){
            return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/task/deleteAllUser")
    public ResponseEntity<?> deleteAllUser () {
        return new ResponseEntity<>(userTaskService.deleteAllUser(), HttpStatus.OK);
    }

    @DeleteMapping("/task/deleteUser/{emailId}")
    public ResponseEntity<?> deleteUserById (@PathVariable String emailId) throws UserNotFoundException {
        try{
            return new ResponseEntity<>(userTaskService.deleteUserById(emailId), HttpStatus.OK);
        }catch(UserNotFoundException e){
            throw new UserNotFoundException();
        }catch(Exception e){
            return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PutMapping("/task/addTask/{emailId}")
    public ResponseEntity<?> addTask (@PathVariable String emailId, @RequestBody Task task) {
        return new ResponseEntity<>(userTaskService.addTask(emailId, task), HttpStatus.CREATED);
    }

    @PutMapping("/task/updateTask/{emailId}")
    public ResponseEntity<?> updateTask (@PathVariable String emailId, @RequestBody Task task) {
        return new ResponseEntity<>(userTaskService.updateTask(task,emailId ), HttpStatus.OK);
    }

    @GetMapping("/task/getAllTasksOfUser/{emailId}")
    public ResponseEntity<?> getAllTasksOfUser (@PathVariable String emailId) {
        return new ResponseEntity<>(userTaskService.getAllTasksOfUser(emailId), HttpStatus.OK);
    }

    @GetMapping("/task/getByTaskId/{emailId}/{taskId}")
    public ResponseEntity<?> getTaskByTaskId (@PathVariable String emailId, @PathVariable int taskId) throws TaskNotFoundException {
        try{
            return new ResponseEntity<>(userTaskService.getTaskByTaskId(emailId, taskId), HttpStatus.OK);
        }catch(TaskNotFoundException e){
            throw new TaskNotFoundException();
        }catch(Exception e){
            return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/task/deleteTaskByTaskId/{emailId}/{taskId}")
    public ResponseEntity<?> deleteTaskByTaskId (@PathVariable String emailId, @PathVariable int taskId) throws TaskNotFoundException {
        try{
            return new ResponseEntity<>(userTaskService.deleteTaskByTaskId(emailId, taskId), HttpStatus.OK);
        }catch(TaskNotFoundException e){
            throw new TaskNotFoundException();
        }catch(Exception e){
            return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
