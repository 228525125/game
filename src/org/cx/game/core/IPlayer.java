package org.cx.game.core;

import java.util.List;

import org.cx.game.card.ICard;
import org.cx.game.command.CommandBuffer;
import org.cx.game.widget.ICamp;
import org.cx.game.widget.ICardGroup;
import org.cx.game.widget.ICemetery;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IUseCard;

/**
 * 游戏玩家
 * @author chenxian
 *
 */
public interface IPlayer
{		
	public Integer getId();
	
	public void setId(Integer id);
	
	public IContext getContext();
	
	public void setContext(IContext context);
	
	public ICardGroup getCardGroup();
	
	public IGround getGround();
	
	public void setGround(IGround ground);
	
	public IUseCard getUseCard();
	
	public Object getObject(String type);
	
	public Integer getResource();
	
	public void setResource(Integer resource);
	
	public CommandBuffer getCommandBuffer();
	
	public List<ICard> getDecks();
	
	public String getName();

}
