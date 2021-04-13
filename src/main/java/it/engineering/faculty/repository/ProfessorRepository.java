package it.engineering.faculty.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import it.engineering.faculty.entity.ProfessorEntity;

@Repository
public interface ProfessorRepository extends PagingAndSortingRepository<ProfessorEntity, Long>{

}
