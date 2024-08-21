package com.shaswat.ToDoTracker.service;



import com.shaswat.ToDoTracker.domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {

    Map<String, String> generateToken(User user);
}
