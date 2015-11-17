package pokerBase;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import enums.eGame;
import enums.eHandStrength;
import enums.eRank;
import enums.eSuit;
import pokerEnums.eEvalType;

public class ListHands_Test {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
 

	@Test
	public void ListHoldEm() {
		Hand playerHand = new Hand();
		Hand commonHand = new Hand();

		Rule rle = new Rule(eGame.TexasHoldEm);
		GamePlay gme = new GamePlay(rle);
		
		playerHand.AddCardToHand(new Card(eSuit.CLUBS, eRank.ACE, 0));
		playerHand.AddCardToHand(new Card(eSuit.CLUBS, eRank.KING, 0));

		commonHand.AddCardToHand(new Card(eSuit.CLUBS, eRank.TWO, 0));
		commonHand.AddCardToHand(new Card(eSuit.CLUBS, eRank.THREE, 0));
		commonHand.AddCardToHand(new Card(eSuit.CLUBS, eRank.SIX, 0));
		commonHand.AddCardToHand(new Card(eSuit.CLUBS, eRank.SEVEN, 0));
		commonHand.AddCardToHand(new Card(eSuit.CLUBS, eRank.EIGHT, 0));

		ArrayList<Hand> AllHands = new ArrayList<Hand>();

		AllHands = Hand.ListHands(playerHand, commonHand,gme);
		System.out.println(AllHands.size());
		assertTrue(AllHands.size() == gme.getRule().getPossibleHandCombinations());

		HashSet hsHands = new HashSet();
		
		for (Hand h : AllHands) {
			hsHands.add(h);
		}
		
		//	Check to see if I have N different hands
		assertTrue(hsHands.size() == gme.getRule().getPossibleHandCombinations());
		

		Hand h = Hand.PickBestHand(AllHands);
		assertTrue("This should be a flush",h.getHandStrength() == eHandStrength.Flush.getHandStrength());
		assertTrue("This should be an ace", h.getHiHand() == eRank.ACE.getRank());

		
		

	}
	


	@Test
	public void ListOmaha() {
		Hand playerHand = new Hand();
		Hand commonHand = new Hand();

		Rule rle = new Rule(eGame.Omaha);
		GamePlay gme = new GamePlay(rle);

		playerHand.AddCardToHand(new Card(eSuit.CLUBS, eRank.ACE, 0));
		playerHand.AddCardToHand(new Card(eSuit.CLUBS, eRank.KING, 0));
		playerHand.AddCardToHand(new Card(eSuit.CLUBS, eRank.QUEEN, 0));
		playerHand.AddCardToHand(new Card(eSuit.CLUBS, eRank.JACK, 0));

		commonHand.AddCardToHand(new Card(eSuit.CLUBS, eRank.TWO, 0));
		commonHand.AddCardToHand(new Card(eSuit.CLUBS, eRank.THREE, 0));
		commonHand.AddCardToHand(new Card(eSuit.CLUBS, eRank.SIX, 0));
		commonHand.AddCardToHand(new Card(eSuit.CLUBS, eRank.SEVEN, 0));
		commonHand.AddCardToHand(new Card(eSuit.CLUBS, eRank.EIGHT, 0));

		ArrayList<Hand> AllHands = new ArrayList<Hand>();

		AllHands = Hand.ListHands(playerHand,  commonHand, gme);
		System.out.println(AllHands.size());
		assertTrue(AllHands.size() == gme.getRule().getPossibleHandCombinations());

		HashSet hsHands = new HashSet();
		
		for (Hand h : AllHands) {
			hsHands.add(h);
		}
		
		//	Check to see if I have N different hands
		assertTrue(hsHands.size() == gme.getRule().getPossibleHandCombinations());
		
		for (Hand h : AllHands) {
			System.out.print(h.getCards().get(0).getRank().getRank());
			System.out.print(" ");
			System.out.print(h.getCards().get(1).getRank().getRank());
			System.out.print(" ");
			System.out.print(h.getCards().get(2).getRank().getRank());
			System.out.print(" ");
			System.out.print(h.getCards().get(3).getRank().getRank());
			System.out.print(" ");
			System.out.print(h.getCards().get(4).getRank().getRank());
			System.out.print(" ");

			System.out.println("");

		}
	}

	@Test
	public void ListSuperOmaha() {
		Hand playerHand = new Hand();
		Hand commonHand = new Hand();

		Rule rle = new Rule(eGame.Omaha);
		GamePlay gme = new GamePlay(rle);

		playerHand.AddCardToHand(new Card(eSuit.CLUBS, eRank.ACE, 0));
		playerHand.AddCardToHand(new Card(eSuit.CLUBS, eRank.KING, 0));
		playerHand.AddCardToHand(new Card(eSuit.CLUBS, eRank.QUEEN, 0));
		playerHand.AddCardToHand(new Card(eSuit.CLUBS, eRank.JACK, 0));

		commonHand.AddCardToHand(new Card(eSuit.CLUBS, eRank.TWO, 0));
		commonHand.AddCardToHand(new Card(eSuit.CLUBS, eRank.THREE, 0));
		commonHand.AddCardToHand(new Card(eSuit.CLUBS, eRank.SIX, 0));
		commonHand.AddCardToHand(new Card(eSuit.CLUBS, eRank.SEVEN, 0));
		commonHand.AddCardToHand(new Card(eSuit.CLUBS, eRank.EIGHT, 0));

		ArrayList<Hand> AllHands = new ArrayList<Hand>();

		AllHands = Hand.ListHands(playerHand,  commonHand, gme);
		System.out.println(AllHands.size());
		assertTrue(AllHands.size() == gme.getRule().getPossibleHandCombinations());

		HashSet hsHands = new HashSet();
		
		for (Hand h : AllHands) {
			hsHands.add(h);
		}
		
		//	Check to see if I have N different hands
		assertTrue(hsHands.size() == gme.getRule().getPossibleHandCombinations());
		
		for (Hand h : AllHands) {
			System.out.print(h.getCards().get(0).getRank().getRank());
			System.out.print(" ");
			System.out.print(h.getCards().get(1).getRank().getRank());
			System.out.print(" ");
			System.out.print(h.getCards().get(2).getRank().getRank());
			System.out.print(" ");
			System.out.print(h.getCards().get(3).getRank().getRank());
			System.out.print(" ");
			System.out.print(h.getCards().get(4).getRank().getRank());
			System.out.print(" ");

			System.out.println("");

		}
	}
	@Test
	public void ListSevenCard() {
		Hand playerHand = new Hand();
		Hand commonHand = new Hand();

		Rule rle = new Rule(eGame.SevenDraw);
		GamePlay gme = new GamePlay(rle);
		
		playerHand.AddCardToHand(new Card(eSuit.CLUBS, eRank.ACE, 0));
		playerHand.AddCardToHand(new Card(eSuit.CLUBS, eRank.KING, 0));
		playerHand.AddCardToHand(new Card(eSuit.CLUBS, eRank.QUEEN, 0));
		playerHand.AddCardToHand(new Card(eSuit.CLUBS, eRank.JACK, 0));

		playerHand.AddCardToHand(new Card(eSuit.CLUBS, eRank.TWO, 0));
		playerHand.AddCardToHand(new Card(eSuit.CLUBS, eRank.THREE, 0));
		playerHand.AddCardToHand(new Card(eSuit.CLUBS, eRank.SIX, 0));


		ArrayList<Hand> AllHands = new ArrayList<Hand>();

		AllHands = Hand.ListHands(playerHand, commonHand,gme);
		System.out.println(AllHands.size());
		assertTrue(AllHands.size() == gme.getRule().getPossibleHandCombinations());

		for (Hand h : AllHands) {
			System.out.print(h.getCards().get(0).getRank().getRank());
			System.out.print(" ");
			System.out.print(h.getCards().get(1).getRank().getRank());
			System.out.print(" ");
			System.out.print(h.getCards().get(2).getRank().getRank());
			System.out.print(" ");
			System.out.print(h.getCards().get(3).getRank().getRank());
			System.out.print(" ");
			System.out.print(h.getCards().get(4).getRank().getRank());
			System.out.print(" ");

			System.out.println("");

		}
	}



}
