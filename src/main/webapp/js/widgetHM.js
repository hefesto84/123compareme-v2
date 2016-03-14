var hotelswidget = new (function(window, document, jQuery){
    var domain = 'https://www.123compare.me/v2'
    var datos = {};
    datos.user = 8;
    
    this.init = function (showWidget,properties) {
    	if(showWidget === false){
            hotelswidget.setAnalytics();
        } else {
        	var type = hotelswidget.typeHotel();
        	hotelswidget.setAnalytics();
        	datos.domain = domain;
		    if (type === 'Whala'){
		    	datos.hotel = $( "[name='hotel'] option:selected" ).val();
        		datos.start = hotelswidget.dateConverse($('#from').val());
        		datos.stop = hotelswidget.dateConverse($('#to').val());
        	} else {
        		datos.hotel = $( "#selectorhoteles option:selected" ).html();
        		datos.start = hotelswidget.dateConverse($('#fini').val());
        		datos.stop = hotelswidget.dateConverse($('#fout').val());
        	}
        	datos.rooms = 1;
		    datos.guests = parseInt($( "[name='adultos'] option:selected" ).html());
		    datos.currency = properties.currency;
		    datos.lang = $('.social-language').find('.language').html().toLowerCase();
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
    		$('#widget123_top_bottom_price').find('#price').html(price.toFixed(2));
    		$('#widget123_top_bottom_price').find('#currency').html(currency);
    		
    		$('#widget123_responsive_left_price').find('.price').html(Math.floor(price));
    		$('#widget123_responsive_left_price').find('.currency').html((price + "").split(".")[1]+currency);
    		
    		$('#widget123_popup_content_ota_right_price').html(price.toFixed(2));
            $('#widget123_popup_content_ota_right_currency').html(currency);
    	},
    	setWidgetJavascript : function(){
    		
    		var widget_responsive = $('#widget123_responsive_div').html();
    		$(widget_responsive).insertBefore('.wrap-frame');
    		
    		var widget_width = $('.wrap-cols').find('.col-text').width() + parseInt($('.wrap-cols').find('.col-text').css('padding-left')) + parseInt($('.wrap-cols').find('.col-text').css('padding-right'));
    		var position_right = $('body').width() - ($('.wrap-cols').find('.col-text').offset().left + widget_width);
    		var widget_top = $('.wrap-cols').find('.col-text').height() + parseInt($('.wrap-cols').find('.col-text').css('padding-top')) + parseInt($('.wrap-cols').find('.col-text').css('padding-bottom')) + $('.wrap-cols').find('.col-text').offset().top + 10;
    		$('#widget123').css({
    			'width' : widget_width,
    			'right' : position_right,
    			'top' : widget_top
    		});
    		$('#widget123_top').click(function(){
                if ($('#widget123_middle').is(':hidden')){
                    $('#widget123_middle').slideDown(1000);
                } else {
                    $('#widget123_middle').slideUp(1000);
                }
            });
    		$('#widget123_responsive').click(function(){
                $('#widget123_popup').show();
                _paq.push(['trackEvent', 'Widget', 'Click', 'Widget clicked']);
            });
            $('#widget123_popup_content_top_close').click(function(){
                $('#widget123_popup').hide();
            });
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
            var content_middle = document.getElementById("widget123_middle");
            var count = 0;
            for (var i = 0; i < data.datos.length; i++) {

                var precio_convertido = parseFloat(data.datos[i].price);
                precio_convertido = precio_convertido / diffDay;

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
            
            var content_middle_popup = document.getElementById("widget123_popup_content_middle");
            for (var j = 0; j < data.datos.length; j++) {

                var precio_convertido = parseFloat(data.datos[j].price);
                precio_convertido = precio_convertido / diffDay;

                if (((Math.round(price) - 1) < Math.round(precio_convertido) || (Math.round(price) - 1) == Math.round(precio_convertido))){
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
            	_paq.push(['trackEvent', 'Widget', 'Show', 'Widget mostrado correctamente']);
                _paq.push(['trackEvent', 'Widget', 'Results', 'Shown '+count+' results']);
                $('#widget123').show();
            }
    	}
    }
    
    this.setUrlHref = function(){
    }

    this.functionReservar = function(pHotelCode, pRateUni, pRedemptionRate, pClearEcertCode){
    }
    
    this.typeHotel = function(){
    	type = 'HM';
    	var url = window.location.href;
    	var patt = /whala/g;
    	if (patt.test(url)){
    		type = 'Whala';
    	}
    	return type;
    }
    
})(window, document,jQuery);
