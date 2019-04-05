function initBillReportChart() {
	$.ajax({
		type:"post",
		url: webRoot + "/account/billReport/findBillReportChartDetail",
		dataType: "json",
		data:{
			statisticType: $.trim($("#statisticType").val()),
			dataTimeType: $.trim($("#dataTimeType").val()),
			dateTime: $.trim($("#dateTime").val()),
			billTypeId: $.trim($("#billTypeId").val()),
			beginTime: $.trim($("#beginTime").val()),
			endTime: $.trim($("#endTime").val())
		},
		success: function(data){
			if(data.status == 200) {
				var billReportChart = echarts.init(document.getElementById('billReportDetail'));
				
				
				var xAxisDates = data.dataMap.xAxisDatas;
				var series = data.dataMap.series;
				var lengedData = data.dataMap.lengedData;
				// 指定图表的配置项和数据
				var title = $.trim($("#statisticType").val()) == "0" ? "类型：" + $.trim($("#billTypeName").val()) : "时间：" + $.trim($("#dateTime").val());
				var option = {
					title: {
						text: title
					},
					tooltip: {},
					legend: {
						data: lengedData
					},
					xAxis: {
						data: xAxisDates
					},
					yAxis: {},
					series: seriesAssemble(series),
				};

				// 使用刚指定的配置项和数据显示图表。
				billReportChart.setOption(option);
			} else if(data.status == 300) {
				eval(data.evalCode);
			} else {
				layer.alert(data.message, { icon: 2 });
			}
		}
	});

}

function seriesAssemble(series){
	var seriesArray = [];
	for (var i = 0; i < series.length;i++) {
		var item = {
			name: series[i].name,
			type: 'bar',
			data: series[i].data
		}
		seriesArray.push(item);
	}
	return seriesArray;
}
