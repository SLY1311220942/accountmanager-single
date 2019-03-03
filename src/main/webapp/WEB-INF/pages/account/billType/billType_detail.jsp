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
				<div class="col-xs-12 ui-sortable">
					<div class="box">
						<div class="row" style="margin-top: 10px; margin-bottom: 8px;">
							<!-- 查询版块 -->
							<div class="wrap1">
								<h4 class="title">
								账单详情
								</h4>
								<div class="col-md-12 column ui-sortable" style="margin-top: 20px;">
									<form class="form-horizontal required-validate" id="billUpdateForm">
										<input type="hidden" id="billId" name="billId" value="${billId}" />
										<div class="view">
											<div class="row">
												<div class="form-group col-md-6  column ui-sortable">
													<label class="col-sm-4 control-label" style="text-align: right; float: left;">账单类型名称：</label>
													<div class="col-sm-8">
														<input readonly="readonly" style="border: none;background: white;padding-top: 6px;" type="text" id="revexpType" name="revexpType" value="" />
													</div>
												</div>
												<div class="form-group col-md-6  column ui-sortable">
													<label class="col-sm-4 control-label" style="text-align: right; float: left;">账单时间：</label>
													<div class="col-sm-8">
														<input readonly="readonly" style="border: none;background: white;padding-top: 6px;" type="text" id="billTime" name="billTime" value="" />
													</div>
												</div>
											</div>

											<div class="row">
												<div class="form-group col-md-6  column ui-sortable">
													<label class="col-sm-4 control-label" style="text-align: right; float: left;">账单金额：</label>
													<div class="col-sm-8">
														<input readonly="readonly" style="border: none;background: white;padding-top: 6px;" type="text" id="billAmount" name="billAmount" value="" />
													</div>
												</div>
											</div>

											<div class="row">
												<div class="form-group col-md-12  column ui-sortable">
													<label class="col-sm-2 control-label" style="text-align: right; float: left;right: 5px;">账单摘要：</label>
													<div class="col-sm-6" style="right: 5px;">
														<textarea class="form-control" readonly="readonly" style="border: none;background: white;height: 100px;padding-top: 6px;" id="billDetail" name="billDetail"></textarea>
													</div>
												</div>
											</div>

											<div class="row">
												<div class="form-group col-md-12  column ui-sortable">
													<label class="col-sm-2 control-label" style="text-align: right; float: left;right: 5px;">备注：</label>
													<div class="col-sm-6" style="right: 5px;">
														<textarea class="form-control" readonly="readonly" style="border: none;background: white;height: 100px;padding-top: 6px;" id="remark" name="remark"></textarea>
													</div>
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
 	<%@include file="/WEB-INF/pages/include/infooter.jsp" %>
	<script src="${pageContext.request.contextPath }/resource/account/js/account/bill/billDetail.js"></script>
	<script>
		$(function() {
			loadBill();
		});
	</script>
</html>