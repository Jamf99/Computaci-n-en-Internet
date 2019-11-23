package co.edu.icesi.ci.talleres.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.ci.talleres.model.UserApp;
import co.edu.icesi.ci.talleres.model.UserType;

public interface UserRepository extends CrudRepository<UserApp, Long> {
	
	public List<UserApp> findByUsername(String username);
	
	public List<UserApp> findByType(UserType type);

}