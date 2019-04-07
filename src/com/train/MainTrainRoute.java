package com.train;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.train.exception.RouteException;
import com.train.process.CityRouteProcessor;
import com.train.util.CommonUtil;
import com.train.validation.RouteValidator;


/**
 * This is to help the railroad provide its customers with information about the routes.  
 * In particular, you will compute the distance along a certain route, the number of different routes between two towns, 
 * and the shortest route between two towns.
 * 
 * @author Arun G
 *
 */
public class MainTrainRoute {
	
	private static final Logger LOGGER = Logger.getLogger(MainTrainRoute.class.getName());

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
			System.out.println("Input Data :"+inputData);
			routes = RouteValidator.validateAndProcess(inputData);
			new CityRouteProcessor(routes).processRoutes();
		
		}catch(RouteException e) {
			LOGGER.log(Level.WARNING, e.getMessage());
		}
	
	}
	
	
}
