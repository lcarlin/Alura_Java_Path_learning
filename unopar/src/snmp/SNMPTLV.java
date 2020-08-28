/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitoraçào Remota de Redes
 * Unopar - Universidade Norte do Paraná
 * Londrina - PR 2002
 * Graduaçào em Engenharia da Computação
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPGetException.java
 * Pacote snmp
 * Função classe utilitária que mantém os componentes de uma Tripla ASN.1
 *       ( tipo, tamanho, valor)
 * Data  14/10/2002
 * Modificação 
 ****************************************************************/



package snmp;


class SNMPTLV
{
	byte tag;
	int totalLength;
	byte[] value;
}