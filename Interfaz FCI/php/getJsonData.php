<?php

	$conexion = new mysqli("agrouginstance.cnoq0sjsxakz.us-east-1.rds.amazonaws.com", "user", "reload20", "agroug");

	if($conexion->connect_errno){
		echo "error";
	}

	if(isset($_GET['culId'])){
		$id_cultivo = $_GET['culId'];
	}
	else{
		$id_cultivo = 0;	
	}
	
  	$res = $conexion->query("CALL SP_UBICACION_CULTIVOS($id_cultivo)");
  	$res = mysqli_fetch_all($res, MYSQLI_ASSOC);

  echo json_encode($res);
?>