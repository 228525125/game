package org.cx.game.core;

import java.util.List;

import org.cx.game.card.ICard;

public class ContextFactory {

	public static Context createContext(IPlayer player1, IPlayer player2){
		Context context = new Context(player1,player2);
		List<ICard> decks = player1.getDecks();
		Long id = 1l;              //设置卡片在比赛中的ID
		for(ICard card : decks){
			card.setPlayId(id++);
			card.setPlayer(player1);  //设置持卡者
		}
		
		player1.setContext(context);
		
		decks = player2.getDecks();
		for(ICard card : decks){
			card.setPlayId(id++);
			card.setPlayer(player2);  //设置持卡者
		}
		player2.setContext(context);
		
		return context;
	}
}
