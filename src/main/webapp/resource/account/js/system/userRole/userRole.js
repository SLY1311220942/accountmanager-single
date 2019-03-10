var setting = {
	check: {
		enable: true
	},
	view: {
		showIcon: true
	},
	data: {
		simpleData: {
			enable: true,
			idKey: "id",
			pIdKey: "pid",

		}
	}
};

var taskNodes = null;
var taskZTreeObj = null;

function loadUserRoleTree() {
	$.ajax({
		url: webRoot + '/system/role/findUserAllRoleTree',
		data: {
			userId: $("#userId").val(),
			isAjax: 1
		},
		type: 'post',
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				taskNodes = data.dataMap.roleTree;
				taskZTreeObj = $.fn.zTree.init($("#userRoleTree"), setting, taskNodes);
			} else if(data.status == 300) {
				eval(data.dataMap.evalStr);
			} else {
				layer.alert(data.message, { icon: 2 });
			}
		}
	});
}

function saveUserRoleTree(w) {
	var userId = $("#userId").val();
	var checkNodes = taskZTreeObj.getCheckedNodes(true); //获取所有选中的 节点
	var roleIdArray = [];
	for(var i in checkNodes) {
		//alert(checkNodes[i].id);
		roleIdArray.push(checkNodes[i].id);
	}
	var l = Ladda.create(w);
	l.start();
	var index = parent.layer.getFrameIndex(window.name);
	$.ajax({
		type: 'post',
		url: webRoot + '/system/userRole/distributeUserRole',
		dataType: 'json',
		data: {
			userId: userId,
			roleIds: JSON.stringify(roleIdArray),
			token: $("#token").val(),
			isAjax: 1
		},
		success: function(data) {
			if(data.status == 200) {
				layer.confirm(data.message, {
					btn: ['确定']
					// 按钮
				}, function() {
					//parent.location.reload();
					parent.layer.close(index);
				});
			} else if(data.status == 300) {
				eval(data.dataMap.evalStr);
			} else {
				layer.alert(data.message, { icon: 2 });
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			layer.alert(COMMON_REQUEST_RESPONSE_FAIL, { icon: 2 });
		},
		complete: function(XMLHttpRequest, textStatus) {
			l.stop();
		}
	});
}