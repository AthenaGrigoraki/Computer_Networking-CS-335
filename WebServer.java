/**
 * Code is taken from Computer Networking: A Top-Down Approach Featuring 
 * the Internet, second edition, copyright 1996-2002 J.F Kurose and K.W. Ross, 
 * All Rights Reserved.
 * csd4109
 * Athina Grigoraki
 **/

import java.io.*;
import java.net.*;
import java.util.*;

class WebServer{
    
    public static void main(String argv[]) throws Exception  {
	
	String requestMessageLine;
	String fileName;
	String random="";
	String check="";	

	
	ServerSocket listenSocket = new ServerSocket(4109);
	System.out.println ("Web server waiting for request on port " + listenSocket);
	Socket connectionSocket = listenSocket.accept();
	
	while(true){

	BufferedReader inFromClient =
            new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
	DataOutputStream outToClient =
            new DataOutputStream(connectionSocket.getOutputStream());
			

	requestMessageLine = inFromClient.readLine();
	System.out.println ("Request: " + requestMessageLine);

  
	
	StringTokenizer tokenizedLine =
			new StringTokenizer(requestMessageLine);
		
			
	random=tokenizedLine.nextToken();
	System.out.println("next token " + random);
	
	if (random.equals("GET")){
	    
	    fileName = tokenizedLine.nextToken();
	    
	    if (fileName.startsWith("/") == true )
		fileName  = fileName.substring(1);

		System.out.println(fileName);
	    
	    File file = new File(fileName);
	    int numOfBytes = (int) file.length();
		
		
	    FileInputStream inFile  = new FileInputStream (fileName);
	    
	    byte[] fileInBytes = new byte[numOfBytes];
	    inFile.read(fileInBytes);
	    
	    outToClient.writeBytes("HTTP/1.1 200 Document Follows\r\n");
	    
		if (fileName.endsWith(".jpg"))
		
		outToClient.writeBytes("Content-Type: image/jpeg\r\n");
	    if (fileName.endsWith(".gif"))
		outToClient.writeBytes("Content-Type: image/gif\r\n");
		if (fileName.endsWith(".html"))
		outToClient.writeBytes("Content-Type: text/html\r\n");
		System.out.println("server sends content type to client");
	    
		outToClient.writeBytes("Content-Length: " + numOfBytes + "\r\n");
		System.out.println("server sends content length to client\n");
	   // outToClient.writeBytes("\r\n");
	    
		outToClient.write(fileInBytes, 0, numOfBytes);
		System.out.println("server sends content of file to client");
		
	

	    
	}

	else if (random.equals("PUT")){
		fileName = tokenizedLine.nextToken();
		
		if (fileName.startsWith("/") == true )
		fileName  = fileName.substring(1);

		System.out.println(fileName);
		File file = new File(fileName);
		int numOfBytes = (int) file.length();

		FileWriter fileWriter = new FileWriter(fileName);
		PrintWriter printWriter = new PrintWriter(fileWriter);

		

		printWriter.close();

		
		outToClient.writeBytes("HTTP/1.1 201 Created\r\n");
		
		outToClient.writeBytes("Content-Location: /" + fileName+ "\r\n");

	}
	
	else {
		System.out.println("Bad Request Message");
	}


}





    }


}

