package davidhons.trask.casestudy.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import davidhons.trask.casestudy.domain.Technology;

public interface TechnologyRepository extends JpaRepository<Technology, Long> {

	
	@Query("SELECT t FROM Technology as t WHERE t.technologyName = :technologyName")
    public Technology getTechnologyByTechnologyName(@Param("technologyName") String technologyName);
	
	public Technology getTechnologyById(long id);
}
