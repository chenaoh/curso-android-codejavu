<?PHP
$hostname_localhost ="localhost";
$database_localhost ="senasoft2016";
$username_localhost ="root";
$password_localhost ="";

	$json=array();
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

		$consulta="select * from agenda";
		$resultado=mysqli_query($conexion,$consulta);
		
		while($registro=mysqli_fetch_array($resultado)){
			$json['agenda'][]=$registro;
			//echo $registro['id'].' - '.$registro['nombre'].'<br/>';
		}
		mysqli_close($conexion);
		//var_dump($json);
		echo json_encode($json);
?>
