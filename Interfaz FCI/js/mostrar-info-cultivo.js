$(document).ready(function() {
	var btnMostrarInfo = $(".cultivo__active");
	var descripcionCultivo = $("#informacion-cultivo");
	var main = $("#main");
	var primeraCarga = true;

	btnMostrarInfo.click(function() {
		var posicionActual	 = $(document).scrollTop();
		var posicion = main.offset().top;

		if(primeraCarga){
			
			descripcionCultivo.css({'display':'none'});	
			
			$("html, body").animate({
				scrollTop : posicion
			}, 500, function(){
				primeraCarga = false;
			});
		}

		if(!primeraCarga){
			descripcionCultivo.children().fadeOut('fast');
			descripcionCultivo.children().fadeIn('fast');
		}
		
		descripcionCultivo.slideDown('1000');
	});
});