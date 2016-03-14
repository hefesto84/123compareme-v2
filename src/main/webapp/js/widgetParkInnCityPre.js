var hotelswidget = new (function(window, document, jQuery){
    var domain = 'https://www.123compare.me/v2'
    var datos = {};
    datos.user = 1;
    
    this.init = function (showWidget) {
    	if(showWidget === false){
            hotelswidget.setAnalytics();
        } else {
        	hotelswidget.setAnalytics();
        	datos.domain = domain;
        	datos.hotel = hotelswidget.hotelName(jQuery('.innername').first().find("a").text());
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
				 url: "https://www.123compare.me/v2/widget/widgetCity",
				 //url: "http://localhost:8080/123CompareMe-v2/widget/widgetCity",
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
   		var date1 = new Date(fini.slice(6,10),(parseInt(fini.slice(3,5)) - 1),fini.slice(0,2));
		var date2 = new Date(fout.slice(6,10),(parseInt(fout.slice(3,5)) - 1),fout.slice(0,2));
		var timeDiff = Math.abs(date2.getTime() - date1.getTime());
		return diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
    }
    
    this.setAnalytics = function(){
        jQuery('body').append("<script type='text/javascript'>var _paq = _paq || [];_paq.push(['trackPageView']);_paq.push(['enableLinkTracking']);(function() {var u='//www.123compare.me/piwik/';_paq.push(['setTrackerUrl', u+'piwik.php']);_paq.push(['setSiteId', 1]);var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];g.type='text/javascript'; g.async=true; g.defer=true; g.src=u+'piwik.js'; s.parentNode.insertBefore(g,s);})();</script><noscript><p><img src='//www.123compare.me/piwik/piwik.php?idsite=1' style='border:0;' alt='' /></p></noscript>");
    }
    
    this.setJavascript = {
    	setPriceInWidget : function(price,currency){
    		if(lang == 'en' || lang == 'en_UK'){
                jQuery('#widget123_left_left_price').find('#price').html((price.toFixed(2)).replace(',','.'));
    		} else{
                jQuery('#widget123_left_left_price').find('#price').html((price.toFixed(2)).replace('.',','));
            }
            jQuery('#widget123_left_left_price').find('#currency').html(currency);
    	},
    	setWidgetJavascript : function(){
    		
    		jQuery('#widget123_left_left_top').html(hotelswidget.hotelName(jQuery('.innername').first().find("a").text()));
    		var widget = jQuery('#widget123_city').html();
    		jQuery('#widget123_city').remove();
    		//jQuery(widget).insertBefore('.PIIhotelmatrixdiv:eq(0)');
    		
    		jQuery('#SortandFilterResults').find('.row.ng-scope:eq(0)').find('.hotelRow').append(widget);
    		
    		jQuery('#widget123').css({
    			'width' : jQuery('.hotelRow').width() + 3
    		});
    		
            _paq.push(['trackEvent', 'Widget', 'Show', 'Widget correctly showed']);
    	},
    	setWidgetData : function(datos,price,currency,diffDay){
    		var href = hotelswidget.setUrlHref();
            data = datos;
            if(data.currency === 'XXX'){
            	_paq.push(['trackEvent', 'Widget', 'No currency', 'Currency not available']);
                return 0;
            }
            if ((data.datos === null) || (data.datos.length === 0)){
                _paq.push(['trackEvent', 'Widget', 'No results', 'Widget not showed (No data available)']);
                return 0;
            }

            var content = document.getElementById("widget123_right_right");
            
            var count = 0;
            for (var i = 0; i < data.datos.length; i++) {

                var precio_convertido = parseFloat(data.datos[i].price);
                
                precio_convertido = precio_convertido / diffDay;

                if (((price * 0.995) < precio_convertido || (price * 0.995) == precio_convertido)) {
                    count = count + 1;
                    
                    var element = document.createElement("div");
                    element.setAttribute('id', 'element');
                    element.setAttribute('data-element',count);
                    element.setAttribute('style','top:'+((count - 1) * jQuery('#widget123_right').height())+'px');
                    
                    var element_top = document.createElement("div");
                    element_top.setAttribute('id', 'element_top');
                    element_top.innerHTML = '<span id="element_top_ota">'+data.datos[i].site.replace('.png','')+'</span>.com';
                    element.appendChild(element_top);
                    
                    var element_bottom = document.createElement("div");
                    element_bottom.setAttribute('id', 'element_bottom');
                    element_bottom.innerHTML = '<span id="price">' + precio_convertido.toFixed(2) + '</span>&nbsp;<span id="currency">' + currency + '</span>';
                    element.appendChild(element_bottom	);
                    
                    content.appendChild(element);
                    
                }
            }
            if (count === 0){
                jQuery('#widget123_popup_content_middle').append("<div id='no_otas'>Sorry, we couldn\'t complete the search for these dates</div>");
                _paq.push(['trackEvent', 'Widget', 'No results', 'No results shown']);
            } else {
                _paq.push(['trackEvent', 'Widget', 'Results', 'Shown '+count+' results']);
                jQuery('#widget123').show();
                if (count > 1){
                	hotelswidget.setAnimationWidget(count,5);
                }
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
    	_paq.push(['trackEvent', 'Widget', 'Click Book', 'Book button clicked']);
        
        if (jQuery('staticPopup'))
        {
        hideStaticDetailDivWithOverlay('staticPopup');
        } 
        var lForm = $('hiddenRateReviewForm');
        lForm['rateSelectForm.hotelCode'].value = pHotelCode;
        lForm['rateSelectForm.rateUni'].value = pRateUni;        
        
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
    
    this.setAnimationWidget = function(elements,time){
    	time = time * 1000;
    	
    	var altura = jQuery('#widget123').height();
    	
    	setInterval(function(){
    		jQuery('div#element').each(function(index,element){
				if (jQuery(element).attr('data-element') === '1'){
					jQuery(element).animate({
						'top' : '-'+altura+'px'
					},1000, function(){
						jQuery(this).css({
							'top': (elements - 1) * altura
						});
						jQuery(this).attr('data-element',elements);
					});
				} else {
					var next_element = parseInt(jQuery(element).attr('data-element')) - 1;
					jQuery(element).animate({
						'top' : ((parseInt(jQuery(element).attr('data-element')) - 2) * altura)
					},1000, function(){
						jQuery(this).attr('data-element', next_element);
					});
				}
			});
		},time);
    	
    }
    
})(window, document,jQuery);
