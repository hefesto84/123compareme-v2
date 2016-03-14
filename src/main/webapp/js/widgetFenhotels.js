var hotelswidget = new (function(window, document, $){
    var domain = 'https://www.123compare.me/v2'
    var datos = {};
    datos.user = 10;
    
    this.init = function (showWidget,properties) {
    	if(showWidget === false){
            hotelswidget.setAnalytics();
        } else {
        	hotelswidget.setAnalytics();
        	datos.domain = domain;
        	datos.hotel = hotelswidget.getHotelName();
        	datos.rooms = 1;
		    datos.guests = 2;
		    datos.start = hotelswidget.dateConverse($('#check_in').val());
		    datos.stop = hotelswidget.dateConverse($('#check_out').val());
		    datos.currency = $('div.small-8.text-right').find('span.bloque.con_strong.destacadoReserva').html().trim().slice($('div.small-8.text-right').find('span.bloque.con_strong.destacadoReserva').html().trim().indexOf(" ") + 1,$('div.small-8.text-right').find('span.bloque.con_strong.destacadoReserva').html().trim().indexOf(" ") + 4);
		    datos.lang =  $('html').attr('lang');
		    datos.defaultLang = 'en';
		    datos.price = parseFloat($('div.small-8.text-right').find('span.bloque.con_strong.destacadoReserva').html().trim().slice(0,$('div.small-8.text-right').find('span.bloque.con_strong.destacadoReserva').html().trim().indexOf(" ")).replace('.','').replace(',','.'));;
		    datos.device = 'isDesktop';
		    datos.diffDay = hotelswidget.diffDate(datos.start,datos.stop);
		    
		    var json = JSON.stringify(datos);
		    
		    console.log(json);
		    
		    jQuery.ajax({
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
        $('body').append("<script type='text/javascript'>var _paq = _paq || [];_paq.push(['trackPageView']);_paq.push(['enableLinkTracking']);(function() {var u='//www.123compare.me/piwik/';_paq.push(['setTrackerUrl', u+'piwik.php']);_paq.push(['setSiteId', 10]);var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];g.type='text/javascript'; g.async=true; g.defer=true; g.src=u+'piwik.js'; s.parentNode.insertBefore(g,s);})();</script><noscript><p><img src='//www.123compare.me/piwik/piwik.php?idsite=10' style='border:0;' alt='' /></p></noscript>");
    }
    
    this.setJavascript = {
    	setPriceInWidget : function(price,currency){
    		$('#widget123_top_price_price').html(price.toFixed(2));
    		$('#widget123_top_price_currency').html(currency);
    		
    	},
    	setWidgetJavascript : function(){
    		
    		var widget_responsive = $('#widget123_div').html();
    		$('#widget123_div').remove();
    		$(widget_responsive).insertAfter('#turesumen');
    		
    		/*$('#widget123_responsive').click(function(){
                $('#widget123_popup').show();
                _paq.push(['trackEvent', 'Widget', 'Click', 'Widget clicado']);
            });
            $('#widget123_popup_content_top_close').click(function(){
                $('#widget123_popup').hide();
            });*/
    	},
    	setWidgetData : function(datos,price,currency,diffDay){
    		data = datos;
            if(data.currency === 'XXX'){
            	_paq.push(['trackEvent', 'Widget', 'No currency', 'Currency not available']);
                return 0;
            }
            if (data.datos.length == 0){
                _paq.push(['trackEvent', 'Widget', 'No results', 'Widget not showed (No data available)']);
                return 0;
            }
          
            var content_middle = document.getElementById("widget123_bottom");
            var count = 0;
            for (var j = 0; j < data.datos.length; j++) {

                var precio_convertido = parseFloat(data.datos[j].price);

                if (((price - 1) < precio_convertido) || ((price - 1) == precio_convertido)){
                	count = count + 1;
                	
                	var element = document.createElement("div");
                    element.setAttribute('id', 'element');
                    element.setAttribute('data-element',count);
                    element.setAttribute('style','top:'+((count - 1) * 40)+'px');
                    
                    var element_left = document.createElement("div");
                    element_left.setAttribute('id', 'element_left'); 
                    element_left.innerHTML = data.datos[j].site.replace('.png','') + ".com";
                    element.appendChild(element_left);
                    
                    var element_right = document.createElement("div");
                    element_right.setAttribute('id', 'element_right'); 
                    element_right.innerHTML = '<span id="element_right_price">' + precio_convertido.toFixed(2) + '</span>&nbsp;<span id="element_right_currency">' + currency + '</span>';
                    element.appendChild(element_right);
                    
                    var clear = document.createElement('div');
                    clear.setAttribute('style', 'clear:both;');
                    element.appendChild(clear);

                    content_middle.appendChild(element);
                }
            }
            if (count === 0){
            	 _paq.push(['trackEvent', 'Widget', 'No results', 'No results shown']);
            } else {
            	_paq.push(['trackEvent', 'Widget', 'Show', 'Widget correctly showed']);
                _paq.push(['trackEvent', 'Widget', 'Results', 'Shown '+count+' results']);
                if (count > 2){
                	$('#widget123_bottom').css({
                    	'height' : 90
                    });
                	$('#widget123').toggle('slide',{ direction: "right" }, 1000, function(){
                		hotelswidget.setAnimationWidget(count,5);
                	});
                } else {
                	$('#widget123_bottom').css({
                    	'height' : ((40 * count) + 10)
                    });
                	$('#widget123').toggle('slide',{ direction: "right" }, 1000);
                }
            }
    	}
    }
    
    this.setUrlHref = function(){
    }


    this.functionReservar = function(pHotelCode, pRateUni, pRedemptionRate, pClearEcertCode){
    }
    
    this.getHotelName = function(){
    	var name = '';
    	if ($('select#hoteldestino').length){
    		name = $('select#hoteldestino option[selected]').html().trim().replace('★ ','').trim();
    	} else {
    		name = $('header').find('nav.nav_large').find('img').attr('alt');
    	}
    	name = normalize(name);
    	return name;
    }
    
    this.setAnimationWidget = function(elements,time){
    	time = time * 1000;
    	
    	setInterval(function(){
    		$('div#element').each(function(index,element){
				if ($(element).attr('data-element') === '1'){
					$(element).animate({
						'top' : '-40px'
					},1000, function(){
						$(this).css({
							'top': (elements - 1) * 40
						});
						$(this).attr('data-element',elements);
					});
				} else {
					$(element).animate({
						'top' : ((parseInt($(element).attr('data-element')) - 2) * 40)
					},1000, function(){
						$(this).attr('data-element', parseInt($(element).attr('data-element')) - 1);
					});
				}
			});
		},time);
    	
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

    
})(window, document, $);
