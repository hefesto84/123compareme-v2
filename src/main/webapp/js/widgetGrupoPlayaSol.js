var hotelswidget = new (function(window, document, jQuery){
    var domain = 'https://www.123compare.me/v2'
    var datos = {};
    datos.user = 12;
    
    this.init = function (showWidget,properties) {
    	if(showWidget === false){
            hotelswidget.setAnalytics();
        } else {
        	hotelswidget.setAnalytics();
        	datos.domain = domain;
        	datos.hotel = hotelswidget.hotelName($('.destine-val').html().trim()).toLowerCase();
        	datos.rooms = 1;
		    datos.guests = $('.number-adults').html();
		    datos.start = dia + "/" + mes + "/" + anio;
		    datos.stop = diaHasta + "/" + mesHasta + "/" + anioHasta;
		    datos.currency = $('#currency-filter').find('option[value="'+$("#currency-filter").val()+'"]').attr('id');
		    datos.lang =  $('html').attr('lang');
		    datos.defaultLang = 'en';
		    datos.price = parseFloat($('#roomTab.subtitle').find('.fullprice').html());
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
    
    this.diffDate = function (fini,fout){
   		var date1 = new Date(fini.slice(6,10),(parseInt(fini.slice(3,5)) - 1),fini.slice(0,2));
		var date2 = new Date(fout.slice(6,10),(parseInt(fout.slice(3,5)) - 1),fout.slice(0,2));
		var timeDiff = Math.abs(date2.getTime() - date1.getTime());
		return diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
    }
    
    this.setAnalytics = function(){
        $('body').append("<script type='text/javascript'>var _paq = _paq || [];_paq.push(['trackPageView']);_paq.push(['enableLinkTracking']);(function() {var u='//www.123compare.me/piwik/';_paq.push(['setTrackerUrl', u+'piwik.php']);_paq.push(['setSiteId', 12]);var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];g.type='text/javascript'; g.async=true; g.defer=true; g.src=u+'piwik.js'; s.parentNode.insertBefore(g,s);})();</script><noscript><p><img src='//www.123compare.me/piwik/piwik.php?idsite=12' style='border:0;' alt='' /></p></noscript>");
    }
    
    this.setJavascript = {
    	setPriceInWidget : function(price,currency){
    		$('#widget123_middle_right_price').html(price.toFixed(2));
    		$('#widget123_middle_right_currency').html(hotelswidget.currencySymbol(currency));
    	},
    	setWidgetJavascript : function(){
    		
    		$('#widget123_deplegado').css({
    			'width' : $('.summary-module-resume').width(),
    			'position' : 'fixed',
    		});
    		
    		var widget = $('#widget123_deplegado').html();
    		$('#widget123_deplegado').remove();
    		$(widget).insertAfter('.summary-module-resume');
    		
    		$('#widget123_inform_1').show('scale',1000, function(){});
    		
    		$(window).on( 'DOMMouseScroll mousewheel', function ( event ) {
				  if ($('.summary-module-resume').css('position') === 'fixed'){
					  $('#widget123').css({
						  'position': 'fixed',
						  'top' : $('.summary-module-resume').height() + 10 + parseInt($('.summary-module-resume').css('top').slice(0,$('.summary-module-resume').css('top').indexOf('p'))),
						  'width' : $('.summary-module-resume').width(),
						  'border' : 'none',
						  '-webkit-box-shadow' : '-1px 2px 20px -4px rgba(0,0,0,0.75)',
						  '-moz-box-shadow' : '-1px 2px 20px -4px rgba(0,0,0,0.75)',
						  'box-shadow' : '-1px 2px 20px -4px rgba(0,0,0,0.75)'
				      });
					  
					  $('#widget123_copyright').css({
						  'position': 'fixed',
						  'top' : $('.summary-module-resume').height() + 10 + parseInt($('.summary-module-resume').css('top').slice(0,$('.summary-module-resume').css('top').indexOf('p'))) + $('#widget123').height() + 10,
						  'width' : $('.summary-module-resume').width()
				      });
					  
					  $('#widget123_inform_1').css({
						  'position': 'fixed',
						  'top' : $('.summary-module-resume').height() + 10 + parseInt($('.summary-module-resume').css('top').slice(0,$('.summary-module-resume').css('top').indexOf('p'))),
						  'width' : $('.summary-module-resume').width(),
						  '-webkit-box-shadow' : '-1px 2px 20px -4px rgba(0,0,0,0.75)',
						  '-moz-box-shadow' : '-1px 2px 20px -4px rgba(0,0,0,0.75)',
						  'box-shadow' : '-1px 2px 20px -4px rgba(0,0,0,0.75)'
				      });
				  } else{
					  $('#widget123').css({
						  'position': 'relative',
						  'top' : '0px',
						  'border' : '1px solid #ccc',
						  '-webkit-box-shadow' : 'none',
						  '-moz-box-shadow' : 'none',
						  'box-shadow' : 'none'
					  });
					  
					  $('#widget123_copyright').css({
						  'position': 'relative',
						  'top' : '0px'
					  });
					  
					  $('#widget123_inform_1').css({
						  'position': 'relative',
						  'top' : '0px',
						  '-webkit-box-shadow' : 'none',
						  '-moz-box-shadow' : 'none',
						  'box-shadow' : 'none'
					  });
				  }
    		});
    		
    		$('#widget123_inform_close_1').click(function(){
    			$('#widget123_inform_1').hide('scale',1000, function(){});
    		})
    		
    		$('#widget123_top').click(function(){
    			$('#widget123_content').toggle('slide',{ direction: "up" }, 1000, function(){});
    		});
    
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
                precio_convertido = (precio_convertido / diffDay);

                if (((price * 0.95) < precio_convertido) || ((price * 0.95) == precio_convertido)){
                	count = count + 1;
                	
                	var element = document.createElement("div");
                    element.setAttribute('id', 'element');
                    
                    var element_left = document.createElement("div");
                    element_left.setAttribute('id', 'element_left'); 
                    element_left.innerHTML = "<span class='element_left_ota'>"+data.datos[j].site.replace('.png','') + "</span>.com";
                    element.appendChild(element_left);
                    
                    var element_right = document.createElement("div");
                    element_right.setAttribute('id', 'element_right'); 
                    element_right.innerHTML = '<span id="element_right_price">' + precio_convertido.toFixed(2) + '</span>&nbsp;<span id="element_right_currency">' + hotelswidget.currencySymbol(currency) + '</span>';
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
                $('#widget123_inform_1').hide('scale',1000, function(){
                	$('#widget123').toggle('slide',{ direction: "down" }, 1000, function(){
                		$('#widget123_copyright').show();
                	});
                });
            }
    	}
    }
    
    this.setUrlHref = function(){
    }


    this.functionReservar = function(pHotelCode, pRateUni, pRedemptionRate, pClearEcertCode){
    }
    
    this.currencySymbol = function(currency){
    	
        var conversion = {
        	EUR:'€',
            CAD:'$',
            CNY:'¥',
            DOP:'$',
            JPY:'¥',
            MXN:'$',
            GBP:'£',
            RUB:'₱',
            USD:'$'
        };
        return conversion[currency];
        
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
    
    this.hotelName = function(name){
    	hotelName = name.replace('&amp;','');
        return hotelName;
    }

    
})(window, document,jQuery);
