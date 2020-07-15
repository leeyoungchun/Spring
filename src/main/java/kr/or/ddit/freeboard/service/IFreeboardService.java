package kr.or.ddit.freeboard.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import kr.or.ddit.vo.FreeboardVO;

public interface IFreeboardService {
	public List<FreeboardVO> freeboardList(Map<String, String> params);
	public String insertFreeboard(FreeboardVO freeboardInfo,FileItem[] items);
	public FreeboardVO freeboardInfo(Map<String, String> params);
	public void deleteFreeboard(Map<String,String> params);
	public void updateFreeboard(FreeboardVO freeboardInfo);
	public String insertFreeboardReply(FreeboardVO freeboardInfo);
	public String totalCount(Map<String, String> params);
}

