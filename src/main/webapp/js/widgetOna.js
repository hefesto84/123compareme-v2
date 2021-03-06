var hotelswidget = new (function(window, document, $){
    var domain = 'https://www.123compare.me/v2'
    var data = '';

    this.init = function (showWidget,properties) {
        if(showWidget === false){
            hotelswidget.setAnalytics();
        } else {

            var conversion = 1;
            var hotel = properties.hotelName;
            var rooms = properties.rooms;
            var guests = properties.guests;
            var start = properties.start;
            var stop = properties.stop;
            var price = properties.price.toFixed(2);
            var currency = properties.currency;
            var lang = properties.lang;

            var device = 'isDesktop';

            var url_post = domain + '/api/prices?currency='+currency+'&base=' + price + '&hotel=' + encodeURI(hotel) + '&rooms=' + rooms + '&guests=' + guests + '&fin=' + start + '&fout=' + stop + '&lang=' + lang;

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
        var locale_da = '{"widget_top_text":"Best Price Guaranteed","widget_middle_text":"Do you want to compare prices?","widget_middle_button":"Check it here","widget_popup_content_top_text":"Best Price Guaranteed","widget_popup_content_parkinn_text":"Our lowest rate:","widget_popup_content_parkinn_left_text_t1":"Free Internet","widget_popup_content_parkinn_left_text_t2":"Earn Club Carlson Points","widget_popup_content_parkinn_text_bottom":"In other sites:","widget_popup_content_parkinn_right_bottom_t1":"No extra fees.","widget_popup_content_parkinn_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"Sorry, we couldn\'t complete the search for these dates"}';
        var locale_de = '{"widget_top_text":"Best Price Guaranteed","widget_middle_text":"Do you want to compare prices?","widget_middle_button":"Check it here","widget_popup_content_top_text":"Best Price Guaranteed","widget_popup_content_parkinn_text":"Our lowest rate:","widget_popup_content_parkinn_left_text_t1":"Free Internet","widget_popup_content_parkinn_left_text_t2":"Earn Club Carlson Points","widget_popup_content_parkinn_text_bottom":"In other sites:","widget_popup_content_parkinn_right_bottom_t1":"No extra fees.","widget_popup_content_parkinn_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"Sorry, we couldn\'t complete the search for these dates"}';
        var locale_es = '{"widget_top_text":"Best Price Guaranteed","widget_middle_text":"Do you want to compare prices?","widget_middle_button":"Check it here","widget_popup_content_top_text":"Best Price Guaranteed","widget_popup_content_parkinn_text":"Our lowest rate:","widget_popup_content_parkinn_left_text_t1":"Free Internet","widget_popup_content_parkinn_left_text_t2":"Earn Club Carlson Points","widget_popup_content_parkinn_text_bottom":"In other sites:","widget_popup_content_parkinn_right_bottom_t1":"No extra fees.","widget_popup_content_parkinn_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"Sorry, we couldn\'t complete the search for these dates"}';
        var locale_en = '{"widget_top_text":"BEST RATE GUARANTEED","widget_middle_text":"Do you want to compare prices?","widget_middle_button":"Check it here","widget_popup_content_top_text":"BEST RATE GUARANTEED","widget_popup_content_parkinn_text":"Our lowest rate:","widget_popup_content_parkinn_left_text_t1":"Free Internet","widget_popup_content_parkinn_left_text_t2":"Earn Club Carlson Points","widget_popup_content_parkinn_text_bottom":"In other sites:","widget_popup_content_parkinn_right_bottom_t1":"No extra fees.","widget_popup_content_parkinn_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"Sorry, we couldn\'t complete the search for these dates"}';
        var locale_en_UK = '{"widget_top_text":"Best Price Guaranteed","widget_middle_text":"Do you want to compare prices?","widget_middle_button":"Check it here","widget_popup_content_top_text":"Best Price Guaranteed","widget_popup_content_parkinn_text":"Our lowest rate:","widget_popup_content_parkinn_left_text_t1":"Free Internet","widget_popup_content_parkinn_left_text_t2":"Earn Club Carlson Points","widget_popup_content_parkinn_text_bottom":"In other sites:","widget_popup_content_parkinn_right_bottom_t1":"No extra fees.","widget_popup_content_parkinn_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"Sorry, we couldn\'t complete the search for these dates"}';
        var locale_fr = '{"widget_top_text":"GARANTIE DU MEILLEUR PRIX","widget_middle_text":"Voulez-vous comparer les prix?","widget_middle_button":"V&#233;rifiez ici","widget_popup_content_top_text":"GARANTIE DU MEILLEUR PRIX","widget_popup_content_parkinn_text":"Notre plus bas tarif:","widget_popup_content_parkinn_left_text_t1":"Internet gratuit","widget_popup_content_parkinn_left_text_t2":"Gagnez points Club Carlson","widget_popup_content_parkinn_text_bottom":"En d&#039;autres sites:","widget_popup_content_parkinn_right_bottom_t1":"Pas de frais suppl&#233;mentaires.","widget_popup_content_parkinn_right_bottom_t2":"Pas de frais cach&#233;s.", "widget_popup_content_bottom_button":"R&#233;server maintenant", "widget_popup_loading_text":"Regarder les résultats", "no_otas":"Sorry, we couldn\'t complete the search for these dates"}';
        var locale_it = '{"widget_top_text":"Best Price Guaranteed","widget_middle_text":"Do you want to compare prices?","widget_middle_button":"Check it here","widget_popup_content_top_text":"Best Price Guaranteed","widget_popup_content_parkinn_text":"Our lowest rate:","widget_popup_content_parkinn_left_text_t1":"Free Internet","widget_popup_content_parkinn_left_text_t2":"Earn Club Carlson Points","widget_popup_content_parkinn_text_bottom":"In other sites:","widget_popup_content_parkinn_right_bottom_t1":"No extra fees.","widget_popup_content_parkinn_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"Sorry, we couldn\'t complete the search for these dates"}';
        var locale_nl = '{"widget_top_text":"Best Price Guaranteed","widget_middle_text":"Do you want to compare prices?","widget_middle_button":"Check it here","widget_popup_content_top_text":"Best Price Guaranteed","widget_popup_content_parkinn_text":"Our lowest rate:","widget_popup_content_parkinn_left_text_t1":"Free Internet","widget_popup_content_parkinn_left_text_t2":"Earn Club Carlson Points","widget_popup_content_parkinn_text_bottom":"In other sites:","widget_popup_content_parkinn_right_bottom_t1":"No extra fees.","widget_popup_content_parkinn_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"Sorry, we couldn\'t complete the search for these dates"}';
        var locale_no = '{"widget_top_text":"Best Price Guaranteed","widget_middle_text":"Do you want to compare prices?","widget_middle_button":"Check it here","widget_popup_content_top_text":"Best Price Guaranteed","widget_popup_content_parkinn_text":"Our lowest rate:","widget_popup_content_parkinn_left_text_t1":"Free Internet","widget_popup_content_parkinn_left_text_t2":"Earn Club Carlson Points","widget_popup_content_parkinn_text_bottom":"In other sites:","widget_popup_content_parkinn_right_bottom_t1":"No extra fees.","widget_popup_content_parkinn_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"Sorry, we couldn\'t complete the search for these dates"}';
        var locale_pt_BR = '{"widget_top_text":"Best Price Guaranteed","widget_middle_text":"Do you want to compare prices?","widget_middle_button":"Check it here","widget_popup_content_top_text":"Best Price Guaranteed","widget_popup_content_parkinn_text":"Our lowest rate:","widget_popup_content_parkinn_left_text_t1":"Free Internet","widget_popup_content_parkinn_left_text_t2":"Earn Club Carlson Points","widget_popup_content_parkinn_text_bottom":"In other sites:","widget_popup_content_parkinn_right_bottom_t1":"No extra fees.","widget_popup_content_parkinn_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"Sorry, we couldn\'t complete the search for these dates"}';
        var locale_ru = '{"widget_top_text":"Best Price Guaranteed","widget_middle_text":"Do you want to compare prices?","widget_middle_button":"Check it here","widget_popup_content_top_text":"Best Price Guaranteed","widget_popup_content_parkinn_text":"Our lowest rate:","widget_popup_content_parkinn_left_text_t1":"Free Internet","widget_popup_content_parkinn_left_text_t2":"Earn Club Carlson Points","widget_popup_content_parkinn_text_bottom":"In other sites:","widget_popup_content_parkinn_right_bottom_t1":"No extra fees.","widget_popup_content_parkinn_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"Sorry, we couldn\'t complete the search for these dates"}';
        var locale_sv = '{"widget_top_text":"Best Price Guaranteed","widget_middle_text":"Do you want to compare prices?","widget_middle_button":"Check it here","widget_popup_content_top_text":"Best Price Guaranteed","widget_popup_content_parkinn_text":"Our lowest rate:","widget_popup_content_parkinn_left_text_t1":"Free Internet","widget_popup_content_parkinn_left_text_t2":"Earn Club Carlson Points","widget_popup_content_parkinn_text_bottom":"In other sites:","widget_popup_content_parkinn_right_bottom_t1":"No extra fees.","widget_popup_content_parkinn_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"Sorry, we couldn\'t complete the search for these dates"}';
        var locale_cn = '{"widget_top_text":"Best Price Guaranteed","widget_middle_text":"Do you want to compare prices?","widget_middle_button":"Check it here","widget_popup_content_top_text":"Best Price Guaranteed","widget_popup_content_parkinn_text":"Our lowest rate:","widget_popup_content_parkinn_left_text_t1":"Free Internet","widget_popup_content_parkinn_left_text_t2":"Earn Club Carlson Points","widget_popup_content_parkinn_text_bottom":"In other sites:","widget_popup_content_parkinn_right_bottom_t1":"No extra fees.","widget_popup_content_parkinn_right_bottom_t2":"No hidden charges.", "widget_popup_content_bottom_button":"Book Now", "widget_popup_loading_text":"Searching", "no_otas":"Sorry, we couldn\'t complete the search for these dates"}';

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
        //var href = hotelswidget.setUrlHref();
        $('.widget_content_loading').hide();
        $('#widget_popup_loading_text').hide();
        data = datos;
        if (data.datos.length == 0){
            //_paq.push(['trackEvent', 'Widget', 'No results', 'No se ha mostrado el widget por que no hay datos']);
            return 0;
        }
        var content_middle = document.getElementById("widget_popup_content_middle");
        var count = 0;
        for (var i = 0; i < data.datos.length; i++) {

            var precio_convertido = (parseFloat(data.datos[i].price) * conversion);

            if (((price - 1) < precio_convertido || (price - 1) == precio_convertido) &&  (count < 5)) {
                count = count + 1;
                //if (data.datos[i].site != 'Park Inn'){
                var element = document.createElement("div");
                element.setAttribute('id', 'element');

                var image = document.createElement('img');
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
            $('#widget_popup_content_middle').append("<div id='no_otas'>Sorry, we couldn\'t complete the search for these dates</div>");
            //_paq.push(['trackEvent', 'Widget', 'No results', 'No se han mostrado resultados']);
        } else {
            //_paq.push(['trackEvent', 'Widget', 'Results', 'Se han mostrado '+count+' resultados']);
        }
        //$('#boton_reservar_widget').attr('href', href);
    }

    this.setWidgetMobile = function(datos,price,conversion, currency){

    }

    this.setJavaScript = function(){

        var top_widget = 258;

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


        $('#widget').click(function(){
            $('#widget_popup').show();
            //_paq.push(['trackEvent', 'Widget', 'Click', 'Widget clicado']);
        });
        $('#widget_popup_content_top_close').click(function(){
            $('#widget_popup').hide();
        });

        //_paq.push(['trackEvent', 'Widget', 'Show', 'Widget mostrado correctamente']);
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
        var script = $('td.v1').find('a').attr('href');
        var pos_first = script.indexOf('(');
        var pos_last = script.indexOf(')');
        var parameters = script.slice(pos_first+1,pos_last);
        var array = parameters.split(',');
        var href = "javascript:hotelswidget.functionReservar("+array[0]+","+array[1]+","+array[2]+","+array[3]+")";
        return href;
    }


    this.functionReservar = function(pHotelCode, pRateUni, pRedemptionRate, pClearEcertCode){
        //_paq.push(['trackEvent', 'Widget', 'Click Book', 'Widget Reservar']);
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
            "#widget_top{padding: 6px;padding-left: 30px;padding-right: 30px;background-color: #0799C7;color: #FFFFFF;text-align: center;font-family: 'Montserrat', Helvetica, Arial, sans-serif;}" +
            "#widget_top_text{font-size: 12px;margin-bottom: 5px;}" +
            "#widget_top_price #price{font-size: 25px;font-weight: bold;}" +
            "#widget_middle_top{height: 2px; background-color: #40BFF9;}" +
            "#widget_middle{margin-top: 0px;background-color: #F5F5F5;text-align: center;font-family: 'Montserrat', Helvetica, Arial, sans-serif; padding-bottom: 10px;}" +
            "#widget_middle_text{color: #0799C7; padding-top: 10px; text-transform: uppercase;}" +
            "#widget_middle_button{width: 70%;margin: auto;padding: 0.55em;color: #FFFFFF;background-color: #A8C334;margin-top: 10px; border-radius: 5px;}" +
            "#widget_popup_content_parkinn_text_bottom {margin-bottom: 0px;margin-top: 10px;text-align: left;padding-left: 10px;font-weight: bold;}" +
            "#widget_popup{display: none;font-family: 'Montserrat', Helvetica, Arial, sans-serif;}" +
            "#widget_content_background{position: fixed;left: 0px;top: 0px;width: 100%;height: 100%;background: rgba(0,0,0,0.5);z-index: 9999999999;}" +
            "#widget_popup_content{position: absolute;top: 50px;width: 300px;box-sizing: border-box;}" +
            "#widget_popup_content_top{background-color: #0799C7 !important;color: #FFFFFF;padding: 5px;text-align: center;line-height: 40px;font-size: 16px;}" +
            "#widget_popup_content_top_text{padding-left: 15px;}" +
            "#widget_popup_content_top_close{margin-bottom: -22px;float: right;width: 25px;height: 25px;background: url('https://www.123compare.me/v1/images/close.png');background-size: 100% 100%;}" +
            "#widget_popup_content_parkinn{padding: 10px;padding-bottom: 0px;background-color: #FFFFFF;}" +
            "#widget_popup_content_parkinn_text {padding-left: 10px;font-size: 12px;margin-bottom: 10px;text-align: left;font-family: 'Montserrat', Helvetica, Arial, sans-serif;font-weight: bold;}" +
            "#widget_popup_content_middle{padding: 10px;padding-top: 0px;background-color: #FFFFFF;}" +
            ".widget_content_loading{display:block;margin:auto;padding-top:15px;}" +
            "#widget_popup_loading_text{margin-top: 10px;font-family: 'Montserrat', Helvetica, Arial, sans-serif;font-size: 14px;}" +
            "#widget_popup_content_bottom{padding: 10px;background-color: #FFFFFF;}" +
            "#widget_popup_content_bottom_button{width: 40%;text-align:center;color: #FFFFFF;margin: auto;background-color: #A8C334;padding: 0.55em;margin-top: 10px;border-radius:5px;}" +
            "#element{padding: 10px;border-bottom: 1px solid #EBEAEC;}" +
            "#element img{width: auto;float: left;height: 22px;}" +
            "#element .currency{float: left;line-height: 22px;color: #4A4B4C;font-family: 'Montserrat', Helvetica, Arial, sans-serif;}" +
            "#element .text{text-align: center;font-size: 12px;color: #4A4B4C;font-family: 'Montserrat', Helvetica, Arial, sans-serif;}" +
            "#element .priceWidgetElement {float: right;font-family: 'Montserrat', Helvetica, Arial, sans-serif;margin-left: 5px;line-height: 22px;color: #4A4B4C;font-size: 18px;}" +
            ".image_parkinn{width: 140px;height: auto;display: block;margin-bottom: 5px;}" +
            "#widget_popup_content_parkinn_left{width: 40%;box-sizing: border-box;float: left;line-height: 10px;}" +
            "#widget_popup_content_parkinn_left_text {font-size: 10px;font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;}" +
            "#widget_popup_content_parkinn_right{padding-right: 10px;width: 60%;box-sizing: border-box;float: right;text-align: right;font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;color: #0799C7;}" +
            "#widget_popup_content_parkinn_right_price{font-weight: bold;font-size: 25px;line-height: 47px;}" +
            "#widget_popup_content_parkinn_right_bottom {font-size: 10px;width: 47%;float: right;line-height: 10px;}" +
            "#boton_reservar_widget {text-decoration: none;}" +
            "#no_otas {padding-top: 10px;font-size: 14px;font-weight: bold;width: 80%;margin: auto;}" +
            "@media (min-width: 1024px) and (max-width: 1280px){" +
            "#widget{width: 145px;}#widget_top{padding-left: 10px;padding-right: 10px;}}");
        hotelswidget.setAnalytics();
    }

    this.setHtml = function(price,currency) {
        $('body').append("<div id='widget'><div id='widget_top'><div id='widget_top_text'></div><div id='widget_top_price'><span id='price'>850.00</span>&nbsp;&nbsp;<span id='currency'>SEK</span></div></div><div id='widget_middle_top'></div><div id='widget_middle'><div id='widget_middle_text'>Do you want to compare prices?</div><div id='widget_middle_button'>Check it here</div></div><div id='widget_copyright' style='padding-top:2px;'><a href='http://www.123compare.me' style='text-decoration: none;'><span style='color:gray;font-size:10px;font-weight:bold;text-align:right;width:auto;position:relative;margin-top:5px;'></span></a></div></div><div id='widget_popup'><div id='widget_content_background'><div id='widget_popup_content'><div id='widget_popup_content_top'><span id='widget_popup_content_top_text'>Best Price Guaranteed</span><div id='widget_popup_content_top_close'></div><div style='clear:both;'></div></div><div id='widget_popup_content_parkinn'><!--<div id='widget_popup_content_parkinn_text'>Our lowest rate:</div>--><div id='widget_popup_content_parkinn_left' style='width:45%;'><img class='image_parkinn' src='https://www.123compare.me/v1/images/pages/onahotels.png' /><!--<span id='widget_popup_content_parkinn_left_text' class='t1' style='display:block;text-align:left;'>Free Internet</span><span id='widget_popup_content_parkinn_left_text' class='t2' style='display: block;text-align: left; font-size: xx-small;'>Earn Club Carlson Points&reg;</span>--></div><div id='widget_popup_content_parkinn_right' style='width:50%;'><span id='widget_popup_content_parkinn_right_price'>1,095.00</span>&nbsp;<span id='widget_popup_content_parkinn_right_currency' style='font-size:18px;'>SEK</span><div id='widget_popup_content_parkinn_right_bottom' style='width:100%;'><span class='widget_popup_content_parkinn_right_bottom_t1' style='display:block;'>No extra fees.</span><span class='widget_popup_content_parkinn_right_bottom_t2'>No hidden charges.</span></div><div style='clear:both;'></div></div><div style='clear:both;'></div><!--<div id='widget_popup_content_parkinn_text_bottom'>In other sites:</div>--></div><div id='widget_popup_content_middle'><img src='https://www.123compare.me/v1/images/loading.GIF' class='widget_content_loading'/><div id='widget_popup_loading_text'></div></div><div id='widget_popup_content_bottom'><a href='' id='boton_reservar_widget'><div id='widget_popup_content_bottom_button'>Book Now</div></a></div><div id='widget_copyright' style='padding-top:5px;'> <a href='http://www.123compare.me' style='text-decoration: none; float:right;'><span style='color:black;font-size:10px;text-align:right;width:auto;position:relative;letter-spacing:1px;'>Powered by 123Compare.me&#169;</span></a></div></div></div></div>");
        $('#widget_top_price').find('#price').html(price.toFixed(2));
        $('#widget_popup_content_parkinn_right_price').html(price.toFixed(2));

        $('#widget_top_price').find('#currency').html(currency);
        $('#widget_popup_content_parkinn_right_currency').html(currency);
        hotelswidget.setJavaScript();
    }

    this.setHtmlMobile = function(price,currency, time) {
    }

    this.setAnalytics = function(){
        $('body').append("<script type='text/javascript'>" +
            "var _paq = _paq || [];" +
            "</script>");
        /*$('body').append("<script type='text/javascript'>" +
            "var _paq = _paq || [];" +
            "_paq.push(['trackPageView']);" +
            "_paq.push(['enableLinkTracking']);" +
            "(function() {var u='//www.123compare.me/piwik/';_paq.push(['setTrackerUrl', u+'piwik.php']);_paq.push(['setSiteId', 2]);var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];g.type='text/javascript'; g.async=true; g.defer=true; g.src=u+'piwik.js'; s.parentNode.insertBefore(g,s);" +
            "})();" +
            "</script>" +
            "<noscript><p><img src='//www.123compare.me/piwik/piwik.php?idsite=2' style='border:0;' alt='' /></p></noscript>");*/
    }

})(window, document, $);