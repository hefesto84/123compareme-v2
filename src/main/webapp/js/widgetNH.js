/**
 * Created by ubiqua on 27/5/15.
 */


var hotelswidget = new (function(window, document, $){
	var debug = true;
    var domain = 'https://www.123compare.me/v2'
    var data = '';
    if(debug){
	    var utag_data = {};
	    utag_data.visitor_language = 'es';
    }
    this.init = function (showWidget) {
        if(showWidget === false){
            hotelswidget.setAnalytics();
        } else {
            var conversion = 1;
            var user = 'UA-41604199-10';
            var hotel = "Park Inn Danube";//this.hotelName(jQuery('.innername').find("a").text());
            var rooms = 1;//jQuery('[name="rateSearchForm.numberRooms"]').val();
            var guests = 2;//jQuery('[name="occupancyForm[0].numberAdults"]').val();
            var start = "10/01/2016";//this.dateConverse(jQuery('[name="rateSearchForm.checkinDate"]').val());
            var stop = "14/01/2016";//this.dateConverse(jQuery('[name="rateSearchForm.checkoutDate"]').val());
            var price = $('.activa').find('.price').html().substring(0,$('.activa').find('.price').html().indexOf(' <'));
            var currency = $('.currency_reservas').html();
            var lang = 'fr';//utag_data.visitor_language;
            var device = 'isDesktop';

            var url_post = domain + '/api/prices?code=' + user + '&hotel=' + encodeURI(hotel) + '&rooms=' + rooms + '&guests=' + guests + '&fin=' + start + '&fout=' + stop + '&lang=' + lang;

            console.log(url_post);
            if ((price !== 'undefined') && (price !== '') && (price !== 'NaN')){
                price = parseFloat(price);

                if (currency != 'EUR'){
                    conversion = hotelswidget.getCurrencyConversion(currency);
                }

                hotelswidget.setCSS();
                hotelswidget.setHtml(price,currency);
                hotelswidget.setTranslate(lang);
                setTimeout(function() {
                    hotelswidget.getDatos(url_post,device,price,conversion,currency,lang);
                },1000) ;
            }
        }
    }

    this.getCurrencyConversion = function (currency){
        var conversion;
        $.ajax({
            type: "GET",
            url: domain + "/currency.php",
            success: function (datos) {
                var datos = JSON.parse(datos);
                conversion = datos[currency];
            },
            async: false
        });
        return conversion;
    }

    this.getDatos = function(url_post,device,price,conversion,currency,lang){
        var datos;
        $.ajax({
            type: "GET",
            url: url_post,
            success: function (respuesta) {
            	console.log(respuesta);
            	datos = respuesta;
                //datos = JSON.parse(respuesta);
                if (device == 'isMobile'){
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
        var locale_da = '{"widget_top_text":"Best Price Guaranteed","widget_middle_text":"Do you want to compare prices?","widget_middle_button":"Check it here","widget_popup_content_top_text":"Best Price Guaranteed","widget_popup_content_parkinn_text":"Our lowest rate:","widget_popup_content_parkinn_left_text_t1":"Free Internet","widget_popup_content_parkinn_left_text_t2":"Earn Club Carlson Points","widget_popup_content_parkinn_text_bottom":"In other sites:","widget_popup_content_parkinn_right_bottom_t1":"No extra fees.","widget_popup_content_parkinn_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"No rooms found for these dates in other sites"}';
        var locale_de = '{"widget_top_text":"Best Price Guaranteed","widget_middle_text":"Do you want to compare prices?","widget_middle_button":"Check it here","widget_popup_content_top_text":"Best Price Guaranteed","widget_popup_content_parkinn_text":"Our lowest rate:","widget_popup_content_parkinn_left_text_t1":"Free Internet","widget_popup_content_parkinn_left_text_t2":"Earn Club Carlson Points","widget_popup_content_parkinn_text_bottom":"In other sites:","widget_popup_content_parkinn_right_bottom_t1":"No extra fees.","widget_popup_content_parkinn_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"No rooms found for these dates in other sites"}';
        var locale_es = '{"widget_top_text":"Best Price Guaranteed","widget_middle_text":"Do you want to compare prices?","widget_middle_button":"Check it here","widget_popup_content_top_text":"Best Price Guaranteed","widget_popup_content_parkinn_text":"Our lowest rate:","widget_popup_content_parkinn_left_text_t1":"Free Internet","widget_popup_content_parkinn_left_text_t2":"Earn Club Carlson Points","widget_popup_content_parkinn_text_bottom":"In other sites:","widget_popup_content_parkinn_right_bottom_t1":"No extra fees.","widget_popup_content_parkinn_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"No rooms found for these dates in other sites"}';
        var locale_en = '{"widget_top_text":"BEST RATE GUARANTEED","widget_middle_text":"Do you want to compare prices?","widget_middle_button":"Check it here","widget_popup_content_top_text":"BEST RATE GUARANTEED","widget_popup_content_parkinn_text":"Our lowest rate:","widget_popup_content_parkinn_left_text_t1":"Free Internet","widget_popup_content_parkinn_left_text_t2":"Earn Club Carlson Points","widget_popup_content_parkinn_text_bottom":"In other sites:","widget_popup_content_parkinn_right_bottom_t1":"No extra fees.","widget_popup_content_parkinn_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"No rooms found for these dates in other sites"}';
        var locale_en_UK = '{"widget_top_text":"Best Price Guaranteed","widget_middle_text":"Do you want to compare prices?","widget_middle_button":"Check it here","widget_popup_content_top_text":"Best Price Guaranteed","widget_popup_content_parkinn_text":"Our lowest rate:","widget_popup_content_parkinn_left_text_t1":"Free Internet","widget_popup_content_parkinn_left_text_t2":"Earn Club Carlson Points","widget_popup_content_parkinn_text_bottom":"In other sites:","widget_popup_content_parkinn_right_bottom_t1":"No extra fees.","widget_popup_content_parkinn_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"No rooms found for these dates in other sites"}';
        var locale_fr = '{"widget_top_text":"GARANTIE DU MEILLEUR PRIX","widget_middle_text":"Voulez-vous comparer les prix?","widget_middle_button":"V&#233;rifiez ici","widget_popup_content_top_text":"GARANTIE DU MEILLEUR PRIX","widget_popup_content_parkinn_text":"Notre plus bas tarif:","widget_popup_content_parkinn_left_text_t1":"Internet gratuit","widget_popup_content_parkinn_left_text_t2":"Gagnez points Club Carlson","widget_popup_content_parkinn_text_bottom":"En d&#039;autres sites:","widget_popup_content_parkinn_right_bottom_t1":"Pas de frais suppl&#233;mentaires.","widget_popup_content_parkinn_right_bottom_t2":"Pas de frais cach&#233;s.", "widget_popup_content_bottom_button":"R&#233;server maintenant", "widget_popup_loading_text":"Regarder les résultats", "no_otas":"No rooms found for these dates in other sites"}';
        var locale_it = '{"widget_top_text":"Best Price Guaranteed","widget_middle_text":"Do you want to compare prices?","widget_middle_button":"Check it here","widget_popup_content_top_text":"Best Price Guaranteed","widget_popup_content_parkinn_text":"Our lowest rate:","widget_popup_content_parkinn_left_text_t1":"Free Internet","widget_popup_content_parkinn_left_text_t2":"Earn Club Carlson Points","widget_popup_content_parkinn_text_bottom":"In other sites:","widget_popup_content_parkinn_right_bottom_t1":"No extra fees.","widget_popup_content_parkinn_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"No rooms found for these dates in other sites"}';
        var locale_nl = '{"widget_top_text":"Best Price Guaranteed","widget_middle_text":"Do you want to compare prices?","widget_middle_button":"Check it here","widget_popup_content_top_text":"Best Price Guaranteed","widget_popup_content_parkinn_text":"Our lowest rate:","widget_popup_content_parkinn_left_text_t1":"Free Internet","widget_popup_content_parkinn_left_text_t2":"Earn Club Carlson Points","widget_popup_content_parkinn_text_bottom":"In other sites:","widget_popup_content_parkinn_right_bottom_t1":"No extra fees.","widget_popup_content_parkinn_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"No rooms found for these dates in other sites"}';
        var locale_no = '{"widget_top_text":"Best Price Guaranteed","widget_middle_text":"Do you want to compare prices?","widget_middle_button":"Check it here","widget_popup_content_top_text":"Best Price Guaranteed","widget_popup_content_parkinn_text":"Our lowest rate:","widget_popup_content_parkinn_left_text_t1":"Free Internet","widget_popup_content_parkinn_left_text_t2":"Earn Club Carlson Points","widget_popup_content_parkinn_text_bottom":"In other sites:","widget_popup_content_parkinn_right_bottom_t1":"No extra fees.","widget_popup_content_parkinn_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"No rooms found for these dates in other sites"}';
        var locale_pt_BR = '{"widget_top_text":"Best Price Guaranteed","widget_middle_text":"Do you want to compare prices?","widget_middle_button":"Check it here","widget_popup_content_top_text":"Best Price Guaranteed","widget_popup_content_parkinn_text":"Our lowest rate:","widget_popup_content_parkinn_left_text_t1":"Free Internet","widget_popup_content_parkinn_left_text_t2":"Earn Club Carlson Points","widget_popup_content_parkinn_text_bottom":"In other sites:","widget_popup_content_parkinn_right_bottom_t1":"No extra fees.","widget_popup_content_parkinn_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"No rooms found for these dates in other sites"}';
        var locale_ru = '{"widget_top_text":"Best Price Guaranteed","widget_middle_text":"Do you want to compare prices?","widget_middle_button":"Check it here","widget_popup_content_top_text":"Best Price Guaranteed","widget_popup_content_parkinn_text":"Our lowest rate:","widget_popup_content_parkinn_left_text_t1":"Free Internet","widget_popup_content_parkinn_left_text_t2":"Earn Club Carlson Points","widget_popup_content_parkinn_text_bottom":"In other sites:","widget_popup_content_parkinn_right_bottom_t1":"No extra fees.","widget_popup_content_parkinn_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"No rooms found for these dates in other sites"}';
        var locale_sv = '{"widget_top_text":"Best Price Guaranteed","widget_middle_text":"Do you want to compare prices?","widget_middle_button":"Check it here","widget_popup_content_top_text":"Best Price Guaranteed","widget_popup_content_parkinn_text":"Our lowest rate:","widget_popup_content_parkinn_left_text_t1":"Free Internet","widget_popup_content_parkinn_left_text_t2":"Earn Club Carlson Points","widget_popup_content_parkinn_text_bottom":"In other sites:","widget_popup_content_parkinn_right_bottom_t1":"No extra fees.","widget_popup_content_parkinn_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"No rooms found for these dates in other sites"}';
        var locale_cn = '{"widget_top_text":"Best Price Guaranteed","widget_middle_text":"Do you want to compare prices?","widget_middle_button":"Check it here","widget_popup_content_top_text":"Best Price Guaranteed","widget_popup_content_parkinn_text":"Our lowest rate:","widget_popup_content_parkinn_left_text_t1":"Free Internet","widget_popup_content_parkinn_left_text_t2":"Earn Club Carlson Points","widget_popup_content_parkinn_text_bottom":"In other sites:","widget_popup_content_parkinn_right_bottom_t1":"No extra fees.","widget_popup_content_parkinn_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"No rooms found for these dates in other sites"}';

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
        var t = JSON.parse(unescape(encodeURIComponent(translations[utag_data.visitor_language])));

        $('#widget_top_text').html(t["widget_top_text"]);
        $('#widget_middle_text').html(t["widget_middle_text"]);
        $('#widget_middle_button').html(t["widget_middle_button"]);
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
        var href = hotelswidget.setUrlHref();
        $('.widget_content_loading').hide();
        $('#widget_popup_loading_text').hide();
        if(debug){
        	console.log(datos);
        }
        data = datos;
        if (data.datos.length == 0){
            _paq.push(['trackEvent', 'Widget', 'No results', 'No se ha mostrado el widget por que no hay datos']);
            return 0;
        }var content_middle = document.getElementById("widget_popup_content_middle");
        var count = 0;
        for (var i = 0; i < data.datos.length; i++) {

            var precio_convertido = (parseFloat(data.datos[i].price) * conversion);

                if (/*(data.datos[i].site == 'Booking.com' || data.datos[i].site == 'Expedia' || data.datos[i].site == 'Hotels.com') && (data.datos[i].site != 'Park Inn') && */ (price < precio_convertido || price == precio_convertido) &&  (count < 5) && (data.datos[i].site != 'Park Inn')) {
                    count = count + 1;
                    //if (data.datos[i].site != 'Park Inn'){
                    var element = document.createElement("div");
                    element.setAttribute('id', 'element');

                    var image = document.createElement('img');
                    //image.setAttribute('src', domain + '/images/pages/' + hotelswidget.convertToSlug(data.datos[i].site) + '.png');
                    image.setAttribute('src', domain + '/img/' + data.datos[i].site);
                    element.appendChild(image);

                    var span = document.createElement('span');
                    span.setAttribute('class', 'priceWidgetElement');
                    span.innerHTML = precio_convertido.toFixed(2) + ' ' + currency;
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
        $('#boton_reservar_widget').attr('href', href);

    }

    this.setWidgetMobile = function(datos,price,conversion, currency){
        $('#widget').hide();
        $('body').append("<div id='widget_mobile'><div id='widget_mobile_top'><div id='widget_mobile_top_left'><span id='widget_mobile_currency'>EUR</span><span id='widget_mobile_price'>1714,59</span></div><div id='widget_mobile_top_middle'><div id='widget_mobile_top_middle_top'>OUR PRICE</div><div id='widget_mobile_top_middle_bottom'>Single guest room</div></div><div id='widget_mobile_top_right'>X</div></div><div id='widget_mobile_bottom'><div id='widget_mobile_bottom_button'>COMPARE PRICE</div></div></div><div id='widget_mobile_content'><div id='widget_mobile_content_top'><div id='widget_mobile_content_top_close'>X</div><div id='widget_mobile_content_top_text'><div id='widget_mobile_content_top_text_left'><span id='widget_mobile_content_currency'>EUR</span><span id='widget_mobile_content_price'>1714,59</span></div><div id='widget_mobile_content_top_text_right'><div id='widget_mobile_content_top_text_right_top'>OUR PRICE</div><div id='widget_mobile_content_top_text_right_bottom'>Single guest room</div></div></div></div><div id='widget_mobile_content_bottom'></div></div>");

        var precio = price;
        var widget_content = document.getElementById("widget_mobile_content_bottom");

        for (var i = 0; i < data.datos.length; i++) {
            var precio_convertido = (parseFloat(data.datos[i].price) * conversion);
            var element = document.createElement("div");
            element.setAttribute('id', 'element');

            var image = document.createElement('img');
            image.setAttribute('src', domain + '/img/' + data.datos[i].site);
            element.appendChild(image);

            var spanCurrency = document.createElement('span');
            spanCurrency.setAttribute('class', 'currencyWidgetElementMobile');
            spanCurrency.innerHTML = currency;
            element.appendChild(spanCurrency);

            var spanPrice = document.createElement('span');
            spanPrice.setAttribute('class', 'priceWidgetElementMobile');
            spanPrice.innerHTML = precio_convertido.toFixed(2);
            element.appendChild(spanPrice);

            var clear = document.createElement('div');
            clear.setAttribute('style', 'clear:both;');
            element.appendChild(clear);

            var explicacion = document.getElementById('explicacion');
            widget_content.insertBefore(element, explicacion);
        }

        if (!($("#element").length > 0)) {
            var mensaje = document.createElement('div');
            var explicacion = document.getElementById('explicacion');
            mensaje.setAttribute('class', 'mensaje_no_precios');
            mensaje.innerHTML = "No hemos podido realizar la búsqueda en este momento";
            widget_content.insertBefore(mensaje, explicacion);
        }

        $('#widget_mobile_currency').html(currency);
        $('#widget_mobile_content_currency').html(currency);
        $('#widget_mobile_price').html('<span>'+parseFloat(price).toFixed(2).toString()+'</span>');
        $('#widget_mobile_content_price').html(parseFloat(price.toFixed(2)));
        hotelswidget.setJavaScriptMobile();

    }

    this.setJavaScript = function(){
        var top_widget = 10;//jQuery('.panelHeaderWrapper').offset().top;
        var left_widget =10; //jQuery('#searchPanel').offset().left + jQuery('#searchPanel').width() + 2;
        $('#widget').css({
           'top' : top_widget,
            'left' : left_widget
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
            _paq.push(['trackEvent', 'Widget', 'Click', 'Widget clicado']);
        });
        $('#widget_popup_content_top_close').click(function(){
            $('#widget_popup').hide();
        });

        _paq.push(['trackEvent', 'Widget', 'Show', 'Widget mostrado correctamente']);
    }

    this.setJavaScriptMobile = function(){
        var top;
        var height;
        var left;

        var ancho_screen = $(window).width();
        var alto_screen = $(window).height();
        var ancho_widget_mobile = $('#widget_mobile').width();

        var alto_widget_mobile_content = $('#widget_mobile_content').height();

        $('#widget_mobile_content').css('display','table');
        var alto_widget_mobile_content_top = $('#widget_mobile_content_top').height();
        var ancho_widget_mobile_content = $('#widget_mobile_content').width();
        $('#widget_mobile_content').css('display', 'none');

        if (alto_widget_mobile_content > (alto_screen - 100 - alto_widget_mobile_content_top)){
            top = 50;
            height = alto_screen - 100 - alto_widget_mobile_content_top;
        } else {
            top = (alto_screen - alto_widget_mobile_content) / 2;
            height = alto_widget_mobile_content;
        }

        left = (ancho_screen - ancho_widget_mobile_content) / 2;

        $('#widget_mobile').css({
            'left': ((ancho_screen - ancho_widget_mobile)/2)
        });

        $('#widget_mobile_bottom_button').click(function(){
            $('#widget_mobile').hide();
            $('html, body').css({
                'overflow': 'hidden',
                'height': '100%'
            });
            $(document).on("touchmove", function(evt) { evt.preventDefault() });
            var fons = "<div id='widget_mobile_background'></div></div>";
            $('body').append(fons);
            $('#widget_mobile_content').css({
                'display':'table',
                'position': 'absolute',
                'left': left,
                'top': top
            });
            $('#widget_mobile_content_bottom').css({
                'height': height
            });
        });
    }

    this.convertToSlug = function(text){
        return text
            .toLowerCase()
            .replace(/ /g,'-')
            .replace(/[^\w-]+/g,'')
            ;
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
        converseDate = date.slice(6,10)+'-'+date.slice(0,2)+'-'+date.slice(3,5)
        return converseDate;
    }

    this.priceConverse = function(price){
        if(utag_data.visitor_language == 'en' || utag_data.visitor_language == 'en_UK'){
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

    this.setUrlHref = function(){
        var script = "javascript:selectRate('BTSPD','27848168', false, true);";//jQuery('td.v1').find('a').attr('href');
        var pos_first = script.indexOf('(');
        var pos_last = script.indexOf(')');
        var parameters = script.slice(pos_first+1,pos_last);
        var array = parameters.split(',');
        var href = "javascript:hotelswidget.functionReservar("+array[0]+","+array[1]+","+array[2]+","+array[3]+")";
        return href;
    }

    this.functionReservar = function(pHotelCode, pRateUni, pRedemptionRate, pClearEcertCode){
        _paq.push(['trackEvent', 'Widget', 'Click Book', 'Widget Reservar']);
        if ($('staticPopup'))
        {
            hideStaticDetailDivWithOverlay('staticPopup');
        }
        var lForm = $('hiddenRateReviewForm');
        lForm['rateSelectForm.hotelCode'].value = pHotelCode;
        lForm['rateSelectForm.rateUni'].value = pRateUni;
        $(lForm).prepend('<input type="hidden" name="facilitatorId" value="REZIDORRATETABLE">');
        $(lForm).prepend('<input type="hidden" name="facilitator" value="REZIDORRATETABLE">');
        $(lForm).prepend('<input type="hidden" name="icid" value="co_booking_direction">');


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

    this.setCSS = function(){
        $('body').append("<style>" +
            "#widget{position: fixed;top: 100px;right: 0px;width: 200px;display: table;box-sizing: border-box;cursor: pointer;z-index: 9999;}" +
            "#widget_top{padding: 10px;padding-left: 30px;padding-right: 30px;background-color: #50386E;color: #FFFFFF;text-align: center;font-family: 'Montserrat', Helvetica, Arial, sans-serif;}" +
            "#widget_top_text{font-size: 13px;margin-bottom: 5px;}" +
            "#widget_top_price #price{font-size: 25px;font-weight: bold;}" +
            "#widget_middle{margin-top: 0px;background-color: #FFFFFF;color: #FFFFFF;text-align: center;font-family: 'Montserrat', Helvetica, Arial, sans-serif;padding: 10px;padding-top: 5px;padding-left: 20px;padding-right: 20px;}" +
            "#widget_middle_text{font-weight: bold;color: #50386E !important;}" +
            "#widget_middle_button{width: 70%;margin: auto;padding: 5px;color: #FFFFFF;background-color: #649D34;margin-top: 5px;margin-bottom: 5px;font-weight: bold;}" +
            "#widget_popup_content_parkinn_text_bottom {margin-bottom: 0px;margin-top: 10px;text-align: left;padding-left: 10px;font-weight: bold;}" +
            "#widget_popup{display: none;font-family: 'Montserrat', Helvetica, Arial, sans-serif;}" +
            "#widget_content_background{position: fixed;left: 0px;top: 0px;width: 100%;height: 100%;background: rgba(0,0,0,0.5);z-index: 9999999999;}" +
            "#widget_popup_content{position: absolute;top: 50px;width: 320px;box-sizing: border-box;}" +
            "#widget_popup_content_top{background-color: #50386E !important;color: #FFFFFF;padding: 5px;text-align: center;line-height: 50px;font-size: 18px;}" +
            "#widget_popup_content_top_text{font-weight: bold;padding-left: 15px;}" +
            "#widget_popup_content_top_close{margin-bottom: -22px;float: right;width: 25px;height: 25px;background: url('https://www.123compare.me/v1/images/close.png');background-size: 100% 100%;}" +
            "#widget_popup_content_parkinn{padding: 10px;padding-bottom: 0px;background-color: #FFFFFF;}" +
            "#widget_popup_content_parkinn_text {padding-left: 10px;font-size: 12px;margin-bottom: 10px;text-align: left;font-family: 'Montserrat', Helvetica, Arial, sans-serif;font-weight: bold;}" +
            "#widget_popup_content_middle{padding: 10px;padding-top: 0px;background-color: #FFFFFF;}" +
            ".widget_content_loading{display:block;margin:auto;padding-top:15px;}" +
            "#widget_popup_loading_text{margin-top: 10px;font-family: 'Montserrat', Helvetica, Arial, sans-serif;font-size: 14px;}" +
            "#widget_popup_content_bottom{padding: 10px;background-color: #FFFFFF;}" +
            "#widget_popup_content_bottom_button{width: 40%;text-align:center;color: #FFFFFF;margin: auto;background-color: #609D34;padding: 5px;padding-left: 20px;padding-right: 20px;font-size: 18px;font-weight: bold;margin-top: 10px;font-family: 'Montserrat', Helvetica, Arial, sans-serif;}" +
            "#element{padding: 10px;border-bottom: 1px solid #EBEAEC;}" +
            "#element img{width: auto;float: left;height: 22px;}" +
            "#element .currency{float: left;line-height: 22px;color: #4A4B4C;font-family: 'Montserrat', Helvetica, Arial, sans-serif;}" +
            "#element .text{text-align: center;font-size: 12px;color: #4A4B4C;font-family: 'Montserrat', Helvetica, Arial, sans-serif;}" +
            "#element .priceWidgetElement {float: right;font-family: 'Montserrat', Helvetica, Arial, sans-serif;margin-left: 5px;line-height: 22px;color: #4A4B4C;font-size: 18px;}" +
            ".image_parkinn{width: 100px;height: auto;display: block;margin-bottom: 5px;}" +
            "#widget_popup_content_parkinn_left{padding-left: 10px;width: 40%;box-sizing: border-box;float: left;line-height: 10px;}" +
            "#widget_popup_content_parkinn_left_text {font-size: 10px;font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;}" +
            "#widget_popup_content_parkinn_right{padding-right: 10px;width: 60%;box-sizing: border-box;float: right;text-align: right;font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;color: #FCA339;}" +
            "#widget_popup_content_parkinn_right_price{font-weight: bold;font-size: 25px;}" +
            "#widget_popup_content_parkinn_right_bottom {font-size: 10px;width: 47%;float: right;margin-top: 10px;line-height: 10px;}" +
            "#boton_reservar_widget {text-decoration: none;}" +
            "#no_otas {padding-top: 10px;font-size: 14px;font-weight: bold;width: 80%;margin: auto;}" +
            "@media (min-width: 1024px) and (max-width: 1280px){" +
            "#widget{width: 160px;}#widget_top{padding-left: 10px;padding-right: 10px;}}");
            hotelswidget.setAnalytics();
    }

    this.setHtml = function(price,currency) {
        $('body').append("<div id='widget'><div id='widget_top'><div id='widget_top_text'></div><div id='widget_top_price'><span id='price'>850.00</span>&nbsp;&nbsp;<span id='currency'>SEK</span></div></div><div id='widget_middle'><div id='widget_middle_text'>Do you want to compare prices?</div><div id='widget_middle_button'>Check it here</div></div><div id='widget_copyright' style='padding-top:2px;'><a href='http://www.123compare.me' style='text-decoration: none;'><span style='color:gray;font-size:10px;font-weight:bold;text-align:right;width:auto;position:relative;margin-top:5px;'></span></a></div></div><div id='widget_popup'><div id='widget_content_background'><div id='widget_popup_content'><div id='widget_popup_content_top'><span id='widget_popup_content_top_text'>Best Price Guaranteed</span><div id='widget_popup_content_top_close'></div><div style='clear:both;'></div></div><div id='widget_popup_content_parkinn'><div id='widget_popup_content_parkinn_text'>Our lowest rate:</div><div id='widget_popup_content_parkinn_left' style='width:45%;'><img class='image_parkinn' src='https://www.123compare.me/v1/images/pages/parkinn.png' /><span id='widget_popup_content_parkinn_left_text' class='t1' style='display:block;text-align:left;'>Free Internet</span><span id='widget_popup_content_parkinn_left_text' class='t2' style='display: block;text-align: left; font-size: xx-small;'>Earn Club Carlson Points&reg;</span></div><div id='widget_popup_content_parkinn_right' style='width:50%;'><span id='widget_popup_content_parkinn_right_price'>1,095.00</span>&nbsp;<span id='widget_popup_content_parkinn_right_currency' style='font-size:25px;'>SEK</span><div id='widget_popup_content_parkinn_right_bottom' style='width:100%;'><span class='widget_popup_content_parkinn_right_bottom_t1' style='display:block;'>No extra fees.</span><span class='widget_popup_content_parkinn_right_bottom_t2'>No hidden charges.</span></div><div style='clear:both;'></div></div><div style='clear:both;'></div><div id='widget_popup_content_parkinn_text_bottom'>In other sites:</div></div><div id='widget_popup_content_middle'><img src='https://www.123compare.me/v1/images/loading.GIF' class='widget_content_loading'/><div id='widget_popup_loading_text'></div></div><div id='widget_popup_content_bottom'><a href='' id='boton_reservar_widget'><div id='widget_popup_content_bottom_button'>Book Now</div></a></div><div id='widget_copyright' style='padding-top:5px;'> <a href='http://www.123compare.me' style='text-decoration: none; float:right;'><span style='color:black;font-size:10px;text-align:right;width:auto;position:relative;letter-spacing:1px;'>Powered by 123Compare.me&#169;</span></a></div></div></div></div>");
        if(utag_data.visitor_language == 'en' || utag_data.visitor_language == 'en_UK'){
            $('#widget_top_price').find('#price').html((price.toFixed(2)).replace(',','.'));
            $('#widget_popup_content_parkinn_right_price').html((price.toFixed(2)).replace(',','.'));
        } else{
            $('#widget_top_price').find('#price').html((price.toFixed(2)).replace('.',','));
            $('#widget_popup_content_parkinn_right_price').html((price.toFixed(2)).replace('.',','));
        }
        $('#widget_top_price').find('#currency').html(currency);
        $('#widget_popup_content_parkinn_right_currency').html(currency);
        hotelswidget.setJavaScript();
    }

    this.setAnalytics = function(){
	/*
		jQuery('body').append("<script type='text/javascript'>" +
		    "var _gaq = _gaq || [];_gaq.push(['_setAccount', 'UA-41604199-10']);_gaq.push(['_trackPageview']);" +
		    "(function() {var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);" +
		    "})();" +
		    "</script>");
	*/
        $('body').append("<script type='text/javascript'>" +
            "var _paq = _paq || [];" +
            "_paq.push(['trackPageView']);" +
            "_paq.push(['enableLinkTracking']);" +
            "(function() {var u='//www.frozenbullets.com/piwik/';_paq.push(['setTrackerUrl', u+'piwik.php']);_paq.push(['setSiteId', 1]);var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];g.type='text/javascript'; g.async=true; g.defer=true; g.src=u+'piwik.js'; s.parentNode.insertBefore(g,s);" +
            "})();" +
            "</script>" +
            "<noscript><p><img src='//www.frozenbullets.com/piwik/piwik.php?idsite=1' style='border:0;' alt='' /></p></noscript>");
    }

})(window, document, $);