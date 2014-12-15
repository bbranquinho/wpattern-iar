package br.com.wpattern.annotation.test.stub;

import java.util.Date;

import br.com.wpattern.annotation.WPatternClass;
import br.com.wpattern.annotation.WPatternField;

@WPatternClass(description="Public Stub 02")
public class PublicStub02 {

	@WPatternField(name="PARAM01")
	public final boolean booleanParameter;

	@WPatternField(name="PARAM02")
	public int intParameter;

	@WPatternField(name="PARAM03")
	public long longParameter;

	@WPatternField(name="PARAM04")
	public char charParameter;

	@WPatternField(name="PARAM05")
	public double doubleParameter;

	@WPatternField(name="PARAM06")
	public float floatParameter;

	@WPatternField(name="PARAM07")
	public Date dateParameter;

	public PublicStub02() {
		this.booleanParameter = false;
	}

	@Override
	public String toString() {
		return "PublicStub02 [booleanParameter=" + this.booleanParameter
				+ ", intParameter=" + this.intParameter + ", longParameter="
				+ this.longParameter + ", charParameter=" + this.charParameter
				+ ", doubleParameter=" + this.doubleParameter + ", floatParameter="
				+ this.floatParameter + ", dateParameter=" + this.dateParameter + "]";
	}

}
