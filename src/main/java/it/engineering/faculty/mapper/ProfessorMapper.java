package it.engineering.faculty.mapper;

import org.mapstruct.Mapper;

import it.engineering.faculty.dto.ProfessorDto;
import it.engineering.faculty.entity.ProfessorEntity;

@Mapper(uses = {TitleMapper.class})
public interface ProfessorMapper {

	ProfessorDto toProfessorDto(ProfessorEntity professorEntity);
	ProfessorEntity toProfessorEntity(ProfessorDto professorDto);
}
