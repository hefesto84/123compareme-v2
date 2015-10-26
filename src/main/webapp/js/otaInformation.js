var barChartData = { 
		labels: ["Expedia","Booking","Hotels","Venere"],
		datasets : [{
			fillColor : "rgba(220,220,220,0.5)",
			strokeColor : "rgba(220,220,220,0.8)",
			highlightFill: "rgba(200,220,220,0.75)",
			highlightStroke: "rgba(220,220,220,1)", 
			
			data : ["14.0","29.0","34.0","86.0"]
		}] 
}; 

window.onload = function(){
	var ctx = document.getElementById("canvas").getContext("2d");
	window.myBar = new Chart(ctx).Bar(barChartData, {responsive : true});
}