package com.luizviana.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luizviana.workshopmongo.dto.UserDTO;
import com.luizviana.workshopmongo.entities.User;
import com.luizviana.workshopmongo.repository.UserRepository;
import com.luizviana.workshopmongo.services.exception.ObjectNotFoundException;


@Service
public class UserServices {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findByID(String id) {
		Optional<User> user = repository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("ID "+ id + " not Found"));
	}
	
	public User insert(User obj) {
		return repository.insert(obj);
	}
	
	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(),objDTO.getName(), objDTO.getEmail());
	}

	public void delete(String id) {
		findByID(id);
		repository.deleteById(id);
	}
	
	public User update(User obj) {
		User newObj = findByID(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	private void updateData(User newObj, User user) {
		newObj.setName(user.getName());
		newObj.setEmail(user.getEmail());
		
	}
}
