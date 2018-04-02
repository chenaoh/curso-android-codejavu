<font face="Arial" size="2">
<?PHP
$hostname_localhost ="mysql6.000webhost.com";
$database_localhost ="a7146134_sena";
$username_localhost ="a7146134_henao";
$password_localhost ="chenao123";

$categoria=$_REQUEST['categoria'];
$regional=$_REQUEST['regional'];

	//Creamos la conexiÃ³n	
	$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost) 
	or die("Ha sucedido un error inexperado en la conexion de la base de datos");
	
	//generamos la consulta
$sql="SELECT centros.centro_id, centros.centro,categorias.categoria FROM regionales,centros,categorias WHERE regionales.regional_id=centros.regional_id AND centros.categoria_id=categorias.categoria_id AND (regionales.regional_id=".$regional." AND categorias.categoria_id=".$categoria.")";
	mysqli_set_charset($conexion, "utf8"); //formato de datos utf8

	if(!$result = mysqli_query($conexion, $sql)) die();
	echo "<FORM NAME= 'Formulario' ACTION ='puntajes.php' method='post' target='puntajes'><b>";
	echo "<input type='hidden' name='categoria' value='".$categoria."'/>";
	echo "<input type='hidden' name='regional' value='".$regional."'/>";

	echo "<label>Seleccione centro</label><br>";
	echo "<select name='centro' onChange ='document.Formulario.submit()'>";
	echo "<option> </option>";
	while($row = mysqli_fetch_array($result)) 
	{ 
		echo "<option value='". $row['centro_id']."'>".$row['centro']."</option>";
	}
	echo "</select>";
	echo "</FORM>";	
	//desconectamos la base de datos
	$close = mysqli_close($conexion) 
	or die("Ha sucedido un error inexperado en la desconexion de la base de datos");


?>