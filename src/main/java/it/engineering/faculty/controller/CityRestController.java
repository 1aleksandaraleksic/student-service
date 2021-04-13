package it.engineering.faculty.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.engineering.faculty.dto.CityDto;
import it.engineering.faculty.logging.Loggable;
import it.engineering.faculty.service.CityService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/city")
public class CityRestController {

	@Autowired
	private CityService cityService;
	
	@PostMapping
	@Loggable
	public @ResponseBody ResponseEntity<CityDto> save(
			@Valid @RequestBody CityDto cityDto) throws Exception{
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(cityService.save(cityDto));
//		try {
//
//		} catch (Exception e) {
//			return ResponseEntity
//					.status(HttpStatus.BAD_REQUEST)
//					.body(cityDto);
//		}
	}
	
	@GetMapping
	@Loggable
	public @ResponseBody ResponseEntity<List<CityDto>> getAll() throws Exception{
		return ResponseEntity.status(HttpStatus.OK).body(cityService.getAll());
//		try {
//
//		}catch (Exception e) {
//			throw new Exception("ERROR: " + e.getMessage());
//		}
	}
}
