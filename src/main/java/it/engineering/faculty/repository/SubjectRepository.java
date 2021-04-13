package it.engineering.faculty.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import it.engineering.faculty.entity.SubjectEntity;

@Repository
public interface SubjectRepository extends PagingAndSortingRepository<SubjectEntity, Long>{

}
