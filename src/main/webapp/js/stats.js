//DonutBar dei nuovi tirocinanti
var tId;

function compareMonth(a,b) {
	arrayA = a.split(" ");
	arrayB = b.split(" ");
	a = parseInt(arrayA[1]+arrayA[0]);
	b = parseInt(arrayB[1]+arrayB[0]);
  if (a < b)
    return -1;
  if (a > b)
    return 1;
  return 0;
}
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

$.get("ReportStudenteServlet?action=getNumReports", function(data, status){
    document.getElementById("currentDateReportsValue").innerHTML = data;
});

$.get("gestionePfServlet?action=countByAziendaAndDate&fromDate='2016-01-01'&toDate='2018-12-01'", function(data, status){
	data = JSON.parse(data);
	
	if(!data || data.length == 0){
		$('#errorFilt').show()
		return;
	}
	
	updateAnimatedChart(data);
});

$.get("gestionePfServlet?action=getTabellaValutazioni" +
		"&fromDate='2016-01-01'&toDate='2018-12-01'",function(data,status){
	data = JSON.parse(data);
	
	if(!data || data.length == 0){
		$('#errorFilt').show();
		return;
	}

	updateTable(data);
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
  ], // As this is axis specific we need to tell Chartist to use whole
		// numbers only on the concerned axis
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
// ==============================================================
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

var updateAnimatedChart = (data) =>{
	$('#errorFilt').hide();
	
	var colors = ['text-info','text-danger','text-success','text-warning'];
	var labelSets = new Set();
	var series = new Array();
	series.push(new Array());
	var listRagSoc = new Array();
	listRagSoc.push(data[0].ragioneSociale);
	var minValue = parseInt(data[0].numStudenti);
	var maxValue = parseInt(data[0].numStudenti);
		
	for(var i = 0;i<data.length;i++){
		var numStudenti = parseInt(data[i].numStudenti);
		var meseInizio = data[i].meseInizio;
		var ragSoc = data[i].ragioneSociale;
		
		labelSets.add(meseInizio);
		
		if(listRagSoc[listRagSoc.length -1] != ragSoc){
			if(series.length > 3)
				break;
			listRagSoc.push(ragSoc);
			series.push(new Array());
		}
		
		if(minValue > numStudenti)
			minValue = numStudenti;
		if(maxValue < numStudenti)
			maxValue = numStudenti;

		series[series.length -1].push(numStudenti);
	}
	var labels = Array.from(labelSets);
	$('#listAziendeAnim').html("");
	for(var ragSoc in listRagSoc){
		var list = '<li><h6 class="text-muted"><i class="fa fa-circle m-r-5 '+colors[ragSoc]+'"></i>'+listRagSoc[ragSoc]+'</h6></li>';
		$('#listAziendeAnim').append(list);
	}
	
	// ct-animation-chart

	var chart = new Chartist.Line('.ct-animation-chart', {
	  labels: labels,
	  series: series
	}, {
	  high: maxValue + 2,
	  low: 0,
	  axisY: {
		  onlyInteger: true
		  }
	});

	// Let's put a sequence number aside so we can use it in the event callbacks
	var seq = 0,
	  delays = 80,
	  durations = 500;

	// Once the chart is fully created we reset the sequence
	chart.on('created', function() {
	  seq = 0;
	});

	// On each drawn element by Chartist we use the Chartist.Svg API to trigger
	// SMIL animations
	chart.on('draw', function(data) {
	  seq++;

	  if(data.type === 'line') {
	    // If the drawn element is a line we do a simple opacity fade in. This
		// could also be achieved using CSS3 animations.
	    data.element.animate({
	      opacity: {
	        // The delay when we like to start the animation
	        begin: seq * delays + 1000,
	        // Duration of the animation
	        dur: durations,
	        // The value where the animation should start
	        from: 0,
	        // The value where it should end
	        to: 1
	      }
	    });
	  } else if(data.type === 'label' && data.axis === 'x') {
	    data.element.animate({
	      y: {
	        begin: seq * delays,
	        dur: durations,
	        from: data.y + 100,
	        to: data.y,
	        // We can specify an easing function from Chartist.Svg.Easing
	        easing: 'easeOutQuart'
	      }
	    });
	  } else if(data.type === 'label' && data.axis === 'y') {
	    data.element.animate({
	      x: {
	        begin: seq * delays,
	        dur: durations,
	        from: data.x - 100,
	        to: data.x,
	        easing: 'easeOutQuart'
	      }
	    });
	  } else if(data.type === 'point') {
	    data.element.animate({
	      x1: {
	        begin: seq * delays,
	        dur: durations,
	        from: data.x - 10,
	        to: data.x,
	        easing: 'easeOutQuart'
	      },
	      x2: {
	        begin: seq * delays,
	        dur: durations,
	        from: data.x - 10,
	        to: data.x,
	        easing: 'easeOutQuart'
	      },
	      opacity: {
	        begin: seq * delays,
	        dur: durations,
	        from: 0,
	        to: 1,
	        easing: 'easeOutQuart'
	      }
	    });
	  } else if(data.type === 'grid') {
	    // Using data.axis we get x or y which we can use to construct our
		// animation definition objects
	    var pos1Animation = {
	      begin: seq * delays,
	      dur: durations,
	      from: data[data.axis.units.pos + '1'] - 30,
	      to: data[data.axis.units.pos + '1'],
	      easing: 'easeOutQuart'
	    };

	    var pos2Animation = {
	      begin: seq * delays,
	      dur: durations,
	      from: data[data.axis.units.pos + '2'] - 100,
	      to: data[data.axis.units.pos + '2'],
	      easing: 'easeOutQuart'
	    };

	    var animations = {};
	    animations[data.axis.units.pos + '1'] = pos1Animation;
	    animations[data.axis.units.pos + '2'] = pos2Animation;
	    animations['opacity'] = {
	      begin: seq * delays,
	      dur: durations,
	      from: 0,
	      to: 1,
	      easing: 'easeOutQuart'
	    };

	    data.element.animate(animations);
	  }
	});
};

var updateTable = (data) => {
	window.db.clients = data;
	initTable();
	$("#basicgrid").jsGrid("refresh");};

var onFilterChanged = function(){
	clearTimeout(tId);
	
	var stato = $('input[name=options]:checked', '#statusRad').val();
	if(!stato)
		stato = ''
			
	var ragSoc = $('#ragSocFilt').val();
	var dal = $('#dalFilt').val();
	var al = $('#alFilt').val();
	
	tId=setTimeout(function () {
		$.get("gestionePfServlet?action=countByAziendaAndDate" +
			"&fromDate="+dal+"&toDate="+al+"&ragSoc="+ragSoc+"&status="+stato,function(data,status){
		data = JSON.parse(data);
		
		if(!data || data.length == 0){
			$('#errorFilt').show();
			return;
		}

		updateAnimatedChart(data);
		});
		$.get("gestionePfServlet?action=getTabellaValutazioni" +
				"&fromDate="+dal+"&toDate="+al+"&ragSoc="+ragSoc+"&status="+stato,function(data,status){
			data = JSON.parse(data);
			
			if(!data || data.length == 0){
				$('#errorFilt').show();
				return;
			}

			updateTable(data);
		});

		},1000);
};

$('#ragSocFilt').bind('input propertychange', () => onFilterChanged());
$('#alFilt').bind('input propertychange', () => onFilterChanged());
$('#dalFilt').bind('input propertychange', () => onFilterChanged());
$('input[name=options]', '#statusRad').change(() => onFilterChanged());