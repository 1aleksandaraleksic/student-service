package it.engineering.faculty.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import it.engineering.faculty.entity.ExamEntity;
import it.engineering.faculty.entity.ExaminationPeriodEntity;

@Repository
public interface ExamRepository extends PagingAndSortingRepository<ExamEntity, Long> {

	List<ExamEntity> findByPeriod(ExaminationPeriodEntity periodEntity);
	Page<ExamEntity> findByPeriod(ExaminationPeriodEntity periodEntity, Pageable pageable);
}
