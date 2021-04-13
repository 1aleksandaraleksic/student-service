package it.engineering.faculty.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.engineering.faculty.dto.ExamRegistrationDto;
import it.engineering.faculty.exception.ExistEntityException;
import it.engineering.faculty.logging.Loggable;
import it.engineering.faculty.service.ExamRegistrationService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/exam-registration")
public class ExamRegistrationRestController {

	@Autowired
	private ExamRegistrationService examRegistrationService;
	
	@GetMapping
	@Loggable
	public @ResponseBody ResponseEntity<List<ExamRegistrationDto>> getAll() throws Exception{
		try {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(examRegistrationService.getAll());
		}catch (Exception e) {
			throw new Exception("ERROR: " + e.getMessage());
		}		
	}
	
	@GetMapping("/{id}")
	@Loggable
	public @ResponseBody ResponseEntity<Object> findById(
			@PathVariable Long id) throws Exception{
		Optional<ExamRegistrationDto> examRegistrationDto = 
								examRegistrationService.findById(id);
		if(examRegistrationDto.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(examRegistrationDto.get());
		}
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body("Registration with id: " + id + ", does't exist!");
	}
	
	@PostMapping
	@Loggable
	public @ResponseBody ResponseEntity<ExamRegistrationDto> save(
			@Valid @RequestBody ExamRegistrationDto examRegistrationDto) throws Exception{
		try {
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(examRegistrationService.save(examRegistrationDto));
		} catch (ExistEntityException e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(examRegistrationDto);
		}
	}
	
	@PutMapping
	@Loggable
	public @ResponseBody ResponseEntity<ExamRegistrationDto> update(
			@Valid @RequestBody ExamRegistrationDto examRegistrationDto) throws Exception{
		try {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(examRegistrationService.update(examRegistrationDto).get());
		} catch (ExistEntityException e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(examRegistrationDto);
		}
	}
	
	@DeleteMapping("/{id}")
	@Loggable
	public @ResponseBody ResponseEntity<String> delete(@PathVariable Long id){
		try {
			examRegistrationService.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Deleted successfuly!");
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("/exam/{examId}/period/{periodId}")
	@Loggable
	public @ResponseBody ResponseEntity<List<ExamRegistrationDto>> getAllForPeriod(
			@PathVariable Long examId, @PathVariable Long periodId
			){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(examRegistrationService.getAllRegistryExamForPeriod(examId, periodId));
		 
	}
	
	@GetMapping("/exam/{examId}/period/{periodId}/page")
	@Loggable
	public @ResponseBody ResponseEntity<Page<ExamRegistrationDto>> getAllForPeriodPaging(
			@PathVariable Long examId, @PathVariable Long periodId, Pageable pageable
			){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(examRegistrationService.getAllRegistryExamForPeriodPageable(examId, periodId, pageable));
		 
	}
}
