package davidhons.trask.casestudy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import davidhons.trask.casestudy.domain.Candidate;
import davidhons.trask.casestudy.domain.Technology;
import davidhons.trask.casestudy.service.CandidateDoesntExistException;
import davidhons.trask.casestudy.service.CandidateService;
import davidhons.trask.casestudy.service.TechnologyDoesntExistException;
import davidhons.trask.casestudy.service.TechnologyService;

@RestController
public class TechnologyController {
	
	
	@Autowired
	private TechnologyService technologyService;
	@Autowired
	private CandidateService candidateService;
	
	
	
	@GetMapping(value="/techlist")
	public List<String> getAllTechnologyNames()
	{
		return technologyService.getAllTechnologyNames();
	}

	@GetMapping(value="/getTech/{id}")
	public ResponseEntity<Technology> getTechnologyById(@PathVariable long id)
	{
		
		Technology technology;
		try {
			technology = technologyService.getTechnologyById(id);
		} catch (TechnologyDoesntExistException e) {
			return new ResponseEntity<Technology>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Technology>(technology,HttpStatus.OK);
	}
	@PostMapping(value="/addTech/{name}/{tech}/{level}/{notes}")
	public ResponseEntity<Technology> addTechnology(@PathVariable String name,
			                                        @PathVariable String tech,
			                                        @PathVariable int level,
			                                        @PathVariable String notes)
	{
		
		Candidate candidate;
		try {
			candidate = candidateService.getCandidateByName(name);
		} catch (CandidateDoesntExistException e) {
			return new ResponseEntity<Technology>(HttpStatus.NOT_FOUND);
		}
		
		Technology newTechnology = new Technology(tech,level,notes);
		
		for(Technology each : candidate.getTechnologies())
		{
			if (each.equals(newTechnology))
			{
				return new ResponseEntity<Technology>(HttpStatus.BAD_REQUEST);
			}
		}
		
		candidate.addTechnology(newTechnology);
		candidateService.updateCandidate(candidate);
		for(Technology techToReturn : candidate.getTechnologies())
		{
			if (techToReturn.getTechnologyName().equals(tech))
			{
				return new ResponseEntity<Technology> (techToReturn,HttpStatus.CREATED);
			}
		}	
		return new ResponseEntity<Technology>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@PutMapping(value="/modifyTech/{name}/{tech}/{level}/{notes}")
	public ResponseEntity<Technology> modifyTechnology(@PathVariable String name,
													   @PathVariable String tech,
													   @PathVariable int level,
													   @PathVariable String notes)
	{
		Candidate candidate;
		try {
			candidate = candidateService.getCandidateByName(name);
		} catch (CandidateDoesntExistException e) {
			return new ResponseEntity<Technology>(HttpStatus.NOT_FOUND);
		}
		
		for (Technology candidatesTech : candidate.getTechnologies())
		{
			if (candidatesTech.getTechnologyName().equals(tech))
			{
				
				candidate.getTechnologies().remove(candidatesTech);
				candidatesTech = new Technology (tech,level,notes);
				candidate.getTechnologies().add(candidatesTech);
				candidateService.updateCandidate(candidate);
				for(Technology techToReturn : candidate.getTechnologies())
				{
					if (techToReturn.getTechnologyName().equals(tech))
					{
						return new ResponseEntity<Technology> (techToReturn,HttpStatus.OK);
					}
				}	
			}
		}
		return new ResponseEntity<Technology>(HttpStatus.NOT_FOUND);
	}
	
	
	
	@DeleteMapping(value="/deleteTech/{name}/{tech}")
	public ResponseEntity deleteTechnologyByName(@PathVariable String name, @PathVariable String tech)
	{
		Candidate candidate;
		try {
			candidate = candidateService.getCandidateByName(name);
		} catch (CandidateDoesntExistException e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
		for (Technology candidatesTech : candidate.getTechnologies())
		{
			if (candidatesTech.getTechnologyName().equals(tech))
			{
				candidate.removeTechnology(candidatesTech);
				candidateService.updateCandidate(candidate);
				return new ResponseEntity (HttpStatus.OK);
			}
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);
		
		
	}

}
