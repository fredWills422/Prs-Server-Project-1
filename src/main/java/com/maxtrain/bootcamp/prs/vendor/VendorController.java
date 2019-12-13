package com.maxtrain.bootcamp.prs.vendor;

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
@RequestMapping(path="/vendors")
public class VendorController {
	
	@Autowired
	private VendorRepository vendorRepo;
	
	private JsonResponse save (Vendor vendor) {
		try {
			Vendor v = vendorRepo.save(vendor);
			return JsonResponse.getInstance(v);
		}catch(IllegalArgumentException ex) {
			return JsonResponse.getInstance("Parameter vendor cannot be null");
		}catch(Exception ex) {
			return JsonResponse.getInstance(ex.getMessage());
		}
	}
	
	@GetMapping()
	public JsonResponse list() {
		try {			
			return JsonResponse.getInstance(vendorRepo.findAll());
		}catch(Exception ex) {
			return JsonResponse.getInstance(ex.getMessage());
		}
	}
	
	@GetMapping("/{id}")
	public JsonResponse getByPK(@PathVariable Integer id) {
		try {
			Optional<Vendor> v= vendorRepo.findById(id);
			if(!v.isPresent()) {
				return JsonResponse.getInstance("Vendor not found");
			}
			return JsonResponse.getInstance(v.get());
		}catch (Exception ex) {
			return JsonResponse.getInstance(ex.getMessage());
		}
	}
	
	
	@PostMapping()
	public JsonResponse add(@RequestBody Vendor vendor) {
		try {
			return save(vendor);
		}catch(Exception ex) {
			return JsonResponse.getInstance(ex.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public JsonResponse update(@RequestBody Vendor vendor, @PathVariable Integer id) {
		try{
			return save(vendor);
		}catch(Exception ex) {
			return JsonResponse.getInstance(ex.getMessage());
		}	
	}
	
	@DeleteMapping("/{id}")
	public JsonResponse remove(@PathVariable Integer id) {
		try {
			Optional<Vendor> v = vendorRepo.findById(id);
			if(!v.isPresent()) {
				return JsonResponse.getInstance("Vendor not found");
			}
			vendorRepo.deleteById(id);
			return JsonResponse.getInstance(v.get());
		}catch(Exception ex) {
			return JsonResponse.getInstance(ex.getMessage());
		}
		
	}
	
}
