package kr.or.ddit.freeboard.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.fileitem.dao.FileItemDaoImpl;
import kr.or.ddit.fileitem.dao.IFileItemDao;
import kr.or.ddit.freeboard.dao.FreeboardDaoImpl;
import kr.or.ddit.freeboard.dao.IFreeboardDao;
import kr.or.ddit.utiles.AttachFileMapper;
import kr.or.ddit.vo.FileItemVO;
import kr.or.ddit.vo.FreeboardVO;

// 설정파일 : <bean name="freeboardService" class="kr.or.ddit.freeboard.service.FreeboardServiceImpl"
@Service("freeboardService")
public class FreeboardServiceImpl implements IFreeboardService {
	@Autowired
	private IFreeboardDao dao;
	@Autowired
	private IFileItemDao fileitemdao;

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<FreeboardVO> freeboardList(Map<String, String> params)
			throws Exception {
		List<FreeboardVO> freeboardList = null;

		return freeboardList = dao.freeboardList(params);

	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	public String insertFreeboard(FreeboardVO freeboardInfo, MultipartFile[] items)
			throws Exception {
		String bo_no = null;
		bo_no = dao.insertFreeboard(freeboardInfo);
		List<FileItemVO> fileItemList = AttachFileMapper.mapper(items, bo_no);
		fileitemdao.insertFileItem(fileItemList);

		return bo_no;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	public String insertFreeboard(FreeboardVO freeboardInfo)
			throws Exception {
		String bo_no = null;
		bo_no = dao.insertFreeboard(freeboardInfo);
		
		return bo_no;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public FreeboardVO freeboardInfo(Map<String, String> params)
			throws Exception {
		FreeboardVO freeboardInfo = null;

		return freeboardInfo = dao.freeboardInfo(params);

	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	public void deleteFreeboard(Map<String, String> params) throws Exception {
		dao.deleteFreeboard(params);

	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	public void updateFreeboard(FreeboardVO freeboardInfo) throws Exception {

		dao.updateFreeboard(freeboardInfo);

	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	public String insertFreeboardReply(FreeboardVO freeboardInfo)
			throws Exception {
		String bo_no = null;

		return bo_no = dao.insertFreeboardReply(freeboardInfo);

	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public String totalCount(Map<String, String> params) throws Exception {
		String totalCount = null;

		return totalCount = dao.totalCount(params);

	}
}
