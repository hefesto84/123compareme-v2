var hotelswidget = new (function(window, document, $){

    var domain = 'https://www.123compare.me/v2'
    var data = '';
    this.init = function (showWidget) {
        if(showWidget === false){
            hotelswidget.setAnalytics();
        } else {
            var conversion = 1;
            var hotel = 'Hotel Duquesa de Cardona';
            var rooms = 1;
            var guests = parseInt($('#form_pax').find(":selected").html());
            var start = hotelswidget.dateConverse($('#form_startdate').val());
            var stop = hotelswidget.dateConverse($('#form_enddate').val());

            var price = hotelswidget.findPrice();

            var currency = 'EUR';
            var lang = $('html').attr('lang');

            var device = 'isDesktop';

            var url_post = domain + '/api/prices?base=' + price + '&hotel=' + encodeURI(hotel) + '&rooms=' + rooms + '&guests=' + guests + '&fin=' + start + '&fout=' + stop + '&lang=' + lang;

            console.log(url_post);

            //hotelswidget.setRotation();

            if ((price !== 'undefined') && (price !== '') && (price !== 'NaN')){
                price = parseFloat(price);

                hotelswidget.setCSS();
                if(device === 'isDesktop'){
                    hotelswidget.setHtml(price,currency);
                } else {
                    hotelswidget.setHtmlMobile(price,currency,show);
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
        var locale_da = '{"widget_top_top":"Book directly with best prices","widget_top_text":"Our price"}';
        var locale_de = '{"widget_top_top":"Book directly with best prices","widget_top_text":"Our price"}';
        var locale_es = '{"widget_top_top":"Reserva directamente con los mejores precios","widget_top_text":"Nuestro mejor precio"}';
        var locale_en = '{"widget_top_top":"Book directly with best prices","widget_top_text":"Our price"}';
        var locale_en_UK = '{"widget_top_top":"Book directly with best prices","widget_top_text":"Our price"}';
        var locale_fr = '{"widget_top_top":"Book directly with best prices","widget_top_text":"Our price"}';
        var locale_it = '{"widget_top_top":"Book directly with best prices","widget_top_text":"Our price"}';
        var locale_nl = '{"widget_top_top":"Book directly with best prices","widget_top_text":"Our price"}';
        var locale_no = '{"widget_top_top":"Book directly with best prices","widget_top_text":"Our price"}';
        var locale_pt_BR = '{"widget_top_top":"Book directly with best prices","widget_top_text":"Our price"}';
        var locale_ru = '{"widget_top_top":"Book directly with best prices","widget_top_text":"Our price"}';
        var locale_sv = '{"widget_top_top":"Book directly with best prices","widget_top_text":"Our price"}';
        var locale_cn = '{"widget_top_top":"Book directly with best prices","widget_top_text":"Our price"}';

        // Array de traducciones
        var translations = {
            da:locale_da,
            de:locale_de,
            es:locale_es,
            en:locale_en,
            en_UK:locale_en_UK,
            fr:locale_fr,
            it:locale_it,
            nl:locale_nl,
            no:locale_no,
            pt_BR:locale_pt_BR,
            nl:locale_nl,
            ru:locale_ru,
            sv:locale_sv,
            cn:locale_cn
        };

        // Array con las traducciones por idioma
        var t = JSON.parse(unescape(encodeURIComponent(translations[lang])));

        jQuery('#widget_top_top').html(t["widget_top_top"]);
        jQuery('#widget_top_top_text').html(t["widget_top_text"]);
        jQuery('#widget_middle_text').html(t["widget_middle_text"]);
        jQuery('#widget_middle_button').html(t["widget_middle_button"]);
        jQuery('#widget_popup_content_top_text').html(t["widget_popup_content_top_text"]);
        jQuery('#widget_popup_content_parkinn_text').html(t["widget_popup_content_parkinn_text"]);
        jQuery('.t1').html(t["widget_popup_content_parkinn_left_text_t1"]);
        jQuery('.t2').html(t["widget_popup_content_parkinn_left_text_t2"]);
        jQuery('#widget_popup_content_parkinn_text_bottom').html(t["widget_popup_content_parkinn_text_bottom"]);
        jQuery('.widget_popup_content_parkinn_right_bottom_t1').html(t["widget_popup_content_parkinn_right_bottom_t1"]);
        jQuery('.widget_popup_content_parkinn_right_bottom_t2').html(t["widget_popup_content_parkinn_right_bottom_t2"]);
        jQuery('#widget_popup_content_bottom_button').html(t["widget_popup_content_bottom_button"]);
        jQuery('#widget_popup_loading_text').html(t["widget_popup_loading_text"]);
        jQuery('#no_otas').html(t["no_otas"]);

    }

    this.setWidget = function(datos,price,conversion, currency) {
        data = datos;
        if (data.datos.length == 0){
            _paq.push(['trackEvent', 'Widget', 'No results', 'No se ha mostrado el widget por que no hay datos']);
            return 0;
        }
        var content_middle = document.getElementById("widget_middle");
        var count = 0;
        for (var i = 0; i < data.datos.length; i++) {

            var precio_convertido = (parseFloat(data.datos[i].price.replace(',','.')) * conversion);

            if (((price - 1) < precio_convertido || (price - 1) == precio_convertido) &&  (count < 5)) {
                count = count + 1;

                var element = document.createElement("div");
                element.setAttribute('id', 'element');

                var left = document.createElement("div");
                left.setAttribute('id', 'element_left');
                element.appendChild(left);

                var image = document.createElement('img');
                image.setAttribute('src', domain + '/img/' + data.datos[i].site);
                left.appendChild(image);

                var right = document.createElement("div");
                right.setAttribute('id', 'element_right');
                element.appendChild(right);

                var span = document.createElement('span');
                span.setAttribute('class', 'priceWidgetElement');
                span.innerHTML = precio_convertido.toFixed(2) + '<span class="priceWidgeElementCurrency">' + currency + '</span>';
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
            $('#widget').show();
        }
    }

    this.setWidgetMobile = function(datos,price,conversion, currency){

    }

    this.setJavaScript = function(){
        var top_widget = jQuery('.cos.container').offset().top;

        $('#widget').css({
            'top' : top_widget
        });


        var screen_width = $(window).width();
        var screen_height = $(window).height();

        var popup_width = $('#widget_popup_content').width();
        var popup_height = $('#widget_popup_content').height();
        $('#widget_popup_content').css({
            'left' : ((screen_width - popup_width)/2)
        });

        $('#widget_top').click(function(){
            if ($('#widget_middle').is(':hidden')){
                $('#widget_middle').slideDown(1000);
            } else {
                $('#widget_middle').slideUp(1000);
            }
        });

        _paq.push(['trackEvent', 'Widget', 'Show', 'Widget mostrado correctamente']);
    }

    this.setJavaScriptMobile = function(time){
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
        converseDate = date.slice(0,2)+'/'+date.slice(3,5)+'/'+date.slice(6,10);
        return converseDate;
    }


    /*this.functionReservar = function(pHotelCode, pRateUni, pRedemptionRate, pClearEcertCode){
        _paq.push(['trackEvent', 'Widget', 'Click Book', 'Widget Reservar']);
        $('#rp1_form').submit();
    }*/

    this.setCSS = function(){

        $('body').append("<style>" +
            "@font-face {font-family: 'Gotham';src: url('https://www.123compare.me/v2/fonts/Gotham-Bold.otf')  format('opentype');font-weight: bold;font-style: normal;}" +
            "@font-face{font-family: 'Gotham';src: url('https://www.123compare.me/v2/fonts/Gotham-Medium.otf')  format('opentype');font-weight: normal;font-style: normal;}" +
            "@font-face{font-family: 'Gotham';src: url('https://www.123compare.me/v2/fonts/Gotham-Light.otf')  format('opentype');font-weight: 100;font-style: normal;}" +
            "#widget{position: fixed;top: 100px;right: 0px;width: 210px;display: none;box-sizing: border-box;cursor: pointer;z-index: 9999;font-family: 'Gotham', Helvetica, Arial, sans-serif;}" +
            "#widget_top_top{line-height: 15px;padding-top: 15px; padding-bottom: 15px; background-color: #7C7766; text-align: center; color: #FFFFFF; border-bottom: 1px solid #A5A186;font-weight: bold;font-size: 11px;}" +
            "#widget_top{padding-top: 5px;padding-bottom: 10px;background-color: #7C7766;color: #FFFFFF;text-align: center;}" +
            "#widget_top_top_text{font-size: 12px;margin-bottom: 5px; font-weight: 100;}" +
            ".alta-flecha{background-image: url('data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAWCAYAAADXYyzPAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyNpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDE0IDc5LjE1MTQ4MSwgMjAxMy8wMy8xMy0xMjowOToxNSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIChNYWNpbnRvc2gpIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjUwRDA3RDg3NzE4ODExRTU5MERCRDZDNjRGNDJGMDU5IiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOjUwRDA3RDg4NzE4ODExRTU5MERCRDZDNjRGNDJGMDU5Ij4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6NTBEMDdEODU3MTg4MTFFNTkwREJENkM2NEY0MkYwNTkiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6NTBEMDdEODY3MTg4MTFFNTkwREJENkM2NEY0MkYwNTkiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz6bjZ3wAAABaElEQVR42mL8//8/w0AAJoYBAsgWhwDxFyC+C8TqVLRDA4hvQ80OgYuCghqKH/9HgJtALIgkRy4GmXEbydynMDlkRb//o4JdQMxMgaUgvXvQzPyNzeIN/zHBBAosnozFvA3YLJYA4kdYFKeQYWkaFnMeArE4NotB2BCIv6Fp+AXEdiRYag/Vgwy+ArEBsjpsGkOxuPYVECsQYSlIzRss+oPR1eIyoAGL5otAzIPHUl4gvoxFXx029bgMYQTiNTgSByMW9UxAvBGL+pU41DPgCzYuID6PxbBWLGrbsag7BzWDgVSLQVgOiF9iMTQSSU0UFvkXQCyLz2xiUqkVEP9EMxiU8k2A2AyIv6PJgdRaEDKX2CySgMVXoOLvGRbxWGLMJKVQ6PlPGHQRax6pZe82PJZuJaVsJ7Uo5Afi61gsvQbEfKSYRU7hrwrE75AsfQvEyqSaQ27NAyqPX0OLUntyzGAccW0ugAADABmgl1ZVu0KpAAAAAElFTkSuQmCC');}" +
            ".flecha{width: 15px; height: 11px; margin: auto; margin-top: 5px; background-size: 100% 100%;}" +
            "#widget_top_bottom_left{width: 15%; height: 20px; float:left;}" +
            "#widget_top_bottom_price{width: 70%; line-height: 20px; float:left;}" +
            "#widget_top_bottom_right{width: 15%; height: 20px; float:left;}" +
            "#widget_top_bottom_price #price{font-size: 22px;font-weight: bold;}" +
            "#widget_middle{margin-top: 0px;background-color: #FFFFFF;}" +
            "#element{padding: 10px; padding-left: 0px; padding-right: 0px; border-bottom: 1px solid #EBEAEC;}" +
            "#element:last-child{border-bottom: 0px}" +
            "#element_left{width: 50%; float: left; text-align: right;}" +
            "#element #element_left img{width: auto;height: 20px;}" +
            "#element_right{width: 50%; float: right; text-align: left;}" +
            "#element #element_right .priceWidgetElement {margin-left: 10px;line-height: 20px;color: #4A4B4C;font-size: 17px;}" +
            "#element #element_right .priceWidgetElement .priceWidgeElementCurrency{font-size: 11px;}" +
            "#widget_copyright{font-size: 10px; text-align:center;font-weight:bold; width: 100%; margin-top: 5px;}" +
            "#widget_copyright a{color: #FFFFFF;}" +
            "#widget_copyright span{color: #7C7766;}" +
            "@media (min-width: 1024px) and (max-width: 1280px){" +
            "#widget{width: 160px;}"+
            "#widget_top_top{padding-top: 10px;padding-bottom: 10px;}" +
            "#widget_top_top_text{font-size: 12px;margin-bottom: 0px; font-weight: 100;}" +
            "#widget_top_bottom_price #price{font-size: 17px;}" +
            "#element_left{width: 100%; float: none; text-align: center;}" +
            "#element_right{width: 100%; float: none; text-align: center;}" +
            "}");
        hotelswidget.setAnalytics();
    }

    this.setHtml = function(price,currency) {
        $('body').append(
            "<div id='widget'>" +
                "<div id='widget_top_top'>" +
                "</div>" +
                "<div id='widget_top'>" +
                    "<div id='widget_top_top_text'></div>" +
                    "<div id='widget_top_bottom_left'>" +
                        "<div class='flecha alta-flecha'></div>" +
                    "</div>" +
                    "<div id='widget_top_bottom_price'>" +
                        "<span id='price'>850.00</span>&nbsp;&nbsp;<span id='currency'>SEK</span>" +
                    "</div>" +
                    "<div id='widget_top_bottom_right'>" +
                        "<div class='flecha alta-flecha'></div>" +
                    "</div>" +
                    "<div style='clear:both;'></div>" +
                "</div>" +
                "<div id='widget_middle'>" +
                "</div>" +
                "<div id='widget_copyright' style='padding-top:2px;'>" +
                    "<a href='http://www.123compare.me' style='text-decoration: none;'>" +
                        "Powered by&nbsp;<span>123compare.me</span>" +
                    "</a>" +
                "</div>" +
            "</div>");
        if($('html').attr('lang') === 'en'){
            $('#widget_top_bottom_price').find('#price').html((price.toFixed(2)).replace('.',','));
        } else{
            $('#widget_top_bottom_price').find('#price').html((price.toFixed(2)).replace('.',','));
        }
        $('#widget_top_bottom_price').find('#currency').html(currency);
        hotelswidget.setJavaScript();
    }

    this.setHtmlMobile = function(price,currency, time) {
    }

    this.setAnalytics = function(){
        $('body').append("<script type='text/javascript'>" +
            "var _paq = _paq || [];" +
            "_paq.push(['trackPageView']);" +
            "_paq.push(['enableLinkTracking']);" +
            "(function() {var u='//www.123compare.me/piwik/';_paq.push(['setTrackerUrl', u+'piwik.php']);_paq.push(['setSiteId', 3]);var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];g.type='text/javascript'; g.async=true; g.defer=true; g.src=u+'piwik.js'; s.parentNode.insertBefore(g,s);" +
            "})();" +
            "</script>" +
            "<noscript><p><img src='//www.123compare.me/piwik/piwik.php?idsite=3' style='border:0;' alt='' /></p></noscript>");
    }

    this.findPrice = function(){
        var cheap_price = '';
        $('.contentRooms > table').each(function(){
            $.td = $(this).find('td').first().next();
            $.div = $.td.find('div').first().next();
            $.div.find('.rowRoomPrice').each(function(){
               $(this).find('.box2 > .rowPriceRoom').each(function(){
                   var price = parseFloat($(this).find('.subBox2').html().replace(',','.'));
                   if ((cheap_price === '') || (price < cheap_price)){
                       cheap_price = price;
                   }
               });
            });
        });
        return cheap_price;
    }

})(window, document, $);