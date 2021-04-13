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

import it.engineering.faculty.dto.ExaminationPeriodDto;
import it.engineering.faculty.exception.ExistEntityException;
import it.engineering.faculty.logging.Loggable;
import it.engineering.faculty.service.ExaminationPeriodService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/examination-period")
public class ExaminationPeriodRestController {

	@Autowired
	private ExaminationPeriodService examinaitonPeriodService;
	
	@GetMapping
	@Loggable
	public @ResponseBody ResponseEntity<List<ExaminationPeriodDto>> getAll() throws Exception{
		try {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(examinaitonPeriodService.getAll());
		}catch (Exception e) {
			throw new Exception("ERROR: " + e.getMessage());
		}		
	}
	
	@GetMapping("/{id}")
	@Loggable
	public @ResponseBody ResponseEntity<Object> findById(
			@PathVariable Long id) throws Exception{
		Optional<ExaminationPeriodDto> examinationPeriodDto = 
											examinaitonPeriodService.findById(id);
		if(examinationPeriodDto.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(examinationPeriodDto.get());
		}
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body("Period with id: " + id + ", does't exist!");
	}
	
	@PostMapping
	@Loggable
	public @ResponseBody ResponseEntity<ExaminationPeriodDto> save(
			@Valid @RequestBody ExaminationPeriodDto examinationPeriodDto) throws Exception{
		try {
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(examinaitonPeriodService.save(examinationPeriodDto));
		} catch (ExistEntityException e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(examinationPeriodDto);
		}
	}
	
	@PutMapping
	@Loggable
	public @ResponseBody ResponseEntity<ExaminationPeriodDto> update(
			@Valid @RequestBody ExaminationPeriodDto examinationPeriodDto) throws Exception{
		try {
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(examinaitonPeriodService.update(examinationPeriodDto).get());
		} catch (ExistEntityException e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(examinationPeriodDto);
		}
	}
	
	@DeleteMapping("/{id}")
	@Loggable
	public @ResponseBody ResponseEntity<String> delete(@PathVariable Long id){
		try {
			examinaitonPeriodService.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Deleted successfuly!");
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("/page")
	@Loggable
	public @ResponseBody ResponseEntity<Page<ExaminationPeriodDto>> getByPage(Pageable pageable) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(examinaitonPeriodService.findByPage(pageable));
	}
	
	@GetMapping("/status-active")
	@Loggable
	public @ResponseBody ResponseEntity<Object> findByStatusActive() throws Exception{
		ExaminationPeriodDto examinationPeriodDto = 
					examinaitonPeriodService.findByStatusActive();

		if(examinationPeriodDto != null) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(examinationPeriodDto);
		}
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body("Active period does not exist!");
	}
}
