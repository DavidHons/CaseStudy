package davidhons.trask.casestudy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import davidhons.trask.casestudy.data.CandidateRepository;
import davidhons.trask.casestudy.domain.Candidate;

@Service
public class CandidateServiceImpl implements CandidateService {

	@Autowired
	private CandidateRepository data;
	
	@Override
	public void addCandidate(Candidate candidate) throws CandidateAlreadyExistsException {
		
		if (candidateExists(candidate.getCandidateName()))
		{
			throw new CandidateAlreadyExistsException();
		}
		
		data.save(candidate);
		

	}

	@Override
	public List<Candidate> getAllCandidates() {
		
		return data.findAll();
	}

	
	private boolean candidateExists(String candidateName) 
	{
        return data.getCandidateByCandidateName(candidateName) != null;
    }

	@Override
	public List<String> getAllCandidateNames() {
		
		List<String> candidateNames = new ArrayList<String>();
		for (Candidate each : data.findAll())
		{
			candidateNames.add(each.getCandidateName());
		}
		
		return candidateNames;
	}

	@Override
	public void deleteCandidate(String name) throws CandidateDoesntExistException {
		
		if (candidateExists(name))
		{
		   data.delete(data.getCandidateByCandidateName(name));
		}
		else 
		{
		   throw new CandidateDoesntExistException();	
		}
			
	
	}

	@Override
	public Candidate getFullCandidateDetailsByCandidateName(String name) throws CandidateDoesntExistException {
		if(candidateExists(name))
		{
		    return data.getByCandidateName(name);
		}
		else
		{
			throw new CandidateDoesntExistException();
		}
	}

	@Override
	public Candidate getCandidateByName(String name) throws CandidateDoesntExistException {
		
		if(candidateExists(name))
		{
			return data.getCandidateByCandidateName(name);
		}
		else
		{
			throw new CandidateDoesntExistException();
		}
		
		
	}

	@Override
	public void updateCandidate(Candidate candidate) {

        data.save(candidate);
		
	}
	

}
