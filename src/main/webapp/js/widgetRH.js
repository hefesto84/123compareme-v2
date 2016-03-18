var hotelswidget = new (function(window, document, $){
    var domain = 'https://www.123compare.me/v2'
    var datos = {};
    datos.user = 6;
    
    this.anchuraElement = 151;
    this.animationTime = 5;
    this.totalElements;
    this.isAnimation = false;
    
    this.init = function (showWidget,properties) {
    	if(showWidget === false){
            hotelswidget.setAnalytics();
        } else {
        	hotelswidget.setAnalytics();
        	datos.domain = domain;
        	datos.hotel = hotelswidget.getHotelName();
        	var tipo = '';
        	if($('#ddHotel').length > 0){
        		tipo = 'spHeader_';
        	}
        	datos.rooms = $('#'+tipo+'spBooking_ddRooms').val();
		    datos.guests = $('#'+tipo+'spBooking_ddAdultos1').val();
		    datos.start = hotelswidget.dateConverse($('#'+tipo+'spBooking_txtStartDate').val());
		    datos.stop = hotelswidget.dateConverse($('#'+tipo+'spBooking_txtEndDate').val());
		    datos.currency = 'EUR';
		    datos.lang =  hotelswidget.getLang();
		    datos.defaultLang = 'en';
		    datos.price = hotelswidget.findPrice();
		    
		    datos.device = 'isDesktop';
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
   		var date1 = new Date(fini.slice(6,10),(parseInt(fini.slice(3,5)) - 1),fini.slice(0,2));
		var date2 = new Date(fout.slice(6,10),(parseInt(fout.slice(3,5)) - 1),fout.slice(0,2));
		var timeDiff = Math.abs(date2.getTime() - date1.getTime());
		return diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
    }
    
    this.setAnalytics = function(){
        $('body').append("<script type='text/javascript'>var _paq = _paq || [];_paq.push(['trackPageView']);_paq.push(['enableLinkTracking']);(function() {var u='//www.123compare.me/piwik/';_paq.push(['setTrackerUrl', u+'piwik.php']);_paq.push(['setSiteId', 6]);var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];g.type='text/javascript'; g.async=true; g.defer=true; g.src=u+'piwik.js'; s.parentNode.insertBefore(g,s);})();</script><noscript><p><img src='//www.123compare.me/piwik/piwik.php?idsite=6' style='border:0;' alt='' /></p></noscript>");
    }
    
    this.setJavascript = {
    	setPriceInWidget : function(price,currency){
    		$('#widget123_left_right_right_price').html(price.toFixed(2));
            $('#widget123_left_right_right_currency').html(hotelswidget.conversionCodeToSymbol(currency));
    	},
    	setWidgetJavascript : function(){
    		
    		var widget = $('#widget123_content').html();
    		$('#widget123_content').remove();
    		$('.room-type-head').append(widget);
    		
    		$('#widget123_left_left').click(function(){
    			$('#widget123').hide();
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
          
            var content = document.getElementById("widget123_right");
            var count = 0;
            for (var j = 0; j < data.datos.length; j++) {

                var precio_convertido = parseFloat(data.datos[j].price);
                //precio_convertido = precio_convertido / diffDay;

                if (((price - 1) < precio_convertido || (price - 1) == precio_convertido)){
                	count = count + 1;
                	
                	var element = document.createElement('div')
                	element.setAttribute('id','element');
                	element.setAttribute('data-element',count);
                	element.setAttribute('style','left:' +(count - 1)*hotelswidget.anchuraElement +'px;');
                	
                	var element_content = document.createElement('div');
                	element_content.setAttribute('id','element_content');
                	element_content.innerHTML = "<span id='element_ota'>" + data.datos[j].site.replace('.png','') + "</span>.com&nbsp<span id='element_price'>" + precio_convertido.toFixed(2) + "</span><span id='element_currency'>" + hotelswidget.conversionCodeToSymbol(currency)+ "</span>";
                	element.appendChild(element_content);
                	
                	content.appendChild(element);
                }
            }
            if (count === 0){
                _paq.push(['trackEvent', 'Widget', 'No results', 'No results shown']);
            } else {
            	_paq.push(['trackEvent', 'Widget', 'Show', 'Widget correctly showed']);
                _paq.push(['trackEvent', 'Widget', 'Results', 'Shown '+count+' results']);
                hotelswidget.totalElements = count;
                $('#widget123_right').css({
                	'width' : (hotelswidget.anchuraElement * count) - 1
                });
                $('#widget123').show();
                hotelswidget.mediaQuery();
                
            }
    	}
    }
    
    this.setUrlHref = function(){
    }


    this.functionReservar = function(pHotelCode, pRateUni, pRedemptionRate, pClearEcertCode){
    }
    
    this.getLang = function(){
    	var lang;
    	if (window.location.hostname === 'www.rh-hotels.co.uk'){
    		lang = "en";
    	} else if(window.location.hostname === 'www.rh-hotels.fr'){
    		lang = "fr";
    	} else {
    		lang = "es";
    	}
    	return lang;
    }
    
    this.findPrice = function(){
    	//var start = $('.room').find('.col12.room-type-holder').find('.rooms-type-item.active').find('.col9').find('.bucle').find('.nit').first().html().indexOf('</span>') + 7;
    	//var stop = $('.room').find('.col12.room-type-holder').find('.rooms-type-item.active').find('.col9').find('.bucle').find('.nit').first().html().indexOf('€');
    	var start = $('.room').find('.col12.room-type-holder').find('.rooms-type-item.active').find('.col9').find('.bucle').find('.tot').first().html().indexOf('</span>') + 7;
    	var stop = $('.room').find('.col12.room-type-holder').find('.rooms-type-item.active').find('.col9').find('.bucle').find('.tot').first().html().indexOf('€');
    	var price = $('.room').find('.col12.room-type-holder').find('.rooms-type-item.active').find('.col9').find('.bucle').find('.tot').first().html().slice(start,stop).trim();
		price = parseFloat(price.replace('.','').replace(',','.'));
		return price;
    }
    
    this.mediaQuery = function(){
    	
    	function mediaqueryresponseFull(mql){
			if (mql.matches){
				$('#widget123_right').css({
                	'width' : (hotelswidget.anchuraElement * hotelswidget.totalElements) - 1
                });
				hotelswidget.stopAnimationWidget();
			}
		}
    	
    	function mediaqueryresponse3Elements(mql){
			if (mql.matches){
				if (hotelswidget.totalElements >= 3){
					$('#widget123_right').css({
	                	'width' : (hotelswidget.anchuraElement * 3) - 1
	                });
				} else {
					$('#widget123_right').css({
	                	'width' : (hotelswidget.anchuraElement * hotelswidget.totalElements) - 1
	                });
					
				}
				if (hotelswidget.totalElements > 3){
					if (hotelswidget.isAnimation === false){
						hotelswidget.setAnimationWidget(hotelswidget.totalElements,hotelswidget.animationTime);
					}
				} else {
					hotelswidget.stopAnimationWidget();
				}
			}
		}
    	
    	function mediaqueryresponse2Elements(mql){
			if (mql.matches){
				if (hotelswidget.totalElements >= 2){
					$('#widget123_right').css({
	                	'width' : (hotelswidget.anchuraElement * 2) - 1
	                });
					
				} else {
					$('#widget123_right').css({
	                	'width' : hotelswidget.anchuraElement - 1
	                });
				}
				if (hotelswidget.totalElements > 2){
					if (hotelswidget.isAnimation === false){
						hotelswidget.setAnimationWidget(hotelswidget.totalElements,hotelswidget.animationTime);
					}
				} else {
					hotelswidget.stopAnimationWidget();
				}
			}
		}
    	
    	function mediaqueryresponse1Element(mql){
			if (mql.matches){
				$('#widget123_right').css({
                	'width' : hotelswidget.anchuraElement - 1
                });
				if (hotelswidget.totalElements > 1){
					if (hotelswidget.isAnimation === false){
						hotelswidget.setAnimationWidget(hotelswidget.totalElements,hotelswidget.animationTime);
					}
				} else {
					hotelswidget.stopAnimationWidget();
				}
			}
		}
    	
    	var mql = window.matchMedia("screen and (min-width: 1251px)");
		var mql1 = window.matchMedia("screen and (min-width: 1101px) and (max-width: 1250px)");
		var mql2 = window.matchMedia("screen and (min-width: 951px) and (max-width: 1100px)");
		var mql3 = window.matchMedia("screen and (min-width: 0px) and (max-width: 950px)");
		mediaqueryresponseFull(mql);
		mediaqueryresponse3Elements(mql1);
		mediaqueryresponse2Elements(mql2);
		mediaqueryresponse1Element(mql3);
		mql.addListener(mediaqueryresponseFull);
		mql1.addListener(mediaqueryresponse3Elements);
		mql2.addListener(mediaqueryresponse2Elements);
		mql3.addListener(mediaqueryresponse1Element);
		
    }
    
    this.setAnimationWidget = function(elements,time){
    	time = time * 1000;
    	
    	var anchura = 151;
    	
    	datos.animation = setInterval(function(){
    		$('div#element').each(function(index,element){
				if ($(element).attr('data-element') === '1'){
					$(element).animate({
						'left' : '-'+anchura+'px'
					},1000, function(){
						$(this).css({
							'left': (elements - 1) * anchura
						});
						$(this).attr('data-element',elements);
					});
				} else {
					var next_element = parseInt($(element).attr('data-element')) - 1;
					$(element).animate({
						'left' : ((parseInt($(element).attr('data-element')) - 2) * anchura)
					},1000, function(){
						$(this).attr('data-element', next_element);
					});
				}
			});
		},time);
    	
    	hotelswidget.isAnimation = true;
    	
    }
    
    this.stopAnimationWidget = function(){
    	clearInterval(datos.animation);
    	hotelswidget.isAnimation = false;
    }
    
    this.conversionCodeToSymbol = function(code){

        var conversion = {
            GBP:'£',
            EUR:'€'
        };
        return conversion[code];
    
    }
    
    this.getParameterByName = function(name,url){
    	if (!url) url = window.location.href;
        url = url.toLowerCase(); // This is just to avoid case sensitiveness  
        name = name.replace(/[\[\]]/g, "\\$&").toLowerCase();// This is just to avoid case sensitiveness for query parameter name
        var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, " "));
    }
    
    this.getHotelName = function(){
    	
    	var hotel;
    	var hot = hotelswidget.getParameterByName("hot");
    	
    	switch (hot){
    	
	    	case "102":
	    		hotel = "RH Princesa";
	    		break;
	    	
	    	case "103":
	    		hotel = "RH Bayren SPA";
	    		break;
	    	
	    	case "104":
	    		hotel = "RH Bayren Parc";
	    		break;
	    	
	    	case "105":
	    		hotel = "RH Canfali";
	    		break;
	    	
	    	case "106":
	    		hotel = "RH Casablanca";
	    		break;
	    	
	    	case "107":
	    		hotel = "RH Corona del Mar";
	    		break;
	    	
	    	case "108":
	    		hotel = "RH Gijon";
	    		break;
	    	
	    	case "109":
	    		hotel = "RH Portocristo";
	    		break;
	    	
	    	case "110":
	    		hotel = "RH Riviera";
	    		break;
	    	
	    	case "111":
	    		hotel = "RH Royal";
	    		break;
	    	
	    	case "112":
	    		hotel = "RH Sol";
	    		break;
	    	
	    	case "113":
	    		hotel = "RH Victoria";
	    		break;
	    	
	    	case "122":
	    		hotel = "RH Vinaros Playa";
	    		break;
	    	
	    	case "123":
	    		hotel = "RH Vinaros Aura";
	    		break;
	    	
	    	case "124":
	    		hotel = "RH Don Carlos SPA";
	    		break;
    	
    	}
    	
    	return hotel;
    	
    }
    
})(window, document,$);
