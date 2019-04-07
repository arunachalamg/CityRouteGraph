package com.route.validation;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.route.exception.RouteException;

public class RouteValidatorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testValidateAndProcess() throws RouteException {
		final Map<String,Long> actual = RouteValidator.validateAndProcess("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		final String extected = "{A-B=5, B-C=4, A-D=5, A-E=7, C-E=2, C-D=8, D-C=8, E-B=3, D-E=6}";
		Assert.assertEquals(extected, actual.toString());
	
	
	}

}
