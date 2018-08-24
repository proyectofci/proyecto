
var map, infoWindow;

function initMap() {
  var infoWindow = new google.maps.InfoWindow;

  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(function(position) {
      var pos = {
        lat: position.coords.latitude,
        lng: position.coords.longitude
      };

      var map = new google.maps.Map(
   	document.getElementById('map'), {center: pos,zoom: 7});
  		
  		var marker = new google.maps.Marker({position: pos, map: map});

  		var direcciones;
  		var geocoder = new google.maps.Geocoder;

		$.ajax({
			url: 'geo.json',
			dataType: 'json',
			success: function(data){
				$.each(data.direcciones, function(index, el) {
					var posicion = {
						lat: el.latitud,
						lng: el.longitud
					};

					// geocodeLatLng(geocoder, posicion, map);
					var marker = new google.maps.Marker({
						position: posicion, 
						map:map,
						title: el.nombre
					});

					var listaUbicaciones = $("#lista-ubicaciones");
					var item = "<li> " + el.nombre + "</li>";
					listaUbicaciones.append(item);
				});	
			}
		});

      // infoWindow.setPosition(pos);
      // infoWindow.setContent('Tu ubicacion...');
      // infoWindow.open(map);
      // map.setCenter(pos);
    }, function() {
      handleLocationError(true, infoWindow, map.getCenter());
    });
  } else {
    // Browser doesn't support Geolocation
    handleLocationError(false, infoWindow, map.getCenter());
  }
}

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
  infoWindow.setPosition(pos);
  infoWindow.setContent(browserHasGeolocation ?
                        'Error: The Geolocation service failed.' :
                        'Error: Your browser doesn\'t support geolocation.');
  infoWindow.open(map);
}

/* === AL API DETECTA LA LOCALIZACION A PARTIR DE LA LATITUD Y LONGITUD ===*/
function geocodeLatLng(geocoder, latlng, longitud,map) {

  geocoder.geocode({'location': latlng}, function(results, status) {
  	console.log(status);
	if (status === 'OK') {
		if (results[0]) {
		  var geoNombre = results[0].formatted_address;

		  var marker = new google.maps.Marker({
				position: latlng, 
				map:map,
				title: geoNombre
			});

		  var listaUbicaciones = $("#lista-ubicaciones");
		  var item = "<li> " + geoNombre + "</li>";
		  listaUbicaciones.append(item);
		  
		} 
		else {
		  window.alert('No results found');
		}
	} else {
	window.alert('Geocoder failed due to: ' + status);
	}
  });
}
