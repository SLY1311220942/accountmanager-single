function initBillReportChart() {
	var billReportChart = echarts.init(document.getElementById('billReportDetail'));

	// 指定图表的配置项和数据
	var option = {
		title: {
			text: 'ECharts 入门示例'
		},
		tooltip: {},
		legend: {
			data: ['金额1']
		},
		xAxis: {
			data: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"]
		},
		yAxis: {},
		series: [{
			name: '金额1',
			type: 'bar',
			data: [5, 20, 36, 10, 10, 20]
		}, ],
	};

	// 使用刚指定的配置项和数据显示图表。
	billReportChart.setOption(option);
}