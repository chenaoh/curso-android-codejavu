<font face="Arial" size="2">
<?PHP
$hostname_localhost ="mysql6.000webhost.com";
$database_localhost ="a7146134_sena";
$username_localhost ="a7146134_henao";
$password_localhost ="chenao123";

	//Creamos la conexiÃ³n	
	$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost) 
	or die("Ha sucedido un error inexperado en la conexion de la base de datos");
	
	//generamos la consulta
	$sql = "SELECT * FROM categorias ORDER BY categoria";
	mysqli_set_charset($conexion, "utf8"); //formato de datos utf8

	if(!$result = mysqli_query($conexion, $sql)) die();
	echo "<FORM NAME= 'Formulario' ACTION ='regionales.php' method='post' target='regionales'><b>";
	echo "<label>Seleccione categoria</label><br>";
	echo "<select name='categoria' onChange ='document.Formulario.submit()'>";
	echo "<option> </option>";
	while($row = mysqli_fetch_array($result)) 
	{ 
		echo "<option value='". $row['categoria_id']."'>".$row['categoria']."</option>";
	}
	echo "</select>";
	echo "</FORM>";	
	//desconectamos la base de datos
	$close = mysqli_close($conexion) 
	or die("Ha sucedido un error inexperado en la desconexion de la base de datos");


?>