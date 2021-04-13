package it.engineering.faculty.dto;

import java.util.Date;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

public class ExamRegistrationDto {

	@NotNull
	private Long id;
	
	@NotNull
	@JsonFormat(pattern = "yyyy.MM.dd")
	private Date date;
	
	@NotNull
	@Valid
	private StudentDto student;
	
	@NotNull
	@Valid
	private ExamDto exam;
	
	public ExamRegistrationDto() {

	}
	
	public ExamRegistrationDto(Long id, Date date, @Valid StudentDto student, @Valid ExamDto exam) {
		super();
		this.id = id;
		this.date = date;
		this.student = student;
		this.exam = exam;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public StudentDto getStudent() {
		return student;
	}

	public void setStudent(StudentDto student) {
		this.student = student;
	}

	public ExamDto getExam() {
		return exam;
	}

	public void setExam(ExamDto exam) {
		this.exam = exam;
	}

	@Override
	public String toString() {
		return "ExamRegistrationDto [id=" + id + ", date=" + date + ", student=" + student + ", exam=" + exam + "]";
	}
	
}
