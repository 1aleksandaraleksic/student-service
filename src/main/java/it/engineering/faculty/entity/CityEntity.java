package it.engineering.faculty.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "city")
public class CityEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "postal_code",
			length = 5,
			nullable = false)
	private Long postalCode;
	
	@Column(name = "city_name",
			length = 30,
			nullable = false)
	private String cityName;
	
	public CityEntity() {
		
	}

	public CityEntity(Long postalCode, String cityName) {
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
		return "CityEntity [postalCode=" + postalCode + ", cityName=" + cityName + "]";
	}

}
