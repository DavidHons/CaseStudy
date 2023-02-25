package davidhons.trask.casestudy.service;

import java.util.List;

import davidhons.trask.casestudy.domain.Technology;

public interface TechnologyService {

	
	
	public List<Technology> getAllTechnologies();
	public List<String> getAllTechnologyNames();
	public Technology getTechnologyById(long id) throws TechnologyDoesntExistException;
}
