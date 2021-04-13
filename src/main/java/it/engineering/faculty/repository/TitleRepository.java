package it.engineering.faculty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.engineering.faculty.entity.TitleEntity;

@Repository
public interface TitleRepository extends JpaRepository<TitleEntity, Long> {

}
