package kr.or.ddit.freeboard.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import kr.or.ddit.fileitem.dao.FileItemDaoImpl;
import kr.or.ddit.fileitem.dao.IFileItemDao;
import kr.or.ddit.freeboard.dao.FreeboardDaoImpl;
import kr.or.ddit.freeboard.dao.IFreeboardDao;
import kr.or.ddit.utiles.AttachFileMapper;
import kr.or.ddit.vo.FileItemVO;
import kr.or.ddit.vo.FreeboardVO;

public class FreeboardServiceImpl implements IFreeboardService {

	private static IFreeboardService service = new FreeboardServiceImpl();
	private IFreeboardDao dao;
	private IFileItemDao fileitemdao;
	
	private FreeboardServiceImpl(){
		dao = FreeboardDaoImpl.getInstance();
		fileitemdao = FileItemDaoImpl.getInstance();
	}
	
	public static IFreeboardService getInstance(){
		return (service == null) ? service : new FreeboardServiceImpl();
	}
	
	@Override
	public List<FreeboardVO> freeboardList(Map<String, String> params) {
		List<FreeboardVO> freeboardList = null;
		try {
			freeboardList= dao.freeboardList(params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return freeboardList;
	}

	@Override
	public String insertFreeboard(FreeboardVO freeboardInfo, FileItem[] items) {
		String bo_no = null;
		try {
			bo_no = dao.insertFreeboard(freeboardInfo);
			List<FileItemVO> fileItemList =  AttachFileMapper.mapper(items, bo_no);
			fileitemdao.insertFileItem(fileItemList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		return bo_no;
	}

	@Override
	public FreeboardVO freeboardInfo(Map<String, String> params){
		FreeboardVO freeboardInfo = null;
		try {
			freeboardInfo = dao.freeboardInfo(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return freeboardInfo;
	}

	@Override
	public void deleteFreeboard(Map<String, String> params) {
		try {
			dao.deleteFreeboard(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateFreeboard(FreeboardVO freeboardInfo) {
		try {
			dao.updateFreeboard(freeboardInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	   public String insertFreeboardReply(FreeboardVO freeboardInfo) {
	      String bo_no = null;
	      try{
	         bo_no = dao.insertFreeboardReply(freeboardInfo);
	      }catch(Exception e){
	         e.printStackTrace();
	      }
	      return bo_no ;
	   }

	@Override
	public String totalCount(Map<String, String> params) {
		String totalCount = null;
		try {
			totalCount = dao.totalCount(params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalCount;
	}
}
