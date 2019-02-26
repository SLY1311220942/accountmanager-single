function billTypeSearch() {
	var bootstrapValidator = $("#billTypeSearch").data('bootstrapValidator');
	bootstrapValidator.validate();
	if(bootstrapValidator.isValid()) {
		$('#dataTable').bootstrapTable('removeAll');
		$("#dataTable").bootstrapTable('refresh', { url: webRoot + '/account/billType/findTopBillTypeList' });
	}
}

function loadBillTypeList() {
	var bootstrapValidator = $("#billTypeSearch").data('bootstrapValidator');
	bootstrapValidator.validate();
	if(bootstrapValidator.isValid()) {
		$('#dataTable').bootstrapTable({
			dataField: 'rows', // 要求服务端返回数据键值 就是说记录放的键值是rows
			totalField: 'total', // 要求服务器端返回的总记录数的参数名为total
			url: webRoot + '/account/billType/findTopBillTypeList',
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
					billTypeName: $.trim($("#billTypeName").val()),
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
			columns: [{
					title: '序号', //标题  可不加  
					width: 20,
					align: 'center',
					formatter: function(value, row, index) {
						return index + 1;
					}
				},
				{
					title: '账单类型名称',
					field: 'billTypeName',
					align: 'center'
				}, {
					title: '创建时间',
					field: 'createTime',
					align: 'center'
				}, {
					title: '操作',
					field: 'operate',
					align: 'center',
					events: operateEvents,
					formatter: operateFormatter
				}
			],
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
		'<a class="btn btn-xs btn-warning update" type="button" href="javaScript:void(0)" style="margin-left: 5px;"> <span>&nbsp;修改</span></a>',
		'<a class="btn btn-xs btn-danger delete" type="button" href="javaScript:void(0)" style="margin-left: 5px;"> <span>&nbsp;删除</span></a>'
	].join('');
}

window.operateEvents = {
	'click .detail': function(e, value, row, index) {
		var Jurl = webRoot + "/account/billType/toBillTypeDetail?billId=" + row.billId;
		layer.open({
			type: 2,
			skin: 'layui-layer-rim', //加上边框
			area: ['100%', '100%'], //宽高
			content: Jurl
		});
	},
	'click .update': function(e, value, row, index) {
		var Jurl = webRoot + "/account/billType/toBillTypeUpdate?billId=" + row.billId;
		layer.open({
			type: 2,
			skin: 'layui-layer-rim', //加上边框
			area: ['100%', '100%'], //宽高
			content: Jurl
		});
	},
	'click .delete': function(e, value, row, index) {
		layer.confirm('确定删除该账单?', {
			btn: ['確定', '取消'] //按钮
		}, function() {
			$.ajax({
				type: "post",
				url: webRoot + "/account/billType/billTypeDelete",
				dataType: "json",
				data: {
					billId: row.billId,
					isAjax: 1
				},
				success: function(data) {
					if(data.status == 200) {
						var index = layer.confirm(data.message, {
							btn: ['确定']
							// 按钮
						}, function() {
							billTypeSearch();
							layer.close(index);
						});
					} else if(data.status == 300) {
						eval(data.evalCode);
					} else {
						layer.alert(data.message, { icon: 2 });
					}
				}
			});
		}, function() {

		});

	},

}

function toBillTypeAdd() {
	var Jurl = webRoot + "/account/billType/toBillTypeAdd";
	layer.open({
		type: 2,
		skin: 'layui-layer-rim', //加上边框
		area: ['100%', '100%'], //宽高
		content: Jurl
	});
}


/*
 * 查询验证
 */
function validator() {
	$("#billTypeSearch").bootstrapValidator({
		feedbackIcons: {
			validating: 'glyphicon glyphicon-refresh'
		},
		message: '',
		fields: {
			billTypeName: {
				message: '',
				validators: {
					stringLength: {
						min: 1,
						max: 32,
						message: '账单类型名称不得超过32个字符!'
					},
				}
			},
		}
	});
}