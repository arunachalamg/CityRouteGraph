package com.route.process;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.route.exception.RouteException;
import com.route.validation.RouteValidator;

/**
 * Unit test of City route processor.
 * 
 * @author Arun G
 *
 */
public class CityRouteProcessorTest {

	Map<String,Long> routes = null;
	CityRouteProcessor p = null;
	
	@Before
	public void setUp() throws Exception {
		routes = RouteValidator.validateAndProcess("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
		p = new CityRouteProcessor(routes);
	}

	@Test
	public void testGetRoutesWithMnDistance() throws RouteException {
		final String actual = p.getRoutesWithMnDistance("C","C", 30);
		final String extected = "(7) via - {C-D-C-E-B-C=25, C-D-E-B-C-D-E-B-C-D-E-B-C=63, C-E-B-C-E-B-C-E-B-C=27, C-D-E-B-C-E-B-C=30, C-E-B-C-D-E-B-C=30, C-E-B-C-D-C=25, C-D-C-D-C-D-C=48, C-D-C-D-C=32, C-E-B-C=9, C-D-E-B-C=21, C-D-E-B-C-D-E-B-C=42, C-E-B-C-E-B-C=18, C-D-C-D-E-B-C=37, C-D-E-B-C-D-C=37, C-D-C=16}";
		Assert.assertEquals(extected, actual);
	}

	@Test
	public void testGetShortestRoute() throws RouteException {
		final String actual = p.getShortestRoute("C","C");
		final String extected = "(9) via - {C-D-C=16, C-E-B-C=9, C-D-E-B-C=21}";
		Assert.assertEquals(extected, actual);

	}

	@Test
	public void testGetNoOfRutesWithExtractStop() throws RouteException {
		final String actual = p.getNoOfRutesWithExtractStop("A", "C", 4);
		final String extected = "(3) via - [A-B-C, A-D-C, A-D-E-B-C, A-E-B-C, A-B-C-D-C, A-D-C-D-C, A-D-E-B-C-D-C, A-E-B-C-D-C, A-B-C-D-E-B-C, A-D-C-D-E-B-C, A-D-E-B-C-D-E-B-C, A-E-B-C-D-E-B-C, A-B-C-E-B-C, A-D-C-E-B-C, A-D-E-B-C-E-B-C, A-E-B-C-E-B-C]"; 
			
		Assert.assertEquals(extected, actual);

	}

	@Test
	public void testGetNoOfRutesWithMaxStop() throws RouteException {
		final String actual = p.getNoOfRutesWithMaxStop("B", "C", 5);
		final String extected = "(4) via - [B-C, B-C-D-C, B-C-D-E-B-C, B-C-E-B-C]";
		Assert.assertEquals(extected, actual);

	}

	@Test
	public void testGetDistanceOfRoute() throws RouteException {
		final String actual = p.getDistanceOfRoute("AE");
		final String extected = "7";
		Assert.assertEquals(extected, actual);

	}

}
