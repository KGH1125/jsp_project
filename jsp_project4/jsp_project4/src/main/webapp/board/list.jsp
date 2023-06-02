<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list</title>
<style type="text/css">
body {
	min-width: 1050px;
	position: relative;
}

.mainbox {
	left: 50%;
	transform: translate(-50%, 0%);
	position: absolute;
}

.resetBtn {
	display: inline-block;
}

.pg {
	left: 50%;
	transform: translate(-50%, 0%);
	position: absolute;
}

h3 {
	text-align: center;
}

table {
	border-collapse: collapse;
	margin-top: 10px;
	margin-bottom: 5px;
}

table td {
	height: 15px;
	overflow: hidden;
	border: 1px solid black;
	border-left: none;
	border-right: none;
	padding-top: 3px;
	padding-bottom: 3px;
}

table td:nth-child(1) {
	width: 80px;
	text-align: center;
}

table td:nth-child(2) {
	width: 80px;
	text-align: center;
}

table td:nth-child(3) {
	width: 320px;
}

table td:nth-child(4) {
	width: 70px;
	text-align: center;
}

table td:nth-child(5) {
	width: 80px;
	text-align: center;
}
</style>
</head>
<body>
	<h3>list</h3>
	<div class="mainbox">

		<form action="/brd/page" method="post" class="resetBtn">
			<c:set value="${pgh.pgvo.type}" var="typed"></c:set>
			<select name="type">
				<option ${typed==null?'selected':'' }>선택</option>
				<option value="t" ${typed eq't'?'selected':'' }>제목</option>
				<option value="c" ${typed eq'c'?'selected':'' }>내용</option>
				<option value="w" ${typed eq'w'?'selected':'' }>작성자</option>
				<option value="tc" ${typed eq'tc'?'selected':'' }>제목+내용</option>
				<option value="tw" ${typed eq'tw'?'selected':'' }>제목+작성자</option>
				<option value="cw" ${typed eq'cw'?'selected':'' }>내용+작성자</option>
			</select> <input type="text" name="keyword" placeholder="Search"> <input
				type="hidden" name="pageNo" value="${pgh.pgvo.pageNo }"> <input
				type="hidden" name="qty" value="${pgh.pgvo.qty }">
			<button type="submit">검색</button>
		</form>

		<a href="/brd/page?type=w&keyword=${ses.id }"><button>내글</button></a>
		<a href="/brd/page"><button>새로고침</button></a> <a
			href="/member/detail.jsp"><button>내정보</button></a> <a
			href="/board/register.jsp"><button>글쓰기</button></a>
		
		<c:if test="${ses.grade eq 'master' }">
			<a href="/mem/list"><button>회원목록</button></a>
		</c:if>
		
		<c:if test="${ses.id ne null }">
			<a href="/mem/logout"><button>로그아웃</button></a>
		</c:if>

		<table>
			<tr>
				<th>글번호</th>
				<th>썸네일</th>
				<th>제목</th>
				<th>작성자</th>
				<th>조회수</th>
			</tr>
			<c:forEach items="${list }" var="tmp">
				<tr>
					<td>${tmp.bno }</td>
					<td><c:if test="${tmp.img_file ne null }">
							<img alt="없" src="/_fileUpload/th_${tmp.img_file }">
						</c:if></td>
					<td><a href="/brd/detail?bno=${tmp.bno }">${tmp.title }</a></td>
					<td>${tmp.writer }</td>
					<td>${tmp.read_count }</td>
				</tr>
			</c:forEach>
		</table>
		<div class="pg">
			<c:if test="${pgh.prev }">
				<a
					href="/brd/page?pageNo=${pgh.startPage-1 }&qty=${pgh.pgvo.qty}&type=${pgh.pgvo.type}&keyword=${pgh.pgvo.keyword}">이전</a>
			</c:if>
			<c:forEach begin="${pgh.startPage }" end="${pgh.endPage }" var="i">
				<a
					href="/brd/page?pageNo=${i }&qty=${pgh.pgvo.qty}&type=${pgh.pgvo.type}&keyword=${pgh.pgvo.keyword}">${i }</a>
			</c:forEach>
			<c:if test="${pgh.next }">
				<a
					href="/brd/page?pageNo=${pgh.endPage+1 }&qty=${pgh.pgvo.qty}&type=${pgh.pgvo.type}&keyword=${pgh.pgvo.keyword}">다음</a>
			</c:if>
		</div>
	</div>
</body>
</html>