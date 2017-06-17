package org.cx.game.widget.building;

import org.cx.game.exception.RuleValidatorException;
import org.cx.game.tools.I18n;
import org.cx.game.validator.UpgradeConsumeValidator;

public class UpgradeOption extends Option implements IOption {

	private Integer productType = null;
	private String name = null;
	
	public UpgradeOption() {
		// TODO Auto-generated constructor stub
	}
	
	public UpgradeOption(Integer productType) {
		// TODO Auto-generated constructor stub
		this.productType = productType;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		if(null==name){
			name = super.getName();
			name += I18n.getMessage(Product.class, productType, "name");
		}
		return name;
	}
	
	@Override
	public void execute(Object... objects) throws RuleValidatorException {
		// TODO Auto-generated method stub
		super.execute(objects);
		
		if(null==this.productType){
			addValidator(new UpgradeConsumeValidator(getOwner().getUpgrade()));
			
			doValidator();
			if(hasError())
				throw new RuleValidatorException(getErrors().getMessage());
			
			getOwner().upgrade();
			
		}else{
			IProduct product = getOwner().getProduct(productType);
			addValidator(new UpgradeConsumeValidator(product.getUpgrade()));
			
			doValidator();
			if(hasError())
				throw new RuleValidatorException(getErrors().getMessage());
			
			product.upgrade();
		}
	}
}
