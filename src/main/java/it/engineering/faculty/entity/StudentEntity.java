package it.engineering.faculty.entity;

import java.io.Serializable;

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
@Table(name = "student")
public class StudentEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",
			length = 20)
	private Long id;
	
	@Column(name = "index_number", 
			unique = true, 
			length = 4,
			nullable = false)
	private String indexNumber;
	
	@Column(name = "index_year", 
			unique = true,
			length = 4,
			nullable = false)
	private String indexYear;
	
	@Column(name = "first_name",
			length = 30,
			nullable = false)
	private String firstName;
	
	@Column(name = "last_name",
			length = 30,
			nullable = false)
	private String lastName;
	
	@Column(name = "email",
			length = 30,
			unique = true
			)
	private String email;
	
	@Column(name = "current_year_of_study")
	private int currentYearOfStudy;
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_student_postal_code"), 
				name = "postal_code")
	private CityEntity city;
	
	public StudentEntity() {

	}

	public StudentEntity(Long id, String indexNumber, String indexYear, String firstName, String lastName, String email,
			int currentYearOfStudy, CityEntity city) {
		super();
		this.id = id;
		this.indexNumber = indexNumber;
		this.indexYear = indexYear;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.currentYearOfStudy = currentYearOfStudy;
		this.city = city;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIndexNumber() {
		return indexNumber;
	}

	public void setIndexNumber(String indexNumber) {
		this.indexNumber = indexNumber;
	}

	public String getIndexYear() {
		return indexYear;
	}

	public void setIndexYear(String indexYear) {
		this.indexYear = indexYear;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCurrentYearOfStudy() {
		return currentYearOfStudy;
	}

	public void setCurrentYearOfStudy(int currentYearOfStudy) {
		this.currentYearOfStudy = currentYearOfStudy;
	}

	public CityEntity getCity() {
		return city;
	}

	public void setCity(CityEntity city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "StudentEntity [id=" + id + ", indexNumber=" + indexNumber + ", indexYear=" + indexYear
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", currentYearOfStudy="
				+ currentYearOfStudy + ", city=" + city + "]";
	}

		
}
