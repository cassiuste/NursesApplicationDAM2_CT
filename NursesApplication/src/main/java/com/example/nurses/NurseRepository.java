package com.example.nurses;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface NurseRepository extends CrudRepository<Nurse, Integer>{
	
	Optional<Nurse> findByUserAndPass(String user, String pass);
	Optional<Nurse> findByName(String name);
	
}
