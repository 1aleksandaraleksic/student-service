package it.engineering.faculty.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import it.engineering.faculty.entity.ExamRegistrationEntity;

@Repository
public interface ExamRegistrationRepository 
			extends PagingAndSortingRepository<ExamRegistrationEntity, Long> {
	
	@Query("SELECT registry FROM ExamRegistrationEntity registry" +
			   " JOIN ExamEntity e on e.id = registry.exam.id " +
			   "  WHERE registry.exam.id =:examId AND e.period.id =:periodId ")
		List<ExamRegistrationEntity> findByExamIdAndPeriodId(Long examId, Long periodId);

	@Query("SELECT registry FROM ExamRegistrationEntity registry" +
		   " JOIN ExamEntity e on e.id = registry.exam.id " +
		   "  WHERE registry.exam.id =:examId AND e.period.id =:periodId ")
	Page<ExamRegistrationEntity> findByPeriodPageable(Long examId, Long periodId, Pageable pageable);
}
