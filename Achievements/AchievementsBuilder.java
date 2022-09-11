import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class AchievementsBuilder {
	// class access variables
	JFrame baseFrame = new JFrame("Achievements Builder");
	int[][] addCoords = {
		{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, // add label x
		{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, // add label y
		{0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, // points label x
		{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10} // points label y
	};
	int[][] subCoords = {
		{0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}, // add label x
		{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, // add label y
		{0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, // points label x
		{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10} // points label y
	};
	int[][] spendCoords = {
		{0, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7}, // add label x
		{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, // add label y
		{0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8}, // points label x
		{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10} // points label y
	};
	int addCount = 1;
	int subCount = 1;
	int spendCount = 1;

	// set-up custom colors
	Color backgroundColor = new Color(35, 41, 50);
	Color lightBlue = new Color(1, 173, 181);
	Color darkBlue = new Color(1, 155, 160);
	Color offWhite = new Color(238, 238, 238);
	Color darkGrey = new Color(58, 63, 71); // banner
	Color medGrey = new Color(71, 77, 86); // other dark
	Color grey = new Color(85, 93, 104); // other light
	Color lightGrey = new Color(98, 107, 119); // lightest

	// set-up topPanel and layout
	GridBagLayout gridTop = new GridBagLayout();
	JPanel topPanel = new JPanel(gridTop);
	GridBagConstraints topConst = new GridBagConstraints();

	// set-up text field arrays
	JTextField[] addFields = new JTextField[20];
	JTextField[] subFields = new JTextField[20];
	JTextField[] spendFields = new JTextField[20];

	public void setup() {
		/**********************************************
		*			SET-UP BASE FRAME/PANELS
		* JFrame (baseFrame)
		* basePanel - holds top/bottomPanel
		* topPanel - holds add, sub, and spend fields
		* bottomPanel - holds +,-,continue,help,cancel
		* 	buttons
		**********************************************/

		// set-up JFrame
		baseFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		// set-up basePanel and layout
		JPanel basePanel = new JPanel(new BorderLayout());
		Border emptyBorder = BorderFactory.createEmptyBorder(25, 10,25, 10);
		basePanel.setBorder(emptyBorder);
		basePanel.setBackground(backgroundColor);

		// set-up bottomPanel and layout
		GridBagLayout gridBottom = new GridBagLayout();
		JPanel bottomPanel = new JPanel(gridBottom);
		GridBagConstraints bottomConst = new GridBagConstraints();
		bottomPanel.setBackground(backgroundColor);
		topPanel.setBackground(backgroundColor);


		/**********************************************
		*			  CREATING BUTTONS
		* +, - : each of add, sub, spend have one to
		* 	add or subtract text fields
		* continue - to move onto the next stage of
		* 	the program
		* help - to give a dialog message of how the
		* 	builder works
		* cancel - close and quit
		**********************************************/
		// set-up all buttons
		JButton addPlus = new JButton("+");
		buttonProperties(addPlus);
		JButton addMinus = new JButton("-");
		buttonProperties(addMinus);

		JButton subPlus = new JButton("+");
		buttonProperties(subPlus);
		JButton subMinus = new JButton("-");
		buttonProperties(subMinus);

		JButton spendPlus = new JButton("+");
		buttonProperties(spendPlus);
		JButton spendMinus = new JButton("-");
		buttonProperties(spendMinus);

		JButton continueButton = new JButton("Continue");
		buttonProperties(continueButton);
		JButton helpButton = new JButton("Help");
		buttonProperties(helpButton);
		JButton cancelButton = new JButton("Cancel");
		buttonProperties(cancelButton);

		/**********************************************
		*			FORMATTING SPACES
		* creating empty spaces on top and bottom 
		* 	panels for better display
		**********************************************/

		// set-up labels on grid topPanel
		topConst.fill = GridBagConstraints.HORIZONTAL;
		topConst.weightx = 1.0;
		topConst.weighty = 1.0;

		// set-up blank spaces on topPanel
		for(int i = 0; i < 10; i++) {
			for(int ii = 0; ii < 11; ii++) {
				if(ii == 0) {
					topConst.gridx = i;
					topConst.gridy = ii;
					topPanel.add(new JLabel(""), topConst);
				}
				else if(i == 0 || i == 3 || i == 6 || i == 9) {
					topConst.gridx = i;
					topConst.gridy = ii;
					topPanel.add(new JLabel(""), topConst);
				}
			}
		}

		topConst.gridy = 0;
		topConst.gridx = 1;
		JLabel addLabel = new JLabel("Add Points Options");
		labelProperties(addLabel);
		topPanel.add(addLabel, topConst);

		topConst.gridx = 2;
		JLabel addPointsLabel = new JLabel("Points");
		labelProperties(addPointsLabel);
		topPanel.add(addPointsLabel, topConst);

		topConst.gridx = 4;
		JLabel subLabel = new JLabel("Subtract Points Option");
		labelProperties(subLabel);
		topPanel.add(subLabel, topConst);

		topConst.gridx = 5;
		JLabel subPointsLabel = new JLabel("Points");
		labelProperties(subPointsLabel);
		topPanel.add(subPointsLabel, topConst);

		topConst.gridx = 7;
		JLabel spendLabel = new JLabel("Spend Points Option");
		labelProperties(spendLabel);
		topPanel.add(spendLabel, topConst);

		topConst.gridx = 8;
		JLabel spendPointsLabel = new JLabel("Points");
		labelProperties(spendPointsLabel);
		topPanel.add(spendPointsLabel, topConst);

		// set-up blank spaces on bottomPanel
		bottomConst.fill = GridBagConstraints.HORIZONTAL;
		bottomConst.weightx = 1.0;
		bottomConst.weighty = 1.0;
		bottomConst.insets = new Insets(3, 3, 3, 3);
		int[][] tempCoords = {
			{0, 3, 6, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 3, 4, 5, 6, 7, 8, 9, 0, 3, 4, 5, 6, 9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
			{0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}
		};

		for(int i = 0; i < 38; i++) {
			bottomConst.gridx = tempCoords[0][i];
			bottomConst.gridy = tempCoords[1][i];
			bottomPanel.add(new JLabel(""), bottomConst);
		}

		/**********************************************
		*			SET-UP TEXT FIELDS
		* initializing and formatting each text field
		* adding first of each category to display
		**********************************************/

		// set-up text fields on topPanel
		for(int i = 0; i < 20; i++) {
			addFields[i] = new JTextField();
			if(i % 2 == 0) {
				addFields[i].setBackground(grey);
				addFields[i].setBorder(BorderFactory.createLineBorder(grey, 3));
			}
			else {
				addFields[i].setBackground(medGrey);
				addFields[i].setBorder(BorderFactory.createLineBorder(medGrey, 3));
			}
			addFields[i].setForeground(offWhite);
		}
		for(int i = 0; i < 20; i++) {
			subFields[i] = new JTextField();
			if(i % 2 == 0) {
				subFields[i].setBackground(grey);
				subFields[i].setBorder(BorderFactory.createLineBorder(grey, 3));
			}
			else {
				subFields[i].setBackground(medGrey);
				subFields[i].setBorder(BorderFactory.createLineBorder(medGrey, 3));
			}
			subFields[i].setForeground(offWhite);
		}
		for(int i = 0; i < 20; i++) {
			spendFields[i] = new JTextField();
			if(i % 2 == 0) {
				spendFields[i].setBackground(grey);
				spendFields[i].setBorder(BorderFactory.createLineBorder(grey, 3));
			}
			else {
				spendFields[i].setBackground(medGrey);
				spendFields[i].setBorder(BorderFactory.createLineBorder(medGrey, 3));
			}
			spendFields[i].setForeground(offWhite);
		}

		// adding one of each
		topConst.insets = new Insets(0, 0, 0, 5);
		topConst.gridy = 1;
		topConst.gridx = 1;
		topPanel.add(addFields[0], topConst);

		topConst.gridx = 2;
		topPanel.add(addFields[10], topConst);

		topConst.gridx = 4;
		topPanel.add(subFields[0], topConst);

		topConst.gridx = 5;
		topPanel.add(subFields[10], topConst);

		topConst.gridx = 7;
		topPanel.add(spendFields[0], topConst);

		topConst.gridx = 8;
		topPanel.add(spendFields[10], topConst);

		/**********************************************
		*		ADDING BOTTOMPANEL BUTTONS
		* aligning, formatting, and adding buttons to
		* 	bottomPanel
		**********************************************/

		// set-up buttons on bottomPanel
		bottomConst.gridx = 1;
		bottomConst.gridy = 1;
		bottomPanel.add(addPlus, bottomConst);

		bottomConst.gridx = 2;
		bottomPanel.add(addMinus, bottomConst);

		bottomConst.gridx = 4;
		bottomPanel.add(subPlus, bottomConst);

		bottomConst.gridx = 5;
		bottomPanel.add(subMinus, bottomConst);

		bottomConst.gridx = 7;
		bottomPanel.add(spendPlus, bottomConst);

		bottomConst.gridx = 8;
		bottomPanel.add(spendMinus, bottomConst);

		bottomConst.gridwidth = 2;
		bottomConst.gridx = 1;
		bottomConst.gridy = 3;
		bottomPanel.add(helpButton, bottomConst);

		bottomConst.gridy = 4;
		bottomPanel.add(cancelButton, bottomConst);

		bottomConst.gridx = 7;
		bottomPanel.add(continueButton, bottomConst);

		/**********************************************
		*				ACTION LISTENERS
		* giving the buttons action listeners
		* - do stuff on click
		* - do stuff on hover
		* giving frame window listener
		**********************************************/

		// set-up [add, sub, spend] [+, -] button listeners
		addPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moreTextFields("add");
			}
		});
		subPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moreTextFields("sub");
			}
		});
		spendPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moreTextFields("spend");
			}
		});

		addMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lessTextFields("add");
			}
		});
		subMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lessTextFields("sub");
			}
		});
		spendMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lessTextFields("spend");
			}
		});

		// set-up [help, cancel, continue] button listeners
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayDialog("help");
			}
		});
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayDialog("cancel");
			}
		});
		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayDialog("continue");
			}
		});
		baseFrame.addWindowListener(new WindowListener() {
			public void windowClosing(WindowEvent e) {
				displayDialog("cancel");
			}
			public void windowDeactivated(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowClosed(WindowEvent e) {}
			public void windowOpened(WindowEvent e) {}
		});


		/**********************************************
		*				PACK AND DISPLAY
		* adding top/bottom to basePanel
		* assing basePanel to baseFrame
		* packing and setting visible
		**********************************************/

		// packing frame and setting visible
		basePanel.add(topPanel, BorderLayout.PAGE_START);
		basePanel.add(bottomPanel, BorderLayout.PAGE_END);
		baseFrame.add(basePanel);
		baseFrame.setPreferredSize(new Dimension(850, 450));
		baseFrame.pack();
		baseFrame.setLocationRelativeTo(null);
		baseFrame.setVisible(true);
		baseFrame.setResizable(false);
	}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	// adding more text fields to topPanel
	public void moreTextFields(String name) {
		topConst.fill = GridBagConstraints.HORIZONTAL;
		topConst.weightx = 1.0;

		switch(name) {
			case "add":
				if(addCount < 10) {
					addCount++;
					topConst.gridx = addCoords[0][addCount]; // add label x
					topConst.gridy = addCoords[1][addCount]; // add label y
					topPanel.add(addFields[addCount-1], topConst);
					topConst.gridx = addCoords[2][addCount]; // points label x
					topConst.gridy = addCoords[3][addCount]; // points label y
					topPanel.add(addFields[addCount+9], topConst);
				}
				break;
			case "sub":
				if(subCount < 10) {
					subCount++;
					topConst.gridx = subCoords[0][subCount]; // sub label x
					topConst.gridy = subCoords[1][subCount]; // sub label y
					topPanel.add(subFields[subCount-1], topConst);
					topConst.gridx = subCoords[2][subCount]; // points label x
					topConst.gridy = subCoords[3][subCount]; // points label y
					topPanel.add(subFields[subCount+9], topConst);
				}
				break;
			case "spend":
				if(spendCount < 10) {
					spendCount++;
					topConst.gridx = spendCoords[0][spendCount]; // spend label x
					topConst.gridy = spendCoords[1][spendCount]; // spend label y
					topPanel.add(spendFields[spendCount-1], topConst);
					topConst.gridx = spendCoords[2][spendCount]; // points label x
					topConst.gridy = spendCoords[3][spendCount]; // points label y
					topPanel.add(spendFields[spendCount+9], topConst);
				}
				break;
			default:
				break;
		}
		topPanel.revalidate();
		topPanel.repaint();
	}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	// removing text fields from topPanel
	public void lessTextFields(String name) {
		switch(name) {
			case "add":
				if(addCount > 1) {
					topPanel.remove(addFields[addCount-1]);
					topPanel.remove(addFields[addCount+9]);
					addCount--;
				}
				break;
			case "sub":
				if(subCount > 1) {
					topPanel.remove(subFields[subCount-1]);
					topPanel.remove(subFields[subCount+9]);
					subCount--;
				}
				break;
			case "spend":
				if(spendCount > 1) {
					topPanel.remove(spendFields[spendCount-1]);
					topPanel.remove(spendFields[spendCount+9]);
					spendCount--;
				}
				break;
			default:
				break;
		}
		topPanel.revalidate();
		topPanel.repaint();
	}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	// set color and font for labels
	public void labelProperties(JLabel label) {
		label.setFont(new Font("Corbel", Font.PLAIN, 14));
		label.setForeground(offWhite);
	}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	// set color, font, and shape for buttons
	public void buttonProperties(JButton button) {
		button.setFont(new Font("Corbel", Font.PLAIN, 16));
		button.setBackground(lightBlue);
		button.setForeground(darkGrey);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setHorizontalTextPosition(SwingConstants.CENTER);
	}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	// helpButton popup dialog
	public void displayDialog(String name) {
		baseFrame.setEnabled(false);
		// set-up base window and panel
		JFrame dialogFrame = new JFrame();
		dialogFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		JPanel dialogPanel = new JPanel(new GridBagLayout());
		dialogPanel.setBackground(darkGrey);
		GridBagConstraints dialogConst = new GridBagConstraints();

		// set-up text label
		String text = "";
		if(name.compareTo("help") == 0) {
			text = "Setup your desired points reward system.\n\n" + 
						  "Determine which actions earn points or take away points\n" + 
						  "and how to spend earned points. You also determine how\n" + 
						  "many points each action is worth.\n\n" + 
						  "For example: under the \"Add Points Option\", you can\n" + 
						  "have an action such as \"Skipped Dessert\" which gives\n" + 
						  "you 5 points. Or, under the \"Subtract Points Action\",\n" + 
						  "you can have an action such as \"Ate too Many Sweets\"\n" + 
						  "which takes away 5 points.\n\n" + 
						  "Once you are finished, click on the Continue button.\n" + 
						  "If you wish to exit, click on the Cancel button.";
		}
		else if(name.compareTo("cancel") == 0) {
			text ="Are you sure you wish to cancel?\nYour input will not be saved.";
		}
		else if(name.compareTo("continue") == 0){
			text = "Are you sure you want to save and continue?";
		}
		else if(name.compareTo("errorText") == 0) {
			text = "One of your text fields is empty. Please either add\n" +
				"text or remove it with the - button.";
		}
		else if(name.compareTo("errorNum") == 0) {
			text ="One of your number fields is either empty or not a\n" + 
				"number. Please either add a number or remove it with\n" +
				"the - button.";
		}

		JTextArea dialogText = new JTextArea(text); 
		dialogText.setEnabled(false);
		dialogText.setBackground(darkGrey);
		dialogText.setForeground(offWhite);
		dialogText.setFont(new Font("Corbel", Font.PLAIN, 14));
		dialogConst.gridx = 0;
		dialogConst.gridy = 0;
		dialogConst.weightx = 1.0;
		dialogConst.weighty = 1.0;
		if(name.compareTo("help") == 0 || name.compareTo("errorText") == 0 || name.compareTo("errorNum") == 0) {
			dialogConst.gridheight = 4;
			dialogConst.gridwidth = 5;
		}
		else if(name.compareTo("cancel") == 0 || name.compareTo("continue") == 0) {
			dialogConst.gridheight = 2;
			dialogConst.gridwidth = 5;
		}
		dialogPanel.add(dialogText, dialogConst);

		// set-up first button
		JButton firstButton = new JButton("");
		if(name.compareTo("help") == 0 || name.compareTo("errorText") == 0 || name.compareTo("errorNum") == 0) {
			firstButton = new JButton("Okay");
			dialogConst.gridy = 4;
			dialogConst.gridheight = 1;
		}
		else if(name.compareTo("cancel") == 0) {
			firstButton = new JButton("Go Back");
			dialogConst.gridwidth = 1;
			dialogConst.gridy = 2;
			dialogConst.gridx = 3;
		}
		else if(name.compareTo("continue") == 0) {
			firstButton = new JButton("Yes");
			dialogConst.gridwidth = 1;
			dialogConst.gridy = 2;
			dialogConst.gridx = 3;
		}
		firstButton.setBackground(offWhite);
		firstButton.setFont(new Font("Corbel", Font.PLAIN, 16));
		firstButton.setForeground(medGrey);
		firstButton.setBorderPainted(false);
		firstButton.setFocusPainted(false);
		firstButton.setHorizontalTextPosition(SwingConstants.CENTER);
		dialogPanel.add(firstButton, dialogConst);

		// setting up second button
		JButton secondButton = new JButton("");
		if(name.compareTo("cancel") == 0) {
			secondButton = new JButton("Quit");
		}
		else if(name.compareTo("continue") == 0) {
			secondButton = new JButton("Go Back");
		}
		secondButton.setBackground(offWhite);
		secondButton.setFont(new Font("Corbel", Font.PLAIN, 16));
		secondButton.setForeground(medGrey);
		secondButton.setBorderPainted(false);
		secondButton.setFocusPainted(false);
		secondButton.setHorizontalTextPosition(SwingConstants.CENTER);
		if(name.compareTo("cancel") == 0 || name.compareTo("continue") == 0) {
			dialogConst.gridx = 1;
			dialogConst.gridwidth = 1;
			dialogPanel.add(secondButton, dialogConst);
		}

		// buttons action listeners
		firstButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				baseFrame.setEnabled(true);
				dialogFrame.dispose();
				if(name.compareTo("continue") == 0) {
					continueCheck();
				}
			}
		});

		secondButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(name.compareTo("cancel") == 0) {
					System.exit(0);
				}
				else {
					baseFrame.setEnabled(true);
					dialogFrame.dispose();
				}
			}
		});

		// packing and displaying
		dialogFrame.add(dialogPanel);
		if(name.compareTo("help") == 0) {
			dialogFrame.setPreferredSize(new Dimension(400, 400));
		}
		else if(name.compareTo("cancel") == 0 || name.compareTo("continue") == 0 || name.compareTo("errorText") == 0 || name.compareTo("errorNum") == 0) {
			dialogFrame.setPreferredSize(new Dimension(400, 200));
		}
		dialogFrame.pack();
		dialogFrame.setLocationRelativeTo(null);
		dialogFrame.setVisible(true);
		dialogFrame.setResizable(false);
	}

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	//checking if can continue or giving error
	public void continueCheck() {
		String tempText;
		String tempNum;
		// checking text fields for validity
		for(int i = addCount - 1; i >= 0; i--) {
			tempText = addFields[i].getText();
			if(tempText.length() == 0) {
				displayDialog("errorText");
				return;
			}
		}
		for(int i = subCount - 1; i >= 0; i--) {
			tempText = subFields[i].getText();
			if(tempText.length() == 0) {
				displayDialog("errorText");
				return;
			}
		}
		for(int i = spendCount - 1; i >= 0; i--) {
			tempText = spendFields[i].getText();
			if(tempText.length() == 0) {
				displayDialog("errorText");
				return;
			}
		}

		// checking number fields for validity
		for(int i = addCount - 1; i > 0; i--) {
			tempNum = addFields[i+10].getText();
			if(tempNum.length() == 0 || !tempNum.matches("[0-9]+")) {
				displayDialog("errorNum");
				return;
			}
		}
		for(int i = subCount - 1; i > 0; i--) {
			tempNum = subFields[i+10].getText();
			if(tempNum.length() == 0 || !tempNum.matches("[0-9]+")) {
				displayDialog("errorNum");
				return;
			}
		}
		for(int i = spendCount - 1; i > 0; i--) {
			tempNum = spendFields[i+10].getText();
			if(tempNum.length() == 0 || !tempNum.matches("[0-9]+")) {
				displayDialog("errorNum");
				return;
			}
		}

		save();
	}

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	// saving to file and running achievements proper
	public void save() {
		// adding values to array lists
		ArrayList<String> addList = new ArrayList<>();
		ArrayList<String> subList = new ArrayList<>();
		ArrayList<String> spendList = new ArrayList<>();

		addList.add(String.valueOf(addCount));
		subList.add(String.valueOf(subCount));
		spendList.add(String.valueOf(spendCount));

		for(int i = 0; i < addCount; i++) {
			addList.add(addFields[i].getText());
			addList.add(addFields[i+10].getText());
		}
		for(int i = 0; i < subCount; i++) {
			subList.add(subFields[i].getText());
			subList.add(subFields[i+10].getText());
		}
		for(int i = 0; i < spendCount; i++) {
			spendList.add(spendFields[i].getText());
			spendList.add(spendFields[i+10].getText());
		}

		// preparing file for writing
		PrintWriter outputFile;
		try {
			outputFile = new PrintWriter("setup.txt");
			for(int ii = 0; ii < addList.size(); ii++) {
				outputFile.println(addList.get(ii));
			}
			for(int ii = 0; ii < subList.size(); ii++) {
				outputFile.println(subList.get(ii));
			}
			for(int ii = 0; ii < spendList.size(); ii++) {
				outputFile.println(spendList.get(ii));
			}
			outputFile.println("");
			for(int ii = 0; ii < 3; ii++) {
				outputFile.print("0");
				outputFile.print(" ");
			}
			outputFile.println("");
			for(int ii = 0; ii < 42; ii++) {
				outputFile.print("0");
				outputFile.print(" ");
			}
			outputFile.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println("File setup.txt could not be found.");
		}

		// close and start program
		baseFrame.dispose();
		Achievements startProgram = new Achievements();
		startProgram.setup();
	}
}