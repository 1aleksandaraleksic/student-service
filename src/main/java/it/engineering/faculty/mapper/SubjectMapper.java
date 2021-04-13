package it.engineering.faculty.mapper;

import org.mapstruct.Mapper;

import it.engineering.faculty.dto.SubjectDto;
import it.engineering.faculty.entity.SubjectEntity;

@Mapper(uses = TitleMapper.class)
public interface SubjectMapper {

	SubjectDto toSubjectDto(SubjectEntity subjectEntity);
	SubjectEntity toSubjectEntity(SubjectDto subjectDto);
}
