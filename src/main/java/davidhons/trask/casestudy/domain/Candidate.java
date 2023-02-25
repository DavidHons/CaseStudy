package davidhons.trask.casestudy.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Candidate {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String candidateName;
	
	@OneToMany(  cascade = CascadeType.ALL, orphanRemoval = true )
	private Set<Technology> techs = new HashSet<Technology>();
	
	
	public Candidate() {}


	public Candidate(String candidateName) {
	
		this.candidateName = candidateName;
				
	}
	
	public void addTechnology (Technology tech)
	{
		techs.add(tech);
	//	tech.setCandidate(this);
	}
	
	public void removeTechnology (Technology tech)
	{
		techs.remove(tech);
	//	tech.setCandidate(null);
	}
	
	
	public String getCandidateName() {
		return candidateName;
	}


	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}


	public Set<Technology> getTechnologies() {
		return techs;
	}


	public void setTechnologies(Set<Technology> techs) {
		this.techs = techs;
	}
	
	
	
	

}
