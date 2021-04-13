package it.engineering.faculty.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import it.engineering.faculty.entity.util.Semester;

@Entity
@Table(name = "subject")
public class SubjectEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 20)
	private Long id;
	
	@Column(name = "name",
			length = 30,
			nullable = false,
			unique = true)
	private String name;
	
	@Column(name = "description",
			length = 200)
	private String description;
	
	@Column(name = "no_of_espb",
			length = 1,
			nullable = false)
	private int noOfEspb;
	
	@Column(name = "year_of_study",
			length = 1)
	private int yearOfStudy;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "semester",
			length = 10)
	private Semester semester;
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_subject_professor"), name = "professor_id")
	private ProfessorEntity professor;
	
	public SubjectEntity() {
		
	}

	public SubjectEntity(Long id, String name, String description, int noOfEspb, int yearOfStudy, 
			Semester semester, ProfessorEntity professor) {
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

	public ProfessorEntity getProfessor() {
		return professor;
	}

	public void setProfessor(ProfessorEntity professor) {
		this.professor = professor;
	}

	@Override
	public String toString() {
		return "SubjectEntity [id=" + id + ", name=" + name + ", description=" + description + ", noOfEspb=" + noOfEspb
				+ ", yearOfStudy=" + yearOfStudy + ", semester=" + semester + ", professor=" + professor + "]";
	}
	
}
