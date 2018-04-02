<font face="Arial" size="2">
<?PHP

$hostname_localhost ="mysql6.000webhost.com";
$database_localhost ="a7146134_sena";
$username_localhost ="a7146134_henao";
$password_localhost ="chenao123";

//se captura el codigo de la categoria seleccionado
$categoria=$_REQUEST['categoria'];
$regional=$_REQUEST['regional'];
$centro=$_REQUEST['centro'];

	$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost) 
	or die("Ha sucedido un error inexperado en la conexion de la base de datos");
	
	//generamos la consulta
$sql="SELECT * FROM resultados WHERE categoria_id=".$categoria." AND regional_id=".$regional." AND centro_id=".$centro;
	mysqli_set_charset($conexion, "utf8"); //formato de datos utf8

	if(!$result = mysqli_query($conexion, $sql)) die();
	echo "<FORM NAME= 'Formulario' ACTION ='guarda.php' method='post' target='puntajes' autocomplete='off'><b>";
	echo "<input type='hidden' name='categoria' value='".$categoria."'/>";
	echo "<input type='hidden' name='regional' value='".$regional."'/>";
	echo "<input type='hidden' name='centro' value='".$centro."'/>";	
	while($row = mysqli_fetch_array($result)) 
	{ 
		echo "Jornada 1: <input type='text' name='jornada1' maxlength='4' size='4' value='".$row['jornada1']."'/> Jornada 2: <input type='text' name='jornada2' maxlength='4' size='4' value='".$row['jornada2']."'/> Jornada 3: <input type='text' name='jornada3' maxlength='4' size='4' value='".$row['jornada3']."'/>Total: <input type='text' name='jornada3' disabled maxlength='4' size='4' value='".$row['total']."'/>";
	}

	echo "<br><br><input type='submit' value='Guardar'>";
	echo "</FORM>";	
	
	//desconectamos la base de datos
	$close = mysqli_close($conexion) 
	or die("Ha sucedido un error inexperado en la desconexion de la base de datos");
?>