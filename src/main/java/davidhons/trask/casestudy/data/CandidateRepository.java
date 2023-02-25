package davidhons.trask.casestudy.data;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import davidhons.trask.casestudy.domain.Candidate;



public interface CandidateRepository extends JpaRepository<Candidate, Long> {

	
	@Query("SELECT c FROM Candidate as c WHERE c.candidateName = :candidateName")
    public Candidate getCandidateByCandidateName(@Param("candidateName") String candidateName);
	
	@EntityGraph(attributePaths = {"techs"})
	public Candidate getByCandidateName( String candidateName);
	
}
