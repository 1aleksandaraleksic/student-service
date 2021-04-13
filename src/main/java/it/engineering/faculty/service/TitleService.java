package it.engineering.faculty.service;

import java.util.List;
import it.engineering.faculty.dto.TitleDto;

public interface TitleService {

	TitleDto save(TitleDto titleDto) throws Exception;
	List<TitleDto> getAll() throws Exception;

}
