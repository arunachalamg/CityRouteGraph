package com.route.util;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.route.exception.RouteException;

public class CommonUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testIsBlank() {
		boolean b = CommonUtil.isBlank("");
		Assert.assertEquals(b, true);

	}

	@Test
	public void testIsNotBlank() {
		boolean b = CommonUtil.isNotBlank("");
		Assert.assertEquals(b, false);
	}

	@Test
	public void testIsValidateCity() throws RouteException {
		boolean b = CommonUtil.isValidateCity('A');
		Assert.assertEquals(b, true);
	}

}
