package pokerBase;

import java.util.ArrayList;

import domain.CardDomainModel;
import enums.eGame;
import enums.eRank;
import enums.eSuit;

public class Rule {

	private int MaxNumberOfPlayers;
	private int PlayerNumberOfCards;
	private int NumberOfJokers;
	private int PlayerCardsMin;
	private int PlayerCardsMax;
	private int CommunityCardsMin;
	private int CommunityCardsMax;
	private int PossibleHandCombinations;
	private ArrayList<CardDomainModel> RuleCards = new ArrayList<CardDomainModel>();
	private eGame Game;

	public Rule(eGame gme) {
		this.Game = gme;
		switch (gme) {
		case FiveStud: {
			this.MaxNumberOfPlayers = 4;
			this.PlayerNumberOfCards = 5;
			this.NumberOfJokers = 0;
			this.PlayerCardsMin = 5;
			this.PlayerCardsMax = 5;
			this.CommunityCardsMin = 0;
			this.CommunityCardsMax = 0;	
			this.PossibleHandCombinations = 1;
			break;
		}
		case FiveStudOneJoker: {
			this.MaxNumberOfPlayers = 4;
			this.PlayerNumberOfCards = 5;
			this.NumberOfJokers = 21;
			this.PlayerCardsMin = 5;
			this.PlayerCardsMax = 5;			
			this.CommunityCardsMin = 0;
			this.CommunityCardsMax = 0;
			this.PossibleHandCombinations = 1;
			break;
		}
		case FiveStudTwoJoker: {
			this.MaxNumberOfPlayers = 4;
			this.PlayerNumberOfCards = 5;
			this.NumberOfJokers = 2;
			this.PlayerCardsMin = 5;
			this.PlayerCardsMax = 5;			
			this.CommunityCardsMin = 0;
			this.CommunityCardsMax = 0;
			this.PossibleHandCombinations = 1;
			break;
		}
		case TexasHoldEm: {
			this.MaxNumberOfPlayers = 8;
			this.PlayerNumberOfCards = 2;
			this.NumberOfJokers = 0;
			this.PlayerCardsMin = 0;
			this.PlayerCardsMax = 2;			
			this.CommunityCardsMin = 3;
			this.CommunityCardsMax = 5;
			this.PossibleHandCombinations = 21;
			break;
		}
		case Omaha: {
			this.MaxNumberOfPlayers = 6;
			this.PlayerNumberOfCards = 4;
			this.NumberOfJokers = 0;
			this.PlayerCardsMin = 2;
			this.PlayerCardsMax = 2;			
			this.CommunityCardsMin = 3;
			this.CommunityCardsMax = 5;
			this.PossibleHandCombinations = 60;
			break;
		}
		case SuperOmaha: {
			this.MaxNumberOfPlayers = 6;
			this.PlayerNumberOfCards = 4;
			this.NumberOfJokers = 0;
			this.PlayerCardsMin = 0;
			this.PlayerCardsMax = 2;			
			this.CommunityCardsMin = 3;
			this.CommunityCardsMax = 5;
			this.PossibleHandCombinations = 81;
			break;
		}		
		case SevenDraw: {
			this.MaxNumberOfPlayers = 4;
			this.PlayerNumberOfCards = 7;
			this.NumberOfJokers = 0;
			this.PlayerCardsMin = 5;
			this.PlayerCardsMax = 5;			
			this.CommunityCardsMin = 0;
			this.CommunityCardsMax = 0;
			this.PossibleHandCombinations = 21;
			break;
		}		
		case DeucesWild: {
			this.MaxNumberOfPlayers = 4;
			this.PlayerNumberOfCards = 5;
			this.NumberOfJokers = 0;
			this.RuleCards.add(new Card(eSuit.DIAMONDS, eRank.TWO, 40));
			this.RuleCards.add(new Card(eSuit.HEARTS, eRank.TWO, 1));
			this.RuleCards.add(new Card(eSuit.SPADES, eRank.TWO, 14));
			this.RuleCards.add(new Card(eSuit.CLUBS, eRank.TWO, 27));
			this.PlayerCardsMin = 5;
			this.PlayerCardsMax = 5;			
			this.CommunityCardsMin = 0;
			this.CommunityCardsMax = 0;
			this.PossibleHandCombinations = 1;
			break;
		}
		case AcesAndEights: {
			this.MaxNumberOfPlayers = 4;
			this.PlayerNumberOfCards = 2;
			this.NumberOfJokers = 0;
			this.RuleCards.add(new Card(eSuit.DIAMONDS, eRank.ACE, 52));
			this.RuleCards.add(new Card(eSuit.HEARTS, eRank.ACE, 13));
			this.RuleCards.add(new Card(eSuit.SPADES, eRank.ACE, 26));
			this.RuleCards.add(new Card(eSuit.CLUBS, eRank.ACE, 39));
			this.RuleCards.add(new Card(eSuit.DIAMONDS, eRank.EIGHT, 46));
			this.RuleCards.add(new Card(eSuit.HEARTS, eRank.EIGHT, 7));
			this.RuleCards.add(new Card(eSuit.SPADES, eRank.EIGHT, 20));
			this.RuleCards.add(new Card(eSuit.CLUBS, eRank.EIGHT, 33));
			this.PlayerCardsMin = 5;
			this.PlayerCardsMax = 5;			
			this.CommunityCardsMin = 0;
			this.CommunityCardsMax = 0;
			this.PossibleHandCombinations = 1;
			break;
		}
		}
	}

	public int GetMaxNumberOfPlayers() {
		return this.MaxNumberOfPlayers;
	}

	public int GetPlayerNumberOfCards() {
		return this.PlayerNumberOfCards;
	}

	public int GetNumberOfJokers() {
		return this.NumberOfJokers;
	}
	
	public int GetCommunityCardsCount()
	{
		return this.CommunityCardsMax;
	}
	
	public ArrayList<CardDomainModel> GetRuleCards()
	{
		return this.RuleCards;
	}
	public eGame GetGame()
	{
		return this.Game;
	}

	public int getCommunityCardsMin() {
		return CommunityCardsMin;
	}

	public void setCommunityCardsMin(int communityCardsMin) {
		CommunityCardsMin = communityCardsMin;
	}

	public int getCommunityCardsMax() {
		return CommunityCardsMax;
	}

	public void setCommunityCardsMax(int communityCardsMax) {
		CommunityCardsMax = communityCardsMax;
	}

	public int getPlayerCardsMin() {
		return PlayerCardsMin;
	}

	public void setPlayerCardsMin(int playerCardsMin) {
		PlayerCardsMin = playerCardsMin;
	}

	public int getPlayerCardsMax() {
		return PlayerCardsMax;
	}
	
	public int getTotalCardsToDraw()
	{
		return this.GetPlayerNumberOfCards() + this.getCommunityCardsMax();
	}

	public void setPlayerCardsMax(int playerCardsMax) {
		PlayerCardsMax = playerCardsMax;
	}

	public int getPossibleHandCombinations() {
		return PossibleHandCombinations;
	}

	public void setPossibleHandCombinations(int possibleHandCombinations) {
		PossibleHandCombinations = possibleHandCombinations;
	}
	
}
