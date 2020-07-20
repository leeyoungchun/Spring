package kr.or.ddit.freeboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.or.ddit.fileitem.service.IFileItemService;
import kr.or.ddit.freeboard.service.IFreeboardService;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.utiles.CryptoGenerator;
import kr.or.ddit.utiles.RolePaginationUtil;
import kr.or.ddit.vo.FileItemVO;
import kr.or.ddit.vo.FreeboardVO;

import org.apache.commons.fileupload.FileItem;
import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/user/freeboard/")
public class FreeboardController {
	@Autowired
	private IFreeboardService freeboardService;
	@Autowired
	private IFileItemService fileitemService;
	
	@Autowired
	private CryptoGenerator cryptoGen;
	// http://localhost/SpringToddler/user/freeboard/freeboardList.do
	@RequestMapping("freeboardList")
	public ModelAndView freeboardList(Map<String,String> params
										,ModelAndView andView
										,HttpSession session
										,String search_keyword
										,String search_keycode
										,String currentPage
										,HttpServletRequest request
										,@RequestHeader("User-Agent") String agent
										,@RequestHeader("Accept-Language") String language
										,@CookieValue("JSESSIONID") String sessionID) throws Exception{
		if (currentPage == null) {
			currentPage = "1";
		}
		
		Map<String, String> publicKeyMap = this.cryptoGen.generatePairKey(session);
		
		params = new HashMap<String, String>();
		params.put("search_keyword", search_keyword);
		params.put("search_keycode", search_keycode);
		String totalCount = freeboardService.totalCount(params);
		
		RolePaginationUtil pagination = new RolePaginationUtil(request,
				Integer.parseInt(currentPage), Integer.parseInt(totalCount));
		params.put("startCount", String.valueOf(pagination.getStartCount()));
		params.put("endCount", String.valueOf(pagination.getEndCount()));
		
		
		
		List<FreeboardVO> freeboardList = this.freeboardService.freeboardList(params);
		andView.addObject("freeboardList",freeboardList);
		andView.addObject("publicKeyMap", publicKeyMap);
		andView.addObject("pagination",pagination.getPagingHtmls());
		andView.setViewName("user/freeboard/freeboardList");
		return andView;
	}
	
	@RequestMapping("insertFreeboard")
	public String insertFreeboard(FreeboardVO freeboardInfo, @RequestParam("files") MultipartFile[] items) throws Exception{
		this.freeboardService.insertFreeboard(freeboardInfo, items);
		return "redirect:/user/freeboard/freeboardList.do";
	}
	@RequestMapping("freeboardForm")
	public void freeboardForm(){}
	
	@RequestMapping("freeboardView")
	@ModelAttribute("freeboardInfo")
	public FreeboardVO freeboardView(Model model, String bo_no, Map<String,String> params, FreeboardVO freeboardInfo) throws Exception{
		params.put("bo_no", bo_no);
		freeboardInfo = this.freeboardService.freeboardInfo(params);
		//model.addAllAttributes("freeboardInfo",freeboardInfo);
		
		return freeboardInfo;
	}
	@RequestMapping("deleteFreeboard")
	public String deleteFreeboard(Map<String,String> params, String bo_no) throws Exception{
		params.put("bo_no", bo_no);
		freeboardService.deleteFreeboard(params);
		return "redirect:/user/freeboard/freeboardList.do";
	}
	@RequestMapping("updateFreeboard")
	public String updateFreeboard(FreeboardVO freeboardInfo) throws Exception{
		freeboardService.updateFreeboard(freeboardInfo);
		return "redirect:/user/freeboard/freeboardList.do";
	}
	@RequestMapping("freeboardReplyForm")
	public ModelAndView freeboardReplyForm(String bo_no, ModelAndView andView, Map<String,String> params) throws Exception{
		params.put("bo_no", bo_no);
		FreeboardVO freeboardInfo = freeboardService.freeboardInfo(params);
		andView.addObject("freeboardInfo",freeboardInfo);
		andView.setViewName("user/freeboard/freeboardReplyForm");
		return andView;
	}
	@RequestMapping("insertFreeboardReply")
	public String insertFreeboardReply(FreeboardVO freeboardInfo) throws Exception{
		System.out.println(freeboardInfo.getBo_depth());
		System.out.println(freeboardInfo.getBo_group());
		freeboardService.insertFreeboardReply(freeboardInfo);
		
		return "redirect:/user/freeboard/freeboardList.do";
	}
	@RequestMapping("freeFileDownload")
	public ModelAndView fileDownload(String file_seq, Map<String,String> params, ModelAndView andView) throws Exception{
		params.put("file_seq", file_seq);
		FileItemVO fileitemInfo = fileitemService.fileitemInfo(params);
		andView.addObject("fileitemInfo",fileitemInfo);
		andView.setViewName("fileDownloadView");
		return andView;
	}
	
	
	
	
	
	
	
	
	
}
