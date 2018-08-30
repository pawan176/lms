package com.qc.starter.controllar;

import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qc.starter.dto.MasterDto;
import com.qc.starter.entity.UserEntity;

public class SearchController {
	private static ResourceBundle resource1=ResourceBundle.getBundle("ApplicationResource");

	@RequestMapping(method={RequestMethod.GET,RequestMethod.POST},value="advanceSerch")
	public String getWorklistPage(HttpSession session,Model model){

		UserEntity userEntity= (UserEntity)session.getAttribute("UserDetails");		
		if(userEntity==null){
			session.invalidate();
			
			return "redirect:login.do";

		}
		MasterDto masterDto = (MasterDto) session.getAttribute("masterDto");
		model.addAttribute("masterDetail",masterDto);
		return "workList";
	}

}
