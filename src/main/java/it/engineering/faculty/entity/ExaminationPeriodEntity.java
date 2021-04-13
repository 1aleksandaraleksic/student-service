package it.engineering.faculty.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import it.engineering.faculty.entity.util.PeriodStatus;

@Entity
@Table(name = "examination_period")
public class ExaminationPeriodEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(
			nullable = false,
			length = 20)
	private String name;
	
	@Column(nullable = false)
	private Date start;
	
	@Column(nullable = false)
	private Date end;
	
	@Enumerated(EnumType.STRING)
	@Column(
			nullable = false,
			length = 20)
	private PeriodStatus status;
	
	public ExaminationPeriodEntity() {

	}

	public ExaminationPeriodEntity(Long id, String name, Date start, Date end, PeriodStatus status) {
		super();
		this.id = id;
		this.name = name;
		this.start = start;
		this.end = end;
		this.status = status;
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

	@Override
	public String toString() {
		return "ExaminationPeriod [id=" + id + ", name=" + name + ", start=" + start + ", end=" + end + ", status="
				+ status + "]";
	}
	
}
