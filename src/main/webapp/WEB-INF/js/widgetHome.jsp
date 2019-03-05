<%@ taglib prefix="s" uri="/struts-tags"%>

var html = htmlDecode("<s:property value="HTML"/>");
var css = "<s:property value="CSS"/>";
var prices = htmlDecode("<s:property value="prices"/>");

user = "<s:property value="dataHome.user"/>";
hotel = "<s:property value="dataHome.hotel"/>";
lang = "<s:property value="dataHome.lang"/>";
device = "<s:property value="dataHome.device"/>";
var datos = JSON.parse(prices);

if ((datos[0] !== undefined) && (datos[0].basePrice !== undefined)){
	price = datos[0].basePrice;
	currency = datos[0].currency.toUpperCase();
} else {
	price = '';
}


if ((price !== undefined) && (price !== '') && (price !== 'NaN')){
    price = parseFloat(price);
    if(device === 'isDesktop'){
    	setCSS123();
        setHtml123(price,currency,lang);
    } else {
    	setCSSMobile123();
        setHtmlMobile123(price,currency,lang);
    }
    setTranslate123(lang);
    setWidget123(datos,price,currency)
}


function setCSS123(){
	jQuery('body').append('<style id="widget123CSS">'+css+'</style>');
}

function setCSSMobile123(){
	jQuery('body').append('<style id="widget123CSS">'+css+'</style>');
}

function setHtml123(price,currency,lang) {
	jQuery('body').append(html);  
	hotelswidget.setJavascript.setPriceInWidget(price,currency,lang);
	hotelswidget.setJavascript.setWidgetJavascript();
}

function setHtmlMobile123(price,currency, time) {
	jQuery('body').append(html);  
	hotelswidget.setJavascript.setPriceInWidget(price,currency,lang);
	hotelswidget.setJavascript.setWidgetJavascript();
}

function setWidget123(datos,price,currency) {    
    hotelswidget.setJavascript.setWidgetData(datos,price,currency);
}

function setTranslate123(lang){
	var translation =  "<s:property value="translation"/>";
	translation  = htmlDecode(translation);
	var obj = JSON.parse(translation);
	jQuery('[data-translation]').each(function(){
		jQuery(this).html(obj[jQuery(this).attr('data-translation')]);
	});
}


function htmlDecode(input){
  var e = document.createElement('div');
  e.innerHTML = input;
  return e.childNodes[0].nodeValue;
}