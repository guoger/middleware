package user;

import java.net.*;
import java.io.*;
import java.util.Random;
import java.lang.Object;

import testasn1.TestQuestion;

import com.turkcelltech.jac.*;
import com.chaosinmotion.asn1.*;

public class Connection extends Thread {
	protected Socket s;
	byte[] inputMessage = new byte[32];
	byte[] outputMessage = new byte[32];
	// outputstream for encoding
	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	BerOutputStream msgOut = new BerOutputStream(outputStream);
	// inputstream for decoding
	ByteArrayInputStream inputStream;
	BerInputStream msgIn;
	// ASN.1 structure TestQuestion foo and bar
	TestQuestion bar = new TestQuestion();
	TestQuestion foo = new TestQuestion();
	
	Connection (Socket s) {
		System.out.println ("\n [SERVER] New client.\n" + s.getInetAddress());
		this.s = s;
	}
	
	public static void printHex(byte[] coded) {
		System.out.println("to byte array in HEX : ");
	    String hexDigits = "0123456789ABCDEF";
	    for (int i=0; i<coded[1]+2; i++) {
	      int c = coded[i];
	      if (c < 0) c += 256;
	      int hex1 = c & 0xF;
	      int hex2 = c >> 4;
	      System.out.print(hexDigits.substring(hex2,hex2+1));
	      System.out.print(hexDigits.substring(hex1,hex1+1) + " ");
	    }
	    System.out.println();
	}
	
	public void run() {
		try {
			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			
			// get message from socket
			int bytesRead = in.read(inputMessage);
			System.out.println("\n [SERVER]\t[RECV]");
			printHex(inputMessage);
			// decode
			inputStream = new ByteArrayInputStream(inputMessage);
			msgIn = new BerInputStream(inputStream);
			bar.decode(msgIn);
			System.out.println(" [SERVER] Decode from message\n");
			System.out.println("code is " + bar.code.getValue());
			System.out.println("word is " + bar.word.getValue());
			// get the number
			int code = (int) bar.code.getValue();
			code++;
			
			// generate random string
			String randStr = rand();
			System.out.println("\n [SERVER] random string is  " + randStr);
			
			// encode
			outputStream.reset();
			foo.code.setValue(code);
			foo.word.setValue(randStr);
			foo.encode(msgOut);
			// send message
			outputMessage = outputStream.toByteArray();
			out.write(outputMessage);
			System.out.println("\n [SERVER]\t[SEND]");
			printHex(outputMessage);
			out.flush();
			
			// get reply
			bytesRead = in.read(inputMessage);
			System.out.println("\n [SERVER]\t[RECV]");
			printHex(inputMessage);
			// decode
			inputStream = new ByteArrayInputStream(inputMessage);
			msgIn = new BerInputStream(inputStream);
			bar.decode(msgIn);
			System.out.println(" [SERVER] Decode from message\n");
			System.out.println("code is " + bar.code.getValue());
			System.out.println("word is " + bar.word.getValue());
			// get the word
			String word = bar.word.getValue();
			word = swapCase(word);
			
			// compare the word and send OK or FAIL
			outputStream.reset();
			if (randStr.equals(word)) {
				foo.word.setValue("OK");
				foo.encode(msgOut);
				// send message
				outputMessage = outputStream.toByteArray();
				out.write(outputMessage);
				System.out.println("\n [SERVER]\t[SEND] OK");
				printHex(outputMessage);
				out.flush();
			} else {
				foo.word.setValue("FAIL");
				foo.encode(msgOut);
				// send message
				outputMessage = outputStream.toByteArray();
				out.write(outputMessage);
				System.out.println("\n [SERVER]\t[SEND] FAIL");
				printHex(outputMessage);
				out.flush();
			}
			
		}
		catch (IOException ex) {
			System.err.println(ex);
		}
		finally {
			try {
				s.close();
			}
			catch (IOException ex) {
				ex.printStackTrace ();
			}
		}
	}
	
	String rand() { 
		char[] chars = "123456abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQR".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
			char ch = chars[random.nextInt(chars.length)];
			sb.append(ch);
		}
		String output = sb.toString();
		return (output); 
	}
	
	public static String swapCase(String str) {
		  int strLen;
		  if (str == null || (strLen = str.length()) == 0) {
			  return str;
		  }
		  StringBuffer buffer = new StringBuffer(strLen);

		  char ch = 0;
		  for (int i = 0; i < strLen; i++) {
			  ch = str.charAt(i);
			  if (Character.isUpperCase(ch)) {
				  ch = Character.toLowerCase(ch);
			  } else if (Character.isTitleCase(ch)) {
				  ch = Character.toLowerCase(ch);
			  } else if (Character.isLowerCase(ch)) {
				  ch = Character.toUpperCase(ch);
			  }
			  buffer.append(ch);
		  }
		  return buffer.toString();
	}
}
