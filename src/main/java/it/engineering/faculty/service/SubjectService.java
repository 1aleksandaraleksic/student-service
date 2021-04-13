package it.engineering.faculty.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.engineering.faculty.dto.SubjectDto;

public interface SubjectService {

	SubjectDto save(SubjectDto subjectDto) throws Exception;
	Optional<SubjectDto> update(SubjectDto subjectDto) throws Exception;
	Optional<SubjectDto> findById(Long subjectId) throws Exception;
	void deleteById(Long subjectId) throws Exception;
	List<SubjectDto> getAll() throws Exception;
	Page<SubjectDto> findByPage(Pageable pageable);
}
