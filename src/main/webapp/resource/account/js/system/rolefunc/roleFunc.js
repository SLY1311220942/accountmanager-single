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

function loadRoleFuncTree() {
	$.ajax({
		url: webRoot + '/system/func/findRoleAllFuncTree',
		data: {
			roleId: $("#roleId").val(),
			isAjax: 1
		},
		type: 'post',
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				taskNodes = data.dataMap.funcTree;
				taskZTreeObj = $.fn.zTree.init($("#roleFuncTree"), setting, taskNodes);
			} else if(data.status == 300) {
				eval(data.dataMap.evalStr);
			} else {
				layer.alert(data.message, { icon: 2 });
			}
			//$('#token').val(data.roleMenuToken);
		}
	});
}

function saveRoleFuncTree() {
	var roleId = $("#roleId").val();
	var checkNodes = taskZTreeObj.getCheckedNodes(true); //获取所有选中的 节点
	var funcIdArray = [];
	for(var i in checkNodes) {
		//alert(checkNodes[i].id);
		funcIdArray.push(checkNodes[i].id);
	}
	
	var index = parent.layer.getFrameIndex(window.name);
	$.ajax({
		type: 'post',
		url: webRoot + '/system/roleFunc/distributeRoleFunc',
		dataType: 'json',
		data: {
			roleId: roleId,
			funcIds: JSON.stringify(funcIdArray),
			token: $("#token").val(),
			isAjax: 1
		},
		success: function(data) {
			if(data.status == 200) {
				layer.confirm(data.message, {
					btn : [ '确定' ]
				// 按钮
				}, function() {
					parent.location.reload();
					parent.layer.close(index);
				});
			} else if(data.status == 300) {
				eval(data.dataMap.evalStr);
			} else {
				layer.alert(data.message, { icon: 2 });
			}
		}
	});
}