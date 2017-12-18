<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="csrfafter.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  request.setCharacterEncoding("UTF-8");
  String msg = (String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<head>
  <meta charset="UTF-8" />
  <title>セキュリティ対策~クロスサイトリクエストフォージェリ~</title>
</head>
<body bgcolor="white">
  <h1>セキュリティ対策~クロスサイトリクエストフォージェリ~</h1>
  <h3 style="color:red;">対策前</h3>
  <h3>ーーーーーーーーパスワード変更完了画面ーーーーーーーー</h3>
  <a href="/javasec/csrfafter/Logout">ログアウト</a><br/>

  <%=msg %><br/>
  <a href="/javasec/csrfafter/Mypage">マイページ</a><br/>
</body>
</html>
