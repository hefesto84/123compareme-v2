<%@ taglib prefix="s" uri="/struts-tags"%>


var hotelswidget = new (function(window, document, jQuery){
    var domain = 'https://www.123compare.me/v2'
    var datos = {};
    datos.user = 1;
    datos.hotel = hotelswidget.hotelName(jQuery('.innername').find("a").text());
    datos.rooms = jQuery('[name="rateSearchForm.numberRooms"]').val();
    //datos.guests = jQuery('[name="occupancyForm[0].numberAdults"]').val();
    datos.guests = 2;
    datos.start = hotelswidget.dateConverse(jQuery('[name="rateSearchForm.checkinDate"]').val());
    datos.stop = hotelswidget.dateConverse(jQuery('[name="rateSearchForm.checkoutDate"]').val());
    datos.currency = jQuery('.ratecurrency').first().text().trim();
    datos.lang = jQuery('html').attr('lang');
    datos.price = hotelswidget.priceConverse(jQuery('.rateamount').first().text().trim(), datos.lang);
    datos.device = 'isDesktop';
    datos.diffDay = hotelswidget.diffDate(datos.start,datos.stop);
    
    var json = JSON2.stringify(datos);
    
    jQuery.ajax({
		 method: "POST",
		 url: "http://localhost:8080/123CompareMe-v2/widgets/new/widget",
		 data: { datos: json },
		 dataType: "script",
		 success: function(msg) {
		 	alert('In Ajax');
		 	alert(msg);
		 	msg;
		 }
		});
    
    
    
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
    
    this.diffDate = function (fini,fout){
   		var date1 = new Date(fini.slice(6,10),fini.slice(3,5),fini.slice(0,2));
		var date2 = new Date(fout.slice(6,10),fout.slice(3,5),fout.slice(0,2));
		var timeDiff = Math.abs(date2.getTime() - date1.getTime());
		return diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
    }
    
})(window, document,jQuery);
