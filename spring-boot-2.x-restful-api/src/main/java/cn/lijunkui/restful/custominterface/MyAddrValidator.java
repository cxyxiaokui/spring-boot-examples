package cn.lijunkui.restful.custominterface;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyAddrValidator  implements ConstraintValidator<Addr, String>{
	String[] addrArray = new String[]{"北京","河北","天津"};
	@Override
    public void initialize(Addr addr) {
        //启动时执行
    }
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		for (String addrStr : addrArray) {
			if(addrStr.equals(value)) {
				 return true;
			}
		}
		return false;
	}

}
