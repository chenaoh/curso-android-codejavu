<?PHP
$hostname_localhost ="localhost";
$database_localhost ="senasoft2016";
$username_localhost ="root";
$password_localhost ="";

$json['empresa']=array();

	//if(true){)
	if(isset($_POST["btn"])){
		
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		
		$ruta="imagenes";
		$archivo=$_FILES['imagen']['tmp_name'];
		
		$nombreArchivo=$_FILES['imagen']['name'];
		move_uploaded_file($archivo,$ruta."/".$nombreArchivo);
		$ruta=$ruta."/".$nombreArchivo;
		$id=$_POST['id'];
		$nombre=$_POST['nombre'];
		$descripcion=$_POST['descripcion'];
		
		echo '<br>';
		echo 'id Imagen: ';
		echo $id;
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
		$sql="INSERT INTO empresas(nombre,descripcion,logo) VALUES (?,?,?)";
		$stm=$conexion->prepare($sql);
		$stm->bind_param('sss',$nombre,$descripcion,$bytesArchivo);

		
	//	$sql="INSERT INTO empresas(nombre,descripcion,imagen) VALUES ('{$nombre}','{$descripcion}','{$bytesArchivo}')";
	//	$stm=$conexion->prepare($sql);
	//	$stm->bind_param(1,$nombre);
//		$stm->bind_param(2,$descripcion);
	//	$stm->bind_param(3,$bytesArchivo);
	
		//$insert ="INSERT INTO empresas(nombre,descripcion,logo) VALUES ('{$nombre}','{$descripcion}',{$bytesArchivo})";
		//$insert ="INSERT INTO empresa(id,nombre,descripcion,logo) VALUES ('{$id}','{$nombre}','{$descripcion}',{$bytesArchivo})";
		//$resultado_insert=mysqli_query($conexion,$insert);
		
		if($stm->execute()){//if($resultado_insert){//
			echo 'empresa Insertada Exitosamente ';
			$consulta="select * from empresas where nombre='{$nombre}'";
			$resultado=mysqli_query($conexion,$consulta);
			echo '<br>';
			while ($row=mysqli_fetch_array($resultado)){
				echo $row['empresa_id'].' - '.$row['nombre'].'<br/>';
				array_push($json['empresa'],array('empresa_id'=>$row['empresa_id'],'nombre'=>$row['nombre'],'logo'=>base64_encode($row['nombre'])));
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
			$json['persona'][]=$resultar;
			echo json_encode($json);
		}
	}
?>