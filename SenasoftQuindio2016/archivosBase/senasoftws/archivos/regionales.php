<font face="Arial" size="2">
<?PHP
$hostname_localhost ="mysql6.000webhost.com";
$database_localhost ="a7146134_sena";
$username_localhost ="a7146134_henao";
$password_localhost ="chenao123";

$codigo=$_REQUEST['categoria'];
	//Creamos la conexiÃ³n	
	$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost) 
	or die("Ha sucedido un error inexperado en la conexion de la base de datos");
	
	//generamos la consulta
$sql="SELECT DISTINCT(regionales.regional),regionales.regional_id FROM regionales,centros,categorias WHERE regionales.regional_id=centros.regional_id AND centros.categoria_id=categorias.categoria_id  AND categorias.categoria_id=".$codigo."  ORDER BY regionales.regional, centros.centro";
	mysqli_set_charset($conexion, "utf8"); //formato de datos utf8

	if(!$result = mysqli_query($conexion, $sql)) die();
	echo "<FORM NAME= 'Formulario' ACTION ='centros.php' method='post' target='centros'><b>";
	echo "<input type='hidden' name='categoria' value='".$codigo."'/>";
	echo "<label>Seleccione Regional</label><br>";
	echo "<select name='regional' onChange ='document.Formulario.submit()'>";
	echo "<option> </option>";
	while($row = mysqli_fetch_array($result)) 
	{ 
		echo "<option value='". $row['regional_id']."'>".$row['regional']."</option>";
	}
	echo "</select>";
	echo "</FORM>";	
	//desconectamos la base de datos
	$close = mysqli_close($conexion) 
	or die("Ha sucedido un error inexperado en la desconexion de la base de datos");


?>