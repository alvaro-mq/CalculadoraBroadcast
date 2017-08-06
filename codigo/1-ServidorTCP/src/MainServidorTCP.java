
public class MainServidorTCP {

	public static void main(String[] args) {
		//Solo le decimos porque puerto escuchara peticiones
		ServidorTCP S = new ServidorTCP(9090);
		S.iniciar();
	}

}
