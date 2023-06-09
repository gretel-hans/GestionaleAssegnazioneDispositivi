package com.hans.service;

import com.hans.model.User;
import com.hans.payload.LoginDto;
import com.hans.payload.RegisterDto;

public interface AuthService {
    
	String login(LoginDto loginDto);
    User register(RegisterDto registerDto);
    
}
