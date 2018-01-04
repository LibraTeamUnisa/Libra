//DonutBar dei nuovi tirocinanti
$.get("gestionePfServlet?action=topAziende&pastDays=30&limit=3", function(data, status){
		var result = [];
		var colors = ['text-warning','text-danger','text-info']
		var i = 0;
		data = JSON.parse(data);
        document.getElementById("topLast30DaysList").innerHTML = "";
		for(x in data){
	        document.getElementById("topLast30DaysList").innerHTML += '<li><h5><i class="fa fa-circle m-r-5 '+colors[i]+'"></i>'+x+'</h5></li>';
			result[i++] = {
					label: x,
					value: data[x]
			};
		}
        var bar = Morris.Donut({
            element: 'topLast30Days',
            data: result,
            resize: true,
            colors: ['#ffbc34', '#f62d51', '#009efb']
        });
});

$.get("gestionePfServlet?action=getNumTirociniCompletati", function(data, status){
    document.getElementById("currentDateCompletatiValue").innerHTML = data;
});

$.get("reportStudenteServlet?action=getNumReports", function(data, status){
    document.getElementById("currentDateReportsValue").innerHTML = data;
});

// ============================================================== 
// Tirocini completati
// ============================================================== 
$(function () {
new Chartist.Line('.usage', {
    labels: ['0', '4', '8', '12', '16', '20', '24', '30']
    , series: [
    [5, 0, 12, 1, 8, 3, 12, 15]

  ]
}, {
    high: 13
    , low: 0
    , showArea: true
    , fullWidth: true
    , plugins: [
    Chartist.plugins.tooltip()
  ], // As this is axis specific we need to tell Chartist to use whole numbers only on the concerned axis
    axisY: {
        onlyInteger: true
        , offset: 20
        , showLabel: false
        , showGrid: false
        , labelInterpolationFnc: function (value) {
            return (value / 1) + 'k';
        }
    }
    , axisX: {
        showLabel: false
        , divisor: 2
        , showGrid: false
        , offset: 0
    }
});
//============================================================== 
// Report generati
// ============================================================== 
var sparklineLogin = function () {
    $('.spark-count').sparkline([4, 5, 0, 10, 9, 12, 4, 9, 4, 5, 3, 10, 9, 12, 10, 9, 12, 4, 9], {
        type: 'bar'
        , width: '100%'
        , height: '100'
        , barWidth: '8'
        , resize: true
        , barSpacing: '5'
        , barColor: 'rgba(255, 255, 255, 0.3)'
    });
}
var sparkResize;
$(window).resize(function (e) {
    clearTimeout(sparkResize);
    sparkResize = setTimeout(sparklineLogin, 500);
});
sparklineLogin();


});