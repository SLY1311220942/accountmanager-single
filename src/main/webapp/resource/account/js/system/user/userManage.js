function userSearch() {
	var bootstrapValidator = $("#userSearch").data('bootstrapValidator');
	bootstrapValidator.validate();
	if(bootstrapValidator.isValid()) {
		$('#dataTable').bootstrapTable('removeAll');
		$("#dataTable").bootstrapTable('refresh', { url: webRoot + '/system/user/findUserList' });
	}
}

function loadUserList() {
	var bootstrapValidator = $("#userSearch").data('bootstrapValidator');
	bootstrapValidator.validate();
	if(bootstrapValidator.isValid()) {
		$('#dataTable').bootstrapTable({
			dataField: 'rows', // 要求服务端返回数据键值 就是说记录放的键值是rows
			totalField: 'total', // 要求服务器端返回的总记录数的参数名为total
			url: webRoot + '/system/user/findUserList',
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
					username: $.trim($("#username").val()),
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
					title: '用户名',
					field: 'username',
					align: 'center'
				}, {
					title: '真实姓名',
					field: 'realname',
					align: 'center'
				}, {
					title: '创建时间',
					field: 'createTime',
					align: 'center'
				}, {
					title: '手机号',
					field: 'phone',
					align: 'center'
				}, {
					title: '邮箱',
					field: 'email',
					align: 'center'
				}, {
					title: '状态',
					field: 'status',
					align: 'center',
					formatter: function(value, row, index) {
						if(value == 0 || value == 2) {
							return '<a class="btn btn-xs btn-success activeUser" type="button" href="javaScript:void(0)" > <span>&nbsp;已禁用</span></a>'
						} else if(value == 1) {
							return '<a class="btn btn-xs btn-danger disableUser" type="button" href="javaScript:void(0)" > <span>&nbsp;已激活</span></a>'
						} else {
							return value;
						}
					}

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
					eval(data.dataMap.evalStr);
				} else if(data.status == 200) {

				} else {
					layer.alert(data.message, { icon: 2 });
				}
			}

		});
	}
}

function validator() {
	$("#userSearch").bootstrapValidator({
		feedbackIcons: {
			validating: 'glyphicon glyphicon-refresh'
		},
		message: '',
		fields: {
			username: {
				message: '',
				validators: {
					regexp: {
						regexp: /^([\d\u4E00-\u9FA5a-zA-Z]){1,32}$/,
						message: "用户名只能是数字字母，最大长度32位!"
					},
				}
			},

		}
	});
}

function operateFormatter(value, row, index) {
	return [
		'<a class="btn btn-xs btn-warning assignRole" type="button" href="javaScript:void(0)" > <span>&nbsp;分配角色</span></a>'
	].join('');
}

window.operateEvents = {
	'click .assignRole': function(e, value, row, index) {
		var Jurl = webRoot + "/system/userRole/toDistributeUserRole?userId=" + row.userId;
		layer.open({
			type: 2,
			skin: 'layui-layer-rim', //加上边框
			area: ['100%', '100%'], //宽高
			content: Jurl
		});
	},
	'click .activeUser': function(e, value, row, index) {
		$.ajax({
			type: "post",
			url: webRoot + "user/activeUser",
			dataType: "json",
			data: {
				id: row.id
			},
			success: function(data) {
				if(data.status == 200) {
					processSearch();
				} else if(data.status == 400) {
					layer.alert(data.message, { icon: 2 });
				} else {
					layer.alert("未知错误！");
				}
			}
		});
	},
	'click .disableUser': function(e, value, row, index) {
		$.ajax({
			type: "post",
			url: webRoot + "user/disableUser",
			dataType: "json",
			data: {
				id: row.id,
				isAjax: 1
			},
			success: function(data) {
				if(data.status == 200) {
					processSearch();
				} else if(data.status == 400) {
					layer.alert(data.message, { icon: 2 });
				} else {
					layer.alert("未知错误！");
				}
			}
		});
	}
}

function toUserAdd() {
	var Jurl = webRoot + "/system/user/toUserAdd";
	layer.open({
		type: 2,
		skin: 'layui-layer-rim', //加上边框
		area: ['100%', '100%'], //宽高
		content: Jurl
	});
}