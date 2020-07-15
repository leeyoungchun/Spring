package kr.or.ddit.utiles;

import javax.servlet.http.HttpServletRequest;

public class RolePaginationUtil {
	private int currentPage;			// 현재 페이지
	private int totalCount;				// 전체 게시글 갯수
	private int totalPage;				// 전체 페이지 갯수
	private int blockCount = 10;		// 페이지별 출력될 게시글 갯수
	private int blockPage = 5;			// 페이지네이션 메뉴 갯수
	private int startPage;				// 페이지네이션 메뉴 시작 페이지 번호
	private int endPage;				// 페이지네이션 메뉴 끝 페이지 번호
	private int startCount;				// 해당 페이지 내 게시글 시작 번호
	private int endCount;				// 해당 페이지 내 게시글 끝 번호
	private HttpServletRequest request;
	private StringBuffer pagingHtmls;
	
	public RolePaginationUtil(HttpServletRequest request,
							  int currentPage,
							  int totalCount){
		this.request = request;
		this.currentPage = currentPage;
		this.totalCount	= totalCount;
		
		pagingHtmls = new StringBuffer();
		
		makePagination();
	}

	private void makePagination() {
		// 전체 페이지 갯수
		this.totalPage = (int)Math.ceil(this.totalCount / (double)this.blockCount);
		if(this.totalPage == 0){
			this.totalPage = 1;
		}
		
		// 해당 페이지 내 게시글 시작 번호, 끝번호
		this.startCount = this.totalCount - (this.currentPage - 1) * this.blockCount;
		this.endCount = this.startCount - this.blockCount + 1;
		if(endCount < 0){
			endCount = 1;
		}
		
		// 페이지별 페이지네이션 메뉴 시작 페이지 번호, 끝 페이지 번호
		// 이전1|2|3|4|5|다음
		// 이전6|7|8|9|10|다음
		this.startPage = ((this.currentPage - 1) / this.blockPage * this.blockPage) + 1;
		this.endPage = this.startPage + this.blockPage - 1;
		if(this.endPage > this.totalPage){
			this.endPage = this.totalPage;
		}
		
		this.pagingHtmls.append("<div class='text-center'>");
		this.pagingHtmls.append("<ul class='pagination mtm mbm'>");
		String requestURI = request.getRequestURI();
		
		// 이전1|2|3|4|5|다음 
		if((this.currentPage - 1) == 0){
			this.pagingHtmls.append("<li class='disabled'><a href='#'>&laquo;</a></li>");
		}else{
			this.pagingHtmls.append("<li><a href='" + requestURI + "?currentPage=" + (this.currentPage - 1) + "'>&laquo;</a></li>");
		}
		//1|2|3|4|5|
		for(int i=this.startPage; i<=this.endPage; i++){
			if(this.currentPage == i){
				this.pagingHtmls.append("<li class='active'><a href='#'>"+ i +"</a></li>");
			}else{
				this.pagingHtmls.append("<li><a href='" + requestURI + "?currentPage=" + i + "'>" + i + "</a></li>");
			}
		}
		// 다음
		if(this.currentPage < this.totalPage){
			this.pagingHtmls.append("<li><a href='" + requestURI + "?currentPage=" + (this.currentPage + 1) + "'>&raquo;</a></li>");
		}else{
			this.pagingHtmls.append("<li class='disabled'><a href='#'>&raquo;</a></li>");
		}
		
		this.pagingHtmls.append("</ul>");	
		this.pagingHtmls.append("</div>");	
	}

	public int getStartCount() {
		return startCount;
	}

	public int getEndCount() {
		return endCount;
	}

	public String getPagingHtmls() {
		return pagingHtmls.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
