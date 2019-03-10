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
         
            <div class="error-content">
              <h3><i class="fa fa-warning text-yellow"></i>抱歉,您无权限访问此页面</h3>
             
            </div><!-- /.error-content -->
          </div><!-- /.error-page -->
        </section><!-- /.content -->
  </body>
</html>

