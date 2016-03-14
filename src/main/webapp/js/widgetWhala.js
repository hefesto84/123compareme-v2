var hotelswidget = new (function(window, document, jQuery){
    var domain = 'https://www.123compare.me/v2'
    var datos = {};
    datos.user = 8;
    
    this.init = function (showWidget,properties) {
    	if(showWidget === false){
            hotelswidget.setAnalytics();
        } else {
        	hotelswidget.setAnalytics();
        	datos.domain = domain;
        	datos.hotel = data.establishment.ticker;
        	datos.rooms = 1;
		    datos.guests = 2;
		    datos.start = hotelswidget.dateConverse(properties.checkin);
		    datos.stop = hotelswidget.dateConverse(properties.checkout);
		    datos.currency = properties.currency;
		    datos.lang =  properties.locale;
		    datos.defaultLang = 'en';
		    datos.price = properties.price;
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
   		var date1 = new Date(fini.slice(6,10),(parseInt(fini.slice(3,5)) - 1),fini.slice(0,2));
		var date2 = new Date(fout.slice(6,10),(parseInt(fout.slice(3,5)) - 1),fout.slice(0,2));
		var timeDiff = Math.abs(date2.getTime() - date1.getTime());
		return diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
    }
    
    this.setAnalytics = function(){
        $('body').append("<script type='text/javascript'>var _paq = _paq || [];_paq.push(['trackPageView']);_paq.push(['enableLinkTracking']);(function() {var u='//www.123compare.me/piwik/';_paq.push(['setTrackerUrl', u+'piwik.php']);_paq.push(['setSiteId', 8]);var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];g.type='text/javascript'; g.async=true; g.defer=true; g.src=u+'piwik.js'; s.parentNode.insertBefore(g,s);})();</script><noscript><p><img src='//www.123compare.me/piwik/piwik.php?idsite=8' style='border:0;' alt='' /></p></noscript>");
    }
    
    this.setJavascript = {
    	setPriceInWidget : function(price,currency){
    		$('#widget123_responsive_left_price').find('.price').html(Math.floor(price));
    		$('#widget123_responsive_left_price').find('.currency').html(price.toFixed(2).split(".")[1]+currency);
    		
    		$('#widget123_popup_content_ota_right_price').html(price.toFixed(2));
            $('#widget123_popup_content_ota_right_currency').html(currency);
    	},
    	setWidgetJavascript : function(){
    		
    		var interval = setInterval(function(){
    			if ($('.col-md-8').length === 0) return;
    			clearInterval(interval);
    			var widget_responsive = $('#widget123_responsive_div').html();
        		$('#widget123_responsive_div').remove();
        		$('.col-md-8').prepend(widget_responsive);
        		$('#widget123_responsive').click(function(){
                    $('#widget123_popup').show();
                    _paq.push(['trackEvent', 'Widget', 'Click', 'Widget clicked']);
                });
                $('#widget123_popup_content_top_close').click(function(){
                    $('#widget123_popup').hide();
                });
    		},100);
    		
    	},
    	setWidgetData : function(datos,price,currency,diffDay){
    		data = datos;
            $('.widget123_content_loading').hide();
            $('#widget123_popup_loading_text').hide();
            if(data.currency === 'XXX'){
            	_paq.push(['trackEvent', 'Widget', 'No currency', 'Currency not available']);
            	$('#widget123_popup_content_middle').append("<div id='no_otas'>Comparison not available in this currency</div>");
            	return 0;
            }
            if (data.datos.length == 0){
                _paq.push(['trackEvent', 'Widget', 'No results', 'Widget not showed (No data available)']);
                $('#widget123_popup_content_middle').append("<div id='no_otas'>Sorry, we couldn\'t complete the search for these dates</div>");
                return 0;
            }
          
            var content_middle_popup = document.getElementById("widget123_popup_content_middle");
            var count = 0;
            for (var j = 0; j < data.datos.length; j++) {

                var precio_convertido = parseFloat(data.datos[j].price);
                precio_convertido = precio_convertido / diffDay;

                if (((Math.round(price) - 1) < Math.round(precio_convertido) || (Math.round(price) - 1) == Math.round(precio_convertido))){
                	count = count + 1;
                	var element = document.createElement("div");
                    element.setAttribute('id', 'element_popup');

                    var image = document.createElement('img');
                    image.setAttribute('src', domain + '/img/' + data.datos[j].site);
                    element.appendChild(image);

                    var span = document.createElement('span');
                    span.setAttribute('class', 'priceWidgetElement');
                    span.innerHTML = precio_convertido.toFixed(2) + ' ' + currency;
                    element.appendChild(span);

                    var clear = document.createElement('div');
                    clear.setAttribute('style', 'clear:both;');
                    element.appendChild(clear);

                    content_middle_popup.appendChild(element);
                }
            }
            if (count === 0){
            	$('#widget123_popup_content_middle').append("<div id='no_otas'>Sorry, we couldn\'t complete the search for these dates</div>");
                _paq.push(['trackEvent', 'Widget', 'No results', 'No results shown']);
            } else {
            	_paq.push(['trackEvent', 'Widget', 'Show', 'Widget correctly showed']);
                _paq.push(['trackEvent', 'Widget', 'Results', 'Shown '+count+' results']);
            }
    	}
    }
    
    this.setUrlHref = function(){
    }


    this.functionReservar = function(pHotelCode, pRateUni, pRedemptionRate, pClearEcertCode){
    }
    
    this.getHotelName = function(){
    	var url = window.location.href;
    	var patt = /whalabeach/g;
    	var hotel = '';
    	if (patt.test(url)){
    		hotel = 'Whala!Beach';
    	} else {
    		hotel = 'Whala!Bavaro';
    	}
    	return hotel;
    }
    
})(window, document,jQuery);
