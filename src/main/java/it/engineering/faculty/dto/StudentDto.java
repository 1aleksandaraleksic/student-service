package it.engineering.faculty.dto;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class StudentDto {

	@NotNull
	private Long id;
	
	@NotNull
	@Min(value = 0001)
	@Max(value = 2000)
	@Size(min = 4, 
		  max = 4,
		  message = "Index must have exactly 4 characters.")
	private String indexNumber;
	
	@NotNull
	@Min(value = 1900)
	@Max(value = 2100)
	@Size(min = 4, 
		  max = 4,
		  message = "Index must have exactly 4 characters.")
	private String indexYear;
	
	@NotNull
	@Size(max = 30,
			  message = "Maximum number of character is 30.")
	private String firstName;
	
	@NotNull
	@Size(max = 30,
			  message = "Maximum number of character is 30.")
	private String lastName;
	
	@Size(max = 30)
	@Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+"
					+ "@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|"
					+ "(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$",
			 message = "E-mail must contain a character '@'.")
	private String email;
	
	@NotNull
	@Min(value = 1)
	@Max(value = 9)
	private int currentYearOfStudy;
	
	@Valid
	private CityDto city;
	
	public StudentDto() {

	}
	
	public StudentDto(@NotNull Long id,
			@NotNull @Min(1) @Max(2000) @Size(min = 4, max = 4, message = "Index must have exactly 4 characters.") String indexNumber,
			@NotNull @Min(1900) @Max(2100) @Size(min = 4, max = 4, message = "Index must have exactly 4 characters.") String indexYear,
			@NotNull @Size(max = 30, message = "Maximum number of character is 30.") String firstName,
			@NotNull @Size(max = 30, message = "Maximum number of character is 30.") String lastName,
			@Size(max = 30) @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$", message = "E-mail must contain a character '@'.") String email,
			@NotNull @Min(1) @Max(9) int currentYearOfStudy, @Valid CityDto city) {
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

	public CityDto getCity() {
		return city;
	}

	public void setCity(CityDto city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "StudentDto [id=" + id + ", indexNumber=" + indexNumber + ", indexYear=" + indexYear
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", currentYearOfStudy="
				+ currentYearOfStudy + ", city=" + city + "]";
	}
	
}
