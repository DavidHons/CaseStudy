package davidhons.trask.casestudy.controller;

import java.util.ArrayList;
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
import davidhons.trask.casestudy.service.CandidateAlreadyExistsException;
import davidhons.trask.casestudy.service.CandidateDoesntExistException;
import davidhons.trask.casestudy.service.CandidateService;

@RestController
public class CandidateController {

	@Autowired
	private CandidateService candidateService;
	
	
	@GetMapping(value="/candidateList")
	public List<String> getAllCandidateNames()
	{
	    return candidateService.getAllCandidateNames();
	}
	
	
	@GetMapping(value="/candidate/{name}")
	public ResponseEntity<Candidate> getFullCandidateDetails(@PathVariable String name)
	{
		
		try 
		{
			return new ResponseEntity<Candidate>(candidateService.getFullCandidateDetailsByCandidateName(name),
					                             HttpStatus.OK);
		} 
		catch (CandidateDoesntExistException e) 
		{
			return new ResponseEntity<Candidate>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	
	@PostMapping(value="/addCandidate/{name}")
	public ResponseEntity<Candidate> addCandidate(@PathVariable String name)
	{
		Candidate newCandidate = new Candidate(name);
		
		try 
		{
			candidateService.addCandidate(newCandidate);
		} 
		catch (CandidateAlreadyExistsException e) 
		{
			return new ResponseEntity<Candidate>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Candidate>(newCandidate,HttpStatus.CREATED);
		
	}
	
	@PutMapping(value="/modifyCandidate/{name}/{newname}")
	public ResponseEntity<Candidate> modifyCandidate(@PathVariable String name,@PathVariable String newname)
	{
		Candidate candidate;
		try 
		{
			candidate = candidateService.getCandidateByName(name);
		}
		catch (CandidateDoesntExistException e) {
			return new ResponseEntity<Candidate>(HttpStatus.NOT_FOUND);
		}
		candidate.setCandidateName(newname);
		candidateService.updateCandidate(candidate);
		return new ResponseEntity<Candidate>(candidate,HttpStatus.OK);
		
	}
	
	@GetMapping(value="/findcandidates/{tech}/{level}")
	public List<Candidate> findCandidatesByTechnologyAndLevel(@PathVariable String tech,
															  @PathVariable int level)
	{
		
		List<Candidate> allCandidates = candidateService.getAllCandidates();
		List<Candidate> results = new ArrayList<Candidate>();
		for (Candidate eachCandidate : allCandidates)
		{
			for (Technology eachTech : eachCandidate.getTechnologies())
			{
				if (eachTech.getTechnologyName().equals(tech) && level <= eachTech.getTechnologyLevel())
				{
					results.add(eachCandidate);
				}
				
			}
		}
		return results;
		
		
		
		
		
	}
	
	@DeleteMapping(value="/deleteCandidate/{name}")
	public ResponseEntity deletCandidate(@PathVariable String name)
	{
		try 
		{
			candidateService.deleteCandidate(name);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} catch (CandidateDoesntExistException e) 
		{
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping(value ="/populate")
	public ResponseEntity populateTestData()
	{
		
		Candidate cand1 = new Candidate("David");
		
		cand1.addTechnology(new Technology("Java",10,"poznamka"));
		cand1.addTechnology(new Technology("Spring",10,"delsi poznamka"));
		cand1.addTechnology(new Technology("JPA",8,"a dalsi poznamka"));
		
		Candidate cand2 = new Candidate("Josef");
		cand2.addTechnology(new Technology("Java",8,"poznamka"));
		cand2.addTechnology(new Technology("Spring",5,"jaro"));
		
		try 
		{
			candidateService.addCandidate(cand1);
			candidateService.addCandidate(cand2);
		} 
		catch (CandidateAlreadyExistsException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity(HttpStatus.CREATED);
		
	}
	
}
