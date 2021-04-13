package it.engineering.faculty.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.engineering.faculty.dto.CityDto;
import it.engineering.faculty.entity.CityEntity;
import it.engineering.faculty.exception.ExistEntityException;
import it.engineering.faculty.mapper.CityMapper;
import it.engineering.faculty.repository.CityRepository;
import it.engineering.faculty.service.CityService;

@Service
@Transactional
public class CityServiceImpl implements CityService{
	
	@Autowired
	private CityRepository cityRepository;

	private CityMapper cityMapper = Mappers.getMapper(CityMapper.class);

	@Override
	public CityDto save(CityDto cityDto) throws Exception {
		Optional<CityEntity> entity = cityRepository.findById(cityDto.getPostalCode());
		
		if(entity.isPresent()) {
			throw new ExistEntityException("City already exists!", entity.get());
		}
		
		CityEntity city = cityRepository.save(cityMapper.toCityEntity(cityDto));
		return cityMapper.toCityDto(city);
	}

	@Override
	public Optional<CityDto> update(CityDto cityDto) throws Exception {
//		cityRepository.
		return null;
	}

	@Override
	public Optional<CityDto> findById(Long postalCode) throws Exception {
		
		Optional<CityEntity> entity = cityRepository.findById(postalCode);
		
		if(entity.isPresent()) {
			return Optional.of(cityMapper.toCityDto(entity.get()));
		}
		
		return Optional.empty();
	}

	@Override
	public List<CityDto> getAll() throws Exception {
		
		List<CityEntity> cityList = cityRepository.findAll();
		
		return cityList.stream().map(cityEntity ->{
			return cityMapper.toCityDto(cityEntity);
		}).collect(Collectors.toList());
	}

	@Override
	public List<CityDto> getAllWithPagAndSort(int page, int size) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		return null;
	}

	@Override
	public void deleteById(Long cityId) throws Exception {
		cityRepository.deleteById(cityId);		
	}

}
