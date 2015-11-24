var hotelswidget = new (function(window, document, $){

    var domain = 'https://www.123compare.me/v2'
    var data = '';
    this.init = function (showWidget, parameters) {
        if(showWidget === false){
            hotelswidget.setAnalytics();
        } else {
            var conversion = 1;
            var hotel = 'The May Fair Hotel';
            var rooms = parameters.rooms;
            var guests = parameters.guests;
            var start = parameters.start;
            var stop = parameters.stop;
            var currency = '£';
            var lang = 'en';
            var device = 'isDesktop';
            if($('html').hasClass('mobile')){
                device = 'isMobile';
                price = hotelswidget.findPriceMobile();
            } else {
                var price = hotelswidget.findPrice();
            }

            var url_post = domain + '/api/prices?currency=GBP&base=' + price + '&hotel=' + encodeURI(hotel) + '&rooms=' + rooms + '&guests=' + guests + '&fin=' + start + '&fout=' + stop + '&lang=' + lang;

            console.log(url_post);


            if ((price !== 'undefined') && (price !== '') && (price !== 'NaN')){
                price = parseFloat(price);

                if(device === 'isDesktop'){
                    hotelswidget.setCSS();
                    hotelswidget.setHtml(price,currency);
                } else {
                    hotelswidget.setCSSMobile();
                    hotelswidget.setHtmlMobile(price,currency);
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
                //console.log(respuesta);
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
        // les ooooques van descalces i els aaanecs també!
        // Posseuuuuu-lis sabaaates, sabaaatesss sabaaates
        // Posseuuuu-lis sabaaates i mitjons tambéeeee

        // Listado de variables con los textos por idiomas
        var locale_en = '{"widget123_top_top":"Book directly with best price","widget123_top_text":"Our price"}';

        // Array de traducciones
        var translations = {
            en:locale_en,
        };

        // Array con las traducciones por idioma
        var t = JSON.parse(unescape(encodeURIComponent(translations[lang])));

        jQuery('#widget123_top_top').html(t["widget123_top_top"]);
        jQuery('#widget123_top_top_text').html(t["widget123_top_text"]);
    }

    this.setWidget = function(datos,price,conversion, currency) {
        data = datos;
        if (data.datos.length == 0){
            _paq.push(['trackEvent', 'Widget', 'No results', 'No se ha mostrado el widget por que no hay datos']);
            return 0;
        }
        var content_middle = document.getElementById("widget123_middle");
        var count = 0;
        for (var i = 0; i < data.datos.length; i++) {

            var precio_convertido = (parseFloat(data.datos[i].price.replace(',','.')) * conversion);

            if (((Math.round(price) - 1) < Math.round(precio_convertido) || (Math.round(price) - 1) == Math.round(precio_convertido)) &&  (count < 5)) {
                count = count + 1;
                var element = document.createElement("div");
                element.setAttribute('id', 'element');

                var left = document.createElement("div");
                left.setAttribute('id', 'element_left');
                left.innerHTML = '<span class="nombre_ota">'+data.datos[i].site.replace('.png','')+'</span>';
                element.appendChild(left);

                var right = document.createElement("div");
                right.setAttribute('id', 'element_right');
                element.appendChild(right);

                var span = document.createElement('span');
                span.setAttribute('class', 'priceWidgetElement');
                span.innerHTML = '<span class="priceWidgeElementCurrency">' + currency + '</span>' + Math.round(precio_convertido);
                right.appendChild(span);

                var clear = document.createElement('div');
                clear.setAttribute('style', 'clear:both;');
                element.appendChild(clear);

                content_middle.appendChild(element);
            }
        }
        if (count === 0){
            _paq.push(['trackEvent', 'Widget', 'No results', 'No se han mostrado resultados']);
        } else {
            _paq.push(['trackEvent', 'Widget', 'Results', 'Se han mostrado '+count+' resultados']);
            $('#widget123').show();
        }
    }

    this.setWidgetMobile = function(datos,price,conversion, currency){

        $('.widget123_content_loading').hide();
        $('#widget123_popup_loading_text').hide();
        data = datos;
        if (data.datos.length == 0){
            _paq.push(['trackEvent', 'Widget', 'No results', 'No se ha mostrado el widget por que no hay datos']);
            return 0;
        }
        var content_middle = document.getElementById("widget123_popup_content_middle");
        var count = 0;
        for (var i = 0; i < data.datos.length; i++) {

            var precio_convertido = (parseFloat(data.datos[i].price.replace(',','.')) * conversion);
            precio_convertido = Math.round(precio_convertido);

            if (((Math.round(price) - 1) < precio_convertido || (Math.round(price) - 1) == precio_convertido) &&  (count < 5)) {

                count = count + 1;
                var element = document.createElement("div");
                element.setAttribute('id', 'element');

                var left = document.createElement("div");
                left.setAttribute('id', 'element_left');
                left.innerHTML = '<span class="nombre_ota">'+data.datos[i].site.replace('.png','')+'</span>';
                element.appendChild(left);

                var right = document.createElement("div");
                right.setAttribute('id', 'element_right');
                element.appendChild(right);

                var span = document.createElement('span');
                span.setAttribute('class', 'priceWidgetElement');
                span.innerHTML = '<span class="priceWidgeElementCurrency">' + currency + '</span>' + Math.round(precio_convertido);
                right.appendChild(span);

                var clear = document.createElement('div');
                clear.setAttribute('style', 'clear:both;');
                element.appendChild(clear);

                content_middle.appendChild(element);

            }
        }
        if (count === 0){
            $('#widget123_popup_content_middle').append("<div id='no_otas'>No rooms found for these dates in other sites</div>");
            _paq.push(['trackEvent', 'Widget', 'No results', 'No se han mostrado resultados']);
        } else {
            _paq.push(['trackEvent', 'Widget', 'Results', 'Se han mostrado '+count+' resultados']);
        }

    }

    this.setJavaScript = function(){
        var top_widget = 200;
        $('#widget123').css({
            'top' : top_widget
        });

        $('#widget123_top').click(function(){
            if ($('#widget123_middle').is(':hidden')){
                $('#widget123_middle').slideDown(1000);
            } else {
                $('#widget123_middle').slideUp(1000);
            }
        });

        _paq.push(['trackEvent', 'Widget', 'Show', 'Widget mostrado correctamente']);
    }

    this.setJavaScriptMobile = function(){

        var width_widget = $(window).width() - 16;
        $('#widget123').css({
            'width' : width_widget
        });

        var screen_width = $(window).width();
        var screen_height = $(window).height();

        var popup_width = $('#widget123_popup_content').width();
        var popup_height = $('#widget123_popup_content').height();
        $('#widget123_popup_content').css({
            'left' : ((screen_width - popup_width)/2)
        });


        $('#widget123').click(function(){
            $('#widget123_popup').show();
            $('#widget123').hide();
            _paq.push(['trackEvent', 'Widget', 'Click', 'Widget clicado']);
        });
        $('#widget123_popup_content_top_close').click(function(){
            $('#widget123').show();
            $('#widget123_popup').hide();
            _paq.push(['trackEvent', 'Widget', 'Hide Popup', 'Widget popup ocultado']);
        });

        $('#widget123_close').click(function(){
            $('#widget123').hide();
            $('#widget123').unbind("click");

            _paq.push(['trackEvent', 'Widget', 'Hide', 'Widget ocultado']);
        });

        _paq.push(['trackEvent', 'Widget', 'Show', 'Widget mostrado correctamente']);

    }

    this.dateConverse = function(date){
        converseDate = date.slice(0,2)+'/'+date.slice(3,5)+'/'+date.slice(6,10);
        return converseDate;
    }

    this.setCSS = function(){

        $('body').append("<style>" +
            "#widget123{position: fixed;top: 100px;right: 0px;width: 210px;display: none;box-sizing: border-box;cursor: pointer;z-index: 9999}" +
            "#widget123_top_top{line-height: 15px;padding-top: 15px; padding-bottom: 15px;padding-left: 5px;padding-right: 5px; background-color: #7C7766; text-align: center; color: #FFFFFF; border-bottom: 1px solid #A5A186;font-weight: bold;font-size: 11px;}" +
            "#widget123_top{padding-top: 3px;padding-bottom: 8px;background-color: #7C7766;color: #ECE7B6;text-align: center;}" +
            "#widget123_top_top_text{font-size: 11px;margin-bottom: 5px; font-weight: 100;}" +
            ".alta-flecha{background-image: url('data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAWCAYAAADXYyzPAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyNpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDE0IDc5LjE1MTQ4MSwgMjAxMy8wMy8xMy0xMjowOToxNSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIChNYWNpbnRvc2gpIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjUwRDA3RDg3NzE4ODExRTU5MERCRDZDNjRGNDJGMDU5IiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOjUwRDA3RDg4NzE4ODExRTU5MERCRDZDNjRGNDJGMDU5Ij4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6NTBEMDdEODU3MTg4MTFFNTkwREJENkM2NEY0MkYwNTkiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6NTBEMDdEODY3MTg4MTFFNTkwREJENkM2NEY0MkYwNTkiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz6bjZ3wAAABaElEQVR42mL8//8/w0AAJoYBAsgWhwDxFyC+C8TqVLRDA4hvQ80OgYuCghqKH/9HgJtALIgkRy4GmXEbydynMDlkRb//o4JdQMxMgaUgvXvQzPyNzeIN/zHBBAosnozFvA3YLJYA4kdYFKeQYWkaFnMeArE4NotB2BCIv6Fp+AXEdiRYag/Vgwy+ArEBsjpsGkOxuPYVECsQYSlIzRss+oPR1eIyoAGL5otAzIPHUl4gvoxFXx029bgMYQTiNTgSByMW9UxAvBGL+pU41DPgCzYuID6PxbBWLGrbsag7BzWDgVSLQVgOiF9iMTQSSU0UFvkXQCyLz2xiUqkVEP9EMxiU8k2A2AyIv6PJgdRaEDKX2CySgMVXoOLvGRbxWGLMJKVQ6PlPGHQRax6pZe82PJZuJaVsJ7Uo5Afi61gsvQbEfKSYRU7hrwrE75AsfQvEyqSaQ27NAyqPX0OLUntyzGAccW0ugAADABmgl1ZVu0KpAAAAAElFTkSuQmCC');}" +
            ".flecha{width: 15px; height: 11px; margin: auto; margin-top: 5px; background-size: 100% 100%;}" +
            "#widget123_top_bottom_left{width: 15%; height: 20px; float:left;}" +
            "#widget123_top_bottom_price{width: 70%; line-height: 20px; float:left;}" +
            "#widget123_top_bottom_right{width: 15%; height: 20px; float:left;}" +
            "#widget123_top_bottom_price #price{font-size: 22px;font-weight: bold;}" +
            "#widget123_top_bottom_price #currency{font-size: 13px;font-weight: bold;}" +
            "#widget123_middle{margin-top: 0px;background-color: #212121;}" +
            "#element{padding: 10px; padding-left: 0px; padding-right: 0px; border-bottom: 1px solid #212121;color: #D7CBBC;}" +
            "#element:last-child{border-bottom: 0px}" +
            "#element_left{width: 45%; float: left; text-align: right; font-size: 12px; text-transform: capitalize; line-height: 22px;}" +
            "#element_right{width: 50%; float: right; text-align: left;}" +
            "#element #element_right .priceWidgetElement {margin-left: 20px;line-height: 20px;color: #D7CBBC;font-size: 15px;}" +
            "#element #element_right .priceWidgetElement .priceWidgeElementCurrency{font-size: 12px;}" +
            "#widget123_copyright{font-size: 10px; text-align:center;font-weight:bold; width: 100%;}" +
            "#widget123_copyright a{color: #757573;}" +
            "#widget123_copyright span{color: #757573;}" +
            "@media (min-width: 0px) and (max-width: 1400px){" +
            "#widget123{width: 180px;}"+
            "#widget123_top_top{padding-top: 10px;padding-bottom: 10px;}" +
            "#widget123_top_top_text{margin-bottom: 0px;}" +
            "#widget123_top_bottom_price #price{font-size: 17px;}" +
            "#element{padding: 5px;}" +
            "}");
        hotelswidget.setAnalytics();
    }

    this.setCSSMobile = function(){

        $('body').append("<style>" +
            "#widget123{box-sizing: border-box;-webkit-box-sizing: border-box;-moz-box-sizing: border-box;margin-left: 8px;position: fixed;bottom: -150px;width: 100%;background-color: #212121;padding: 10px; padding-left: 0px;}" +
            "#widget123_close{position: absolute;right: 0px;top: 0px;width: 25px;height: 25px;background: url('https://www.123compare.me/v1/images/close.png');background-size: 100% 100%;}" +
            "#widget123_top{box-sizing: border-box;-webkit-box-sizing: border-box;-moz-box-sizing: border-box;width: 100%;height: 40px;}" +
            "#widget123_top_left{box-sizing: border-box; -webkit-box-sizing: border-box; -moz-box-sizing: border-box;float: left;width: 56%;height: 40px;padding: 10px;padding-top:5px;padding-right: 5px;color: #FFFFFF;font-size: 15px;line-height: 15px;border-right: 1px solid #FFFFFF;}" +
            "#widget123_top_right_price{font-size: 25px;}" +
            "#widget123_top_right{box-sizing: border-box; -webkit-box-sizing: border-box; -moz-box-sizing: border-box;float: left;width: 44%;height: 40px;padding: 10px;padding-top:5px;padding-right: 5px;color: #FFFFFF;font-size: 12px;text-transform: uppercase;line-height: 30px;}" +
            "#widget123_bottom{width: 100%;margin-top: 10px; padding-left: 5px;}" +
            "#widget123_bottom_button{box-sizing: border-box; -webkit-box-sizing: border-box; -moz-box-sizing: border-box;width: 50%;margin: auto;background-color: #7C7766;color: #F1EAC0;font-size: 14px;padding: 0.55em;text-align: center;}" +
            "#widget123_popup_content_mayfair_text_bottom {margin-bottom: 0px;margin-top: 10px;text-align: left;padding-left: 10px;font-weight: bold;}" +
            "#widget123_popup{display: none;font-family: 'Montserrat', Helvetica, Arial, sans-serif;}" +
            "#widget123_content_background{position: fixed;left: 0px;top: 0px;width: 100%;height: 100%;background: rgba(0,0,0,0.5);z-index: 9999999999;}" +
            "#widget123_popup_content{position: absolute;top: 50px;width: 240px;box-sizing: border-box;}" +
            "#widget123_popup_content_top{background-color: #7C7766 !important;color: #FFFFFF;padding: 5px;text-align: center;line-height: 50px;font-size: 13px;}" +
            "#widget123_popup_content_top_text{font-weight: bold;padding-left: 15px;}" +
            "#widget123_popup_content_top_close{margin-bottom: -22px;float: right;width: 25px;height: 25px;background: url('https://www.123compare.me/v1/images/close.png');background-size: 100% 100%;}" +
            "#widget123_popup_content_mayfair{padding: 10px;padding-bottom: 0px;background-color: #212121;}" +
            "#widget123_popup_content_mayfair_text {padding-left: 10px;font-size: 12px;margin-bottom: 10px;text-align: left;font-family: 'Montserrat', Helvetica, Arial, sans-serif;font-weight: bold;}" +
            "#widget123_popup_content_middle{padding: 10px;padding-top: 0px;background-color: #212121;}" +
            ".widget123_content_loading{display:block;margin:auto;padding-top:15px;}" +
            "#widget123_popup_loading_text{margin-top: 10px;font-family: 'Montserrat', Helvetica, Arial, sans-serif;font-size: 11px; text-align: center;}" +
            "#element{padding: 10px;border-bottom: 1px solid #EBEAEC;}" +
            "#element:last-child{border-bottom: 0px}" +
            "#element_left{width: 45%; float: left; text-align: right; font-size: 16px; text-transform: capitalize; line-height: 22px; color: #D7CBBC;}" +
            "#element_right{width: 50%; float: right; text-align: left; color: #D7CBBC;}" +
            "#element #element_right .priceWidgetElement {margin-left: 20px;line-height: 20px;font-size: 15px;}" +
            "#element #element_right .priceWidgetElement .priceWidgeElementCurrency{font-size: 12px;}" +
            "#widget123_popup_content_mayfair_left{padding-left: 10px;width: 40%;box-sizing: border-box;float: left;line-height: 10px;}" +
            "#widget123_popup_content_mayfair_left_text {font-size: 10px;font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;}" +
            "#widget123_popup_content_mayfair_right{padding-right: 10px;width: 60%;box-sizing: border-box;float: right;text-align: right;font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;color: #D7CBBC;line-height: 30px; margin-top: 5px;}" +
            "#widget123_popup_content_mayfair_right_price{font-weight: bold;font-size: 25px;}" +
            "#widget123_popup_content_mayfair_right_bottom {font-size: 10px;width: 47%;float: right;margin-top: -5px;line-height: 10px;}" +
            "#boton_reservar_widget123 {text-decoration: none;}" +
            "#no_otas {padding-top: 10px;font-size: 14px;font-weight: bold;width: 80%;margin: auto;text-align: center;}");
        hotelswidget.setAnalytics();

    }

    this.setHtml = function(price,currency) {
        $('body').append("<div id='widget123'><div id='widget123_top_top'></div><div id='widget123_top'><div id='widget123_top_top_text'></div><div id='widget123_top_bottom_left'><div class='flecha alta-flecha'></div></div><div id='widget123_top_bottom_price'><span id='currency'>SEK</span><span id='price'>850.00</span></div><div id='widget123_top_bottom_right'><div class='flecha alta-flecha'></div></div><div style='clear:both;'></div></div><div id='widget123_middle'></div><div id='widget123_copyright' style='padding-top:2px;'><a href='http://www.123compare.me' style='text-decoration: none;'>Powered by&nbsp;<span>123compare.me</span></a></div></div>");

        $('#widget123_top_bottom_price').find('#price').html((Math.round(price)));
        $('#widget123_top_bottom_price').find('#currency').html(currency);
        hotelswidget.setJavaScript();
    }

    this.setHtmlMobile = function(price,currency) {

        $('body').append("<div id='widget123'><div id='widget123_close'></div><div id='widget123_top'><div id='widget123_top_left'>BEST PRICE GUARANTEED</div><div id='widget123_top_right'><span id='widget123_top_right_price'>263.34</span>&nbsp;<span id='widget123_top_right_currency'>EUR</span></div></div><div id='widget123_bottom'><div id='widget123_bottom_button'>CHECK IT HERE</div></div></div><div id='widget123_popup'><div id='widget123_content_background'><div id='widget123_popup_content'><div id='widget123_popup_content_top'><span id='widget123_popup_content_top_text'>Best Price Guaranteed</span><div id='widget123_popup_content_top_close'></div><div style='clear:both;'></div></div><div id='widget123_popup_content_mayfair'><!--<div id='widget123_popup_content_mayfair_text'>Our lowest rate:</div>--><div id='widget123_popup_content_mayfair_left' style='width:45%;'><img class='image_mayfair' src='https://www.123compare.me/v1/images/pages/mayfair.png' /><!--<span id='widget123_popup_content_mayfair_left_text' class='t1' style='display:block;text-align:left;'>Free Internet</span><span id='widget123_popup_content_mayfair_left_text' class='t2' style='display: block;text-align: left; font-size: xx-small;'>Earn Club Carlson Points&reg;</span>--></div><div id='widget123_popup_content_mayfair_right' style='width:50%;'><span id='widget123_popup_content_mayfair_right_price'>1,095.00</span>&nbsp;<span id='widget123_popup_content_mayfair_right_currency'>SEK</span><div id='widget123_popup_content_mayfair_right_bottom' style='width:100%;'><span class='widget123_popup_content_mayfair_right_bottom_t1' style='display:block;'>No extra fees.</span><span class='widget123_popup_content_mayfair_right_bottom_t2'>No hidden charges.</span></div><div style='clear:both;'></div></div><div style='clear:both;'></div><!--<div id='widget123_popup_content_mayfair_text_bottom'>In other sites:</div>--></div><div id='widget123_popup_content_middle'><img src='https://www.123compare.me/v2/img/assets/loading.gif' class='widget123_content_loading'/><div id='widget123_popup_loading_text'></div></div><div id='widget123_copyright' style='padding-top:5px;'> <a href='http://www.123compare.me' style='text-decoration: none; float:right;'><span style='color:black;font-size:10px;text-align:right;width:auto;position:relative;letter-spacing:1px;'>Powered by 123Compare.me&#169;</span></a></div></div></div></div>");
        if($('html').attr('lang') === 'en'){
            $('#widget123_top_right_price').html((price));
            $('#widget123_popup_content_mayfair_right_price').html((price));
        } else{
            $('#widget123_top_right_price').html((price));
            $('#widget123_popup_content_mayfair_right_price').html((price));
        }
        $('#widget123_top_right_currency').html(currency);
        $('#widget123_popup_content_mayfair_right_currency').html(currency);

        hotelswidget.setAnimation();
        hotelswidget.setJavaScriptMobile();

    }

    this.setAnalytics = function(){
        $('body').append("<script type='text/javascript'>" +
            "var _paq = _paq || [];" +
            "_paq.push(['trackPageView']);" +
            "_paq.push(['enableLinkTracking']);" +
            "(function() {var u='//www.123compare.me/piwik/';_paq.push(['setTrackerUrl', u+'piwik.php']);_paq.push(['setSiteId', 5]);var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];g.type='text/javascript'; g.async=true; g.defer=true; g.src=u+'piwik.js'; s.parentNode.insertBefore(g,s);" +
            "})();" +
            "</script>" +
            "<noscript><p><img src='//www.123compare.me/piwik/piwik.php?idsite=5' style='border:0;' alt='' /></p></noscript>");
    }

    this.findPrice = function(){
        var cheap_price = '';
        $('.summary').each(function(){
            $.total = $(this).find('div.price').find('.total');
            if ($.total.find('del').length){
                var price = parseFloat($.total.html().substring(($.total.html().indexOf("</del>") + 8),$.total.html().length).replace(',',''));
                if ((cheap_price === '') || (price < cheap_price)){
                    cheap_price = price;
                }
            } else {
                var price = parseFloat($.total.html().substring(9,$.total.html().length).replace(',',''));
                if ((cheap_price === '') || (price < cheap_price)){
                    cheap_price = price;
                }
            }
        });

        return cheap_price;
    }

    this.findPriceMobile = function(){
        var cheap_price = '';

        $('.article').each(function(){
            $.total = $(this).find('div.fleft').find('b');
            var price = parseFloat($.total.html().substring(($.total.html().indexOf("£") + 1),$.total.html().length).replace(',',''));
            if ((cheap_price === '') || (price < cheap_price)){
                cheap_price = price;
            }
        });

        return cheap_price;
    }

    this.setAnimation = function(){
        $('#widget123').animate({
            bottom: '15px'
    },5000, function(){
            setTimeout(function(){
                $('#widget123').hide();
            }, 20 * 1000);
        });
    }

})(window, document, $);