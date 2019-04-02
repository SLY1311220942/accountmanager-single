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
		<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/bootstrap-table/css/bootstrap-table.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/bootstrap-validator/css/bootstrapValidator.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath }/resource/datetimepicker/css/bootstrap-datetimepicker.min.css" />
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
			
			.wrap2 {
				position: relative;
				margin: 20px auto;
				width: 97%;
				height: 570px;
				border: 1px solid gray;
				border-radius: 20px;
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
								财务报表
								</h4>
								<div class="col-md-12 column ui-sortable" style="margin-top: 20px;">
									<form class="form-horizontal required-validate" id="billReportSearch">
										<div class="view">
											<div class="row">
												<div class="form-group col-md-6  column ui-sortable">
													<label for="billTypeName" class="col-sm-4 control-label" style="text-align: right; float: left;">统计方式：</label>
													<div class="col-sm-6">
														<!--<input type="text" class="form-control " id="statisticType" name="statisticType">-->
														<input type="hidden" id="statisticTypeTemp" name="statisticTypeTemp" value="0" />
														<select class="form-control" id="statisticType" name="statisticType">
															<option value="0">时间</option>	
															<option value="1">类型</option>	
														</select>
													</div>
												</div>
												<div class="form-group col-md-6  column ui-sortable">
													<label for="billTypeName" class="col-sm-4 control-label" style="text-align: right; float: left;">时间：</label>
													<div class="col-sm-6">
														<!--<input type="text" class="form-control " id="statisticType" name="statisticType">-->
														<select class="form-control" id="dataTimeType" name="dataTimeType">
															<option value="0">年</option>
															<option value="2" selected="selected">月</option>
															<option value="4">日</option>
														</select>
													</div>
												</div>
												
												
											</div>
											
											<div class="row">
												<div class="form-group col-md-6  column ui-sortable">
													<label for="billTypeName" class="col-sm-4 control-label" style="text-align: right; float: left;">类型：</label>
													<div class="col-sm-6">
														<!--<input type="text" class="form-control " id="statisticType" name="statisticType">-->
														<select class="form-control" id="billTypeId" name="billTypeId">
															<option value="">请选择</option>
														</select>
													</div>
												</div>
											
												<div class="form-group col-md-6  column ui-sortable">
													<label class="col-sm-4 control-label" style="text-align: right; float: left;">账单时间：</label>
													<div class="col-sm-6">
														<div style="float: left; width: 48%;">
															<input style="width: 100%;background: white;" id="beginTime" name="beginTime" type="text" readonly class="form-control">
														</div>
														<div style="float: left; width: 4%;padding-top: 5px;text-align: center;">~</div>
														<div style="float: left; width: 48%;">
															<input style="width: 100%;background: white;" id="endTime" name="endTime" type="text" readonly class="form-control">
														</div>
													</div>
												</div>
												
											</div>
										</div>
									</form>

								</div>

								<div style="margin-left: 46%;margin-bottom: 10px;">
									<button type="button" onclick="billReportSearch()" class="btn btn-info" style="margin-bottom: 10px;margin-top: 10px;">查&nbsp;&nbsp;&nbsp;询</button>
									<button type="button" onclick="billReportExport()" class="btn btn-info" style="margin-bottom: 10px;margin-top: 10px;">导&nbsp;&nbsp;&nbsp;出</button>
								</div>
							</div>
							<!-- 数据列表 -->
							<div class="wrap2">
								<h4 class="title">
								账单类型列表
								</h4>
								<div class="col-md-12 column ui-sortable" style="margin-top: 20px;">
									<table id="dataTable" class="table table-bordered">
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</body>
	<%@include file="/WEB-INF/pages/include/infooter.jsp"%>
	<script src="${pageContext.request.contextPath }/resource/bootstrap-table/js/bootstrap-table.min.js"></script>
	<script src="${pageContext.request.contextPath }/resource/bootstrap-table/local/bootstrap-table-zh-CN.min.js"></script>
	<script src="${pageContext.request.contextPath }/resource/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script src="${pageContext.request.contextPath }/resource/datetimepicker/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script src="${pageContext.request.contextPath }/resource/bootstrap-validator/js/bootstrapValidator.min.js"></script>
	<script src="${pageContext.request.contextPath }/resource/bootstrap-validator/local/zh_CN.js"></script>
	<script src="${pageContext.request.contextPath }/resource/account/js/account/billReport/billReportManage.js"></script>
	<script>
		loadBillType();
		validator();
		loadBillReportList();
	</script>

</html>