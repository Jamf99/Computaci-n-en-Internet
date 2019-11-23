package co.edu.icesi.ci.talleres.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.ci.talleres.dao.UserRepository;
import co.edu.icesi.ci.talleres.model.UserApp;

@Service
public class UserService {
	
	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	
	
	public void save(UserApp user) {
		userRepository.save(user);

	}

	
	
	public Optional<UserApp> findById(long id) {

		return userRepository.findById(id);
	}

	public Iterable<UserApp> findAll() {
		return userRepository.findAll();
	}

	public void delete(UserApp user) {
		userRepository.delete(user);

	}
	
}
