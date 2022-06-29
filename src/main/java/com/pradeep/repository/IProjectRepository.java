package com.pradeep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pradeep.entities.Project;

@Repository
public interface IProjectRepository extends JpaRepository<Project, Long>{

	

}
