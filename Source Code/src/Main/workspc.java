package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Lock.TextDemo;
import Player.Tanks;
//Title screen code, annoyingly reprogrammed hours before its due.
//Written by Fenner Hughes
public class workspc {
	GameWindow gw = new GameWindow();
	int numtanks = 0;
	int green = -1, red = -1, blue = -1, cyan = -1, magenta = -1, yellow = -1;
	int tankX = 0, tankY = 0;
    public void start()
    {   //Initializing menu elements
        JFrame window = new JFrame();
        window.setSize(600,600);
        window.setLayout(null);
        window.getContentPane().setBackground(Color.BLACK);
        JTextArea title = new JTextArea("AT-Robots: Blue Edition"), rName1 = new JTextArea("Select Robot..."), rName2 = new JTextArea("Select Robot..."), rName3 = new JTextArea("Select Robot..."), rName4 = new JTextArea("Select Robot..."), rName5 = new JTextArea("Select Robot..."), rName6 = new JTextArea("Select Robot..."), errorMsg = new JTextArea("Slot already filled.");
        JButton rSelect1 = new JButton("..."), rSelect2 = new JButton("..."), rSelect3 = new JButton("..."), rSelect4 = new JButton("..."), rSelect5 = new JButton("..."), rSelect6 = new JButton("...");
        JButton rDel1 = new JButton("X"), rDel2 = new JButton("X"), rDel3 = new JButton("X"), rDel4 = new JButton("X"), rDel5 = new JButton("X"), rDel6 = new JButton("X");
        JButton start = new JButton("Start"), lockR = new JButton ("Lock");
        JTextArea[] textAreas = new JTextArea[8];
        textAreas[0] = title; textAreas[1] = rName1; textAreas[2] = rName2; textAreas[3] = rName3; textAreas[4] = rName4; textAreas[5] = rName5; textAreas[6] = rName6; textAreas[7] = errorMsg;
        JButton[] buttons = new JButton[14];
        buttons[0] = rSelect1; buttons[1] = rSelect2; buttons[2] = rSelect3; buttons[3] = rSelect4; buttons[4] = rSelect5; buttons[5] = rSelect6; 
        buttons[6] = rDel1; buttons[7] = rDel2; buttons[8] = rDel3; buttons[9] = rDel4; buttons[10] = rDel5; buttons[11] = rDel6; buttons[12] = start; buttons[13] = lockR;
        for(int i = 0; i < 7; i++)
        	textAreas[i].setEditable(false);
        int coordX, coordY, coordW = 150, coordH = 50;
        for(int j = 0; j < 8; j++)
        {
        	if(j == 0)
        		coordX = 225;
        	else if(j == 7)
        		coordX = 250;
        	else if(j % 2 == 0)
        		coordX = 300;
        	else
        		coordX = 100;
        	if(j == 0)
        		coordY = 50;
        	else if (j == 7)
        		coordY = 350;
        	else if(j < 3)
        		coordY = 150;
        	else if(j < 5)
        		coordY = 200;
        	else
        		coordY = 250;
        	txtAInitialize(textAreas[j], coordX, coordY, coordW, coordH);
        }
        for(int k = 0; k < 14; k++)
        {
        	if(k > 11)
        		coordW = 75;
        	else	
        		coordW = 50;
        	if(k > 5 && k != 12)
        		if (k % 2 != 0)
        			coordX = 500;
        		else
        			coordX = 50;
        	else
        		if (k % 2 != 0)
        			coordX = 450;
        		else
        			coordX = 250;
        	if(k < 2 || (k < 8 && k > 5))
        		coordY = 150;
        	else if(k < 4 || (k > 5 && k < 10))
        		coordY = 200;
        	else if (k < 6 || (k > 6 && k < 12))
        		coordY = 250;
        	else
        		coordY = 400;
        	btnInitialize(buttons[k], coordX, coordY, coordW, coordH);
        }
        //Add actions to each button
        rSelect1.addActionListener(e -> addRobot(rName1, rName2, rName3, rName4, rName5, rName6, "JADE", 0, errorMsg));
        rDel1.addActionListener(e -> moveRobots(rName1, rName2, rName3, rName4, rName5, rName6, 0, errorMsg));
        rSelect2.addActionListener(e -> addRobot(rName1, rName2, rName3, rName4, rName5, rName6, "RUBY", 1, errorMsg));
        rDel2.addActionListener(e -> moveRobots(rName1, rName2, rName3, rName4, rName5, rName6, 1, errorMsg));
        rSelect3.addActionListener(e -> addRobot(rName1, rName2, rName3, rName4, rName5, rName6, "SAPPHIRE", 2, errorMsg));
        rDel3.addActionListener(e -> moveRobots(rName1, rName2, rName3, rName4, rName5, rName6, 2, errorMsg));
        rSelect4.addActionListener(e -> addRobot(rName1, rName2, rName3, rName4, rName5, rName6, "DIAMOND", 3, errorMsg));
        rDel4.addActionListener(e -> moveRobots(rName1, rName2, rName3, rName4, rName5, rName6, 3, errorMsg));
        rSelect5.addActionListener(e -> addRobot(rName1, rName2, rName3, rName4, rName5, rName6, "PEARL", 4, errorMsg));
        rDel5.addActionListener(e -> moveRobots(rName1, rName2, rName3, rName4, rName5, rName6, 4, errorMsg));
        rSelect6.addActionListener(e -> addRobot(rName1, rName2, rName3, rName4, rName5, rName6, "TOPAZ", 5, errorMsg));
        rDel6.addActionListener(e -> moveRobots(rName1, rName2, rName3, rName4, rName5, rName6, 5, errorMsg));
        start.addActionListener(e -> {
        	if(numtanks < 2)
        	{
        		errorMsg.setForeground(Color.white);
        		errorMsg.setText("You need at least 2 robots.");
        	}
        	else
        	{
        	window.dispose();
        	gw.setUpGame();  
        	}
        	});
        lockR.addActionListener(e -> {
        	TextDemo td = new TextDemo();
        	td.createAndShowGUI();
        	
        });
        for(int l = 0; l < 13; l++)
        	btnHover(buttons[l], l);
        for(int m = 0; m < 8; m++)
        	window.add(textAreas[m]);
        for(int n = 0; n < 14; n++)
        	window.add(buttons[n]);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
    public static void txtAInitialize(JTextArea txtA, int X, int Y, int W, int H)
    //Places textArea at coordinates with different area colors
    {
    	txtA.setBounds(X,Y,W,H);
    	if (X == 250)
    		txtA.setForeground(Color.black);
    	else
    		txtA.setForeground(Color.white);
    	if(X == 225 || X == 250)
    		txtA.setBackground(Color.black);
    	else
    		txtA.setBackground(Color.gray);
    }
    public static void btnInitialize(JButton btn, int X, int Y, int W, int H)
    //Initializes default button colors
    {
    	btn.setBounds(X,Y,W,H);
    	if(X != 50 && X != 500)
    	{
    		btn.setForeground(Color.white);
    	    if(W == 75)
    	    	btn.setBackground(Color.gray);
    	    else
    	    	btn.setBackground(Color.black);
    	}
    	else
    		btn.setBackground(Color.red);
    }
    //Initializes button colors when you hover over the button
    public static void btnHover(JButton btn, int num)
    {
    	if(num < 6)
    	{
    		btn.addMouseListener(new MouseAdapter() {
            	public void mouseEntered(MouseEvent e) {
            		btn.setBackground(Color.gray);
            	}
            	public void mouseExited(MouseEvent e) {
            		btn.setBackground(Color.black);
            	}
            });
    	}
    	else if(num != 12)
    	{
    		btn.addMouseListener(new MouseAdapter() {
            	public void mouseEntered(MouseEvent e) {
            		btn.setBackground(Color.ORANGE);
            	}
            	public void mouseExited(MouseEvent e) {
            		btn.setBackground(Color.RED);
            	}
            });
    	}
    	else
    	{
    		btn.addMouseListener(new MouseAdapter() {
            	public void mouseEntered(MouseEvent e) {
            		btn.setBackground(Color.WHITE);
            		btn.setForeground(Color.black);
            	}
            	public void mouseExited(MouseEvent e) {
            		btn.setBackground(Color.gray);
            		btn.setForeground(Color.white);
            	}
            });
    	}
    	
    }
    //Adds robot
    public void addRobot(JTextArea slot1, JTextArea slot2, JTextArea slot3, JTextArea slot4, JTextArea slot5, JTextArea slot6, String name, int btnNum, JTextArea msg)
    {
    	boolean errorShowing = true;
    	if(slot1.getText().contains("Select Robot...") && btnNum == 0) {
    		numtanks++;
    		green = numtanks - 1;
    		slot1.setText(name + " loaded in slot " + (green + 1));
    		errorShowing = false;
    		String color = "green";
    		Tanks tanks = new Tanks(gw);
    		position(green);
    		tanks.build(tankX,tankY,1,color);
    		gw.entityList.add(tanks);
    		
    	}
    	else if(slot2.getText().contains("Select Robot...") && btnNum == 1) {
    		numtanks++;
    		red = numtanks - 1;
    		slot2.setText(name + " loaded in slot " + (red + 1));
    		errorShowing = false;
    		String color = "red";
    		Tanks tanks = new Tanks(gw);
    		position(red);
    		tanks.build(tankX,tankY,1,color);
    		gw.entityList.add(tanks);
    		
    	}
    	else if(slot3.getText().contains("Select Robot...") && btnNum == 2) {
    		numtanks++;
    		blue = numtanks - 1;
    		slot3.setText(name + " loaded in slot " + (blue + 1));
    		errorShowing = false;
    		String color = "blue";
    		Tanks tanks = new Tanks(gw);
    		position(blue);
    		tanks.build(100,200,1,color);
    		gw.entityList.add(tanks);
    		
    	}
    	else if(slot4.getText().contains("Select Robot...") && btnNum == 3) {
    		numtanks++;
    		cyan = numtanks - 1;
    		slot4.setText(name + " loaded in slot " + (cyan + 1));
    		errorShowing = false;
    		String color = "cyan";
    		Tanks tanks = new Tanks(gw);
    		position(cyan);
    		tanks.build(tankX,tankY,1,color);
    		gw.entityList.add(tanks);
    		
    	}
    	else if (slot5.getText().contains("Select Robot...") && btnNum == 4){
    		numtanks++;
    		magenta = numtanks - 1;
    		slot5.setText(name + " loaded in slot " + (magenta + 1));
    		errorShowing = false;
    		String color = "magenta";
    		Tanks tanks = new Tanks(gw);
    		position(magenta);
    		tanks.build(tankX,tankY,1,color);
    		gw.entityList.add(tanks);
    		
    	}
    	else if(slot6.getText().contains("Select Robot...") && btnNum == 5) {
    		numtanks++;
    		yellow = numtanks - 1;
    		slot6.setText(name + " loaded in slot " + (yellow + 1));
    		errorShowing = false;
    		String color = "yellow";
    		Tanks tanks = new Tanks(gw);
    		position(yellow);
    		tanks.build(tankX,tankY,1,color);
    		gw.entityList.add(tanks);
    		
    	}
    	else
    	{
    		msg.setForeground(Color.white);
    		msg.setText("Slot already filled.");
    	}
    	if(errorShowing == false)
    		msg.setForeground(Color.black);
    }
    //Moves robots after one gets deleted
    public void moveRobots(JTextArea slot1, JTextArea slot2, JTextArea slot3, JTextArea slot4, JTextArea slot5, JTextArea slot6, int btnNum, JTextArea msg)
    {
    	int removed = -1;
    	boolean flag = false;
    	switch(btnNum)
    	{
    	case 0: if(!slot1.getText().contains("Select Robot..."))
    			{
    				slot1.setText("Select Robot...");
    				flag = true;
    				removed = green;
    				green = -1;
    			} break;
    	case 1: if(!slot2.getText().contains("Select Robot..."))
		{
			slot2.setText("Select Robot...");
			flag = true;
			removed = red;
			red = -1;
		} break;
    	case 2: if(!slot3.getText().contains("Select Robot..."))
		{
			slot3.setText("Select Robot...");
			flag = true;
			removed = blue;
			blue = -1;
		} break;
    	case 3: if(!slot4.getText().contains("Select Robot..."))
		{
			slot4.setText("Select Robot...");
			flag = true;
			removed = cyan;
			cyan = -1;
		} break;
    	case 4: if(!slot5.getText().contains("Select Robot..."))
		{
			slot5.setText("Select Robot...");
			flag = true;
			removed = magenta;
			magenta = -1;
		} break;
    	case 5: if(!slot6.getText().contains("Select Robot..."))
		{
			slot6.setText("Select Robot...");
			flag = true;
			removed = yellow;
			yellow = -1;
		}
    	}
    	if(flag == true && numtanks > 0)
    	{
    		gw.entityList.remove(numtanks - 1);
    		numtanks--;
    		renumber(removed, slot1, slot2, slot3, slot4, slot5, slot6);
    	}
    	msg.setForeground(Color.black);
    }
    
    public void renumber(int slot, JTextArea slot1, JTextArea slot2, JTextArea slot3, JTextArea slot4, JTextArea slot5, JTextArea slot6)
    {
    	if(green > slot)
    	{
    		green--;
    		slot1.setText("JADE loaded in slot " + (green + 1));
    	}
    	if(red > slot)
    	{
    		red--;
    		slot2.setText("RUBY loaded in slot " + (red + 1));
    	}
    	if(blue > slot)
    	{
    		blue--;
    		slot3.setText("SAPPHIRE loaded in slot " + (blue + 1));
    	}
    	if(cyan > slot)
    	{
    		cyan--;
    		slot4.setText("DIAMOND loaded in slot " + (cyan + 1));
    	}
    	if(magenta > slot)
    	{
    		magenta--;
    		slot5.setText("PEARL loaded in slot " + (magenta + 1));
    	}
    	if(yellow > slot)
    	{
    		yellow--;
    		slot6.setText("TOPAZ loaded in slot " + (yellow + 1));
    	}
    }
    public void position(int robot)
    {
    	switch(robot)
    	{
    	case 0: tankX = 50; tankY = 50; break;
    	case 1: tankX = 350; tankY = 50; break;
    	case 2: tankX = 50; tankY = 200; break;
    	case 3: tankX = 350; tankY = 200; break;
    	case 4: tankX = 50; tankY = 350; break;
    	case 5: tankX = 350; tankY = 350; break;
    	}
    }
}

