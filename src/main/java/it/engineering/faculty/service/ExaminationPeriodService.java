package it.engineering.faculty.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.engineering.faculty.dto.ExaminationPeriodDto;

public interface ExaminationPeriodService {

	ExaminationPeriodDto save(ExaminationPeriodDto examinationPeriodDto) throws Exception;
	Optional<ExaminationPeriodDto> update(ExaminationPeriodDto examinationPeriodDto) throws Exception;
	Optional<ExaminationPeriodDto> findById(Long examinationPeriodId) throws Exception;
	void deleteById(Long examinationPeriodId) throws Exception;
	List<ExaminationPeriodDto> getAll() throws Exception;
	Page<ExaminationPeriodDto> findByPage(Pageable pageable);
	ExaminationPeriodDto findByStatusActive();
}
