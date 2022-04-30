/*
 * Simple example TCPClient
 *
 * @author csd4109
 * Athina Grigoraki
 */
import java.io.*; 
import java.net.*; 
public class TCPClient { 

	 public static void main(String argv[]) throws Exception 
	 { 

		  String sentence; 
		  String modifiedSentence; 
		  String newSentence="";

		  BufferedReader inFromUser = 
			 new BufferedReader(new InputStreamReader(System.in)); 
while(true){
	
		  Socket clientSocket = new Socket("localhost", 6789); 

		  DataOutputStream outToServer = 
			 new DataOutputStream(clientSocket.getOutputStream()); 
        
		  BufferedReader inFromServer = 
			 new BufferedReader(new
			 InputStreamReader(clientSocket.getInputStream())); 
		

		  sentence = inFromUser.readLine(); 
		try{

		  outToServer.writeBytes(sentence + '\n'); 

		  modifiedSentence = inFromServer.readLine(); 
		  newSentence+= modifiedSentence;
		  

		  clientSocket.close(); 	

		  

}catch(SocketException exception){
	break;
}

}
System.out.println("FROM SERVER: " + newSentence);

                   
	 } 
} 		
		 


