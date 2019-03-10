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
								分配任务
								</h4>

								<div class="col-sm-12 column ui-sortable" style="margin-top: 20px;">
									<input type="hidden" id="userId" name="userId" value="${userId}" />
									<input type="hidden" id="token" name="token" value="${SYSTEM_USERROLE_DISTRIBUTE_TOKEN}" />
									<div class="ibox-content" style="display: block;">
										<ul id="userRoleTree" class="ztree" style="margin-left:46%">
											<!-- 菜单树 -->
										</ul>
									</div>
								</div>

								<div style="margin-left: 46%;margin-bottom: 10px;">
									<button type="button" onclick="saveUserRoleTree(this)" class="btn btn-info ladda-button" data-style="zoom-in" data-size="s" style="margin-bottom: 10px;margin-top: 10px;">
										<span class="ladda-label">
											提&nbsp;&nbsp;&nbsp;交
										</span>
									</button>
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
	<script src="${pageContext.request.contextPath }/resource/account/js/system/userRole/userRole.js"></script>
	<script>
		loadUserRoleTree();
	</script>
</html>