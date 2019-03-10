<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <%@include file="/WEB-INF/pages/include/inheader.jsp" %>
  </head>
  <body >
        <!-- Main content -->
        <section class="content">
          <div class="error-page">
            <h2 class="headline text-yellow"> 404</h2>
            <div class="error-content">
              <h3><i class="fa fa-warning text-yellow"></i>抱歉，页面未找到</h3>
              <p>
             		抱歉， 您访问的页面好像被叼走了... <br/>
             		请检查您的访问页面是否有误。
              </p>
              <p style="text-align: center"> <a type="button" href="javascript:top.location='${pageContext.request.contextPath}/index/home'"
               class="btn btn-warning btn-sm">返回首页</a></p>
            </div><!-- /.error-content -->
          </div><!-- /.error-page -->
        </section><!-- /.content -->
  </body>
</html>

