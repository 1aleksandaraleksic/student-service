package it.engineering.faculty.mapper;

import org.mapstruct.Mapper;

import it.engineering.faculty.dto.StudentDto;
import it.engineering.faculty.entity.StudentEntity;

@Mapper
public interface StudentMapper {

	StudentDto toStudentDto(StudentEntity studentEntity);
	StudentEntity toStudentEntity(StudentDto studentDto);
}
