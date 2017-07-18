import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class Interface extends JFrame{
	
	private JRadioButton radioButtonDigitarEntrada, radioButtonSelecionarArq;
	private JButton buttonAnalisar, buttonLimpar, buttonSelecionar;
	private JTextArea textArea;
	private JLabel arqSelecionadoLabel;
	private JLabel status;
	
	private StringBuffer entrada = new StringBuffer();
	/**
	 * Construtor da interface, ele que fica responsavel por montar toda a interface do programa.
	 */
	public Interface(){
		super("Analisador Lexico/Sintatico");
		setSize(600, 410);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container containerTop 	= 	new JPanel();
		Container containerCentroE	= 	new JPanel();
		Container containerCentroD	= 	new JPanel();
		Container l1	=	new JPanel();
		Container l2	=	new JPanel();
		Container n 	= 	getContentPane();
		
		l1.setLayout(new GridLayout(1,1,0,0));
		l1.add(containerTop);
		JLabel tituloLabel = new JLabel("TRABALHO DE LFA");
		tituloLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		containerTop.add(tituloLabel);
		
		l2.setLayout(new GridLayout(1, 2, 0, 0));
		l2.add(containerCentroE);
		l2.add(containerCentroD);
		////////////////////////////////////////// LADO ESQUERDO
		radioButtonDigitarEntrada = new JRadioButton("Digitar entrada", false);
		
		Container op1 = new JPanel();
		op1.add(radioButtonDigitarEntrada);
		containerCentroE.add(op1);
		
		textArea = new JTextArea();
		JScrollPane areaDigitar = new JScrollPane(textArea);
		areaDigitar.setPreferredSize(new Dimension(270, 255));
		Container textBOX = new JPanel();
		textBOX.setPreferredSize(new Dimension(270, 260));
		textBOX.add(areaDigitar);
		containerCentroE.add(textBOX);
		
		buttonAnalisar = new JButton("Analisar");
		buttonLimpar = new JButton("Limpar");
		Container botoesE = new JPanel();
		botoesE.add(buttonAnalisar);
		botoesE.add(buttonLimpar);
		containerCentroE.add(botoesE);
		/////////////////////////////////////////////////// LADO DIREITO
		radioButtonSelecionarArq = new JRadioButton("Selecionar Arquivo", false);
		
		Container op2 = new JPanel();
		op2.add(radioButtonSelecionarArq);
		containerCentroD.add(op2);
		
		Container containerSelecionarArq = new JPanel();
		containerSelecionarArq.setPreferredSize(new Dimension(200, 230));
		JLabel selecionarArqLabel = new JLabel("Selecionar: ");
		arqSelecionadoLabel = new JLabel("Nenhum arquivo selecionado.");
		buttonSelecionar = new JButton("SELECIONAR");
		containerSelecionarArq.add(selecionarArqLabel);
		containerSelecionarArq.add(buttonSelecionar);
		containerSelecionarArq.add(arqSelecionadoLabel);
		containerCentroD.add(containerSelecionarArq);
		
		Container containerStatus = new JPanel();
		containerStatus.setLayout(new BorderLayout());
		containerStatus.setPreferredSize(new Dimension(275, 30));
		JLabel statusLabel = new JLabel("Status:        ");
		status = new JLabel("Nenhuma analise realizada.");
		//status.setForeground(Color.GREEN);
		containerStatus.add(BorderLayout.LINE_START, statusLabel);
		containerStatus.add(status);
		containerCentroD.add(containerStatus);
		
		JLabel creditos = new JLabel("GRUPO 5");
		Container containerCreditos = new JPanel();
		containerCreditos.setPreferredSize(new Dimension(275, 40));
		containerCreditos.setLayout(new BorderLayout());
		containerCreditos.add(BorderLayout.LINE_END, creditos);
		containerCentroD.add(containerCreditos);
		//////////////////////////////////////////
		n.add(BorderLayout.NORTH, l1);
		n.add(BorderLayout.CENTER, l2);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		
		/// INICIALIZANDO INTERFACE
		buttonSelecionar.setEnabled(false);
		radioButtonDigitarEntrada.setSelected(true);
		ativarAcoes();
	}
	
	/**
	 * Responsavel por ativar as ações de todos os componentes da interface.
	 */
	private void ativarAcoes(){
		radioButtonDigitarEntrada.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(radioButtonDigitarEntrada.isSelected()){
					buttonSelecionar.setEnabled(false);
					arqSelecionadoLabel.setText("Nenhum arquivo selecionado.");
					radioButtonSelecionarArq.setSelected(false);;
				} else {
					radioButtonDigitarEntrada.setSelected(true);;
				}
			}
		});
		
		radioButtonSelecionarArq.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(radioButtonSelecionarArq.isSelected()){
					buttonSelecionar.setEnabled(true);
					radioButtonDigitarEntrada.setSelected(false);
				} else {
					radioButtonSelecionarArq.setSelected(true);
				}
			}
		});
		
		buttonSelecionar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				escolhe();
			}
		});
		
		buttonAnalisar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				entrada = new StringBuffer(textArea.getText());
				if(entrada.toString().isEmpty())
					JOptionPane.showMessageDialog(null, "Nao há codigo para ser analisado, porfavor digite um codigo.", "Sem codigo", JOptionPane.ERROR_MESSAGE);
				else
					ExecutarAnalisador.analisar(entrada);
			}
		});
		
		buttonLimpar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] options = { "Confirmar", "Cancelar" };
				if(JOptionPane.showOptionDialog(null, "Clique confirmar para continuar", "LIMPAR CODIGO", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]) == 0)
					textArea.setText("");
			}
		});
	}
	
	/**
	 * Responsavel por carregar os arquivos que o usuario deseja e passar para o programa.
	 */
	private void escolhe(){
		   try {
			   JFileChooser chooser = new JFileChooser();
			   chooser.setFileFilter(new FileNameExtensionFilter("c", "txt"));
			   int retorno = chooser.showOpenDialog(null);
		 
			   if (retorno == JFileChooser.APPROVE_OPTION) {
				   FileReader reader = new FileReader(chooser.getSelectedFile());
		       
				   int c;
				   try {
					   this.entrada = new StringBuffer();
					   while((c = reader.read()) != -1) {
						   this.entrada.append((char)c);
					   }
					   reader.close();
					   textArea.setText("");
					   textArea.setText(this.entrada.toString());
					   arqSelecionadoLabel.setText("Arquivo carregado com sucesso.");
				   } catch (IOException e) {
					   e.printStackTrace();
				   }
			   }
		   } catch (FileNotFoundException e){
			   e.printStackTrace();
		   }
	}
	
	/**
	 * Responsavel por alterar o status apresentado para o usuário.
	 * @param str Qual será a mensagem que sera apresentado na interface.
	 * @param color Qual será a cor da mensagem que sera apresentada na interface.
	 */
	public void setStatus(String str, Color color){
		this.status.setText(str);
		this.status.setForeground(color);
	}
	
	/**
	 * Responsavel por emitir os alertas de erro do programa.
	 * @param titulo Qual é o titulo apresentado na janela do alerta.
	 * @param str Qual é a mensagem apresentada na janela do alerta.
	 */
	public void emitirAlerta(String titulo, String str){
		JOptionPane.showMessageDialog(null, str, titulo, JOptionPane.ERROR_MESSAGE);
	}
}