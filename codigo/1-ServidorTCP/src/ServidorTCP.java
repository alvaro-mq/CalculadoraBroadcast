import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServidorTCP {
	ServerSocket sServidor;
	Socket sCliente;
	int puerto;
	PrintStream salida;
	Scanner entrada;
	String mensajeSolicitud = "";
	String mensajeRespuesta = "";
	
	public ServidorTCP(int p){
		puerto = p;
	}
	public void iniciar(){
		try {
			sServidor = new ServerSocket(puerto);
			System.out.println("- SERVIDOR TCP INICIADO -");
			System.out.println("- Esperando Cliente -");
			while(true){
				//creamos un socket intermediario
				sCliente = sServidor.accept();
				//flujos de datos
				entrada = new Scanner(sCliente.getInputStream());
				salida = new PrintStream(sCliente.getOutputStream());
				//recibimos solicitudes
				mensajeSolicitud = entrada.nextLine();
				//respondemos solicitudes
				System.out.println("Solicitud del Cliente :"+mensajeSolicitud);
				String resultado = "";
				if(mensajeSolicitud != null){
					String descompone[] = mensajeSolicitud.split(" ");
					String ip = descompone[0];
					String mascara = descompone[1];
					String red = calculaRed(ip,mascara);
					resultado = calculaBroadcast(red, mascara);
				}
				mensajeRespuesta = "El area es : "+ resultado;
				salida.println(mensajeRespuesta);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			finalizar();
		}
		finally{
			finalizar();
		}
	}
	public void finalizar(){
		try {
			salida.close();
			entrada.close();
			sServidor.close();
			sCliente.close();
			System.out.println("Conexion Finalizada...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String completa(String num){
		String nuevo = num;
		for(int i = 0;i < 8 - num.length();i++)
			nuevo = "0"+nuevo;
		return nuevo;
	}
	public static String calculaRed(String ip,String mascara){
		ip = ip.replace('.','-'); 
		String partes[] = ip.split("-");
		mascara = mascara.replace('.','-'); 
		String partes1[] = mascara.split("-");
		int res[] = new int[4];
		for(int i = 0 ;i < partes.length; i++){
			 res[i] = Integer.parseInt(partes[i]) & Integer.parseInt(partes1[i]);
		}
		return String.valueOf(res[0])+"."+String.valueOf(res[1])+"."+String.valueOf(res[2])+"."+String.valueOf(res[3]);
	}
	public String calculaBroadcast(String red,String mascara){
		red = red.replace('.','-'); 
		String partes[] = red.split("-");
		String enBinario = "";
		//convertimos en binario
		for(int i = 0 ;i < partes.length; i++)
			 enBinario = enBinario + completa(Integer.toBinaryString(Integer.parseInt(partes[i])));
		//aniadir la cantidad de ceros que tiene la mascara
		enBinario = enBinario.substring(0,32-cuentaCeros(mascara));
		for(int i = 0 ;i <cuentaCeros(mascara);i++)
			enBinario = enBinario + "1";
		//Convertir a direccion IP
		int a = Integer.parseInt(enBinario.substring(0,8),2);
		int b = Integer.parseInt(enBinario.substring(8,16),2);
		int c = Integer.parseInt(enBinario.substring(16,24),2);
		int d = Integer.parseInt(enBinario.substring(24,32),2);
		return a+"."+b+"."+c+"."+d;
	}
	public  int cuentaCeros(String mascara){
		int cont = 0;
		mascara = mascara.replace('.', '-');
		String[] partes = mascara.split("-");
		for(int i= 0;i < partes.length;i++)
			cont = cont +Integer.bitCount(Integer.parseInt(partes[i]));
		return 32-cont;
	}
}
