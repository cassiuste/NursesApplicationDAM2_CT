package com.example.nurses;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/nurse")
public class NurseController {
	
	@Autowired
	private NurseRepository nurseRepository;
	
	@GetMapping("/index")
	public @ResponseBody ResponseEntity<List<Nurse>> getAll() {
		Iterable<Nurse> optionalNurses =  nurseRepository.findAll();
		List<Nurse> nurses = new ArrayList<>();
		for (Nurse nurse : optionalNurses) {
			nurses.add(nurse);
		}
		return ResponseEntity.ok(nurses);
	}
	
		@GetMapping("/name")
		public ResponseEntity<Nurse> findByName(@RequestParam String name) {
			Optional<Nurse> optionalNurse =  nurseRepository.findByName(name);
			if(optionalNurse.isPresent()) {
				return ResponseEntity.ok(optionalNurse.get());
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}
		
		 @PostMapping("/login")
		 public ResponseEntity<Boolean>login(@RequestBody Nurse nurse) {
			 Optional<Nurse> optionalNurse = nurseRepository.findByUserAndPass(nurse.getUser(), nurse.getPass());
			 if(optionalNurse.isPresent()) {
				 return ResponseEntity.ok(true);
			 }
			 else {
				 return ResponseEntity.ok(false);	    						 
			 }
		 }
		 
		 @GetMapping("/{id}")
			public ResponseEntity<Nurse> findById(@PathVariable Long id) {
				
				Optional<Nurse> nurse =  nurseRepository.findById(id);
				
				if(nurse.isPresent()) {
					return ResponseEntity.ok(nurse.get());
				}
				else {
					return ResponseEntity.notFound().build();
				}
			
			}

			@PostMapping()
			public ResponseEntity<Nurse>createNurse(@RequestBody Nurse nurse) {
				try {
					 Nurse _nurse = nurseRepository.save(
					            new Nurse(
					                nurse.getName(),
					                nurse.getSurname(),
					                nurse.getEmail(),
					                nurse.getUser(),
					                nurse.getPass()
					            )
					        );
					  return ResponseEntity.status(HttpStatus.CREATED).body(_nurse);
				
				
				}catch(Exception e){
					
					return ResponseEntity.badRequest().build();
				}
			}
			
		 @PutMapping("/{id}")
			public ResponseEntity<Void> updateNurse(@PathVariable long id, @RequestBody Nurse updatedNurse){
				try {
					Optional<Nurse> originalNurse = nurseRepository.findById(id);
					if(originalNurse.isPresent()) {
						Nurse nurse = originalNurse.get();
			            if (updatedNurse.getName() != null) {
			                nurse.setName(updatedNurse.getName());
			            }
			            if (updatedNurse.getUser() != null) {
			                nurse.setUser(updatedNurse.getUser());
			            }
			            if (updatedNurse.getEmail() != null) {
			                nurse.setEmail(updatedNurse.getEmail());
			            }
			            if (updatedNurse.getSurname() != null) {
			                nurse.setSurname(updatedNurse.getSurname());
			            }
			            if (updatedNurse.getPass() != null) {
			                nurse.setPass(updatedNurse.getPass());
			            }				
						nurseRepository.save(nurse);	
						return ResponseEntity.ok().build();
					}
					else {
						return ResponseEntity.notFound().build();
					}
				}
				catch (Exception e) {
					return ResponseEntity.badRequest().build();
				}
			}
}


