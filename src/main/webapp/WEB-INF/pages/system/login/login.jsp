<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<%@include file="/WEB-INF/pages/include/inheader.jsp" %>
	</head>

	<body>
		<div style="height: 200px;">

		</div>
		<div>
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<div class="box box-info" style="border: solid;">
					<div class="box-header with-border">
						<h3 class="box-title">登录</h3>
					</div>

					<form class="form-horizontal">
						<input type="hidden" id="publicKey" name="publicKey" value="${publicKey}" />
						<div class="box-body" id="login">
							<div class="form-group">
								<label for="username" class="col-sm-2 control-label">Username</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="username" placeholder="Username">
								</div>
							</div>

							<div class="form-group">
								<label for="password" class="col-sm-2 control-label">Password</label>
								<div class="col-sm-10">
									<input type="password" class="form-control" id="password" placeholder="Password">
								</div>
							</div>

							<div class="form-group">
								<label for="validate" class="col-sm-2 control-label">Validate</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="validate" placeholder="Validate">
								</div>
								<img id="validatePic" src="${pageContext.request.contextPath}/system/validate/getJPGCode?type=l" onclick="loadValidate()" />
							</div>

							<div>
								<span style="padding-left: 100px;color: red;"></span>
							</div>
						</div>

						<div class="box-footer">
							<button type="reset" class="btn btn-default">重置</button>
							<button type="button" class="btn btn-info pull-right" onclick="login();">登录</button>
						</div>

					</form>
				</div>
			</div>
			<div class="col-md-4"></div>
		</div>
		<div>

		</div>
	</body>
	<%@include file="/WEB-INF/pages/include/infooter.jsp" %>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/rsa/jsencrypt.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/account/js/system/login/login.js"></script>
	<script type="text/javascript">
		$(function() {
			
		});
	</script>

</html>