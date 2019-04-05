<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
    <head>
        <base href="<%=basePath%>">

		<title>修改账单</title>

		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<%@include file="/WEB-INF/pages/include/inheader.jsp" %>
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
				<input type="hidden" id="statisticType" name="statisticType" value="${billReport.statisticType}" />
				<input type="hidden" id="dataTimeType" name="dataTimeType" value="${billReport.dataTimeType}" />
				<input type="hidden" id="dateTime" name="dateTime" value="${billReport.dateTime}" />
				<input type="hidden" id="billTypeName" name="billTypeName" value="${billReport.billTypeName}" />
				<input type="hidden" id="billTypeId" name="billTypeId" value="${billReport.billTypeId}" />
				<input type="hidden" id="beginTime" name="beginTime" value="${billReport.beginTime}" />
				<input type="hidden" id="endTime" name="endTime" value="${billReport.endTime}" />
				<div class="col-xs-12 ui-sortable">
					<div class="box">
						<div class="row" style="margin-top: 10px; margin-bottom: 8px;">
							<!-- 查询版块 -->
							<div class="wrap1">
								<h4 class="title">
								账单报表详情
								</h4>
								<div class="col-md-12 column ui-sortable" style="margin-top: 20px;"  >
									<div class="view">
										<div id="billReportDetail" style="height: 600px;">
											
											
										</div>
									</div>
									
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
 	</body>
 	<%@include file="/WEB-INF/pages/include/infooter.jsp" %>
 	<script src="${pageContext.request.contextPath }/resource/echarts/dist/echarts.min.js"></script>
	<script src="${pageContext.request.contextPath }/resource/account/js/account/billReport/billReportDetail.js"></script>
	<script>
		initBillReportChart();
	</script>
</html>