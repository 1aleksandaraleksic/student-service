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

import it.engineering.faculty.dto.ExamDto;
import it.engineering.faculty.entity.ExamEntity;
import it.engineering.faculty.entity.ExaminationPeriodEntity;
import it.engineering.faculty.entity.SubjectEntity;
import it.engineering.faculty.exception.ExistEntityException;
import it.engineering.faculty.mapper.ExamMapper;
import it.engineering.faculty.mapper.ExaminationPeriodMapper;
import it.engineering.faculty.mapper.SubjectMapper;
import it.engineering.faculty.repository.ExamRepository;
import it.engineering.faculty.repository.ExaminationPeriodRepository;
import it.engineering.faculty.service.ExamService;

@Service
@Transactional
public class ExamServiceImpl implements ExamService {

	@Autowired
	private ExamRepository examRepository;
	@Autowired
	private ExaminationPeriodRepository periodRepository;

	private ExamMapper examMapper = Mappers.getMapper(ExamMapper.class);
	private ExaminationPeriodMapper periodMapper = Mappers.getMapper(ExaminationPeriodMapper.class);
	private SubjectMapper subjectMapper = Mappers.getMapper(SubjectMapper.class);

	@Override
	public ExamDto save(ExamDto examDto) throws Exception {
		Optional<ExamEntity> entity = examRepository.findById(examDto.getId());

		if (entity.isPresent()) {
			throw new ExistEntityException("Exam already exists!", entity.get());
		}
		
		System.out.println("provera da li ispit vec postoji u bazi");
		System.out.println(checkExamExistInPeriod(examDto));
		if(checkExamExistInPeriod(examDto)) {
			System.out.println("ussao u metodu");
			throw new ExistEntityException("Exam already exists for that Period!", entity.get());
		}

		ExamEntity exam = examRepository.save(examMapper.toExamEntity(examDto));
		return examMapper.toExamDto(exam);
	}

	@Override
	public Optional<ExamDto> update(ExamDto examDto) throws Exception {
		Optional<ExamEntity> entity = examRepository.findById(examDto.getId());
		if (entity.isPresent()) {
			ExamEntity eentity = examRepository.save(examMapper.toExamEntity(examDto));
			return Optional.of(examMapper.toExamDto(eentity));
		}
		return Optional.empty();
	}

	@Override
	public Optional<ExamDto> findById(Long examId) throws Exception {
		Optional<ExamEntity> exam = examRepository.findById(examId);
		if (exam.isPresent()) {
			return Optional.of(examMapper.toExamDto(exam.get()));
		}
		return Optional.empty();
	}

	@Override
	public void deleteById(Long examId) throws Exception {
		examRepository.deleteById(examId);
	}

	@Override
	public List<ExamDto> getAll() throws Exception {
		List<ExamEntity> examList = (List<ExamEntity>) examRepository.findAll();
		return examList.stream().map(exam -> {
			return examMapper.toExamDto(exam);
		}).collect(Collectors.toList());
	}

	@Override
	public List<ExamDto> findByPeriod(Long periodId) {
		Optional<ExaminationPeriodEntity> periodEntity = periodRepository.findById(periodId);
		if (periodEntity.isPresent()) {
			List<ExamEntity> examList = examRepository.findByPeriod(periodEntity.get());
			return examList.stream().map(exam -> {
				return examMapper.toExamDto(exam);
			}).collect(Collectors.toList());
		}
		return null;
	}

	@Override
	public Page<ExamDto> findByPage(Pageable pageable) {
		return examRepository.findAll(pageable).map(examMapper::toExamDto);
	}
	
	@Override
	public Page<ExamDto> findByPeriod(ExaminationPeriodEntity periodEntity, Pageable pageable) {
		return examRepository.findByPeriod(periodEntity, pageable).map(examMapper::toExamDto);
	}

	private boolean checkExamExistInPeriod(ExamDto examDto) {
		List<ExamEntity> examList = (List<ExamEntity>) examRepository.findAll();

		for (ExamEntity exam : examList) {
			ExaminationPeriodEntity period = 
					periodMapper.toExaminationPeriodEntity(examDto.getPeriod());
			SubjectEntity subject = 
					subjectMapper.toSubjectEntity(examDto.getSubject());

			if (exam.getPeriod().getId() == period.getId() && 
					exam.getSubject().getId() == subject.getId()) {
				return true;
			}
		}
		return false;
	}

}
