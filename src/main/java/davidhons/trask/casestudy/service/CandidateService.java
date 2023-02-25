package davidhons.trask.casestudy.service;

import java.util.List;

import davidhons.trask.casestudy.domain.Candidate;

public interface CandidateService {
	
	public void addCandidate(Candidate candidate) throws CandidateAlreadyExistsException;
	public List<Candidate> getAllCandidates();
	public List<String> getAllCandidateNames();
	public void deleteCandidate(String name) throws CandidateDoesntExistException;
	public Candidate getFullCandidateDetailsByCandidateName(String name) throws CandidateDoesntExistException;
	public Candidate getCandidateByName(String name) throws CandidateDoesntExistException;
	public void updateCandidate(Candidate candidate);

}
