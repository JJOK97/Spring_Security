<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>MVC 게시판</title>
<jsp:include page="header.jsp" />
<script src="../resources/js/reply.js"></script>
<style>
h1 {
   font-size: 1.5rem;
   text-align: center;
   color: #1a92b9;
}

.container {
   width: 60%;
}

label {
   font-weight: bold;
}
</style>
</head>
<body>
   <div class="container">
      <form action="replyAction" method="post" name="replyform">
         <input type="hidden" name="BOARD_RE_REF" value="${boarddata.BOARD_RE_REF}">
         <input type="hidden" name="BOARD_RE_LEV" value="${boarddata.BOARD_RE_LEV}">
         <input type="hidden" name="BOARD_RE_SEQ" value="${boarddata.BOARD_RE_SEQ}">
         <h1>MVC게시판 - Reply</h1>
         <div class="form-group">
            <label for="board_name">글쓴이</label> 
            <input name="BOARD_NAME" id="board_name" value="${id}" readOnly
            type="text" class="form-control">
         </div>
         <div class="form-group">
            <label for="board_subject">제목</label> 
            <textarea name="BOARD_SUBJECT" id="board_subject" rows="1"
             maxlength="100" class="form-control">Re:${boarddata.BOARD_SUBJECT}</textarea>
         </div>
         <div class="form-group">
            <label for="board_content">내용</label> 
            <textarea name="BOARD_CONTENT" id="board_content" 
                  rows="10" class="form-control" ></textarea>
         </div>
         <div class="form-group">
            <label for="board_pass">비밀번호</label> 
            <input name="BOARD_PASS" id="board_pass" type="password" class="form-control" >
         </div>
         <div class="form-group">
         <input type= submit class="btn btn-primary" value="등록">
         <input type= button class="btn btn-danger" value="취소" onClick="history.go(-1)">
         </div>
      </form>
   </div>
</body>
</html>