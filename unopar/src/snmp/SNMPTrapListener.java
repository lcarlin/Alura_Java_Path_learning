/****************************************************************
 * SNMP Ferramenta Configuravel p/ Monitoraçào Remota de Redes
 * Unopar - Universidade Norte do Paraná
 * Londrina - PR 2002
 * Graduaçào em Engenharia da Computação
 * Aluno Luiz Antonio Carlin
 * Docente Luciano Tadeu Esteves Pansanato
 *****************************************************************
 * Modulo SNMPInteger32.java
 * Pacote snmp
 * Função é uma interface  que deve ser implementada por qualquer classe que 
 *        deseje atuar como listener para as mensagens de TRAP enviadas pelas
 *        entidades remotas SNMP, usando a classe SNMPv1TrapReceiver, que "escuta"
 *        por mensagens de trap e as passa qualquer uma para a subclasse 
 *        SNMPTrapListener que tem regsitrado com ela através de seu
 *        metodo addTrapListener()
 *        
 * Data  13/10/2002
 * Modificação 
 ****************************************************************/



package snmp;


public interface SNMPTrapListener
{
	
	public void processTrap(SNMPTrapPDU trapPDU);
	
}