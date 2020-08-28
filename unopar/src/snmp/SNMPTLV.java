/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitora��o Remota de Redes
 * Unopar - Universidade Norte do Paran�
 * Londrina - PR 2002
 * Gradua��o em Engenharia da Computa��o
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPGetException.java
 * Pacote snmp
 * Fun��o classe utilit�ria que mant�m os componentes de uma Tripla ASN.1
 *       ( tipo, tamanho, valor)
 * Data  14/10/2002
 * Modifica��o 
 ****************************************************************/



package snmp;


class SNMPTLV
{
	byte tag;
	int totalLength;
	byte[] value;
}