/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitora��o Remota de Redes
 * Unopar - Universidade Norte do Paran�
 * Londrina - PR 2002
 * Gradua��o em Engenharia da Computa��o
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPInteger32.java
 * Pacote snmp
 * Fun��o � uma interface  que deve ser implementada por qualquer classe que 
 *        deseje atuar como listener para as mensagens de TRAP enviadas pelas
 *        entidades remotas SNMP, usando a classe SNMPv1TrapReceiver, que "escuta"
 *        por mensagens de trap e as passa qualquer uma para a subclasse 
 *        SNMPTrapListener que tem regsitrado com ela atrav�s de seu
 *        metodo addTrapListener()
 *        
 * Data  13/10/2002
 * Modifica��o 
 ****************************************************************/



package snmp;


public interface SNMPTrapListener
{
	
	public void processTrap(SNMPTrapPDU trapPDU);
	
}