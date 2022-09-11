import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.*;

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

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public static void setup() {
		// variables
		File setupFile = new File("setup.txt");
		ArrayList<String> addList = new ArrayList<>(); // text, num, text, num...
		ArrayList<String> subList = new ArrayList<>();
		ArrayList<String> spendList = new ArrayList<>();
		ArrayList<Integer> dataList = new ArrayList<>(); // weight, goal, points
		String lastWorkout = "";

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
				dataList.add(Integer.parseInt(inputFile.next()));
			}
			for(int i = 0; i < 42; i++) {
				dataList.add(Integer.parseInt(inputFile.next()));
			}
			inputFile.nextLine();
			lastWorkout = inputFile.nextLine();

			inputFile.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println("File setup.txt not found.");
		}
		start(addList, subList, spendList, dataList, lastWorkout);
	}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public static void start(ArrayList<String> addList, ArrayList<String> subList,
		ArrayList<String> spendList, ArrayList<Integer> dataList, String lastWorkout) {
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
		baseFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
		leftPanel.setPreferredSize(new Dimension(180, 700));

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
		rightPanel.setPreferredSize(new Dimension(420, 700));

		// exercise table
		GridLayout exerciseLayout = new GridLayout(6, 7);
		JLabel exerciseTable = new JLabel(getExerciseGraphic(weekDay));
		exerciseTable.setLayout(exerciseLayout);
		rightPanel.add(exerciseTable);
		rightLayout.putConstraint(SpringLayout.NORTH, exerciseTable, 135, SpringLayout.NORTH, rightPanel);
		rightLayout.putConstraint(SpringLayout.EAST, exerciseTable, -60, SpringLayout.EAST, rightPanel);

		// exercise weekday label
		JLabel weekDaysLabel = new JLabel(" sun     mon     tue     wed     thur     fri     sat");
		weekDaysLabel.setFont(new Font("Corbel", Font.PLAIN, 14));
		weekDaysLabel.setForeground(offWhite);
		rightPanel.add(weekDaysLabel);
		rightLayout.putConstraint(SpringLayout.SOUTH, weekDaysLabel, 2, SpringLayout.NORTH, exerciseTable);
		rightLayout.putConstraint(SpringLayout.WEST, weekDaysLabel, 5, SpringLayout.WEST, exerciseTable);

		// exercise table buttons
		JButton tableBtnOne = new JButton(new ImageIcon("Graphics/Mini.png"));
		buttonProperties(tableBtnOne);
		rightPanel.add(tableBtnOne);
		rightLayout.putConstraint(SpringLayout.NORTH, tableBtnOne, 138, SpringLayout.NORTH, rightPanel);
		rightLayout.putConstraint(SpringLayout.WEST, tableBtnOne, -3, SpringLayout.EAST, exerciseTable);
		JButton tableBtnTwo = new JButton(new ImageIcon("Graphics/Mini.png"));
		buttonProperties(tableBtnTwo);
		exerciseTableBtn(tableBtnTwo, tableBtnOne, exerciseTable, rightPanel, rightLayout);
		JButton tableBtnThree = new JButton(new ImageIcon("Graphics/Mini.png"));
		buttonProperties(tableBtnThree);
		exerciseTableBtn(tableBtnThree, tableBtnTwo, exerciseTable, rightPanel, rightLayout);
		JButton tableBtnFour = new JButton(new ImageIcon("Graphics/Mini.png"));
		buttonProperties(tableBtnFour);
		exerciseTableBtn(tableBtnFour, tableBtnThree, exerciseTable, rightPanel, rightLayout);
		JButton tableBtnFive = new JButton(new ImageIcon("Graphics/Mini.png"));
		buttonProperties(tableBtnFive);
		exerciseTableBtn(tableBtnFive, tableBtnFour, exerciseTable, rightPanel, rightLayout);
		JButton tableBtnSix = new JButton(new ImageIcon("Graphics/Mini.png"));
		buttonProperties(tableBtnSix);
		exerciseTableBtn(tableBtnSix, tableBtnFive, exerciseTable, rightPanel, rightLayout);

		// exercise table last date
		JLabel exerciseLastDate = new JLabel();
		BorderLayout datLayout = new BorderLayout();
		exerciseLastDate.setLayout(datLayout);
		exerciseLastDate.setIcon(new ImageIcon("Graphics/LastEdit.png"));
		rightPanel.add(exerciseLastDate);
		rightLayout.putConstraint(SpringLayout.NORTH, exerciseLastDate, 10, SpringLayout.SOUTH, exerciseTable);
		rightLayout.putConstraint(SpringLayout.WEST, exerciseLastDate, 0, SpringLayout.WEST, exerciseTable);
		JLabel lastDate = new JLabel("        " + lastWorkout);
		lastDate.setFont(new Font("Corbel", Font.PLAIN, 16));
		lastDate.setForeground(offWhite);
		exerciseLastDate.add(lastDate, BorderLayout.CENTER);
		JLabel lastLabel = new JLabel("last edit");
		lastLabel.setFont(new Font("Corbel", Font.PLAIN, 14));
		lastLabel.setForeground(offWhite);


		// exercise table reset button
		BorderLayout resetLayout = new BorderLayout();
		JButton exerciseResetBtn = new JButton(new ImageIcon("Graphics/Reset.png"));
		buttonProperties(exerciseResetBtn);
		exerciseResetBtn.setLayout(resetLayout);
		rightPanel.add(exerciseResetBtn);
		rightLayout.putConstraint(SpringLayout.NORTH, exerciseResetBtn, 9, SpringLayout.SOUTH, exerciseTable);
		rightLayout.putConstraint(SpringLayout.EAST, exerciseResetBtn, 0, SpringLayout.EAST, exerciseTable);
		JLabel resetLabel = new JLabel("reset");
		resetLabel.setFont(new Font("Corbel", Font.PLAIN, 14));
		resetLabel.setForeground(medGrey);
		exerciseResetBtn.add(resetLabel, BorderLayout.CENTER);

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
		rightPanel.add(lastLabel);
		rightLayout.putConstraint(SpringLayout.EAST, lastLabel, 0, SpringLayout.EAST, calvesLabel);
		rightLayout.putConstraint(SpringLayout.NORTH, lastLabel, 21, SpringLayout.SOUTH, calvesLabel);

		// exercise table checks
		displayChecks(dataList, exerciseTable, exerciseLayout);

		// exit button
		JLabel exitBtn = new JLabel(new ImageIcon("Graphics/Exit.png"));
		BorderLayout exitLayout = new BorderLayout();
		exitBtn.setLayout(exitLayout);
		JButton exit = new JButton("exit");
		buttonProperties(exit);
		exit.setHorizontalAlignment(JLabel.CENTER);
		exit.setVerticalAlignment(JLabel.CENTER);
		exit.setForeground(lightGrey);
		exit.setFont(new Font("Corbel", Font.PLAIN, 20));
		exitBtn.add(exit, BorderLayout.CENTER);
		rightPanel.add(exitBtn);
		rightLayout.putConstraint(SpringLayout.SOUTH, exitBtn, -15, SpringLayout.SOUTH, rightPanel);
		rightLayout.putConstraint(SpringLayout.EAST, exitBtn, -25, SpringLayout.EAST, rightPanel);


		/***********************************************
		 *					CENTER
		 * defining center panel
		 * adding labels to panel
		 **********************************************/

		// center panel
		SpringLayout centerLayout = new SpringLayout();
		JPanel centerPanel = new JPanel(centerLayout);
		centerPanel.setBackground(backgroundColor);
		basePanel.add(centerPanel, BorderLayout.CENTER);

		// welcome back label
		JLabel welcomeLabel = new JLabel(new ImageIcon("Graphics/Top.png"));
		BorderLayout welcomeLayout = new BorderLayout();
		welcomeLabel.setLayout(welcomeLayout);
		JLabel welcome = new JLabel("Welcome Back");
		welcome.setHorizontalAlignment(JLabel.CENTER);
		welcome.setVerticalAlignment(JLabel.CENTER);
		welcome.setForeground(offWhite);
		welcome.setFont(new Font("Corbel", Font.PLAIN, 20));
		welcomeLabel.add(welcome, BorderLayout.CENTER);
		centerPanel.add(welcomeLabel);
		centerLayout.putConstraint(SpringLayout.NORTH, welcomeLabel, 15, SpringLayout.NORTH, centerPanel);
		centerLayout.putConstraint(SpringLayout.WEST, welcomeLabel, 100, SpringLayout.WEST, centerPanel);

		// date label
		JLabel dateLabel = new JLabel(new ImageIcon("Graphics/Top.png"));
		BorderLayout dateLayout = new BorderLayout();
		dateLabel.setLayout(dateLayout);
		Date dateToday = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMMM yyyy");
		JLabel date = new JLabel(dateFormat.format(dateToday));
		date.setHorizontalAlignment(JLabel.CENTER);
		date.setVerticalAlignment(JLabel.CENTER);
		date.setForeground(offWhite);
		date.setFont(new Font("Corbel", Font.PLAIN, 20));
		dateLabel.add(date, BorderLayout.CENTER);
		centerPanel.add(dateLabel);
		centerLayout.putConstraint(SpringLayout.NORTH, dateLabel, 15, SpringLayout.NORTH, centerPanel);
		centerLayout.putConstraint(SpringLayout.WEST, dateLabel, 25, SpringLayout.EAST, welcomeLabel);

		// columns
		SpringLayout addLayout = new SpringLayout();
		JLabel addColumn = new JLabel(getColumnsGraphic(Integer.parseInt(addList.get(0))));
		addColumn.setLayout(addLayout);
		SpringLayout subLayout = new SpringLayout();
		JLabel subColumn = new JLabel(getColumnsGraphic(Integer.parseInt(subList.get(0))));
		subColumn.setLayout(subLayout);
		SpringLayout spendLayout = new SpringLayout();
		JLabel spendColumn = new JLabel(getColumnsGraphic(Integer.parseInt(spendList.get(0))));
		spendColumn.setLayout(spendLayout);
		getColumnLayout(Integer.parseInt(addList.get(0)), Integer.parseInt(subList.get(0)), Integer.parseInt(spendList.get(0)), addColumn, subColumn, spendColumn, centerPanel, centerLayout, leftPanel);
		labelColumns(addColumn, subColumn, spendColumn, centerPanel, centerLayout, offWhite);

		// update weight
		JLabel updateWeightBtn = new JLabel(new ImageIcon("Graphics/MainButton.png"));
		BorderLayout updateLayout = new BorderLayout();
		updateWeightBtn.setLayout(updateLayout);
		JButton updateWeight = new JButton("update weight");
		buttonProperties(updateWeight);
		updateWeightBtn.add(updateWeight, BorderLayout.CENTER);
		centerPanel.add(updateWeightBtn);
		centerLayout.putConstraint(SpringLayout.SOUTH, updateWeightBtn, -15, SpringLayout.SOUTH, centerPanel);
		centerLayout.putConstraint(SpringLayout.WEST, updateWeightBtn, 0, SpringLayout.WEST, addColumn);

		// update goal
		JLabel updateGoalBtn = new JLabel(new ImageIcon("Graphics/MainButton.png"));
		BorderLayout goalLayout = new BorderLayout();
		updateGoalBtn.setLayout(goalLayout);
		JButton updateGoal = new JButton("update goal");
		buttonProperties(updateGoal);
		updateGoalBtn.add(updateGoal, BorderLayout.CENTER);
		centerPanel.add(updateGoalBtn);
		centerLayout.putConstraint(SpringLayout.SOUTH, updateGoalBtn, -15, SpringLayout.SOUTH, centerPanel);
		centerLayout.putConstraint(SpringLayout.WEST, updateGoalBtn, 15, SpringLayout.EAST, updateWeightBtn);

		// add action buttons
		JButton addTen = new JButton();
		JButton addNine = new JButton();
		JButton addEight = new JButton();
		JButton addSeven = new JButton();
		JButton addSix = new JButton();
		JButton addFive = new JButton();
		JButton addFour = new JButton();
		JButton addThree = new JButton();
		JButton addTwo = new JButton();
		JButton addOne = new JButton();
		switch(Integer.parseInt(addList.get(0))) {
			case 10:
				formatActionButton(addTen, 10, addList);
				addColumn.add(addTen);
				addLayout.putConstraint(SpringLayout.NORTH, addTen, 355, SpringLayout.NORTH, addColumn);
				addLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, addTen, 0, SpringLayout.HORIZONTAL_CENTER, addColumn);
			case 9:
				formatActionButton(addNine, 9, addList);
				addColumn.add(addNine);
				addLayout.putConstraint(SpringLayout.NORTH, addNine, 319, SpringLayout.NORTH, addColumn);
				addLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, addNine, 0, SpringLayout.HORIZONTAL_CENTER, addColumn);
			case 8:
				formatActionButton(addEight, 8, addList);
				addColumn.add(addEight);
				addLayout.putConstraint(SpringLayout.NORTH, addEight, 283, SpringLayout.NORTH, addColumn);
				addLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, addEight, 0, SpringLayout.HORIZONTAL_CENTER, addColumn);
			case 7:
				formatActionButton(addSeven, 7, addList);
				addColumn.add(addSeven);
				addLayout.putConstraint(SpringLayout.NORTH, addSeven, 247, SpringLayout.NORTH, addColumn);
				addLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, addSeven, 0, SpringLayout.HORIZONTAL_CENTER, addColumn);
			case 6:
				formatActionButton(addSix, 6, addList);
				addColumn.add(addSix);
				addLayout.putConstraint(SpringLayout.NORTH, addSix, 211, SpringLayout.NORTH, addColumn);
				addLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, addSix, 0, SpringLayout.HORIZONTAL_CENTER, addColumn);
			case 5:
				formatActionButton(addFive, 5, addList);
				addColumn.add(addFive);
				addLayout.putConstraint(SpringLayout.NORTH, addFive, 174, SpringLayout.NORTH, addColumn);
				addLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, addFive, 0, SpringLayout.HORIZONTAL_CENTER, addColumn);
			case 4:
				formatActionButton(addFour, 4, addList);
				addColumn.add(addFour);
				addLayout.putConstraint(SpringLayout.NORTH, addFour, 138, SpringLayout.NORTH, addColumn);
				addLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, addFour, 0, SpringLayout.HORIZONTAL_CENTER, addColumn);
			case 3:
				formatActionButton(addThree, 3, addList);
				addColumn.add(addThree);
				addLayout.putConstraint(SpringLayout.NORTH, addThree, 102, SpringLayout.NORTH, addColumn);
				addLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, addThree, 0, SpringLayout.HORIZONTAL_CENTER, addColumn);
			case 2:
				formatActionButton(addTwo, 2, addList);
				addColumn.add(addTwo);
				addLayout.putConstraint(SpringLayout.NORTH, addTwo, 66, SpringLayout.NORTH, addColumn);
				addLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, addTwo, 0, SpringLayout.HORIZONTAL_CENTER, addColumn);
			default:
				formatActionButton(addOne, 1, addList);
				addColumn.add(addOne);
				addLayout.putConstraint(SpringLayout.NORTH, addOne, 30, SpringLayout.NORTH, addColumn);
				addLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, addOne, 0, SpringLayout.HORIZONTAL_CENTER, addColumn);
				break;
		}

		// sub action buttons
		JButton subTen = new JButton();
		JButton subNine = new JButton();
		JButton subEight = new JButton();
		JButton subSeven = new JButton();
		JButton subSix = new JButton();
		JButton subFive = new JButton();
		JButton subFour = new JButton();
		JButton subThree = new JButton();
		JButton subTwo = new JButton();
		JButton subOne = new JButton();
		switch(Integer.parseInt(subList.get(0))) {
			case 10:
				formatActionButton(subTen, 10, subList);
				subColumn.add(subTen);
				subLayout.putConstraint(SpringLayout.NORTH, subTen, 355, SpringLayout.NORTH, subColumn);
				subLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, subTen, 0, SpringLayout.HORIZONTAL_CENTER, subColumn);
			case 9:
				formatActionButton(subNine, 9, subList);
				subColumn.add(subNine);
				subLayout.putConstraint(SpringLayout.NORTH, subNine, 319, SpringLayout.NORTH, subColumn);
				subLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, subNine, 0, SpringLayout.HORIZONTAL_CENTER, subColumn);
			case 8:
				formatActionButton(subEight, 8, subList);
				subColumn.add(subEight);
				subLayout.putConstraint(SpringLayout.NORTH, subEight, 283, SpringLayout.NORTH, subColumn);
				subLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, subEight, 0, SpringLayout.HORIZONTAL_CENTER, subColumn);
			case 7:
				formatActionButton(subSeven, 7, subList);
				subColumn.add(subSeven);
				subLayout.putConstraint(SpringLayout.NORTH, subSeven, 247, SpringLayout.NORTH, subColumn);
				subLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, subSeven, 0, SpringLayout.HORIZONTAL_CENTER, subColumn);
			case 6:
				formatActionButton(subSix, 6, subList);
				subColumn.add(subSix);
				subLayout.putConstraint(SpringLayout.NORTH, subSix, 211, SpringLayout.NORTH, subColumn);
				subLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, subSix, 0, SpringLayout.HORIZONTAL_CENTER, subColumn);
			case 5:
				formatActionButton(subFive, 5, subList);
				subColumn.add(subFive);
				subLayout.putConstraint(SpringLayout.NORTH, subFive, 174, SpringLayout.NORTH, subColumn);
				subLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, subFive, 0, SpringLayout.HORIZONTAL_CENTER, subColumn);
			case 4:
				formatActionButton(subFour, 4, subList);
				subColumn.add(subFour);
				subLayout.putConstraint(SpringLayout.NORTH, subFour, 138, SpringLayout.NORTH, subColumn);
				subLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, subFour, 0, SpringLayout.HORIZONTAL_CENTER, subColumn);
			case 3:
				formatActionButton(subThree, 3, subList);
				subColumn.add(subThree);
				subLayout.putConstraint(SpringLayout.NORTH, subThree, 102, SpringLayout.NORTH, subColumn);
				subLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, subThree, 0, SpringLayout.HORIZONTAL_CENTER, subColumn);
			case 2:
				formatActionButton(subTwo, 2, subList);
				subColumn.add(subTwo);
				subLayout.putConstraint(SpringLayout.NORTH, subTwo, 66, SpringLayout.NORTH, subColumn);
				subLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, subTwo, 0, SpringLayout.HORIZONTAL_CENTER, subColumn);
			default:
				formatActionButton(subOne, 1, subList);
				subColumn.add(subOne);
				subLayout.putConstraint(SpringLayout.NORTH, subOne, 30, SpringLayout.NORTH, subColumn);
				subLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, subOne, 0, SpringLayout.HORIZONTAL_CENTER, subColumn);
				break;
		}

		// spend action buttons
		JButton spendTen = new JButton();
		JButton spendNine = new JButton();
		JButton spendEight = new JButton();
		JButton spendSeven = new JButton();
		JButton spendSix = new JButton();
		JButton spendFive = new JButton();
		JButton spendFour = new JButton();
		JButton spendThree = new JButton();
		JButton spendTwo = new JButton();
		JButton spendOne = new JButton();
		switch(Integer.parseInt(spendList.get(0))) {
			case 10:
				formatActionButton(spendTen, 10, spendList);
				spendColumn.add(spendTen);
				spendLayout.putConstraint(SpringLayout.NORTH, spendTen, 355, SpringLayout.NORTH, spendColumn);
				spendLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, spendTen, 0, SpringLayout.HORIZONTAL_CENTER, spendColumn);
			case 9:
				formatActionButton(spendNine, 9, spendList);
				spendColumn.add(spendNine);
				spendLayout.putConstraint(SpringLayout.NORTH, spendNine, 319, SpringLayout.NORTH, spendColumn);
				spendLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, spendNine, 0, SpringLayout.HORIZONTAL_CENTER, spendColumn);
			case 8:
				formatActionButton(spendEight, 8, spendList);
				spendColumn.add(spendEight);
				spendLayout.putConstraint(SpringLayout.NORTH, spendEight, 283, SpringLayout.NORTH, spendColumn);
				spendLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, spendEight, 0, SpringLayout.HORIZONTAL_CENTER, spendColumn);
			case 7:
				formatActionButton(spendSeven, 7, spendList);
				spendColumn.add(spendSeven);
				spendLayout.putConstraint(SpringLayout.NORTH, spendSeven, 247, SpringLayout.NORTH, spendColumn);
				spendLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, spendSeven, 0, SpringLayout.HORIZONTAL_CENTER, spendColumn);
			case 6:
				formatActionButton(spendSix, 6, spendList);
				spendColumn.add(spendSix);
				spendLayout.putConstraint(SpringLayout.NORTH, spendSix, 211, SpringLayout.NORTH, spendColumn);
				spendLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, spendSix, 0, SpringLayout.HORIZONTAL_CENTER, spendColumn);
			case 5:
				formatActionButton(spendFive, 5, spendList);
				spendColumn.add(spendFive);
				spendLayout.putConstraint(SpringLayout.NORTH, spendFive, 174, SpringLayout.NORTH, spendColumn);
				spendLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, spendFive, 0, SpringLayout.HORIZONTAL_CENTER, spendColumn);
			case 4:
				formatActionButton(spendFour, 4, spendList);
				spendColumn.add(spendFour);
				spendLayout.putConstraint(SpringLayout.NORTH, spendFour, 138, SpringLayout.NORTH, spendColumn);
				spendLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, spendFour, 0, SpringLayout.HORIZONTAL_CENTER, spendColumn);
			case 3:
				formatActionButton(spendThree, 3, spendList);
				spendColumn.add(spendThree);
				spendLayout.putConstraint(SpringLayout.NORTH, spendThree, 102, SpringLayout.NORTH, spendColumn);
				spendLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, spendThree, 0, SpringLayout.HORIZONTAL_CENTER, spendColumn);
			case 2:
				formatActionButton(spendTwo, 2, spendList);
				spendColumn.add(spendTwo);
				spendLayout.putConstraint(SpringLayout.NORTH, spendTwo, 66, SpringLayout.NORTH, spendColumn);
				spendLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, spendTwo, 0, SpringLayout.HORIZONTAL_CENTER, spendColumn);
			default:
				formatActionButton(spendOne, 1, spendList);
				spendColumn.add(spendOne);
				spendLayout.putConstraint(SpringLayout.NORTH, spendOne, 30, SpringLayout.NORTH, spendColumn);
				spendLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, spendOne, 0, SpringLayout.HORIZONTAL_CENTER, spendColumn);
				break;
		}


		/**********************************************
		*				PACK AND DISPLAY
		* adding top/bottom to basePanel
		* assing basePanel to baseFrame
		* packing and setting visible
		**********************************************/

		//pack and display
		baseFrame.add(basePanel);
		if(Integer.parseInt(addList.get(0)) + Integer.parseInt(subList.get(0)) + Integer.parseInt(spendList.get(0)) > 21) {
			baseFrame.setPreferredSize(new Dimension(1250, 700));
		}
		else {
			baseFrame.setPreferredSize(new Dimension(1100, 700));
		}
		baseFrame.pack();
		baseFrame.setLocationRelativeTo(null);
		baseFrame.setVisible(true);
		baseFrame.setResizable(false);

		/***********************************************
		 *				ACTION LISTENERS
		 * hover listeners
		 * press listeners
		 **********************************************/

		MouseListener genericListener = new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				if(evt.getSource() == updateWeight) {
					updateWeight.setIcon(new ImageIcon("Graphics/MainButtonHover.png"));
				}
				else if(evt.getSource() == updateGoal) {
					updateGoal.setIcon(new ImageIcon("Graphics/MainButtonHover.png"));
				}
				else if(evt.getSource() == exerciseResetBtn) {
					exerciseResetBtn.setIcon(new ImageIcon("Graphics/ResetHover.png"));
				}
				else if(evt.getSource() == exit) {
					exit.setIcon(new ImageIcon("Graphics/ExitHover.png"));
				}
				else if(evt.getSource() == tableBtnOne) {
					tableBtnOne.setIcon(new ImageIcon("Graphics/MiniHover.png"));
				}
				else if(evt.getSource() == tableBtnTwo) {
					tableBtnTwo.setIcon(new ImageIcon("Graphics/MiniHover.png"));
				}
				else if(evt.getSource() == tableBtnThree) {
					tableBtnThree.setIcon(new ImageIcon("Graphics/MiniHover.png"));
				}
				else if(evt.getSource() == tableBtnFour) {
					tableBtnFour.setIcon(new ImageIcon("Graphics/MiniHover.png"));
				}
				else if(evt.getSource() == tableBtnFive) {
					tableBtnFive.setIcon(new ImageIcon("Graphics/MiniHover.png"));
				}
				else if(evt.getSource() == tableBtnSix) {
					tableBtnSix.setIcon(new ImageIcon("Graphics/MiniHover.png"));
				}
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
				if(evt.getSource() == updateWeight) {
					updateWeight.setIcon(new ImageIcon("Graphics/MainButton.png"));
				}
				else if(evt.getSource() == updateGoal){
					updateGoal.setIcon(new ImageIcon("Graphics/MainButton.png"));
				}
				else if(evt.getSource() == exerciseResetBtn) {
					exerciseResetBtn.setIcon(new ImageIcon("Graphics/Reset.png"));
				}
				else if(evt.getSource() == exit) {
					exit.setIcon(new ImageIcon("Graphics/Exit.png"));
				}
				else if(evt.getSource() == tableBtnOne) {
					tableBtnOne.setIcon(new ImageIcon("Graphics/Mini.png"));
				}
				else if(evt.getSource() == tableBtnTwo) {
					tableBtnTwo.setIcon(new ImageIcon("Graphics/Mini.png"));
				}
				else if(evt.getSource() == tableBtnThree) {
					tableBtnThree.setIcon(new ImageIcon("Graphics/Mini.png"));
				}
				else if(evt.getSource() == tableBtnFour) {
					tableBtnFour.setIcon(new ImageIcon("Graphics/Mini.png"));
				}
				else if(evt.getSource() == tableBtnFive) {
					tableBtnFive.setIcon(new ImageIcon("Graphics/Mini.png"));
				}
				else if(evt.getSource() == tableBtnSix) {
					tableBtnSix.setIcon(new ImageIcon("Graphics/Mini.png"));
				}
			}
		};

		// update weight listeners
		updateWeight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayDialog("updateweight", dataList, weightNumber, currentWeightLabel, baseFrame, differenceNumber, differenceWeightLabel);
			}
		});
		updateWeight.addMouseListener(genericListener);

		// update goal listeners
		updateGoal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayDialog("updategoal", dataList, goalNumber, goalWeightLabel, baseFrame, differenceNumber, differenceWeightLabel);
			}
		});
		updateGoal.addMouseListener(genericListener);

		// exercise reset listeners
		exerciseResetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateLastWorkout(dataList, lastWorkout, lastDate, exerciseLastDate, dateToday, dateFormat);
				for(int i = 3; i < dataList.size(); i++) {
					dataList.set(i, 0);
				}
				displayChecks(dataList, exerciseTable, exerciseLayout);
			}
		});
		exerciseResetBtn.addMouseListener(genericListener);

		// exercise buttons listeners
		tableBtnOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeCheck(1, weekDay, dataList);
				displayChecks(dataList, exerciseTable, exerciseLayout);
				updateLastWorkout(dataList, lastWorkout, lastDate, exerciseLastDate, dateToday, dateFormat);
			}
		});
		tableBtnOne.addMouseListener(genericListener);
		tableBtnTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeCheck(2, weekDay, dataList);
				displayChecks(dataList, exerciseTable, exerciseLayout);
				updateLastWorkout(dataList, lastWorkout, lastDate, exerciseLastDate, dateToday, dateFormat);
			}
		});
		tableBtnTwo.addMouseListener(genericListener);
		tableBtnThree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeCheck(3, weekDay, dataList);
				displayChecks(dataList, exerciseTable, exerciseLayout);
				updateLastWorkout(dataList, lastWorkout, lastDate, exerciseLastDate, dateToday, dateFormat);
			}
		});
		tableBtnThree.addMouseListener(genericListener);
		tableBtnFour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeCheck(4, weekDay, dataList);
				displayChecks(dataList, exerciseTable, exerciseLayout);
				updateLastWorkout(dataList, lastWorkout, lastDate, exerciseLastDate, dateToday, dateFormat);
			}
		});
		tableBtnFour.addMouseListener(genericListener);
		tableBtnFive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeCheck(5, weekDay, dataList);
				displayChecks(dataList, exerciseTable, exerciseLayout);
				updateLastWorkout(dataList, lastWorkout, lastDate, exerciseLastDate, dateToday, dateFormat);
			}
		});
		tableBtnFive.addMouseListener(genericListener);
		tableBtnSix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeCheck(6, weekDay, dataList);
				displayChecks(dataList, exerciseTable, exerciseLayout);
				updateLastWorkout(dataList, lastWorkout, lastDate, exerciseLastDate, dateToday, dateFormat);
			}
		});
		tableBtnSix.addMouseListener(genericListener);

		// add action listeners
		addOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePoints(addList, 1*2, 1, dataList, pointsLabel, pointsNumber);
			}
		});
		addTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePoints(addList, 2*2, 1, dataList, pointsLabel, pointsNumber);
			}
		});
		addThree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePoints(addList, 3*2, 1, dataList, pointsLabel, pointsNumber);
			}
		});
		addFour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePoints(addList, 4*2, 1, dataList, pointsLabel, pointsNumber);
			}
		});
		addFive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePoints(addList, 5*2, 1, dataList, pointsLabel, pointsNumber);
			}
		});
		addSix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePoints(addList, 6*2, 1, dataList, pointsLabel, pointsNumber);
			}
		});
		addSeven.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePoints(addList, 7*2, 1, dataList, pointsLabel, pointsNumber);
			}
		});
		addEight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePoints(addList, 8*2, 1, dataList, pointsLabel, pointsNumber);
			}
		});
		addNine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePoints(addList, 9*2, 1, dataList, pointsLabel, pointsNumber);
			}
		});
		addTen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePoints(addList, 10*2, 1, dataList, pointsLabel, pointsNumber);
			}
		});

		// sub action listeners
		subOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePoints(subList, 1*2, -1, dataList, pointsLabel, pointsNumber);
			}
		});
		subTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePoints(subList, 2*2, -1, dataList, pointsLabel, pointsNumber);
			}
		});
		subThree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePoints(subList, 3*2, -1, dataList, pointsLabel, pointsNumber);
			}
		});
		subFour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePoints(subList, 4*2, -1, dataList, pointsLabel, pointsNumber);
			}
		});
		subFive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePoints(subList, 5*2, -1, dataList, pointsLabel, pointsNumber);
			}
		});
		subSix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePoints(subList, 6*2, -1, dataList, pointsLabel, pointsNumber);
			}
		});
		subSeven.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePoints(subList, 7*2, -1, dataList, pointsLabel, pointsNumber);
			}
		});
		subEight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePoints(subList, 8*2, -1, dataList, pointsLabel, pointsNumber);
			}
		});
		subNine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePoints(subList, 9*2, -1, dataList, pointsLabel, pointsNumber);
			}
		});
		subTen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePoints(subList, 10*2, -1, dataList, pointsLabel, pointsNumber);
			}
		});

		// spend action listeners
		spendOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePoints(spendList, 1*2, -1, dataList, pointsLabel, pointsNumber);
			}
		});
		spendTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePoints(spendList, 2*2, -1, dataList, pointsLabel, pointsNumber);
			}
		});
		spendThree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePoints(spendList, 3*2, -1, dataList, pointsLabel, pointsNumber);
			}
		});
		spendFour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePoints(spendList, 4*2, -1, dataList, pointsLabel, pointsNumber);
			}
		});
		spendFive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePoints(spendList, 5*2, -1, dataList, pointsLabel, pointsNumber);
			}
		});
		spendSix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePoints(spendList, 6*2, -1, dataList, pointsLabel, pointsNumber);
			}
		});
		spendSeven.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePoints(spendList, 7*2, -1, dataList, pointsLabel, pointsNumber);
			}
		});
		spendEight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePoints(spendList, 8*2, -1, dataList, pointsLabel, pointsNumber);
			}
		});
		spendNine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePoints(spendList, 9*2, -1, dataList, pointsLabel, pointsNumber);
			}
		});
		spendTen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePoints(spendList, 10*2, -1, dataList, pointsLabel, pointsNumber);
			}
		});

		// close listeners
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveExit(addList, subList, spendList, dataList, lastWorkout, baseFrame);
			}
		});
		exit.addMouseListener(genericListener);

		baseFrame.addWindowListener(new WindowListener() {
			public void windowClosing(WindowEvent e) {
				saveExit(addList, subList, spendList, dataList, lastWorkout, baseFrame);
			}
			public void windowDeactivated(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowClosed(WindowEvent e) {}
			public void windowOpened(WindowEvent e) {}
		});
	}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	// setting big info label properties
	public static void infoLabelBig(JLabel label, Color color) {
		label.setFont(new Font("Corbel", Font.BOLD, 65));
		label.setForeground(color);
	}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	// setting little info label properties
	public static void infoLabelSmall(JLabel label, Color color) {
		label.setFont(new Font("Corbel", Font.PLAIN, 14));
		label.setForeground(color);
	}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	// determine which graphic to use for exercise table
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

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	// add exercise buttons
	public static void exerciseTableBtn(JButton mainBtn, JButton prevBtn, JLabel table, JPanel panel, SpringLayout layout) {
		panel.add(mainBtn);
		layout.putConstraint(SpringLayout.WEST, mainBtn, -3, SpringLayout.EAST, table);
		layout.putConstraint(SpringLayout.NORTH, mainBtn, 1, SpringLayout.SOUTH, prevBtn);
	}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	// add exercise labels
	public static void exerciseTableLabel(JLabel mainLabel, JLabel prevLabel, JLabel table, JPanel panel, SpringLayout layout, Color color) {
		mainLabel.setFont(new Font("Corbel", Font.PLAIN, 14));
		mainLabel.setForeground(color);
		panel.add(mainLabel);
		layout.putConstraint(SpringLayout.NORTH, mainLabel, 36, SpringLayout.NORTH, prevLabel);
		layout.putConstraint(SpringLayout.EAST, mainLabel, -15, SpringLayout.WEST, table);
	}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	// determine which graphic to use for columns in center
	public static ImageIcon getColumnsGraphic(int x) {
		ImageIcon image = new ImageIcon("Graphics/One.png");
		switch(x) {
			case 1:
				image = new ImageIcon("Graphics/One.png");
				break;
			case 2:
				image = new ImageIcon("Graphics/Two.png");
				break;
			case 3:
				image = new ImageIcon("Graphics/Three.png");
				break;
			case 4:
				image = new ImageIcon("Graphics/Four.png");
				break;
			case 5:
				image = new ImageIcon("Graphics/Five.png");
				break;
			case 6:
				image = new ImageIcon("Graphics/Six.png");
				break;
			case 7:
				image = new ImageIcon("Graphics/Seven.png");
				break;
			case 8:
				image = new ImageIcon("Graphics/Eight.png");
				break;
			case 9:
				image = new ImageIcon("Graphics/Nine.png");
				break;
			case 10:
				image = new ImageIcon("Graphics/Ten.png");
				break;
			default:
				break;
		}
		return image;
	}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	// determine and set appropriate layout for center columns
	public static void getColumnLayout(int add, int sub, int spend, JLabel addLabel, JLabel subLabel, JLabel spendLabel, JPanel panel, SpringLayout layout, JPanel leftPanel) {
		if(add + sub <= 11) { // add and sub in same column
			panel.add(addLabel);
			layout.putConstraint(SpringLayout.NORTH, addLabel, 100, SpringLayout.NORTH, panel);
			layout.putConstraint(SpringLayout.WEST, addLabel, 35, SpringLayout.WEST, leftPanel);

			panel.add(subLabel);
			layout.putConstraint(SpringLayout.NORTH, subLabel, 40, SpringLayout.SOUTH, addLabel);
			layout.putConstraint(SpringLayout.WEST, subLabel, 35, SpringLayout.WEST, leftPanel);

			panel.add(spendLabel);
			layout.putConstraint(SpringLayout.NORTH, spendLabel, 100, SpringLayout.NORTH, panel);
			layout.putConstraint(SpringLayout.WEST, spendLabel, 50, SpringLayout.EAST, addLabel);
		}
		else if(sub + spend <= 11) { // sub and spend in same column
			panel.add(addLabel);
			layout.putConstraint(SpringLayout.NORTH, addLabel, 100, SpringLayout.NORTH, panel);
			layout.putConstraint(SpringLayout.WEST, addLabel, 35, SpringLayout.WEST, leftPanel);

			panel.add(spendLabel);
			layout.putConstraint(SpringLayout.NORTH, spendLabel, 100, SpringLayout.NORTH, panel);
			layout.putConstraint(SpringLayout.WEST, spendLabel, 50, SpringLayout.EAST, addLabel);

			panel.add(subLabel);
			layout.putConstraint(SpringLayout.NORTH, subLabel, 40, SpringLayout.SOUTH, spendLabel);
			layout.putConstraint(SpringLayout.WEST, subLabel, 0, SpringLayout.WEST, spendLabel);
		}
		else if(add + spend <= 11) { // add and spend in same column
			panel.add(addLabel);
			layout.putConstraint(SpringLayout.NORTH, addLabel, 100, SpringLayout.NORTH, panel);
			layout.putConstraint(SpringLayout.WEST, addLabel, 35, SpringLayout.WEST, leftPanel);

			panel.add(spendLabel);
			layout.putConstraint(SpringLayout.NORTH, spendLabel, 40, SpringLayout.SOUTH, addLabel);
			layout.putConstraint(SpringLayout.WEST, spendLabel, 35, SpringLayout.WEST, leftPanel);

			panel.add(subLabel);
			layout.putConstraint(SpringLayout.NORTH, subLabel, 100, SpringLayout.NORTH, panel);
			layout.putConstraint(SpringLayout.WEST, subLabel, 50, SpringLayout.EAST, addLabel);
		}
		else { // all in separate columns
			panel.add(addLabel);
			layout.putConstraint(SpringLayout.NORTH, addLabel, 100, SpringLayout.NORTH, panel);
			layout.putConstraint(SpringLayout.WEST, addLabel, 15, SpringLayout.WEST, leftPanel);

			panel.add(subLabel);
			layout.putConstraint(SpringLayout.NORTH, subLabel, 100, SpringLayout.NORTH, panel);
			layout.putConstraint(SpringLayout.WEST, subLabel, 20, SpringLayout.EAST, addLabel);

			panel.add(spendLabel);
			layout.putConstraint(SpringLayout.NORTH, spendLabel, 100, SpringLayout.NORTH, panel);
			layout.putConstraint(SpringLayout.WEST, spendLabel, 20, SpringLayout.EAST, subLabel);
		}
	}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public static void labelColumns(JLabel addColumn, JLabel subColumn, JLabel spendColumn, JPanel panel, SpringLayout layout, Color color) {
		JLabel add = new JLabel("add actions");
		add.setFont(new Font("Corbel", Font.PLAIN, 14));
		add.setForeground(color);
		panel.add(add);
		layout.putConstraint(SpringLayout.SOUTH, add, 0, SpringLayout.NORTH, addColumn);
		layout.putConstraint(SpringLayout.WEST, add, 5, SpringLayout.WEST, addColumn);

		JLabel sub = new JLabel("minus actions");
		sub.setFont(new Font("Corbel", Font.PLAIN, 14));
		sub.setForeground(color);
		panel.add(sub);
		layout.putConstraint(SpringLayout.SOUTH, sub, 0, SpringLayout.NORTH, subColumn);
		layout.putConstraint(SpringLayout.WEST, sub, 5, SpringLayout.WEST, subColumn);

		JLabel spend = new JLabel("spend actions");
		spend.setFont(new Font("Corbel", Font.PLAIN, 14));
		spend.setForeground(color);
		panel.add(spend);
		layout.putConstraint(SpringLayout.SOUTH, spend, 0, SpringLayout.NORTH, spendColumn);
		layout.putConstraint(SpringLayout.WEST, spend, 5, SpringLayout.WEST, spendColumn);
	}

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public static void displayDialog(String name, ArrayList<Integer> data, JLabel label, JLabel parentLabel, JFrame baseFrame, JLabel diffNum, JLabel diffLabel) {
		Color backgroundColor = new Color(35, 41, 50);
		Color lightBlue = new Color(1, 173, 181);
		Color darkBlue = new Color(1, 155, 160);
		Color offWhite = new Color(238, 238, 238);
		Color darkGrey = new Color(58, 63, 71); // banner
		Color medGrey = new Color(71, 77, 86); // other dark
		Color grey = new Color(85, 93, 104); // other light
		Color lightGrey = new Color(98, 107, 119); // lightest

		baseFrame.setEnabled(false);
		// set-up base window and panel
		JFrame dialogFrame = new JFrame();
		dialogFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		JPanel dialogPanel = new JPanel(new GridBagLayout());
		dialogPanel.setBackground(darkGrey);
		GridBagConstraints dialogConst = new GridBagConstraints();
		dialogConst.insets = new Insets(10, 10, 10, 10);

		// set-up text label
		String text = "";
		if(name.compareTo("updateweight") == 0 || name.compareTo("updategoal") == 0) {
			text = "Enter a new weight: ";
		}

		JTextArea dialogText = new JTextArea(text); 
		dialogText.setEnabled(false);
		dialogText.setBackground(darkGrey);
		dialogText.setForeground(offWhite);
		dialogText.setFont(new Font("Corbel", Font.PLAIN, 14));
		dialogConst.gridx = 0;
		dialogConst.gridy = 0;
		dialogPanel.add(dialogText, dialogConst);

		JButton firstButton = new JButton();
		if(name.compareTo("updateweight") == 0 || name.compareTo("updategoal") == 0) {
			firstButton = new JButton("Accept");
			dialogConst.gridx = 0;
			dialogConst.gridy = 2;
		}
		firstButton.setBackground(offWhite);
		firstButton.setFont(new Font("Corbel", Font.PLAIN, 16));
		firstButton.setForeground(medGrey);
		firstButton.setBorderPainted(false);
		firstButton.setFocusPainted(false);
		firstButton.setHorizontalTextPosition(SwingConstants.CENTER);
		dialogPanel.add(firstButton, dialogConst);

		if(name.compareTo("updateweight") == 0 || name.compareTo("updategoal") == 0) {
			dialogConst.gridx = 0;
			dialogConst.gridy = 1;
		}
		JTextField textField = new JTextField("", 20);
		textField.setBackground(offWhite);
		textField.setFont(new Font("Corbel", Font.PLAIN, 14));
		textField.setBorder(BorderFactory.createLineBorder(offWhite, 3));
		dialogPanel.add(textField, dialogConst);

		// buttons action listeners
		firstButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(name.compareTo("updateweight") == 0) {
					String temp = textField.getText();
					if(temp.length() != 0 && temp.matches("[0-9]+")) {
						data.set(0, Integer.parseInt(temp));
						label.setText(String.valueOf(data.get(0)));
						parentLabel.revalidate();
						parentLabel.repaint();
						diffNum.setText(String.valueOf(data.get(0) - data.get(1)));
						diffLabel.revalidate();
						diffLabel.repaint();
						baseFrame.setEnabled(true);
						dialogFrame.dispose();
					}
				}
				else if(name.compareTo("updategoal") == 0) {
					String temp = textField.getText();
					if(temp.length() != 0 && temp.matches("[0-9]+")) {
						data.set(1, Integer.parseInt(temp));
						label.setText(String.valueOf(data.get(1)));
						parentLabel.revalidate();
						parentLabel.repaint();
						diffNum.setText(String.valueOf(data.get(0) - data.get(1)));
						diffLabel.revalidate();
						diffLabel.repaint();
						baseFrame.setEnabled(true);
						dialogFrame.dispose();
					}
				}
			}
		});

		// window listener
		dialogFrame.addWindowListener(new WindowListener() {
			public void windowClosing(WindowEvent e) {
				baseFrame.setEnabled(true);
				dialogFrame.dispose();
			}
			public void windowDeactivated(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowClosed(WindowEvent e) {}
			public void windowOpened(WindowEvent e) {}
		});

		// packing and displaying
		dialogFrame.add(dialogPanel);
		if(name.compareTo("updateweight") == 0 || name.compareTo("updategoal") == 0) {
			dialogFrame.setPreferredSize(new Dimension(400, 200));
		}
		dialogFrame.pack();
		dialogFrame.setLocationRelativeTo(null);
		dialogFrame.setVisible(true);
		dialogFrame.setResizable(false);
	}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	// format buttons
	public static void buttonProperties(JButton button) {
		button.setFont(new Font("Corbel", Font.PLAIN, 16));
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setForeground(new Color(58, 63, 71));
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setHorizontalAlignment(JLabel.CENTER);
		button.setVerticalAlignment(JLabel.CENTER);
	}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	// updating last workout
	public static void updateLastWorkout(ArrayList<Integer> dataList, String lastWorkout, JLabel lastDate, JLabel exerciseLastDate, Date date, SimpleDateFormat dateFormat) {
		lastWorkout = String.valueOf(dateFormat.format(date));
		lastDate.setText("        " + lastWorkout);
		exerciseLastDate.revalidate();
		exerciseLastDate.repaint();	
	}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// display exercise table check marks
	public static void displayChecks(ArrayList<Integer> dataList, JLabel exerciseTable, GridLayout exerciseLayout) {
		exerciseTable.removeAll();
		int count = 3;
		for(int i = 0; i < 6; i++) {
			for(int ii = 0; ii < 7; ii++) {
				if(dataList.get(count) == 1) {
					exerciseTable.add(new JLabel(new ImageIcon("Graphics/Check.png")));
				}
				else {
					exerciseTable.add(new JLabel(new ImageIcon("Graphics/Empty.png")));
				}
				count++;
			}
		}
		exerciseTable.revalidate();
		exerciseTable.repaint();
	}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	// change on/off check
	public static void changeCheck(int n, int weekDay, ArrayList<Integer> dataList) {
		int[][] tempCoords = {
			{3, 4, 5, 6, 7, 8, 9},			// n = 0
			{10, 11, 12, 13, 14, 15, 16},	// n = 1
			{17, 18, 19, 20, 21, 22, 23},	// n = 2
			{24, 25, 26, 27, 28, 29, 30},	// n = 3
			{31, 32, 33, 34, 35, 36, 37},	// n = 4
			{38, 39, 40, 41, 42, 43, 44}	// n = 5
		};
		int x = tempCoords[n-1][weekDay-1];
		if(dataList.get(x) == 0) {
			dataList.set(x, 1);
		}
		else {
			dataList.set(x, 0);
		}
	}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	// formatting action buttons
	public static void formatActionButton(JButton btn, int x, ArrayList<String> list) {
		buttonProperties(btn);
		String temp = "";
		int chars = 0;
		temp += list.get(x*2-1);
		temp += list.get(x*2);
		chars = 30 - temp.length();
		if(temp.length() - chars < 0) {
			chars *= 1.4;
		}
		temp = "";
		temp += list.get(x*2-1);
		for(int i = 0; i < chars; i++) {
			temp += " ";
		}
		temp += list.get(x*2);
		btn.setText(temp);
		btn.setFont(new Font("Corbel", Font.PLAIN, 16));
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setForeground(new Color(238, 238, 238));
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		btn.setHorizontalTextPosition(SwingConstants.CENTER);
		btn.setHorizontalAlignment(JLabel.CENTER);
		btn.setVerticalAlignment(JLabel.CENTER);
	}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	// changing points from action buttons
	public static void changePoints(ArrayList<String> list, int index, int sign, ArrayList<Integer> dataList, JLabel pointsLabel, JLabel pointsNumber) {
		int tempNew = Integer.parseInt(list.get(index));
		tempNew *= sign;
		int tempOld = dataList.get(2);
		dataList.set(2, tempNew + tempOld);
		pointsNumber.setText(String.valueOf(dataList.get(2)));
		pointsLabel.validate();
		pointsLabel.repaint();
	}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	// save and exit
	public static void saveExit(ArrayList<String> addList, ArrayList<String> subList, ArrayList<String> spendList, ArrayList<Integer> dataList, String lastDayWorkout, JFrame baseFrame) {
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
			for(int ii = 0; ii < 3; ii++) {
				outputFile.print(dataList.get(ii));
				outputFile.print(" ");
			}
			outputFile.println("");
			for(int ii = 0; ii < 42; ii++) {
				outputFile.print(dataList.get(ii+3));
				outputFile.print(" ");
			}
			outputFile.println();
			outputFile.print(lastDayWorkout);
			outputFile.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println("File setup.txt could not be found.");
		}
		baseFrame.dispose();
	}
}