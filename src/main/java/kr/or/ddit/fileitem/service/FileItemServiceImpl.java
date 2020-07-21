package kr.or.ddit.fileitem.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.fileitem.dao.FileItemDaoImpl;
import kr.or.ddit.fileitem.dao.IFileItemDao;
import kr.or.ddit.vo.FileItemVO;

@Service("fileItemService")
public class FileItemServiceImpl implements IFileItemService {
	@Autowired
	private IFileItemDao dao;
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	@Override
	public void insertFileItem(List<FileItemVO> fileitemList) throws Exception{
		
			dao.insertFileItem(fileitemList);
		
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	@Override
	public FileItemVO fileitemInfo(Map<String, String> params) throws Exception{
		FileItemVO fileitemInfo = null;
		
		return 	fileitemInfo = dao.fileitemInfo(params);
		
	}

}
