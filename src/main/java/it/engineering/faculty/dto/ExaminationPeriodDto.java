package it.engineering.faculty.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import it.engineering.faculty.entity.util.PeriodStatus;

public class ExaminationPeriodDto {

	@NotNull
	private Long id;
	
	@NotNull
	@Size(min = 3,
		  max= 30,
		  message = "Min character 3, and max is 30")
	private String name;
	
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date start;
	
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date end;
	
	@Valid
	private PeriodStatus status;
	
	private List<ExamDto> examList;
	
	public ExaminationPeriodDto() {
		examList = new ArrayList<ExamDto>();
	}

	public ExaminationPeriodDto(@NotNull Long id,
			@NotNull @Size(min = 3, max = 30, message = "Min character 3, and max is 30") String name,
			@NotNull Date start, @NotNull Date end, @Valid PeriodStatus status) {
		super();
		this.id = id;
		this.name = name;
		this.start = start;
		this.end = end;
		this.status = status;
		examList = new ArrayList<ExamDto>();
	}

	public ExaminationPeriodDto(@NotNull Long id,
			@NotNull @Size(min = 3, max = 30, message = "Min character 3, and max is 30") String name,
			@NotNull Date start, @NotNull Date end, @Valid PeriodStatus status, List<ExamDto> examList) {
		super();
		this.id = id;
		this.name = name;
		this.start = start;
		this.end = end;
		this.status = status;
		this.examList = examList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public PeriodStatus getStatus() {
		return status;
	}

	public void setStatus(PeriodStatus status) {
		this.status = status;
	}

	public List<ExamDto> getExamList() {
		return examList;
	}

	public void setExamList(List<ExamDto> examList) {
		this.examList = examList;
	}

	@Override
	public String toString() {
		if(examList.isEmpty()) {
			return "ExaminationPeriodDto [id=" + id + ", name=" + name + ", start=" + start + ", end=" + end + ", status="
					+ status + "]";			
		}
		return "ExaminationPeriodDto [id=" + id + ", name=" + name + ", start=" + start + ", end=" + end + ", status="
				+ status + ", examList=" + examList + "]";
	}
	
	
	
	
}
