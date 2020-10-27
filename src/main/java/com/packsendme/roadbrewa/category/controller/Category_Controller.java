package com.packsendme.roadbrewa.category.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.packsendme.roadbrewa.category.service.Category_Service;
import com.packsendme.roadbrewa.dto.CategoryDto;

@RestController
@RequestMapping("/roadbrewa")
public class Category_Controller {

	
	@Autowired
	private Category_Service categoryService;	

	
	/***************************************
	 CATEGORY :: GET | POST | DELETE 
	***************************************/

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/category")
	public ResponseEntity<?> getCategory(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp) {	
		return categoryService.findAll();
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/category/transport/{transport}")
	public ResponseEntity<?> getCategory(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp,
			@Validated  @PathVariable("transport") String transport) {	
		return categoryService.findCategoriesByTransports(transport);
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/category")
	public ResponseEntity<?> postCategory(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp,
			@Validated  @RequestBody CategoryDto category){	
		return categoryService.save(category);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@DeleteMapping("/category")
	public ResponseEntity<?> deleteCategory(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp, @Validated @RequestParam("id") String id)
	{	
		return categoryService.delete(id);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping("/category")
	public ResponseEntity<?> putCategory(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp, @Validated @RequestParam("id") String id, 
			@Validated  @RequestBody CategoryDto category)
	{	
		return categoryService.preparedUpdate(id, category);
	}

}
