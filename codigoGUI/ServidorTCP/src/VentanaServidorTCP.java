import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.border.TitledBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.xml.ws.handler.MessageContext;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;


public class VentanaServidorTCP extends JFrame {
	//Variables Servidor
	ServidorTCP servidor;
	//Variables Interfaz Grafica
	private JPanel contentPane;
	private JTextField cajaPuerto;
	public static JTextArea areaPeticiones;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaServidorTCP frame = new VentanaServidorTCP();
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
	public VentanaServidorTCP() {
		setTitle("SERVIDOR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 314, 300);
		contentPane = new JPanel();
		contentPane.setToolTipText("Peticiones\r\n");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("Peticiones");
		panel.setBorder(new TitledBorder(null, "Peticiones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 91, 278, 160);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 258, 124);
		panel.add(scrollPane);
		
		areaPeticiones = new JTextArea();
		scrollPane.setViewportView(areaPeticiones);
		areaPeticiones.setEditable(false);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 278, 82);
		contentPane.add(panel_1);
		panel_1.setToolTipText("Datos Servidor");
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos Servidor", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setLayout(null);
		
		JLabel lblPuerto = new JLabel("Puerto:");
		lblPuerto.setBounds(10, 28, 64, 14);
		panel_1.add(lblPuerto);
		
		cajaPuerto = new JTextField();
		cajaPuerto.setBounds(71, 25, 197, 20);
		panel_1.add(cajaPuerto);
		cajaPuerto.setColumns(10);
		//boton IniciarServidor
		JButton botonIniciar = new JButton("INICIAR ");
		botonIniciar.setBounds(10, 48, 122, 23);
		botonIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int puerto = Integer.parseInt(cajaPuerto.getText());
				if(puerto < 0 || puerto > 65535){
					JOptionPane.showMessageDialog(null,"Puerto fuera de rango\nrango: 0 < puerto < 65535");
				}
				else{
				servidor = new ServidorTCP(puerto);
				servidor.start();
				}
			}
		});
		panel_1.add(botonIniciar);
		
		JButton btnDetener = new JButton("DETENER");
		btnDetener.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				servidor.finalizar();
			}
		});
		btnDetener.setBounds(146, 48, 122, 23);
		panel_1.add(btnDetener);
		
		
	}
}
