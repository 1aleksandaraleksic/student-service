package it.engineering.faculty.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.engineering.faculty.dto.ExamRegistrationDto;
import it.engineering.faculty.entity.ExamRegistrationEntity;
import it.engineering.faculty.exception.ExistEntityException;
import it.engineering.faculty.mapper.ExamRegistrationMapper;
import it.engineering.faculty.repository.ExamRegistrationRepository;
import it.engineering.faculty.service.ExamRegistrationService;

@Service
@Transactional
public class ExamRegistrationServiceImpl implements ExamRegistrationService {
	
	@Autowired
	private ExamRegistrationRepository examRegistrationRepo;
	private ExamRegistrationMapper examRegistrationMapper = Mappers.getMapper(ExamRegistrationMapper.class);

	@Override
	public ExamRegistrationDto save(ExamRegistrationDto examRegistrationDto) throws Exception {

		if(examRegistrationDto.getExam().getSubject().getYearOfStudy() > 
						examRegistrationDto.getStudent().getCurrentYearOfStudy()) {
			String index = examRegistrationDto.getStudent().getIndexNumber();
			String indexYear = examRegistrationDto.getStudent().getIndexYear();
			throw new NoSuchElementException("Student with index: " 
								+ index + "/" + indexYear + " cannot register for the exam ");
		}
		
		if(!validateExam(examRegistrationDto)) {
			throw new NoSuchElementException("the exam can be registered no later than " 
							+ examRegistrationDto.getExam().getPeriod().getStart());
		}
		Optional<ExamRegistrationEntity> entity = 
				examRegistrationRepo.findById(examRegistrationDto.getId());
		
		if(entity.isPresent()) {
			throw new ExistEntityException("Exam already exists!", entity.get());
		}
		
		ExamRegistrationEntity exam = 
				examRegistrationRepo.save(examRegistrationMapper.toExamRegistrationEntity(examRegistrationDto));
		return examRegistrationMapper.toExamRegistrationDto(exam);
	}

	@Override
	public Optional<ExamRegistrationDto> update(ExamRegistrationDto examRegistrationDto) throws Exception {
		Optional<ExamRegistrationEntity> entity = 
				examRegistrationRepo.findById(examRegistrationDto.getId());
		
		if(entity.isPresent()) {
			ExamRegistrationEntity exam = 
					examRegistrationRepo.save(examRegistrationMapper.toExamRegistrationEntity(examRegistrationDto));
			return Optional.of(examRegistrationMapper.toExamRegistrationDto(exam));
		}
		
		return Optional.empty();
	}

	@Override
	public Optional<ExamRegistrationDto> findById(Long examRegistrationId) throws Exception {
		Optional<ExamRegistrationEntity> exam = 
				examRegistrationRepo.findById(examRegistrationId);
		if(exam.isPresent()) {
			return Optional.of(examRegistrationMapper.toExamRegistrationDto(exam.get()));
		}
		return Optional.empty();
	}

	@Override
	public void deleteById(Long examRegistrationId) throws Exception {
		examRegistrationRepo.deleteById(examRegistrationId);
	}

	@Override
	public List<ExamRegistrationDto> getAll() throws Exception {
		List<ExamRegistrationEntity> registrationList =
				(List<ExamRegistrationEntity>) examRegistrationRepo.findAll();
		return registrationList.stream().map(registration ->{
			return examRegistrationMapper.toExamRegistrationDto(registration);
		}).collect(Collectors.toList());
	}

	@Override
	public Page<ExamRegistrationDto> getAllRegistryExamForPeriodPageable(Long examId, Long periodId, Pageable pageable) {
		return examRegistrationRepo
				.findByPeriodPageable(examId, periodId, pageable)
				.map(examRegistrationMapper::toExamRegistrationDto);
	}
	
	@Override
	public List<ExamRegistrationDto> getAllRegistryExamForPeriod(Long examId, Long periodId) {
		List<ExamRegistrationEntity> registrationList =
				examRegistrationRepo.findByExamIdAndPeriodId(examId, periodId);
		registrationList.stream().forEach(System.out::println);
		return registrationList.stream().map((registry) ->{
			return examRegistrationMapper.toExamRegistrationDto(registry);
		}).collect(Collectors.toList());
	}

	
	private boolean validateExam(ExamRegistrationDto examRegDto) throws ParseException {

		long differenceTime = 
				examRegDto.getExam().getPeriod().getStart().getTime() - new Date().getTime();

		long differenceDays = 
				TimeUnit.DAYS.convert(differenceTime, TimeUnit.MILLISECONDS);
		
		if(differenceDays >= 0 && differenceDays <=7) {
			return true;
		}
		return false;
	}


}
