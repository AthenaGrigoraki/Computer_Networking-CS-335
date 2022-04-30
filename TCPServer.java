/*
 * Simple example TCPClient
 *
 * @author csd4109
 * Athina Grigoraki
 */


import java.io.*;
import java.net.*;


public class TCPServer
{

   public static void main(String argv[]) throws Exception
   {
      
      String clientSentence;
      String capitalizedSentence;

      int port = 6789;

      ServerSocket welcomeSocket = new ServerSocket(port);

      while (true)
      {
      	System.out.println("Server ready on "+port);

         Socket connectionSocket = welcomeSocket.accept();

         BufferedReader inFromClient =
            new BufferedReader(
               new InputStreamReader(connectionSocket.getInputStream()));

         DataOutputStream outToClient =
            new DataOutputStream(connectionSocket.getOutputStream());

         clientSentence = inFromClient.readLine();

	

     capitalizedSentence = clientSentence.toUpperCase() + '\n';

         
	
         if (capitalizedSentence.contains("$")){
            capitalizedSentence=capitalizedSentence.substring(0,capitalizedSentence.indexOf("$")+1);
            outToClient.writeBytes(capitalizedSentence);
            connectionSocket.close();
            break;
         }
         outToClient.writeBytes(capitalizedSentence);
      }


      
   }
}

