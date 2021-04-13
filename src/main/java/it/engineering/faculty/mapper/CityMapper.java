package it.engineering.faculty.mapper;

import org.mapstruct.Mapper;

import it.engineering.faculty.dto.CityDto;
import it.engineering.faculty.entity.CityEntity;

@Mapper
public interface CityMapper {

	CityDto toCityDto(CityEntity cityEntity);
	CityEntity toCityEntity(CityDto cityDto);
	
}
