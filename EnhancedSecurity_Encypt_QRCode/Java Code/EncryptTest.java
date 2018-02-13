//This code is for encryption and QRCode Generation


package module1;
//import module2.QRCod;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import javax.crypto.*;

import java.util.Base64;

import javax.crypto.spec.*;

import java.io.*;
import java.awt.*; 
import java.awt.event.*;

import javax.swing.*;

 


public class EncryptTest 
  {
    String code;
   
    public static void main(String[] args) throws IOException 
    {
    //String ab[]=new String[2];
   
    //EncryptTest tester = new EncryptTest();
    AwtControlDemo b=new AwtControlDemo();
    b.showTextFieldDemo();
    //String a[]=b.key();
    //tester.start(a);
    
    //new Frame3();
    
    //new Frame1();
    }
    public void start (String a[]) 
    {
        code = a[0];
        a[0] = null;
        for (String lines : a) 
          {
            if (lines != null) 
               {
                if (lines == a[1])
                  {
                    createFile(lines);
                  }
                else 
                  {
                    addToFile(lines);
                  }
               }
         }
    }
   
    public void createFile (String toEncode) 
    {
     try{
       /* SecretKeySpec skeySpec = new SecretKeySpec(code.getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(toEncode.getBytes("UTF-8"));
        OutputStream out = new FileOutputStream("psswd");
        out.write(encrypted);
        out.close();*/
        SecretKeySpec skeySpec = new SecretKeySpec(code.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(toEncode.getBytes()); 
        Base64.Encoder encoder = Base64.getEncoder();
        OutputStream out = new FileOutputStream("psswd.txt");   
        String encryptedText = encoder.encodeToString(encrypted);
        out.write(encryptedText.getBytes());
        out.close();
        }
     catch (Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }
    }
    public void addToFile(String toEncode) 
    {
    try
     {
       /* SecretKeySpec skeySpec = new SecretKeySpec(code.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(toEncode.getBytes());
        byte[] spacer = cipher.doFinal(System.getProperty("line.separator").getBytes());
            OutputStream out = new FileOutputStream("psswd", true);
            out.write(spacer);
            out.write(encrypted);
            out.close();*/
       
        SecretKeySpec skeySpec = new SecretKeySpec(code.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        Base64.Encoder encoder = Base64.getEncoder();
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(toEncode.getBytes());
        String encrypt = encoder.encodeToString(encrypted);
        //Base64.Encoder encoder = Base64.getEncoder();
        byte[] spacer = cipher.doFinal(System.getProperty("line.separator").getBytes());
        String encryptText = encoder.encodeToString(spacer);
        OutputStream out = new FileOutputStream("psswd", true);
            out.write(encryptText.getBytes());
            out.write(encrypt.getBytes());
            out.close();
     }
    catch (Exception e)
     {
            System.err.println("Error: " + e.getMessage());
     }
   }
}

 class QRCod {
	static String ttemp;
	/*public QRCod(String a)
	{
	    ttemp=a;
	}*/
	/*public static void main(String[] args) throws IOException {
		QRCod q=new QRCod();
		q.demo();
	}*/
	public void demo() throws IOException
	{
        BufferedReader br=new BufferedReader(new FileReader("M:\\eclipse prog\\module1\\psswd.txt"));
        ttemp=br.readLine();
		ByteArrayOutputStream out = QRCode.from(ttemp).to(ImageType.PNG).withSize(250,250).stream();
 
        try {
            FileOutputStream fout = new FileOutputStream(new File("M:\\new\\QR_Code.PNG"));
 
            fout.write(out.toByteArray());
 
            fout.flush();
            fout.close();
 
        } catch (FileNotFoundException e) {
            // Do Logging
        	e.printStackTrace();
        } catch (IOException e) {
            // Do Logging
        	e.printStackTrace();
        }
    }
}
/*class Frame3 extends JFrame implements ActionListener
{
  JLabel answer = new JLabel("");
  JPanel pane = new JPanel(); // create pane object
  JButton pressme = new JButton("Press Me to Create QR Code");
  Frame3()   // the constructor
  {
    super("Event Handler Demo"); setBounds(100,100,300,200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container con = this.getContentPane(); // inherit main frame
    con.add(pane); pressme.setMnemonic('P'); // associate hotkey
    pressme.addActionListener(this);   // register button listener
    pane.add(answer); pane.add(pressme); pressme.requestFocus();
    setVisible(true); // make frame visible
  }
  // here is the basic event handler
  public void actionPerformed(ActionEvent event)
  {
    Object source = event.getSource();
    if (source == pressme)
    {
      answer.setText("Button pressed!");
      JOptionPane.showMessageDialog(null,"I hear you!  QR Code is generated","Result",
      JOptionPane.PLAIN_MESSAGE); setVisible(true);  // show something
    }
  }
  
}*/
 class AwtControlDemo {

	   private Frame mainFrame;
	   private Label headerLabel;
	   private Label statusLabel;
	   private Panel controlPanel;
	   public Label l; 
	   public String a[];
	   final TextField userText1 = new TextField(16);
	   final TextField userText2 = new TextField(30);
	   EncryptTest tester = new EncryptTest();

	   public AwtControlDemo(){
	      prepareGUI();
	   }

	   /*public static void main(String[] args){
	      AwtControlDemo  awtControlDemo = new AwtControlDemo();
	      
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

	      l=new Label(" ",Label.CENTER);
	      
	      mainFrame.add(headerLabel);
	      mainFrame.add(controlPanel);
	      mainFrame.add(l);
	      mainFrame.add(statusLabel);
	      mainFrame.setVisible(true);  
	   }

	   public void showTextFieldDemo(){
	      headerLabel.setText("ENCRYPTION PROJECT DEMO"); 

	      Label  namelabel1= new Label("Key ", Label.RIGHT);
	      Label  nameLabel2 = new Label("Message ", Label.CENTER);
	      //final TextField userText1 = new TextField(16);
	      //final TextField userText2 = new TextField(20);
	     // userText2.setEchoChar('*');

	      Button loginButton = new Button("Encrypt and Generate");
	   
	      loginButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {     
	            String data = "Key is:  " + userText1.getText();
	            data += ", Message is:  " + userText2.getText();
	            statusLabel.setText(data);
	            String ab[]={userText1.getText(),userText2.getText()};
	            tester.start(ab);
	            QRCod q=new QRCod();
	            try {
					q.demo();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            String s;
	            if((s=userText1.getText()).length()==16)
	            l.setText("QUICK RESPONSE(QR) CODE IS GENERATED");
	            else
	            	l.setText("Please enter 16 bit key");
	         }
	      });
	      
	      
	      
	      controlPanel.add(namelabel1);
	      controlPanel.add(userText1);
	      controlPanel.add(nameLabel2);       
	      controlPanel.add(userText2);
	      controlPanel.add(loginButton);
	      mainFrame.setVisible(true);  
	   }
	   /*public String[] key()
	   {
		      a=new String[2];
		      a[0]=userText1.getText();
		      a[1]=userText2.getText();
		      return a;
	   }*/
	   
	}

