var hotelswidget = new (function(window, document, jQuery){
    var domain = 'https://www.123compare.me/v2'
    var datos = {};
    datos.user = 3;
    
    this.init = function (showWidget) {
    	if(showWidget === false){
            hotelswidget.setAnalytics();
        } else {
        	hotelswidget.setAnalytics();
        	datos.hotel = hotelswidget.getHotelName();
		    datos.lang = $('html').attr('lang');
		    datos.defaultLang = "en";
		    datos.device = 'isDesktop';
		    
		    var json = JSON.stringify(datos);
		    
		    console.log(json);
		    
		    jQuery.ajax({
				 method: "POST",
				 url: "https://www.123compare.me/v2/widget/widgetHome",
				 //url: "http://localhost:8080/123CompareMe-v2/widget/widgetHome",
				 data: { datos: json },
				 dataType: "script",
				 success: function(msg) {
				 }
			});
				
		}
			
	}
    
    this.setAnalytics = function(){
        $('body').append("<script type='text/javascript'>var _paq = _paq || [];_paq.push(['trackPageView']);_paq.push(['enableLinkTracking']);(function() {var u='//www.123compare.me/piwik/';_paq.push(['setTrackerUrl', u+'piwik.php']);_paq.push(['setSiteId', 3]);var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];g.type='text/javascript'; g.async=true; g.defer=true; g.src=u+'piwik.js'; s.parentNode.insertBefore(g,s);})();</script><noscript><p><img src='//www.123compare.me/piwik/piwik.php?idsite=3' style='border:0;' alt='' /></p></noscript>");
    }
    
    this.setJavascript = {
    	setPriceInWidget : function(price,currency){
    		$('#widget123_middle_price_price').html(price.toFixed(2));
    		$('#widget123_middle_price_currency').html(currency);
    	},
    	setWidgetJavascript : function(){
    		/*$('#widget123_top').click(function(){
                if ($('#widget123_middle').is(':hidden')){
                    $('#widget123_middle').slideDown(1000);
                } else {
                    $('#widget123_middle').slideUp(1000);
                }
            });*/
    		
    		var widget_responsive = $('#widget123_div').html();
    		$('#widget123_div').remove();
    		$(widget_responsive).insertAfter($('.pull-right.head-rside'));
    		var ancho_barra = $('#header-brand').width();
    		var ancho_imagen = $('#header-brand').find('a').find('img').width();
    		var ancho_idiomas = $('.pull-right.head-rside').width();
    		var ancho_widget = ancho_barra - ancho_imagen - ancho_idiomas - 10;
    		var ancho_widget_porcentage = ((ancho_widget * 100) / ancho_barra)+'%';
    		var alto_widget = $('#header-brand').find('a').find('img').height();
    		
    		$('#widget123').css({
    			'width': ancho_widget_porcentage,
    			'height' : alto_widget,
    		});
    		
    	},
    	setWidgetData : function(datos,price,currency){
    		data = datos;
    		if (data.length === 0){
    			_paq.push(['trackEvent', 'WidgetHome', 'No results', 'Widget not showed (No data available)']);
                return 0;
    		}
            var content_middle = document.getElementById("widget123_right");
            var count = 0;
            for (var i = 0; i < data.length; i++) {
            	
            	var precio_convertido = parseFloat(data[i].price);
            	if (((price - 1) < precio_convertido || (price - 1) == precio_convertido) &&  (count < 5)) {
            		count = count + 1;
            		var element = document.createElement("div");
                    element.setAttribute('id', 'element');
                    element.setAttribute('data-element',count);
                    element.setAttribute('style','top:'+((count - 1) * $('#widget123').height())+'px');
                    
                    var element_top = document.createElement("div");
                    element_top.setAttribute('id', 'widget123_right_ota'); 
                    element_top.innerHTML = data[i].site + ".com";
                    element.appendChild(element_top);
                    
                    var element_bottom = document.createElement("div");
                    element_bottom.setAttribute('id', 'widget123_right_price'); 
                    element_bottom.innerHTML = '<span id="widget123_right_price_price">' + precio_convertido.toFixed(2) + '</span><span id="widget123_right_price_currency">' + currency + '</span>';
                    element.appendChild(element_bottom);

                    content_middle.appendChild(element);
            		
            	}
            	
            }
            if (count === 0){
                _paq.push(['trackEvent', 'WidgetHome', 'No results', 'No results shown']);
            } else {
            	_paq.push(['trackEvent', 'WidgetHome', 'Show', 'Widget correctly showed']);
                _paq.push(['trackEvent', 'WidgetHome', 'Results', 'Shown '+count+' results']);
                if (count > 1){
                	$('#widget123').show();
                	hotelswidget.setAnimationWidget(count,5);
                } else {
                	$('#widget123').show();
                }
            }
    	}
    }
    
    this.setAnimationWidget = function(elements,time){
    	time = time * 1000;
    	
    	var altura = $('#widget123').height();
    	
    	setInterval(function(){
    		$('div#element').each(function(index,element){
				if ($(element).attr('data-element') === '1'){
					$(element).animate({
						'top' : '-'+altura+'px'
					},1000, function(){
						$(this).css({
							'top': (elements - 1) * altura
						});
						$(this).attr('data-element',elements);
					});
				} else {
					var next_element = parseInt($(element).attr('data-element')) - 1;
					$(element).animate({
						'top' : ((parseInt($(element).attr('data-element')) - 2) * altura)
					},1000, function(){
						$(this).attr('data-element', next_element);
					});
				}
			});
		},time);
    	
    }
    
    this.getHotelName = function(){
    	var url = window.location.href;
    	var patt = /skiportdelcomte/g;
    	var hotel = '';
    	if (patt.test(url)){
    		hotel = 'Serhs Ski Port Del Comte';
    	} else {
    		hotel = 'Hotel Serhs Carlit';
    	}
    	return hotel;
    }
    
})(window, document,jQuery);
