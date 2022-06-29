package com.pradeep.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pradeep.entities.OtherCompanyInfo;


@Repository
public interface IOtherCompanyRepository extends JpaRepository<OtherCompanyInfo, Long>{

	Optional<OtherCompanyInfo> findByCompanyName(String companyName);
}
