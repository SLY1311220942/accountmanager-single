<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-ch">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <%@include file="/WEB-INF/pages/include/inheader.jsp" %>
  </head>
 <script src="${pageContext.request.contextPath }/resources/adminlte-2.3.0/plugins/jQuery/jQuery-2.1.4.min.js"></script>
  <script type="text/javascript">
    $(document).ready(function(){
		timer();
	});
	var sec = 3;
	function timer(){
		  if(sec == 0){
			  //去登录页面
			 top.location= "${pageContext.request.contextPath}/system/login/toLogin";
		} else {
			$('#sec').html(sec);
			setTimeout("timer()",1000);
			sec--;
		}  
	}
  </script>
  <body >
        <!-- Main content -->
        <section class="content">
          <div class="error-page">
           <!--  <h2 class="headline text-yellow"> 404</h2> -->
            <div class="error-content">
              <h3><i class="fa fa-warning text-yellow"></i>登录会话超时</h3>
              <p>抱歉，您的登录会话已超时,请重新登录!</p>
              <span id="sec">3</span> 秒后自动返回登录页
              <p style="text-align: center"> <a type="buttn" href="${pageContext.request.contextPath}/user_toLogin"
               class="btn btn-warning btn-sm">立即返回</a></p>
            </div>
          </div>
        </section>
  </body>
  
</html>
