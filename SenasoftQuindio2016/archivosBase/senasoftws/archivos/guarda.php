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
$jornada1=$_REQUEST['jornada1'];
$jornada2=$_REQUEST['jornada2'];
$jornada3=$_REQUEST['jornada3'];
$total=$jornada3+$jornada2+$jornada1;
//echo $codigo;
	$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost) 
	or die("Ha sucedido un error inexperado en la conexion de la base de datos");
	
	//generamos la consulta
$sql="UPDATE resultados SET jornada1=".$jornada1.",jornada2=".$jornada2.", jornada3=".$jornada3.", total=".$total." WHERE  categoria_id=".$categoria." AND regional_id=".$regional." AND centro_id=".$centro;
//echo "<BR>".$sql;
	mysqli_set_charset($conexion, "utf8"); //formato de datos utf8

	if(!$result = mysqli_query($conexion, $sql)) die();
	//desconectamos la base de datos
	$close = mysqli_close($conexion);
	echo "Datos guardados correctamente" ;
?>