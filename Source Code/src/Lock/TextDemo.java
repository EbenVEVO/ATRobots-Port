package Lock;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.annotation.processing.FilerException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
 
public class TextDemo extends JPanel implements ActionListener {
    static JTextField textField = new JTextField(45);
    protected JTextArea textArea;
    static JTextField key = new JTextField();
    static JTextField ENC = new JTextField(45);
    static JTextField DEC = new JTextField(45);
    
    static String k = ("largecats");
    String inputFileName;
    String decryptFileName = DEC.getText();
    String outputFileName = ENC.getText();
    static JButton unlock = new JButton("Unlock");
    static JButton lock = new JButton("Lock");
  
        
 ////////////////////////////////////////////////////////////////////////////////////////////////////       
        //Component Properties
/////////////////////////////////////////////////////////////////////////////////////////////////////
    

    public TextDemo() {
        super(new GridBagLayout());
    
        JButton Select = new JButton("...");
        Select.setBackground(Color.RED);
        Select.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	Select.setBackground(Color.ORANGE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	Select.setBackground(Color.RED);
            }
        });
        
        JButton Select1 = new JButton("...");
        Select1.setBackground(Color.RED);
        Select1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	Select1.setBackground(Color.ORANGE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	Select1.setBackground(Color.RED);
            }
        });
        
        JButton Select2 = new JButton("...");
        Select2.setBackground(Color.RED);
        Select2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	Select2.setBackground(Color.ORANGE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	Select2.setBackground(Color.RED);
            }
        });
       
       
        JButton lock = new JButton("Lock");
        lock.setBackground(Color.RED);
        lock.setVerticalTextPosition(AbstractButton.CENTER);
        lock.setHorizontalTextPosition(AbstractButton.LEADING);
        lock.setPreferredSize(new Dimension(40, 40));
        lock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lock.setBackground(Color.ORANGE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                lock.setBackground(Color.RED);
            }
        });
        
        JButton unlock = new JButton("Unlock");
        unlock.setBackground(Color.RED);
        unlock.setVerticalTextPosition(AbstractButton.BOTTOM);
        unlock.setHorizontalTextPosition(AbstractButton.LEADING);
        unlock.setPreferredSize(new Dimension(40, 40));
        unlock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                unlock.setBackground(Color.ORANGE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                unlock.setBackground(Color.RED);
            }
        });
        
        
        
        JLabel KeyLabel = new JLabel("Enter key:");
        KeyLabel.setForeground(Color.white);
        
        JTextField key = new JTextField(45);
        key.addActionListener(this);
        key.setBackground(Color.GRAY);
        key.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) { 
                if (key.getText().length() >= 4 ) // limit textfield to 4 characters
                    e.consume(); 
            }
        });
       
        
        JLabel ENClabel = new JLabel("Enter Full File Path for Encypted File Here:");
        ENClabel.setForeground(Color.white);
        
        JTextField ENC = new JTextField(45);
        ENC.addActionListener(this);
        ENC.setBackground(Color.GRAY);
        
        
        JLabel DEClabel = new JLabel("Enter Full File Path for Decypted File Here:");
        DEClabel.setForeground(Color.white);
        
        JTextField DEC = new JTextField(45);
        DEC.addActionListener(this);
        DEC.setBackground(Color.GRAY);
        
       
        JLabel myLabel = new JLabel("Enter Full File Path for Robot File Here:");
        myLabel.setForeground(Color.white);
        
        JTextField textField = new JTextField(45);
        textField.addActionListener(this);
        textField.setBackground(Color.GRAY);
        
        
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////        
       //Button Action Listeners
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
        
        
        lock.addActionListener(e -> { 
  
    		try {
    	    	String n = key.getText();
    	    	int ni = Integer.parseInt(n);
    	    	byte nb = (byte)ni;
    	    	System.out.println(nb);
    	    	
    	    	//getting files properly 
    	    	inputFileName = textField.getText();
    	    	outputFileName = ENC.getText();
    	    	System.out.println(outputFileName);
    	    	System.out.println(inputFileName);
    	    	
    	    	encryptFileContent(inputFileName,  nb, outputFileName);
    	    	 }
    	    	 catch(Exception r) {
    	    		 System.err.println("HELP!" + r);
    	    		 JOptionPane.showMessageDialog(null, "Error In Encryption");
    	    	 }
    		//removed finally statement with k string, wasn't doing anything
    			
    		});
    	
    	
    	unlock.addActionListener(e -> {
    		try {
    	    	String n = (key.getText());
    	    	int ni = Integer.parseInt(n);
    	    	byte nb = (byte)ni;
    	    	System.out.println(nb);
    	    	
    	    	decryptFileName = DEC.getText();
    	    	outputFileName = ENC.getText();
    	    	System.out.println(outputFileName);
    	    	System.out.println(decryptFileName);
    	    	
    	    	decryptFileContent(outputFileName, nb, decryptFileName);
    	    	 }
    	    	 catch(NumberFormatException | IOException r) {
    	    		 System.err.println("HELP!" + r);
    	    		 JOptionPane.showMessageDialog(null, "Error In Decryption");
    	    		 
    	    	 }
    	 //removed finally statement with k string, wasn't doing anything
    	});
    	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////    	
    	//Action Listener for files choosers
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
    	
    	Select.addActionListener(e -> {
    		
    	     JFileChooser test = new JFileChooser("Test");
    	     
    	     ///////////////////////////////////////////////////////////////////////txt files filter
    	     FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
       	     test.setFileFilter(filter);
       	     /////////////////////////////////////////////////////////////////////////////////////////////
       	     
    	        test.setCurrentDirectory(new File("."));
    	        int responce = test.showOpenDialog(null);

    	        if (responce == JFileChooser.APPROVE_OPTION){
					File file = new File(test.getSelectedFile().getAbsolutePath());
    	            System.out.println(file);
    	            
    	          
    	            
    	            textField.setText(file.getAbsolutePath()); 
    	            
    	      
    		
    		JWindow window = new JWindow(); 
    		 window.setBackground(Color.red);
    		 
             
             window.add(test);
             
    		
    	
               
                
            
    	        }
    	});
    	
    	Select1.addActionListener(e -> {
    		
   	     JFileChooser test1 = new JFileChooser("Test");
   	     
         /////////////////////////////////////////////////////////////////txt file filter
   	     FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
   	     test1.setFileFilter(filter);
   	     ////////////////////////////////////////////////////////////////////////////////
   	     
   	        test1.setCurrentDirectory(new File("."));
   	        int responce = test1.showOpenDialog(null);

   	        if (responce == JFileChooser.APPROVE_OPTION){
					File file = new File(test1.getSelectedFile().getAbsolutePath());
   	            System.out.println(file);
  
   	            
   	            ENC.setText(file.getAbsolutePath()); 
   	            
   	      
   		
   		JWindow window = new JWindow(); 
   		 window.setBackground(Color.red);
   		 
            
            window.add(test1);
            
   		
   	
              
               
           
   	        }
   	});
    	
    	
    	Select2.addActionListener(e -> {
    		
   	     JFileChooser test2 = new JFileChooser("Test");
   	     
   	  ///////////////////////////////////////////////////////////////////////txt files filter
		     FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
	   	     test2.setFileFilter(filter);
	   	     /////////////////////////////////////////////////////////////////////////////////////////////
	   	     
   	        test2.setCurrentDirectory(new File("."));
   	        int responce = test2.showOpenDialog(null);

   	        if (responce == JFileChooser.APPROVE_OPTION){
					File file = new File(test2.getSelectedFile().getAbsolutePath());
   	            System.out.println(file);
   	            

   	            
   	            DEC.setText(file.getAbsolutePath()); 
   	            
   	      
   		
   		JWindow window = new JWindow(); 
   		 window.setBackground(Color.red);
   		 
            
            window.add(test2);
            
   		 
          
           
   	        }
   	});
     
        
       
//////////////////////////////////////////////////////////////////////////////////////////////////////
        //Add Components to displayed panel.
/////////////////////////////////////////////////////////////////////////////////////////////////////
        
        GridBagConstraints c = new GridBagConstraints();
       c.gridwidth = GridBagConstraints.REMAINDER;
 
        setBackground(Color.BLACK);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets (0,0,0,0); 
        add(myLabel, c);
        
        
       
        
        c.fill = GridBagConstraints.RELATIVE;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets (0,0,0,15); 
        c.ipadx = 1;
        c.ipady = 1;
        add(Select, c);
       
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets (0,0,0,0); 
        add(textField, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets (0,0,0,0); 
        add(KeyLabel, c);
        
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets (0,0,0,0); 
        add(key, c);
        
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        c.insets = new Insets (0,0,0,0); 
        add(ENClabel, c);
        
        c.fill = GridBagConstraints.RELATIVE;
        c.gridx = 0;
        c.gridy = 5;
        c.insets = new Insets (0,0,0,-20); 
        c.ipadx = 1;
        c.ipady = 1;
        add(Select1, c);
       
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 6;
        c.insets = new Insets (0,0,0,0); 
        add(ENC, c);
        
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 7;
        c.insets = new Insets (0,0,0,0); 
        add(DEClabel, c);
        
        c.fill = GridBagConstraints.RELATIVE;
        c.gridx = 0;
        c.gridy = 7;
        c.insets = new Insets (0,0,0,-20); 
        c.ipadx = 1;
        c.ipady = 1;
        add(Select2, c);
        
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 8;
        c.insets = new Insets (0,0,0,0); 
        add(DEC, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 9;
        c.insets = new Insets (0,0,0,0); 
        add(lock, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 10;
        c.insets = new Insets (0,0,0,0); 
        add(unlock, c);
        
        
       
        
    }
    
 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
    //Launching GUI function
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("ATR-Lock");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
 
        //Add contents to the window.
        frame.add(new TextDemo());
 
        //Display the window.
        frame.setBackground(Color.BLACK);
        frame.pack();
        frame.setVisible(true);
    }
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////    
  //function to encrypt the input file
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    public static void encryptFileContent(String inputFileName,

    		int nb, String outputFileName) throws IOException, FileNotFoundException{

    		File file = new File(inputFileName);

    		FileInputStream fin = new FileInputStream(file);

    		FileOutputStream fout = new FileOutputStream(outputFileName);

    		try{

    		while(fin.available() != 0){

    		int inData = fin.read();//reads all char

    		//For every character in input add code value and

    		//write result to encypted file 

    		fout.write(inData+nb);

    		}

    		}finally{

    		fin.close();

    		fout.close();
    		
    		}
    }
    
    
    
///////////////////////////////////////////////////////////////////////////////////////////////////   
  //function to decrypt the encrypted file
///////////////////////////////////////////////////////////////////////////////////////////////
    
    
    public static void decryptFileContent(String outputFileName,

    int nb, String decryptFileName) throws IOException, FileNotFoundException{

    File file = new File(outputFileName);

    FileInputStream fin = new FileInputStream(file);

    FileOutputStream fout = new FileOutputStream(decryptFileName);

    try{

    while(fin.available() != 0){

    int inData = fin.read();

    //For every character in encrypted file

    //subtract the code value from each character and

    //write it to decrypted file 

    fout.write(inData-nb);

    }

    }finally{

    fin.close();

    fout.close();

    }

    }

    
    
 /*
    public static void main(String[] args) {
    	System.out.println("Entering main....");
    	
	    
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {	
                createAndShowGUI();     
      }

   });
}
*/ //this isn't need as the lock is ran through the start screen instead of on its own 
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

    