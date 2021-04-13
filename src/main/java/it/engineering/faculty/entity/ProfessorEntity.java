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
@Table(name = "professor")
public class ProfessorEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id",
			length = 20)
	private Long id;
	
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
			unique = true)
	private String email;
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_professor_postal_code"), name = "postal_code")
	private CityEntity city;
	
	@Column(name = "phone",
			length = 15)
	private String phone;
	
	@Column(name = "reelection_date",
			nullable = false)
	private Date reelectionDate;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_professor_title"), name = "title_id")
	private TitleEntity title;
	
	public ProfessorEntity() {

	}

	public ProfessorEntity(Long id, String firstName, String lastName, String email, CityEntity city,
			String phone, Date reelectionDate, TitleEntity title) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.city = city;
		this.phone = phone;
		this.reelectionDate = reelectionDate;
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public CityEntity getCity() {
		return city;
	}

	public void setCity(CityEntity city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public TitleEntity getTitle() {
		return title;
	}

	public void setTitle(TitleEntity title) {
		this.title = title;
	}

	public Date getReelectionDate() {
		return reelectionDate;
	}

	public void setReelectionDate(Date reelectionDate) {
		this.reelectionDate = reelectionDate;
	}

	@Override
	public String toString() {
		return "ProfessorEntity [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", city=" + city + ", phone=" + phone + ", reelectionDate=" + reelectionDate + ", title=" + title
				+ "]";
	}

}
