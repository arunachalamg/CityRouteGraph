package com.route.validation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.route.constant.City;
import com.route.domain.Route;
import com.route.exception.RouteException;
import com.route.util.CommonUtil;

/**
 * This Class validates the given cities and process the route object if given cities are valid, otherwise throw an error with message.
 *  
 * @author Arun G
 *
 */
public class RouteValidator {
	private static final Logger LOGGER = Logger.getLogger(RouteValidator.class.getSimpleName());

	/**
	 * It validates the given cities and returns the city and distances rute details.
	 * 
	 * @param inputStr
	 * @return
	 * @throws RouteException
	 */
	public static Map<String,Long> validateAndProcess(String inputStr) throws RouteException{
		Map<String,Long> routes = null;
		Set<String> cities 		= null;
		
		if(CommonUtil.isBlank(inputStr)){
			throw new RouteException("input is empty. Pls provide valid input values");
		}
		
		routes = new HashMap<String,Long>(); 
		cities = new HashSet<String>();
		final String[] strArr =inputStr.trim().split(","); 
		for(String str:strArr) {
			final Route route = getValidCitiesAndDistance(str);
			if(null!=route) {
				final String key = String.format("%s-%s",route.getOriginCity(),route.getDestCity());
				if(!cities.add(key)){
					throw new RouteException(String.format("duplicate. city is already exist [%s]",str));
				}
				routes.put(key,route.getDistance());
			}
		}
		LOGGER.log(Level.INFO, "Sucessfully Valided all given city routes..");
		return routes;
	}
	
	/**
	 * It validates the given cities and distance and all validations were success, then response with route object.
	 * 
	 * @param str
	 * @return
	 * @throws RouteException
	 */
	private static Route getValidCitiesAndDistance(String str)throws RouteException{
		if(CommonUtil.isBlank(str)){
			throw new RouteException("city is empty. Pls provide valid input values");
		}
		
		final char[] chArr = str.trim().toUpperCase().toCharArray();//convert to upper case to avoid and validate the cities  
		if(chArr.length<3) {
			throw new RouteException(String.format("Invalid city/distance [%s]",str));
		}else {
			getValidateCity(chArr[0],str);// validate origin city
			getValidateCity(chArr[1],str);// validarte destination city
			
			if(chArr[0] == chArr[1]) {// check whether two cities are the same
				throw new RouteException(String.format("Origin and destination cities should not be the same or invalid [%s]",str));
			}
			final long distance = getCityDistance(chArr);
			if(distance>0) {
				return new Route(Character.toString(chArr[0]),Character.toString(chArr[1]),distance);
			}
		}
		
		return null;
	}
	
	/**
	 * Check he given city is valid.
	 * 
	 * @param ch
	 * @return
	 * @throws RouteException
	 */
	private static City getValidateCity(char ch,String str) throws RouteException{
		try {
			return City.valueOf(Character.toString(ch));
		}catch(Exception e) {
			throw new RouteException(String.format("Invalid Origin/Destination city [%c] in the given route [%s]",ch,str));
		}
	}
	
	/**
	 * Filter and Validate the distance of two cities. 
	 * First two chars are Origin and destination cities and remaining values are distance between two cities.
	 * 
	 * @param chArr
	 * @return
	 * @throws RouteException
	 */
	private static long getCityDistance(final char[] chArr) throws RouteException {
		StringBuilder sb = null;
		long distance = 0l;
		if(null!=chArr) {
			sb = new StringBuilder();
			for(int i=2;i<chArr.length;i++) {// first two chars are Origin and destination cities
				if(Character.isDigit(chArr[i])) {
					sb.append(chArr[i]);
				}else {
					throw new RouteException(String.format("Distance is invalid [%s]",String.copyValueOf(chArr)));
				}
			}
			distance = Long.valueOf(sb.toString());
			if(distance==0) {
				throw new RouteException("Distance zero is invalid.");
			}
		}
		return distance;
	}
}
