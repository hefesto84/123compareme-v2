<%@ taglib prefix="s" uri="/struts-tags"%>

var html = htmlDecode("<s:property value="HTML"/>");
var css = "<s:property value="CSS"/>";

jQuery('body').append('<style>'+css+'</style>');
jQuery('body').append(html);



function htmlDecode(input){
  var e = document.createElement('div');
  e.innerHTML = input;
  return e.childNodes[0].nodeValue;
}

