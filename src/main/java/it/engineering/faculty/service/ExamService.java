package it.engineering.faculty.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.engineering.faculty.dto.ExamDto;
import it.engineering.faculty.entity.ExaminationPeriodEntity;

public interface ExamService {

	ExamDto save(ExamDto examDto) throws Exception;
	Optional<ExamDto> update(ExamDto examDto) throws Exception;
	Optional<ExamDto> findById(Long examId) throws Exception;
	void deleteById(Long examId) throws Exception;
	List<ExamDto> getAll() throws Exception;
	List<ExamDto> findByPeriod(Long period);
	Page<ExamDto> findByPeriod(ExaminationPeriodEntity periodEntity, Pageable pageable);
	Page<ExamDto> findByPage(Pageable pageable);
}
