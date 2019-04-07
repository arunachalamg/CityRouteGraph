package com.route;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.route.exception.RouteException;
import com.route.process.CityRouteProcessor;
import com.route.util.CommonUtil;
import com.route.validation.RouteValidator;


/**
 * This is to help the railroad provide its customers with information about the routes.  
 * In particular, you will compute the distance along a certain route, the number of different routes between two towns, 
 * and the shortest route between two towns.
 * 
 * @author Arun G
 *
 */
public class Main {
	
	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

	final static String FILE_NAME = "inputcityroutes.txt";

	/**
	 * Trigger location to execute the application.
	 *  
	 * @param args
	 */
	public static void main(String[] args) throws RouteException {

		Map<String,Long> routes = null;
		try {
			final String inputData = CommonUtil.readFile(FILE_NAME);
			LOGGER.log(Level.INFO,"Input Data :"+inputData);
			routes = RouteValidator.validateAndProcess(inputData);
			new CityRouteProcessor(routes).processRoutes();
		
		}catch(RouteException e) {
			LOGGER.log(Level.WARNING, e.getMessage());
		}
	
	}
		
}
