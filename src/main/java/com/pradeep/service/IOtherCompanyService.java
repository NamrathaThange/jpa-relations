package com.pradeep.service;

import java.util.List;

import com.pradeep.dtos.ResponseDto;
import com.pradeep.entities.OtherCompanyInfo;

public interface IOtherCompanyService {

	OtherCompanyInfo createOtherCompanyInfo(OtherCompanyInfo otherCompanyInfo);

	List<OtherCompanyInfo> getOtherCompanies();

	OtherCompanyInfo getOtherCompanyById(Long id);

	OtherCompanyInfo updateOtherCompany(Long otherCompanyId, OtherCompanyInfo otherCompanyInfo);

	ResponseDto deleteOtherCompany(Long otherCompanyId);

}
