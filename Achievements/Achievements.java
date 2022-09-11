import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Calendar;
import java.util.Date;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

public class Achievements {
	public static void main(String[] args) {
		File setupFile = new File("setup.txt");
		if(!setupFile.exists()) {
			AchievementsBuilder newBuild = new AchievementsBuilder();
			newBuild.setup();
		}
		else {
			setup();
		}
	}

	public static void setup() {
		// variables
		File setupFile = new File("setup.txt");
		ArrayList<String> addList = new ArrayList<>(); // text, num, text, num...
		ArrayList<String> subList = new ArrayList<>();
		ArrayList<String> spendList = new ArrayList<>();
		ArrayList<Integer> dataList = new ArrayList<>(); // weight, goal, points

		// reading data from setup file
		try {
			Scanner inputFile = new Scanner(setupFile);

			addList.add(inputFile.nextLine());
			for(int i = 0; i < 2 * Integer.parseInt(addList.get(0)); i++) {
				addList.add(inputFile.nextLine());
			}
			subList.add(inputFile.nextLine());
			for(int i = 0; i < 2 *Integer.parseInt(subList.get(0)); i++) {
				subList.add(inputFile.nextLine());
			}
			spendList.add(inputFile.nextLine());
			for(int i = 0; i < 2 *Integer.parseInt(spendList.get(0)); i++) {
				spendList.add(inputFile.nextLine());
			}
			for(int i = 0; i < 3; i++) {
				dataList.add(Integer.parseInt(inputFile.nextLine()));
			}

			inputFile.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println("File setup.txt not found.");
		}

		start(addList, subList, spendList, dataList);
	}

	public static void start(ArrayList<String> addList, ArrayList<String> subList,
		ArrayList<String> spendList, ArrayList<Integer> dataList) {
		/***********************************************
		 *					BASE SETUP
		 * defining base variables
		 * making base frame and panels
		 **********************************************/
		// set-up custom colors
		Color backgroundColor = new Color(35, 41, 50);
		Color lightBlue = new Color(1, 173, 181);
		Color darkBlue = new Color(1, 155, 160);
		Color offWhite = new Color(238, 238, 238);
		Color darkGrey = new Color(58, 63, 71); // banner
		Color medGrey = new Color(71, 77, 86); // other dark
		Color grey = new Color(85, 93, 104); // other light
		Color lightGrey = new Color(98, 107, 119); // lightest

		// getting date information
		Calendar cal = Calendar.getInstance();
		int weekDay = cal.get(Calendar.DAY_OF_WEEK);

		// set-up frame and base panel
		JFrame baseFrame = new JFrame("Achievements");
		// baseFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //TODO UNCOMMENT
		JPanel basePanel = new JPanel(new BorderLayout());

		/***********************************************
		 *					LEFT SIDE
		 * defining left panel
		 * adding labels to panel
		 **********************************************/

		// set-up left info
		SpringLayout leftLayout = new SpringLayout();
		JPanel leftPanel = new JPanel(leftLayout);
		leftPanel.setBackground(backgroundColor);
		basePanel.add(leftPanel, BorderLayout.LINE_START);
		leftPanel.setPreferredSize(new Dimension(185, 700));

		// current weight box
		JLabel currentWeightLabel = new JLabel(new ImageIcon("Graphics/Info.png"));
		SpringLayout cwLayout = new SpringLayout();
		currentWeightLabel.setLayout(cwLayout);
		leftPanel.add(currentWeightLabel);
		leftLayout.putConstraint(SpringLayout.NORTH, currentWeightLabel, 100, SpringLayout.NORTH, leftPanel);
		leftLayout.putConstraint(SpringLayout.WEST, currentWeightLabel, 25, SpringLayout.WEST, leftPanel);
		// current weight label
		JLabel weightLabel = new JLabel("current weight");
		infoLabelSmall(weightLabel, offWhite);
		currentWeightLabel.add(weightLabel);
		cwLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, weightLabel, 0, SpringLayout.HORIZONTAL_CENTER, currentWeightLabel);
		cwLayout.putConstraint(SpringLayout.SOUTH, weightLabel, -8, SpringLayout.SOUTH, currentWeightLabel);
		// current weight number
		JLabel weightNumber = new JLabel(String.valueOf(dataList.get(0)));
		infoLabelBig(weightNumber, offWhite);
		currentWeightLabel.add(weightNumber);
		cwLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, weightNumber, 0, SpringLayout.HORIZONTAL_CENTER, currentWeightLabel);
		cwLayout.putConstraint(SpringLayout.NORTH, weightNumber, 0, SpringLayout.NORTH, currentWeightLabel);
		
		// goal weight box
		JLabel goalWeightLabel = new JLabel(new ImageIcon("Graphics/Info.png"));
		SpringLayout gwLayout = new SpringLayout();
		goalWeightLabel.setLayout(gwLayout);
		leftPanel.add(goalWeightLabel);
		leftLayout.putConstraint(SpringLayout.NORTH, goalWeightLabel, 25, SpringLayout.SOUTH, currentWeightLabel);
		leftLayout.putConstraint(SpringLayout.WEST, goalWeightLabel, 25, SpringLayout.WEST, leftPanel);
		// goal weight label
		JLabel goalLabel = new JLabel("weight goal");
		infoLabelSmall(goalLabel, offWhite);
		goalWeightLabel.add(goalLabel);
		gwLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, goalLabel, 0, SpringLayout.HORIZONTAL_CENTER, goalWeightLabel);
		gwLayout.putConstraint(SpringLayout.SOUTH, goalLabel, -8, SpringLayout.SOUTH, goalWeightLabel);
		// goal weight number
		JLabel goalNumber = new JLabel(String.valueOf(dataList.get(1)));
		infoLabelBig(goalNumber, offWhite);
		goalWeightLabel.add(goalNumber);
		gwLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, goalNumber, 0, SpringLayout.HORIZONTAL_CENTER, goalWeightLabel);
		gwLayout.putConstraint(SpringLayout.NORTH, goalNumber, 0, SpringLayout.NORTH, goalWeightLabel);
		
		// difference weight box
		JLabel differenceWeightLabel = new JLabel(new ImageIcon("Graphics/Info.png"));
		SpringLayout dwLayout = new SpringLayout();
		differenceWeightLabel.setLayout(dwLayout);
		leftPanel.add(differenceWeightLabel);
		leftLayout.putConstraint(SpringLayout.NORTH, differenceWeightLabel, 25, SpringLayout.SOUTH, goalWeightLabel);
		leftLayout.putConstraint(SpringLayout.WEST, differenceWeightLabel, 25, SpringLayout.WEST, leftPanel);
		// difference weight label
		JLabel differenceLabel = new JLabel("weight difference");
		infoLabelSmall(differenceLabel, offWhite);
		differenceWeightLabel.add(differenceLabel);
		dwLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, differenceLabel, 0, SpringLayout.HORIZONTAL_CENTER, differenceWeightLabel);
		dwLayout.putConstraint(SpringLayout.SOUTH, differenceLabel, -8, SpringLayout.SOUTH, differenceWeightLabel);
		// difference weight number
		JLabel differenceNumber = new JLabel(String.valueOf(dataList.get(0) - dataList.get(1)));
		infoLabelBig(differenceNumber, offWhite);
		differenceWeightLabel.add(differenceNumber);
		dwLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, differenceNumber, 0, SpringLayout.HORIZONTAL_CENTER, differenceWeightLabel);
		dwLayout.putConstraint(SpringLayout.NORTH, differenceNumber, 0, SpringLayout.NORTH, differenceWeightLabel);

		// points box
		JLabel pointsBox = new JLabel(new ImageIcon("Graphics/Info.png"));
		SpringLayout pLayout = new SpringLayout();
		pointsBox.setLayout(pLayout);
		leftPanel.add(pointsBox);
		leftLayout.putConstraint(SpringLayout.NORTH, pointsBox, 25, SpringLayout.SOUTH, differenceWeightLabel);
		leftLayout.putConstraint(SpringLayout.WEST, pointsBox, 25, SpringLayout.WEST, leftPanel);
		// points label
		JLabel pointsLabel = new JLabel("points");
		infoLabelSmall(pointsLabel, offWhite);
		pointsBox.add(pointsLabel);
		pLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, pointsLabel, 0, SpringLayout.HORIZONTAL_CENTER, pointsBox);
		pLayout.putConstraint(SpringLayout.SOUTH, pointsLabel, -8, SpringLayout.SOUTH, pointsBox);
		// points number
		JLabel pointsNumber = new JLabel(String.valueOf(dataList.get(2)));
		infoLabelBig(pointsNumber, offWhite);
		pointsBox.add(pointsNumber);
		pLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, pointsNumber, 0, SpringLayout.HORIZONTAL_CENTER, pointsBox);
		pLayout.putConstraint(SpringLayout.NORTH, pointsNumber, 0, SpringLayout.NORTH, pointsBox);

		/***********************************************
		 *					RIGHT SIDE
		 * defining right panel
		 * adding labels to panel
		 **********************************************/

		// set-up right info
		SpringLayout rightLayout = new SpringLayout();
		JPanel rightPanel = new JPanel(rightLayout);
		rightPanel.setBackground(backgroundColor);
		basePanel.add(rightPanel, BorderLayout.LINE_END);
		rightPanel.setPreferredSize(new Dimension(425, 700));

		// exercise table
		JLabel exerciseTable = new JLabel(getExerciseGraphic(weekDay));
		rightPanel.add(exerciseTable);
		rightLayout.putConstraint(SpringLayout.NORTH, exerciseTable, 115, SpringLayout.NORTH, rightPanel);
		rightLayout.putConstraint(SpringLayout.EAST, exerciseTable, -60, SpringLayout.EAST, rightPanel);
		// exercise table buttons
		JLabel tableBtnOne = new JLabel(new ImageIcon("Graphics/Mini.png"));
		rightPanel.add(tableBtnOne);
		rightLayout.putConstraint(SpringLayout.NORTH, tableBtnOne, 145, SpringLayout.NORTH, rightPanel);
		rightLayout.putConstraint(SpringLayout.WEST, tableBtnOne, 10, SpringLayout.EAST, exerciseTable);
		JLabel tableBtnTwo = new JLabel(new ImageIcon("Graphics/Mini.png"));
		exerciseTableBtn(tableBtnTwo, tableBtnOne, exerciseTable, rightPanel, rightLayout);
		JLabel tableBtnThree = new JLabel(new ImageIcon("Graphics/Mini.png"));
		exerciseTableBtn(tableBtnThree, tableBtnTwo, exerciseTable, rightPanel, rightLayout);
		JLabel tableBtnFour = new JLabel(new ImageIcon("Graphics/Mini.png"));
		exerciseTableBtn(tableBtnFour, tableBtnThree, exerciseTable, rightPanel, rightLayout);
		JLabel tableBtnFive = new JLabel(new ImageIcon("Graphics/Mini.png"));
		exerciseTableBtn(tableBtnFive, tableBtnFour, exerciseTable, rightPanel, rightLayout);
		JLabel tableBtnSix = new JLabel(new ImageIcon("Graphics/Mini.png"));
		exerciseTableBtn(tableBtnSix, tableBtnFive, exerciseTable, rightPanel, rightLayout);
		// exercise table last date
		JLabel exerciseLastDate = new JLabel();
			//TODO ADD LAST EXERCISE DATE
		exerciseLastDate.setIcon(new ImageIcon("Graphics/LastEdit.png"));
		rightPanel.add(exerciseLastDate);
		rightLayout.putConstraint(SpringLayout.NORTH, exerciseLastDate, 10, SpringLayout.SOUTH, exerciseTable);
		rightLayout.putConstraint(SpringLayout.WEST, exerciseLastDate, 0, SpringLayout.WEST, exerciseTable);
		// exercise table reset button
		JLabel exerciseResetBtn = new JLabel(new ImageIcon("Graphics/Reset.png"));
		rightPanel.add(exerciseResetBtn);
		rightLayout.putConstraint(SpringLayout.NORTH, exerciseResetBtn, 10, SpringLayout.SOUTH, exerciseTable);
		rightLayout.putConstraint(SpringLayout.EAST, exerciseResetBtn, 0, SpringLayout.EAST, exerciseTable);

		// exercise table labels
		JLabel chestLabel = new JLabel("chest");
		chestLabel.setFont(new Font("Corbel", Font.PLAIN, 14));
		chestLabel.setForeground(offWhite);
		rightPanel.add(chestLabel);
		rightLayout.putConstraint(SpringLayout.NORTH, chestLabel, 5, SpringLayout.NORTH, tableBtnOne);
		rightLayout.putConstraint(SpringLayout.EAST, chestLabel, -15, SpringLayout.WEST, exerciseTable);
		JLabel backLabel = new JLabel("back");
		exerciseTableLabel(backLabel, chestLabel, exerciseTable, rightPanel, rightLayout, offWhite);
		JLabel armsLabel = new JLabel("arms");
		exerciseTableLabel(armsLabel, backLabel, exerciseTable, rightPanel, rightLayout, offWhite);
		JLabel shouldersLabel = new JLabel("shoulders");
		exerciseTableLabel(shouldersLabel, armsLabel, exerciseTable, rightPanel, rightLayout, offWhite);
		JLabel legsLabel = new JLabel("legs");
		exerciseTableLabel(legsLabel, shouldersLabel, exerciseTable, rightPanel, rightLayout, offWhite);
		JLabel calvesLabel = new JLabel("calves");
		exerciseTableLabel(calvesLabel, legsLabel, exerciseTable, rightPanel, rightLayout, offWhite);





		//pack and display
		baseFrame.add(basePanel);
		baseFrame.setPreferredSize(new Dimension(1100, 700));
		baseFrame.pack();
		baseFrame.setLocationRelativeTo(null);
		baseFrame.setVisible(true);
		baseFrame.setResizable(false);
	}

	// setting big info label properties
	public static void infoLabelBig(JLabel label, Color color) {
		label.setFont(new Font("Corbel", Font.BOLD, 65));
		label.setForeground(color);
	}

	// setting little info label properties
	public static void infoLabelSmall(JLabel label, Color color) {
		label.setFont(new Font("Corbel", Font.PLAIN, 14));
		label.setForeground(color);
	}

	// determine which graphic to use
	public static ImageIcon getExerciseGraphic(int weekDay) {
		ImageIcon image = new ImageIcon("Graphics/Sunday.png");
		switch(weekDay) {
			case 1: 
				image = new ImageIcon("Graphics/Sunday.png");
				break;
			case 2:
				image = new ImageIcon("Graphics/Monday.png");
				break;
			case 3:
				image = new ImageIcon("Graphics/Tuesday.png");
				break;
			case 4:
				image = new ImageIcon("Graphics/Wednesday.png");
				break;
			case 5:
				image = new ImageIcon("Graphics/Thursday.png");
				break;
			case 6:
				image = new ImageIcon("Graphics/Friday.png");
				break;
			case 7:
				image = new ImageIcon("Graphics/Saturday.png");
				break;
			default:
				break;
		}
		return image;
	}

	// add exercise buttons
	public static void exerciseTableBtn(JLabel mainBtn, JLabel prevBtn, JLabel table, JPanel panel, SpringLayout layout) {
		panel.add(mainBtn);
		layout.putConstraint(SpringLayout.WEST, mainBtn, 10, SpringLayout.EAST, table);
		layout.putConstraint(SpringLayout.NORTH, mainBtn, 12, SpringLayout.SOUTH, prevBtn);
	}

	// add exercise labels
	public static void exerciseTableLabel(JLabel mainLabel, JLabel prevLabel, JLabel table, JPanel panel, SpringLayout layout, Color color) {
		mainLabel.setFont(new Font("Corbel", Font.PLAIN, 14));
		mainLabel.setForeground(color);
		panel.add(mainLabel);
		layout.putConstraint(SpringLayout.NORTH, mainLabel, 36, SpringLayout.NORTH, prevLabel);
		layout.putConstraint(SpringLayout.EAST, mainLabel, -15, SpringLayout.WEST, table);
	}
}