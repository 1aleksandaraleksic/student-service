package it.engineering.faculty.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.engineering.faculty.dto.TitleDto;
import it.engineering.faculty.entity.TitleEntity;
import it.engineering.faculty.exception.ExistEntityException;
import it.engineering.faculty.mapper.TitleMapper;
import it.engineering.faculty.repository.TitleRepository;
import it.engineering.faculty.service.TitleService;

@Service
@Transactional
public class TitleServiceImpl implements TitleService{

	@Autowired
	private TitleRepository titleRepository;
	private TitleMapper titleMapper = Mappers.getMapper(TitleMapper.class);


	@Override
	public TitleDto save(TitleDto titleDto) throws Exception {
		Optional<TitleEntity> entity = titleRepository.findById(titleDto.getId());
		
		if(entity.isPresent()) {
			throw new ExistEntityException("Title already exists!", entity.get());
		}
		
		TitleEntity title = titleRepository.save(titleMapper.toTitleEntity(titleDto));
		return titleMapper.toTitleDto(title);
	}

	@Override
	public List<TitleDto> getAll() throws Exception {
		List<TitleEntity> registrationList =
				titleRepository.findAll();
		return registrationList.stream().map(title ->{
			return titleMapper.toTitleDto(title);
		}).collect(Collectors.toList());
	}
	
}
