package com.jppla.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jppla.workshopmongo.domain.User;
import com.jppla.workshopmongo.dto.UserDTO;
import com.jppla.workshopmongo.repository.UserRepository;
import com.jppla.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll() {
		
		return repo.findAll();			
	}
	
	public User findById(String id) {
		Optional<User> obj= repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
		}
	
	public User insert(User obj) { // insere usuários
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id); // realiza a busca e caso nao exista realiza a exceção
		repo.deleteById(id);
	}
	
	public User update(User entity) {

	     User newObj = repo.findById(entity.getId()).get();
	     updateData(newObj, entity);
	     return repo.save(newObj);
	}
	
	public void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
	
	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}
}
