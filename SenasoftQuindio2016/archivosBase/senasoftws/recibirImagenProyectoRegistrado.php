<?PHP
$hostname_localhost ="localhost";
$database_localhost ="senasoft2016";
$username_localhost ="root";
$password_localhost ="";

$json['proyecto']=array();

	//if(true){)
	if(isset($_POST["btn"])){
		
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		
		$ruta="imagenes";
		$archivo=$_FILES['imagen']['tmp_name'];
		
		$nombreArchivo=$_FILES['imagen']['name'];
		move_uploaded_file($archivo,$ruta."/".$nombreArchivo);
		$ruta=$ruta."/".$nombreArchivo;
		$nombre=$_POST['nombre'];
		$descripcion=$_POST['descripcion'];
		

		echo '<br>';
		echo '<br>';
		echo 'Nombre Imagen: ';
		echo $nombre;
		echo '<br>';
		echo 'Descripcion ';
		echo $descripcion;
		echo '<br>';
		echo 'ruta :';
		echo $ruta;
		echo '<br>';
		echo 'Tipo Imagen: ';
		echo ($_FILES['imagen']['type']);
		echo '<br>';
		echo '<br>';
		echo "Imagen: <br><img src='$ruta'>";
		echo '<br>';
		echo '<br>';
		echo 'imagen en Bytes: ';
		echo '<br>';
		echo '<br>';
	//	echo $bytesArchivo=file_get_contents($ruta);
		echo '<br>';
		
		$bytesArchivo=file_get_contents($ruta);
		$sql="INSERT INTO proyecto(nombre,descripcion,logo) VALUES (?,?,?)";
		$stm=$conexion->prepare($sql);
		$stm->bind_param('sss',$nombre,$descripcion,$bytesArchivo);

		if($stm->execute()){
			echo 'empresa Insertada Exitosamente ';
			$consulta="select * from proyecto where nombre='{$nombre}'";
			$resultado=mysqli_query($conexion,$consulta);
			echo '<br>';
			while ($row=mysqli_fetch_array($resultado)){
				echo $row['proyecto_id'].' - '.$row['nombre'].'<br/>';
				array_push($json['proyecto'],array('proyecto_id'=>$row['proyecto_id'],'nombre'=>$row['nombre'],'logo'=>base64_encode($row['nombre'])));
			}
			mysqli_close($conexion);
			
			echo '<br>';
			echo 'Objeto JSON 2';
			echo '<br>';
			echo json_encode($json);
		}
		else{
			$resultar["id"]=0;
			$resultar["nombre"]='no registro';
			$json['proyecto'][]=$resultar;
			echo json_encode($json);
		}
	}
?>