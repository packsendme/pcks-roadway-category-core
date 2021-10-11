package com.packsendme.roadway.category.controller;

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

import com.packsendme.roadway.commons.dto.CategoryDto;
import com.packsendme.roadway.commons.dto.UnityMeasurementDto;
import com.packsendme.roadway.category.service.Category_Service;
import com.packsendme.roadway.category.service.UnityMeasurement_Service;

@RestController
@RequestMapping("/roadway/category")
public class Category_Controller {

	
	@Autowired
	private Category_Service categoryService;	

	@Autowired
	private UnityMeasurement_Service unityService;
	
	/***************************************
	 CATEGORY :: GET | POST | DELETE 
	***************************************/

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/")
	public ResponseEntity<?> getCategory(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp) {	
		return categoryService.findAll();
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/transport/{transport}")
	public ResponseEntity<?> getCategory(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp,
			@Validated  @PathVariable("transport") String transport) {	
		return categoryService.findCategoriesByTransports(transport);
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/")
	public ResponseEntity<?> postCategory(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp,
			@Validated  @RequestBody CategoryDto category){	
		return categoryService.save(category);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@DeleteMapping("/")
	public ResponseEntity<?> deleteCategory(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp, @Validated @RequestParam("id") String id)
	{	
		return categoryService.delete(id);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping("/")
	public ResponseEntity<?> putCategory(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp, @Validated @RequestParam("id") String id, 
			@Validated  @RequestBody CategoryDto category)
	{	
		return categoryService.preparedUpdate(id, category);
	}
	
	/***************************************
	 UNITY_MEASUREMENT :: GET | POST | DELETE 
	***************************************/

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/unitymeasurement/type")
	public ResponseEntity<?> getUnityMeasurementByCountry(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp, @Validated @RequestParam("unity") String unity) {	
		return unityService.findUnityMeasurementByTypUnity(unity);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/unitymeasurement")
	public ResponseEntity<?> getUnityMeasurement(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp) {	
		return unityService.findAll();
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/unitymeasurement")
	public ResponseEntity<?> postUnityMeasurement(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp,
			@Validated  @RequestBody UnityMeasurementDto unityMeasurement)
	{	
		return unityService.save(unityMeasurement);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@DeleteMapping("/unitymeasurement")
	public ResponseEntity<?> deleteUnityMeasurement(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp, @Validated @RequestParam("id") String id)
	{	
		return unityService.delete(id);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping("/unitymeasurement")
	public ResponseEntity<?> putUnityMeasurement(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp, @Validated @RequestParam("id") String id, 
			@Validated  @RequestBody UnityMeasurementDto unityMeasurement)
	{	
		return unityService.update(id, unityMeasurement);
	}

}
