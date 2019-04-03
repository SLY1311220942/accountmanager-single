function loadMean() {
	$.ajax({
		type: "post",
		url: webRoot + "/system/func/findUserMean",
		dataType: "json",
		data: {
			isAjax: 1
		},
		success: function(data) {
			if(data.status == 200) {
				var funcs = data.dataMap.funcs;
				var subStr = new StringBuffer();
				subStr.append('<li class="header">主菜单</li>');
				genMean(funcs, subStr);
				$("#menu").html(subStr.toString());
			} else if(data.status == 300) {
				eval(data.dataMap.evalStr);
			} else {
				layer.alert(data.message, { icon: 2 });
			}
		}
	});
}

function genMean(funcs, subStr) {
	for(var i = 0; i < funcs.length; i++) {
		var func = funcs[i];
		if(func.childrenFunc){
			subStr.append('<li class="treeview">');
			subStr.append('<a href="javascript：void(0);"><i class="' + func.funcIcon + '"></i> <span>' + func.funcName + '</span>');
			subStr.append('<span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>');
			subStr.append('</a>');
			subStr.append('<ul class="treeview-menu">');
			genMean(func.childrenFunc,subStr);
			subStr.append('</ul>');
			subStr.append('</li>')
		}else{
			if(func.parentId){
				subStr.append('<li class="treeview">');
				subStr.append('<a class="multitabs" href="' + webRoot +  func.funcUrl + '" data-type="info" data-iframe="true">');
				subStr.append('<i class="' + func.funcIcon + '"></i> <span>' + func.funcName + '</span>');
				subStr.append('</a></li>');
			}else{
				subStr.append('<li class="treeview">');
				subStr.append('<a href="javascript：void(0);"><i class="' + func.funcIcon + '"></i> <span>' + func.funcName + '</span>');
				subStr.append('<span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>');
				subStr.append('</a>');
				subStr.append('<ul class="treeview-menu">');
				subStr.append('</ul>');
				subStr.append('</li>')
			}
			
		}
	}
}