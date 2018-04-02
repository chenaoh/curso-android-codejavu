<?PHP
$hostname_localhost ="localhost";
$database_localhost ="senasoft2016";
$username_localhost ="root";
$password_localhost ="";

	//Creamos la conexión	
	$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost) 
	or die("Ha sucedido un error inexperado en la conexion de la base de datos");
	
	//generamos la consulta
	$sql = "SELECT * FROM agenda";
	mysqli_set_charset($conexion, "utf8"); //formato de datos utf8

	if(!$result = mysqli_query($conexion, $sql)) die();

	$agenda = array(); //creamos un array

	while($row = mysqli_fetch_array($result)) 
	{ 
		$agenda_id=$row['agenda_id'];
		$orden=$row['orden'];
		$modalidad=$row['modalidad'];
		$tema=$row['tema'];
		$conferencista=$row['conferencista'];
		$fecha=$row['fecha'];
		$hora=$row['hora'];
		$lugar=$row['lugar'];
				

		$agenda['agenda'][] = array('agenda_id'=> $agenda_id, 'orden'=> $orden, 'modalidad'=> $modalidad, 'tema'=> $tema,'conferencista'=> $conferencista,'fecha'=> $fecha, 'hora'=> $hora, 'lugar'=> $lugar);
		//$clientes[] = array('agenda_id'=> $agenda_id, 'orden'=> $orden, 'modalidad'=> $modalidad, 'tema'=> $tema,'conferencista'=> $conferencista,'fecha'=> $fecha, 'hora'=> $hora, 'lugar'=> $lugar);
	}
		
	//desconectamos la base de datos
	$close = mysqli_close($conexion) 
	or die("Ha sucedido un error inexperado en la desconexion de la base de datos");
	  

	//Creamos el JSON
	$json_string = json_encode($agenda);
	echo $json_string;

?>
