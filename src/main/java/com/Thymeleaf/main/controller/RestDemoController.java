package com.Thymeleaf.main.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Thymeleaf.main.entity.Person;
import com.Thymeleaf.main.service.FirebaseService;

@RestController
public class RestDemoController {

	@Autowired
	FirebaseService fireBaseService;
	
	@GetMapping("/")
	public String home() {
		return "Application is started successfully...";
	}
	
	@GetMapping("/getUserDetails")
	public Person getData(@RequestParam String name) throws InterruptedException, ExecutionException {
	    return fireBaseService.getUserDetails(name);    
	}
	
	@PostMapping("/createUser")
	public String saveData(@RequestBody Person person) throws InterruptedException, ExecutionException {		
		return fireBaseService.saveUserDetails(person);		
	}
	
	@DeleteMapping("/deleteUserDetails")
	public String deleteData(@RequestParam String name) {
		return fireBaseService.deleteUserDetails(name);
	}
	
	@PutMapping("/updateUserDetails")
	public String updateData(@RequestBody Person person) throws InterruptedException, ExecutionException {
		return fireBaseService.UpdateUserDetails(person);
	}
}
