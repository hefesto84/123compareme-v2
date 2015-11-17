var hotelswidget = new (function(window, document, $){
    var debug = false;
    var domain = 'https://www.123compare.me/v2'
    var data = '';
    this.init = function (showWidget, timeShow) {
        if(showWidget === false){
            hotelswidget.setAnalytics();
        } else {
        	if (timeShow !== undefined){
        		var show = timeShow;
        	} else {
        		var show = 0;
        	}
            var conversion = 1;
            var user = 1;
            var hotel = jQuery('header.criteria').find('a:eq(1)').children('div').children('div').html();
            var rooms = jQuery('[name="rateSearchForm.numberRooms"]').val();
            var guests = 2;
            var start = this.dateConverse(jQuery('[name="rateSearchForm.checkinDate"]').val());
            var stop = this.dateConverse(jQuery('[name="rateSearchForm.checkoutDate"]').val());
            var lang = utag_data.visitor_language;
            var price = this.priceConverse(jQuery('.rate-amount').first().html().trim().substring(1,jQuery('.rate-amount').first().html().trim().indexOf('<')).trim(),lang);
            var currency = jQuery('.rate-amount').first().find('span.currency').html();
            var device = 'isMobile';

            var diffDay = this.diffDate(start,stop);

            var url_post = domain + '/api/prices?base=' + price + '&code=' + user + '&hotel=' + encodeURI(hotel) + '&rooms=' + rooms + '&guests=' + guests + '&fin=' + start + '&fout=' + stop + '&lang=' + lang;

            console.log(url_post);

            if ((price !== 'undefined') && (price !== '') && (price !== 'NaN')){
                price = parseFloat(price);

                if (currency != 'EUR'){
                    conversion = hotelswidget.getCurrencyConversion(currency);
                }

                hotelswidget.setCSS();
                if(device === 'isDesktop'){
                    hotelswidget.setHtml(price,currency);
                } else {
                    hotelswidget.setHtmlMobile(price,currency,show);
                }
                hotelswidget.setTranslate(lang);
                setTimeout(function() {
                    hotelswidget.getDatos(url_post,device,price,conversion,currency,lang, diffDay);
                },1000) ;
            }
        }
    }

    this.getDatos = function(url_post,device,price,conversion,currency,lang, diffDays){
        var datos;
        $.ajax({
            type: "GET",
            url: url_post,
            success: function (respuesta) {
                console.log(respuesta);
                datos = respuesta;
                if (device === 'isMobile'){
                    hotelswidget.setWidgetMobile(datos,price,conversion, currency, diffDays);
                } else {
                    hotelswidget.setWidget(datos,price,conversion, currency);
                }
            }//,
            //async: false
        });
    }

    this.setTranslate = function(lang){
        // Les ooooques van descalces, descaaaalces descaaaaalcesss....
        // les ooooques van descalces i els aaanecs tambÃ©!
        // Posseuuuuu-lis sabaaates, sabaaatesss sabaaates
        // Posseuuuu-lis sabaaates i mitjons tambÃ©eeee

        // Listado de variables con los textos por idiomas
        var locale_de = '{"widget_top_left":"Best price guaranteed from","widget_middle_text":"Do you want to compare prices?","widget_bottom_button":"Check it here","widget_popup_content_top_text":"Best Price Guaranteed","widget_popup_content_parkinn_text":"Our lowest rate:","widget_popup_content_parkinn_left_text_t1":"Free Internet","widget_popup_content_parkinn_left_text_t2":"Earn Club Carlson Points","widget_popup_content_parkinn_text_bottom":"In other sites:","widget_popup_content_parkinn_right_bottom_t1":"No extra fees.","widget_popup_content_parkinn_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"No rooms found for these dates in other sites"}';
        var locale_es = '{"widget_top_left":"Mejor precio garantizado desde","widget_middle_text":"¿Quieres comparar los precios?","widget_bottom_button":"Comparar precio","widget_popup_content_top_text":"Mejor precio garantizado","widget_popup_content_parkinn_text":"Our lowest rate:","widget_popup_content_parkinn_left_text_t1":"Free Internet","widget_popup_content_parkinn_left_text_t2":"Earn Club Carlson Points","widget_popup_content_parkinn_text_bottom":"In other sites:","widget_popup_content_parkinn_right_bottom_t1":"Sin taxas extras.","widget_popup_content_parkinn_right_bottom_t2":"Sin cargos ocultos.", "widget_popup_content_bottom_button":"Reservar", "widget_popup_loading_text":"Buscando", "no_otas":"Sin habitaciones para estas fechas en otros sitios"}';
        var locale_en = '{"widget_top_left":"Best price guaranteed from","widget_middle_text":"Do you want to compare prices?","widget_bottom_button":"Check it here","widget_popup_content_top_text":"BEST RATE GUARANTEED","widget_popup_content_parkinn_text":"Our lowest rate:","widget_popup_content_parkinn_left_text_t1":"Free Internet","widget_popup_content_parkinn_left_text_t2":"Earn Club Carlson Points","widget_popup_content_parkinn_text_bottom":"In other sites:","widget_popup_content_parkinn_right_bottom_t1":"No extra fees.","widget_popup_content_parkinn_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"No rooms found for these dates in other sites"}';
        var locale_fr = '{"widget_top_left":"GARANTIE DU MEILLEUR PRIX","widget_middle_text":"Voulez-vous comparer les prix?","widget_bottom_button":"V&#233;rifiez ici","widget_popup_content_top_text":"GARANTIE DU MEILLEUR PRIX","widget_popup_content_parkinn_text":"Notre plus bas tarif:","widget_popup_content_parkinn_left_text_t1":"Internet gratuit","widget_popup_content_parkinn_left_text_t2":"Gagnez points Club Carlson","widget_popup_content_parkinn_text_bottom":"En d&#039;autres sites:","widget_popup_content_parkinn_right_bottom_t1":"Pas de frais suppl&#233;mentaires.","widget_popup_content_parkinn_right_bottom_t2":"Pas de frais cach&#233;s.", "widget_popup_content_bottom_button":"R&#233;server maintenant", "widget_popup_loading_text":"Regarder les rÃ©sultats", "no_otas":"No rooms found for these dates in other sites"}';
        var locale_it = '{"widget_top_left":"Best price guaranteed from","widget_middle_text":"Do you want to compare prices?","widget_bottom_button":"Check it here","widget_popup_content_top_text":"Best Price Guaranteed","widget_popup_content_parkinn_text":"Our lowest rate:","widget_popup_content_parkinn_left_text_t1":"Free Internet","widget_popup_content_parkinn_left_text_t2":"Earn Club Carlson Points","widget_popup_content_parkinn_text_bottom":"In other sites:","widget_popup_content_parkinn_right_bottom_t1":"No extra fees.","widget_popup_content_parkinn_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"No rooms found for these dates in other sites"}';
        var locale_nl = '{"widget_top_left":"Best price guaranteed from","widget_middle_text":"Do you want to compare prices?","widget_bottom_button":"Check it here","widget_popup_content_top_text":"Best Price Guaranteed","widget_popup_content_parkinn_text":"Our lowest rate:","widget_popup_content_parkinn_left_text_t1":"Free Internet","widget_popup_content_parkinn_left_text_t2":"Earn Club Carlson Points","widget_popup_content_parkinn_text_bottom":"In other sites:","widget_popup_content_parkinn_right_bottom_t1":"No extra fees.","widget_popup_content_parkinn_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"No rooms found for these dates in other sites"}';
        var locale_pt_BR = '{"widget_top_left":"Best price guaranteed from,"widget_middle_text":"Do you want to compare prices?","widget_bottom_button":"Check it here","widget_popup_content_top_text":"Best Price Guaranteed","widget_popup_content_parkinn_text":"Our lowest rate:","widget_popup_content_parkinn_left_text_t1":"Free Internet","widget_popup_content_parkinn_left_text_t2":"Earn Club Carlson Points","widget_popup_content_parkinn_text_bottom":"In other sites:","widget_popup_content_parkinn_right_bottom_t1":"No extra fees.","widget_popup_content_parkinn_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"No rooms found for these dates in other sites"}';
        var locale_ru = '{"widget_top_left":"Best price guaranteed from","widget_middle_text":"Do you want to compare prices?","widget_bottom_button":"Check it here","widget_popup_content_top_text":"Best Price Guaranteed","widget_popup_content_parkinn_text":"Our lowest rate:","widget_popup_content_parkinn_left_text_t1":"Free Internet","widget_popup_content_parkinn_left_text_t2":"Earn Club Carlson Points","widget_popup_content_parkinn_text_bottom":"In other sites:","widget_popup_content_parkinn_right_bottom_t1":"No extra fees.","widget_popup_content_parkinn_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"No rooms found for these dates in other sites"}';

        // Array de traducciones
        var translations = {
            de:locale_de,
            es:locale_es,
            en:locale_en,
            fr:locale_fr,
            it:locale_it,
            pt:locale_pt_BR,
            nl:locale_nl,
            ru:locale_ru
        };

        // Array con las traducciones por idioma
        var t = JSON.parse(unescape(translations[lang]));

        $('#widget_top_left').html(t["widget_top_left"]);
        $('#widget_middle_text').html(t["widget_middle_text"]);
        $('#widget_bottom_button').html(t["widget_bottom_button"]);
        $('#widget_popup_content_top_text').html(t["widget_popup_content_top_text"]);
        $('#widget_popup_content_parkinn_text').html(t["widget_popup_content_parkinn_text"]);
        $('.t1').html(t["widget_popup_content_parkinn_left_text_t1"]);
        $('.t2').html(t["widget_popup_content_parkinn_left_text_t2"]);
        $('#widget_popup_content_parkinn_text_bottom').html(t["widget_popup_content_parkinn_text_bottom"]);
        $('.widget_popup_content_parkinn_right_bottom_t1').html(t["widget_popup_content_parkinn_right_bottom_t1"]);
        $('.widget_popup_content_parkinn_right_bottom_t2').html(t["widget_popup_content_parkinn_right_bottom_t2"]);
        $('#widget_popup_content_bottom_button').html(t["widget_popup_content_bottom_button"]);
        $('#widget_popup_loading_text').html(t["widget_popup_loading_text"]);
        $('#no_otas').html(t["no_otas"]);

        $('#widget').show();

    }

    this.setWidget = function(datos,price,conversion, currency) {

    }

    this.setWidgetMobile = function(datos,price,conversion, currency, diffDay){
        var href = hotelswidget.setUrlHref();
        $('.widget_content_loading').hide();
        $('#widget_popup_loading_text').hide();
        data = datos;
        if (data.datos.length == 0){
            _paq.push(['trackEvent', 'Widget', 'No results', 'No se ha mostrado el widget por que no hay datos']);
            return 0;
        }
        var content_middle = document.getElementById("widget_popup_content_middle");
        var count = 0;
        for (var i = 0; i < data.datos.length; i++) {

            var precio_convertido = (parseFloat(data.datos[i].price.replace(',','.')) * conversion);
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
                span.innerHTML = precio_convertido.toFixed(2) + ' <span class="priceWidgeElementCurrency">' + currency + '</span>';
                element.appendChild(span);

                var clear = document.createElement('div');
                clear.setAttribute('style', 'clear:both;');
                element.appendChild(clear);

                content_middle.appendChild(element);
            }
        }
        if (count === 0){
            $('#widget_popup_content_middle').append("<div id='no_otas'>No rooms found for these dates in other sites</div>");
            _paq.push(['trackEvent', 'Widget', 'No results', 'No se han mostrado resultados']);
        } else {
            _paq.push(['trackEvent', 'Widget', 'Results', 'Se han mostrado '+count+' resultados']);
        }
        $('.enlace_button_popup_widget').attr('href', href);

    }

    this.setJavaScript = function(){

    }

    this.setJavaScriptMobile = function(time){
        var width_widget = jQuery('body').width() - 16;
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
            _paq.push(['trackEvent', 'Widget', 'Click', 'Widget clicado']);
        });
        $('#widget_popup_content_top_close').click(function(){
            $('#widget').show();
            $('#widget_popup').hide();
            _paq.push(['trackEvent', 'Widget', 'Hide Popup', 'Widget popup ocultado']);
        });

        $('#widget_close').click(function(){
            $('#widget').hide();
            $('#widget').unbind("click");

            _paq.push(['trackEvent', 'Widget', 'Hide', 'Widget ocultado']);
        });

        _paq.push(['trackEvent', 'Widget', 'Show', 'Widget mostrado correctamente']);

        $('#widget_popup_content_bottom_button').click(function(){
            hotelswidget.functionReservar();
        });
    }

    this.hotelName = function(name){
        var n = name.indexOf(',');
        if (n > 0){
            hotelName = name.slice(0,n);
        } else {
            hotelName = name;
        }
        return hotelName;
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


    this.functionReservar = function(pHotelCode, pRateUni, pRedemptionRate, pClearEcertCode){
        _paq.push(['trackEvent', 'Widget', 'Click Book', 'Widget Reservar']);
        $('#rp1_form').submit();
    }

    this.setCSS = function(){
        $('body').append("<style>" +
            "#widget{box-sizing: border-box;-webkit-box-sizing: border-box;-moz-box-sizing: border-box;margin-left: 8px;position: fixed;bottom: -150px;width: 100%;background-color: #EEEEEE;padding: 10px; padding-left: 0px;}" +
            "#widget_close{position: absolute;right: 0px;top: 0px;width: 25px;height: 25px;background: url('https://www.123compare.me/v1/images/close_black.png');background-size: 100% 100%;}" +
            "#widget_top{box-sizing: border-box;-webkit-box-sizing: border-box;-moz-box-sizing: border-box;width: 100%;height: 40px;}" +
            "#widget_top_left{box-sizing: border-box; -webkit-box-sizing: border-box; -moz-box-sizing: border-box;float: left;width: 56%;height: 40px;padding: 10px;padding-top:5px;padding-right: 5px;color: #000000;font-size: 12px;text-transform: uppercase;line-height: 14px;border-right: 1px solid #000000;}" +
            "#widget_top_right_price{font-size: 25px;}" +
            "#widget_top_right{box-sizing: border-box; -webkit-box-sizing: border-box; -moz-box-sizing: border-box;float: left;width: 44%;height: 40px;padding: 10px;padding-top:5px;padding-right: 5px;color: #000000;font-size: 12px;text-transform: uppercase;line-height: 30px;}" +
            "#widget_bottom{width: 100%;margin-top: 10px; padding-left: 5px;}" +
            "#widget_bottom_button{box-sizing: border-box; -webkit-box-sizing: border-box; -moz-box-sizing: border-box;width: 50%;margin: auto;background-image: -webkit-gradient(linear,left 0,left 100%,from(#f7c046),to(#f5af15));background-image: -webkit-linear-gradient(top,#f7c046,0,#f5af15,100%);background-image: -moz-linear-gradient(top,#f7c046 0,#f5af15 100%);background-image: linear-gradient(to bottom,#f7c046 0,#f5af15 100%);background-repeat: repeat-x;border-color: #f4ac0b;color: #FFFFFF;text-transform: uppercase;padding: 0.55em;text-align: center;}" +
            "#widget_popup_content_parkinn_text_bottom {margin-bottom: 0px;margin-top: 10px;text-align: left;padding-left: 10px;font-weight: bold; font-size:12px}" +
            "#widget_popup{display: none;font-family: 'Montserrat', Helvetica, Arial, sans-serif;}" +
            "#widget_content_background{position: fixed;left: 0px;top: 0px;width: 100%;height: 100%;background: rgba(0,0,0,0.5);z-index: 9999999999;}" +
            "#widget_popup_content{position: absolute;top: 50px;width: 280px;box-sizing: border-box;}" +
            "#widget_popup_content_top{background-image: -webkit-gradient(linear,left 0,left 100%,from(#00c),to(#009));background-image: -webkit-linear-gradient(top,#00c,0,#009,100%);background-image: -moz-linear-gradient(top,#00c 0,#009 100%);background-image: linear-gradient(to bottom,#00c 0,#009 100%);background-repeat: repeat-x;color: #FFFFFF;padding: 5px;text-align: center;line-height: 50px;font-size: 16px;}" +
            "#widget_popup_content_top_text{font-weight: bold;padding-left: 15px;}" +
            "#widget_popup_content_top_close{margin-bottom: -22px;float: right;width: 25px;height: 25px;background: url('https://www.123compare.me/v1/images/close.png');background-size: 100% 100%;}" +
            "#widget_popup_content_parkinn{padding: 10px;padding-bottom: 0px;background-color: #FFFFFF;}" +
            "#widget_popup_content_parkinn_text {padding-left: 10px;font-size: 12px;margin-bottom: 10px;text-align: left;font-family: 'Montserrat', Helvetica, Arial, sans-serif;font-weight: bold;}" +
            "#widget_popup_content_middle{padding: 10px;padding-top: 0px;background-color: #FFFFFF;}" +
            ".widget_content_loading{display:block;margin:auto;padding-top:15px;}" +
            "#widget_popup_loading_text{margin-top: 10px;font-family: 'Montserrat', Helvetica, Arial, sans-serif;font-size: 11px; text-align: center;}" +
            "#widget_popup_content_bottom{padding: 10px;background-color: #FFFFFF;}" +
            "#widget_popup_content_bottom_button{width: 60%;text-align:center;color: #FFFFFF;margin: auto;background-image: -webkit-gradient(linear,left 0,left 100%,from(#f7c046),to(#f5af15));background-image: -webkit-linear-gradient(top,#f7c046,0,#f5af15,100%);background-image: -moz-linear-gradient(top,#f7c046 0,#f5af15 100%);background-image: linear-gradient(to bottom,#f7c046 0,#f5af15 100%);background-repeat: repeat-x;padding: 0.55em;padding-left: 20px;padding-right: 20px;font-size: 18px;font-weight: bold;margin-top: 10px;font-family: 'Montserrat', Helvetica, Arial, sans-serif;}" +
            "#element{padding: 10px;border-bottom: 1px solid #EBEAEC;}" +
            "#element img{width: auto;float: left;height: 22px;}" +
            "#element .currency{float: left;line-height: 22px;color: #4A4B4C;font-family: 'Montserrat', Helvetica, Arial, sans-serif;}" +
            "#element .text{text-align: center;font-size: 12px;color: #4A4B4C;font-family: 'Montserrat', Helvetica, Arial, sans-serif;}" +
            "#element .priceWidgetElement {float: right;font-family: 'Montserrat', Helvetica, Arial, sans-serif;margin-left: 5px;line-height: 22px;color: #4A4B4C;font-size: 18px;}" +
            "#element .priceWidgetElement .priceWidgeElementCurrency{font-size:13px;}" +
            ".image_parkinn{width: 100px;height: auto;display: block;margin-bottom: 5px;}" +
            "#widget_popup_content_parkinn_left{padding-left: 10px;width: 40%;box-sizing: border-box;float: left;line-height: 10px;}" +
            "#widget_popup_content_parkinn_left_text {font-size: 10px;font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;}" +
            "#widget_popup_content_parkinn_right{padding-right: 10px;width: 60%;box-sizing: border-box;float: right;text-align: right;font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;color: #f5af15;line-height: 30px;}" +
            "#widget_popup_content_parkinn_right_price{font-weight: bold;font-size: 25px;}" +
            "#widget_popup_content_parkinn_right_bottom {font-size: 10px;width: 47%;float: right;margin-top: 4px;line-height: 10px;}" +
            "#boton_reservar_widget {text-decoration: none;}" +
            "#no_otas {padding-top: 10px;font-size: 14px;font-weight: bold;width: 80%;margin: auto;}" +
            "@media (min-width: 1024px) and (max-width: 1280px){" +
            "#widget{width: 160px;}#widget_top{padding-left: 10px;padding-right: 10px;}}");
        hotelswidget.setAnalytics();
    }

    this.setHtml = function(price,currency) {
    }

    this.setHtmlMobile = function(price,currency, time) {
        $('body').append("<div id='widget'><div id='widget_close'></div><div id='widget_top'><div id='widget_top_left'>BEST PRICE GUARANTEED</div><div id='widget_top_right'><span id='widget_top_right_price'>263.34</span>&nbsp;<span id='widget_top_right_currency'>EUR</span></div></div><div id='widget_bottom'><div id='widget_bottom_button'>CHECK IT HERE</div></div></div><div id='widget_popup'><div id='widget_content_background'><div id='widget_popup_content'><div id='widget_popup_content_top'><span id='widget_popup_content_top_text'>Best Price Guaranteed</span><div id='widget_popup_content_top_close'></div><div style='clear:both;'></div></div><div id='widget_popup_content_parkinn'><div id='widget_popup_content_parkinn_text'>Our lowest rate:</div><div id='widget_popup_content_parkinn_left' style='width:45%;'><img class='image_parkinn' src='https://www.123compare.me/v1/images/pages/parkinn.png' /><span id='widget_popup_content_parkinn_left_text' class='t1' style='display:block;text-align:left;'>Free Internet</span><span id='widget_popup_content_parkinn_left_text' class='t2' style='display: block;text-align: left; font-size: xx-small;'>Earn Club Carlson Points&reg;</span></div><div id='widget_popup_content_parkinn_right' style='width:50%;'><span id='widget_popup_content_parkinn_right_price'>1,095.00</span>&nbsp;<span id='widget_popup_content_parkinn_right_currency'>SEK</span><div id='widget_popup_content_parkinn_right_bottom' style='width:100%;'><span class='widget_popup_content_parkinn_right_bottom_t1' style='display:block;'>No extra fees.</span><span class='widget_popup_content_parkinn_right_bottom_t2'>No hidden charges.</span></div><div style='clear:both;'></div></div><div style='clear:both;'></div><div id='widget_popup_content_parkinn_text_bottom'>In other sites:</div></div><div id='widget_popup_content_middle'><img src='https://www.123compare.me/v1/images/loading.GIF' class='widget_content_loading'/><div id='widget_popup_loading_text'></div></div><div id='widget_popup_content_bottom'><a href='' class='enlace_button_popup_widget'><div id='widget_popup_content_bottom_button'>Book Now</div></a></div><div id='widget_copyright' style='padding-top:5px;'> <a href='http://www.123compare.me' style='text-decoration: none; float:right;'><span style='color:black;font-size:10px;text-align:right;width:auto;position:relative;letter-spacing:1px;'>Powered by 123Compare.me&#169;</span></a></div></div></div></div>");
        if($('html').attr('lang') === 'en'){
            $('#widget_top_right_price').html((price.toFixed(2)).replace(',','.'));
            $('#widget_popup_content_parkinn_right_price').html((price.toFixed(2)).replace(',','.'));
        } else{
            $('#widget_top_right_price').html((price.toFixed(2)).replace('.',','));
            $('#widget_popup_content_parkinn_right_price').html((price.toFixed(2)).replace('.',','));
        }
        $('#widget_top_right_currency').html(currency);
        $('#widget_popup_content_parkinn_right_currency').html(currency);

        hotelswidget.setAnimation(time);
        hotelswidget.setJavaScriptMobile();
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

    this.diffDate = function (fini,fout){
        var date1 = new Date(fini.slice(6,10),fini.slice(3,5),fini.slice(0,2));
        var date2 = new Date(fout.slice(6,10),fout.slice(3,5),fout.slice(0,2));
        var timeDiff = Math.abs(date2.getTime() - date1.getTime());
        return diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));
    }

    this.setUrlHref = function(){
        var href = jQuery('#LOWESTRATES').find('li').first().find('a').first().attr('href');
        return href;
    }

})(window, document, $);

//$(window).bind('orientationchange', hotelswidget.establecerOrientacion);