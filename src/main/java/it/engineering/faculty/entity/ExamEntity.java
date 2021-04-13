package it.engineering.faculty.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
@Table(name = "exam")
public class ExamEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(length = 20)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "exam_date",
			nullable = false)
	private Date examDate;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_exam_subject"), name = "subject_id")
	private SubjectEntity subject;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_exam_professor"), name = "professor_id")
	private ProfessorEntity professor;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_exam_period"), name = "period_id")
	private ExaminationPeriodEntity period;
	
	public ExamEntity() {
		
	}

	public ExamEntity(Long id, Date examDate, SubjectEntity subject, ProfessorEntity professor,
			ExaminationPeriodEntity period, List<ExamRegistrationEntity> listOfRegisteredStudents) {
		super();
		this.id = id;
		this.examDate = examDate;
		this.subject = subject;
		this.professor = professor;
		this.period = period;
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

	public SubjectEntity getSubject() {
		return subject;
	}

	public void setSubject(SubjectEntity subject) {
		this.subject = subject;
	}

	public ProfessorEntity getProfessor() {
		return professor;
	}

	public void setProfessor(ProfessorEntity professor) {
		this.professor = professor;
	}

	public ExaminationPeriodEntity getPeriod() {
		return period;
	}

	public void setPeriod(ExaminationPeriodEntity period) {
		this.period = period;
	}

	@Override
	public String toString() {
		return "ExamEntity [id=" + id + ", examDate=" + examDate + ", subject=" + subject + ", professor=" + professor
				+ ", period=" + period + "]";
	}


}
