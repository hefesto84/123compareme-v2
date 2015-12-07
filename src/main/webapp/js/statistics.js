/**
 * Graphics generation for statistics download
 * Created by Sergio Zambrano Delfa <sergio.zambrano@gmail.com> on 6/12/15.
 */

window.onload = function() {

    $(function() {
        var dateIni = new Date();
        if($("#dateIni").val().length < 2){
            $("#dateIni").val(dateIni.getDate()+"/"+(dateIni.getMonth()+1)+"/"+dateIni.getFullYear());
        }
        $("#dateIni").datepicker();
    });

    $(function() {
        var dateEnd = new Date();
        if($("#dateEnd").val().length < 2){
            $("#dateEnd").val((dateEnd.getDate()+1)+"/"+(dateEnd.getMonth()+1)+"/"+dateEnd.getFullYear());
        }
        $("#dateEnd").datepicker();
    });

    var graph1Data = {
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

    var graph2Data = {
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

    var graph3Data = {
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

    var graph4Data = {
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

    var ctxGraph1 = document.getElementById("graph1").getContext("2d");
    window.graph1Line = new Chart(ctxGraph1).Line(graph1Data, {responsive: true});
    $.getJSON(
        "../api/otasgraph1",
        $('#dataFilter').serialize(),
        function (data) {
            for (i = 0; i < data.otas.length; i++) {
                var quality = [];
                quality.push(data.otas[i].quality);
                window.graph1Line.addData(quality, data.otas[i].name);
            }
        }
    );

    var ctxGraph2 = document.getElementById("graph2").getContext("2d");
    window.graph2Line = new Chart(ctxGraph2).Line(graph2Data, {responsive: true});
    $.getJSON(
        "../api/otasgraph2",
        $('#dataFilter').serialize(),
        function (data) {
            for (i = 0; i < data.otas.length; i++) {
                var quality = [];
                quality.push(data.otas[i].quality);
                window.graph2Line.addData(quality, data.otas[i].name);
            }
        }
    );

    var ctxGraph3 = document.getElementById("graph3").getContext("2d");
    window.graph3Line = new Chart(ctxGraph3).Line(graph3Data, {responsive: true});
    $.getJSON(
        "../api/otasgraph3",
        $('#dataFilter').serialize(),
        function (data) {
            for (i = 0; i < data.otas.length; i++) {
                var quality = [];
                quality.push(data.otas[i].quality);
                window.graph3Line.addData(quality, data.otas[i].name);
            }
        }
    );

    var ctxGraph4 = document.getElementById("graph4").getContext("2d");
    window.graph4Line = new Chart(ctxGraph4).Line(graph4Data, {responsive: true});
    $.getJSON(
        "../api/otasgraph4",
        $('#dataFilter').serialize(),
        function (data) {
            for (i = 0; i < data.otas.length; i++) {
                var quality = [];
                quality.push(data.otas[i].quality);
                window.graph4Line.addData(quality, data.otas[i].name);
            }
        }
    );
};
