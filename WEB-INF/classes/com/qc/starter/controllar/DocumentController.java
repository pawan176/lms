package com.qc.starter.controllar;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.qc.starter.dto.DocumentDto;
import com.qc.starter.dto.LeadHeaderDto;
import com.qc.starter.entity.CaseXSellEntity;
import com.qc.starter.entity.DocumentEntity;
import com.qc.starter.entity.LeadEntity;
import com.qc.starter.entity.UserEntity;
import com.qc.starter.form.DocumentForm;
import com.qc.starter.service.CustomerService;
import com.qc.starter.service.DocumentService;
import com.qc.starter.service.LeadHeaderService;
import com.qc.starter.service.MasterService;
import com.qc.starter.service.XSellService;

@Controller
public class DocumentController {
	private static final int BUFFER_SIZE = 4096;
	private static final Logger logger = Logger.getLogger(DocumentController.class.getName());
	private static ResourceBundle resource1=ResourceBundle.getBundle("ApplicationResource");
	@Autowired	DocumentService documentService;
	@Autowired	LeadHeaderService leadHeaderService;
	@Autowired	CustomerService customerService;
	@Autowired	XSellService xSellService;
	@Autowired	MasterService masterService;

	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = { "document" })
	public String showDocument(@RequestParam("error") String docError,HttpSession session, Model model) {
		LeadEntity leadEntity = (LeadEntity) session.getAttribute("leadDetails");
		int leadid = leadEntity == null ? 1000001 : Integer.parseInt(leadEntity.getCaseId());
		logger.info("DocumentController | showDocument() | :- START");
		UserEntity userEntity = (UserEntity) session.getAttribute("UserDetails");
		logger.info("DocumentController | showDocument() | :- END");
		if (userEntity == null) {
			session.invalidate();
			
			return "redirect:login.do";
		} else {
			int companyId=Integer.parseInt(userEntity.getCompanyId());
			String personalDtlId = customerService.getCustomerFromLeadId(leadid).getPersonalDetailId();
			List<CaseXSellEntity> xSellList = xSellService.getXSellList(leadid);
			LeadHeaderDto leadHeaderDto = leadHeaderService.getLeadHeader(""+ leadid);
			List<DocumentDto> documentList = documentService.getDocumentHistory(leadid +"");
			//List<DocumentDto> documentList = documentService.getDocumentHistory(leadid +"","");
			DocumentForm documentForm = new DocumentForm();
			documentForm.setDocumentList(documentList);

			List<DocumentEntity> docmentTypeList = masterService.getDocumentTypeList(companyId, leadEntity.getQueueId());
			List<DocumentEntity> documentStatusList = masterService.getDocumentStatusList();
			List<DocumentEntity> documentCreatedByList = masterService.getDocumentCreatedByList();
			List<DocumentEntity> documentUpdatedByList = masterService.getDocumentUpdatedByList();

			/*	int loop=0;

			if(documentList.size()!=0 && docmentTypeList.size()!=0){
				for(DocumentDto docHist:documentList){
					for(DocumentEntity docTypelist:docmentTypeList){
						if(docHist.getDocumentTypeId()==docTypelist.getDocumentTypeId()){
							loop++;
						}
					}
				}
			}else{
				loop=3;
			}
			 */
			model.addAttribute("personalDtlId", personalDtlId);
			model.addAttribute("leadHeaderDetail", leadHeaderDto);
			model.addAttribute("xSellList", xSellList);
			model.addAttribute("documentForm", documentForm);
			model.addAttribute("docmentTypeMaster", docmentTypeList);
			model.addAttribute("documentStatusList", documentStatusList);
			model.addAttribute("documentCreatedByList", documentCreatedByList);
			model.addAttribute("documentUpdatedByList", documentUpdatedByList);
			model.addAttribute("docError", docError);
			logger.info("showDocument | showDocument() | :- END");
			return "document";
		}
	}

	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "getDocumentName")
	public @ResponseBody
	String getDocumentName(HttpSession session, Model model,
			@RequestParam("docType") String docType) {
		UserEntity userEntity = (UserEntity) session
				.getAttribute("UserDetails");
		if (userEntity == null) {
			session.invalidate();
				
			return "redirect:login.do?error=null";
		} else {
			List<DocumentDto> list = documentService.getDocumentName(docType);
			Gson gsonObj = new Gson();
			String responseJson = gsonObj.toJson(list);
			return responseJson;
		}
	}

	@RequestMapping(method = { RequestMethod.POST }, headers = "content-type=multipart/form-data", value = { "documentUpload" })
	public String saveorUpdateDocument(
			@ModelAttribute DocumentForm documentForm, HttpSession session,Model model) {
		LeadEntity leadEntity = (LeadEntity) session.getAttribute("leadDetails");
		int caseId = leadEntity == null ? 1000001 : Integer.parseInt(leadEntity.getCaseId());
		UserEntity userEntity = (UserEntity) session.getAttribute("UserDetails");
		List<DocumentDto> newDocList=new ArrayList<DocumentDto>();
		if (userEntity == null) {
			session.invalidate();
			
			return "redirect:login.do";
		} else {
			List<DocumentDto> docList = documentForm.getDocumentList();
			for (DocumentDto docData : docList) {
				System.out.println(docData.getDocumentBlob().getName());
				System.out.println(docData.getDocumentBlob().getOriginalFilename());
				if(docData.getDocId()!=null && !(docData.getDocId().equals(""))){
					docData.setMakerId(""+userEntity.getUserid());
					newDocList.add(docData);
				}else{
					System.out.println("Not checked");
				}
			}
			if(newDocList.size()>0){
				List<DocumentDto> list = documentService.saveUpdateDocument(newDocList, caseId);
			}
			return "redirect:document.do?error=null";
		}
	}


	@RequestMapping(method = { RequestMethod.POST }, headers = "content-type=multipart/form-data", value = { "sedocumentUpload" })
	public String sesaveorUpdateDocument(
			@ModelAttribute DocumentForm documentForm, HttpSession session,Model model) {
		LeadEntity leadEntity = (LeadEntity) session.getAttribute("leadDetails");
		int caseId = leadEntity == null ? 1000001 : Integer.parseInt(leadEntity.getCaseId());
		UserEntity userEntity = (UserEntity) session.getAttribute("UserDetails");
		List<DocumentDto> newDocList=new ArrayList<DocumentDto>();
		if (userEntity == null) {
			session.invalidate();
				
			return "redirect:login.do";
		} else {
			List<DocumentDto> docList = documentForm.getDocumentList();
			for (DocumentDto docData : docList) {
				if(docData.getDocId()!=null && !(docData.getDocId().equals(""))){
					docData.setMakerId(""+userEntity.getUserid());
					newDocList.add(docData);
				}else{
					System.out.println("Not checked");
				}
			}
			if(newDocList.size()>0){
				List<DocumentDto> list = documentService.saveUpdateDocument(newDocList, caseId);
				//String status2 = documentService.saveUpdatePathDocument(list,caseId);
			}
			return "workList";
		}
	}

	@RequestMapping(method = { RequestMethod.POST }, headers = "content-type=multipart/form-data", value = { "deleteDocument" })
	public String deleteDocument(@ModelAttribute DocumentForm documentForm,HttpSession session,Model model) {
		UserEntity userEntity = (UserEntity) session.getAttribute("UserDetails");
		LeadEntity leadEntity = (LeadEntity) session.getAttribute("leadDetails");
		int caseId = leadEntity == null ? 1000001 : Integer.parseInt(leadEntity.getCaseId());
		List<DocumentDto> newDocList=new ArrayList<DocumentDto>();
		if (userEntity == null) {
			session.invalidate();
		
			return "redirect:login.do";
		} else {
			List<DocumentDto> docList = documentForm.getDocumentList();
			for (DocumentDto docData : docList) {
				if(docData.getDocId()!=null && !(docData.getDocId().equals(""))){
					newDocList.add(docData);
				}else{
					System.out.println("Not checked");
				}
			}
			if(newDocList.size()>0){
				String status1=documentService.deleteDocument(newDocList,caseId);
				String status2 = documentService.deleteDocumentBlob(newDocList);
			}
			return "redirect:document.do?error=null";
		}
	}

	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "downloadDocument")
	public String download(HttpSession session, Model model,
			@RequestParam("docId") String docId,@RequestParam("docTypeId") String docTypeId, HttpServletResponse response) {
		List<DocumentEntity> list = documentService.getDocumentBlob(docId,docTypeId);
		/* Document doc = documentDao.get(documentId); */
		try {

			String filePath = list.get(0).getDocumentCode();
			filePath = filePath.replaceAll("\\\\", "//");

			FileInputStream fileInputStream = new FileInputStream(filePath);

			InputStream inputStream = (InputStream) fileInputStream;

			response.setHeader("Content-Disposition", "inline;filename=\""+ list.get(0).getDocumentName() + "\"");
			OutputStream out = response.getOutputStream();
			response.setContentType("application/octet-stream");
			IOUtils.copy(inputStream, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			return "redirect:document.do?error=NoDocFound";
		}
		return null;
	}

}