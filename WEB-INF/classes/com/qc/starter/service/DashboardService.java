package com.qc.starter.service;

import java.util.List;

import com.qc.starter.dto.DashboardDto;
import com.qc.starter.dto.LeadsSerchDto;
import com.qc.starter.entity.CampaignEntity;
import com.qc.starter.entity.ProductEntity;
import com.qc.starter.entity.UserEntity;

public interface DashboardService {

	List<DashboardDto> getMyleadListState(String string, String userid);

	List<UserEntity> getMyTeamList(Integer userid);


	List<ProductEntity> getproductList(String userid);

	List<DashboardDto> getMyTeamListState(String productId, String string);

	List<DashboardDto> getDayDataForLead(String string, String date);
	List<DashboardDto> getMonthDataForLead(String string, String date);
	List<DashboardDto> getWeekDataForLead(String string, String date);

	List<CampaignEntity> getCompaignList();

	List<DashboardDto> getdashboardData(LeadsSerchDto serchDto);

}