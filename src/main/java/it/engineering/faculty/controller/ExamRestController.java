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

import it.engineering.faculty.dto.ExamDto;
import it.engineering.faculty.entity.ExaminationPeriodEntity;
import it.engineering.faculty.exception.ExistEntityException;
import it.engineering.faculty.logging.Loggable;
import it.engineering.faculty.service.ExamService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/exam")
public class ExamRestController {

	@Autowired
	private ExamService examService;
	
	@GetMapping
	@Loggable
	public @ResponseBody ResponseEntity<List<ExamDto>> getAll() throws Exception{
		try {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(examService.getAll());
		}catch (Exception e) {
			throw new Exception("ERROR: " + e.getMessage());
		}		
	}
	
	@GetMapping("/{id}")
	@Loggable
	public @ResponseBody ResponseEntity<Object> findById(
			@PathVariable Long id) throws Exception{
		Optional<ExamDto> examDto = examService.findById(id);
		if(examDto.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(examDto.get());
		}
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body("Exam with id: " + id + ", does't exist!");
	}
	
	@GetMapping("/period/{id}")
	@Loggable
	public @ResponseBody ResponseEntity<List<ExamDto>> getAllByPeriod(
					@PathVariable Long id) throws Exception{
		try {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(examService.findByPeriod(id));
		}catch (Exception e) {
			throw new Exception("ERROR: " + e.getMessage());
		}		
	}
	
	@PostMapping
	@Loggable
	public @ResponseBody ResponseEntity<ExamDto> save(
			@Valid @RequestBody ExamDto examDto) throws Exception{
		try {
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(examService.save(examDto));
		} catch (ExistEntityException e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(examDto);
		}
	}
	
	@DeleteMapping("/{id}")
	@Loggable
	public @ResponseBody ResponseEntity<String> delete(@PathVariable Long id){
		try {
			examService.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Deleted successfuly!");
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping
	@Loggable
	public @ResponseBody ResponseEntity<ExamDto> update(
			@Valid @RequestBody ExamDto examDto){
		try {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(examService.update(examDto).get());
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(examDto);
		}
	}
	
	@GetMapping("/page")
	@Loggable
	public @ResponseBody ResponseEntity<Page<ExamDto>> getByPage(
			@RequestBody ExaminationPeriodEntity periodEntity, Pageable pageable) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(examService.findByPeriod(periodEntity, pageable));
	}
}
