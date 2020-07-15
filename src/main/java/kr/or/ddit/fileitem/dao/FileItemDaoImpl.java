package kr.or.ddit.fileitem.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.fileitem.dao.IFileItemDao;
import kr.or.ddit.vo.FileItemVO;

@Repository("fileItemDAO")
public class FileItemDaoImpl implements IFileItemDao {
	@Autowired
	private SqlMapClient smc;
	
	@Override
	public void insertFileItem(List<FileItemVO> fileitemList) throws Exception {
		try{
			// ibatis 트랜잭션
			// Commit : startTransaction() => 쿼리 질의(전체 성공)
			//			=> commitTransaction()
			//			=> endTransaction() 
			// Rollback : startTransaction() => 쿼리 질의(일부 성공 후 에러)
			//		      => endTransaction()
			smc.startTransaction();
			for(FileItemVO fileItemInfo : fileitemList){
				smc.insert("fileitem.insertFileItem",fileItemInfo);
			}
			smc.commitTransaction();
		} finally{
			smc.endTransaction();
		}
	}
	@Override
	public FileItemVO fileitemInfo(Map<String, String> params) throws Exception {
		return (FileItemVO) smc.queryForObject("fileitem.fileitemInfo",params);
	}

}
