<%@ taglib prefix="s" uri="/struts-tags"%>

var html = htmlDecode("<s:property value="HTML"/>");
var css = "<s:property value="CSS"/>";

domain = "<s:property value="data.domain"/>";
user = "<s:property value="data.user"/>";
hotel = "<s:property value="data.hotel"/>";
rooms = "<s:property value="data.rooms"/>";
guests = "<s:property value="data.guests"/>";
start = "<s:property value="data.start"/>";
stop = "<s:property value="data.stop"/>";
currency = "<s:property value="data.currency"/>";
lang = "<s:property value="data.lang"/>";
price = "<s:property value="data.price"/>";
device = "<s:property value="data.device"/>";
diffDay = "<s:property value="data.diffDay"/>";

var url_post = domain + '/api/prices?currency='+ currency + '&base=' + price + '&code=' + user + '&hotel=' + encodeURI(hotel) + '&rooms=' + rooms + '&guests=' + guests + '&fin=' + start + '&fout=' + stop + '&lang=' + lang;

if ((price !== 'undefined') && (price !== '') && (price !== 'NaN')){
    price = parseFloat(price);
    if(device === 'isDesktop'){
    	setCSS123();
        setHtml123(price,currency,lang);
    } else {
    	setCSSMobile123();
        setHtmlMobile123(price,currency,lang);
    }
    setTranslate123(lang);
    setTimeout(function() {
       	getDatosWidget123(url_post,device,price,currency,lang,diffDay);
    },1000) ;
}


function setCSS123(){
	jQuery('body').append('<style>'+css+'</style>');
}

function setCSSMobile123(){
}

function setHtml123(price,currency,lang) {
	jQuery('body').append(html);  
	hotelswidget.setJavascript.setPriceInWidget(price,currency,lang);
	hotelswidget.setJavascript.setWidgetJavascript();
}

function setHtmlMobile123(price,currency, time) {
}

function getDatosWidget123(url_post,device,price,currency,lang,diffDays){
    var datos;
    jQuery.ajax({
        type: "GET",
        url: url_post,
        success: function (respuesta) {
            console.log(respuesta);
            datos = respuesta;
            if (device === 'isMobile'){
                setWidgetMobile123(datos,price,currency);
            } else {
                setWidget123(datos,price,currency,diffDays);
            }
        }
    });
}

function setWidget123(datos,price,currency,diffDay) {    
    var href = hotelswidget.setUrlHref();
    hotelswidget.setJavascript.setWidgetData(datos,price,currency,diffDay);
    jQuery('#boton_reservar_widget').attr('href', href);
}

function setWidgetMobile123(datos,price,currency){

}

function setTranslate123(lang){

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
    var t = JSON.parse(unescape(encodeURIComponent(translations[lang])));

    jQuery('#widget_top_text').html(t["widget_top_text"]);
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

    jQuery('#widget').show();

}


function htmlDecode(input){
  var e = document.createElement('div');
  e.innerHTML = input;
  return e.childNodes[0].nodeValue;
}

