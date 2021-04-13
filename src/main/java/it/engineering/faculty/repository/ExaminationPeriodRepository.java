package it.engineering.faculty.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import it.engineering.faculty.entity.ExaminationPeriodEntity;
import it.engineering.faculty.entity.util.PeriodStatus;

@Repository
public interface ExaminationPeriodRepository 
				extends PagingAndSortingRepository<ExaminationPeriodEntity, Long> {

	List<ExaminationPeriodEntity> findByStatus(PeriodStatus status);
}
