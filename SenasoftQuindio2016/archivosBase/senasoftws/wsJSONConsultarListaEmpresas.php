<?PHP
$hostname_localhost ="localhost";
$database_localhost ="senasoft2016";
$username_localhost ="root";
$password_localhost ="";

$json=array();
	//			
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost)
		or die("Ha sucedido un error inexperado en la conexion de la base de datos");
		mysqli_set_charset($conexion, "utf8"); //formato de datos utf8
		
		$agenda = array(); //creamos un array
		$x = 0;
	
		//generamos la consulta
		$sql = "SELECT * FROM empresas";
		if(!$resultado = mysqli_query($conexion, $sql)) die();
		
		while($registro=mysqli_fetch_array($resultado)){
			$resulta['empresa_id']=$registro['empresa_id'];
			$resulta['nombre']=$registro['nombre'];
			$resulta['logo']=base64_encode($registro['logo']);
			$json['empresa'][]=$resulta;
			
		}
	//	mysqli_close($conexion);
		echo json_encode($json);
		//desconectamos la base de datos
		$close = mysqli_close($conexion) 
		or die("Ha sucedido un error inexperado en la desconexion de la base de datos");
	  
?>

