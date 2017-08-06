
public class MainClienteTCP{
	public static void main(String[] args) {
		//Le decimos porque puerto e ip queremos conectarnos  con el servidor
		ClienteTCP C = new ClienteTCP("127.0.0.1",9090);
		C.iniciar();
	}

}
