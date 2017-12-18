<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="csrfbefore.*" %>
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
  <h3>ーーーーーーーーログイン画面ーーーーーーーー</h3>
<%
  if (msg != null && msg.length() > 0) {
%>
  <p style="color:red;"><%=msg %></p>
<%
  }
%>
  <form action="/javasec/csrfbefore/Login" method="POST" >
    ユーザーID:
    <input type="text" name="loginid"><br/>
    パスワード:
    <input type="password" name="loginpass"><br/>
    <input type="submit" value="送信">
  </form>
</body>
</html>
