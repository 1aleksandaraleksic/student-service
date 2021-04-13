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

import it.engineering.faculty.dto.StudentDto;
import it.engineering.faculty.exception.ExistEntityException;
import it.engineering.faculty.logging.Loggable;
import it.engineering.faculty.service.StudentService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/student")
public class StudentRestController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping
	@Loggable
	public @ResponseBody ResponseEntity<List<StudentDto>> findAll() throws Exception{
		try {
			return ResponseEntity.status(HttpStatus.OK).body(studentService.getAll());
		}catch (Exception e) {
			throw new Exception("ERROR: " + e.getMessage());
		}
	}
	
	@GetMapping("/{id}")
	@Loggable
	public @ResponseBody ResponseEntity<Object> findById(
					@PathVariable Long id)throws Exception{
		
		Optional<StudentDto> studentDto = studentService.findById(id);
		if(studentDto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(studentDto.get());
		}
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body("Student with id: " + id + ", doesn't exist!");
	}
	
	@PostMapping
	@Loggable
	public @ResponseBody ResponseEntity<StudentDto> saveStudent(
				@Valid @RequestBody StudentDto studentDto) throws Exception{
		try {
			return ResponseEntity
							.status(HttpStatus.OK)
							.body(studentService.save(studentDto));
		} catch (ExistEntityException e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(studentDto);
		}
	}
	
	@DeleteMapping("/{id}")
	@Loggable
	public @ResponseBody ResponseEntity<String> delete(@PathVariable Long id){
		try {
			studentService.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Deleted successfuly!");
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping
	@Loggable
	public @ResponseBody ResponseEntity<StudentDto> updateStudent(
			@Valid @RequestBody StudentDto studentDto) throws Exception{
		try {
			return ResponseEntity
							.status(HttpStatus.OK)
							.body(studentService.update(studentDto).get());
		} catch (ExistEntityException e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(studentDto);
		}
	}
	
	@GetMapping("/page")
	@Loggable
	public @ResponseBody ResponseEntity<Page<StudentDto>> getByPage(Pageable pageable) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(studentService.findByPage(pageable));
	}

}
