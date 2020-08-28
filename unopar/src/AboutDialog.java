/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitoraçào Remota de Redes
 * Unopar - Universidade Norte do Paraná
 * Londrina - PR 2002
 * Graduaçào em Engenharia da Computação
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo AboutDialog.java
 * Função Creditos !!
 * Data  05/11/2002
 * Modificação 
 *****************************************************************/
 


import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.applet.*;

public class AboutDialog extends JDialog
								implements Runnable
{

	private JLabel aboutLabel1 = new JLabel("Ferramenta Configurável p/ Monitoração Remota de Redes");
	private JLabel aboutLabel2 = new JLabel("Luiz Antonio Carlin");
	private JLabel aboutLabel3 = new JLabel("Novembro / 2002");
	private JLabel aboutLabel4 = new JLabel("");
	private JLabel aboutLabel5 = new JLabel("");
	
	private String inquisitionString = "\"Unopar - Universidade Norte do Paraná\" ";
	private String mpString = "   (Graduação em Engenharia da Computação)";
	
	Thread displayThread;
	
	
	public AboutDialog(JFrame parent)
	{
		super(parent, "Sobre FCMRR...", true /*modal*/);
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setUpDisplay();
		
		this.setLocation(Math.round((parent.getSize().width - this.getSize().width)/2), Math.round((parent.getSize().height - this.getSize().height)/2));
		
		// Cria e inicializa o Display thread
		displayThread = new Thread(this);
		displayThread.start();
		
		this.show();
	
	}
	
	
	
	
	public void hide()
	{
		super.hide();
		
		// interrompe o Thread para poder sair
		displayThread.interrupt();
	}
	
	
		
		
	private void setUpDisplay()
	{
		
		// seta parametros para o gerenciador do layout
		GridBagLayout  theLayout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.NONE;
		c.ipadx = 0;
		c.ipady = 0;
		c.insets = new Insets(2,2,2,2);
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0;
		c.weighty = 0;
		
		JPanel aboutPanel = new JPanel();
		aboutPanel.setLayout(theLayout);
		
		c.gridx = 1;
		c.gridy = 1;
		theLayout.setConstraints(aboutLabel1, c);
		aboutPanel.add(aboutLabel1);
		
		c.gridx = 1;
		c.gridy = 2;
		theLayout.setConstraints(aboutLabel2, c);
		aboutPanel.add(aboutLabel2);
		
		c.gridx = 1;
		c.gridy = 3;
		theLayout.setConstraints(aboutLabel3, c);
		aboutPanel.add(aboutLabel3);
		
		c.gridx = 1;
		c.gridy = 4;
		theLayout.setConstraints(aboutLabel4, c);
		aboutPanel.add(aboutLabel4);
		
		c.gridx = 1;
		c.gridy = 5;
		theLayout.setConstraints(aboutLabel5, c);
		aboutPanel.add(aboutLabel5);
		
		
		this.getContentPane().add(aboutPanel);
		this.pack();
		
		this.setSize(300, 150);
		
	}
	
	
	
	public void run()
	{
		
		
		try
		{
			
			
			// Tenta tocar som se a versào do java for a correta
			
			String version = System.getProperty("java.version");
			
			//System.out.println(version);
			//System.out.println(version.compareTo("1.3"));
			
			if (version.compareTo("1.3") >= 0)
			{
				AudioFilePlayer audioPlayer = new AudioFilePlayer("SNMPInquisitor.jar", "inquisition.wav");
				audioPlayer.playFromJarFile();
			}
			
			// e simultaneamente escreve os caracteres da mensagem
			int numChars = inquisitionString.length();
			
			for (int i = 0; i < numChars; i++)
			{
				aboutLabel4.setText(inquisitionString.substring(0,i));
				Thread.currentThread().sleep(60);
			}
			
			aboutLabel5.setText(mpString);
			
	
		}
		catch(Exception e)
		{
			
			//System.out.println(e);
		}
			
		
		// Sair......
	}
			
			
			
}