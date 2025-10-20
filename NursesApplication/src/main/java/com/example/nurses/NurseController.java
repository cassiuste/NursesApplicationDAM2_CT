package com.example.nurses;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	public @ResponseBody Iterable<Nurse> getAll() {
		return nurseRepository.findAll();
		
	}
	
		@GetMapping("/name")
		public Optional<Nurse> findByName(@RequestParam String name) {
			return nurseRepository.findByName(name);
		}
		
		
		 @PostMapping("/login")
		 public ResponseEntity<Boolean>login(@RequestBody Nurse nurse) {
			 Optional<Nurse> optionalNurse = nurseRepository.findByUserAndPass(nurse.getUser(), nurse.getPass());
			 if(optionalNurse.isPresent()) {
				 return ResponseEntity.ok(true);
			 }
			 else {
				 return ResponseEntity.notFound().build();	    						 
			 }
		 }
}


