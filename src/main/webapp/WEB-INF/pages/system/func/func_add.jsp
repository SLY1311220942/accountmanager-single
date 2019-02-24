<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

	<head>
		<base href="<%=basePath%>">

		<title>新增功能</title>

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
									<form class="form-horizontal required-validate" id="funcAddForm">
										<input type="hidden" id="parentId" name="parentId" value="${parentId}" />
										<input type="hidden" id="token" name="token" value="${SYSTEM_FUNC_ADD_TOKEN}" />
										<div class="view">
											<div class="row">
												<div class="form-group col-md-12  column ui-sortable">
													<label class="col-sm-3 control-label" style="text-align: right; float: left;">功能名称：</label>
													<div class="col-sm-6">
														<input type="text" class="form-control " id="funcName" name="funcName">
													</div>
												</div>
											</div>
											<div class="row">
												<div class="form-group col-md-12  column ui-sortable">
													<label class="col-sm-3 control-label" style="text-align: right; float: left;">功能图标：</label>
													<div class="col-sm-6">
														<input type="text" class="form-control " id="funcIcon" name="funcIcon">
													</div>
												</div>
											</div>
											<div class="row">
												<div class="form-group col-md-12  column ui-sortable">
													<label class="col-sm-3 control-label" style="text-align: right; float: left;">功能Url：</label>
													<div class="col-sm-6">
														<input type="text" class="form-control " id="funcUrl" name="funcUrl">
													</div>
												</div>
											</div>
											<div class="row">
												<div class="form-group col-md-12  column ui-sortable">
													<label class="col-sm-3 control-label" style="text-align: right; float: left;">功能标签：</label>
													<div class="col-sm-6">
														<select class="form-control" id="funcTag" name="funcTag">
															<option value="0">菜单</option>
															<option value="1">按钮</option>
														</select>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="form-group col-md-12  column ui-sortable">
													<label class="col-sm-3 control-label" style="text-align: right; float: left;">功能排序：</label>
													<div class="col-sm-6">
														<input type="text" class="form-control " id="funcSort" name="funcSort">
													</div>
												</div>
											</div>
											<div class="row">
												<div class="form-group col-md-12  column ui-sortable">
													<label class="col-sm-3 control-label" style="text-align: right; float: left;">是否启用：</label>
													<div class="col-sm-6">
														<select class="form-control" id="isOpen" name="isOpen">
															<option value="1">启用</option>
															<option value="0">未启用</option>
														</select>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="form-group col-md-12  column ui-sortable">
													<label class="col-sm-3 control-label" style="text-align: right; float: left;">功能类型：</label>
													<div class="col-sm-6">
														<select class="form-control" id="funcType" name="funcType">
															<option value="1">普通功能</option>
															<option value="0">系统内置</option>
														</select>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="form-group col-md-12  column ui-sortable">
													<label class="col-sm-3 control-label" style="text-align: right; float: left;">备注：</label>
													<div class="col-sm-6">
														<textarea class="form-control" style="height: 100px;" id="remark" name="remark"></textarea>
													</div>
												</div>
											</div>
										</div>
									</form>

								</div>

								<div style="margin-left: 48%;margin-bottom: 10px;">
									<button type="button" onclick="funcAdd()" class="btn btn-info" style="margin-bottom: 10px;margin-top: 10px;">提&nbsp;&nbsp;&nbsp;交</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</body>
	<%@include file="/WEB-INF/pages/include/infooter.jsp" %>
	<script src="${pageContext.request.contextPath }/resource/account/js/system/func/funcAdd.js"></script>
	<script src="${pageContext.request.contextPath }/resource/bootstrap-validator/js/bootstrapValidator.min.js"></script>
	<script src="${pageContext.request.contextPath }/resource/bootstrap-validator/local/zh_CN.js"></script>
	<script>
		$(function(){
			validator();
		})
	</script>
</html>