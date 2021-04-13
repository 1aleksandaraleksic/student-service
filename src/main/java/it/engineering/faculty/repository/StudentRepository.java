package it.engineering.faculty.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import it.engineering.faculty.entity.StudentEntity;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<StudentEntity, Long>{

}
