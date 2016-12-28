package org.cx.game.core;

import java.util.List;

import org.cx.game.card.ICard;
import org.cx.game.card.LifeCard;
import org.cx.game.intercepter.Intercepter;
import org.cx.game.widget.IStrongHold;

public class ContextFactory {

	public static IContext createContext(IPlayer player1, IPlayer player2){
		IContext context = new ContextDecorator(new Context(player1,player2));
		
		List<ICard> decks = player1.decksList();              
		for(ICard card : decks){
			if (card instanceof LifeCard) {
				LifeCard life = (LifeCard) card;
				if(life.getHero())
					player1.setHeroCard(life);
			}
			card.setPlayId(context.newCardPlayId());     //设置卡片在比赛中的ID
			card.setPlayer(player1);  //设置持卡者
		}

		player1.setContext(context);
		

		decks = player2.decksList();
		for(ICard card : decks){
			if (card instanceof LifeCard) {
				LifeCard life = (LifeCard) card;
				if(life.getHero())
				player2.setHeroCard(life);
			}
			card.setPlayId(context.newCardPlayId());
			card.setPlayer(player2);  //设置持卡者
		}
		
		player2.setContext(context);
		
		for(final IStrongHold strongHold : player1.getGround().getStrongHoldList()){
			strongHold.getPlayer().setContext(context);
			
			context.addIntercepter(new Intercepter("addBout"){
			
				@Override
				public void after(Object[] args) {
					// TODO Auto-generated method stub
					strongHold.refurbish();
				}
			});
		}
		
		return context;
	}
}
