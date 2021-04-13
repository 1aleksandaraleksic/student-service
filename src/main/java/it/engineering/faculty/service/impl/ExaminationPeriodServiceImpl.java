package it.engineering.faculty.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.engineering.faculty.dto.ExaminationPeriodDto;
import it.engineering.faculty.entity.ExaminationPeriodEntity;
import it.engineering.faculty.entity.util.PeriodStatus;
import it.engineering.faculty.exception.ExistEntityException;
import it.engineering.faculty.mapper.ExaminationPeriodMapper;
import it.engineering.faculty.repository.ExaminationPeriodRepository;
import it.engineering.faculty.service.ExaminationPeriodService;

@Service
@Transactional
public class ExaminationPeriodServiceImpl implements ExaminationPeriodService {
	
	@Autowired
	private ExaminationPeriodRepository examPeriodRepository;
	private ExaminationPeriodMapper examPeriodMapper 
				= Mappers.getMapper(ExaminationPeriodMapper.class);

	@Override
	public ExaminationPeriodDto save(ExaminationPeriodDto examinationPeriodDto) throws Exception {
		Optional<ExaminationPeriodEntity> entity = 
				examPeriodRepository.findById(examinationPeriodDto.getId());
		
		if(entity.isPresent()) {
			System.out.println("prvi exception if");
			throw new ExistEntityException("Examination Period already exists!", entity.get());
		}
		
		List<ExaminationPeriodEntity> entityStatusByStatus =
				examPeriodRepository.findByStatus(examinationPeriodDto.getStatus());

		entityStatusByStatus.stream().forEach(period ->{
			if(period.getStatus() == PeriodStatus.ACTIVE) {
				throw new NoSuchElementException("Examination Period already exists with status ACTIVE!");
			}
		});
		
		ExaminationPeriodEntity examPeriod = 
				examPeriodRepository.save(examPeriodMapper.toExaminationPeriodEntity(examinationPeriodDto));
		return examPeriodMapper.toExaminationPeriodDto(examPeriod);
	}

	@Override
	public Optional<ExaminationPeriodDto> update(ExaminationPeriodDto examinationPeriodDto) throws Exception {
		Optional<ExaminationPeriodEntity> entity = 
				examPeriodRepository.findById(examinationPeriodDto.getId());
		if(entity.isPresent()) {
			ExaminationPeriodEntity examPeriod = 
					examPeriodRepository.save(examPeriodMapper.toExaminationPeriodEntity(examinationPeriodDto));
			return Optional.of(examPeriodMapper.toExaminationPeriodDto(examPeriod));
		}

		return Optional.empty();	
	}

	@Override
	public Optional<ExaminationPeriodDto> findById(Long examinationPeriodId) throws Exception {
		Optional<ExaminationPeriodEntity> period = 
				examPeriodRepository.findById(examinationPeriodId);
		if(period.isPresent()) {
			return Optional.of(examPeriodMapper.toExaminationPeriodDto(period.get()));
		}
		return Optional.empty();
	}

	@Override
	public void deleteById(Long examinationPeriodId) throws Exception {
		examPeriodRepository.deleteById(examinationPeriodId);
	}

	@Override
	public List<ExaminationPeriodDto> getAll() throws Exception {
		List<ExaminationPeriodEntity> periodList 
					= (List<ExaminationPeriodEntity>) examPeriodRepository.findAll();
		return periodList.stream().map(period ->{
			return examPeriodMapper.toExaminationPeriodDto(period);
		}).collect(Collectors.toList());
	}

	@Override
	public Page<ExaminationPeriodDto> findByPage(Pageable pageable) {
		return examPeriodRepository.findAll(pageable).map(examPeriodMapper::toExaminationPeriodDto);
	}

	@Override
	public ExaminationPeriodDto findByStatusActive() {
		
		ExaminationPeriodEntity activePeriod = null;
		List<ExaminationPeriodEntity> activeEntityList = (List<ExaminationPeriodEntity>) examPeriodRepository.findAll();
		
		for(ExaminationPeriodEntity period: activeEntityList) {
			if(period.getStatus() == PeriodStatus.ACTIVE) {
				activePeriod =  period;
			}
		}
		
		return examPeriodMapper.toExaminationPeriodDto(activePeriod);
	}

}
