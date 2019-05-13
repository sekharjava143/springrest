package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dao.RestUserDao;
import com.spring.model.RestUser;

@Service
@Transactional
public class RestUserServiceImpl implements RestUserService {
	@Autowired
	private RestUserDao dao;

	public void saveRestUser(RestUser usr) {
		dao.saveRestUser(usr);
	}

	public List<RestUser> findAllRestUsers() {
		return dao.findAllRestUsers();
	}

	public void deleteRestUserById(int id) {
		dao.deleteRestUserById(id);
	}

	public RestUser findById(int id) {
		return dao.findById(id);
	}

	public void updateRestUser(RestUser User) {
		dao.updateRestUser(User);
	}
	
	public boolean findRestUser(RestUser usr) {
		return dao.findRestUser(usr);
	}
}
