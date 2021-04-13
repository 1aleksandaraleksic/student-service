package it.engineering.faculty.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.engineering.faculty.dto.StudentDto;

public interface StudentService {

	StudentDto save(StudentDto studentDto) throws Exception;
	Optional<StudentDto> update(StudentDto studentDto) throws Exception;
	Optional<StudentDto> findById(Long studentId) throws Exception;
	void deleteById(Long studentId) throws Exception;
	List<StudentDto> getAll() throws Exception;
	Page<StudentDto> findByPage(Pageable pageable);
}
