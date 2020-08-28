
import snmp.*;
import java.util.*;
import java.math.*;
import java.net.*;



public class SampleSNMP
{

	public static void main(String args[]) 
	{

		try
		{

			// create a communications interface to a remote SNMP-capable device;
			// need to provide the remote host's InetAddress and the community
			// name for the device; in addition, need to  supply the version number
			// for the SNMP messages to be sent (the value 0 corresponding to SNMP
			// version 1)
			InetAddress hostAddress = InetAddress.getByName("127.0.0.1");
			String community = "public";
			int version = 0;	// SNMPv1
			
			SNMPv1CommunicationInterface comInterface = new SNMPv1CommunicationInterface(version, hostAddress, community);
			
			
			
			// now send an SNMP GET request to retrieve the value of the SNMP variable
			// corresponding to OID 1.3.6.1.2.1.2.1.0; this is the OID corresponding to
			// the device identifying string, and the type is thus SNMPOctetString
			String itemID = "1.3.6.1.2.1.1.1.0";
			
			System.out.println("Retrieving value corresponding to OID " + itemID);
			
			// the getMIBEntry method of the communications interface returns an SNMPVarBindList
			// object; this is essentially a Vector of SNMP (OID,value) pairs. In this case, the
			// returned Vector has just one pair inside it.
			SNMPVarBindList newVars = comInterface.getMIBEntry(itemID);
			
			// extract the (OID,value) pair from the SNMPVarBindList; the pair is just a two-element
			// SNMPSequence
			SNMPSequence pair = (SNMPSequence)(newVars.getSNMPObjectAt(0));
			
			// extract the object identifier from the pair; it's the first element in the sequence
			SNMPObjectIdentifier snmpOID = (SNMPObjectIdentifier)pair.getSNMPObjectAt(0);
			
			// extract the corresponding value from the pair; it's the second element in the sequence
			SNMPObject snmpValue = pair.getSNMPObjectAt(1);
			
			// print out the String representation of the retrieved value
			System.out.println("Retrieved value: type " + snmpValue.getClass().getName() + ", value " + snmpValue.toString());
			
			// the retrieved value can be obtained from the SNMPObject using the getValue method;
			// the return type of the method is the generic base class Object, and must be cast to 
			// the appropriate actual Java type; in this case, for an SNMPOctetString, the underlying
			// Java type is a byte array[]
			byte[] javaByteArrayValue = (byte[])snmpValue.getValue();
			
			
			
			// now send an SNMP GET request to retrieve the value of the SNMP variable
			// corresponding to OID 1.3.6.1.2.1.1.3.0; this is the OID corresponding to
			// the uptime of the device, and the return type is thus SNMPTimeTicks
			itemID = "1.3.6.1.2.1.1.3.0";
			
			System.out.println("Retrieving value corresponding to OID " + itemID);
			
			// the getMIBEntry method of the communications interface returns an SNMPVarBindList
			// object; this is essentially a Vector of SNMP (OID,value) pairs. In this case, the
			// returned Vector has just one pair inside it.
			newVars = comInterface.getMIBEntry(itemID);
			
			// extract the (OID,value) pair from the SNMPVarBindList; the pair is just a two-element
			// SNMPSequence
			pair = (SNMPSequence)(newVars.getSNMPObjectAt(0));
			
			// extract the object identifier from the pair; it's the first element in the sequence
			snmpOID = (SNMPObjectIdentifier)pair.getSNMPObjectAt(0);
			
			// extract the corresponding value from the pair; it's the second element in the sequence
			snmpValue = pair.getSNMPObjectAt(1);
			
			// print out the String representation of the retrieved value
			System.out.println("Retrieved value: type " + snmpValue.getClass().getName() + ", value " + snmpValue.toString());
			
			// the retrieved value can be obtained from the SNMPObject using the getValue method;
			// the return type of the method is the generic base class Object, and must be cast to 
			// the appropriate actual Java type; in this case, for SNMPTimeTicks, which is a subclass
			// of SNMPInteger, the actual type is BigInteger (which permits arbitrarily large values to 
			// be represented).
			BigInteger javaIntegerValue = (BigInteger)snmpValue.getValue();
			
			
			
			// now send an SNMP SET request to set the value of the SNMP variable
			// corresponding to OID 1.3.6.1.2.1.1.1.0; this is the OID corresponding to
			// the device identifying string, and the type is thus SNMPOctetString;
			// to set a new value, a string is supplied
			itemID = "1.3.6.1.2.1.1.1.0";	
			
			SNMPOctetString newValue = new SNMPOctetString("New device name");
			
			System.out.println("Setting value corresponding to OID " + itemID);
			System.out.println("New value: " + newValue.toString());
			
			// the setMIBEntry method of the communications interface returns the SNMPVarBindList
			// corresponding to the supplied OID and value
			// This call will probably cause an SNMPSetException to be thrown, since the
			// community name "public" is probably not the read/write password of the device 
			newVars = comInterface.setMIBEntry(itemID, newValue);
				
			
				
		}
		catch(Exception e)
		{
			System.out.println("Exception during SNMP operation:  " + e + "\n");
		}
		
	}

}