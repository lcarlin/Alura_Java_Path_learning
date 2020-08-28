/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitoraçào Remota de Redes
 * Unopar - Universidade Norte do Paraná
 * Londrina - PR 2002
 * Graduaçào em Engenharia da Computação
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPInquisitor.java
 * Função Aplicaçào principal
 * Data  11/10/2002
 * Modificação 
 *****************************************************************/



import java.util.*;
import java.net.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import java.awt.event.*;
import java.io.*;
import java.math.*;
import snmp.*;




public class SNMPInquisitor extends JFrame
							implements ActionListener, Runnable
{
	
	JButton getDataButton, getTreewalkDataButton, setValueButton;
	JButton clearButton;
	JTextArea messagesArea;
	JScrollPane messagesScroll;
	JTextField hostIDField, communityField, OIDField, valueField;
	JLabel authorLabel, hostIDLabel, communityLabel, OIDLabel, valueLabel;
	JComboBox valueTypeBox;
	
	MenuBar theMenubar;
	Menu fileMenu;
	MenuItem aboutItem, quitItem;
	
	Thread treewalkThread;
	
	SNMPv1CommunicationInterface comInterface;
	String community;
	InetAddress hostAddress;
	int version;
	
	
	// WindowCloseAdapter para pegar as janelas que fecham
	private class WindowCloseAdapter extends WindowAdapter
	{ 
		public void windowClosing(WindowEvent e)
		{
		    System.exit(0);
		}
	};
			
	
	public SNMPInquisitor() 
	{
		treewalkThread = new Thread(this);
		
		setUpDisplay();
		
	}
	
	
	
	private void setUpDisplay()
	{
		
		
		this.setTitle("FCMRR - Ferramenta Configurável p/ Monitoração Remota de Redes");
			
		this.getRootPane().setBorder(new BevelBorder(BevelBorder.RAISED));
		
		
		// diminui o tamanho das fontes para compactaçào
		UIManager manager = new UIManager();
		FontUIResource appFont = new FontUIResource("SansSerif", Font.PLAIN, 10);
		UIDefaults defaults = manager.getLookAndFeelDefaults();
		Enumeration keys = defaults.keys();
		
		while (keys.hasMoreElements())
		{
			String nextKey = (String)(keys.nextElement());
			if ((nextKey.indexOf("font") > -1) || (nextKey.indexOf("Font") > -1))
			{
				manager.put(nextKey, appFont);
			}
		}
		
		
		// adiciona WindowCloseAdapter para verificar janelas que fecham
		addWindowListener(new WindowCloseAdapter());

		
		theMenubar = new MenuBar();
		this.setMenuBar(theMenubar);
		fileMenu = new Menu("Arquivo");
		
		aboutItem = new MenuItem("Sobre...");
		aboutItem.setActionCommand("about");
		aboutItem.addActionListener(this);
		fileMenu.add(aboutItem);
		
		fileMenu.addSeparator();
		
		quitItem = new MenuItem("Sair");
		quitItem.setActionCommand("quit");
		quitItem.addActionListener(this);
		fileMenu.add(quitItem);
		
		theMenubar.add(fileMenu);
		
		
		hostIDLabel = new JLabel("Endereço do Dispositivo:");
		hostIDField = new JTextField(20);
		hostIDField.setText("127.0.0.1");
		hostIDField.setEditable(true);
		
		OIDLabel = new JLabel("OID:");
		OIDField = new JTextField(20);
		OIDField.setText("1.3.6.1.2.1.1.1.0");
		OIDField.setEditable(true);
		
		valueLabel = new JLabel("Valor (para Set):");
		valueField = new JTextField(20);
		valueField.setEditable(true);
		
		communityLabel = new JLabel("Comunidade:");
		communityField = new JTextField(20);
		communityField.setText("public");
		communityField.setEditable(true);
		
		authorLabel = new JLabel(" Versão 0.1        Luiz Antonio Carlin, Novembro 2002 ");
		authorLabel.setFont(new Font("SansSerif", Font.BOLD, 8));
			
		
		getDataButton = new JButton("Extrair valor OID");
		getDataButton.setActionCommand("get data");
		getDataButton.addActionListener(this);
		
		setValueButton = new JButton("Configurar valor OID");
		setValueButton.setActionCommand("set value");
		setValueButton.addActionListener(this);
		
		getTreewalkDataButton = new JButton("Extrair todos valores OID");
		getTreewalkDataButton.setActionCommand("get treewalk data");
		getTreewalkDataButton.addActionListener(this);
		
		clearButton = new JButton("Limpar respostas");
		clearButton.setActionCommand("clear messages");
		clearButton.addActionListener(this);
		
		messagesArea = new JTextArea(10,60);
		messagesScroll = new JScrollPane(messagesArea);
		
		valueTypeBox = new JComboBox();
		valueTypeBox.addItem("SNMPInteger");
		valueTypeBox.addItem("SNMPCounter32");
		valueTypeBox.addItem("SNMPCounter64");
		valueTypeBox.addItem("SNMPGauge32");
		valueTypeBox.addItem("SNMPOctetString");
		valueTypeBox.addItem("SNMPIPAddress");
		valueTypeBox.addItem("SNMPNSAPAddress");
		valueTypeBox.addItem("SNMPObjectIdentifier");
		valueTypeBox.addItem("SNMPTimeTicks");
		valueTypeBox.addItem("SNMPUInteger32");
		 

        
		// now set up display
		
		// set params for layout manager
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
		
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(theLayout);
		
		c.gridx = 1;
		c.gridy = 1;
		theLayout.setConstraints(getDataButton, c);
		buttonPanel.add(getDataButton);
		
		c.gridx = 2;
		c.gridy = 1;
		theLayout.setConstraints(getTreewalkDataButton, c);
		buttonPanel.add(getTreewalkDataButton);
		
		c.gridx = 3;
		c.gridy = 1;
		theLayout.setConstraints(setValueButton, c);
		buttonPanel.add(setValueButton);
		
		
		JPanel hostPanel = new JPanel();
		hostPanel.setLayout(theLayout);
		
		c.gridx = 1;
		c.gridy = 1;
		theLayout.setConstraints(hostIDLabel, c);
		hostPanel.add(hostIDLabel);
		
		c.gridx = 2;
		c.gridy = 1;
		theLayout.setConstraints(hostIDField, c);
		hostPanel.add(hostIDField);
		
		c.gridx = 1;
		c.gridy = 2;
		theLayout.setConstraints(communityLabel, c);
		hostPanel.add(communityLabel);
		
		c.gridx = 2;
		c.gridy = 2;
		theLayout.setConstraints(communityField, c);
		hostPanel.add(communityField);
		
		
		
		JPanel oidPanel = new JPanel();
		oidPanel.setLayout(theLayout);
		
		c.gridx = 1;
		c.gridy = 1;
		theLayout.setConstraints(OIDLabel, c);
		oidPanel.add(OIDLabel);
		
		c.gridx = 2;
		c.gridy = 1;
		theLayout.setConstraints(OIDField, c);
		oidPanel.add(OIDField);
		
		c.gridx = 1;
		c.gridy = 2;
		theLayout.setConstraints(valueLabel, c);
		oidPanel.add(valueLabel);
		
		c.gridx = 2;
		c.gridy = 2;
		theLayout.setConstraints(valueField, c);
		oidPanel.add(valueField);
		
		c.gridx = 3;
		c.gridy = 2;
		theLayout.setConstraints(valueTypeBox, c);
		oidPanel.add(valueTypeBox);
		
		
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.CENTER;
		
		
		
		JPanel messagesPanel = new JPanel();
		messagesPanel.setLayout(theLayout);
		
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.WEST;
		JLabel messagesLabel = new JLabel(" Respostas:");
		theLayout.setConstraints(messagesLabel, c);
		messagesPanel.add(messagesLabel);
		
		c.gridx = 2;
		c.gridy = 1;
		c.anchor = GridBagConstraints.EAST;
		theLayout.setConstraints(clearButton, c);
		messagesPanel.add(clearButton);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		c.weightx = .5;
		c.weighty = .5;
		c.anchor = GridBagConstraints.CENTER;
		theLayout.setConstraints(messagesScroll, c);
		messagesPanel.add(messagesScroll);
		
		
		c.gridwidth = 1;
		c.weightx = 0;
		c.weighty = 0;
		
		
		
		this.getContentPane().setLayout(theLayout);
		
		
		c.gridx = 1;
		c.gridy = 1;
		theLayout.setConstraints(hostPanel, c);
		this.getContentPane().add(hostPanel);
		
		c.gridx = 1;
		c.gridy = 2;
		theLayout.setConstraints(oidPanel, c);
		this.getContentPane().add(oidPanel);
		
		c.gridx = 1;
		c.gridy = 3;
		theLayout.setConstraints(buttonPanel, c);
		this.getContentPane().add(buttonPanel);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 4;
		c.weightx = .5;
		c.weighty = .5;
		theLayout.setConstraints(messagesPanel, c);
		this.getContentPane().add(messagesPanel);
		
		c.fill = GridBagConstraints.NONE;
		c.gridx = 1;
		c.gridy = 5;
		c.weightx = 0;
		c.weighty = 0;
		theLayout.setConstraints(authorLabel, c);
		this.getContentPane().add(authorLabel);
		
		
	}
	
	
	
	
	
	public void actionPerformed(ActionEvent theEvent)
	
	// responde ao botão pressionado, seleçào domenu
	{
		String command = theEvent.getActionCommand();
		
	
		if (command == "quit")
		{
			System.exit(0);
		}
		
		
		
		if (command == "clear messages")
		{
			messagesArea.setText("");
		}
		
		
		
		if (command == "about")
		{
			AboutDialog aboutDialog = new AboutDialog(this);
		}
		
		
		
		if (command == "get data")
		{
			try
			{
			
				String community = communityField.getText();
				int version = 0;	// SNMPv1
				InetAddress hostAddress = InetAddress.getByName(hostIDField.getText());
				SNMPv1CommunicationInterface comInterface = new SNMPv1CommunicationInterface(version, hostAddress, community);
				
				StringTokenizer st = new StringTokenizer(OIDField.getText(), " ,;");
				
				while (st.hasMoreTokens())
				{
					String itemID = st.nextToken();	
					SNMPVarBindList newVars = comInterface.getMIBEntry(itemID);
					SNMPSequence pair = (SNMPSequence)(newVars.getSNMPObjectAt(0));
					SNMPObjectIdentifier snmpOID = (SNMPObjectIdentifier)pair.getSNMPObjectAt(0);
					SNMPObject snmpValue = pair.getSNMPObjectAt(1);
					String typeString = snmpValue.getClass().getName();
					
					if (typeString.equals("snmp.SNMPOctetString"))
					{
						String snmpString = snmpValue.toString();
						
						
						// Trunca ao primeiro caracter nulo
						int nullLocation = snmpString.indexOf('\0');
						if (nullLocation >= 0)
							snmpString = snmpString.substring(0,nullLocation);
						
						messagesArea.append("OID: " + snmpOID + "  tipo: " + typeString + "  valor: " + snmpString);
						messagesArea.append("  (hex: " + ((SNMPOctetString)snmpValue).toHexString() + ")\n");
					}
					else
					{
						messagesArea.append("OID: " + snmpOID + "  type: " + typeString + "  value: " + snmpValue);
						messagesArea.append("\n");
					}
				}	
			}
			catch(InterruptedIOException e)
			{
				messagesArea.append("Interrompido durante a extração:  " + e + "\n");
			}
			catch(Exception e)
			{
				messagesArea.append("Excessão durante a extração:  " + e + "\n");
			}
					
		}
		
		
		
		
		if (command == "set value")
		{
			try
			{
			
				String community = communityField.getText();
				int version = 0;	// SNMPv1
				InetAddress hostAddress = InetAddress.getByName(hostIDField.getText());
				SNMPv1CommunicationInterface comInterface = new SNMPv1CommunicationInterface(version, hostAddress, community);
			
				
				String itemID = OIDField.getText();
				String valueString = valueField.getText();
				String valueTypeString = (String)valueTypeBox.getSelectedItem();
				valueTypeString = "snmp." + valueTypeString;
				
				SNMPObject itemValue;
				Class valueClass = Class.forName(valueTypeString);
				itemValue = (SNMPObject)valueClass.newInstance();
				itemValue.setValue(valueString);
					
				SNMPVarBindList newVars = comInterface.setMIBEntry(itemID, itemValue);
				
				SNMPSequence pair = (SNMPSequence)(newVars.getSNMPObjectAt(0));
			
				SNMPObjectIdentifier snmpOID = (SNMPObjectIdentifier)pair.getSNMPObjectAt(0);
				
				SNMPObject snmpValue = pair.getSNMPObjectAt(1);
				
				String typeString = snmpValue.getClass().getName();
				
				messagesArea.append("OID: " + snmpOID + "  tipo: " + typeString + "  valor: " + snmpValue);
				
				if (typeString.equals("snmp.SNMPOctetString"))
					messagesArea.append("  (hex: " + ((SNMPOctetString)snmpValue).toHexString() + ")\n");
				else
					messagesArea.append("\n");
			
			}
			catch(InterruptedIOException e)
			{
				messagesArea.append("Interrompido durante a extração:  " + e + "\n");
			}
			catch(Exception e)
			{
				messagesArea.append("Excessão durante a extração:  " + e + "\n");
			}
					
		}
		

		
		if (command == "get treewalk data")
		{
			if (!treewalkThread.isAlive())
			{
				treewalkThread = new Thread(this);
				treewalkThread.start();
				getTreewalkDataButton.setText("Parar com extração de OID");
			}
			else
			{
				treewalkThread.interrupt();
			}
		}
		
		
	
		
	}
	
	
	
	
	
	
	public void run() 
	{
		
		try
		{
		
			String community = communityField.getText();
			int version = 0;	// SNMPv1
			InetAddress hostAddress = InetAddress.getByName(hostIDField.getText());
			SNMPv1CommunicationInterface comInterface = new SNMPv1CommunicationInterface(version, hostAddress, community);
		
			
			//String itemID = "1.3.6.1.2.1.1.1.0";	// start with device name
			String itemID = "";			
			String retrievedID = "1.3.6.1.2.1";		// start point
				
				
			while (!Thread.interrupted() && !retrievedID.equals(itemID))
			{
				itemID = retrievedID;
				
				SNMPVarBindList newVars = comInterface.getNextMIBEntry(itemID);
				
				SNMPSequence pair = (SNMPSequence)(newVars.getSNMPObjectAt(0));
				SNMPObjectIdentifier snmpOID = (SNMPObjectIdentifier)pair.getSNMPObjectAt(0);
				SNMPObject snmpValue = pair.getSNMPObjectAt(1);
				retrievedID = snmpOID.toString();
				String typeString = snmpValue.getClass().getName();
				
				if (typeString.equals("snmp.SNMPOctetString"))
				{
					String snmpString = snmpValue.toString();
					
					// truncate at first null character
					int nullLocation = snmpString.indexOf('\0');
					if (nullLocation >= 0)
						snmpString = snmpString.substring(0,nullLocation);
					
					messagesArea.append("OID: " + snmpOID + "  tipo: " + typeString + "  valor: " + snmpString);
					messagesArea.append("  (hex: " + ((SNMPOctetString)snmpValue).toHexString() + ")\n");
				}
				else
				{
					messagesArea.append("OID: " + snmpOID + "  tipo: " + typeString + "  valor: " + snmpValue);
					messagesArea.append("\n");
				}
			}
			
			
		}
		catch(InterruptedIOException e)
		{
			messagesArea.append("Interrompido durante a extração:  " + e + "\n");
		}
		catch(Exception e)
		{
			messagesArea.append("Excessão durante a extração:  " + e + "\n");
		}
		catch(Error err)
		{
			messagesArea.append("Erro durante a extração:  " + err + "\n");
		}
		
		getTreewalkDataButton.setText("Extrair todos valores OID");
		
	}
	
	
				
	
	private String hexByte(byte b)
	{
		int pos = b;
		if (pos < 0)
			pos += 256;
		String returnString = new String();
		returnString += Integer.toHexString(pos/16);
		returnString += Integer.toHexString(pos%16);
		return returnString;
	}
	
	
	
	
	
	
	
	
	
	public static void main(String args[]) 
	{
		try
		{
			SNMPInquisitor theApp = new SNMPInquisitor();
			theApp.pack();
			theApp.setSize(500,400);
			theApp.show();
		}
		catch (Exception e)
		{}
	}
	

}