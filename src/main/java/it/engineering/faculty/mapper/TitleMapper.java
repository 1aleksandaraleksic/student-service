package it.engineering.faculty.mapper;

import org.mapstruct.Mapper;

import it.engineering.faculty.dto.TitleDto;
import it.engineering.faculty.entity.TitleEntity;

@Mapper
public interface TitleMapper {

	TitleDto toTitleDto(TitleEntity titleEntity);
	TitleEntity toTitleEntity(TitleDto titleDto);
}
