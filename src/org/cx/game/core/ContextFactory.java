package org.cx.game.core;

import org.cx.game.card.CardFactory;
import org.cx.game.card.LifeCard;
import org.cx.game.intercepter.Intercepter;
import org.cx.game.widget.IStrongHold;

public class ContextFactory {
	
	private static IContext context = null;
	
	/**
	 * 该方法必须在createContext被调用后，才有效；
	 * @return
	 */
	public static IContext getInstance(){
		return context;
	}

	public static IContext createContext(IPlayer player1, IPlayer player2){
		context = new ContextDecorator(new Context(player1,player2));

		player1.setContext(context);
		player1.setHero((LifeCard) CardFactory.getInstance(player1.getHeroCardID(), player1));
		
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
