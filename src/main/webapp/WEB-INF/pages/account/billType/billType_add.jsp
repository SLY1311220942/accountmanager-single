<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
    <head>
        <base href="<%=basePath%>">

		<title>新增账单类型</title>

		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<%@include file="/WEB-INF/pages/include/inheader.jsp" %>
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
								新增账单类型
								</h4>
								<div class="col-md-12 column ui-sortable" style="margin-top: 20px;">
									<form class="form-horizontal required-validate" id="billTypeAddForm">
										<input type="hidden" id="token" name="token" value="${ACCOUNT_BILLTYPE_ADD_TOKEN}" />
										<div class="view">

											<div class="row">
												<div class="form-group col-md-6  column ui-sortable">
													<label class="col-sm-4 control-label" style="text-align: right; float: left;">账单类型名称：</label>
													<div class="col-sm-8">
														<input type="text" class="form-control " id="billTypeName" name="billTypeName">
													</div>
												</div>
											</div>
											
										</div>
									</form>

								</div>

								<div style="margin-left: 48%;margin-bottom: 10px;">
									<button type="button" onclick="billTypeAdd()" class="btn btn-info" style="margin-bottom: 10px;margin-top: 10px;">提&nbsp;&nbsp;&nbsp;交</button>
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
	<script src="${pageContext.request.contextPath }/resource/bootstrap-validator/local/zh_CN.js"></script>
	<script src="${pageContext.request.contextPath }/resource/account/js/account/billType/billTypeAdd.js"></script>
	<script>
		$(function() {
			validator();
		});
	</script>
</html>