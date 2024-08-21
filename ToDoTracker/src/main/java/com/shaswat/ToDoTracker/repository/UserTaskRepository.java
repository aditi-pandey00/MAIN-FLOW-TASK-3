package com.shaswat.ToDoTracker.repository;


import com.shaswat.ToDoTracker.domain.User;
import com.shaswat.ToDoTracker.exception.UserNotFoundException;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserTaskRepository extends MongoRepository<User, String> {

    User findByEmailId(String emailId);
    void deleteByEmailId(String id);

    @Query("{'tasks.taskId': ?0}")
    User findByTaskId (int taskId);

    User findByEmailIdAndPassword(String emailId, String password) throws UserNotFoundException;

}
