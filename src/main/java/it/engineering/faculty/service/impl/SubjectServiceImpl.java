package it.engineering.faculty.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.engineering.faculty.dto.SubjectDto;
import it.engineering.faculty.entity.SubjectEntity;
import it.engineering.faculty.exception.ExistEntityException;
import it.engineering.faculty.mapper.SubjectMapper;
import it.engineering.faculty.repository.SubjectRepository;
import it.engineering.faculty.service.SubjectService;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	private SubjectMapper subjectMapper = Mappers.getMapper(SubjectMapper.class);

	@Override
	public SubjectDto save(SubjectDto subjectDto) throws Exception {
		Optional<SubjectEntity> entity = subjectRepository.findById(subjectDto.getId());
		
		if(entity.isPresent()) {
			throw new ExistEntityException("Subject already exists!", entity.get());
		}
		
		SubjectEntity subject = subjectRepository.save(subjectMapper.toSubjectEntity(subjectDto));
		return subjectMapper.toSubjectDto(subject);
	}

	@Override
	public Optional<SubjectDto> update(SubjectDto subjectDto) throws Exception {
		Optional<SubjectEntity> entity = subjectRepository.findById(subjectDto.getId());
		
		if(entity.isPresent()) {
			SubjectEntity subject = subjectRepository.save(subjectMapper.toSubjectEntity(subjectDto));
			return Optional.of(subjectMapper.toSubjectDto(subject));
		}
		
		return Optional.empty();
	}

	@Override
	public Optional<SubjectDto> findById(Long subjectId) throws Exception {
		Optional<SubjectEntity> entity = subjectRepository.findById(subjectId);
		
		if(entity.isPresent()) {
			return Optional.of(subjectMapper.toSubjectDto(entity.get()));
		}
		
		return Optional.empty();
	}

	@Override
	public void deleteById(Long subjectId) throws Exception {
		subjectRepository.deleteById(subjectId);

	}

	@Override
	public List<SubjectDto> getAll() throws Exception {
		List<SubjectEntity> subjectList = (List<SubjectEntity>) subjectRepository.findAll();
		return subjectList.stream().map(subject ->{
			return subjectMapper.toSubjectDto(subject);
		}).collect(Collectors.toList());
	}
	
	@Override
	public Page<SubjectDto> findByPage(Pageable pageable) {
		Page<SubjectDto> entites = subjectRepository.findAll(pageable).map(subjectMapper::toSubjectDto);
		return entites;
	}

}
