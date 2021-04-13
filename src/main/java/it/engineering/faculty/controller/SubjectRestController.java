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

import it.engineering.faculty.dto.SubjectDto;
import it.engineering.faculty.logging.Loggable;
import it.engineering.faculty.service.SubjectService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/subject")
public class SubjectRestController {
	
	@Autowired
	private SubjectService subjectService;
	
	@GetMapping
	@Loggable
	public @ResponseBody ResponseEntity<List<SubjectDto>> getAll() throws Exception{
		try {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(subjectService.getAll());
		}catch (Exception e) {
			throw new Exception("ERROR: " + e.getMessage());
		}		
	}
	
	@GetMapping("/{id}")
	@Loggable
	public @ResponseBody ResponseEntity<Object> findById(
			@PathVariable Long id) throws Exception{
		Optional<SubjectDto> subjectDto = subjectService.findById(id);
		if(subjectDto.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(subjectDto.get());
		}
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body("Subject with id: " + id + ", does't exist!");
	}
	
	@PostMapping
	@Loggable
	public @ResponseBody ResponseEntity<SubjectDto> save(
			@Valid @RequestBody SubjectDto subjectDto) throws Exception{
		try {
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(subjectService.save(subjectDto));
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(subjectDto);
		}
	}
	
	@DeleteMapping("/{id}")
	@Loggable
	public @ResponseBody ResponseEntity<String> delete(@PathVariable Long id){
		try {
			subjectService.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Deleted successfuly!");
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping
	@Loggable
	public @ResponseBody ResponseEntity<SubjectDto> update(
			@Valid @RequestBody SubjectDto subjectDto) throws Exception{
		try {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(subjectService.update(subjectDto).get());
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(subjectDto);
		}
	}
	
	@GetMapping("/page")
	@Loggable
	public @ResponseBody ResponseEntity<Page<SubjectDto>> getByPage(Pageable pageable) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(subjectService.findByPage(pageable));
	}

}
