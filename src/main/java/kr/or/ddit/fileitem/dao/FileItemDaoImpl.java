package kr.or.ddit.fileitem.dao;

import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.fileitem.dao.IFileItemDao;
import kr.or.ddit.ibatis.factory.SqlMapClientFactory;
import kr.or.ddit.vo.FileItemVO;

public class FileItemDaoImpl implements IFileItemDao {
	private static IFileItemDao dao = new FileItemDaoImpl();
	private SqlMapClient smc;
	
	private FileItemDaoImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	
	}
	public static IFileItemDao getInstance(){
		return (dao == null) ? dao = new FileItemDaoImpl() : dao;
	}
	
	
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
