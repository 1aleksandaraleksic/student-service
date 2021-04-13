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

import it.engineering.faculty.dto.ProfessorDto;
import it.engineering.faculty.exception.ExistEntityException;
import it.engineering.faculty.logging.Loggable;
import it.engineering.faculty.service.ProfessorService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/professor")
public class ProfessorRestController {

	@Autowired
	private ProfessorService professorService;
	
	@GetMapping
	@Loggable
	public @ResponseBody ResponseEntity<List<ProfessorDto>> getAll() throws Exception{
		try {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(professorService.getAll());
		}catch (Exception e) {
			throw new Exception("ERROR: " + e.getMessage());
		}		
	}
	
	@GetMapping("/{id}")
	@Loggable
	public @ResponseBody ResponseEntity<Object> findById(
			@PathVariable Long id) throws Exception{
		Optional<ProfessorDto> professorDto = professorService.findById(id);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(professorDto.get());
	}
	
	@PostMapping
	@Loggable
	public @ResponseBody ResponseEntity<ProfessorDto> save(
			@Valid @RequestBody ProfessorDto professorDto) throws Exception{
		try {
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(professorService.save(professorDto));
		} catch (ExistEntityException e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(professorDto);
		}
	}
	
	@DeleteMapping("/{id}")
	@Loggable
	public @ResponseBody ResponseEntity<String> delete(@PathVariable Long id){
		try {
			professorService.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Deleted successfuly!");
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping
	@Loggable
	public @ResponseBody ResponseEntity<ProfessorDto> update(
			@Valid @RequestBody ProfessorDto professorDto){
		try {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(professorService.update(professorDto).get());
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(professorDto);
		}
	}
	
	@GetMapping("/page")
	@Loggable
	public @ResponseBody ResponseEntity<Page<ProfessorDto>> getByPage(Pageable pageable) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(professorService.findByPage(pageable));
	}
}
