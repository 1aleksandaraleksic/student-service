package it.engineering.faculty.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TitleDto {

	@NotNull
	private Long id;
	
	@NotNull
	@Size(max = 30)
	private String tileName;
	
	public TitleDto() {

	}

	public TitleDto(@NotNull Long id, @NotNull @Size(max = 30) String tileName) {
		super();
		this.id = id;
		this.tileName = tileName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitleName() {
		return tileName;
	}

	public void setTitleName(String tileName) {
		this.tileName = tileName;
	}

	@Override
	public String toString() {
		return "TitleDto [id=" + id + ", tileName=" + tileName + "]";
	}
	
}
