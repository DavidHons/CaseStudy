package davidhons.trask.casestudy.domain;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Technology {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String technologyName;
	private int technologyLevel;
	private String notes;
	
		
	public Technology() {}


	public Technology(String technologyName, int technologyLevel, String notes)
	{

		this.technologyName = technologyName;
		this.technologyLevel = technologyLevel;
		this.notes = notes;
	}
	
	
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getTechnologyName() {
		return technologyName;
	}

	public void setTechnologyName(String technologyName) {
		this.technologyName = technologyName;
	}

	public int getTechnologyLevel() {
		return technologyLevel;
	}


	public void setTechnologyLevel(int technologyLevel) {
		this.technologyLevel = technologyLevel;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	@Override
	public int hashCode() {
		return Objects.hash(technologyName);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Technology other = (Technology) obj;
		return Objects.equals(technologyName, other.technologyName);
	}


	


}
