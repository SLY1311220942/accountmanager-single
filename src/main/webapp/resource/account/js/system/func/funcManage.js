/**
 * 初始化功能树
 */
function func_initZTree() {
	$.ajax({
		url: webRoot + '/system/func/findAllTopFunc',
		data: {
			'isAjax': 1
		},
		type: 'post',
		dataType: 'json',
		success: function(data) {
			if(data.status == 200) {
				var nodeList = data.dataMap.funcs;
				var zTree = $.fn.zTree.init($("#funcTree"), setting, nodeList);
			} else if(data.status == 300) {
				eval(data.dataMap.evalStr);
			} else {
				layer.alert(data.status + ":" + data.message, { icon: 2 });
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			layer.alert(errorThrown, { icon: 2 });
		}
	});
	resetDetail();
}

//显示 子树
var setting = {
	async: {
		autoParam: ["funcId"],
		//返回的数据过滤器
		dataFilter: filter,
		enable: true,
		type: "post",
		url: webRoot + "/system/func/findChildFunc",
		dataFilter: filter,
	},
	view: {
		addHoverDom: addHoverDom,
		removeHoverDom: removeHoverDom,
		showLine: true,
		selectedMulti: false,
	},
	check: {
		enable: false
	},
	data: {
		simpleData: {
			enable: true,
			idKey: "funcId",
			pIdKey: "parentId",
		}
	},
	callback: {
		beforeAsync: ztreeBeforeAsync,
		onAsyncSuccess: ztreeOnAsyncSuccess,
		onnAsyncError: ztreeOnAsyncError,
		onClick: nodeClick,
	},
};

function addHoverDom(treeId, treeNode) {
	var sObj = $("#" + treeNode.tId + "_span");
	if(treeNode.editNameFlag || $("#editBtn_" + treeNode.tId).length > 0) return;
	var addId = 'addBtn_' + treeNode.tId;
	var updateId = 'editBtn_' + treeNode.tId;
	var deleteId = 'deleteBtn_' + treeNode.tId;

	var addStr = '';
	if(treeNode.funcTag == 0) {
		addStr += '<span class="button add" title="添加子项" type="button" href="javaScript:void(0)" id=' + addId + ' onclick="func_toAddChild(\'' + treeNode.funcId + '\')"></span>';
	}
	addStr += '<span class="button edit" title="编辑此项" type="button" href="javaScript:void(0)" id=' + updateId + ' onclick="func_toUpdate(\'' + treeNode.funcId + '\')"></span>';
	addStr += '<span class="button remove" title="删除此项" type="button" href="javaScript:void(0)" id=' + deleteId + ' onclick="func_toDelete(\'' + treeNode.funcId + '\')"></span>';

	sObj.append(addStr);

};

function removeHoverDom(treeId, treeNode) {
	$("#addBtn_" + treeNode.tId).unbind().remove();
	$("#editBtn_" + treeNode.tId).unbind().remove();
	$("#deleteBtn_" + treeNode.tId).unbind().remove();

};

/**
 * 请求成功后的数据过滤函数
 */
function filter(treeId, parentNode, childNodes) {
	if(!childNodes) return null;
	for(var i = 0; i < childNodes.length; i++) {
		childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
	}
	return childNodes;
}

/**
 * 节点点击前触发方法，用于捕获单击节点之前的事件回调函数，并且根据返回值确定是否允许单击操作，默认值：null
 * 若返回false, 则中断后续操作， 不会向服务器发送请求
 */
function ztreeBeforeAsync(treeId, treeNode) {
	//该处判断点击节点是否属于父节点
	return(treeNode && treeNode.isParent); //如果是父节点    则发出异步ajax请求
	// 	$("#funcName").text(treeNode.myAttrs.funcName);

}

/**
 * 异步请求错误回调方法
 */
function ztreeOnAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
	console.log("XMLHttpRequest:" + XMLHttpRequest);
	console.log("textStatus:" + textStatus);
};

/**
 * ztree异步数据加载成功 时，回调
 */
function ztreeOnAsyncSuccess(event, treeId, treeNode) {

};

/**
 * 当点击znode时 触发
 */
function nodeClick(event, treeId, treeNode) {
	showDetail(event, treeId, treeNode);
}

function resetDetail() {
	$("#ti").text('');
	$("#funcId").text('');
	$("#funcName").text('');
	$("#funcTag").text('');
	$("#funcUrl").text('');
	$("#isOpen").text('');

	$("#funcSort").text('');
	$("#funcIcon").text('');
	$("#createtime").text('');
	$("#updatetime").text('');
	$("#remark").text('');

	$("#sysTag").text('');

}

function showDetail(event, treeId, treeNode) {
	$("#ti").text(treeNode.funcName);
	$("#funcId").text(treeNode.funcId);
	$("#funcName").text(treeNode.funcName);
	if(treeNode.funcTag == 0) {
		$("#funcTag").text('菜单');
	} else {
		$("#funcTag").text('按钮');
	}

	$("#funcUrl").text(treeNode.funcUrl);

	if(treeNode.isOpen == 1) {
		$("#isOpen").text('启用');
	} else {
		$("#isOpen").text('禁用');
	}
	if(treeNode.sysTag == 1) {
		$("#sysTag").text('是');
	} else {
		$("#sysTag").text('否');
	}
	$("#funcSort").text(treeNode.funcSort);
	$("#funcIcon").text(treeNode.funcIcon);
	$("#createtime").text(treeNode.createTime);
	$("#updatetime").text(treeNode.updateTime);
	$("#remark").text(treeNode.remark);
}

function func_toAdd() {
	var Jurl = webRoot + "/system/func/toFuncAdd"; //添加一级菜单
	layer.open({
		type: 2,
		skin: 'layui-layer-rim', //加上边框
		area: ['100%', '100%'], //宽高
		content: Jurl
	});
	//layer.full(index);
}

function func_toAddChild(funcId) {
	var Jurl = webRoot + "/system/func/toFuncAdd?funcId=" + funcId;
	layer.open({
		type: 2,
		skin: 'layui-layer-rim', //加上边框
		area: ['100%', '100%'], //宽高
		content: Jurl
	});
	//layer.full(index);
}

function func_toUpdate(funcId) {
	var Jurl = webRoot + "/system/func/toFuncUpdate?funcId=" + funcId;
	layer.open({
		type: 2,
		skin: 'layui-layer-rim', //加上边框
		area: ['100%', '100%'], //宽高
		content: Jurl
	});
	// layer.full(index);
}

function func_toDelete(funcId) {
	layer.confirm('确定删除此功能及其下级节点吗?', { icon: 3, title: '提示' }, function(index) {
		$.ajax({
			url: webRoot + "/system/func/funcDelete",
			data: {
				'funcId': funcId,
				'token': $.trim($("#token").val()),
				'isAjax': 1
			},
			type: 'post',
			dataType: 'json',
			success: function(data) {
				if(data.status == 200) {
					$("#token").val(data.dataMap.SYSTEM_FUNC_DELETE_TOKEN);
					layer.alert(data.message, { icon: 1 });
					func_initZTree();
				} else if(data.status == 300) {
					eval(data.dataMap.evalStr);
				} else {
					layer.alert(data.status + ":" + data.message, { icon: 2 });
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				layer.alert(errorThrown, { icon: 2 });
			}
		});
		layer.close(index);
	});
}