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

import it.engineering.faculty.dto.TitleDto;
import it.engineering.faculty.logging.Loggable;
import it.engineering.faculty.service.TitleService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/title")
public class TitleRestController {

	@Autowired
	private TitleService titleService;
	
	
	@PostMapping
	@Loggable
	public @ResponseBody ResponseEntity<TitleDto> save(
			@Valid @RequestBody TitleDto titleDto) throws Exception{

		try {
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(titleService.save(titleDto));
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(titleDto);
		}
	}
	
	@GetMapping
	@Loggable
	public @ResponseBody ResponseEntity<List<TitleDto>> getAll() throws Exception{
		try {
			return ResponseEntity.status(HttpStatus.OK).body(titleService.getAll());
		}catch (Exception e) {
			throw new Exception("ERROR: " + e.getMessage());
		}
	}
}
