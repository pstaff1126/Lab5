package pokerBase;

import pokerEnums.eDrawAction;

public class Action {

	private GamePlay gme;
	private int iCardDrawn;
	private eDrawAction eDrawAction;
	private boolean bEvaluateHand;

	public Action(GamePlay gme, int iCardDrawn) {
		
		this.setGme(gme);
		this.bEvaluateHand = false;
		if (iCardDrawn <= gme.getRule().GetPlayerNumberOfCards()) {
			eDrawAction = eDrawAction.DrawPlayer;
		} else if ((iCardDrawn > gme.getRule().GetPlayerNumberOfCards())
				&& (iCardDrawn <= gme.getRule().GetCommunityCardsCount() + gme.getRule().GetPlayerNumberOfCards())) {
			eDrawAction = eDrawAction.DrawCommon;
		}
		if (iCardDrawn == gme.getRule().getTotalCardsToDraw())
		{
			this.bEvaluateHand = true;
		}

	}

	public GamePlay getGme() {
		return gme;
	}

	public void setGme(GamePlay gme) {
		this.gme = gme;
	}

	public int getiCardDrawn() {
		return iCardDrawn;
	}

	public void setiCardDrawn(int iCardDrawn) {
		this.iCardDrawn = iCardDrawn;
	}

	public eDrawAction geteDrawAction() {
		return eDrawAction;
	}

	public void seteDrawAction(eDrawAction eDrawAction) {
		this.eDrawAction = eDrawAction;
	}

	public boolean isbEvaluateHand() {
		return bEvaluateHand;
	}

	public void setbEvaluateHand(boolean bEvaluateHand) {
		this.bEvaluateHand = bEvaluateHand;
	}

}
