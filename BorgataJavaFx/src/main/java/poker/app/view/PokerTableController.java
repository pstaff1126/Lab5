package poker.app.view;

import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.math3.util.Combinations;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import enums.eGame;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.SequentialTransitionBuilder;
import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import poker.app.MainApp;
import pokerBase.Card;
import pokerBase.Deck;
import pokerBase.GamePlay;
import pokerBase.GamePlayPlayerHand;
import pokerBase.Hand;
import pokerBase.Player;
import pokerBase.Rule;
import pokerEnums.eDrawAction;
import pokerEnums.eEvalType;

public class PokerTableController {

	boolean bPlay = false;

	boolean bP1Sit = false;
	boolean bP2Sit = false;
	boolean bP3Sit = false;
	boolean bP4Sit = false;

	// Reference to the main application.
	private MainApp mainApp;
	private GamePlay gme = null;
	private int iCardDrawn = 0;

	private Player PlayerCommon = new Player("Common", 0);

	@FXML
	public AnchorPane APMainScreen;

	private ImageView imgTransCard = new ImageView();

	@FXML
	public HBox HboxCommonArea;

	@FXML
	public HBox HboxCommunityCards;

	@FXML
	public HBox hBoxP1Cards;
	@FXML
	public HBox hBoxP2Cards;
	@FXML
	public HBox hBoxP3Cards;
	@FXML
	public HBox hBoxP4Cards;

	@FXML
	public TextField txtP1Name;
	@FXML
	public TextField txtP2Name;
	@FXML
	public TextField txtP3Name;
	@FXML
	public TextField txtP4Name;

	@FXML
	public Label lblP1Name;
	@FXML
	public Label lblP2Name;
	@FXML
	public Label lblP3Name;
	@FXML
	public Label lblP4Name;

	@FXML
	public ToggleButton btnP1SitLeave;
	@FXML
	public ToggleButton btnP2SitLeave;
	@FXML
	public ToggleButton btnP3SitLeave;
	@FXML
	public ToggleButton btnP4SitLeave;

	@FXML
	public Button btnDraw;

	@FXML
	public Button btnPlay;

	public PokerTableController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

	}

	@FXML
	private void handleP1SitLeave() {
		int iPlayerPosition = 1;
		handleSitLeave(bP1Sit, iPlayerPosition, lblP1Name, txtP1Name, btnP1SitLeave);
	}

	@FXML
	private void handleP2SitLeave() {
		int iPlayerPosition = 2;
		handleSitLeave(bP1Sit, iPlayerPosition, lblP2Name, txtP2Name, btnP2SitLeave);
	}

	@FXML
	private void handleP3SitLeave() {
		int iPlayerPosition = 3;
		handleSitLeave(bP1Sit, iPlayerPosition, lblP3Name, txtP3Name, btnP3SitLeave);
	}

	@FXML
	private void handleP4SitLeave() {
		int iPlayerPosition = 3;
		handleSitLeave(bP1Sit, iPlayerPosition, lblP4Name, txtP4Name, btnP4SitLeave);
	}

	private void handleSitLeave(boolean bSit, int iPlayerPosition, Label lblPlayer, TextField txtPlayer,
			ToggleButton btnSitLeave) {
		if (bSit == false) {
			Player p = new Player(txtPlayer.getText(), iPlayerPosition);
			mainApp.AddPlayerToTable(p);
			lblPlayer.setText(txtPlayer.getText());
			lblPlayer.setVisible(true);
			btnSitLeave.setText("Leave");
			txtPlayer.setVisible(false);
			bSit = true;
		} else {
			mainApp.RemovePlayerFromTable(iPlayerPosition);
			btnSitLeave.setText("Sit");
			txtPlayer.setVisible(true);
			lblPlayer.setVisible(false);
			bSit = false;
		}
	}

	@FXML
	private void handlePlay() {

		// Clear all players hands
		hBoxP1Cards.getChildren().clear();
		hBoxP2Cards.getChildren().clear();
		hBoxP3Cards.getChildren().clear();
		hBoxP4Cards.getChildren().clear();

		ImageView imgBottomCard = new ImageView(
				new Image(getClass().getResourceAsStream("/res/img/b2fh.png"), 75, 75, true, true));

		HboxCommonArea.getChildren().clear();
		HboxCommonArea.getChildren().add(imgBottomCard);
		
		HboxCommunityCards.getChildren().clear();
		
		
		// Get the Rule, start the Game
		Rule rle = new Rule(eGame.FiveStud);
		gme = new GamePlay(rle);

		// Add the seated players to the game
		for (Player p : mainApp.GetSeatedPlayers()) {
			gme.addPlayerToGame(p);
			GamePlayPlayerHand GPPH = new GamePlayPlayerHand();
			GPPH.setGame(gme);
			GPPH.setPlayer(p);
			GPPH.setHand(new Hand());
			gme.addGamePlayPlayerHand(GPPH);
			DealFaceDownCards(gme.getNbrOfCards(), p.getiPlayerPosition());
		}

		GamePlayPlayerHand GPCH = new GamePlayPlayerHand();
		GPCH.setGame(gme);
		GPCH.setPlayer(PlayerCommon);
		GPCH.setHand(new Hand());
		gme.addGamePlayCommonHand(GPCH);

		// Add a deck to the game
		gme.setGameDeck(new Deck());

		btnDraw.setVisible(true);
		btnPlay.setVisible(false);
		iCardDrawn = 0;



	}

	public void DealFaceDownCards(int nbrOfCards, int iPlayerPosition) {
		HBox PlayerCardBox = null;

		switch (iPlayerPosition) {
		case 1:
			PlayerCardBox = hBoxP1Cards;
			break;
		case 2:
			PlayerCardBox = hBoxP2Cards;
			break;

		case 3:
			PlayerCardBox = hBoxP3Cards;
			break;

		case 4:
			PlayerCardBox = hBoxP4Cards;
			break;

		}
		String strCard = "/res/img/b1fv.png";

		for (int i = 0; i < nbrOfCards; i++) {
			ImageView img = new ImageView(new Image(getClass().getResourceAsStream(strCard), 75, 75, true, true));
			PlayerCardBox.getChildren().add(img);
		}
	}

	@FXML
	private void handleDraw() {
		iCardDrawn++;
		eDrawAction eDrawAction = null;
		boolean bEvalHand = false;
		eEvalType eEval = null;

		// Disable the button in case of double-click
		btnDraw.setDisable(true);

		// Determine the iDrawAction (draw to player, draw to common
		if ((gme.getRule().GetGame() == eGame.FiveStud) || (gme.getRule().GetGame() == eGame.AcesAndEights)
				|| (gme.getRule().GetGame() == eGame.DeucesWild) || (gme.getRule().GetGame() == eGame.FiveStudOneJoker)
				|| (gme.getRule().GetGame() == eGame.FiveStudTwoJoker)) {
			eEval = eEvalType.Normal;
			if ((iCardDrawn >= 1) && (iCardDrawn <= 5)) {
				eDrawAction = eDrawAction.DrawPlayer;
			} else if (iCardDrawn == 5) {
				bEvalHand = true;
			}
		}

		if ((gme.getRule().GetGame() == eGame.Omaha)) {
			eEval = eEvalType.Omaha;
			if ((iCardDrawn >= 1) && (iCardDrawn <= 4)) {
				eDrawAction = eDrawAction.DrawPlayer;
			}
			if ((iCardDrawn > 4) && (iCardDrawn <= 9)) {
				eDrawAction = eDrawAction.DrawCommon;
			} else if (iCardDrawn == 9) {
				bEvalHand = true;
			}
		}

		if ((gme.getRule().GetGame() == eGame.TexasHoldEm)) {
			eEval = eEvalType.TexasHoldEm;
			if ((iCardDrawn >= 1) && (iCardDrawn <= 2)) {
				eDrawAction = eDrawAction.DrawPlayer;
			}
			if ((iCardDrawn > 2) && (iCardDrawn <= 7)) {
				eDrawAction = eDrawAction.DrawCommon;
			}
			if (iCardDrawn == 7) {
				bEvalHand = true;
			}
		}

		if ((gme.getRule().GetGame() == eGame.SevenDraw)) {
			eEval = eEvalType.SevenCard;
			if ((iCardDrawn >= 1) && (iCardDrawn <= 7)) {
				eDrawAction = eDrawAction.DrawPlayer;
			}
			if (iCardDrawn == 7) {
				bEvalHand = true;
			}
		}

		if (eDrawAction == eDrawAction.DrawPlayer) {

			// Draw a card for each player seated
			for (Player p : mainApp.GetSeatedPlayers()) {
				Card c = gme.getGameDeck().drawFromDeck();

				HBox PlayerCardBox = null;

				switch (p.getiPlayerPosition()) {
				case 1:
					PlayerCardBox = hBoxP1Cards;
					break;
				case 2:
					PlayerCardBox = hBoxP2Cards;
					break;

				case 3:
					PlayerCardBox = hBoxP3Cards;
					break;

				case 4:
					PlayerCardBox = hBoxP4Cards;
					break;

				}
				GamePlayPlayerHand GPPH = gme.FindPlayerGame(gme, p);
				GPPH.addCardToHand(c);
				PerformTransitions(c, PlayerCardBox);
			}

		} else if (eDrawAction == eDrawAction.DrawCommon) {
			Card c = gme.getGameDeck().drawFromDeck();
			GamePlayPlayerHand GPCH = gme.FindPlayerGame(gme, PlayerCommon);
			GPCH.addCardToHand(c);
			PerformTransitions(c, HboxCommunityCards);
		}

		// If bEvalHand is true, it's time to evaluate the Hand...
		if (bEvalHand) {
			btnDraw.setVisible(false);
			ArrayList<GamePlayPlayerHand> AllPlayersHands = new ArrayList<GamePlayPlayerHand>();
			
			for (Player p : mainApp.GetSeatedPlayers()) {
				GamePlayPlayerHand GPPH = gme.FindPlayerGame(gme, p);
				Hand PlayerHand = GPPH.getHand();
				GamePlayPlayerHand GPCH = gme.FindPlayerGame(gme, PlayerCommon);
				
				ArrayList<Hand> AllHands = Hand.ListHands(GPPH.getHand(), GPCH.getHand(), GPPH.getGame() );
				Hand hBestHand = Hand.PickBestHand(AllHands);
				GPPH.setBestHand(hBestHand);
			}

		}

	}

	


	private void PerformTransitions(Card c, HBox PlayerCardBox) {
		// This is the card that is going to be dealt to the player.
		String strCard = "/res/img/" + c.getCardImg();
		ImageView imgvCardDealt = new ImageView(new Image(getClass().getResourceAsStream(strCard), 96, 71, true, true));

		// imgvCardFaceDown - There's already a place holder card
		// sitting in
		// the player's hbox. It's face down. Find it
		// and then determine it's bounds and top left hand handle.
		ImageView imgvCardFaceDown = (ImageView) PlayerCardBox.getChildren().get(iCardDrawn - 1);
		Bounds bndCardDealt = imgvCardFaceDown.localToScene(imgvCardFaceDown.getBoundsInLocal());
		Point2D pntCardDealt = new Point2D(bndCardDealt.getMinX(), bndCardDealt.getMinY());

		// imgvDealerDeck = the card in the common area, where dealer's
		// card
		// is located. Find the boundary top left point.
		ImageView imgvDealerDeck = (ImageView) HboxCommonArea.getChildren().get(0);
		Bounds bndCardDeck = imgvDealerDeck.localToScene(imgvDealerDeck.getBoundsInLocal());
		Point2D pntCardDeck = new Point2D(bndCardDeck.getMinX(), bndCardDeck.getMinY());

		// Add a sequential transition to the card (move, rotate)
		SequentialTransition transMoveRotCard = createTransition(pntCardDeck, pntCardDealt);

		// Add a parallel transition to the card (fade in/fade out).
		final ParallelTransition transFadeCardInOut = createFadeTransition(imgvCardFaceDown,
				new Image(getClass().getResourceAsStream(strCard), 75, 75, true, true));
		/*
		 * transFadeCardInOut.onFinishedProperty().set(new
		 * EventHandler<ActionEvent>() {
		 * 
		 * @Override public void handle(ActionEvent actionEvent) { } });
		 */

		transMoveRotCard.onFinishedProperty().set(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {

				// get rid of the created card, run the fade in/fade out
				// transition
				// This isn't going to fire until the transMoveRotCard
				// is
				// complete.
				APMainScreen.getChildren().remove(imgTransCard);
				transFadeCardInOut.play();

				// Enable the draw button after the animation is done.
				btnDraw.setDisable(false);
			}
		});
	}

	private SequentialTransition createTransition(final Point2D pntStartPoint, final Point2D pntEndPoint) {

		imgTransCard = new ImageView(
				new Image(getClass().getResourceAsStream("/res/img/b2fh.png"), 75, 75, true, true));

		imgTransCard.setX(pntStartPoint.getX());
		imgTransCard.setY(pntStartPoint.getY() - 30);

		APMainScreen.getChildren().add(imgTransCard);

		TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), imgTransCard);
		translateTransition.setFromX(0);
		translateTransition.setToX(pntEndPoint.getX() - pntStartPoint.getX());
		translateTransition.setFromY(0);
		translateTransition.setToY(pntEndPoint.getY() - pntStartPoint.getY());

		translateTransition.setCycleCount(1);
		translateTransition.setAutoReverse(false);

		int rnd = randInt(1, 6);

		System.out.println(rnd);

		RotateTransition rotateTransition = new RotateTransition(Duration.millis(150), imgTransCard);
		rotateTransition.setByAngle(90F);
		rotateTransition.setCycleCount(rnd);
		rotateTransition.setAutoReverse(false);

		ParallelTransition parallelTransition = new ParallelTransition();
		parallelTransition.getChildren().addAll(translateTransition, rotateTransition);

		SequentialTransition seqTrans = new SequentialTransition();
		seqTrans.getChildren().addAll(parallelTransition);

		return seqTrans;
	}

	private ParallelTransition createFadeTransition(final ImageView iv, final Image img) {

		FadeTransition fadeOutTransition = new FadeTransition(Duration.seconds(.25), iv);
		fadeOutTransition.setFromValue(1.0);
		fadeOutTransition.setToValue(0.0);
		fadeOutTransition.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				iv.setImage(img);
				;
			}

		});

		FadeTransition fadeInTransition = new FadeTransition(Duration.seconds(.25), iv);
		fadeInTransition.setFromValue(0.0);
		fadeInTransition.setToValue(1.0);

		ParallelTransition parallelTransition = new ParallelTransition();
		parallelTransition.getChildren().addAll(fadeOutTransition, fadeInTransition);

		return parallelTransition;
	}

	/**
	 * randInt - Create a random number
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	private static int randInt(int min, int max) {

		return (int) (Math.random() * (min - max)) * -1;

	}

}
