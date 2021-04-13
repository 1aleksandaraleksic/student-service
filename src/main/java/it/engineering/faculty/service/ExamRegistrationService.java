package it.engineering.faculty.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.engineering.faculty.dto.ExamRegistrationDto;

public interface ExamRegistrationService {

	ExamRegistrationDto save(ExamRegistrationDto examRegistrationDto) throws Exception;
	Optional<ExamRegistrationDto> update(ExamRegistrationDto examRegistrationDto) throws Exception;
	Optional<ExamRegistrationDto> findById(Long examRegistrationId) throws Exception;
	void deleteById(Long examRegistrationId) throws Exception;
	List<ExamRegistrationDto> getAll() throws Exception;
	List<ExamRegistrationDto> getAllRegistryExamForPeriod(Long examId, Long periodId);
	Page<ExamRegistrationDto> getAllRegistryExamForPeriodPageable(Long examId, Long periodId, Pageable pageable);
}
