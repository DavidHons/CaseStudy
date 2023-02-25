package davidhons.trask.casestudy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import davidhons.trask.casestudy.data.TechnologyRepository;
import davidhons.trask.casestudy.domain.Technology;

@Service
public class TechnologyServiceImpl implements TechnologyService {

	@Autowired
	private TechnologyRepository data;
	
	
	
	@Override
	public List<Technology> getAllTechnologies() {
		
		return data.findAll();
	}

	@Override
	public List<String> getAllTechnologyNames() {
		
		List<String> technologyNames = new ArrayList<String>();
		for (Technology each : data.findAll())
		{
			
			technologyNames.add(each.getTechnologyName());
			
		}
		
		return technologyNames;
	}

	@Override
	public Technology getTechnologyById(long id) throws TechnologyDoesntExistException {
		
		 Technology technology = data.getTechnologyById(id);
		 
		 if (technology == null)
		 {
			 throw new TechnologyDoesntExistException();
		 }
		
		return technology;
	}
}
