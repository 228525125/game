package org.cx.game.core;

import java.util.List;
import java.util.Map;

import org.cx.game.card.LifeCard;
import org.cx.game.command.CommandBuffer;
import org.cx.game.intercepter.IIntercepter;
import org.cx.game.intercepter.ProxyFactory;
import org.cx.game.widget.ICardGroup;
import org.cx.game.widget.IGround;
import org.cx.game.widget.IUseCard;

public class PlayerDecorator implements IPlayer {

	private IPlayer player = null;
	
	public PlayerDecorator(IPlayer player) {
		// TODO Auto-generated constructor stub
		this.player = player;
	}
	
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return player.getId();
	}

	@Override
	public IContext getContext() {
		// TODO Auto-generated method stub
		return player.getContext();
	}

	@Override
	public void setContext(IContext context) {
		// TODO Auto-generated method stub
		player.setContext(context);
	}

	@Override
	public IGround getGround() {
		// TODO Auto-generated method stub
		return player.getGround();
	}

	@Override
	public void setGround(IGround ground) {
		// TODO Auto-generated method stub
		player.setGround(ground);
	}

	@Override
	public IUseCard getUseCard() {
		// TODO Auto-generated method stub
		return player.getUseCard();
	}

	@Override
	public Object getObject(String type) {
		// TODO Auto-generated method stub
		return player.getObject(type);
	}

	@Override
	public Integer getResource() {
		// TODO Auto-generated method stub
		return player.getResource();
	}

	@Override
	public void setResource(Integer res) {
		// TODO Auto-generated method stub
		player.setResource(res);
	}

	@Override
	public void addToResource(Integer res) {
		// TODO Auto-generated method stub
		player.addToResource(res);
	}

	@Override
	public CommandBuffer getCommandBuffer() {
		// TODO Auto-generated method stub
		return player.getCommandBuffer();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return player.getName();
	}

	@Override
	public LifeCard getHero() {
		// TODO Auto-generated method stub
		return player.getHero();
	}

	@Override
	public void setHero(LifeCard hero) {
		// TODO Auto-generated method stub
		player.setHero(hero);
	}

	@Override
	public Integer getHeroCardID() {
		// TODO Auto-generated method stub
		return player.getHeroCardID();
	}

	@Override
	public void setHeroCardID(Integer cardID) {
		// TODO Auto-generated method stub
		player.setHeroCardID(cardID);
	}

	@Override
	public Integer getHeroEntry() {
		// TODO Auto-generated method stub
		return player.getHeroEntry();
	}

	@Override
	public void setHeroEntry(Integer position) {
		// TODO Auto-generated method stub
		player.setHeroEntry(position);
	}

	@Override
	public Integer getCallCountOfPlay() {
		// TODO Auto-generated method stub
		return player.getCallCountOfPlay();
	}

	@Override
	public void addCallCountOfPlay(Integer time) {
		// TODO Auto-generated method stub
		player.addCallCountOfPlay(time);
	}

	@Override
	public List<LifeCard> getAttendantList() {
		// TODO Auto-generated method stub
		return player.getAttendantList();
	}

	@Override
	public List<LifeCard> getAttendantList(Boolean activate) {
		// TODO Auto-generated method stub
		return player.getAttendantList(activate);
	}

	@Override
	public Integer getRationLimit() {
		// TODO Auto-generated method stub
		return player.getRationLimit();
	}

	@Override
	public void setRationLimit(Integer ration) {
		// TODO Auto-generated method stub
		player.setRationLimit(ration);
	}

	@Override
	public Integer getRation() {
		// TODO Auto-generated method stub
		return player.getRation();
	}

	@Override
	public void addToRation(Integer ration) {
		// TODO Auto-generated method stub
		player.addToRation(ration);
	}

	@Override
	public Integer getBout() {
		// TODO Auto-generated method stub
		return player.getBout();
	}

	@Override
	public void addBout() {
		// TODO Auto-generated method stub
		Object proxy = ProxyFactory.getProxy(this.player);     
		((IPlayer)proxy).addBout();
	}

	@Override
	public Map<String, List<IIntercepter>> getIntercepterList() {
		// TODO Auto-generated method stub
		return player.getIntercepterList();
	}

	@Override
	public void addIntercepter(IIntercepter intercepter) {
		// TODO Auto-generated method stub
		player.addIntercepter(intercepter);
	}

	@Override
	public void deleteIntercepter(IIntercepter intercepter) {
		// TODO Auto-generated method stub
		player.deleteIntercepter(intercepter);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		player.clear();
	}

}
