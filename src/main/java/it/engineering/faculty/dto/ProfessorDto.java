package it.engineering.faculty.dto;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

public class ProfessorDto {

	@NotNull
	private Long id;
	
	@NotNull
	@Size(min = 3,
		  max = 30,
		  message = "Maximum number of character is 30.")
	private String firstName;
	
	@NotNull
	@Size(min = 3,
		  max = 30,
		  message = "Maximum number of character is 30.")
	private String lastName;
	
	@Size(max = 30,
	      message = "Maximum number of character is 30.")
	@Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+"
					+ "@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|"
					+ "(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$",
			 message = "E-mail must contain a character '@'.")
	private String email;
	
	@Valid
	private CityDto city;
	
	@Size(min = 6,
		  max = 15,
		  message = "Minimum number of character is 6, and maximum is 15.")
	private String phone;
	
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date reelectionDate;
	
	@NotNull
	@Valid
	private TitleDto title;
	
	public ProfessorDto() {
		
	}
	
	public ProfessorDto(Long id,
			@Size(min = 3, max = 30, message = "Maximum number of character is 30.") String firstName,
			@Size(min = 3, max = 30, message = "Maximum number of character is 30.") String lastName,
			@Size(max = 30, message = "Maximum number of character is 30.") @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$", message = "E-mail must contain a character '@'.") String email,
			@Valid CityDto city,
			@Size(min = 6, max = 15, message = "Minimum number of character is 6, and maximum is 15.") String phone,
			Date reelectionDate, @Valid TitleDto title) {
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
	
	public CityDto getCity() {
		return city;
	}
	
	public void setCity(CityDto city) {
		this.city = city;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Date getReelectionDate() {
		return reelectionDate;
	}
	
	public void setReelectionDate(Date reelectionDate) {
		this.reelectionDate = reelectionDate;
	}

	public TitleDto getTitle() {
		return title;
	}

	public void setTitle(TitleDto title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "ProfessorDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", city=" + city + ", phone=" + phone + ", reelectionDate=" + reelectionDate + ", title=" + title
				+ "]";
	}
	
}
