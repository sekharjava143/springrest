package com.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.spring.model.RestUser;
 
@Repository
public class RestUserDaoImpl extends AbstractDao implements RestUserDao{
 
    public void saveRestUser(RestUser usr) {
        persist(usr);
    }
 
    @SuppressWarnings("unchecked")
    public List<RestUser> findAllRestUsers() {
        Criteria criteria = getSession().createCriteria(RestUser.class);
        return (List<RestUser>) criteria.list();
    }
 
    public void deleteRestUserById(int id) {
        Query query = getSession().createSQLQuery("delete from RestUser where userId = :userId");
        query.setInteger("userId", id);
        query.executeUpdate();
    }
 
     
    public RestUser findById(int id){
        Criteria criteria = getSession().createCriteria(RestUser.class);
        criteria.add(Restrictions.eq("userId", id));
        return (RestUser) criteria.uniqueResult();
    }
     
    public void updateRestUser(RestUser usr){
        getSession().update(usr);
    }
    
    public boolean findRestUser(RestUser usr) {
        Criteria criteria = getSession().createCriteria(RestUser.class);
        criteria.add(Restrictions.eq("userId", usr.getUserId()));
    	
        usr = (RestUser) criteria.uniqueResult();
        
        if(usr == null)
        	return false;
        
        return true;
    }
}