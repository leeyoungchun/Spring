<%@ page language="JAVA" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시글 정보</title>
<!-- 이미지 슬라이드 사이즈 조정 -->
<style type="text/css">
.carousel{
	width:200px;
    height:150px;
    margin-left: 200px;
}
.carousel-inner > .item > img{
    width:200px;
    height:150px;
}       
</style>
<script>
$(function(){
	// 섬머노트를 div를 활용한 textarea에 추가.
	// http://summernote.org 활용
    $('#bo_content').summernote({
			height: 150,
			codemirror: {
			theme: 'monokai'
		}
    });
    $('#bo_content').summernote('code','${freeboardInfo.bo_content}');
    $('#listBTN').click(function(){
    	location.replace('${pageContext.request.contextPath}/user/freeboard/freeboardList.do');
    });
    
    $('#deleteBTN').click(function(){
    	location.replace('${pageContext.request.contextPath}/user/freeboard/deleteFreeboard.do?bo_no=${freeboardInfo.bo_no}');
    });
    
    $('#replyBTN').click(function(){
    	location.replace('${pageContext.request.contextPath}/user/freeboard/freeboardReplyForm.do?bo_no=${freeboardInfo.bo_no}');
    });
    
    $('form[name=form]').submit(function(){
    	var bo_content = $('#bo_content').summernote('code');
		$(this).append('<input type="hidden" name ="bo_content" value="'+bo_content+'"/>');
    	$(this).append('<input type=hidden name="bo_no" value="${freeboardInfo.bo_no}"/>');
		$(this).attr('action','${pageContext.request.contextPath}/user/freeboard/updateFreeboard.do');
    });
    
});
</script>
</head>
<body>
<form class="form-horizontal" role="form" action="" method="post" name="form">
	<div class="form-group">
		<label class="control-label col-sm-2" for="bo_title">제목:</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="bo_title" name="bo_title" value="${freeboardInfo.bo_title }">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="bo_nickname">작성자 대화명:</label>
		<div class="col-sm-10"> 
			<input type="text" class="form-control" id="bo_nickname" name="bo_nickname" value="${freeboardInfo.bo_nickname }">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="bo_pwd">패스워드:</label>
		<div class="col-sm-10"> 
			<input type="password" class="form-control" id="bo_pwd" name="bo_pwd" value="${freeboardInfo.bo_pwd }">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="bo_mail">메일:</label>
		<div class="col-sm-10"> 
			<input type="password" class="form-control" id="bo_mail" name="bo_mail" value="${freeboardInfo.bo_mail }">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="bo_content">내용:</label>
		<div class="col-sm-10"> 
			<div id="bo_content"><p></p></div>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="bo_content">첨부파일:</label>
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
				<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
			</ol>
			<!-- Wrapper for slides -->

			<div class="carousel-inner" role="listbox" style="height: 150px; width : 150px;">
				<c:forEach items="${freeboardInfo.items }" var="fileitemInfo" varStatus="stat">
					<c:if test="${stat.first }">
						<div class="item active">
					</c:if>
					<c:if test="${stat.last}">
						<div class="item">
					</c:if>
						<img src="/files/${fileitemInfo.file_save_name }" alt="pic1"
						onclick="javascript:location.href='${pageContext.request.contextPath }/user/freeboard/freeFileDownload.do?file_seq=${fileitemInfo.file_seq }';" />
					</div>
				</c:forEach>
			<!-- Left and right controls -->
			<a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
			<a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
		</div>
	</div>
	<div class="form-group"> 
		<div class="col-sm-offset-2 col-sm-10">
			<button type="button" id="deleteBTN" class="btn btn-danger">삭제</button>
			<button type="button" id="replyBTN" class="btn btn-primary">답글</button>
			<button type="button" id="listBTN" class="btn btn-info">목록</button>
			<button type="submit" class="btn btn-default" style="float: right">수정</button>
		</div>
	</div>
</form>
</body>
</html>