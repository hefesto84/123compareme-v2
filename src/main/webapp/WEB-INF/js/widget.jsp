<%@ taglib prefix="s" uri="/struts-tags"%>

var html = htmlDecode("<s:property value="HTML"/>");
alert(html);
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
	var translation =  "<s:property value="translation"/>";
	translation  = htmlDecode(translation);
	var obj = JSON.parse(translation);
	for(var key in obj) {
    var value = obj[key];
    	jQuery("#"+key+"").html(value);
	}
}


function htmlDecode(input){
  var e = document.createElement('div');
  e.innerHTML = input;
  return e.childNodes[0].nodeValue;
}

