package it.engineering.faculty.mapper;

import org.mapstruct.Mapper;

import it.engineering.faculty.dto.ExamDto;
import it.engineering.faculty.entity.ExamEntity;

@Mapper(uses = {
		TitleMapper.class,
		SubjectMapper.class,
		ProfessorMapper.class,
		ExaminationPeriodMapper.class})
public interface ExamMapper {

	ExamDto toExamDto(ExamEntity examEntity);
	ExamEntity toExamEntity(ExamDto examDto);
}
