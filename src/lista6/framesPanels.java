package lista6;
/*package lista6;

	import java.awt.BorderLayout;
	import java.awt.EventQueue;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JPanel;
	import javax.swing.border.EmptyBorder;
	import java.awt.BasicStroke;
	import java.awt.Color;
	import java.awt.FontMetrics;
	import java.awt.Graphics;
	import java.awt.Graphics2D;
	import java.awt.Point;
	import java.awt.RenderingHints;
	import java.awt.Stroke;
	import java.util.ArrayList;
	import java.util.List;
	import javax.swing.JPanel;
	import javax.swing.JButton;
	import java.awt.GridLayout;


public class JanelaPrincipal extends JFrame {

	private JFrame contentPane;
	private GridLayout gridLayout1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaPrincipal frame = new JanelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	public JanelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 300, 700, 500);
		contentPane = new JFrame("Lista6");
		contentPane.setSize(500,500);
		//setContentPane(contentPane);
		
		gridLayout1 = new GridLayout(3,1);   //2 POR 3; LACUNAS DE 5
		
		JPanel space_button = new JPanel();
		space_button.setBackground(Color.BLACK);
		space_button.setSize(250,250);
		space_button.setBounds(250,250,120,35);
		space_button.setLayout(gridLayout1);
		contentPane.add(space_button);
		
		JButton Caso1 = new JButton("caso 1");
		Caso1.setBounds(250,250,120,35);
		space_button.add(Caso1);
		
	}

}
*/

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class framesPanels {

	public static void main(String[] args) {
		
		int w = 1200;
		int h = 600;
		
		JFrame janela = new JFrame("");
		Painel painelPontos = new Painel(w, h);
		
		janela.getContentPane().setLayout(null);
		
		janela.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		painelPontos.setBounds(50,50,w,h);
		painelPontos.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.BLACK));
		
		janela.setSize(1900,1000);
		JButton A = new JButton("A");
		A.setBounds(500,100,500,30);
		JButton B = new JButton("B");
		B.setBounds(500,200,500,30);
		JButton C = new JButton("C");
		C.setBounds(500,300,500,30);
		
		JLabel Titulo = new JLabel("Titulo");
		Titulo.setBounds(730,30,380,30);
		
		JPanel painelPrincipal = new JPanel();
		painelPrincipal.setLayout(null);
		painelPrincipal.add(Titulo);
		painelPrincipal.add(A);
		painelPrincipal.add(B);
		painelPrincipal.add(C);
		janela.add(painelPrincipal);
		
		painelPrincipal.setBounds(100,200,1600,800);
		
		janela.add(painelPrincipal);
		janela.setSize(1800,1000);
		janela.setVisible(true);
		
		A.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				painelPrincipal.setVisible(false);
				Strassen matriz = new Strassen();
				matriz.start(janela);
			}
		});
		
		B.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				painelPrincipal.setVisible(false);
				painelPontos.setVisible(true);
				painelPontos.setBounds(100,200,1600,550);
				painelPontos.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.BLACK));
				janela.add(painelPontos);
			}
		});
	}

}