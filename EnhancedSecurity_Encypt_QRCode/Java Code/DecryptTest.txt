//This code is for decryption


package remodule;


import java.security.*;

import javax.crypto.*;

import java.util.Base64;

import javax.crypto.spec.*;

import java.io.*;
import java.awt.*;
import java.awt.event.*;


public class DecryptTest {
    public static void main(String[] arg) throws Exception {
    	
    	AwtControlDemo a=new AwtControlDemo();
    	a.showTextFieldDemo();
    }
    public String decrypt(String args[]) throws Exception{
        String code = args[0];
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec skeySpec = new SecretKeySpec(code.getBytes(), "AES");
        Base64.Decoder decoder = Base64.getDecoder();//byte[] encryptedTextByte = decoder.decode(encryptedText);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        OutputStream out = new FileOutputStream("pssd.txt");   
        String encryptedText = args[1];
        out.write(encryptedText.getBytes());
        File f=new File("psswd.txt");
        InputStream in = new FileInputStream(f);
        byte[] encrypted = new byte[(int)f.length()];
        in.read(encrypted);
        //String encryp =new String(encrypted);
       // byte[] encryptedTextByte = decoder.decode(encryp);
        in.close();
        String encryp =new String(encrypted);
	byte[] encryptedTextByte = decoder.decode(encryp);
        //byte[] encryptedTextByte = decoder.decode(encrypted);
        byte[] original = cipher.doFinal(encryptedTextByte);
        String originalString = new String(original,"UTF-8");
        
        //System.out.println("The result is\n\t"+originalString);
        return originalString;
    }
}
    


     class AwtControlDemo {

       private Frame mainFrame;
       private Label headerLabel;
       private Label statusLabel;
       private Panel controlPanel;
       public Label l;
       DecryptTest t=new DecryptTest();
       //String s1,s2;

       public AwtControlDemo(){
          prepareGUI();
       }

     /*  public static void main(String[] args){
          AwtControlDemo  awtControlDemo = new AwtControlDemo();
          awtControlDemo.showTextFieldDemo();
       }*/

       private void prepareGUI(){
          mainFrame = new Frame("QR CODE PROJECT");
          mainFrame.setSize(600,600);
          mainFrame.setLayout(new GridLayout(4, 1));
          mainFrame.addWindowListener(new WindowAdapter() {
             public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
             }        
          });    
          headerLabel = new Label();
          headerLabel.setAlignment(Label.CENTER);
          statusLabel = new Label();        
          statusLabel.setAlignment(Label.CENTER);
          statusLabel.setSize(350,100);

          controlPanel = new Panel();
          controlPanel.setLayout(new FlowLayout());

          l=new Label("Result will be displayed here",Label.CENTER);
          
          
          mainFrame.add(headerLabel);
          mainFrame.add(controlPanel);
          mainFrame.add(l);
          mainFrame.add(statusLabel);
          mainFrame.setVisible(true);  
       }

       public void showTextFieldDemo(){
          headerLabel.setText("DECRYPTION PROJECT DEMO"); 

          Label  namelabel1= new Label("Key ", Label.RIGHT);
          Label  nameLabel2 = new Label("Message ", Label.CENTER);
          final TextField userText1 = new TextField(16);
          final TextField userText2 = new TextField();
         // userText2.setEchoChar('*');

          Button loginButton = new Button("Generate");
       
          loginButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {     
                String data = "Key is:  " + userText1.getText();
                data += ", Message is:  " + userText2.getText();
                statusLabel.setText(data);
                String a[]={userText1.getText(),userText2.getText()};
                String s;
                if((s=userText1.getText()).length()==16)
                {
                try {
					l.setText(t.decrypt(a));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                }
                else
                {
                	l.setText("Invalid key, 16 bit valid key is required");
                }
             }
          }); 
          //s1=userText1.getText();
          //s2=userText2.getText();

          controlPanel.add(namelabel1);
          controlPanel.add(userText1);
          controlPanel.add(nameLabel2);       
          controlPanel.add(userText2);
          controlPanel.add(loginButton);
          mainFrame.setVisible(true);  
       }
    }
	      
	           
	  
