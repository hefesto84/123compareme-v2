var hotelswidget = new (function(window, document, jQuery){
    var domain = 'https://www.123compare.me/v2'
    var datos = {};
    datos.user = 7;
    
    this.init = function (showWidget) {
    	if(showWidget === false){
            hotelswidget.setAnalytics();
        } else {
        	hotelswidget.setAnalytics();
        	datos.domain = domain;
        	datos.hotel = $('#hotel_details').find('#productNameId').html();
        	datos.rooms = $('select#rooms').val();
		    datos.guests = $('input#occupants').val();
		    datos.start = hotelswidget.dateConverse($('div#checkin-info').find('div.ui-richcal').find('td.selected').attr('class').slice($('div#checkin-info').find('div.ui-richcal').find('td.selected').attr('class').indexOf('-') + 1,$('div#checkin-info').find('div.ui-richcal').find('td.selected').attr('class').indexOf(' ')));
		    datos.stop = hotelswidget.dateConverse($('div#checkout-info').find('div.ui-richcal').find('td.selected').attr('class').slice($('div#checkout-info').find('div.ui-richcal').find('td.selected').attr('class').indexOf('-') + 1,$('div#checkout-info').find('div.ui-richcal').find('td.selected').attr('class').indexOf(' ')));
		    datos.currency = $('em.selling-currency').html().replace('(','').replace(')','');
		    datos.lang = jQuery('html').attr('lang');
		    datos.defaultLang = "en";
		    datos.price = hotelswidget.findPrice();
		    datos.device = 'isDesktop';
		    datos.diffDay = hotelswidget.diffDate(datos.start,datos.stop);
		    
		    var json = JSON.stringify(datos);
		    
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
    	var price = 0;
    	$('.price').each(function(){
    		var precio_encontrado = parseFloat($('.price').html().slice($('.price').html().indexOf('&nbsp;')+18,$('.price').html().indexOf('</span>')).replace('.','').replace(',','.'));
    		if ((price === 0) || (precio_encontrado < price)){
    			price = precio_encontrado;
    		}
    	});
    	return price;
    }
    
    this.diffDate = function (fini,fout){
   		var date1 = new Date(fini.slice(6,10),fini.slice(3,5),fini.slice(0,2));
		var date2 = new Date(fout.slice(6,10),fout.slice(3,5),fout.slice(0,2));
		var timeDiff = Math.abs(date2.getTime() - date1.getTime());
		return diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
    }
    
    this.setAnalytics = function(){
        $('body').append("<script type='text/javascript' id='widget123Analytics'>var _paq = _paq || [];_paq.push(['trackPageView']);_paq.push(['enableLinkTracking']);(function() {var u='//www.123compare.me/piwik/';_paq.push(['setTrackerUrl', u+'piwik.php']);_paq.push(['setSiteId', 7]);var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];g.type='text/javascript'; g.async=true; g.defer=true; g.src=u+'piwik.js'; s.parentNode.insertBefore(g,s);})();</script><noscript id='widget123NoScript'><p><img src='//www.123compare.me/piwik/piwik.php?idsite=7' style='border:0;' alt='' /></p></noscript>");
    }
    
    this.setJavascript = {
    	setPriceInWidget : function(price,currency){
    		$('#widget123_top_bottom_price').find('#price').html(price.toFixed(2));
    		$('#widget123_top_bottom_price').find('#currency').html(currency);
    	},
    	setWidgetJavascript : function(){
    		$('#widget123_top').click(function(){
                if ($('#widget123_middle').is(':hidden')){
                    $('#widget123_middle').slideDown(1000);
                } else {
                    $('#widget123_middle').slideUp(1000);
                }
            });
    		
    		
    		var html = $('#ratesContainer').html()
    		setInterval(function(){ 
    			if($('#ratesContainer').html() != html){
    				html = $('#ratesContainer').html() 
    				hotelswidget.setJavascript.widgetRestart();
    			} 
    		}, 2000);
    		
    	},
    	widgetRestart : function(){
    		$('#widget123').hide();
    		$('#widget123_middle').children().remove()
    		hotelswidget.reInit();
    	},
    	setWidgetData : function(datos,price,currency,diffDay,rooms){
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
                precio_convertido = precio_convertido / diffDay;
                precio_convertido = precio_convertido * rooms

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
    
    this.reInit = function () {
    		var user_re = 7;
        	var hotel_re = $('#hotel_details').find('#productNameId').html();
        	var rooms_re = $('select#rooms').val();
		    var guests_re = $('input#occupants').val();
		    var start_re = hotelswidget.dateConverse($('div#checkin-info').find('div.ui-richcal').find('td.selected').attr('class').slice($('div#checkin-info').find('div.ui-richcal').find('td.selected').attr('class').indexOf('-') + 1,$('div#checkin-info').find('div.ui-richcal').find('td.selected').attr('class').indexOf(' ')));
		    var stop_re = hotelswidget.dateConverse($('div#checkout-info').find('div.ui-richcal').find('td.selected').attr('class').slice($('div#checkout-info').find('div.ui-richcal').find('td.selected').attr('class').indexOf('-') + 1,$('div#checkout-info').find('div.ui-richcal').find('td.selected').attr('class').indexOf(' ')));
		    var currency_re = $('em.selling-currency').html().replace('(','').replace(')','');
		    var lang_re = jQuery('html').attr('lang');
		    var price_re = hotelswidget.findPrice();
		    var device_re = 'isDesktop';
		    var diffDay_re = hotelswidget.diffDate(start_re,stop_re);
		    
		    var url_repost = domain + '/api/prices?currency='+ currency_re + '&base=' + price_re + '&code=' + user_re + '&hotel=' + encodeURI(hotel_re) + '&rooms=' + rooms_re + '&guests=' + guests_re + '&fin=' + start_re + '&fout=' + stop_re + '&lang=' + lang_re;
		    

    		$('#widget123_top_bottom_price').find('#price').html(price_re.toFixed(2));
    		$('#widget123_top_bottom_price').find('#currency').html(currency_re);
    		
		    getDatosWidget123Room(url_repost,device_re,price_re,currency_re,lang_re,diffDay_re,rooms_re)
		    
    }
			
    
})(window, document,jQuery);
