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
            var price = hotelswidget.findPrice();
            var currency = '£';
            var lang = 'en';
            var device = 'isDesktop';

            var url_post = domain + '/api/prices?base=' + price + '&hotel=' + encodeURI(hotel) + '&rooms=' + rooms + '&guests=' + guests + '&fin=' + start + '&fout=' + stop + '&lang=' + lang;

            console.log(url_post);


            if ((price !== 'undefined') && (price !== '') && (price !== 'NaN')){
                price = parseFloat(price);

                hotelswidget.setCSS();
                if(device === 'isDesktop'){
                    hotelswidget.setHtml(price,currency);
                } else {
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

    this.setJavaScriptMobile = function(time){
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
                        "<span id='currency'>SEK</span><span id='price'>850.00</span>" +
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

        $('#widget123_top_bottom_price').find('#price').html((Math.round(price)));
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

})(window, document, $);