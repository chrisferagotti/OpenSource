package com.unsettlingtrend.java.apps.caluculator;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Caluculator extends JFrame implements Runnable {

	// Below are the variables for stretching out the calculator
	// I only recommend playing with the WINDOW_HEIGHT and WINDOW_WIDTH
	// parameters
	private static int WINDOW_HEIGHT = 400; // Total Height for the window
	private static int WINDOW_WIDTH = 500; // Total width for the window
	private static int WINDOW_MARGIN = 5; // The margin around the content in
											// the window
	private static int TEXT_FIELD_HEIGHT = 45; // Size of the TextArea
	private static int BUTTON_PADDING = 2; // Padding between buttons
	private static int BUTTON_HEIGHT = (WINDOW_HEIGHT - TEXT_FIELD_HEIGHT
			- WINDOW_MARGIN * 3 - BUTTON_PADDING * 4) / 5;
	private static int BUTTON_WIDTH = (WINDOW_WIDTH - BUTTON_PADDING * 4 - WINDOW_MARGIN * 2) / 5;
	private static Font BUTTON_FONT = new Font("Verdana", Font.PLAIN,
			BUTTON_HEIGHT / 3);
	private static Font BUTTON_FONT_BOLD = new Font("Verdana", Font.BOLD,
			BUTTON_HEIGHT / 3);
	private static Font TEXTFIELD_FONT = new Font("Verdana", Font.PLAIN,
			TEXT_FIELD_HEIGHT / 2);
	private static Float MEMORY = new Float(0);
	private static Float FORMER = null;

	public enum Operation {
		ADD, SUBTRACT, DIVIDE, MULTIPLY, EQUALS
	};

	private Operation currentOperation = null;

	// Declare the frame and panel
	private JPanel backgroundPanel;

	// Declare the text widget
	private JTextField displayTextField;

	// Declare all the Buttons
	private JButton num0Button;
	private JButton num1Button;
	private JButton num2Button;
	private JButton num3Button;
	private JButton num4Button;
	private JButton num5Button;
	private JButton num6Button;
	private JButton num7Button;
	private JButton num8Button;
	private JButton num9Button;
	private JButton decimalButton;
	private JButton plusMinusButton;
	private JButton memPlusButton;
	private JButton memMinusButton;
	private JButton memRemButton;
	private JButton memClearButton;
	private JButton plusButton;
	private JButton minusButton;
	private JButton multiplyButton;
	private JButton divideButton;
	private JButton equalsButton;
	private JButton sqrRootButton;
	private JButton clearButton;
	private JButton reciprocalButton;

	public static void main(String[] args) {
		Caluculator Calc = new Caluculator();
		Calc.run();
	}

	@Override
	public void run() {
		buildUI();
	}

	private void buildUI() {

		// Initialize/setup the Frame and Panel
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(WINDOW_WIDTH + 4, WINDOW_HEIGHT + 25);
		backgroundPanel = new JPanel(null);
		backgroundPanel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setContentPane(backgroundPanel);

		// Initialize displayTextField and set accordingly
		displayTextField = new JTextField();
		displayTextField.setFocusable(false);
		displayTextField.setBounds(WINDOW_MARGIN, WINDOW_MARGIN,
				WINDOW_WIDTH - 11, TEXT_FIELD_HEIGHT);
		displayTextField.setHorizontalAlignment(JTextField.RIGHT);
		displayTextField.setFont(TEXTFIELD_FONT);
		backgroundPanel.add(displayTextField);

		// Initialize the buttons, assign their event listeners, and add them to
		// the buttonBox object
		num0Button = new InputButton("0", BUTTON_FONT);
		num0Button.addActionListener(new addToTextWindow("0"));
		num0Button.setBounds(WINDOW_MARGIN + BUTTON_WIDTH + BUTTON_PADDING,
				WINDOW_MARGIN * 2 + TEXT_FIELD_HEIGHT + BUTTON_HEIGHT * 4
						+ BUTTON_PADDING * 4, BUTTON_WIDTH, BUTTON_HEIGHT);
		backgroundPanel.add(num0Button);

		num1Button = new InputButton("1", BUTTON_FONT);
		num1Button.addActionListener(new addToTextWindow("1"));
		num1Button.setBounds(WINDOW_MARGIN, WINDOW_MARGIN * 2
				+ TEXT_FIELD_HEIGHT + BUTTON_HEIGHT * 3 + BUTTON_PADDING * 3,
				BUTTON_WIDTH, BUTTON_HEIGHT);
		backgroundPanel.add(num1Button);

		num2Button = new InputButton("2", BUTTON_FONT);
		num2Button.addActionListener(new addToTextWindow("2"));
		num2Button.setBounds(WINDOW_MARGIN + BUTTON_PADDING + BUTTON_WIDTH,
				WINDOW_MARGIN * 2 + TEXT_FIELD_HEIGHT + BUTTON_PADDING * 3
						+ BUTTON_HEIGHT * 3, BUTTON_WIDTH, BUTTON_HEIGHT);
		backgroundPanel.add(num2Button);

		num3Button = new InputButton("3", BUTTON_FONT);
		num3Button.addActionListener(new addToTextWindow("3"));
		num3Button.setBounds(WINDOW_MARGIN + BUTTON_PADDING * 2 + BUTTON_WIDTH
				* 2, WINDOW_MARGIN * 2 + TEXT_FIELD_HEIGHT + BUTTON_PADDING * 3
				+ BUTTON_HEIGHT * 3, BUTTON_WIDTH, BUTTON_HEIGHT);
		backgroundPanel.add(num3Button);

		num4Button = new InputButton("4", BUTTON_FONT);
		num4Button.addActionListener(new addToTextWindow("4"));
		num4Button.setBounds(WINDOW_MARGIN, WINDOW_MARGIN * 2
				+ TEXT_FIELD_HEIGHT + BUTTON_HEIGHT * 2 + BUTTON_PADDING * 2,
				BUTTON_WIDTH, BUTTON_HEIGHT);
		backgroundPanel.add(num4Button);

		num5Button = new InputButton("5", BUTTON_FONT);
		num5Button.addActionListener(new addToTextWindow("5"));
		num5Button.setBounds(WINDOW_MARGIN + BUTTON_PADDING + BUTTON_WIDTH,
				WINDOW_MARGIN * 2 + TEXT_FIELD_HEIGHT + BUTTON_PADDING * 2
						+ BUTTON_HEIGHT * 2, BUTTON_WIDTH, BUTTON_HEIGHT);
		backgroundPanel.add(num5Button);

		num6Button = new InputButton("6", BUTTON_FONT);
		num6Button.addActionListener(new addToTextWindow("6"));
		num6Button.setBounds(WINDOW_MARGIN + BUTTON_PADDING * 2 + BUTTON_WIDTH
				* 2, WINDOW_MARGIN * 2 + TEXT_FIELD_HEIGHT + BUTTON_PADDING * 2
				+ BUTTON_HEIGHT * 2, BUTTON_WIDTH, BUTTON_HEIGHT);
		backgroundPanel.add(num6Button);

		num7Button = new InputButton("7", BUTTON_FONT);
		num7Button.addActionListener(new addToTextWindow("7"));
		num7Button.setBounds(WINDOW_MARGIN, WINDOW_MARGIN * 2
				+ TEXT_FIELD_HEIGHT + BUTTON_HEIGHT + BUTTON_PADDING,
				BUTTON_WIDTH, BUTTON_HEIGHT);
		backgroundPanel.add(num7Button);

		num8Button = new InputButton("8", BUTTON_FONT);
		num8Button.addActionListener(new addToTextWindow("8"));
		num8Button.setBounds(WINDOW_MARGIN + BUTTON_PADDING + BUTTON_WIDTH,
				WINDOW_MARGIN * 2 + TEXT_FIELD_HEIGHT + BUTTON_PADDING
						+ BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
		backgroundPanel.add(num8Button);

		num9Button = new InputButton("9", BUTTON_FONT);
		num9Button.addActionListener(new addToTextWindow("9"));
		num9Button.setBounds(WINDOW_MARGIN + BUTTON_PADDING * 2 + BUTTON_WIDTH
				* 2, WINDOW_MARGIN * 2 + TEXT_FIELD_HEIGHT + BUTTON_PADDING
				+ BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
		backgroundPanel.add(num9Button);

		decimalButton = new InputButton(".", BUTTON_FONT);
		decimalButton.addActionListener(new addToTextWindow("."));
		decimalButton.setBounds(WINDOW_MARGIN, WINDOW_MARGIN * 2
				+ TEXT_FIELD_HEIGHT + BUTTON_HEIGHT * 4 + BUTTON_PADDING * 4,
				BUTTON_WIDTH, BUTTON_HEIGHT);
		backgroundPanel.add(decimalButton);

		plusMinusButton = new InputButton("-/+", BUTTON_FONT);
		plusMinusButton.addActionListener(new addToTextWindow("-/+"));
		plusMinusButton.setBounds(WINDOW_MARGIN + BUTTON_PADDING * 2
				+ BUTTON_WIDTH * 2, WINDOW_MARGIN * 2 + TEXT_FIELD_HEIGHT
				+ BUTTON_PADDING * 4 + BUTTON_HEIGHT * 4, BUTTON_WIDTH,
				BUTTON_HEIGHT);
		backgroundPanel.add(plusMinusButton);

		memPlusButton = new MemoryButton("M+", BUTTON_FONT);
		memPlusButton.addActionListener(new ClickMemoryButton("M+"));
		memPlusButton.setBounds(WINDOW_MARGIN + BUTTON_PADDING * 2
				+ BUTTON_WIDTH * 2, WINDOW_MARGIN * 2 + TEXT_FIELD_HEIGHT,
				BUTTON_WIDTH, BUTTON_HEIGHT);
		backgroundPanel.add(memPlusButton);

		memMinusButton = new MemoryButton("M-", BUTTON_FONT);
		memMinusButton.addActionListener(new ClickMemoryButton("M-"));
		memMinusButton.setBounds(WINDOW_MARGIN + BUTTON_PADDING * 3
				+ BUTTON_WIDTH * 3, WINDOW_MARGIN * 2 + TEXT_FIELD_HEIGHT,
				BUTTON_WIDTH, BUTTON_HEIGHT);
		backgroundPanel.add(memMinusButton);

		memRemButton = new MemoryButton("MR", BUTTON_FONT);
		memRemButton.addActionListener(new ClickMemoryButton("MR"));
		memRemButton.setBounds(WINDOW_MARGIN + BUTTON_PADDING + BUTTON_WIDTH,
				WINDOW_MARGIN * 2 + TEXT_FIELD_HEIGHT, BUTTON_WIDTH,
				BUTTON_HEIGHT);
		backgroundPanel.add(memRemButton);

		memClearButton = new MemoryButton("MC", BUTTON_FONT);
		memClearButton.addActionListener(new ClickMemoryButton("MC"));
		memClearButton.setBounds(WINDOW_MARGIN, WINDOW_MARGIN * 2
				+ TEXT_FIELD_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
		backgroundPanel.add(memClearButton);

		plusButton = new OperationButton("+", BUTTON_FONT);
		plusButton.addActionListener(new ClickOperatorButton("+"));
		plusButton.setBounds(WINDOW_MARGIN + BUTTON_PADDING * 3 + BUTTON_WIDTH
				* 3, WINDOW_MARGIN * 2 + TEXT_FIELD_HEIGHT + BUTTON_PADDING
				+ BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
		backgroundPanel.add(plusButton);

		minusButton = new OperationButton("-", BUTTON_FONT);
		minusButton.addActionListener(new ClickOperatorButton("-"));
		minusButton.setBounds(WINDOW_MARGIN + BUTTON_PADDING * 3 + BUTTON_WIDTH
				* 3, WINDOW_MARGIN * 2 + TEXT_FIELD_HEIGHT + BUTTON_PADDING * 2
				+ BUTTON_HEIGHT * 2, BUTTON_WIDTH, BUTTON_HEIGHT);
		backgroundPanel.add(minusButton);

		multiplyButton = new OperationButton("x", BUTTON_FONT);
		multiplyButton.addActionListener(new ClickOperatorButton("x"));
		multiplyButton.setBounds(WINDOW_MARGIN + BUTTON_PADDING * 3
				+ BUTTON_WIDTH * 3, WINDOW_MARGIN * 2 + TEXT_FIELD_HEIGHT
				+ BUTTON_PADDING * 3 + BUTTON_HEIGHT * 3, BUTTON_WIDTH,
				BUTTON_HEIGHT);
		backgroundPanel.add(multiplyButton);

		divideButton = new OperationButton("/", BUTTON_FONT);
		divideButton.addActionListener(new ClickOperatorButton("/"));
		divideButton.setBounds(WINDOW_MARGIN + BUTTON_PADDING * 3
				+ BUTTON_WIDTH * 3, WINDOW_MARGIN * 2 + TEXT_FIELD_HEIGHT
				+ BUTTON_PADDING * 4 + BUTTON_HEIGHT * 4, BUTTON_WIDTH,
				BUTTON_HEIGHT);
		backgroundPanel.add(divideButton);

		sqrRootButton = new OperationButton("SqrRt", BUTTON_FONT);
		sqrRootButton.addActionListener(new addToTextWindow("SqrRt"));
		sqrRootButton.setBounds(WINDOW_MARGIN + BUTTON_PADDING * 4
				+ BUTTON_WIDTH * 4, WINDOW_MARGIN * 2 + TEXT_FIELD_HEIGHT
				+ BUTTON_PADDING * 2 + BUTTON_HEIGHT * 2, BUTTON_WIDTH,
				BUTTON_HEIGHT);
		backgroundPanel.add(sqrRootButton);

		clearButton = new OperationButton("C", BUTTON_FONT);
		clearButton.addActionListener(new addToTextWindow("C"));
		clearButton.setBounds(WINDOW_MARGIN + BUTTON_PADDING * 4 + BUTTON_WIDTH
				* 4, WINDOW_MARGIN * 2 + TEXT_FIELD_HEIGHT, BUTTON_WIDTH,
				BUTTON_HEIGHT);
		backgroundPanel.add(clearButton);

		reciprocalButton = new OperationButton("1/x", BUTTON_FONT);
		reciprocalButton.addActionListener(new addToTextWindow("1/x"));
		reciprocalButton.setBounds(WINDOW_MARGIN + BUTTON_PADDING * 4
				+ BUTTON_WIDTH * 4, WINDOW_MARGIN * 2 + TEXT_FIELD_HEIGHT
				+ BUTTON_PADDING + BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
		backgroundPanel.add(reciprocalButton);

		equalsButton = new OperationButton("=", BUTTON_FONT);
		equalsButton.addActionListener(new ClickOperatorButton("="));
		equalsButton.setBounds(WINDOW_MARGIN + BUTTON_PADDING * 4
				+ BUTTON_WIDTH * 4, WINDOW_MARGIN * 2 + TEXT_FIELD_HEIGHT
				+ BUTTON_PADDING * 3 + BUTTON_HEIGHT * 3, BUTTON_WIDTH,
				BUTTON_HEIGHT * 2 + BUTTON_PADDING);
		backgroundPanel.add(equalsButton);

		// Make the Frame visible
		this.setVisible(true);

	}

	// EVENT LISTENERS

	// This listener is triggered when one of the inputButtons is pressed (just
	// 0-9)
	public class addToTextWindow implements ActionListener {
		private String textToAdd;

		public addToTextWindow(String text) {
			textToAdd = text;
		}

		public void actionPerformed(ActionEvent a) {
			System.out.println(currentOperation);
			if (currentOperation == Operation.EQUALS) {
				currentOperation = null;
				displayTextField.setText("");
			}
			// make sure to only add one decimal
			if (textToAdd == ".") {
				// If there's no decimal
				if (displayTextField.getText().indexOf(".") == -1)
					// Add a decimal
					displayTextField.setText(displayTextField.getText()
							+ textToAdd);
			} else
				displayTextField
						.setText(displayTextField.getText() + textToAdd);
		}
	}

	public class ClickMemoryButton implements ActionListener {
		private String memOp;

		public ClickMemoryButton(String text) {
			memOp = text;
		}

		public void actionPerformed(ActionEvent a) {
			if (memOp == "MC") {
				MEMORY.parseFloat("0");
				memRemButton.setFont(BUTTON_FONT);
			} else if (memOp == "M+") {
				MEMORY += Float.parseFloat(displayTextField.getText());
				memRemButton.setFont(BUTTON_FONT_BOLD);
			} else if (memOp == "M-") {
				MEMORY -= Float.parseFloat(displayTextField.getText());
				memRemButton.setFont(BUTTON_FONT_BOLD);
			} else if (memOp == "MR") {
				displayTextField.setText(MEMORY.toString());
			}

		}
	}

	public class ClickOperatorButton implements ActionListener {
		private String opButton;

		public ClickOperatorButton(String text) {
			opButton = text;
		}

		public void actionPerformed(ActionEvent a) {
			if (opButton == "+") {
				FORMER = Float.parseFloat(displayTextField.getText());
				currentOperation = Operation.ADD;
				displayTextField.setText("");
			} else if (opButton == "-") {
				FORMER = Float.parseFloat(displayTextField.getText());
				currentOperation = Operation.SUBTRACT;
				displayTextField.setText("");
			} else if (opButton == "/") {
				FORMER = Float.parseFloat(displayTextField.getText());
				currentOperation = Operation.DIVIDE;
				displayTextField.setText("");
			} else if (opButton == "x") {
				FORMER = Float.parseFloat(displayTextField.getText());
				currentOperation = Operation.MULTIPLY;
				displayTextField.setText("");
			} else if (opButton == "=") {
				if (FORMER != null) {
					if (currentOperation == Operation.ADD)
						displayTextField.setText(String.valueOf((FORMER + Float
								.parseFloat(displayTextField.getText()))));
					else if (currentOperation == Operation.SUBTRACT)
						displayTextField.setText(String.valueOf((FORMER - Float
								.parseFloat(displayTextField.getText()))));
					else if (currentOperation == Operation.DIVIDE)
						displayTextField.setText(String.valueOf((FORMER / Float
								.parseFloat(displayTextField.getText()))));
					else if (currentOperation == Operation.MULTIPLY)
						displayTextField.setText(String.valueOf((FORMER * Float
								.parseFloat(displayTextField.getText()))));
					FORMER = null;
					currentOperation = Operation.EQUALS;
				}
			}

		}

	}
}
