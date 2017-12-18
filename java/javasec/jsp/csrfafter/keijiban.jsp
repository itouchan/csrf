<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="csrfafter.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  request.setCharacterEncoding("UTF-8");
  String msg = (String)request.getAttribute("msg");
  ArrayList list = (ArrayList)request.getAttribute("list");
%>
<!DOCTYPE html>
<head>
  <meta charset="UTF-8" />
  <title>セキュリティ対策~クロスサイトリクエストフォージェリ~</title>
</head>
<body bgcolor="white">
  <h1>セキュリティ対策~クロスサイトリクエストフォージェリ~</h1>
  <h3 style="color:red;">対策前</h3>
  <h3>ーーーーーーーー掲示板ーーーーーーーー</h3>
  <form action="/javasec/csrfafter/Keijiban" method="POST" >
    発言者:
    <input type="text" name="speaker"><br/>
    コメント:<br/>
    <textarea name="msg" rows="10" cols="60"></textarea><br/>
    <input type="submit" value="送信">
  </form>
  <hr>
<%
  for(int i = 0; i < list.size(); i++){
    Comments cmt = (Comments)list.get(i);
%>
  NO:<c:out value="<%=cmt.getId() %>" /><br/>
  発言者:<c:out value="<%=cmt.getSpeaker() %>" /><br/>
  メッセージ:<%=cmt.getMsg() %><br/>
  <!--
  メッセージ:<c:out value="<%=cmt.getMsg() %>" /><br/>
  -->
  <hr>
<%
  }
%>
</body>
</html>
