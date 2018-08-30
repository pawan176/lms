package com.qc.starter.controllar;

import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.qc.starter.dto.MasterDto;
import com.qc.starter.dto.PersonListDto;
import com.qc.starter.dto.SearchAndAllocateDto;
import com.qc.starter.entity.LeadEntity;
import com.qc.starter.entity.UserEntity;
import com.qc.starter.form.SearchAndAllocateForm;
import com.qc.starter.service.ContactService;
import com.qc.starter.service.MasterService;
import com.qc.starter.service.SearchAndAllocateService;


@Controller
public class SearchAndAllocateController {

	@Autowired SearchAndAllocateService searchAndAllocateService;
	@Autowired ContactService contactService;
	@Autowired	MasterService masterService;
	private static ResourceBundle resource1=ResourceBundle.getBundle("ApplicationResource");

	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value="serchAllocate")
	public String getWorklistPage(HttpSession session,Model model){

		MasterDto masterDto;
		/*
		UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");		
		if(userEntity==null){
			session.invalidate();
					String ceomessage = resource1.getString("CEOMESSAGE");
		String svpmessage = resource1.getString("SVPMESSAGE");
		model.addAttribute("ceomessage",ceomessage);
		model.addAttribute("svpmessage",svpmessage);
			return "redirect:login.do";		
		}
		int userid = userEntity.getUserid();
		List<SearchAndAllocateDto> LeadList = searchAndAllocateService.getLeadsToAllocate(userEntity,"");
		List<PersonListDto> peerAndreportee =  contactService.getAllPerson("",userid+"","peerAndreportee");
		model.addAttribute("peerAndreportee",peerAndreportee);
		model.addAttribute("LeadList",LeadList);*/
		UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");	
		if(userEntity==null){
			session.invalidate();		
			return "redirect:login.do";		
		}
		
		masterDto = masterService.getMasters("ProductMasterEntity");

		LeadEntity leadEntity = (LeadEntity)session.getAttribute("leadDetails");
		//LeadManager leadManager = LeadManager.getInstance();
		//boolean flag=leadManager.removeLead(leadEntity, userEntity);

		int userid = userEntity.getUserid();
		List<PersonListDto> peerAndreportee =  contactService.getAllPerson("",userid+"","peerAndreportee");
		model.addAttribute("peerAndreportee",peerAndreportee);
		model.addAttribute("masterDetail", masterDto);
		List<PersonListDto> allocatedTo = contactService.getSearchPerson();
		model.addAttribute("allocatedList", allocatedTo);
		return "serchallocate";
	}


	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value="allocatecases")
	public String allocateCases(HttpSession session,Model model,PersonListDto personDto,
			@RequestParam("case_no") String case_no,@RequestParam("subId") 
	String subId,@RequestParam("sourceId") 	String sourceId,@RequestParam("comid") String comid,
	@RequestParam("person_id") String personid,	@RequestParam("qid") String queid,@RequestParam("dnd") String dnd,
	@RequestParam("casetype") String flag){
		UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");
		SearchAndAllocateDto sdto= new SearchAndAllocateDto();
		sdto.setAllocate(personid);
		sdto.setQueueId(queid);
		sdto.setCampaignId(comid);
		sdto.setSourceId(sourceId);
		sdto.setNoOfCase(case_no);
		sdto.setSubQueueId(subId);
		sdto.setDnd(dnd);
		//searchAndAllocateService.allotcases(sdto, flag, userEntity.getUserid()+"", userEntity.getCompanyId());
		return "redirect:serchAllocate.do";
	}

	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value="getcases")
	public @ResponseBody
	String getCases(HttpSession session, @RequestParam("casetype") String type,Model model, @RequestParam(required=false) String userId){
		UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");				
		List<SearchAndAllocateDto> LeadList = searchAndAllocateService.getLeadsToAllocate(userEntity,type, userId);
		Gson gsonObj=new Gson();
		String responseJson=gsonObj.toJson(LeadList);
		return responseJson;
	}

	@RequestMapping(value={"saveAllotedcases"}, method=RequestMethod.POST)
	public String SearchAndAllocate(@ModelAttribute("SearchAndAllocate") SearchAndAllocateForm SearchAndAllocatedto,HttpSession session,@RequestParam("flag") String flag){	
		List<SearchAndAllocateDto> searchandallocate = SearchAndAllocatedto.getSearchAndAllocateDto();

		if(searchandallocate != null &&  searchandallocate.size() > 0 ){
			UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");
			searchAndAllocateService.allotcases(searchandallocate,userEntity,flag);
		}else{

			return "redirect:serchAllocate.do";
		}


		return "redirect:serchAllocate.do";
	}

	@RequestMapping(value={"sesaveAllotedcases"}, method=RequestMethod.POST)
	public String SearchAndAllocateExit(@ModelAttribute("SearchAndAllocate") SearchAndAllocateForm SearchAndAllocatedto,HttpSession session,@RequestParam("flag") String flag){	

		List<SearchAndAllocateDto> searchandallocate = SearchAndAllocatedto.getSearchAndAllocateDto();

		if(searchandallocate != null &&  searchandallocate.size() > 0 ){

			UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");
			searchAndAllocateService.allotcases(searchandallocate,userEntity,flag);
			return "workList";
		}
		else
		{

			return "workList";
		}

	}

}
