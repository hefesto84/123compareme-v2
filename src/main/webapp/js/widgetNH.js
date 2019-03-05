var hotelswidget = new (function(window, document, $){

    var domain = 'https://www.123compare.me/v2'
    var data = '';

    this.init = function (showWidget, timeShow, reserva) {
        if(showWidget === false){
            hotelswidget.setAnalytics();
        } else {
            var show = timeShow;
            var conversion = 1;
            var user = 2;
            var hotel = $('#barra_datos_reserva_va').find('div.name').html();
            hotel = normalize(hotel);
            var rooms = $('#number_rooms').val();
            var guests = $('#number_guests').val();
            var start = hotelswidget.dateConverse($('#date_ini').val());
            var stop = hotelswidget.dateConverse($('#date_out').val());

            if($('html').attr('lang') === 'en'){
                if ($('span.reservas_tarifas_precio').length){
                    var price = parseFloat($('span.reservas_tarifas_precio').html());
                } else {
                    var price = parseFloat($('.activa').find('.price').html().substring(0,$('.activa').find('.price').html().indexOf('<')));
                }
            } else {
                if ($('span.reservas_tarifas_precio').length){
                    var price = parseFloat($('span.reservas_tarifas_precio').html().replace('.','').replace(',','.'));
                } else {
                    var price = parseFloat($('.activa').find('.price').html().substring(0,$('.activa').find('.price').html().indexOf('<')).replace('.','').replace(',','.'));
                }
            }

            price = Math.round(price);
            var currency = $('.currency_reservas').html();
            var lang = $('html').attr('lang');
            var device = 'isMobile';

            var url_post = domain + '/api/prices?currency='+currency+'&base=' + price + '&code=' + user + '&hotel=' + encodeURI(hotel) + '&rooms=' + rooms + '&guests=' + guests + '&fin=' + start + '&fout=' + stop + '&lang=' + lang;

            console.log(url_post);

            if ((price !== 'undefined') && (price !== '') && (price !== 'NaN')){
                price = parseFloat(price);

                hotelswidget.setCSS();
                if(device === 'isDesktop'){
                    hotelswidget.setHtml(price,currency);
                } else {
                    hotelswidget.setHtmlMobile(price,currency,show,reserva);
                }
                hotelswidget.setTranslate(lang);
                setTimeout(function() {
                    hotelswidget.getDatos(url_post,device,price,conversion,currency,lang);
                },1000) ;
            }
        }
    }

    this.getDatos = function(url_post,device,price,conversion,currency,lang){
        var datos;
        $.ajax({
            type: "GET",
            url: url_post,
            success: function (respuesta) {
                console.log(respuesta);
                datos = respuesta;
                if (device === 'isMobile'){
                    hotelswidget.setWidgetMobile(datos,price,conversion, currency);
                } else {
                    hotelswidget.setWidget(datos,price,conversion, currency);
                }
            }//,
            //async: false
        });
    }

    this.setTranslate = function(lang){
        // Les ooooques van descalces, descaaaalces descaaaaalcesss....
        // les ooooques van descalces i els aaanecs tambÃƒÂ©!
        // Posseuuuuu-lis sabaaates, sabaaatesss sabaaates
        // Posseuuuu-lis sabaaates i mitjons tambÃƒÂ©eeee

        // Listado de variables con los textos por idiomas
        var locale_de = '{"widget_top_left":"Best price guaranteed from","widget_middle_text":"Do you want to compare prices?","widget_bottom_button":"Check it here","widget_popup_content_top_text":"Best Price Guaranteed","widget_popup_content_nh_text":"Our lowest rate:","widget_popup_content_nh_left_text_t1":"Free Internet","widget_popup_content_nh_left_text_t2":"Earn Club Carlson Points","widget_popup_content_nh_text_bottom":"In other sites:","widget_popup_content_nh_right_bottom_t1":"No extra fees.","widget_popup_content_nh_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"Sorry, we couldn\'t complete the search for these dates"}';
        var locale_es = '{"widget_top_left":"Mejor precio garantizado desde","widget_middle_text":"Â¿Quieres comparar los precios?","widget_bottom_button":"Comparar precio","widget_popup_content_top_text":"MEJOR TARIFA GARANTIZADA","widget_popup_content_nh_text":"Our lowest rate:","widget_popup_content_nh_left_text_t1":"Free Internet","widget_popup_content_nh_left_text_t2":"Earn Club Carlson Points","widget_popup_content_nh_text_bottom":"In other sites:","widget_popup_content_nh_right_bottom_t1":"Sin taxas extras.","widget_popup_content_nh_right_bottom_t2":"Sin cargos extra", "widget_popup_content_bottom_button":"Reservar", "widget_popup_loading_text":"Buscando", "no_otas":"Disculpa, no podemos completar la búsqueda para estas fechas"}';
        var locale_en = '{"widget_top_left":"Best price guaranteed from","widget_middle_text":"Do you want to compare prices?","widget_bottom_button":"Check it here","widget_popup_content_top_text":"BEST RATE GUARANTEED","widget_popup_content_nh_text":"Our lowest rate:","widget_popup_content_nh_left_text_t1":"Free Internet","widget_popup_content_nh_left_text_t2":"Earn Club Carlson Points","widget_popup_content_nh_text_bottom":"In other sites:","widget_popup_content_nh_right_bottom_t1":"No extra fees.","widget_popup_content_nh_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"Sorry, we couldn\'t complete the search for these dates"}';
        var locale_fr = '{"widget_top_left":"GARANTIE DU MEILLEUR PRIX","widget_middle_text":"Voulez-vous comparer les prix?","widget_bottom_button":"V&#233;rifiez ici","widget_popup_content_top_text":"GARANTIE DU MEILLEUR PRIX","widget_popup_content_nh_text":"Notre plus bas tarif:","widget_popup_content_nh_left_text_t1":"Internet gratuit","widget_popup_content_nh_left_text_t2":"Gagnez points Club Carlson","widget_popup_content_nh_text_bottom":"En d&#039;autres sites:","widget_popup_content_nh_right_bottom_t1":"Pas de frais suppl&#233;mentaires.","widget_popup_content_nh_right_bottom_t2":"Pas de frais cach&#233;s.", "widget_popup_content_bottom_button":"R&#233;server maintenant", "widget_popup_loading_text":"Regarder les rÃƒÂ©sultats", "no_otas":"Sorry, we couldn\'t complete the search for these dates"}';
        var locale_it = '{"widget_top_left":"Best price guaranteed from","widget_middle_text":"Do you want to compare prices?","widget_bottom_button":"Check it here","widget_popup_content_top_text":"Best Price Guaranteed","widget_popup_content_nh_text":"Our lowest rate:","widget_popup_content_nh_left_text_t1":"Free Internet","widget_popup_content_nh_left_text_t2":"Earn Club Carlson Points","widget_popup_content_nh_text_bottom":"In other sites:","widget_popup_content_nh_right_bottom_t1":"No extra fees.","widget_popup_content_nh_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"Sorry, we couldn\'t complete the search for these dates"}';
        var locale_nl = '{"widget_top_left":"Best price guaranteed from","widget_middle_text":"Do you want to compare prices?","widget_bottom_button":"Check it here","widget_popup_content_top_text":"Best Price Guaranteed","widget_popup_content_nh_text":"Our lowest rate:","widget_popup_content_nh_left_text_t1":"Free Internet","widget_popup_content_nh_left_text_t2":"Earn Club Carlson Points","widget_popup_content_nh_text_bottom":"In other sites:","widget_popup_content_nh_right_bottom_t1":"No extra fees.","widget_popup_content_nh_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"Sorry, we couldn\'t complete the search for these dates"}';
        var locale_pt = '{"widget_top_left":"Best price guaranteed from,"widget_middle_text":"Do you want to compare prices?","widget_bottom_button":"Check it here","widget_popup_content_top_text":"Best Price Guaranteed","widget_popup_content_nh_text":"Our lowest rate:","widget_popup_content_nh_left_text_t1":"Free Internet","widget_popup_content_nh_left_text_t2":"Earn Club Carlson Points","widget_popup_content_nh_text_bottom":"In other sites:","widget_popup_content_nh_right_bottom_t1":"No extra fees.","widget_popup_content_nh_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"Sorry, we couldn\'t complete the search for these dates"}';
        var locale_ru = '{"widget_top_left":"Best price guaranteed from","widget_middle_text":"Do you want to compare prices?","widget_bottom_button":"Check it here","widget_popup_content_top_text":"Best Price Guaranteed","widget_popup_content_nh_text":"Our lowest rate:","widget_popup_content_nh_left_text_t1":"Free Internet","widget_popup_content_nh_left_text_t2":"Earn Club Carlson Points","widget_popup_content_nh_text_bottom":"In other sites:","widget_popup_content_nh_right_bottom_t1":"No extra fees.","widget_popup_content_nh_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"Sorry, we couldn\'t complete the search for these dates"}';

        // Array de traducciones
        var translations = {
            de:locale_de,
            es:locale_es,
            en:locale_en,
            fr:locale_fr,
            it:locale_it,
            pt:locale_pt,
            nl:locale_nl,
            ru:locale_ru
        };

        // Array con las traducciones por idioma
        var t = JSON.parse(unescape(translations[$('html').attr('lang')]));

        $('#widget_top_left').html(t["widget_top_left"]);
        $('#widget_middle_text').html(t["widget_middle_text"]);
        $('#widget_bottom_button').html(t["widget_bottom_button"]);
        $('#widget_popup_content_top_text').html(t["widget_popup_content_top_text"]);
        $('#widget_popup_content_nh_text').html(t["widget_popup_content_nh_text"]);
        $('.t1').html(t["widget_popup_content_nh_left_text_t1"]);
        $('.t2').html(t["widget_popup_content_nh_left_text_t2"]);
        $('#widget_popup_content_nh_text_bottom').html(t["widget_popup_content_nh_text_bottom"]);
        $('.widget_popup_content_nh_right_bottom_t1').html(t["widget_popup_content_nh_right_bottom_t1"]);
        $('.widget_popup_content_nh_right_bottom_t2').html(t["widget_popup_content_nh_right_bottom_t2"]);
        $('#widget_popup_content_bottom_button').html(t["widget_popup_content_bottom_button"]);
        $('#widget_popup_loading_text').html(t["widget_popup_loading_text"]);
        $('#no_otas').html(t["no_otas"]);

        $('#widget').show();

    }

    this.setWidget = function(datos,price,conversion, currency) {

    }

    this.setWidgetMobile = function(datos,price,conversion, currency){

        $('.widget_content_loading').hide();
        $('#widget_popup_loading_text').hide();
        data = datos;
        if (data.datos.length == 0){
            _paq.push(['trackEvent', 'Widget', 'No results', 'Widget not showed (No data available)']);
            return 0;
        }
        var content_middle = document.getElementById("widget_popup_content_middle");
        var count = 0;
        for (var i = 0; i < data.datos.length; i++) {

            var precio_convertido = (parseFloat(data.datos[i].price.replace(',','.')) * conversion);
            precio_convertido = Math.round(precio_convertido);

            if (((price - 1) < precio_convertido || (price - 1) == precio_convertido) &&  (count < 5)) {
                count = count + 1;
                var element = document.createElement("div");
                element.setAttribute('id', 'element');

                var image = document.createElement('img');
                image.setAttribute('src', domain + '/img/' + data.datos[i].site);
                element.appendChild(image);

                var span = document.createElement('span');
                span.setAttribute('class', 'priceWidgetElement');
                span.innerHTML = precio_convertido + ' <span class="priceWidgeElementCurrency">' + currency + '</span>';
                element.appendChild(span);

                var clear = document.createElement('div');
                clear.setAttribute('style', 'clear:both;');
                element.appendChild(clear);

                content_middle.appendChild(element);
            }
        }
        if (count === 0){
            $('#widget_popup_content_middle').append("<div id='no_otas'>No rooms found for these dates in other sites</div>");
            _paq.push(['trackEvent', 'Widget', 'No results', 'No results shown']);
        } else {
            _paq.push(['trackEvent', 'Widget', 'Results', 'Shown '+count+' results']);
        }

    }

    this.setJavaScript = function(){

    }

    this.setJavaScriptMobile = function(reserva){
        var width_widget = jQuery('#principal').width() - 16;
        $('#widget').css({
            'width' : width_widget
        });

        var screen_width = $(window).width();
        var screen_height = $(window).height();

        var popup_width = $('#widget_popup_content').width();
        var popup_height = $('#widget_popup_content').height();
        $('#widget_popup_content').css({
            'left' : ((screen_width - popup_width)/2)
        });


        $('#widget').click(function(){
            $('#widget_popup').show();
            $('#widget').hide();
            _paq.push(['trackEvent', 'Widget', 'Click', 'Widget clicked']);
        });
        $('#widget_popup_content_top_close').click(function(){
            $('#widget').show();
            $('#widget_popup').hide();
            _paq.push(['trackEvent', 'Widget', 'Hide Popup', 'Widget popup hidden']);
        });

        $('#widget_close').click(function(){
            $('#widget').hide();
            $('#widget').unbind("click");

            _paq.push(['trackEvent', 'Widget', 'Hide', 'Widget closed']);
        });

        _paq.push(['trackEvent', 'Widget', 'Show', 'Widget correctly showed']);

        $('#widget_popup_content_bottom_button').click(function(){
            hotelswidget.functionReservar(reserva);
        });
    }

    this.dateConverse = function(date){
        converseDate = date.slice(0,2)+'/'+date.slice(3,5)+'/'+date.slice(6,10);
        return converseDate;
    }

    this.functionReservar = function(reserva){
        _paq.push(['trackEvent', 'Widget', 'Click Book', 'Book button clicked']);
        $('#rp1_rate').val(reserva.rate);
        $('#rp1_room').val(reserva.room);
        $('#rp1_bed').val(reserva.bed);
        $('#rp1_board').val(reserva.board);
        $('#rp1_form').submit();
    }

    this.setCSS = function(){
        $('body').append("<style>" +
            "#widget{box-sizing: border-box;-webkit-box-sizing: border-box;-moz-box-sizing: border-box;margin-left: 8px;position: fixed;bottom: -150px;width: 100%;background-color: #003A6F;padding: 10px; padding-left: 0px;}" +
            "#widget_close{position: absolute;right: 0px;top: 0px;width: 25px;height: 25px;background: url('https://www.123compare.me/v1/images/close.png');background-size: 100% 100%;}" +
            "#widget_top{box-sizing: border-box;-webkit-box-sizing: border-box;-moz-box-sizing: border-box;width: 100%;height: 40px;}" +
            "#widget_top_left{box-sizing: border-box; -webkit-box-sizing: border-box; -moz-box-sizing: border-box;float: left;width: 56%;height: 40px;padding: 10px;padding-top:5px;padding-right: 5px;color: #FFFFFF;font-size: 15px;line-height: 15px;border-right: 1px solid #FFFFFF;}" +
            "#widget_top_right_price{font-size: 25px;}" +
            "#widget_top_right{box-sizing: border-box; -webkit-box-sizing: border-box; -moz-box-sizing: border-box;float: left;width: 44%;height: 40px;padding: 10px;padding-top:5px;padding-right: 5px;color: #FFFFFF;font-size: 12px;text-transform: uppercase;line-height: 30px;}" +
            "#widget_bottom{width: 100%;margin-top: 10px; padding-left: 5px;}" +
            "#widget_bottom_button{box-sizing: border-box; -webkit-box-sizing: border-box; -moz-box-sizing: border-box;width: 50%;margin: auto;background-color: #ffa300;color: #FFFFFF;font-size: 14px;padding: 0.55em;text-align: center;}" +
            "#widget_popup_content_nh_text_bottom {margin-bottom: 0px;margin-top: 10px;text-align: left;padding-left: 10px;font-weight: bold;}" +
            "#widget_popup{display: none;font-family: 'Montserrat', Helvetica, Arial, sans-serif;}" +
            "#widget_content_background{position: fixed;left: 0px;top: 0px;width: 100%;height: 100%;background: rgba(0,0,0,0.5);z-index: 9999999999;}" +
            "#widget_popup_content{position: absolute;top: 50px;width: 260px;box-sizing: border-box;}" +
            "#widget_popup_content_top{background-color: #003A6F !important;color: #FFFFFF;padding: 5px;text-align: center;line-height: 50px;font-size: 13px;}" +
            "#widget_popup_content_top_text{font-weight: bold;padding-left: 15px;}" +
            "#widget_popup_content_top_close{margin-bottom: -22px;float: right;width: 25px;height: 25px;background: url('https://www.123compare.me/v1/images/close.png');background-size: 100% 100%;}" +
            "#widget_popup_content_nh{padding: 10px;padding-bottom: 0px;background-color: #FFFFFF;}" +
            "#widget_popup_content_nh_text {padding-left: 10px;font-size: 12px;margin-bottom: 10px;text-align: left;font-family: 'Montserrat', Helvetica, Arial, sans-serif;font-weight: bold;}" +
            "#widget_popup_content_middle{padding: 10px;padding-top: 0px;background-color: #FFFFFF;}" +
            ".widget_content_loading{display:block;margin:auto;padding-top:15px;}" +
            "#widget_popup_loading_text{margin-top: 10px;font-family: 'Montserrat', Helvetica, Arial, sans-serif;font-size: 11px; text-align: center;}" +
            "#widget_popup_content_bottom{padding: 10px;background-color: #FFFFFF;}" +
            "#widget_popup_content_bottom_button{width: 40%;text-align:center;color: #FFFFFF;margin: auto;background-color: #ffa300;padding: 0.55em;padding-left: 20px;padding-right: 20px;font-size: 18px;font-weight: bold;margin-top: 10px;font-family: 'Montserrat', Helvetica, Arial, sans-serif;}" +
            "#element{padding: 10px;border-bottom: 1px solid #EBEAEC;}" +
            "#element img{width: auto;float: left;height: 22px;}" +
            "#element .currency{float: left;line-height: 22px;color: #4A4B4C;font-family: 'Montserrat', Helvetica, Arial, sans-serif;}" +
            "#element .text{text-align: center;font-size: 12px;color: #4A4B4C;font-family: 'Montserrat', Helvetica, Arial, sans-serif;}" +
            "#element .priceWidgetElement {float: right;font-family: 'Montserrat', Helvetica, Arial, sans-serif;margin-left: 5px;line-height: 22px;color: #4A4B4C;font-size: 18px;}" +
            "#element .priceWidgetElement .priceWidgeElementCurrency{font-size:13px;}" +
            ".image_nh{width: 70px;height: auto;display: block;margin-bottom: 5px;}" +
            "#widget_popup_content_nh_left{padding-left: 10px;width: 40%;box-sizing: border-box;float: left;line-height: 10px;}" +
            "#widget_popup_content_nh_left_text {font-size: 10px;font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;}" +
            "#widget_popup_content_nh_right{padding-right: 10px;width: 60%;box-sizing: border-box;float: right;text-align: right;font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;color: #FFA300;line-height: 30px; margin-top: 5px;}" +
            "#widget_popup_content_nh_right_price{font-weight: bold;font-size: 25px;}" +
            "#widget_popup_content_nh_right_bottom {font-size: 10px;width: 47%;float: right;margin-top: -5px;line-height: 10px;}" +
            "#boton_reservar_widget {text-decoration: none;}" +
            "#no_otas {padding-top: 10px;font-size: 14px;font-weight: bold;width: 80%;margin: auto;}" +
            "@media (min-width: 1024px) and (max-width: 1280px){" +
            "#widget{width: 160px;}#widget_top{padding-left: 10px;padding-right: 10px;}}");
        hotelswidget.setAnalytics();
    }

    this.setHtml = function(price,currency) {
    }

    this.setHtmlMobile = function(price,currency, time, reserva) {
        $('body').append("<div id='widget'><div id='widget_close'></div><div id='widget_top'><div id='widget_top_left'>BEST PRICE GUARANTEED</div><div id='widget_top_right'><span id='widget_top_right_price'>263.34</span>&nbsp;<span id='widget_top_right_currency'>EUR</span></div></div><div id='widget_bottom'><div id='widget_bottom_button'>CHECK IT HERE</div></div></div><div id='widget_popup'><div id='widget_content_background'><div id='widget_popup_content'><div id='widget_popup_content_top'><span id='widget_popup_content_top_text'>Best Price Guaranteed</span><div id='widget_popup_content_top_close'></div><div style='clear:both;'></div></div><div id='widget_popup_content_nh'><!--<div id='widget_popup_content_nh_text'>Our lowest rate:</div>--><div id='widget_popup_content_nh_left' style='width:45%;'><img class='image_nh' src='https://www.123compare.me/v1/images/pages/nh_hotels.png' /><!--<span id='widget_popup_content_nh_left_text' class='t1' style='display:block;text-align:left;'>Free Internet</span><span id='widget_popup_content_nh_left_text' class='t2' style='display: block;text-align: left; font-size: xx-small;'>Earn Club Carlson Points&reg;</span>--></div><div id='widget_popup_content_nh_right' style='width:50%;'><span id='widget_popup_content_nh_right_price'>1,095.00</span>&nbsp;<span id='widget_popup_content_nh_right_currency'>SEK</span><div id='widget_popup_content_nh_right_bottom' style='width:100%;'><!--<span class='widget_popup_content_nh_right_bottom_t1' style='display:block;'>No extra fees.</span>--><span class='widget_popup_content_nh_right_bottom_t2'>No hidden charges.</span></div><div style='clear:both;'></div></div><div style='clear:both;'></div><!--<div id='widget_popup_content_nh_text_bottom'>In other sites:</div>--></div><div id='widget_popup_content_middle'><img src='https://www.123compare.me/v2/img/assets/loading.gif' class='widget_content_loading'/><div id='widget_popup_loading_text'></div></div><div id='widget_popup_content_bottom'><div id='widget_popup_content_bottom_button'>Book Now</div></div><!--<div id='widget_copyright' style='padding-top:5px;'> <a href='http://www.123compare.me' style='text-decoration: none; float:right;'><span style='color:black;font-size:10px;text-align:right;width:auto;position:relative;letter-spacing:1px;'>Powered by 123Compare.me&#169;</span></a></div>--></div></div></div>");
        if($('html').attr('lang') === 'en'){
            $('#widget_top_right_price').html((price));
            $('#widget_popup_content_nh_right_price').html((price));
        } else{
            $('#widget_top_right_price').html((price));
            $('#widget_popup_content_nh_right_price').html((price));
        }
        $('#widget_top_right_currency').html(currency);
        $('#widget_popup_content_nh_right_currency').html(currency);

        hotelswidget.setAnimation(time);
        hotelswidget.setJavaScriptMobile(reserva);
    }

    this.setAnalytics = function(){
        $('body').append("<script type='text/javascript'>" +
            "var _paq = _paq || [];" +
            "_paq.push(['trackPageView']);" +
            "_paq.push(['enableLinkTracking']);" +
            "(function() {var u='//www.123compare.me/piwik/';_paq.push(['setTrackerUrl', u+'piwik.php']);_paq.push(['setSiteId', 2]);var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];g.type='text/javascript'; g.async=true; g.defer=true; g.src=u+'piwik.js'; s.parentNode.insertBefore(g,s);" +
            "})();" +
            "</script>" +
            "<noscript><p><img src='//www.123compare.me/piwik/piwik.php?idsite=2' style='border:0;' alt='' /></p></noscript>");
    }

    this.setAnimation = function(time){
        $('#widget').animate({
            bottom: '15px'
        },5000, function(){
            if( time !== 0) {
                setTimeout(function(){
                    $('#widget').hide();
                }, time * 1000);
            }
        });
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
