package com.pradeep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pradeep.entities.ProjectUser;

@Repository
public interface IProjectTeamRepository extends JpaRepository<ProjectUser, Long>{

	
	

}
