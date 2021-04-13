package it.engineering.faculty.mapper;

import org.mapstruct.Mapper;

import it.engineering.faculty.dto.ExaminationPeriodDto;
import it.engineering.faculty.entity.ExaminationPeriodEntity;

@Mapper(uses = {ExamMapper.class})
public interface ExaminationPeriodMapper {

	ExaminationPeriodEntity toExaminationPeriodEntity (ExaminationPeriodDto examinationPeriodDto);
	ExaminationPeriodDto toExaminationPeriodDto (ExaminationPeriodEntity examinationPeriodEntity);
}
