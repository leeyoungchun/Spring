package kr.or.ddit.freeboard.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.vo.FreeboardVO;

public interface IFreeboardService {
	public List<FreeboardVO> freeboardList(Map<String, String> params) throws Exception;
	public String insertFreeboard(FreeboardVO freeboardInfo,MultipartFile[] items)throws Exception;
	public String insertFreeboard(FreeboardVO freeboardInfo)throws Exception;
	public FreeboardVO freeboardInfo(Map<String, String> params)throws Exception;
	public void deleteFreeboard(Map<String,String> params)throws Exception;
	public void updateFreeboard(FreeboardVO freeboardInfo)throws Exception;
	public String insertFreeboardReply(FreeboardVO freeboardInfo)throws Exception;
	public String totalCount(Map<String, String> params)throws Exception;
}

