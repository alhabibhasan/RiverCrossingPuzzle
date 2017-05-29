package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.LeftBoatController;
import controllers.LeftPieceController;
import controllers.RightBoatController;
import controllers.RightPieceController;
import models.Boat;
import models.Piece;
/**
 * This class is used to model the GUI for the game.
 * @author Muhammed Hasan, Samuel Singh
 *
 */
public class GUI extends JFrame implements Observer {
	/**
	 * Default serial version ID
	 */
	private static final long serialVersionUID = 1L;
	private Observable observableFarmer;
	private Observable observableFox;
	private Observable observableBean;
	private Observable observableDuck;
	private Observable observableBoat;
	private static JPanel river;
	private static JPanel start;
	private static JPanel end;
	private JPanel buttons;
	private JButton boatLeft, boatRight, foxLeft, foxRight, farmLeft, farmRight, duckLeft, duckRight, beanLeft,
			beanRight;
	private ArrayList<JButton> allButtons;
	private JLabel boat, fox, farm, duck, bean;

	private static Boat boatModel;

	public GUI(Observable beanToObserve, Observable duckToObserve, Observable foxToObserve, Observable farmerToObserve,
			Observable boat) {

		super();
		// the observable pieces are assigned to the fields, they will be passed to the controllers.
		this.observableFarmer = farmerToObserve;
		this.observableBean = beanToObserve;
		this.observableDuck = duckToObserve;
		this.observableFox = foxToObserve;
		this.observableBoat = boat;

		boatModel = (Boat) boat;

		river = new JPanel(new BorderLayout());
		start = new Shore(new GridLayout(6, 1)); // 6,1 allows us to have enough space for the pieces.
		end = new Shore(new GridLayout(6, 1));
		buttons = new JPanel(new FlowLayout());

		setUpButtons();

	}

	private void setUpFox() {

		fox = new JLabel("Fox:");
		foxLeft = new JButton("<");
		foxRight = new JButton(">");

		allButtons.add(foxLeft);
		allButtons.add(foxRight);

		foxLeft.addActionListener(new LeftPieceController((Piece) observableFox, (Boat) observableBoat));
		foxRight.addActionListener(new RightPieceController((Piece) observableFox, (Boat) observableBoat));

		JPanel foxHolder = new JPanel(new GridLayout(1, 3));
		foxHolder.add(fox);
		foxHolder.add(foxLeft);
		foxHolder.add(foxRight);

		buttons.add(foxHolder);
	}

	private void setUpFarmer() {
		farm = new JLabel("Farmer:");
		farmLeft = new JButton("<");
		farmRight = new JButton(">");

		allButtons.add(farmLeft);
		allButtons.add(farmRight);

		farmLeft.addActionListener(new LeftPieceController((Piece) observableFarmer, (Boat) observableBoat));
		farmRight.addActionListener(new RightPieceController((Piece) observableFarmer, (Boat) observableBoat));

		JPanel farmHolder = new JPanel(new GridLayout(1, 3));
		farmHolder.add(farm);
		farmHolder.add(farmLeft);
		farmHolder.add(farmRight);

		buttons.add(farmHolder);
	}

	private void setUpDuck() {
		duck = new JLabel("Duck:");
		duckLeft = new JButton("<");
		duckRight = new JButton(">");

		allButtons.add(duckLeft);
		allButtons.add(duckRight);

		duckLeft.addActionListener(new LeftPieceController((Piece) observableDuck, (Boat) observableBoat));
		duckRight.addActionListener(new RightPieceController((Piece) observableDuck, (Boat) observableBoat));

		JPanel duckHolder = new JPanel(new GridLayout(1, 3));
		duckHolder.add(duck);
		duckHolder.add(duckLeft);
		duckHolder.add(duckRight);

		buttons.add(duckHolder);
	}

	private void setUpBean() {
		bean = new JLabel("Bean:");
		beanLeft = new JButton("<");
		beanRight = new JButton(">");

		allButtons.add(beanLeft);
		allButtons.add(beanRight);

		beanLeft.addActionListener(new LeftPieceController((Piece) observableBean, (Boat) observableBoat));
		beanRight.addActionListener(new RightPieceController((Piece) observableBean, (Boat) observableBoat));

		JPanel beanHolder = new JPanel(new GridLayout(1, 3));
		beanHolder.add(bean);
		beanHolder.add(beanLeft);
		beanHolder.add(beanRight);

		buttons.add(beanHolder);
	}

	private void setUpBoat() {
		boat = new JLabel("Boat:");
		boatLeft = new JButton("<");
		boatRight = new JButton(">");

		allButtons.add(boatLeft);
		allButtons.add(boatRight);

		boatLeft.addActionListener(new LeftBoatController(boatModel));
		boatRight.addActionListener(new RightBoatController(boatModel));

		JPanel boatHolder = new JPanel(new GridLayout(1, 3));
		boatHolder.add(boat);
		boatHolder.add(boatLeft);
		boatHolder.add(boatRight);

		buttons.add(boatHolder);
	}

	private void setUpButtons() {

		allButtons = new ArrayList<JButton>();

		setUpFox();
		setUpFarmer();
		setUpDuck();
		setUpBean();
		setUpBoat();
	}

	public void createGUI() {
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		drawRiver(boatModel);

		add(river, BorderLayout.CENTER);
		add(start, BorderLayout.EAST);
		add(end, BorderLayout.WEST);
		add(buttons, BorderLayout.SOUTH);
		setTitle("Fox, Goose and Bag of Beans");

		setSize(1490, 1000);
		setResizable(false);
	}

	public static void drawRiver(Boat boat) {
		River r = new River(boat);
		river.add(r, BorderLayout.CENTER);
		river.repaint();
	}

	public static void removeFromStartPanel(JComponent toRemove) {
		start.remove(toRemove);
		start.revalidate(); // makes sure that the latest componenets are displayed only.
		start.repaint();
	}

	public static void addToStartPanel(JComponent toAdd) {
		start.add(toAdd);
		start.revalidate();
		start.repaint();
	}

	public static void removeFromEndPanel(JComponent toRemove) {
		end.remove(toRemove);
		end.revalidate();
		end.repaint();
	}

	public static void addToEndPanel(JComponent toAdd) {
		end.add(toAdd);
		end.revalidate();
		end.repaint();
	}

	@Override
	public void update(Observable obs, Object arg) {
		if (obs instanceof Piece) {

			if (arg == null) { // null value would incicate that the game has reached a fail state.
				setTitle("Game over: Predetor ate prey!");
				for (JButton button : allButtons) {
					button.setEnabled(false);
				}
			}

			Piece temp = (Piece) arg;


			try {
				ArrayList<Piece> startSide = temp.getStartSide();
				start.removeAll();
				for (Piece p : startSide) {
					addToStartPanel(new JLabel(p.getImageIcon()));
				}

				ArrayList<Piece> endSide = temp.getEndSide();
				end.removeAll();
				for (Piece p : endSide) {
					addToEndPanel(new JLabel(p.getImageIcon()));
				}
			} catch (NullPointerException e) {
				System.out.println("Null value returned by the piece class!");
			}

			/*
			 * Check that the game has been won by checking the size of the end and start panel.
			 */
			if (start.getComponentCount() == 1 && end.getComponentCount() == 5) {
				setTitle("You won! Score: " + boatModel.getMovesMade());
				for (JButton button : allButtons) {
					button.setEnabled(false);
				}
			}
			
			drawRiver(boatModel);
		}

		if (obs instanceof Boat) {
			if (arg instanceof Boat && arg instanceof Boat) {
				// drawRiver((Boat) arg);
				setTitle("Fox, Goose and Bag of Beans | Score: " + ((Boat) arg).getMovesMade());
			}

		}

	}

}
