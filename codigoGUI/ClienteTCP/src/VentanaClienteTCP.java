import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class VentanaClienteTCP extends JFrame {
	public static boolean existeServidor;
	
	private JPanel contentPane;
	private JTextField cajaIp;
	private JTextField cajaPuerto;
	private JTextField cajaIpSol;
	private  JTextField cajaMascaraSol;
	public static JTextField cajaBroadcastResp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaClienteTCP frame = new VentanaClienteTCP();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaClienteTCP() {
		setTitle("CLIENTE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 314, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelDatos = new JPanel();
		panelDatos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelDatos.setBounds(10, 11, 278, 240);
		contentPane.add(panelDatos);
		panelDatos.setLayout(null);
		
		JLabel lblIp = new JLabel("IP: ");
		lblIp.setBounds(10, 22, 25, 14);
		panelDatos.add(lblIp);
		
		cajaIp = new JTextField();
		cajaIp.setBounds(32, 19, 102, 20);
		panelDatos.add(cajaIp);
		cajaIp.setColumns(10);
		
		cajaPuerto = new JTextField();
		cajaPuerto.setColumns(10);
		cajaPuerto.setBounds(197, 19, 71, 20);
		panelDatos.add(cajaPuerto);
		
		JLabel lblPuerto = new JLabel("Puerto:");
		lblPuerto.setBounds(144, 22, 54, 14);
		panelDatos.add(lblPuerto);
		
		JPanel panelSolicitud = new JPanel();
		panelSolicitud.setBounds(10, 47, 258, 83);
		panelDatos.add(panelSolicitud);
		panelSolicitud.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Solicitud del Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelSolicitud.setLayout(null);
		
		JLabel lblDireccionIp = new JLabel("Direccion IP:");
		lblDireccionIp.setBounds(17, 23, 86, 14);
		panelSolicitud.add(lblDireccionIp);
		
		cajaIpSol = new JTextField();
		cajaIpSol.setBounds(92, 20, 156, 20);
		panelSolicitud.add(cajaIpSol);
		cajaIpSol.setColumns(10);
		
		JLabel lblMascara = new JLabel("Mascara:");
		lblMascara.setBounds(17, 48, 75, 14);
		panelSolicitud.add(lblMascara);
		
		cajaMascaraSol = new JTextField();
		cajaMascaraSol.setColumns(10);
		cajaMascaraSol.setBounds(92, 48, 156, 20);
		panelSolicitud.add(cajaMascaraSol);
		
		JPanel panelRespuesta = new JPanel();
		panelRespuesta.setBounds(10, 181, 258, 48);
		panelDatos.add(panelRespuesta);
		panelRespuesta.setLayout(null);
		panelRespuesta.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Respuesta del Servidor", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JLabel lblBroadcast = new JLabel("Broadcast:");
		lblBroadcast.setBounds(21, 23, 76, 14);
		panelRespuesta.add(lblBroadcast);
		
		cajaBroadcastResp = new JTextField();
		cajaBroadcastResp.setForeground(Color.RED);
		cajaBroadcastResp.setEditable(false);
		cajaBroadcastResp.setColumns(10);
		cajaBroadcastResp.setBounds(96, 20, 152, 20);
		panelRespuesta.add(cajaBroadcastResp);
		
		JButton botonConectar = new JButton("CONECTAR Y REALIZAR PETICION");
		botonConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//datos del cliente
				String ip = cajaIp.getText();
				String puerto = cajaPuerto.getText();
				//datos de la solicitud
				String ipSol = cajaIpSol.getText();
				String mascaraSol = cajaMascaraSol.getText();
				if(!ip.equals("") && !puerto.equals("") && !ipSol.equals("") && !mascaraSol.equals("")){
					int p = Integer.parseInt(puerto);
					ClienteTCP cliente = new ClienteTCP(ip,p);
					String mensajeSolicitud = ipSol + " " + mascaraSol;
					cliente.mensajeSolicitud = mensajeSolicitud;
					cliente.iniciar();
					if(!existeServidor){
						JOptionPane.showMessageDialog(null,"No existe algun servidor que acepte esta solicitud\nverifique ip y puerto");
					}
				}
				else{
					JOptionPane.showMessageDialog(null,"Datos faltantes para la conexion\nComplete las cajas de texto");
				}
				
			
				
				
			}
		});
		botonConectar.setBounds(10, 141, 258, 23);
		panelDatos.add(botonConectar);
	}
}
