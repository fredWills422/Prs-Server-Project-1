package com.maxtrain.bootcamp.prs.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxtrain.bootcamp.prs.util.JsonResponse;

@CrossOrigin
@RestController
@RequestMapping(path="/users")

public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	private JsonResponse save (User user) {
		try {
			User u = userRepo.save(user);
			return JsonResponse.getInstance(u);
		}catch(IllegalArgumentException ex) {
			return JsonResponse.getInstance("Parameter user cannot be null");
		}catch (Exception ex){
			return JsonResponse.getInstance(ex.getMessage());
		}
	}
	
	@GetMapping()
	public JsonResponse list() {
		try {
			Iterable<User> users = userRepo.findAll();
			return JsonResponse.getInstance(users);
		}catch(Exception ex) {
			return JsonResponse.getInstance(ex.getMessage());
		}
	}
	
	@GetMapping("/{id}")
	public JsonResponse getByPK(@PathVariable Integer id) {
		try {
			Optional<User> u = userRepo.findById(id);
			if(!u.isPresent()) {
				return JsonResponse.getInstance("User not found");
			}return JsonResponse.getInstance(u.get());
		}catch (Exception ex) {
			return JsonResponse.getInstance(ex.getMessage());
		}
	}
	
	@PostMapping()
	public JsonResponse add(@RequestBody User user) {
		try {			
			return save(user);
		}catch(Exception ex) {
			return JsonResponse.getInstance(ex.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public JsonResponse update(@RequestBody User user, @PathVariable Integer id) {
		try {
			return save(user);
		}catch(Exception ex) {
			return JsonResponse.getInstance(ex.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public JsonResponse remove(@PathVariable Integer id) {
		try {
			Optional<User> u = userRepo.findById(id);
			if(!u.isPresent()) {
				return JsonResponse.getInstance("User not found");
			}
			userRepo.deleteById(id);
			return JsonResponse.getInstance(u.get());
		}catch(IllegalArgumentException ex) {
			return JsonResponse.getInstance("Parameter id cannot be null");
		}catch(Exception ex) {
			return JsonResponse.getInstance(ex.getMessage());
		}
	}
	
	@PostMapping("/login")
	public JsonResponse authenticate(@RequestBody User user) {
		String userName= user.getUserName();
		String password = user.getPassword();
		try {
			User u = userRepo.findByUserNameAndPassword(userName, password);
			if(u==null) {
				return JsonResponse.getInstance("User not found");
			}
			return JsonResponse.getInstance(u);
		}catch (Exception ex) {
			return JsonResponse.getInstance(ex.getMessage());
		}
	}
	
}
