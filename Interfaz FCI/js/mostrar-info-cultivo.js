$(document).ready(function() {
	var btnMostrarInfo = $(".cultivo__active");
	var descripcionCultivo = $("#informacion-cultivo");

	btnMostrarInfo.click(function() {
		descripcionCultivo.css({'display':'none'});
		descripcionCultivo.slideDown('1000');
	});
});