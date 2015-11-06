var otaData = { 
		labels : [],
		    datasets: [
		        {
		        	label: "My First dataset",
		            fillColor: "rgba(100,220,220,0.2)",
		            strokeColor: "rgba(220,220,220,1)",
		            pointColor: "rgba(220,220,220,1)",
		            pointStrokeColor: "#fff",
		            pointHighlightFill: "#fff",
		            pointHighlightStroke: "rgba(220,220,220,1)"
		        }
		    ]
		    
}; 

var priceData = { 
		labels : [],
		    datasets: [
		        {
		        	label: "My First dataset",
		            fillColor: "rgba(100,220,220,0.2)",
		            strokeColor: "rgba(220,220,220,1)",
		            pointColor: "rgba(220,220,220,1)",
		            pointStrokeColor: "#fff",
		            pointHighlightFill: "#fff",
		            pointHighlightStroke: "rgba(220,220,220,1)"
		        }
		    ]
		    
}; 

var evolutionData = {
	    labels: ["Evolution"],
	    datasets: [
	        {
	            label: "My First dataset",
	            fillColor: "rgba(220,220,220,0.2)",
	            strokeColor: "rgba(220,220,220,1)",
	            pointColor: "rgba(220,220,220,1)",
	            pointStrokeColor: "#fff",
	            pointHighlightFill: "#fff",
	            pointHighlightStroke: "rgba(220,220,220,1)",
	            data: [65]
	        }
	    ]
	};

window.onload = function(){
	var ctxOtas = document.getElementById("cnvOtas").getContext("2d");
	window.otaBar = new Chart(ctxOtas).Bar(otaData, {responsive : true});
	$.getJSON( "../api/otasinfo", function( data ) {
		for(i = 0; i<data.otas.length; i++){
			var quality = [];
			quality.push(data.otas[i].quality);
			window.otaBar.addData(quality, data.otas[i].name);
		}
	});
	
	/*
	var ctxPrices = document.getElementById("cnvPrices").getContext("2d");
	window.priceBar = new Chart(ctxPrices).Bar(priceData, {responsive : true});
	$.getJSON( "../api/pricesinfo", function( data ) {
		for(i = 0; i<data.prices.length; i++){
			var times = [];
			times.push(data.prices[i].times);
			window.priceBar.addData(times, data.prices[i].name);
		}
	});
	
	
	var ctxEvolution = document.getElementById("cnvEvolution").getContext("2d");
	window.evolutionLine = new Chart(ctxEvolution).Line(evolutionData, {responsive : true});
	for(i=0; i<10; i++){
		window.evolutionLine.addData(Math.floor((Math.random() * 100) + 1),i);
	}
	*/
	//window.evolutionLine.addData([10,11,2,31,40,12,64,72,4,33],"Evolution");
	//$.getJSON( "../api/pricesinfo", function( data ) {
	//	for(i = 0; i<data.prices.length; i++){
	//		var times = [];
	//		times.push(data.prices[i].times);
	//		window.evolutionLine.addData(times, data.prices[i].name);
	//	}
	//});
}