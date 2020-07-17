package kr.or.ddit.freeboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.or.ddit.freeboard.service.IFreeboardService;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.utiles.CryptoGenerator;
import kr.or.ddit.utiles.RolePaginationUtil;
import kr.or.ddit.vo.FreeboardVO;

import org.apache.commons.fileupload.FileItem;
import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/user/freeboard/")
public class FreeboardController {
	@Autowired
	private IFreeboardService service;
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
										,HttpServletRequest request) throws Exception{
		if (currentPage == null) {
			currentPage = "1";
		}
		
		Map<String, String> publicKeyMap = this.cryptoGen.generatePairKey(session);
		
		params = new HashMap<String, String>();
		params.put("search_keyword", search_keyword);
		params.put("search_keycode", search_keycode);
		String totalCount = service.totalCount(params);
		
		RolePaginationUtil pagination = new RolePaginationUtil(request,
				Integer.parseInt(currentPage), Integer.parseInt(totalCount));
		params.put("startCount", String.valueOf(pagination.getStartCount()));
		params.put("endCount", String.valueOf(pagination.getEndCount()));
		
		
		
		List<FreeboardVO> freeboardList = this.service.freeboardList(params);
		andView.addObject("freeboardList",freeboardList);
		andView.addObject("publicKeyMap", publicKeyMap);
		andView.addObject("pagination",pagination.getPagingHtmls());
		andView.setViewName("user/freeboard/freeboardList");
		return andView;
	}
	
	@RequestMapping("insertFreeboard")
	public String insertFreeboard(FreeboardVO freeboardInfo) throws Exception{
		this.service.insertFreeboard(freeboardInfo);
		return "redirect:/user/freeboard/freeboardList.do";
	}
	@RequestMapping("freeboardForm")
	public void freeboardForm(){}
	
	@RequestMapping("freeboardView")
	public ModelAndView freeboardView(ModelAndView andView, String bo_no, Map<String,String> params) throws Exception{
		params.put("bo_no", bo_no);
		FreeboardVO freeboardInfo = service.freeboardInfo(params);
		andView.addObject("freeboardInfo",freeboardInfo);
		andView.setViewName("user/freeboard/freeboardView");
		return andView;
	}
	@RequestMapping("deleteFreeboard")
	public String deleteFreeboard(Map<String,String> params, String bo_no) throws Exception{
		params.put("bo_no", bo_no);
		service.deleteFreeboard(params);
		return "redirect:/user/freeboard/freeboardList.do";
	}
	@RequestMapping("updateFreeboard")
	public String updateFreeboard(FreeboardVO freeboardInfo) throws Exception{
		service.updateFreeboard(freeboardInfo);
		return "redirect:/user/freeboard/freeboardList.do";
	}
	@RequestMapping("freeboardReplyForm")
	public ModelAndView freeboardReplyForm(String bo_no, ModelAndView andView, Map<String,String> params) throws Exception{
		params.put("bo_no", bo_no);
		FreeboardVO freeboardInfo = service.freeboardInfo(params);
		andView.addObject("freeboardInfo",freeboardInfo);
		andView.setViewName("user/freeboard/freeboardReplyForm");
		return andView;
	}
	@RequestMapping("insertFreeboardReply")
	public String insertFreeboardReply(FreeboardVO freeboardInfo) throws Exception{
		System.out.println(freeboardInfo.getBo_depth());
		System.out.println(freeboardInfo.getBo_group());
		service.insertFreeboardReply(freeboardInfo);
		
		return "redirect:/user/freeboard/freeboardList.do";
	}
	
	
	
	
	
	
	
	
	
	
}
