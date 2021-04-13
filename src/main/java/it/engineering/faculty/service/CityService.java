package it.engineering.faculty.service;

import java.util.List;
import java.util.Optional;

import it.engineering.faculty.dto.CityDto;

public interface CityService {

	CityDto save(CityDto cityDto) throws Exception;
	Optional<CityDto> update(CityDto cityDto) throws Exception;
	Optional<CityDto> findById(Long postalCode) throws Exception;
	void deleteById(Long cityId) throws Exception;
	List<CityDto> getAll() throws Exception;
	List<CityDto> getAllWithPagAndSort(int page, int size) throws Exception;	
	Long count();
}
