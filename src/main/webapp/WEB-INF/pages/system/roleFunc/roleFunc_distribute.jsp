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
									<input type="hidden" id="roleId" name="roleId" value="${roleId}" />
									<input type="hidden" id="token" name="token" value="${SYSTEM_ROLEFUNC_DISTRIBUTE_TOKEN}" />
									<div class="ibox-content" style="display: block;">
										<ul id="roleFuncTree" class="ztree" style="margin-left:46%">
											<!-- 菜单树 -->
										</ul>
									</div>
								</div>

								<div style="margin-left: 46%;margin-bottom: 10px;">
									<button onclick="saveRoleFuncTree()" type="button" class="btn btn-success ladda-button" data-style="zoom-in" data-size="s"><span class="ladda-label">保&nbsp;&nbsp;&nbsp;存</span></button>
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
	<script src="${pageContext.request.contextPath }/resource/account/js/system/rolefunc/roleFunc.js"></script>
	<script>
		loadRoleFuncTree();
	</script>

</html>