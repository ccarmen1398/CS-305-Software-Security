package com.snhu.sslserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}
//FIXME: Add route to enable check sum return of static data example:  String data = "Hello World Check Sum!";

//SeverController will be recognized as the Controller with request for Mappping

@RestController
@RequestMapping("/api")
class ServerController {

  //This function will take in the String "data" and create a hash value for Checksum
  @GetMapping("/hash")
  public String hash() {
  	//String data declaration
      String data = "Hello World Carmen Mitchum!";
      
      //If Cipher Algorithm is created correctly.... 
      try {
      	
      	//create MessageDigest object 'md' to get instance of algorithm "SHA-256"
      	MessageDigest md = MessageDigest.getInstance("SHA-256");
      	
      	//generate has value of bit type for String 'data'
          byte[] hashBytes = md.digest(data.getBytes());
          
          //cipherAlgo will hold the string of 'data' hash value processed into a hex
          String cipherAlgo = bytesToHex(hashBytes);
          
          //return value of 'data' and 'cipherAlgo'
          return "<p>data: " + data + "<p>Name of Cipher Algorithm Used : " + cipherAlgo;
      }
      //If Cipher Algorithm cannot be created...
      catch (NoSuchAlgorithmException e){
      	//return description that the algorithm could not be created
      	return "Error: No Such Algorithm!";
      }
  }

  //bytesToHex will convert a hash value to hex
  private String bytesToHex(byte[] bytes) {
  	 
  	StringBuilder hexString = new StringBuilder();
  	
  	//loop through every individual byte
      for (byte b : bytes) {
      	
      	//hex will continue adding on characters for every bite hexing with the value '0xff'
          String hex = Integer.toHexString(0xff & b);
          
          //If hex is empty...
          if (hex.length() == 1) {
          	
              hexString.append('0');
              
          }
          //Else if hex is not empty, it'll be added to StringBuilder hexString
          hexString.append(hex);
      }
      
      //returns the hex value of the hash
      return hexString.toString();  
  }
}
