package org.cx.game.command.check;

import java.util.ArrayList;
import java.util.List;

import org.cx.game.builder.ObjectTypeBuilder;
import org.cx.game.builder.ObjectTypeParse;
import org.cx.game.exception.BuilderException;
import org.cx.game.exception.ParseException;
import org.cx.game.exception.ValidatorException;
import org.cx.game.tools.XmlUtil;
import org.dom4j.Element;

public class CheckHelper {

	private List<ICheck> checkerList = new ArrayList<ICheck>();
	
	public void setCheckerList(List<ICheck> checkerList) {
		this.checkerList = checkerList;
	}
	
	private static CheckHelper helper = null;
	
	public static CheckHelper getInstance() {
		if(null==helper){
			Element checkHelper = XmlUtil.getRoot("check.path");
			if(null!=checkHelper){
				ObjectTypeBuilder otb = new ObjectTypeBuilder();
				try {
					new ObjectTypeParse(otb).parse(checkHelper);
					helper = (CheckHelper) otb.builder();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BuilderException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return helper;
	}
	
	public void check(Object source, String method, Object [] arguments) throws ValidatorException {
		CheckEvent ce = new CheckEvent(source, method, arguments);
		
		for(ICheck check : this.checkerList){
			if(check.isMatch(ce)){
				check.check(ce);
			}
		} 
	}
}
