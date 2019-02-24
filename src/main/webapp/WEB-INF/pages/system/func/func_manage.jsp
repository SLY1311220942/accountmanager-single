<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html>
<html>

	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>主页</title>
		<!-- Tell the browser to be responsive to screen width -->
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

		<%@include file="/WEB-INF/pages/include/inheader.jsp"%>
		<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/zTree-bootstrap/css/zTreeStyle/metro.css" type="text/css">
		<style type="text/css">
			body,
			body.full-height-layout .wrapper,
			html {
				height: 100%;
			}
			
			body.full-height-layout .content-wrapper {
				height: calc(100% - 140px);
			}
			
			.wrap1 {
				position: relative;
				margin: 10px auto;
				background: #fff;
				padding: 10px;
				float: left;
				margin-left: 25px;
				margin-top: 10px;
				margin-bottom: 10px;
				width: 28%;
				background-color: #fff;
			}
			
			.wrap2 {
				position: relative;
				margin: 10px auto;
				background: #fff;
				padding: 10px;
				float: left;
				margin-left: 25px;
				margin-top: 10px;
				margin-bottom: 10px;
				width: 66%;
				background-color: #fff;
			}
		</style>
	</head>

	<body>
		<section class="content">
			<div class="row">
				<div class="col-xs-12 ui-sortable">
					<div class="box">
						<input type="hidden" id="token" name="token" value="${SYSTEM_FUNC_DELETE_TOKEN}" />
						<div class="row" style="margin-top: 10px; margin-bottom: 8px;">
							<!-- 树 -->
							<div class="wrap1">
								<h4 class="title">功能列表</h4>
								<div class="col-sm-12 column ui-sortable">
									<button class="btn btn-success btn-xs" onclick="func_toAdd()" style="margin-top: 5px;margin-left: 9px;">添加一级菜单</button>
								</div>

								<div class="col-sm-12 column ui-sortable" style="margin-top:5px;">
									<div id="menuContent" class="menuContent" style="overflow:auto;">
										<ul id="funcTree" class="ztree">

										</ul>
									</div>
								</div>
							</div>
							<!--详情-->
							<div class="wrap2">
								<h4 class="title" style="text-align: center;">功能详情</h4>
								<div class="col-md-12 column ui-sortable" style="margin-top: 20px;">

									<form class="form-horizontal required-validate">
										<div class="form-group">
											<label class=" col-sm-3 control-label">功能id:</label>
											<div class="col-sm-9">
												<div style="margin-top:7px;" id="funcId"> </div>
											</div>
										</div>
										<div class="form-group">
											<label class=" col-sm-3 control-label">功能名称:</label>
											<div class="col-sm-9">
												<div style="margin-top:7px;" id="funcName"> </div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label">功能标识:</label>
											<div class="col-sm-9">
												<div style="margin-top:7px;" id="funcTag"></div>
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-3 control-label">功能url:</label>
											<div class="col-sm-9">
												<div style="margin-top:7px;" id="funcUrl"></div>
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-3 control-label">功能图标:</label>
											<div class="col-sm-9">
												<div style="margin-top:7px;" id="funcIcon"></div>
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-3 control-label">是否系统内置:</label>
											<div class="col-sm-9">
												<div style="margin-top:7px;" id="sysTag"></div>
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-3 control-label">是否启用:</label>
											<div class="col-sm-9">
												<div style="margin-top:7px;" id="isOpen"></div>
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-3 control-label">排序值:</label>
											<div class="col-sm-9">
												<div style="margin-top:7px;" id="funcSort"> </div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label">创建时间:</label>
											<div class="col-sm-9">
												<div style="margin-top:7px;" id="createtime"> </div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label">最后修改时间:</label>
											<div class="col-sm-9">
												<div style="margin-top:7px;" id="updatetime"> </div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label">备注:</label>
											<div class="col-sm-9">
												<div id="remark" style="margin-top:7px;width:350px; 
									    			height:auto; float:left;white-space:normal;
									     			word-wrap:break-word; word-break:break-all;">
											</div>
										</div>
								</div>
								</form>

							</div>
						</div>
					</div>
				</div>
			</div>
			</div>
		</section>
	</body>
	<%@include file="/WEB-INF/pages/include/infooter.jsp"%>
	<script src="${pageContext.request.contextPath }/resource/zTree-bootstrap/js/jquery.ztree.all-3.5.min.js"></script>
	<script src="${pageContext.request.contextPath }/resource/account/js/system/func/funcManage.js"></script>
	<script>
		$(function() {
			func_initZTree();
		});
	</script>

</html>