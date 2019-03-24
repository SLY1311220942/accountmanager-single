<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>主页</title>
		<!-- Tell the browser to be responsive to screen width -->
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

		<%@include file="/WEB-INF/pages/include/inheader.jsp" %>
		<!-- Multi Tabs -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap-multitabs/css/style.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap-multitabs/demo/plugins/adminLTE/css/skins/skin-purple.min.css" />
		<style type="text/css">
			body,
			body.full-height-layout .wrapper,
			html {
				height: 100%;
			}
			
			body.full-height-layout .content-wrapper {
				height: calc(100% - 140px);
			}
		</style>
	</head>

	<body class="hold-transition skin-purple sidebar-mini">
		<div class="wrapper">
			<!-- 顶部导航栏 -->
			<header class="main-header">
				<!-- Logo -->
				<a href="#" class="logo">
					<!-- mini logo for sidebar mini 50x50 pixels -->
					<span class="logo-mini"><b>A</b>M</span>
					<!-- logo for regular state and mobile devices -->
					<span class="logo-lg"><b>Account</b> Manage</span>
				</a>
				<!-- Header Navbar: style can be found in header.less -->
				<nav class="navbar navbar-static-top">
					<!-- Sidebar toggle button-->
					<a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
						<span class="sr-only">Toggle navigation</span>
					</a>

					<div class="navbar-custom-menu">
						<ul class="nav navbar-nav">
							<!-- Messages: style can be found in dropdown.less-->
							<li class="dropdown messages-menu">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">
									<i class="fa fa-envelope-o"></i>
									<span class="label label-success">99</span>
								</a>
								<ul class="dropdown-menu">

								</ul>
							</li>
							<!-- Notifications: style can be found in dropdown.less -->
							<li class="dropdown notifications-menu">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">
									<i class="fa fa-bell-o"></i>
									<span class="label label-warning">99</span>
								</a>
								<ul class="dropdown-menu">

								</ul>
							</li>

							<li class="dropdown notifications-menu">
								<a href="#" class="dropdown-toggle" onclick="logout();" data-toggle="dropdown" title="登出">
									<i class="fa fa-sign-out"></i>
									<span class="label label-warning"></span>
								</a>
								<ul class="dropdown-menu">

								</ul>
							</li>
						</ul>
					</div>

				</nav>

			</header>

			<!-- 左侧菜单栏 -->
			<aside class="main-sidebar">
				<section class="sidebar">
					<div class="user-panel">
						<div class="pull-left image">
							<img src="${pageContext.request.contextPath }/resource/AdminLTE/dist/img/avatar5.png" class="img-circle" alt="User Image">
						</div>
						<div class="pull-left info">
							<p>${SESSION_USER.username}</p>
							<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
						</div>
					</div>

					<ul class="sidebar-menu" data-widget="tree" id="menu">

					</ul>
				</section>
			</aside>

			<!-- 中间内容 -->
			<div class="content-wrapper" id="content_wrapper">

			</div>

			<!-- 底部标注 -->
			<!-- Main Footer -->
			<footer class="main-footer">
				<!-- To the right -->
				<div class="pull-right hidden-xs">
					Anything you want
				</div>
				<!-- Default to the left -->
				<strong>Copyright &copy; 2016 <a href="#">Company</a>.</strong> All rights reserved.
			</footer>
		</div>
	</body>

	<%@include file="/WEB-INF/pages/include/infooter.jsp" %>
	<!-- Multi Tabs -->
	<script src="${pageContext.request.contextPath }/resource/bootstrap-multitabs/js/multitabs.js"></script>
	<!-- AdminLTE App -->
	<script src="${pageContext.request.contextPath }/resource/bootstrap-multitabs/demo/plugins/adminLTE/js/app.js?v=2"></script>
	<!--<script src="${pageContext.request.contextPath }/resource/AdminLTE/dist/js/adminlte.js"></script>-->
	<script src="${pageContext.request.contextPath }/resource/account/js/system/home/home.js"></script>
	<script>
		$(function() {
			var multitabs = $('#content_wrapper').multitabs();

			$(".mt-dropdown").click(function() {
				$(".mt-dropdown").addClass("open");
			});
		});

		function logout() {
			layer.confirm('确定登出?', {
				btn: ['確定', '取消'] //按钮
			}, function() {
				$.ajax({
					type: "post",
					url: webRoot + "/system/login/logout",
					dataType: "json",
					data: {

					},
					success: function(data) {
						if(data.status == 200) {
							top.location = webRoot + '/system/login/toLogin';
						} else if(data.status == 300) {
							eval(data.evalCode);
						} else {
							layer.alert(data.message, { icon: 2 });
						}
					}
				});
			}, function() {

			});

		}

		loadMean();
	</script>

</html>