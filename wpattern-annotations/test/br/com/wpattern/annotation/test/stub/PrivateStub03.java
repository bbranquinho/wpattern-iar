package br.com.wpattern.annotation.test.stub;

import java.util.Date;

import br.com.wpattern.annotation.WPatternClass;
import br.com.wpattern.annotation.WPatternField;
import br.com.wpattern.annotation.WPatternValue;

@WPatternClass(description="Private Stub 03")
public class PrivateStub03 {

	@WPatternField(name="PARAM01")
	private boolean booleanParameter;

	@WPatternField(name="PARAM02", values={ @WPatternValue(value="1"), @WPatternValue(value="2"),  @WPatternValue(value="3"), @WPatternValue(value="4") })
	private int intParameter;

	@WPatternField(name="PARAM03")
	protected long longParameter;

	@WPatternField(name="PARAM04")
	protected char charParameter;

	@WPatternField(name="PARAM05")
	public double doubleParameter;

	@WPatternField(name="PARAM06")
	public float floatParameter;

	@WPatternField(name="PARAM07")
	public Date dateParameter;

	@Override
	public String toString() {
		return "PrivateStub03 [booleanParameter=" + this.booleanParameter
				+ ", intParameter=" + this.intParameter + ", longParameter="
				+ this.longParameter + ", charParameter=" + this.charParameter
				+ ", doubleParameter=" + this.doubleParameter + ", floatParameter="
				+ this.floatParameter + ", dateParameter=" + this.dateParameter + "]";
	}

}
