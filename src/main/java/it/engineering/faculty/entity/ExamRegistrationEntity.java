package it.engineering.faculty.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "exam_registry")
public class ExamRegistrationEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id", 
			length = 20)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "date",
			nullable = false)
	private Date date;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_exam_registry_student"), name = "student_id")
	private StudentEntity student;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_exam_registry_exam"), name = "exam_id")
	private ExamEntity exam;

	public ExamRegistrationEntity() {
		
	}

	public ExamRegistrationEntity(Long id, Date date, StudentEntity student, ExamEntity exam) {
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

	public StudentEntity getStudent() {
		return student;
	}

	public void setStudent(StudentEntity student) {
		this.student = student;
	}

	public ExamEntity getExam() {
		return exam;
	}

	public void setExam(ExamEntity exam) {
		this.exam = exam;
	}

	@Override
	public String toString() {
		return "ExamRegistrationEntity [id=" + id + ", date=" + date + ", student=" + student + ", exam=" + exam + "]";
	}
	
}
