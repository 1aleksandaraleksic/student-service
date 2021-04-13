package it.engineering.faculty.dto;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import it.engineering.faculty.entity.util.Semester;

public class SubjectDto {

	@NotNull
	private Long id;
	
	@NotNull
	@Size(max = 30,
			  message = "Maximum number of character is 30.")
	private String name;
	
	@Size(max = 200,
			  message = "Maximum number of character is 200.")
	private String description;
	
	@NotNull
	@Min(value = 1)
	@Max(value = 21)	
	private int noOfEspb;
	
	@Min(value = 1)
	@Max(value = 4)
	private int yearOfStudy;
	
	@Valid
	private Semester semester;
	
	@Valid
	private ProfessorDto professor;
	
	public SubjectDto() {

	}
	
	public SubjectDto(@NotNull Long id,
			@NotNull @Size(max = 30, message = "Maximum number of character is 30.") String name,
			@Size(max = 200, message = "Maximum number of character is 200.") String description,
			@NotNull @Min(1) @Max(21) int noOfEspb, @Min(1) @Max(4) int yearOfStudy, @Valid Semester semester,
			@Valid ProfessorDto professor) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.noOfEspb = noOfEspb;
		this.yearOfStudy = yearOfStudy;
		this.semester = semester;
		this.professor = professor;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNoOfEspb() {
		return noOfEspb;
	}

	public void setNoOfEspb(int noOfEspb) {
		this.noOfEspb = noOfEspb;
	}

	public int getYearOfStudy() {
		return yearOfStudy;
	}

	public void setYearOfStudy(int yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	public ProfessorDto getProfessor() {
		return professor;
	}

	public void setProfessor(ProfessorDto professor) {
		this.professor = professor;
	}

	@Override
	public String toString() {
		return "SubjectDto [id=" + id + ", name=" + name + ", description=" + description + ", noOfEspb=" + noOfEspb
				+ ", yearOfStudy=" + yearOfStudy + ", semester=" + semester + ", professor=" + professor + "]";
	}
	
}
