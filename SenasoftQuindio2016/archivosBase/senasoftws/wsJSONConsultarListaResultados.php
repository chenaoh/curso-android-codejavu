<?PHP
$hostname_localhost ="localhost";
$database_localhost ="senasoft2016";
$username_localhost ="root";
$password_localhost ="";

	$json=array();

	if(isset($_GET["categoria_id"])){
		$cant=0;
		$codigo=$_GET["categoria_id"];
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost) 
		or die("Ha sucedido un error inexperado en la conexion de la base de datos");
	

		//$consulta="SELECT * FROM resultados WHERE categoria_id= '{$codigo}' ORDER BY total DESC";
		
		$consulta="SELECT r.categoria_id,re.regional,c.centro,r.jornada1,r.jornada2,r.jornada3,r.total 
			FROM resultados r, centros c, regionales re
				WHERE r.categoria_id=c.categoria_id  AND r.regional_id=c.regional_id
					AND r.regional_id=re.regional_id AND c.centro_id=r.centro_id AND r.categoria_id='{$codigo}' 
				ORDER BY total DESC";
			
				
	
		
		mysqli_set_charset($conexion, "utf8"); //formato de datos utf8
		
		$resultado=mysqli_query($conexion,$consulta);
					
		while($registro=mysqli_fetch_array($resultado)){
			$json['resultados'][]=$registro;
			$cant=1;
			//echo $registro['id'].' - '.$registro['nombre'].'<br/>';
		}
		
		if($cant==0){
			$resultar["categoria_id"]=0;
			$json['resultados'][]=$resultar;
		}
		
		mysqli_close($conexion);
		echo json_encode($json);
	}
	else{
		$resultar["success"]=0;
		$resultar["message"]='Ws no Retorna';
		$json['resultados'][]=$resultar;
		echo json_encode($json);
	}

?>
