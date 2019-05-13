package com.spring.dao;

import java.util.List;

import com.spring.model.RestUser;

public interface RestUserDao {
	void saveRestUser(RestUser usr);
    
    List<RestUser> findAllRestUsers();
     
    void deleteRestUserById(int id);
     
    RestUser findById(int id);
     
    void updateRestUser(RestUser usr);
    
    boolean findRestUser(RestUser usr);
}
