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


window.onload = function(){
	var ctxPrices = document.getElementById("cnvPrices").getContext("2d");
	window.priceBar = new Chart(ctxPrices).Bar(priceData, {responsive : true});
	$.getJSON( "../api/pricesinfo", function( data ) {
		for(i = 0; i<data.prices.length; i++){
			var times = [];
			times.push(data.prices[i].times);
			window.priceBar.addData(times, data.prices[i].name);
		}
	});
}