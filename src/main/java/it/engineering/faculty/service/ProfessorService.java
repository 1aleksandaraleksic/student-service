package it.engineering.faculty.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.engineering.faculty.dto.ProfessorDto;

public interface ProfessorService {

	ProfessorDto save(ProfessorDto professorDto) throws Exception;
	Optional<ProfessorDto> update(ProfessorDto professorDto) throws Exception;
	Optional<ProfessorDto> findById(Long professorId) throws Exception;
	void deleteById(Long professorId) throws Exception;
	List<ProfessorDto> getAll() throws Exception;
	Page<ProfessorDto> findByPage(Pageable pageable);
}
