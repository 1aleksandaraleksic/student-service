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

import it.engineering.faculty.dto.StudentDto;
import it.engineering.faculty.entity.StudentEntity;
import it.engineering.faculty.exception.ExistEntityException;
import it.engineering.faculty.mapper.StudentMapper;
import it.engineering.faculty.repository.StudentRepository;
import it.engineering.faculty.service.StudentService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	private StudentMapper studentMapper = Mappers.getMapper(StudentMapper.class);

	@Override
	public StudentDto save(StudentDto studentDto) throws Exception {
		Optional<StudentEntity> entity = studentRepository.findById(studentDto.getId());
		
		if(entity.isPresent()) {
			throw new ExistEntityException("Student already exist!", entity.get());
		}
		
		StudentEntity student = studentRepository.save(studentMapper.toStudentEntity(studentDto));
		return studentMapper.toStudentDto(student);
	}

	@Override
	public Optional<StudentDto> update(StudentDto studentDto) throws Exception {
		Optional<StudentEntity> entity = studentRepository.findById(studentDto.getId());
		
		if(entity.isPresent()) {
			StudentEntity student = studentRepository.save(studentMapper.toStudentEntity(studentDto));
			return Optional.of(studentMapper.toStudentDto(student));
		}
		
		return Optional.empty();
	}

	@Override
	public Optional<StudentDto> findById(Long studentId) throws Exception {
		Optional<StudentEntity> student = studentRepository.findById(studentId);
		
		if(student.isPresent()) {
			return Optional.of(studentMapper.toStudentDto(student.get()));
		}
		
		return Optional.empty();
	}

	@Override
	public void deleteById(Long studentId) throws Exception {
		studentRepository.deleteById(studentId);		
	}

	@Override
	public List<StudentDto> getAll() throws Exception {
		List<StudentEntity> studentList = (List<StudentEntity>) studentRepository.findAll();
		return studentList.stream().map(student ->{
			return studentMapper.toStudentDto(student);
		}).collect(Collectors.toList());
	}

	@Override
	public Page<StudentDto> findByPage(Pageable pageable) {
		Page<StudentDto> entites = studentRepository.findAll(pageable).map(studentMapper::toStudentDto);
		return entites;
	}

}
