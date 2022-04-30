/*
 * Simple example TCPClient
 *
 * @author csd4109
 * Athina Grigoraki
 */

 import java.io.*; 
import java.net.*; 
public class WebClient { 

	 public static void main(String argv[]) throws Exception 
	 { 

        String sentence; 
        String input;
        String type;
        String length;
        String space;
        int numOfBytes=0;
        



        


        BufferedReader inFromUser = 
        new BufferedReader(new InputStreamReader(System.in)); 

        Socket clientSocket = new Socket("localhost", 4109); 
        System.out.println("client:" + clientSocket);
        
        DataOutputStream outToServer = 
			 new DataOutputStream(clientSocket.getOutputStream()); 
		
		  BufferedReader inFromServer = 
			 new BufferedReader(new
             InputStreamReader(clientSocket.getInputStream())); 

             while(true){
             
             sentence = inFromUser.readLine(); 

             outToServer.writeBytes(sentence + '\n'); 

             input = inFromServer.readLine();
             type = inFromServer.readLine();
             length = inFromServer.readLine();
            // space = inFromServer.readLine();

             for (char ch : length.toCharArray()) {
                
                
                if (Character.isDigit(ch)) {
                    
                    
                    numOfBytes= Character.getNumericValue(ch);
                    
                }
            }

            byte[] byteArray=new byte[numOfBytes];
            
            clientSocket.getInputStream().read(byteArray,0,numOfBytes);
            
            String bytes=new String(byteArray);
            


             System.out.println(input);
             System.out.println(type);
             System.out.println(length);
            // System.out.println(space);
             System.out.println(bytes);


        }

     }


}