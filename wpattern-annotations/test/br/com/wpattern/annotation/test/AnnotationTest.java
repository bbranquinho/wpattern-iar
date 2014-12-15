package br.com.wpattern.annotation.test;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.wpattern.annotation.exception.InjectionException;
import br.com.wpattern.annotation.exception.MapFieldException;
import br.com.wpattern.annotation.injection.InjectorManager;
import br.com.wpattern.annotation.test.stub.PrivateStub01;
import br.com.wpattern.annotation.test.stub.PrivateStub02;
import br.com.wpattern.annotation.test.stub.PrivateStub03;
import br.com.wpattern.annotation.test.stub.PublicStub01;
import br.com.wpattern.annotation.test.stub.PublicStub02;
import br.com.wpattern.annotation.util.MapFields;

public class AnnotationTest {

	private final Logger logger = Logger.getLogger(this.getClass());

	private MapFields mapFields;

	@Before
	public void startUp() {
		this.mapFields = new MapFields();

		try {
			this.mapFields.AddField("PARAM01", "true");
			this.mapFields.AddField("PARAM02", "4");
			this.mapFields.AddField("PARAM03", "6");
			this.mapFields.AddField("PARAM04", "c");
			this.mapFields.AddField("PARAM05", "5.45");
			this.mapFields.AddField("PARAM06", "9.78");
			this.mapFields.AddField("PARAM07", "01-01-2000 01:01:01");
		} catch (MapFieldException e) {
			this.logger.error(e.getMessage());
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void publicStub01_testInjection_success() {
		this.logger.debug("Test injection of PublicStub01.");

		PublicStub01 instanceObject = new PublicStub01();

		try {
			InjectorManager.injectValues(instanceObject, this.mapFields);
		} catch (InjectionException e) {
			this.logger.error(e.getMessage());
			Assert.fail(e.getMessage());
		}

		this.logger.debug(instanceObject.toString());
	}

	@Test
	public void publicStub02_testInjection_success() {
		this.logger.debug("Test injection of PublicStub02.");

		PublicStub02 instanceObject = new PublicStub02();

		try {
			InjectorManager.injectAllValues(instanceObject, this.mapFields);
		} catch (InjectionException e) {
			this.logger.error(e.getMessage());
			Assert.fail(e.getMessage());
		}

		this.logger.debug(instanceObject.toString());
	}

	@Test
	public void publicStub02_testInjection_failure() {
		this.logger.debug("Test injection of PublicStub02.");

		PublicStub02 instanceObject = new PublicStub02();

		try {
			InjectorManager.injectValues(instanceObject, this.mapFields);
		} catch (InjectionException e) {
			this.logger.debug(e.getMessage());

			if (!e.getMessage().equals("Can not set final boolean field br.com.wpattern.annotation.test.stub.PublicStub02.booleanParameter to java.lang.Boolean")) {
				Assert.fail("Wrong type of exception.");
			}

			return;
		}

		Assert.fail("Const value can't be injected.");
	}

	@Test
	public void privateStub01_testInjection_success() {
		this.logger.debug("Test injection of PrivateStub01.");

		PrivateStub01 instanceObject = new PrivateStub01();

		try {
			InjectorManager.injectValues(instanceObject, this.mapFields);
		} catch (InjectionException e) {
			this.logger.error(e.getMessage());
			Assert.fail(e.getMessage());
		}

		this.logger.debug(instanceObject.toString());
	}

	@Test
	public void privateStub01_testAllInjection_success() {
		this.logger.debug("Test injection of PrivateStub01.");

		PrivateStub01 instanceObject = new PrivateStub01();

		try {
			InjectorManager.injectAllValues(instanceObject, this.mapFields);
		} catch (InjectionException e) {
			this.logger.error(e.getMessage());
			Assert.fail(e.getMessage());
		}

		this.logger.debug(instanceObject.toString());
	}

	@Test
	public void privateStub02_testAllInjection_failure() {
		this.logger.debug("Test injection of PrivateStub02.");

		PrivateStub02 instanceObject = new PrivateStub02();

		try {
			InjectorManager.injectAllValues(instanceObject, this.mapFields);
		} catch (InjectionException e) {
			if (!e.getMessage().equalsIgnoreCase("Field [PARAM02] with invalid value [4].")) {
				this.logger.error(e.getMessage());
				Assert.fail(e.getMessage());
			}

			return;
		}

		Assert.fail("Can't set a value outside values 01 or 02.");
	}

	@Test
	public void privateStub03_testAllInjection_success() {
		this.logger.debug("Test injection of PrivateStub03.");

		PrivateStub03 instanceObject = new PrivateStub03();

		try {
			InjectorManager.injectAllValues(instanceObject, this.mapFields);
		} catch (InjectionException e) {
			this.logger.error(e.getMessage());
			Assert.fail(e.getMessage());
		}

		this.logger.debug(instanceObject.toString());
	}

}
