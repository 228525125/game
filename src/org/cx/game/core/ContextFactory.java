package org.cx.game.core;

import java.util.List;

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
	public static IContext getContext(){
		return context;
	}

	public static IContext getInstance(IPlayer... players){
		context = new ContextDecorator(new Context(players));

		for(int i=0;i<players.length;i++){
			players[i].setContext(context);
		}

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
