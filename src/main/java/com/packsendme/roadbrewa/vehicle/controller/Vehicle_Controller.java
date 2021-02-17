package com.packsendme.roadbrewa.vehicle.controller;

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

import com.packsendme.roadbrewa.dto.BodyworkDto;
import com.packsendme.roadbrewa.dto.VehicleDto;
import com.packsendme.roadbrewa.dto.VehicleTypeDto;
import com.packsendme.roadbrewa.dto.VehicleCategoryDto;
import com.packsendme.roadbrewa.vehicle.service.Bodywork_Service;
import com.packsendme.roadbrewa.vehicle.service.VehicleCategory_Service;
import com.packsendme.roadbrewa.vehicle.service.VehicleType_Service;
import com.packsendme.roadbrewa.vehicle.service.Vehicle_Service;

@RestController
@RequestMapping("/roadbrewa")
public class Vehicle_Controller {

	
	@Autowired
	private Vehicle_Service vehicle_Service;	
	@Autowired
	private VehicleType_Service vehicleType_Service;	
	@Autowired
	private VehicleCategory_Service vehicleCategory_Service;	
	@Autowired
	private Bodywork_Service bodywork_Service;	

	
	/***************************************
	 VEHICLE :: GET | POST | DELETE 
	 ***************************************/
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/vehicle")
	public ResponseEntity<?> getVehicles(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp) {	
		return vehicle_Service.findAll();
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/vehicle/cargo")
	public ResponseEntity<?> getVehicleForCargoType(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp, @Validated @RequestParam("type") String type) {	
		return vehicle_Service.searchVehicleWithCargoType(type);
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/vehicle")
	public ResponseEntity<?> postVehicle(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp, @Validated  @RequestBody VehicleDto vehicle)
	{	
		return vehicle_Service.save(vehicle);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@DeleteMapping("/vehicle")
	public ResponseEntity<?> deleteVehicle(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp, @Validated @RequestParam("id") String id)
	{	
		return vehicle_Service.delete(id);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping("/vehicle")
	public ResponseEntity<?> putVehicle(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp, @Validated @RequestParam("id") String id,
			@Validated  @RequestBody VehicleDto vehicle)
	{	
		return vehicle_Service.prepareUpdate(id, vehicle);
	}

	/***************************************
	 VEHICLE_CATEGORY :: GET | POST | DELETE 
	 ***************************************/
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/category")
	public ResponseEntity<?> getVehicleCategory(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp) {	
		return vehicleCategory_Service.findAll();
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/vehicle/category/type/{vehicle_type}")
	public ResponseEntity<?> getVehicleCategoryByType(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp,
			@PathVariable("vehicle_type") String vehicle_type) {	
		return vehicleCategory_Service.findVehicleByType(vehicle_type);
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/vehicle/category")
	public ResponseEntity<?> postVehicleCategory(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp, @Validated  @RequestBody VehicleCategoryDto vehicleCategory)
	{	
		return vehicleCategory_Service.save(vehicleCategory);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@DeleteMapping("/vehicle/category")
	public ResponseEntity<?> deleteVehicleCategory(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp, @Validated @RequestParam("id") String id)
	{	
		return vehicleCategory_Service.delete(id);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping("/vehicle/category")
	public ResponseEntity<?> putVehicleCategory(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp, @Validated @RequestParam("id") String id,
			@Validated  @RequestBody VehicleCategoryDto vehicleCategory)
	{	
		return vehicleCategory_Service.update(id, vehicleCategory);
	}
	

	/***************************************
	 VEHICLE_TYPE :: GET | POST | DELETE 
	 ***************************************/
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/vehicle/type")
	public ResponseEntity<?> getVehicleType(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp) {	
		return vehicleType_Service.findAll();
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/vehicle/type")
	public ResponseEntity<?> postVehicleType(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp, @Validated  @RequestBody VehicleTypeDto vehicleType)
	{	
		return vehicleType_Service.save(vehicleType);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@DeleteMapping("/vehicle/type")
	public ResponseEntity<?> deleteVehicleType(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp, @Validated @RequestParam("id") String id)
	{	
		return vehicleType_Service.delete(id);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping("/vehicle/type")
	public ResponseEntity<?> putVehicleType(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp, @Validated @RequestParam("id") String id,
			@Validated  @RequestBody VehicleTypeDto vehicleType)
	{	
		return vehicleType_Service.update(id, vehicleType);
	}
	
	
	/***************************************
	 BODYWORK (RELASHIONSHIP - VEHICLE) <--> GET | POST | DELETE 
	***************************************/

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/vehicle/bodywork")
	public ResponseEntity<?> getBodywork(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp) {	
		return bodywork_Service.findAll();
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/vehicle/bodywork")
	public ResponseEntity<?> postBodywork(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp,
			@Validated  @RequestBody BodyworkDto bodywork)
	{	
		return bodywork_Service.save(bodywork);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@DeleteMapping("/vehicle/bodywork")
	public ResponseEntity<?> deleteBodywork(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp, @Validated @RequestParam("id") String id)
	{	
		return bodywork_Service.delete(id);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PutMapping("/vehicle/bodywork")
	public ResponseEntity<?> putBodywork(@RequestHeader("isoLanguageCode") String isoLanguageCode,@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,@RequestHeader("originApp") String originApp, @Validated @RequestParam("id") String id, 
			@Validated  @RequestBody BodyworkDto bodywork)
	{	
		return bodywork_Service.prepareUpdate(id,bodywork);
	}
}
