package pokerBase;

import pokerEnums.eDrawAction;

public class Action {

	private GamePlay gme;
	private int iCardDrawn;
	private eDrawAction eDrawAction;
	private boolean bEvaluateHand;
	private int iCardsToDraw;

	public Action(GamePlay gme, int iCardDrawnPlayer, int iCardDrawnCommon, int iDrawCount) {
		
		this.setGme(gme);
		this.bEvaluateHand = false;
		if (iCardDrawnPlayer < gme.getRule().GetPlayerNumberOfCards()) {
			eDrawAction = eDrawAction.DrawPlayer;
			iCardDrawnPlayer++;
		} else if ((iCardDrawnPlayer >= gme.getRule().GetPlayerNumberOfCards())
				&& (iCardDrawnCommon <= gme.getRule().GetCommunityCardsCount())) {
			eDrawAction = eDrawAction.DrawCommon;
			iCardDrawnCommon++;
		}
		if (iCardDrawnPlayer + iCardDrawnCommon == gme.getRule().getTotalCardsToDraw())
		{
			this.bEvaluateHand = true;
		}
		this.setiCardDrawn(gme.getRule().getiCardsToDraw()[iDrawCount-1]);
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

	public int getiCardsToDraw() {
		return iCardsToDraw;
	}

	public void setiCardsToDraw(int iCardsToDraw) {
		this.iCardsToDraw = iCardsToDraw;
	}

}
