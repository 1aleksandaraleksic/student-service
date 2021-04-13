package it.engineering.faculty.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;



public class ExamDto {

	@NotNull
	private Long id;
	
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date examDate;
	
	@NotNull
	@Valid
	private SubjectDto subject;
	
	@NotNull
	@Valid
	private ProfessorDto professor;
	
	@NotNull
	@Valid
	private ExaminationPeriodDto period;
	
	
	private List<ExamRegistrationDto> listOfRegisteredStudents;
	
	public ExamDto() {
		this.listOfRegisteredStudents = new ArrayList<ExamRegistrationDto>();
	}

	public ExamDto(@NotNull Long id, @NotNull Date examDate, @NotNull @Valid SubjectDto subject,
			@NotNull @Valid ProfessorDto professor, @NotNull @Valid ExaminationPeriodDto period) {
		super();
		this.id = id;
		this.examDate = examDate;
		this.subject = subject;
		this.professor = professor;
		this.period = period;
		this.listOfRegisteredStudents = new ArrayList<ExamRegistrationDto>();
	}

	public ExamDto(@NotNull Long id, @NotNull Date examDate, @NotNull @Valid SubjectDto subject,
			@NotNull @Valid ProfessorDto professor, @NotNull @Valid ExaminationPeriodDto period,
			List<ExamRegistrationDto> listOfRegisteredStudents) {
		super();
		this.id = id;
		this.examDate = examDate;
		this.subject = subject;
		this.professor = professor;
		this.period = period;
		this.listOfRegisteredStudents = listOfRegisteredStudents;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public SubjectDto getSubject() {
		return subject;
	}

	public void setSubject(SubjectDto subject) {
		this.subject = subject;
	}

	public ProfessorDto getProfessor() {
		return professor;
	}

	public void setProfessor(ProfessorDto professor) {
		this.professor = professor;
	}

	public ExaminationPeriodDto getPeriod() {
		return period;
	}

	public void setPeriod(ExaminationPeriodDto period) {
		this.period = period;
	}

	public List<ExamRegistrationDto> getListOfRegisteredStudents() {
		return listOfRegisteredStudents;
	}

	public void setListOfRegisteredStudents(List<ExamRegistrationDto> listOfRegisteredStudents) {
		this.listOfRegisteredStudents = listOfRegisteredStudents;
	}

	@Override
	public String toString() {
		return "ExamDto [id=" + id + ", examDate=" + examDate + ", subject=" + subject + ", professor=" + professor
				+ ", period=" + period + "]";
	}
	
}
