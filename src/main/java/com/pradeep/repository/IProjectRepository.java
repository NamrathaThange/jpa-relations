package com.pradeep.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pradeep.entities.Project;

@Repository
public interface IProjectRepository extends JpaRepository<Project, Long>{

	Optional<Project> findByCompanyIdAndJobNumber(String jobNumber);

	@Query("SELECT p FROM Project p WHERE p.jobNumber=:jobNumber AND p.companyId=:companyId AND NOT p.projectId=:projectId")
	Optional<Project> findByCompanyIdAndProjectIdAndJobNumber(@Param("companyId")Long companyId, @Param("projectId") Long projectId, @Param("jobNumber")String jobNumber);

	List<Project> findByCompanyId(Long CompanyId);
	

}
