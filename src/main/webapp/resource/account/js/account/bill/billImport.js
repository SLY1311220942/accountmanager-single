var clearFlag = 0;

function initFileUpload() {
	$('#file').fileinput({
		uploadUrl: webRoot + '/account/bill/billImport',
		language: 'zh',
		//allowedPreviewTypes: ['image'],  
		//allowedFileTypes: ['xls','xlsx'], 
		allowedFileExtensions: ['xls','xlsx'],
		overwriteInitial: false,
		maxFilePreviewSize: 1024 * 1,
		maxFileSize: 1024 * 1,
		showRemove: false,
		showUpload: false,
		showClose: false,
		showPreview: false,
		maxFilesNum: 1, //同时最多上传1个文件
		initialPreviewCount: 1,
		layoutTemplates: {
			actionUpload: '',
			actionZoom: ''
		},
		slugCallback: function(filename) { //文件名替换  
			return filename.replace('(', '_').replace(']', '_');
		},
		uploadExtraData: function(previewId, index) { //额外参数的关键点
			var obj = {
				token : $.trim($("#token").val())
			};
			return obj;
		}
	}).on("fileuploaded", function(e, data) {
		clearFlag = 1;
		layer.confirm(data.response.message, {
			btn: ['確定'] //按钮
		}, function() {
			
			if(data.response.status == 200){
				parent.billSearch();
			}
			parent.layer.closeAll();
			
		});
	}).on("fileuploaderror", function(e, data, msg) {
		//msg = "请上传标准的jpg,png,jpeg格式的图片";
		clearFlag = 0;
		layer.alert(msg, {
			skin: 'layui-layer-molv',
			closeBtn: 0,
			anim: 1 //动画类型
		});
	}).on('change', function(event) {
		if(clearFlag == 1) {
			$('#file').fileinput('refresh');
			clearFlag = 0;
		}
	});
}

function billImport() {
	$('#file').fileinput('upload');
}