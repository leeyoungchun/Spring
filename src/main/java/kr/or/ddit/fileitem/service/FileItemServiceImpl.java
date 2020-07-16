package kr.or.ddit.fileitem.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.or.ddit.fileitem.dao.FileItemDaoImpl;
import kr.or.ddit.fileitem.dao.IFileItemDao;
import kr.or.ddit.vo.FileItemVO;

@Service("fileItemService")
public class FileItemServiceImpl implements IFileItemService {
	@Autowired
	private IFileItemDao dao;
	
	@Override
	public void insertFileItem(List<FileItemVO> fileitemList) throws Exception{
		
			dao.insertFileItem(fileitemList);
		
	}

	@Override
	public FileItemVO fileitemInfo(Map<String, String> params) throws Exception{
		FileItemVO fileitemInfo = null;
		
		return 	fileitemInfo = dao.fileitemInfo(params);
		
	}

}
