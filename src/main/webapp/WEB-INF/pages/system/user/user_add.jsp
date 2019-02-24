<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

	<head>
		<base href="<%=basePath%>">

		<title>新增用户</title>

		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<%@include file="/WEB-INF/pages/include/inheader.jsp" %>
		<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/bootstrap-table/css/bootstrap-table.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/bootstrap-validator/css/bootstrapValidator.min.css" />
		<style>
			.wrap1 {
				position: relative;
				margin: 10px auto;
				width: 97%;
				border: 1px solid gray;
				border-radius: 20px;
				background: #fff;
			}
			
			.title {
				position: absolute;
				left: 10px;
				margin: 0;
				padding: 0 20px;
				line-height: 30px;
				border: 1px solid gray;
				border-radius: 15px;
				transform: translateY(-50%);
				background: #fff;
			}
		</style>
	</head>

	<body>
		<section class="content">
			<div class="row">
				<div class="col-xs-12 ui-sortable">
					<div class="box">
						<div class="row" style="margin-top: 10px; margin-bottom: 8px;">
							<!-- 查询版块 -->
							<div class="wrap1">
								<h4 class="title">
								新增功能
								</h4>
								<div class="col-md-12 column ui-sortable" style="margin-top: 20px;">
									<form class="form-horizontal required-validate" id="userAddForm">
										<input type="hidden" id="token" name="token" value="${SYSTEM_USER_ADD_TOKEN}" />
										<input type="hidden" id="publicKey" name="publicKey" value="${userAddPublicKey}" />
										<div class="view">
											<div class="row">
												<div class="form-group col-md-6  column ui-sortable">
													<label class="col-sm-4 control-label" style="text-align: right; float: left;">用户名称：</label>
													<div class="col-sm-8">
														<input type="text" class="form-control " id="username" name="username">
													</div>
												</div>
												<div class="form-group col-md-6  column ui-sortable">
													<label class="col-sm-4 control-label" style="text-align: right; float: left;">昵称：</label>
													<div class="col-sm-8">
														<input type="text" class="form-control " id="nickname" name="nickname">
													</div>
												</div>
											</div>

											<div class="row">
												<div class="form-group col-md-6  column ui-sortable">
													<label class="col-sm-4 control-label" style="text-align: right; float: left;">真实姓名：</label>
													<div class="col-sm-8">
														<input type="text" class="form-control " id="realname" name="realname">
													</div>
												</div>
											</div>

											<div class="row">
												<div class="form-group col-md-6  column ui-sortable">
													<label class="col-sm-4 control-label" style="text-align: right; float: left;">密码：</label>
													<div class="col-sm-8">
														<input type="password" class="form-control " id="password" name="password">
													</div>
												</div>
												<div class="form-group col-md-6  column ui-sortable">
													<label class="col-sm-4 control-label" style="text-align: right; float: left;">确认密码：</label>
													<div class="col-sm-8">
														<input type="password" class="form-control " id="repassword" name="repassword">
													</div>
												</div>
											</div>

											<div class="row">
												<div class="form-group col-md-6  column ui-sortable">
													<label class="col-sm-4 control-label" style="text-align: right; float: left;">手机号：</label>
													<div class="col-sm-8">
														<input type="text" class="form-control " id="phone" name="phone">
													</div>
												</div>
												<div class="form-group col-md-6  column ui-sortable">
													<label class="col-sm-4 control-label" style="text-align: right; float: left;">座机号：</label>
													<div class="col-sm-8">
														<input type="text" class="form-control " id="tel" name="tel">
													</div>
												</div>
											</div>

											<div class="row">
												<div class="form-group col-md-6  column ui-sortable">
													<label class="col-sm-4 control-label" style="text-align: right; float: left;">邮箱：</label>
													<div class="col-sm-8">
														<input type="text" class="form-control " id="email" name="email">
													</div>
												</div>
												<div class="form-group col-md-6  column ui-sortable">
													<label class="col-sm-4 control-label" style="text-align: right; float: left;">性别：</label>
													<div class="col-sm-8">
														<select class="form-control" id="sex" name="sex">
															<option value="M">男</option>	
															<option value="W">女</option>	
														</select>
													</div>
												</div>
											</div>

											<div class="row">
												<div class="form-group col-md-6  column ui-sortable">
													<label class="col-sm-4 control-label" style="text-align: right; float: left;">用户类型：</label>
													<div class="col-sm-8">
														<select class="form-control" id="userTag" name="userTag">
															<option value="2">普通用户</option>
															<option value="1">系统内置</option>
														</select>
													</div>
												</div>
												<div class="form-group col-md-6  column ui-sortable">
													<label class="col-sm-4 control-label" style="text-align: right; float: left;">用户状态：</label>
													<div class="col-sm-8">
														<select class="form-control" id="status" name="status">
															<option value="0">未激活</option>
															<option value="1" selected="selected">激活</option>
															<option value="2">封禁</option>
														</select>
													</div>
												</div>
											</div>

											<div class="row">
												<div class="form-group col-md-12  column ui-sortable">
													<label class="col-sm-2 control-label" style="text-align: right; float: left;right: 5px;">备注：</label>
													<div class="col-sm-6" style="right: 5px;">
														<textarea class="form-control" style="height: 100px;" id="remark" name="remark"></textarea>
													</div>
												</div>
											</div>
										</div>
									</form>

								</div>

								<div style="margin-left: 48%;margin-bottom: 10px;">
									<button type="button" onclick="userAdd()" class="btn btn-info" style="margin-bottom: 10px;margin-top: 10px;">提&nbsp;&nbsp;&nbsp;交</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</body>
	<%@include file="/WEB-INF/pages/include/infooter.jsp" %>
	<script src="${pageContext.request.contextPath }/resource/bootstrap-validator/js/bootstrapValidator.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/rsa/jsencrypt.min.js"></script>
	<script src="${pageContext.request.contextPath }/resource/bootstrap-validator/local/zh_CN.js"></script>
	<script src="${pageContext.request.contextPath }/resource/account/js/system/user/userAdd.js"></script>
	<script>
		$(function() {
			validator();
		});
	</script>

</html>