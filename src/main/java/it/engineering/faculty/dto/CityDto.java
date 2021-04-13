package it.engineering.faculty.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

import com.sun.istack.NotNull;

public class CityDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Min(value = 10000)
	@Max(value = 99999)
	private Long postalCode;
	
	@NonNull
	@Size(min = 2, 
		  message = "Minimum number of character is 2.")
	private String cityName;
	
	public CityDto() {

	}

	public CityDto(@Min(10000) @Max(99999) Long postalCode,
			@Size(min = 2, message = "Minimum number of character is 2.") String cityName) {
		super();
		this.postalCode = postalCode;
		this.cityName = cityName;
	}

	public Long getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Long postalCode) {
		this.postalCode = postalCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public String toString() {
		return "CityDto [postalCode=" + postalCode + ", cityName=" + cityName + "]";
	}
	
	
}
