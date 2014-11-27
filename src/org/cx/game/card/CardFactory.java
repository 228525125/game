package org.cx.game.card;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.cx.game.builder.ObjectTypeBuilder;
import org.cx.game.builder.ObjectTypeParse;
import org.cx.game.exception.BuilderException;
import org.cx.game.exception.ParseException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class CardFactory {
	
	private static String filePath = "/org/cx/game/card/life.xml";
	
	private static Element getRoot() {
		SAXReader saxReader = new SAXReader();
		InputStream is=CardFactory.class.getResourceAsStream(filePath); 
		try {
			Document document = saxReader.read(is);
			return document.getRootElement();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static ICard getInstance(Element cardEl){
		ObjectTypeBuilder otb = new ObjectTypeBuilder();
		try {
			new ObjectTypeParse(otb).parse(cardEl);
			return (ICard) otb.builder();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BuilderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<ICard> getInstances(List<Integer> cid){
		List<ICard> list = new ArrayList<ICard>();
		Element cards = getRoot().element("cards");
		for(Iterator it = cards.elementIterator("card");it.hasNext();){
			Element card = (Element) it.next();
			if(cid.contains(Integer.valueOf(card.attribute("cardID").getText())))
				list.add(getInstance(card));
		}
		return list;
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
		
		List<ICard> list = getInstances(ids);
		for(ICard card : list){
			System.out.println(card.getName());
		}
	}
}
