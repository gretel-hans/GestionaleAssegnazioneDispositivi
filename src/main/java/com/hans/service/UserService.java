package com.hans.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hans.model.User;
import com.hans.repository.UserPageableRepository;
import com.hans.repository.UserRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	@Autowired UserRepository db;
	@Autowired UserPageableRepository dbPage;
	
	boolean giaPresente;
	
    public User findUser(Long id) {
    	if (UserExists(id)) {
			return db.findById(id).get();
		}else 
			throw new EntityExistsException("ERROR!! User passed doesn't exist!");
    }
    
    public User updateUser(User u) {
    	List<User> listaUtenti=db.findAll();
    	giaPresente=false;
    	listaUtenti.forEach(utente->{
    		if(u.equals(utente)) {
    			giaPresente=true;    		
    			throw new EntityNotFoundException("ERROR!! User passed already exist!");
    		}
    	});
    	if(!giaPresente)
    		 db.save(u);
		     return u;
    }
    
    public List<User> findAllUsers(){
    	return db.findAll();
    }
    
    public Page<User> findAllUsersPage(Pageable p){
    	return dbPage.findAll(p);
    }
    
    public boolean UserExists(Long id) {
    	if(db.existsById(id)) {
    		return true;
    	}else 
    		return false;
    }
    
    boolean trovato;
    public boolean UserExistsDb(User u) {
    	trovato=false;
    	db.findAll().forEach(e->{
    		Example<User> example = Example.of(u);
    		if(db.exists(example)) {
    			trovato= true;
    		}
    		
    	});
    	if(!trovato) {
    		return false;
    	}else
    		return true;
    	
    }
    
    
}
