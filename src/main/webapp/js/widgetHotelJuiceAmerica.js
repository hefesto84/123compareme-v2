var hotelswidget = new (function(window, document, $){

    var domain = 'https://www.123compare.me/v2'
    var data = '';
    this.init = function (showWidget) {
        if(showWidget === false){
            hotelswidget.setAnalytics();
        } else {
        	setTimeout(function(){
        		var conversion = 1;
                var hotel = 'Hotel America Barcelona';
                var rooms = 1;
                var guests = $('a.addroomaction').first().attr('data-adults');
                var user = 3;
                var start = $('ul.dates li:eq(0)').find('.date-value').html().trim();
                var stop = $('ul.dates li:eq(1)').find('.date-value').html().trim();
                var price = hotelswidget.findPrice();
                var currency = 'EUR';
                var lang = $('html').attr('lang');
                var device = 'isDesktop';

                var url_post = domain + '/api/prices?currency='+ currency + '&base=' + price + '&code=' + user + '&hotel=' + encodeURI(hotel) + '&rooms=' + rooms + '&guests=' + guests + '&fin=' + start + '&fout=' + stop + '&lang=' + lang;

                console.log(url_post);

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
        	},500);
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
        var locale_ca = '{"widget123_top_top":"Estalvia reservant directament a la web oficial","widget123_top_text":"El nostre preu"}';
        var locale_de = '{"widget123_top_top":"Direkt buchen auf der offiziellen Webseite und sparen","widget123_top_text":"Unser preis"}';
        var locale_es = '{"widget123_top_top":"Ahorra reservando directamente en la web oficial","widget123_top_text":"Nuestro precio"}';
        var locale_en = '{"widget123_top_top":"Save by booking direct on the official website","widget123_top_text":"Our price"}';
        var locale_fr = '{"widget123_top_top":"Économisez en réservant directement sur le site officiel","widget123_top_text":"Notre prix"}';
        var locale_it = '{"widget123_top_top":"Risparmi prenotando direttamente sul sito ufficiale","widget123_top_text":"Il nostro prezzo"}';
        var locale_ru = '{"widget123_top_top":"Сэкономь бронируя на официальном сайте","widget123_top_text":"наша цена"}';

        // Array de traducciones
        var translations = {
            da:locale_ca,
            de:locale_de,
            es:locale_es,
            en:locale_en,
            fr:locale_fr,
            it:locale_it,
            ru:locale_ru
        };

        // Array con las traducciones por idioma
        var t = JSON.parse(unescape(encodeURIComponent(translations[lang])));

        jQuery('#widget123_top_top').html(t["widget123_top_top"]);
        jQuery('#widget123_top_top_text').html(t["widget123_top_text"]);
    }

    this.setWidget = function(datos,price,conversion, currency) {
        data = datos;
        if (data.datos.length == 0){
            _paq.push(['trackEvent', 'Widget', 'No results', 'Widget not showed (No data available)']);
            return 0;
        }
        var content_middle = document.getElementById("widget123_middle");
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
            _paq.push(['trackEvent', 'Widget', 'No results', 'No results shown']);
        } else {
            _paq.push(['trackEvent', 'Widget', 'Results', 'Shown '+count+' results']);
            $('#widget123').show();
        }
    }

    this.setWidgetMobile = function(datos,price,conversion, currency){

    }

    this.setJavaScript = function(){
        var top_widget = jQuery('.bgsteps').offset().top;

        $('#widget123').css({
            'top' : top_widget
        });


        var screen_width = $(window).width();
        var screen_height = $(window).height();

        var popup_width = $('#widget123_popup_content').width();
        var popup_height = $('#widget123_popup_content').height();
        $('#widget123_popup_content').css({
            'left' : ((screen_width - popup_width)/2)
        });

        $('#widget123_top').click(function(){
            if ($('#widget123_middle').is(':hidden')){
                $('#widget123_middle').slideDown(1000);
            } else {
                $('#widget123_middle').slideUp(1000);
            }
        });

        _paq.push(['trackEvent', 'Widget', 'Show', 'Widget correctly showed']);
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
            "#widget123{position: fixed;top: 100px;right: 0px;width: 210px;display: none;box-sizing: border-box;cursor: pointer;z-index: 9999}" +
            "#widget123_top_top{line-height: 15px;padding-top: 15px; padding-bottom: 15px;padding-left: 5px;padding-right: 5px; background-color: #DB291D; text-align: center; color: #FFFFFF; border-bottom: 1px solid #A5A186;font-weight: bold;font-size: 11px;}" +
            "#widget123_top{padding-top: 5px;padding-bottom: 10px;background-color:  #DB291D;color: #FFFFFF;text-align: center;}" +
            "#widget123_top_top_text{font-size: 13px;margin-bottom: 5px; font-weight: 100;}" +
            ".alta-flecha{background-image: url('data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAWCAYAAADXYyzPAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyNpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDE0IDc5LjE1MTQ4MSwgMjAxMy8wMy8xMy0xMjowOToxNSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIChNYWNpbnRvc2gpIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjUwRDA3RDg3NzE4ODExRTU5MERCRDZDNjRGNDJGMDU5IiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOjUwRDA3RDg4NzE4ODExRTU5MERCRDZDNjRGNDJGMDU5Ij4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6NTBEMDdEODU3MTg4MTFFNTkwREJENkM2NEY0MkYwNTkiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6NTBEMDdEODY3MTg4MTFFNTkwREJENkM2NEY0MkYwNTkiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz6bjZ3wAAABaElEQVR42mL8//8/w0AAJoYBAsgWhwDxFyC+C8TqVLRDA4hvQ80OgYuCghqKH/9HgJtALIgkRy4GmXEbydynMDlkRb//o4JdQMxMgaUgvXvQzPyNzeIN/zHBBAosnozFvA3YLJYA4kdYFKeQYWkaFnMeArE4NotB2BCIv6Fp+AXEdiRYag/Vgwy+ArEBsjpsGkOxuPYVECsQYSlIzRss+oPR1eIyoAGL5otAzIPHUl4gvoxFXx029bgMYQTiNTgSByMW9UxAvBGL+pU41DPgCzYuID6PxbBWLGrbsag7BzWDgVSLQVgOiF9iMTQSSU0UFvkXQCyLz2xiUqkVEP9EMxiU8k2A2AyIv6PJgdRaEDKX2CySgMVXoOLvGRbxWGLMJKVQ6PlPGHQRax6pZe82PJZuJaVsJ7Uo5Afi61gsvQbEfKSYRU7hrwrE75AsfQvEyqSaQ27NAyqPX0OLUntyzGAccW0ugAADABmgl1ZVu0KpAAAAAElFTkSuQmCC');}" +
            ".flecha{width: 15px; height: 11px; margin: auto; margin-top: 5px; background-size: 100% 100%;}" +
            "#widget123_top_bottom_left{width: 15%; height: 20px; float:left;}" +
            "#widget123_top_bottom_price{width: 70%; line-height: 20px; float:left;}" +
            "#widget123_top_bottom_right{width: 15%; height: 20px; float:left;}" +
            "#widget123_top_bottom_price #price{font-size: 22px;font-weight: bold;}" +
            "#widget123_middle{margin-top: 0px;background-color: #FFFFFF;}" +
            "#element{padding: 10px; padding-left: 0px; padding-right: 0px; border-bottom: 1px solid #EBEAEC;}" +
            "#element:last-child{border-bottom: 0px}" +
            "#element_left{width: 50%; float: left; text-align: right;}" +
            "#element #element_left img{width: auto;height: 20px;}" +
            "#element_right{width: 50%; float: right; text-align: left;}" +
            "#element #element_right .priceWidgetElement {margin-left: 10px;line-height: 20px;color: #4A4B4C;font-size: 17px;}" +
            "#element #element_right .priceWidgetElement .priceWidgeElementCurrency{font-size: 11px;}" +
            "#widget123_copyright{font-size: 10px; text-align:center;font-weight:bold; width: 100%;}" +
            "#widget123_copyright a{color: #FFFFFF;}" +
            "#widget123_copyright span{color: #DB291D;}" +
            "@media (min-width: 0px) and (max-width: 1400px){" +
            "#widget123{width: 160px;}"+
            "#widget123_top_top{padding-top: 10px;padding-bottom: 10px;}" +
            "#widget123_top_top_text{margin-bottom: 0px;}" +
            "#widget123_top_bottom_price #price{font-size: 17px;}" +
            "#element{padding: 5px;}" +
            "#element_left{width: 100%; float: none; text-align: center;}" +
            "#element_right{width: 100%; float: none; text-align: center;}" +
            "}");
        hotelswidget.setAnalytics();
    }

    this.setHtml = function(price,currency) {
        $('body').append(
            "<div id='widget123'>" +
                "<div id='widget123_top_top'>" +
                "</div>" +
                "<div id='widget123_top'>" +
                    "<div id='widget123_top_top_text'></div>" +
                    "<div id='widget123_top_bottom_left'>" +
                        "<div class='flecha alta-flecha'></div>" +
                    "</div>" +
                    "<div id='widget123_top_bottom_price'>" +
                        "<span id='price'>850.00</span>&nbsp;&nbsp;<span id='currency'>SEK</span>" +
                    "</div>" +
                    "<div id='widget123_top_bottom_right'>" +
                        "<div class='flecha alta-flecha'></div>" +
                    "</div>" +
                    "<div style='clear:both;'></div>" +
                "</div>" +
                "<div id='widget123_middle'>" +
                "</div>" +
                "<div id='widget123_copyright' style='padding-top:2px;'>" +
                    "<a href='http://www.123compare.me' style='text-decoration: none;'>" +
                        "Powered by&nbsp;<span>123compare.me</span>" +
                    "</a>" +
                "</div>" +
            "</div>");
        /*if($('html').attr('lang') === 'en'){
            $('#widget123_top_bottom_price').find('#price').html((price.toFixed(2)).replace('.',','));
        } else{
            $('#widget123_top_bottom_price').find('#price').html((price.toFixed(2)).replace('.',','));
        }*/
        $('#widget123_top_bottom_price').find('#price').html(price);
        $('#widget123_top_bottom_price').find('#currency').html(currency);
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
        $('table.detailroom').each(function(){
            var price = parseFloat($(this).find('span.roomlist-price').html().substring(0,$(this).find('span.roomlist-price').html().indexOf('<')).replace('.','').replace(',','.'));
            if ((cheap_price === '') || (price < cheap_price)){
                cheap_price = price;
            }
        });
        return cheap_price;         
    }

})(window, document, $);