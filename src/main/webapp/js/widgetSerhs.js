var hotelswidget = new (function(window, document, jQuery){
    var domain = 'https://www.123compare.me/v2'
    var datos = {};
    datos.user = 3;
    
    this.init = function (showWidget) {
    	if(showWidget === false){
            hotelswidget.setAnalytics();
        } else {
        	hotelswidget.setAnalytics();
        	datos.domain = domain;
        	datos.hotel = $('#maindesc').find('.content').find('h1').html().substring(0,$('#maindesc').find('.content').find('h1').html().indexOf('<')).trim();
        	datos.rooms = 1;
		    datos.guests = $('a.addroomaction').first().attr('data-adults');
		    datos.start = $('ul.dates li:eq(0)').find('.date-value').html().trim()
		    datos.stop = $('ul.dates li:eq(1)').find('.date-value').html().trim();
		    datos.currency = 'EUR';
		    datos.lang = $('html').attr('lang');
		    datos.defaultLang = "en";
		    datos.price = hotelswidget.findPrice();
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
        converseDate = date.slice(8,10)+'/'+date.slice(5,7)+'/'+date.slice(0,4);
        return converseDate;
    }

    this.findPrice = function(){
    	
    	var cheap_price = '';
        $('div.panelblock').each(function(){
            var price = parseFloat($(this).find('.desde').find('.bestprice').html().replace('.','').replace(',','.'));
            if ((cheap_price === '') || (price < cheap_price)){
                cheap_price = price;
            }
        });
        return cheap_price; 
        
    }
    
    this.diffDate = function (fini,fout){
   		var date1 = new Date(fini.slice(6,10),fini.slice(3,5),fini.slice(0,2));
		var date2 = new Date(fout.slice(6,10),fout.slice(3,5),fout.slice(0,2));
		var timeDiff = Math.abs(date2.getTime() - date1.getTime());
		return diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
    }
    
    this.setAnalytics = function(){
        $('body').append("<script type='text/javascript'>var _paq = _paq || [];_paq.push(['trackPageView']);_paq.push(['enableLinkTracking']);(function() {var u='//www.123compare.me/piwik/';_paq.push(['setTrackerUrl', u+'piwik.php']);_paq.push(['setSiteId', 3]);var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];g.type='text/javascript'; g.async=true; g.defer=true; g.src=u+'piwik.js'; s.parentNode.insertBefore(g,s);})();</script><noscript><p><img src='//www.123compare.me/piwik/piwik.php?idsite=3' style='border:0;' alt='' /></p></noscript>");
    }
    
    this.setJavascript = {
    	setPriceInWidget : function(price,currency){
    		$('#widget123_top_bottom_price').find('#price').html(price.toFixed(2));
    		$('#widget123_top_bottom_price').find('#currency').html(currency);
    	},
    	setWidgetJavascript : function(){
    		/*$('#widget123_top').click(function(){
                if ($('#widget123_middle').is(':hidden')){
                    $('#widget123_middle').slideDown(1000);
                } else {
                    $('#widget123_middle').slideUp(1000);
                }
            });*/
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
            var content_middle = document.getElementById("widget123_middle");
            var count = 0;
            for (var i = 0; i < data.datos.length; i++) {

                var precio_convertido = parseFloat(data.datos[i].price);

                if (((Math.round(price) - 1) < Math.round(precio_convertido) || (Math.round(price) - 1) == Math.round(precio_convertido)) &&  (count < 5)) {
                    count = count + 1;
                    var element = document.createElement("div");
                    element.setAttribute('id', 'element');

                    var image = document.createElement('img');
                    image.setAttribute('src', domain + '/img/' + data.datos[i].site);
                    element.appendChild(image);

                    var span = document.createElement('span');
                    span.setAttribute('class', 'priceWidgetElement');
                    span.innerHTML = precio_convertido.toFixed(2) + '<span class="currency">' + currency + '</span>';
                    element.appendChild(span);

                    var clear = document.createElement('div');
                    clear.setAttribute('style', 'clear:both;');
                    element.appendChild(clear);

                    content_middle.appendChild(element);
                }
            }
            if (count === 0){
                _paq.push(['trackEvent', 'Widget', 'No results', 'No se han mostrado resultados']);
            } else {
            	_paq.push(['trackEvent', 'Widget', 'Show', 'Widget mostrado correctamente']);
                _paq.push(['trackEvent', 'Widget', 'Results', 'Se han mostrado '+count+' resultados']);
                $('#widget123').show();
            }
    	}
    }
    
    this.setUrlHref = function(){
    }


    this.functionReservar = function(pHotelCode, pRateUni, pRedemptionRate, pClearEcertCode){
    }
    
})(window, document,jQuery);
