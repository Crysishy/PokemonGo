/*
 * Author: Yang Hong
 * Class: buttonController.java
 * Description: This class extends JPanel. It is a button controlling panel
 * 				of the game GUI. It contains two JPanels that each contains
 * 				four direction buttons and an instruction button. It is the
 * 				only entry to change the status of the game.
 */

package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Bait;
import model.Game;
import model.Pokeball;
import model.Rock;
import model.Trainer;
import songplayer.SongPlayer;

import javax.swing.JTextArea;
import javax.swing.Timer;

public class ButtonController extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3316968282503323656L;
	private JPanel moveHolder;
	private JPanel actionHolder;
	private JButton moveTrainer;
	private JButton action;
	private JButton moveUp;
	private JButton moveDown;
	private JButton moveLeft;
	private JButton moveRight;
	private JButton pokeball;
	private JButton runAway;
	private JButton rock;
	private JButton bait;
	private JTextArea info;
	private Game game;

	private MoveKeyListener moveListener;
	private ActKeyListener actListener;
	private SongPlayer throwing;

	/**
	 * @wbp.nonvisual location=21,541
	 */
	public ButtonController() {
		this.game = Game.getInstance();
		this.moveHolder = new JPanel();
		this.actionHolder = new JPanel();

		this.moveTrainer = new JButton("Move");
		this.moveUp = new JButton("^");
		this.moveDown = new JButton("v");
		this.moveLeft = new JButton("<");
		this.moveRight = new JButton(">");

		this.action = new JButton("Action");
		this.pokeball = new JButton("Pokeball");
		this.runAway = new JButton("Run");
		this.rock = new JButton("Rock");
		this.bait = new JButton("Bait");

		setupButtonLayout();

		this.info = new JTextArea();
		info.setText("Game about to start...");
		info.setFont(new Font("Courier", Font.BOLD, 18));
		info.setEditable(false);

		this.setSize(250, 750);
		this.setLocation(0, 0);
		this.setLayout(new GridLayout(3, 0));
		this.add(moveHolder);
		this.add(actionHolder);
		this.add(info);
		moveListener = new MoveKeyListener();
		actListener = new ActKeyListener();

		setupMoveListeners();
		throwing = new SongPlayer(null,"SoundSet/throw.wav");
	}

	public void setupMoveListeners() {
		if (moveUp.getKeyListeners().length == 0) {
			moveUp.addKeyListener(moveListener);
			moveDown.addKeyListener(moveListener);
			moveLeft.addKeyListener(moveListener);
			moveRight.addKeyListener(moveListener);
		}

		pokeball.removeKeyListener(actListener);
		runAway.removeKeyListener(actListener);
		bait.removeKeyListener(actListener);
		rock.removeKeyListener(actListener);
	}

	public void setupActListeners() {

		moveUp.removeKeyListener(moveListener);
		moveDown.removeKeyListener(moveListener);
		moveLeft.removeKeyListener(moveListener);
		moveRight.removeKeyListener(moveListener);

		if (pokeball.getKeyListeners().length == 0) {
			pokeball.addKeyListener(actListener);
			runAway.addKeyListener(actListener);
			bait.addKeyListener(actListener);
			rock.addKeyListener(actListener);
		}
	}

	public Timer timer = new Timer(40, new MoveListener());

	private void movingTrainer() {
		Trainer.tic = 0;
		timer.start();
		if (Trainer.canMove)
			Trainer.moving = true;
	}

	private class MoveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// System.out.println("~~~~~~~~TIC:"+Trainer.tic);
			if (!Trainer.moving) {
				timer.stop();
			} else {
				Trainer.tic++;
				game.getTrainerMoving();
			}
		}
	}

	public Timer timer2;

	private class ThrowingItemListener implements ActionListener {

		private String itemName;

		public ThrowingItemListener(String string) {
			itemName = string;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (itemName) {
			case "Pokeball":
				if (!Pokeball.animating) {
					timer2.stop();
					game.checkCapturedOrRan();
				} else {
					Pokeball.tic++;
					game.getTrainerMoving();
				}
				break;
			case "Rock":
				if (!Rock.animating) {
					timer2.stop();
					game.checkCapturedOrRan();
				} else {
					Rock.tic++;
					game.getTrainerMoving();
				}
				break;
			case "Bait":
				if (!Bait.animating) {
					timer2.stop();
					game.checkCapturedOrRan();
				} else {
					Bait.tic++;
					game.getTrainerMoving();
				}
				break;
			default:
				break;
			}
		}
	}

	private void animatingItem(String itemName) {
		throwing.play();
		switch (itemName) {
		case "Pokeball":
			timer2 = new Timer(100, new ThrowingItemListener("Pokeball"));
			Pokeball.tic = 0;
			timer2.start();
			Pokeball.animating = true;
			break;
		case "Rock":
			timer2 = new Timer(100, new ThrowingItemListener("Rock"));
			Rock.tic = 0;
			timer2.start();
			Rock.animating = true;
			break;
		case "Bait":
			timer2 = new Timer(100, new ThrowingItemListener("Bait"));
			Bait.tic = 0;
			timer2.start();
			Bait.animating = true;
			break;
		default:
			break;
		}
	}

	private void setupButtonLayout() {
		// ----------move buttons----------
		moveTrainer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Choose a direction to MOVE.\n(You can not move through rocks, trees or water)");
			}
		});

		moveUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Trainer: " + game.getTrainer().getPosition());
				//System.out.println("    Map: " + game.getGameMap().getTrainerLocation());
				game.moveTrainer("moveUp");
				movingTrainer();
			}
		});

		moveDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.moveTrainer("moveDown");
				movingTrainer();
			}
		});

		moveLeft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.moveTrainer("moveLeft");
				movingTrainer();
			}
		});

		moveRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.moveTrainer("moveRight");
				movingTrainer();
			}
		});

		moveHolder.setPreferredSize(new Dimension(250, 250));
		moveHolder.setLayout(new GridLayout(3, 3));
		moveHolder.setBackground(Color.BLACK);
		moveHolder.add(new emptyButton());
		moveHolder.add(moveUp);
		moveHolder.add(new emptyButton());
		moveHolder.add(moveLeft);
		moveHolder.add(moveTrainer);
		moveHolder.add(moveRight);
		moveHolder.add(new emptyButton());
		moveHolder.add(moveDown);
		moveHolder.add(new emptyButton());

		// ----------action buttons----------
		action.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Choose an Item to use.\n(you can only perform ONE action at a time)");
			}
		});

		pokeball.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				animatingItem("Pokeball");
				game.actTrainer("usePokeball");
			}
		});

		runAway.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.actTrainer("runAway");
			}
		});

		rock.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				animatingItem("Rock");
				game.actTrainer("useRock");
			}
		});

		bait.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				animatingItem("Bait");
				game.actTrainer("useBait");
			}
		});

		actionHolder.setPreferredSize(new Dimension(250, 250));
		actionHolder.setLayout(new GridLayout(3, 3));
		actionHolder.setBackground(Color.BLACK);
		actionHolder.add(new emptyButton());
		actionHolder.add(pokeball);
		actionHolder.add(new emptyButton());
		actionHolder.add(rock);
		actionHolder.add(action);
		actionHolder.add(bait);
		actionHolder.add(new emptyButton());
		actionHolder.add(runAway);
		actionHolder.add(new emptyButton());
	}

	private class emptyButton extends JButton {
		public emptyButton() {
			this.setText("");
			this.setEnabled(false);
		}
	}

	public void resetButtons(boolean state) {
		moveTrainer.setEnabled(state);
		moveUp.setEnabled(state);
		moveDown.setEnabled(state);
		moveLeft.setEnabled(state);
		moveRight.setEnabled(state);

		action.setEnabled(state);
		pokeball.setEnabled(state);
		runAway.setEnabled(state);
		rock.setEnabled(state);
		bait.setEnabled(state);
	}

	public void resetMoveButtons(boolean state) {
		moveTrainer.setEnabled(state);
		moveUp.setEnabled(state);
		moveDown.setEnabled(state);
		moveLeft.setEnabled(state);
		moveRight.setEnabled(state);
	}

	public void resetActionButtons(boolean state) {
		action.setEnabled(state);
		pokeball.setEnabled(state);
		runAway.setEnabled(state);
		rock.setEnabled(state);
		bait.setEnabled(state);
	}

	public void setInfo(String information) {
		info.setText(information);
	}

	private class MoveKeyListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				game.moveTrainer("moveUp");
				movingTrainer();
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				game.moveTrainer("moveDown");
				movingTrainer();
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				game.moveTrainer("moveLeft");
				movingTrainer();
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				game.moveTrainer("moveRight");
				movingTrainer();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}

	private class ActKeyListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_P) {
				animatingItem("Pokeball");
				game.actTrainer("usePokeball");
			} else if (e.getKeyCode() == KeyEvent.VK_Q) {

				game.actTrainer("runAway");
			} else if (e.getKeyCode() == KeyEvent.VK_B) {
				animatingItem("Bait");
				game.actTrainer("useBait");
			} else if (e.getKeyCode() == KeyEvent.VK_R) {
				animatingItem("Rock");
				game.actTrainer("useRock");
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}

}
