package com.example.nurses;

import java.io.File;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/nurse")
public class NurseController {
	
	@GetMapping("/name")
	public ResponseEntity<Nurse> findByName(@RequestParam String name) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			File file = new File("src/main/resources/static/nurses.json");
			JsonNode node = mapper.readTree(file);
			Nurse[] nurses = mapper.convertValue(node.get("nurses"), Nurse[].class);
			for (Nurse nurse : nurses) {
				if(nurse.getName().equals(name)) {
					return ResponseEntity.ok(nurse);
				}
			}
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.notFound().build();
	}
}


