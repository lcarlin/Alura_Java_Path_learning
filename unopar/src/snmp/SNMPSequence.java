/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitoraçào Remota de Redes
 * Unopar - Universidade Norte do Paraná
 * Londrina - PR 2002
 * Graduaçào em Engenharia da Computação
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPSequence.java
 * Pacote snmp
 * Função uma das classes mais importantes do SNMP. representa a sequencia 
 *        de outros tipos de dados SNMP . Virtualmente, todas estruturas compostas
 *        são subclasses do SNMPSeqeunce
 * Data  01/10/2002
 * Modificação 
 *****************************************************************/


package snmp;

import java.util.*;
import java.io.*;

public class SNMPSequence extends SNMPObject
{
	protected Vector sequence;	// Vector of whatever is in sequence
	
	protected byte tag = SNMPBERCodec.SNMPSEQUENCE;
		
	
	/**
	*   Cria uma nova sequencia vazia
	*/
	
	public SNMPSequence()
	{
		sequence = new Vector();
	}
	
	
	
	
	/**
	*   cria uma nova Sequencia SNMP à partir do vetor fornecido pelo SNMPObjecs
	*   @throws SNMPBadValueException se um objeto não-SNMP é fornecido no vetor v
	*/
	
	public SNMPSequence(Vector v)
		throws SNMPBadValueException
	{
		
		Enumeration e = v.elements();
		
		while (e.hasMoreElements())
		{
			if (!(e.nextElement() instanceof SNMPObject))
				throw new SNMPBadValueException("Non-SNMPObject supplied to SNMPSequence.");
		}
		
		
		sequence = v;
	}
	
	
	
	
	/**
	*   costroi uma SNMPMessage de um byte recebido na representaçào ASN.1
	*	@throws SNMPBadValueException indica sequencia de codificçào SNMP invalida fornecida
	*/
	
	protected SNMPSequence(byte[] enc)
		throws SNMPBadValueException
	{
		extractFromBEREncoding(enc);
	}
	
	
	
	
	
	/**
	*   retorna um vetor contendo o SNMPObjects na sequencia
	*/
	
	public Object getValue()
	{
		return sequence;
	}
	
	
	
	
	
	/** 
	*   utilizado para configurar o objeto que conyem o SNMP de um vetor fornecido.
	*   @throws SNMPBadValueException indica que um tipo de onjeto incorreto foi 
	*      fornecido ou que o vetor contém non-SNMPObjects
	*/
	
	public void setValue(Object newSequence)
		throws SNMPBadValueException
	{
		if (newSequence instanceof Vector)
		{
						
			// verifica se todos os objetos no vetor são SNMPObejcts
			
			Enumeration e = ((Vector)newSequence).elements();
			
			while (e.hasMoreElements())
			{
				if (!(e.nextElement() instanceof SNMPObject))
					throw new SNMPBadValueException("Non-SNMPObject supplied to SNMPSequence.");
			}
			
			
			this.sequence = (Vector)newSequence;
		}
		else
			throw new SNMPBadValueException(" Sequence: bad object supplied to set value ");
	}
	
	
	
	/** 
	*  retorna o numero de SNMPObjects contidos na sequencia
	*/
	
	public int size()
	{
		return sequence.size();
	}
	
	
	
	/** 
	*  Adiciona um Objeto SNMP no final da sequencia
	*  @throws SNMPBadValueException é relevanbte somente nas subclasses
	*/
	
	public void addSNMPObject(SNMPObject newObject)
		throws SNMPBadValueException
	{
		sequence.insertElementAt(newObject, sequence.size());
	}
	
	
	
	
	
	/** 
	*   Retorna o objeto SNMP no indice especifico. 
	*/
	
	public SNMPObject getSNMPObjectAt(int index)
	{
		return (SNMPObject)(sequence.elementAt(index));
	}
	
	
	
	
	/** 
	*   Retorna a codificaçào BER para as sequencias
	*/
	
	protected byte[] getBEREncoding()
	{
		ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
						
		// escrita recursiva do conetuido dos vetores
		byte[] data = encodeVector();
				
		// calcula a codificaçào pro tamanho do dado		
		byte[] len = SNMPBERCodec.encodeLength(data.length);
		
		
		// codifica a informaçào T.L.V
		outBytes.write(tag);
		outBytes.write(len, 0, len.length);
		outBytes.write(data, 0, data.length);
		
		return outBytes.toByteArray();
	}
	
	
	
	private byte[] encodeVector()
	{
		ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
		
		int numElements = sequence.size();
		for (int i = 0; i < numElements; ++i)
		{
			byte[] nextBytes = ((SNMPObject)(sequence.elementAt(i))).getBEREncoding();
			outBytes.write(nextBytes, 0, nextBytes.length);
		}
		
		return outBytes.toByteArray();
	}
	
	
	
	protected void extractFromBEREncoding(byte[] enc)
		throws SNMPBadValueException
	{
		Vector newVector = new Vector();
		
		int totalLength = enc.length;
		int position = 0;
		
		while (position < totalLength)
		{
			SNMPTLV nextTLV = SNMPBERCodec.extractNextTLV(enc, position);
			newVector.insertElementAt(SNMPBERCodec.extractEncoding(nextTLV), newVector.size());
			position += nextTLV.totalLength;
		}
		
		sequence = newVector;
		
	}
	
	
	
	
	
	/** 
	*   Retorna uma sequencia de representaçào dos objetos contidos, sepqrados por
	*   espaços fechados em parentesis
	*/
	
	public String toString()
	{
		String valueString = new String("(");
		
		
		for (int i = 0; i < sequence.size(); ++i)
		{
			valueString +=  " " + ((SNMPObject)sequence.elementAt(i)).toString() + " ";
		}
		
		valueString += ")";
		return valueString;
	}
	
	
	
}