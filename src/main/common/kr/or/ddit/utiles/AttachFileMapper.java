package kr.or.ddit.utiles;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import kr.or.ddit.global.GlobalConstant;
import kr.or.ddit.vo.FileItemVO;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;

public class AttachFileMapper {
	public static List<FileItemVO> mapper(FileItem[] items,
											String bo_no){
		List<FileItemVO> fileItemList = new ArrayList<>();
		
		if(items != null){
			FileItemVO fileItemInfo = null;
			for(FileItem item : items){
				fileItemInfo = new FileItemVO();
				fileItemInfo.setFile_bo_no(bo_no);
				// 파일명 취득
				// 브라우저별   d:\\temp\image\a.png
				//		  or a. png
				String fileName = FilenameUtils.getName(item.getName());
				fileItemInfo.setFile_name(fileName);
				
				// 파일 실제 저장소 : d:\\temp\\files
				// 저장용 파일명을 별도로 작성
				// a.png => a
				String baseName = FilenameUtils.getBaseName(fileName);
				// a.png => png
				String extension = FilenameUtils.getExtension(fileName);
				//	|--------------------| -> 유니크한 랜덤값
				// a124314124125125215125.png
				//UUID(Univerally Unique Identifier) : 128bit 유일값 생성(-포함)
				String genID = UUID.randomUUID().toString().replace("-", "");
				String saveFileName = baseName+genID+"."+extension;
				fileItemInfo.setFile_save_name(saveFileName);
				fileItemInfo.setFile_content_type(item.getContentType());
				fileItemInfo.setFile_size(String.valueOf(item.getSize()));
				
				fileItemList.add(fileItemInfo);
				
				saveFile(saveFileName, item);
			}
		}
		
		return fileItemList;
	}

	private static void saveFile(String saveFileName, FileItem item) {
		File saveFile = new File(GlobalConstant.FILE_PATH,saveFileName);
		try {
			item.write(saveFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
