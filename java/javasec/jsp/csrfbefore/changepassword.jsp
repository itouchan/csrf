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
  <h3>ーーーーーーーーパスワード変更画面ーーーーーーーー</h3>
  <a href="/javasec/csrfbefore/Logout">ログアウト</a><br/>
  <%=msg %>
  <form action="/javasec/csrfbefore/ChangePasswordExe" method="POST">
    Newパスワード：<input type="password" name="newpass" /><br/>
    Newパスワード再入力：<input type="password" name="repass" /><br/>
    <input type="submit" value="変更" />
  </form>
</body>
</html>
