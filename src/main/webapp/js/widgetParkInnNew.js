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
        	datos.hotel = hotelswidget.hotelName(jQuery('.innername').find("a").text());
        	datos.rooms = jQuery('[name="rateSearchForm.numberRooms"]').val();
		    datos.guests = jQuery('[name="occupancyForm[0].numberAdults"]').val();
		    datos.start = hotelswidget.dateConverse(jQuery('[name="rateSearchForm.checkinDate"]').val());
		    datos.stop = hotelswidget.dateConverse(jQuery('[name="rateSearchForm.checkoutDate"]').val());
		    datos.currency = jQuery('.ratecurrency').first().text().trim();
		    datos.lang = jQuery('html').attr('lang');
		    datos.defaultLang = "en";
		    datos.price = hotelswidget.priceConverse(jQuery('.rateamount').first().text().trim(), datos.lang);
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
        converseDate = date.slice(3,5)+'/'+date.slice(0,2)+'/'+date.slice(6,10);
        return converseDate;
    }

    this.priceConverse = function(price,lang){
        if(lang == 'en' || lang == 'en_UK'){
            price = price.replace(',','');
        } else{
            price = price.replace(',','.');
        }
        if (isNaN(price)){
            return parseFloat(price.slice(1)).toFixed(2);
        } else {
            return parseFloat(price).toFixed(2);
        }
    }
    
    this.diffDate = function (fini,fout){
   		var date1 = new Date(fini.slice(6,10),fini.slice(3,5),fini.slice(0,2));
		var date2 = new Date(fout.slice(6,10),fout.slice(3,5),fout.slice(0,2));
		var timeDiff = Math.abs(date2.getTime() - date1.getTime());
		return diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
    }
    
    this.setAnalytics = function(){
        jQuery('body').append("<script type='text/javascript'>var _paq = _paq || [];_paq.push(['trackPageView']);_paq.push(['enableLinkTracking']);(function() {var u='//www.123compare.me/piwik/';_paq.push(['setTrackerUrl', u+'piwik.php']);_paq.push(['setSiteId', 1]);var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];g.type='text/javascript'; g.async=true; g.defer=true; g.src=u+'piwik.js'; s.parentNode.insertBefore(g,s);})();</script><noscript><p><img src='//www.123compare.me/piwik/piwik.php?idsite=1' style='border:0;' alt='' /></p></noscript>");
    }
    
    this.setJavascript = {
    	setPriceInWidget : function(price,currency){
    		if(lang == 'en' || lang == 'en_UK'){
                jQuery('#widget123_top_price').find('#price').html((price.toFixed(2)).replace(',','.'));
                jQuery('#widget123_popup_content_parkinn_right_price').html((price.toFixed(2)).replace(',','.'));
            } else{
                jQuery('#widget123_top_price').find('#price').html((price.toFixed(2)).replace('.',','));
                jQuery('#widget123_popup_content_parkinn_right_price').html((price.toFixed(2)).replace('.',','));
            }
            jQuery('#widget123_top_price').find('#currency').html(currency);
            jQuery('#widget123_popup_content_parkinn_right_currency').html(currency);
    	},
    	setWidgetJavascript : function(){
    		var top_widget = jQuery('.panelHeaderWrapper').offset().top;
            var left_widget = jQuery('#searchPanel').offset().left + jQuery('#searchPanel').width() + 2;

            jQuery('#widget123').css({
                'top' : top_widget,
                'left' : left_widget
            });


            var screen_width = jQuery(window).width();
            var screen_height = jQuery(window).height();

            var popup_width = jQuery('#widget123_popup_content').width();
            var popup_height = jQuery('#widget123_popup_content').height();
            jQuery('#widget123_popup_content').css({
                'left' : ((screen_width - popup_width)/2)
            });


            jQuery('#widget123').click(function(){
            	var lForm = $('hiddenRateReviewForm');
                jQuery(lForm).prepend('<input type="hidden" name="facilitatorId" value="REZIDORRATETABLE">');
                jQuery(lForm).prepend('<input type="hidden" name="icid" value="co_booking_direction">');
                jQuery('#widget123_popup').show();
                _paq.push(['trackEvent', 'Widget', 'Click', 'Widget clicado']);
            });
            jQuery('#widget123_popup_content_top_close').click(function(){
                jQuery('#widget123_popup').hide();
            });

            _paq.push(['trackEvent', 'Widget', 'Show', 'Widget mostrado correctamente']);
    	},
    	setWidgetData : function(datos,price,currency,diffDay){
    		var href = hotelswidget.setUrlHref();
            jQuery('.widget123_content_loading').hide();
            jQuery('#widget123_popup_loading_text').hide();
            data = datos;
            if(data.currency === 'XXX'){
            	_paq.push(['trackEvent', 'Widget', 'No currency', 'Currency no disponible']);
                jQuery('#widget123_popup_content_middle').append("<div id='no_otas'>Comparison not available in this currency</div>");
                return 0;
            }
            if ((data.datos === null) || (data.datos.length === 0)){
                _paq.push(['trackEvent', 'Widget', 'No results', 'No se ha mostrado el widget por que no hay datos']);
                jQuery('#widget123_popup_content_middle').append("<div id='no_otas'>Sorry, we couldn\'t complete the search for these dates</div>");
                return 0;
            }
            var content_middle = document.getElementById("widget123_popup_content_middle");
            var count = 0;
            for (var i = 0; i < data.datos.length; i++) {

                var precio_convertido = parseFloat(data.datos[i].price);
                
                precio_convertido = precio_convertido / diffDay;

                if (((price - 1) < precio_convertido || (price - 1) == precio_convertido) &&  (count < 5)) {
                    count = count + 1;
                    var element = document.createElement("div");
                    element.setAttribute('id', 'element');

                    var image = document.createElement('img');
                    image.setAttribute('src', domain + '/img/' + data.datos[i].site);
                    element.appendChild(image);

                    var span = document.createElement('span');
                    span.setAttribute('class', 'priceWidgetElement');
                    span.innerHTML = precio_convertido.toFixed(2) + ' ' + currency;
                    element.appendChild(span);

                    var clear = document.createElement('div');
                    clear.setAttribute('style', 'clear:both;');
                    element.appendChild(clear);

                    content_middle.appendChild(element);
                }
            }
            if (count === 0){
                jQuery('#widget123_popup_content_middle').append("<div id='no_otas'>Sorry, we couldn\'t complete the search for these dates</div>");
                _paq.push(['trackEvent', 'Widget', 'No results', 'No se han mostrado resultados']);
            } else {
                _paq.push(['trackEvent', 'Widget', 'Results', 'Se han mostrado '+count+' resultados']);
            }
            jQuery('#boton_reservar_widget').attr('href', href);
    	}
    }
    
    this.hotelName = function(name){
    	hotelName = name.replace('&','');
        return hotelName;
    }
    
    this.setUrlHref = function(){
    	var script = jQuery('td.v1').find('a').attr('href');
        var pos_first = script.indexOf('(');
        var pos_last = script.indexOf(')');
        var parameters = script.slice(pos_first+1,pos_last);
        var array = parameters.split(',');
        var href = "javascript:hotelswidget.functionReservar("+array[0]+","+array[1]+","+array[2]+","+array[3]+")";
        return href;
    }


    this.functionReservar = function(pHotelCode, pRateUni, pRedemptionRate, pClearEcertCode){
    	_paq.push(['trackEvent', 'Widget', 'Click Book', 'Widget Reservar']);
        
        if (jQuery('staticPopup'))
        {
        hideStaticDetailDivWithOverlay('staticPopup');
        } 
        var lForm = jQuery('hiddenRateReviewForm');
        lForm['rateSelectForm.hotelCode'].value = pHotelCode;
        lForm['rateSelectForm.rateUni'].value = pRateUni;
        //jQuery(lForm).prepend('<input type="hidden" name="facilitatorId" value="REZIDORRATETABLE">');
        //jQuery(lForm).prepend('<input type="hidden" name="icid" value="co_booking_direction">');
        
        
        if (pClearEcertCode)
        {
      	  lForm['rateSearchForm.ecertCodeForNonEligibleRate'].value =  lForm['rateSearchForm.ecertCode'].value;
      	  lForm['rateSearchForm.ecertCode'].value = '';
        }
        if (pRedemptionRate)
        {
      	  lForm['rateSearchForm.redemptionSearch'].value = true;
        }
        lForm.submit();
    }
    
})(window, document,jQuery);
