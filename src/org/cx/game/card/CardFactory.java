package org.cx.game.card;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.cx.game.builder.ObjectTypeBuilder;
import org.cx.game.builder.ObjectTypeParse;
import org.cx.game.core.ContextFactory;
import org.cx.game.core.IPlayer;
import org.cx.game.exception.BuilderException;
import org.cx.game.exception.ParseException;
import org.cx.game.tools.PropertiesUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class CardFactory {

	private static Element getRoot() {
		SAXReader saxReader = new SAXReader();
		try {
			InputStream is=new BufferedInputStream(new FileInputStream(PropertiesUtil.getConfigure("life.path"))); 
		
			Document document = saxReader.read(is);
			return document.getRootElement();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static LifeCard getInstance(Element cardEl){
		ObjectTypeBuilder otb = new ObjectTypeBuilder();
		try {
			new ObjectTypeParse(otb).parse(cardEl);
			LifeCard card = (LifeCard) otb.builder();
			return card;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BuilderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static LifeCard getInstance(Integer cid, IPlayer player){
		Element cardEl = getElement(cid);
		LifeCard card = getInstance(cardEl);
		card.setPlayer(player);
		card.setPlayId(ContextFactory.getContext().newCardPlayId());
		return card;
	}
	
	public static List<LifeCard> getInstances(List<Integer> cid, IPlayer player){
		List<LifeCard> list = new ArrayList<LifeCard>();
		
		for(Integer id : cid){
			Element cardEl = getElement(id);
			LifeCard card = getInstance(cardEl);
			card.setPlayer(player);
			card.setPlayId(ContextFactory.getContext().newCardPlayId());
			list.add(getInstance(cardEl));
		}
		return list;
	}
	
	private static Element getElement(Integer cid){
		
		Element el = null;
		
		Element cards = getRoot().element("cards");
		
		for(Iterator it = cards.elementIterator("card");it.hasNext();){
			Element card = (Element) it.next();
			if(cid.equals(Integer.valueOf(card.attribute("cardID").getText())))
				el = card;
		}
		
		return el;
	}
	
	public static void main(String[] args) {
		Element root = getRoot();
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1130001);
		ids.add(1120002);
		ids.add(1110004);
		ids.add(1140005);
		ids.add(1120006);
		ids.add(1140007);
		ids.add(1230008);
		ids.add(1220009);
		ids.add(1230010);
		ids.add(1210011);
		ids.add(1330012);
		ids.add(1320013);
		ids.add(1420016);
		ids.add(1430017);
		ids.add(1410018);
		ids.add(1440019);
		ids.add(1440020);
		
		/*List<ICard> list = getInstances(ids);
		for(ICard card : list){
			System.out.println(card.getName());
		}*/
	}
}
