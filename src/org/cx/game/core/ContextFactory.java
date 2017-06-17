package org.cx.game.core;

import org.cx.game.card.CardFactory;
import org.cx.game.card.LifeCard;
import org.cx.game.intercepter.Intercepter;
import org.cx.game.widget.IStrongHold;

public class ContextFactory {

	public static IContext createContext(IPlayer player1, IPlayer player2){
		IContext context = new ContextDecorator(new Context(player1,player2));
		
		/*List<ICard> decks = player1.decksList();              
		for(ICard card : decks){
			if (card instanceof LifeCard) {
				LifeCard life = (LifeCard) card;
				if(life.getHero())
					player1.setHero(life);
			}
			card.setPlayId(context.newCardPlayId());     //设置卡片在比赛中的ID
			card.setPlayer(player1);  //设置持卡者
		}*/

		player1.setContext(context);
		player1.setHero((LifeCard) CardFactory.getInstance(player1.getHeroCardID(), player1));
		

		/*decks = player2.decksList();
		for(ICard card : decks){
			if (card instanceof LifeCard) {
				LifeCard life = (LifeCard) card;
				if(life.getHero())
				player2.setHero(life);
			}
			card.setPlayId(context.newCardPlayId());
			card.setPlayer(player2);  //设置持卡者
		}*/
		
		player2.setContext(context);
		player2.setHero((LifeCard) CardFactory.getInstance(player2.getHeroCardID(), player2));
		
		/*for(final IStrongHold strongHold : player1.getGround().getStrongHoldList()){
			strongHold.getPlayer().setContext(context);
			
			context.addIntercepter(new Intercepter("addBout"){
			
				@Override
				public void after(Object[] args) {
					// TODO Auto-generated method stub
					strongHold.refurbish();
				}
			});
		}*/
		
		return context;
	}
}
