package com.qc.starter.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qc.starter.dao.DashboardDao;
import com.qc.starter.dto.DashboardDto;
import com.qc.starter.dto.LeadsSerchDto;
import com.qc.starter.entity.CampaignEntity;
import com.qc.starter.entity.ProductEntity;
import com.qc.starter.entity.UserEntity;
import com.qc.starter.service.DashboardService;
@Service
public class DashboardServiceImpl  implements DashboardService{

	@Autowired
	DashboardDao dashboardDao;

	@Override
	public List<DashboardDto> getMyleadListState(String productId, String userid) {
		// TODO Auto-generated method stub
		List<DashboardDto> lst=dashboardDao.getMyleadListState(productId,userid);
		return lst;
	}

	@Override
	public List<UserEntity> getMyTeamList(Integer userid) {
		// TODO Auto-generated method stub
		List<UserEntity> lst=dashboardDao.getMyTeamList(userid);
		return lst;
	}

	@Override
	public List<ProductEntity> getproductList(String userid) {
		List<ProductEntity> lst=dashboardDao.getproductList(userid);
		return lst;

	}

	@Override
	public List<DashboardDto> getMyTeamListState(String productId, String string) {
		List<DashboardDto> lst=dashboardDao.getMyTeamListState(productId,string);
		return lst;
	}

	@Override
	public List<DashboardDto> getDayDataForLead(String userId, String date) {
		List<DashboardDto> lst=dashboardDao.getDayDataForLead(userId,date);
		return lst;
	}

	@Override
	public List<DashboardDto> getMonthDataForLead(String userId, String date) {
		List<DashboardDto> lst=dashboardDao.getMonthDataForLead(userId,date);
		return lst;
	}

	@Override
	public List<DashboardDto> getWeekDataForLead(String userId, String date) {
		List<DashboardDto> lst=dashboardDao.getWeekDataForLead(userId,date);
		return lst;
	}

	@Override
	public List<CampaignEntity> getCompaignList() {
		List<CampaignEntity> lst=dashboardDao.getCompaignList();
		return lst;
	}

	@Override
	public List<DashboardDto> getdashboardData(LeadsSerchDto serchDto) {
		List<DashboardDto> lst=dashboardDao.getdashboardData(serchDto);
		return lst;
	}

}