package com.pradeep.service;

import java.util.List;

import com.pradeep.dtos.OperationResponseDto;
import com.pradeep.entities.OtherCompanyInfo;
import com.pradeep.exception.ResourceExistsException;
import com.pradeep.exception.ResourceNotFoundException;

public interface IOtherCompanyService {

	OtherCompanyInfo createOtherCompanyInfo(OtherCompanyInfo otherCompanyInfo) throws ResourceExistsException;

	List<OtherCompanyInfo> getOtherCompanies();

	OtherCompanyInfo getOtherCompanyById(Long id) throws ResourceNotFoundException;

	OtherCompanyInfo updateOtherCompany(Long otherCompanyId, OtherCompanyInfo otherCompanyInfo) throws ResourceNotFoundException, ResourceExistsException;

	OperationResponseDto deleteOtherCompany(Long otherCompanyId) throws ResourceNotFoundException;

}
