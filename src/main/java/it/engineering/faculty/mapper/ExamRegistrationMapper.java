package it.engineering.faculty.mapper;

import org.mapstruct.Mapper;

import it.engineering.faculty.dto.ExamRegistrationDto;
import it.engineering.faculty.entity.ExamRegistrationEntity;

@Mapper(uses= {
		ExamMapper.class, 
		StudentMapper.class})
public interface ExamRegistrationMapper {

	ExamRegistrationDto toExamRegistrationDto(ExamRegistrationEntity examRegistrationEntity);
	ExamRegistrationEntity toExamRegistrationEntity(ExamRegistrationDto examRegistrationDto);
}
