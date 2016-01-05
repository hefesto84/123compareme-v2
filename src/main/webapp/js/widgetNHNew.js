var hotelswidget = new (function(window, document, $){
    var domain = 'https://www.123compare.me/v2'
    var datos = {};
    datos.user = 2;
    
    this.init = function (showWidget) {
    	if(showWidget === false){
            hotelswidget.setAnalytics();
        } else {
        	hotelswidget.setAnalytics();
        	datos.domain = domain;
        	datos.hotel = normalize($('span.nombre_hotel').html());
        	datos.start = hotelswidget.dateConverse($('#fini_dato_barra').val());
        	datos.stop = hotelswidget.dateConverse($('#fout_dato_barra').val());
        	datos.rooms = $('#barra_rooms').val();
		    datos.guests = $('#clients_barra_dato').val();
		    datos.currency = 'EUR';
		    datos.lang = $('html').attr('lang');
		    datos.defaultLang = 'en';
		    if($('html').attr('lang') === 'en'){
		    	var price = parseFloat($('.precio_hotel_detalle').find('span').html());
            } else {
            	var price = parseFloat($('.precio_hotel_detalle').find('span').html().replace('.','').replace(',','.'));
            }
		    datos.price = Math.round(price);
		    datos.device = 'isMobile';
		    datos.diffDay = hotelswidget.diffDate(datos.start,datos.stop);
		    
		    var json = JSON.stringify(datos);
		    
		    console.log(json);
		    
		    $.ajax({
				 method: "POST",
				 url: "https://www.123compare.me/v2/widget/widget",
				 data: { datos: json },
				 dataType: "script",
				 success: function(msg) {
				 }
			});
				
		}
			
	}

    this.dateConverse = function(date){
        converseDate = date.slice(0,2)+'/'+date.slice(3,5)+'/'+date.slice(6,10);
        return converseDate;
    }
    
    this.diffDate = function (fini,fout){
   		var date1 = new Date(fini.slice(6,10),fini.slice(3,5),fini.slice(0,2));
		var date2 = new Date(fout.slice(6,10),fout.slice(3,5),fout.slice(0,2));
		var timeDiff = Math.abs(date2.getTime() - date1.getTime());
		return diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
    }
    
    this.setAnalytics = function(){
        $('body').append("<script type='text/javascript'>var _paq = _paq || [];_paq.push(['trackPageView']);_paq.push(['enableLinkTracking']);(function() {var u='//www.123compare.me/piwik/';_paq.push(['setTrackerUrl', u+'piwik.php']);_paq.push(['setSiteId', 2]);var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];g.type='text/javascript'; g.async=true; g.defer=true; g.src=u+'piwik.js'; s.parentNode.insertBefore(g,s);})();</script><noscript><p><img src='//www.123compare.me/piwik/piwik.php?idsite=2' style='border:0;' alt='' /></p></noscript>");
    }
    
    this.setJavascript = {
    	setPriceInWidget : function(price,currency){
    		
    		$('#widget123_left_price').find('#price').html(price);
    		$('#widget123_left_price').find('#currency').html(currency);
    		
    	},
    	setWidgetJavascript : function(){
    		
    		var widget = $('#widget123');
    		$(widget).insertBefore('.contenido_detalle_hoteles_relative');
    		
    	},
    	setWidgetData : function(datos,price,currency,diffDay){
    		data = datos;
            if(data.currency === 'XXX'){
            	_paq.push(['trackEvent', 'Widget', 'No currency', 'Currency no disponible']);
            	return 0;
            }
            if (data.datos.length == 0){
                _paq.push(['trackEvent', 'Widget', 'No results', 'No se ha mostrado el widget por que no hay datos']);
                return 0;
            }
            var content = document.getElementById("widget123_right_otas");
            var count = 0;
            for (var i = 0; i < data.datos.length; i++) {

                var precio_convertido = parseFloat(data.datos[i].price);

                if (((Math.round(price) - 1) < Math.round(precio_convertido) || (Math.round(price) - 1) == Math.round(precio_convertido)) &&  (count < 5)) {
                    count = count + 1;
                    
                    var element = document.createElement("div");
                    element.setAttribute('class','ota');
                    element.setAttribute('data-ota',count);
                    
                    var name = document.createElement("div");
                    name.setAttribute('id','ota_name');
                    name.innerHTML = data.datos[i].site.replace('.png','');
                    element.appendChild(name)
                    
                    var price = document.createElement("div");
                    price.setAttribute('id','ota_price');
                    price.innerHTML = '<span id="ota_price_price">' + precio_convertido + '</span>&nbsp;<span id="ota_price_currency">' + currency + '</span>';
                    element.appendChild(price);
                    
                    content.appendChild(element);
                
                }
            }
            
            
            if (count === 0){
                _paq.push(['trackEvent', 'Widget', 'No results', 'No se han mostrado resultados']);
            } else {
            	_paq.push(['trackEvent', 'Widget', 'Show', 'Widget mostrado correctamente']);
                _paq.push(['trackEvent', 'Widget', 'Results', 'Se han mostrado '+count+' resultados']);
                if (count > 1){
                	$('#widget123').slideDown(1000);
                	hotelswidget.setJavascript.setAnimationWidget();
                } else {
                	$('#widget123').slideDown(1000);
                	$( "[data-ota = '1']" ).show();
                }
            }
    	},
    	setAnimationWidget : function(){
    		function animacion(){
	           $( "[data-ota = '1']" ).fadeIn( 1000, function() {
	               var t = setInterval(function(){
	                   $( "[data-ota = '1']" ).fadeOut( 1000, function() {
	                       clearInterval(t);
	                       $( "[data-ota = '2']" ).fadeIn( 1000, function() {
	                            var d = setInterval(function(){
	                                $( "[data-ota = '2']" ).fadeOut( 1000, function() {
	                                    clearInterval(d);
	                                    animacion();
	                                });
	                           },5000);
	                       });
	                   });
	               },5000);
	           });
    		}
    		animacion();
    	}
    }
    
    this.setUrlHref = function(){
    }

    this.functionReservar = function(pHotelCode, pRateUni, pRedemptionRate, pClearEcertCode){
    }
    
    var normalize = (function() {
  	  var from = "ÃÀÁÄÂÈÉËÊÌÍÏÎÒÓÖÔÙÚÜÛãàáäâèéëêìíïîòóöôùúüûÑñÇç", 
  	      to   = "AAAAAEEEEIIIIOOOOUUUUaaaaaeeeeiiiioooouuuunncc",
  	      mapping = {};
  	 
  	  for(var i = 0, j = from.length; i < j; i++ )
  	      mapping[ from.charAt( i ) ] = to.charAt( i );
  	 
  	  return function( str ) {
  	      var ret = [];
  	      for( var i = 0, j = str.length; i < j; i++ ) {
  	          var c = str.charAt( i );
  	          if( mapping.hasOwnProperty( str.charAt( i ) ) )
  	              ret.push( mapping[ c ] );
  	          else
  	              ret.push( c );
  	      }      
  	      return ret.join( '' );
  	  }
  	 
  	})();
    
})(window, document,$);
