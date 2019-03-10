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
            <h2 class="headline text-red">500</h2>
            <div class="error-content">
              <h3><i class="fa fa-warning text-red"></i>糟糕! 服务器出错啦~~(>_<)~~</h3>
              <p>
                                            我们将致力于解决此问题。同时，您可以
             <a href="javascript:top.location='${pageContext.request.contextPath }/index/home'">返回首页</a>或者尝试使用表单搜索更多信息。
              </p>
              <form class="search-form">
                <div class="input-group">
                  <input type="text" name="search" class="form-control" placeholder="Search">
                  <div class="input-group-btn">
                    <button type="submit" name="submit" class="btn btn-danger btn-flat"><i class="fa fa-search"></i></button>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </section>
   </body>
</html>





