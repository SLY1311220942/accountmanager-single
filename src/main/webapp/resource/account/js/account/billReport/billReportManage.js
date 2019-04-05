function billReportSearch() {
	var bootstrapValidator = $("#billReportSearch").data('bootstrapValidator');
	bootstrapValidator.validate();
	if(bootstrapValidator.isValid()) {
		$("#statisticType1").val($.trim($("#statisticType").val()));
		$("#beginTime1").val($.trim($("#beginTime").val()));
		$("#endTime1").val($.trim($("#endTime").val()));
		$("#billTypeId1").val($.trim($("#billTypeId").val()));
		$("#dataTimeType1").val($.trim($("#dataTimeType").val()));
		
		if($("#statisticType").val() == $("#statisticTypeTemp").val()){
			//没有切换类型
			$('#dataTable').bootstrapTable('removeAll');
			$("#dataTable").bootstrapTable('refresh', { url: webRoot + '/account/billReport/findReportList' });
		}else{
			//切换了类型
			$("#statisticTypeTemp").val($("#statisticType").val());
			$('#dataTable').bootstrapTable('destroy');
			loadBillReportList();
		}		
	}
}



function loadBillReportList() {
	var bootstrapValidator = $("#billReportSearch").data('bootstrapValidator');
	bootstrapValidator.validate();
	if(bootstrapValidator.isValid()) {
		var questionColumns = [];
		var column0 = {
			title: '序号',
			width: 20,
			align: 'center',
			formatter: function(value, row, index) {
				return index + 1;
			}
		}
		var column1;
		if($.trim($("#statisticType").val()) == 0) {
			column1 = {
				title: '日期',
				field: 'dateTime',
				align: 'center',
			}
		} else {
			column1 = {
				title: '类型',
				field: 'billTypeName',
				align: 'center'
			}
		}
		column2 = {
			title: '账单金额',
			field: 'billAmount',
			align: 'center'
		}
		column3 = {
			title: '账单数量',
			field: 'billCount',
			align: 'center'
		}
		column4 = {
			title: '操作',
			field: 'operate',
			align: 'center',
			events: operateEvents,
			formatter: operateFormatter
		}
		questionColumns.push(column0);
		questionColumns.push(column1);
		questionColumns.push(column2);
		questionColumns.push(column3);
		questionColumns.push(column4);
		
		
		$('#dataTable').bootstrapTable({
			dataField: 'rows', // 要求服务端返回数据键值 就是说记录放的键值是rows
			totalField: 'total', // 要求服务器端返回的总记录数的参数名为total
			url: webRoot + '/account//billReport/findReportList',
			method: 'post',
			singleSelect: false,
			striped: true, // 是否显示行间隔色
			cache: false,
			height: 550, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination: true, // 是否显示分页（*）
			sortable: false, // 是否启用排序
			sortOrder: "asc", // 排序方式
			queryParams: function getParams(params) {
				var obj = {
					currentPage: (params.offset / params.limit) + 1,
					statisticType: $.trim($("#statisticType").val()),
					dataTimeType: $.trim($("#dataTimeType").val()),
					billTypeId: $.trim($("#billTypeId").val()),
					beginTime: $.trim($("#beginTime").val()),
					endTime: $.trim($("#endTime").val()),
					pageSize: params.limit,
					sortName: this.sortName,
					sortOrder: this.sortOrder,
					isAjax: 1
				};
				return obj;
			},
			sidePagination: "server", // 分页方式：client客户端分页，server服务端分页（*）
			pageNumber: 1, // 初始化加载第一页，默认第一页
			pageSize: 10, // 每页的记录行数（*）
			pageList: [5, 10, 20, 50, 100], // 可供选择的每页的行数（*）
			strictSearch: true,
			showColumns: false, // 是否显示所有的列
			showRefresh: false, // 是否显示刷新按钮
			clickToSelect: false, // 是否启用点击选中行
			idField: "id",
			buttonsAlign: "left", // 按钮对齐方式
			selectItemName: 'id',
			align: 'center',
			columns: questionColumns,
			showToggle: false, // 是否显示详细视图和列表视图的切换按钮
			cardView: false, // 是否显示详细视图
			detailView: false, // 是否显示父子表
			'contentType': 'application/x-www-form-urlencoded; charset=UTF-8',
			onLoadSuccess: function(data) {
				
				if(data.status == 201) {
					$("#dataTable").bootstrapTable('removeAll');
				} else if(data.status == 300) {
					eval(data.evalCode);
				} else if(data.status == 200) {

				} else {
					layer.alert(data.message, { icon: 2 });
				}
			}

		});

	}
}

function operateFormatter(value, row, index) {
	return [
		'<a class="btn btn-xs btn-info detail" type="button" href="javaScript:void(0)" > <span>&nbsp;详情</span></a>',
	].join('');
}

window.operateEvents = {
	'click .detail': function(e, value, row, index) {
		var statisticType = $.trim($("#statisticType1").val())
		var beginTime = $.trim($("#beginTime1").val());
		var endTime = $.trim($("#endTime1").val());
		var billTypeId = $.trim($("#billTypeId1").val());
		var dataTimeType = $.trim($("#dataTimeType1").val());
		
		if(statisticType == "0"){
			//时间
			statisticType = "1";
		}else{
			//类型
			statisticType = "0";
			billTypeId = row.billTypeId;
		}
		
		
		var Jurl = webRoot + "/account/billReport/toReportDetail?statisticType=" + statisticType 
			+ "&dataTimeType=" + dataTimeType + "&billTypeId=" + billTypeId + "&beginTime=" + beginTime
			+ "&endTime=" + endTime + "&dateTime=" + row.dateTime + "&billTypeName=" + row.billTypeName;
		layer.open({
			type: 2,
			skin: 'layui-layer-rim', //加上边框
			area: ['100%', '100%'], //宽高
			content: Jurl
		});
	},
}

/**
 * 加载账单类型
 */
function loadBillType() {
	$.ajax({
		type: "post",
		url: webRoot + "/account/billType/findAllTopBillType",
		dataType: "json",
		data: {
			isAjax: 1
		},
		success: function(data) {
			if(data.status == 200) {
				var html = new StringBuffer();
				html.append('<option value="">请选择</option>');
				for(var i = 0; i < data.rows.length; i++) {
					html.append('<option value="' + data.rows[i].billTypeId + '">' + data.rows[i].billTypeName + '</option>');
				}

				$("#billTypeId").html(html.toString());

			} else if(data.status == 300) {
				eval(data.dataMap.evalStr);
			} else {
				layer.alert(data.message, { icon: 2 });
			}
		}
	});
}

function billReportExport() {
	alert("导出");
}

/**
 * 账单报表时间初始化 start
 */
$('#beginTime').datetimepicker({
	language: 'zh-CN',
	todayHighlight: true,
	minView: 'month',
	autoclose: true,
	viewMode: 'years',
	format: 'yyyy-mm-dd',
	clearBtn: true
}).on('hide', function(e) {
	$('#billReportSearch').data('bootstrapValidator')
		.updateStatus('beginTime', 'NOT_VALIDATED', null)
		.validateField('beginTime');
	$('#billReportSearch').data('bootstrapValidator')
		.updateStatus('endTime', 'NOT_VALIDATED', null)
		.validateField('endTime');
});

$('#endTime').datetimepicker({
	language: 'zh-CN',
	todayHighlight: true,
	minView: 'month',
	autoclose: true,
	viewMode: 'years',
	format: 'yyyy-mm-dd',
	clearBtn: true
}).on('hide', function(e) {
	$('#billReportSearch').data('bootstrapValidator')
		.updateStatus('beginTime', 'NOT_VALIDATED', null)
		.validateField('beginTime');
	$('#billReportSearch').data('bootstrapValidator')
		.updateStatus('endTime', 'NOT_VALIDATED', null)
		.validateField('endTime');
});
/*账单时间初始化 end*/

/*
 * 查询验证
 */
function validator() {
	$("#billReportSearch").bootstrapValidator({
		feedbackIcons: {
			validating: 'glyphicon glyphicon-refresh'
		},
		message: '',
		fields: {
			statisticType: {
				message: '',
				validators: {
					notEmpty: {
						message: '请选择统计方式!'
					},
				}
			},
			beginTime: {
				validators: {
					callback: {
						message: '开始日期不能大于结束日期',
						callback: function(value, validator) {
							var items = $('#endTime').val();
							if(items == '' || items == null || items == undefined) {
								return true
							} else {
								var d1 = new Date(items.replace(/\-/g, "\/"));
								var d2 = new Date(value.replace(/\-/g, "\/"));
								if(d2 > d1) {
									return false;
								} else {
									return true;
								}
							}
						}
					}
				}
			},
			endTime: {
				validators: {
					callback: {
						// message: '结束日期不能小于开始日期',
						callback: function(value, validator) {
							var items = $('#beginTime').val();
							if(items == '' || items == null || items == undefined) {
								return true
							} else {
								var d1 = new Date(items.replace(/\-/g, "\/"));
								var d2 = new Date(value.replace(/\-/g, "\/"));
								if(d2 < d1) {
									return false;
								} else {
									return true;
								}
							}
						}
					}
				}
			},

		}
	});
}