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

import it.engineering.faculty.dto.ProfessorDto;
import it.engineering.faculty.entity.ProfessorEntity;
import it.engineering.faculty.exception.ExistEntityException;
import it.engineering.faculty.mapper.ProfessorMapper;
import it.engineering.faculty.repository.ProfessorRepository;
import it.engineering.faculty.service.ProfessorService;

@Service
@Transactional
public class ProfessorServiceImpl implements ProfessorService {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	private ProfessorMapper professorMapper = Mappers.getMapper(ProfessorMapper.class);

	@Override
	public ProfessorDto save(ProfessorDto professorDto) throws Exception {
		Optional<ProfessorEntity> entity = 
				professorRepository.findById(professorDto.getId());
		
		if(entity.isPresent()) {
			throw new ExistEntityException("Professor already exists!", entity.get());
		}
		
		ProfessorEntity professor = 
				professorRepository.save(professorMapper.toProfessorEntity(professorDto));
		return professorMapper.toProfessorDto(professor);
	}

	@Override
	public Optional<ProfessorDto> update(ProfessorDto professorDto) throws Exception {
		Optional<ProfessorEntity> entity = 
				professorRepository.findById(professorDto.getId());
		
		if(entity.isPresent()) {
			ProfessorEntity professor = 
					professorRepository.save(professorMapper.toProfessorEntity(professorDto));
			return Optional.of(professorMapper.toProfessorDto(professor));
		}
		
		return Optional.empty();
	}

	@Override
	public Optional<ProfessorDto> findById(Long professorId) throws Exception {
		Optional<ProfessorEntity> professor = professorRepository.findById(professorId);
		

		if(!professor.isPresent()) {
			throw new ExistEntityException("ne postoji profesor", professor);
		}
		return Optional.of(professorMapper.toProfessorDto(professor.get()));
	}

	@Override
	public void deleteById(Long professorId) throws Exception {
		professorRepository.deleteById(professorId);
	}

	@Override
	public List<ProfessorDto> getAll() throws Exception {
		List<ProfessorEntity> professorList = (List<ProfessorEntity>) professorRepository.findAll();
		return professorList.stream().map(professor ->{
			return professorMapper.toProfessorDto(professor);
		}).collect(Collectors.toList());
	}

	@Override
	public Page<ProfessorDto> findByPage(Pageable pageable) {
		return professorRepository.findAll(pageable).map(professorMapper::toProfessorDto);
	}

}
