package com.pradeep.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pradeep.dtos.OperationResponseDto;
import com.pradeep.entities.OtherCompanyInfo;
import com.pradeep.exception.ResourceExistsException;
import com.pradeep.exception.ResourceNotFoundException;
import com.pradeep.repository.IOtherCompanyRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OtherCompanyServiceImpl implements IOtherCompanyService {

	@Autowired
	private IOtherCompanyRepository otherCompanyRepository;
	
	@Override
	public OtherCompanyInfo createOtherCompanyInfo(OtherCompanyInfo otherCompanyInfo) throws ResourceExistsException {
		findByCompanyName(otherCompanyInfo.getCompanyName());
		OtherCompanyInfo savedOtherCompanyInfo=otherCompanyRepository.save(otherCompanyInfo);
		return savedOtherCompanyInfo;
	}

	@Override
	public List<OtherCompanyInfo> getOtherCompanies() {
		List<OtherCompanyInfo> listOfOtherCompanies=otherCompanyRepository.findAll();
		return listOfOtherCompanies;
	}

	@Override
	public OtherCompanyInfo getOtherCompanyById(Long id) throws ResourceNotFoundException {
		OtherCompanyInfo company = otherCompanyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company not found :: " + id));
		return company;
	}

	@Override
	public OtherCompanyInfo updateOtherCompany(Long otherCompanyId, OtherCompanyInfo otherCompanyInfo) throws ResourceNotFoundException, ResourceExistsException {
		verifyByCompanyNameAndCompanyId(otherCompanyId,otherCompanyInfo.getCompanyName());
		OtherCompanyInfo existingCompany = otherCompanyRepository.findById(otherCompanyId).orElseThrow(() -> new ResourceNotFoundException("Company not found :: " + otherCompanyId));
		String[] ignoreFields = { "companyId",  "version", "createdBy", "createdDate" };
		BeanUtils.copyProperties(otherCompanyInfo, existingCompany, ignoreFields);
		OtherCompanyInfo savedOtherCompanyInfo=otherCompanyRepository.save(existingCompany);
		return savedOtherCompanyInfo;
	}

	@Override
	public OperationResponseDto deleteOtherCompany(Long otherCompanyId) throws ResourceNotFoundException {
		OtherCompanyInfo company = otherCompanyRepository.findById(otherCompanyId).orElseThrow(() -> new ResourceNotFoundException("Company not found :: " + otherCompanyId));
		otherCompanyRepository.delete(company);
		return new OperationResponseDto(true,"Deleted successfully");
	}
	
	private void findByCompanyName(String companyName) throws ResourceExistsException {
		Optional<OtherCompanyInfo> optionalOtherCompanyInfo=otherCompanyRepository.findByCompanyName(companyName);
		if(optionalOtherCompanyInfo.isPresent()) {
			throw new ResourceExistsException("Company exists");
		}
	}

	private void verifyByCompanyNameAndCompanyId(Long otherCompanyId, String companyName) throws ResourceExistsException, ResourceNotFoundException {
		Optional<OtherCompanyInfo> optionalOtherCompanyInfo = otherCompanyRepository.findByCompanyName(companyName);
		if(optionalOtherCompanyInfo.isPresent()) {
			OtherCompanyInfo otherCompanyInfo=optionalOtherCompanyInfo.get();
			if(otherCompanyInfo.getCompanyId().longValue()!=otherCompanyId.longValue()) {
				throw new ResourceExistsException("Company exists");
			}
		}
	}

}
