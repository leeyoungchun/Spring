package kr.or.ddit.utiles;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;






import kr.or.ddit.global.GlobalConstant;

// 파일 다운로드 처리 jsp 내
// FileDownloadView.fileDownload(pageContext,"a.png");
public class FileDownloadView {
	public static void fileDownload(PageContext pageContext,
										String downloadFileName,
										String realName) throws IOException{
		HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
		JspWriter out = pageContext.getOut(); 
		
		File downloadFile = new File(GlobalConstant.FILE_PATH,downloadFileName);
		
		if(downloadFile.exists()){
			realName = URLEncoder.encode(realName,"UTF-8");
			response.setHeader("Content-Disposition", "attachment;fileName=" + realName);
			response.setContentType("application/octet-stream");
			response.setContentLength((int)(downloadFile.length()));
			
			
			byte[] buffer = new byte[(int)(downloadFile.length())];
			BufferedInputStream inputStream = new BufferedInputStream(
														new FileInputStream(
																downloadFile));
			
			BufferedOutputStream outputStream = new BufferedOutputStream(
														response.getOutputStream());
			
			int readCnt = 0;
			while((readCnt = inputStream.read(buffer)) != -1){
				outputStream.write(buffer);
			}
			
			inputStream.close();
			outputStream.close();
		}
	}
}
