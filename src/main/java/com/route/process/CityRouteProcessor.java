package com.route.process;

import static com.route.constant.RouteConstants.DIFF_ROUTES_CC;
import static com.route.constant.RouteConstants.DISTANCE_ROUTE_ABC;
import static com.route.constant.RouteConstants.DISTANCE_ROUTE_AD;
import static com.route.constant.RouteConstants.DISTANCE_ROUTE_ADC;
import static com.route.constant.RouteConstants.DISTANCE_ROUTE_AEBCD;
import static com.route.constant.RouteConstants.DISTANCE_ROUTE_AED;
import static com.route.constant.RouteConstants.SHORTEST_ROUTE_AC;
import static com.route.constant.RouteConstants.SHORTEST_ROUTE_BB;
import static com.route.constant.RouteConstants.TRIPS_AC_4;
import static com.route.constant.RouteConstants.TRIPS_CC_3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.route.exception.RouteException;

/**
 * it process the route details with cities.
 * 
 * @author Arun G
 *
 */
public class CityRouteProcessor {
	private static final Logger LOGGER = Logger.getLogger(CityRouteProcessor.class.getName());

	private Map<String,Set<String>> routeMap = null;
	private Map<String,Long> routes = null;
	
	/**
	 * Initialize the node and corresponding edges.
	 * 
	 * @param routes
	 * @throws RouteException
	 */
	public CityRouteProcessor(final Map<String,Long> routes) throws RouteException{
		this.routes=routes;
		this.routeMap = getNodeAndEdgesOfCityRoutes(routes);
	}
	
	/**
	 * Prints All requested output.
	 * 
	 * @throws RouteException
	 */
	public void processRoutes() throws RouteException{
		LOGGER.log(Level.INFO, "processRoutes() : Processing the route details."+routes);
		System.out.println(String.format("%s = %s",DISTANCE_ROUTE_ABC, getDistanceOfRoute("ABC")));
		System.out.println(String.format("%s = %s",DISTANCE_ROUTE_AD, getDistanceOfRoute("AD")));
		System.out.println(String.format("%s = %s",DISTANCE_ROUTE_ADC, getDistanceOfRoute("ADC")));
		System.out.println(String.format("%s = %s",DISTANCE_ROUTE_AEBCD, getDistanceOfRoute("AEBCD")));
		System.out.println(String.format("%s = %s",DISTANCE_ROUTE_AED, getDistanceOfRoute("AED")));
		System.out.println(String.format("%s = %s",TRIPS_CC_3, getNoOfRutesWithMaxStop("C","C",3)));//max 3 stops
		System.out.println(String.format("%s = %s",TRIPS_AC_4, getNoOfRutesWithExtractStop("A","C",4)));//max 3 stops
		System.out.println(String.format("%s = %s",SHORTEST_ROUTE_AC, getShortestRoute("A","C")));//Shortest route
		System.out.println(String.format("%s = %s",SHORTEST_ROUTE_BB, getShortestRoute("B","B")));//Shortest route
		System.out.println(String.format("%s = %s",DIFF_ROUTES_CC, getRoutesWithMnDistance("C","C",30)));//routes with Max 30 route
	}

	/**
	 * Returns the routes with no.of Minimum distance of given cities.
	 *  
	 * @param routes
	 * @param connectRoute
	 * @param originCity
	 * @param destCity
	 * @return
	 * @throws RouteException
	 */
	public String getRoutesWithMnDistance(final String originCity, final String destCity,int dis) throws RouteException{
		long n = 0;
		
		final Map<String,Long> routeAndDistance = getAllPossibleRoundRouteAndDistance(originCity,destCity);
		if(null!=routeAndDistance) {
			for(Long d:routeAndDistance.values()) {
				if(d>0 && d<dis) {
					n++;
				}
			}
		}
		return String.format("(%s) via - %s",n,routeAndDistance);
	}
	
	/**
	 * Returns the shortest route of given cities.
	 *  
	 * @param routes
	 * @param connectRoute
	 * @param originCity
	 * @param destCity
	 * @return
	 * @throws RouteException
	 */
	public String getShortestRoute(final String originCity, final String destCity) throws RouteException{
		long shortestDis = 0;
		
		final Map<String,Long> routeAndDistance = getRouteAndDistance(originCity,destCity);	
		shortestDis = this.getValidShortDistance(routeAndDistance);	
		return String.format("(%s) via - %s",shortestDis,routeAndDistance);
	}
	
	/**
	 * Return Shortest distance.
	 * 
	 * @param distances
	 * @return
	 * @throws RouteException
	 */
	private long getValidShortDistance(Map<String,Long> routeAndDistance)throws RouteException{
		Set<Long> distances = null;
		if(null!=routeAndDistance) {
			distances = new TreeSet<Long>();
			for(Map.Entry<String, Long> entry:routeAndDistance.entrySet()) {
				distances.add(entry.getValue());// arranging with asc for get the shortest one.
			}
		}
		if(null!=distances && !distances.isEmpty()) {
			for(long dis:distances) {
				if(dis!=0) {
					return dis;
				}
			}
		}
		return 0;
	}
	
	/**
	 * Get route and total distance of the route
	 *   
	 * @param routes
	 * @param connectRoute
	 * @param originCity
	 * @param destCity
	 * @return
	 * @throws RouteException
	 */
	private Map<String,Long> getAllPossibleRoundRouteAndDistance(final String source, final String dest) throws RouteException{
		
		Map<String,Long> rotesWithDistance = null;
		Set<String> cityRoundRoutes = null;
		
		cityRoundRoutes = new HashSet<String>();
		final List<String> cityRoutes = this.findAllRutes(source, dest);
		cityRoundRoutes.addAll(cityRoutes);
				
		for(int i=0;i<cityRoutes.size();i++) {
			for(int j=0;j<cityRoutes.size();j++) {// find all possible pattern
				cityRoundRoutes.add(cityRoutes.get(i)+cityRoutes.get(i).substring(1)+cityRoutes.get(i).substring(1));
				cityRoundRoutes.add(cityRoutes.get(i)+cityRoutes.get(j).substring(1));
				cityRoundRoutes.add(cityRoutes.get(j)+cityRoutes.get(i).substring(1));
			}
		}
		if(null!=cityRoundRoutes && !cityRoundRoutes.isEmpty()) {
			rotesWithDistance = new HashMap<String,Long>();
			for(String cityRoute:cityRoundRoutes) {
				final long totalDistance = this.getDistance(cityRoute);
				rotesWithDistance.put(cityRoute, totalDistance);//add distance with route
			}
		}
		return rotesWithDistance;
	}
	
	/**
	 * Get route and total distance of the route
	 *   
	 * @param routes
	 * @param connectRoute
	 * @param originCity
	 * @param destCity
	 * @return
	 * @throws RouteException
	 */
	private Map<String,Long> getRouteAndDistance(final String source, final String dest) throws RouteException{
		
		Map<String,Long> rotesWithDistance = null;
		
		final List<String> cityRoutes = this.findAllRutes(source, dest);
		if(null!=cityRoutes && !cityRoutes.isEmpty()) {
			rotesWithDistance = new HashMap<String,Long>();
			for(String cityRoute:cityRoutes) {
				final long totalDistance = this.getDistance(cityRoute);
				rotesWithDistance.put(cityRoute, totalDistance);//add distance with route
			}
		}
		return rotesWithDistance;
	}
	
	/**
	 * it returns is the total distances of the specific routes.
	 * 
	 * @param routes
	 * @param route
	 * @return
	 * @throws RouteException
	 */
	private long getDistance(final String route) throws RouteException{
		long totalDistance = 0;
		if(null!=routes && null!=route) {
			final char[] chArr= route.replaceAll("-","").toCharArray();
			if(null!=chArr) {
				for(int i=0; i<chArr.length-1;i++) {
					final String cityRoute = String.format("%c-%c",chArr[i],chArr[i+1]);
					final long routeDistance = null==routes.get(cityRoute)?0:routes.get(cityRoute);
					totalDistance+=routeDistance;
				}
			}
		}
		return totalDistance;
	}
	
	
	/**
	 * Returns the max no of stop list.
	 * 
	 * @param routes
	 * @param connectRoute
	 * @param originCity
	 * @param destCity
	 * @param maxcount
	 * @return
	 * @throws RouteException
	 */
	public String getNoOfRutesWithExtractStop(String source, String dest,int stop) throws RouteException{
		int n = 0;
	
		final List<String> cityRoutes = this.findAllRutes(source, dest);
		if(null!=cityRoutes && !cityRoutes.isEmpty()) {
			for(String cityRoute:cityRoutes) {
				String[] strArr = cityRoute.split("-");
				if(strArr.length>1 && (strArr.length-1)==stop ) {
					n++;
				}
			}
		}
		return String.format("(%s) via - %s",n,cityRoutes);
	}

	
	/**
	 * Returns the max no of stop list.
	 * 
	 * @param routes
	 * @param connectRoute
	 * @param originCity
	 * @param destCity
	 * @param maxcount
	 * @return
	 * @throws RouteException
	 */
	public String getNoOfRutesWithMaxStop(final String source, final String dest, int stop) throws RouteException{
		int n = 0;
		
		final List<String> cityRoutes = this.findAllRutes(source, dest);
		if(null!=cityRoutes && !cityRoutes.isEmpty()) {
			for(String cityRoute:cityRoutes) {
				String[] strArr = cityRoute.split("-");
				if(strArr.length>1 && (strArr.length-1)<=stop ) {
					n++;
				}
			}
		}
		return String.format("(%s) via - %s",n,cityRoutes);
	}
	
	/**
	 * Returns the total distance of the given city range. if routes are not available, then return no such routes message
	 * 
	 * @param routes
	 * @param cities
	 * @return
	 * @throws RouteException
	 */
	public String getDistanceOfRoute(final String cities) throws RouteException{
		long totalDistance = 0l;
		
		if(null!=routes && !routes.isEmpty()) {
			final Set<String> ruteList = this.getRutes(cities);
			if(null!=ruteList && !ruteList.isEmpty()) {
				for(final String rute:ruteList) {
					if(!routes.containsKey(rute)) {
						return "NO SUCH ROUTE";// no route.so Stop the flow
					}
					totalDistance += routes.get(rute);
				}
			}
		}
		if(totalDistance>0) {
			return String.valueOf(totalDistance);
		}
		return "NO SUCH ROUTE";// no route.
	}
	
	/**
	 * This returns the list of routes in the given string.
	 * 
	 * @param cities
	 * @return
	 * @throws RouteException
	 */
	private Set<String> getRutes(final String cities)throws RouteException{
		Set<String> routes = null;
		if(null!=cities) {
			final char[] chArr = cities.toCharArray();
			if(null!=chArr && chArr.length>1){
				routes = new HashSet<String>();
				for(int i=0;i<(chArr.length-1);i++) {
					final String key=String.format("%c-%c", chArr[i],chArr[(i+1)]);
					routes.add(key);
				}
			}
		}
		return routes;
	}
	
	/**
	 * Find all routes.
	 * 
	 * @param source
	 * @param dest
	 * @return
	 * @throws RouteException
	 */
	private List<String> findAllRutes(final String source,final String dest) throws RouteException{
        
		List<String> finalCityRoutes = null;
		final List<String> fianlRoutes = this.findRutes(source, dest);
		
		finalCityRoutes = new ArrayList<String>();
		finalCityRoutes.addAll(fianlRoutes);
		// restet the Source as destination to find any possible routes from dest to dest.
		final Set<String> cities = routeMap.get(dest);
		for(String city:cities) {
			final List<String> addtionalRoutes = this.findRutes(city, dest);
			for(String additionalRoute : addtionalRoutes) {
				if(null!=fianlRoutes && !fianlRoutes.isEmpty()) {
					for(String route:fianlRoutes) {
						finalCityRoutes.add(String.format("%s-%s", route,additionalRoute));
					}
				}else {
					finalCityRoutes.add(String.format("%s-%s", city,additionalRoute));
				}
			}
		}
		if(null!=finalCityRoutes) {
			finalCityRoutes.remove(source);// remove unwanted value
		}
		return finalCityRoutes;
	}
	
	/**
	 * Find the routes from source to destination
	 * 
	 * @param source
	 * @param dest
	 * @return
	 * @throws RouteException
	 */
	private List<String> findRutes(final String source,final String dest) throws RouteException{
        
		Map<String,Boolean> reachedCity = null;
		List<String> routePaths = null;
		List<String> finalRoues = null;
			
		reachedCity = new HashMap<String,Boolean>(); 
		routePaths = new ArrayList<String>();
		finalRoues = new ArrayList<String>();
		
		routePaths.add(source);
		traverseRoute(source, dest, reachedCity, routePaths,finalRoues); // find all the possible routes until reach the destination.
		return finalRoues;
	}
	/**
	 * Find all the possible routes until reach the destination.
	 * 
	 * @param source
	 * @param dest
	 * @param rachedCity
	 * @param routePaths
	 * @param finalRoues
	 * @throws RouteException
	 */
	private void traverseRoute(String source, String dest, Map<String,Boolean> rachedCity, List<String> routePaths,List<String> finalRoues) throws RouteException{
		//Just initilize the starting city route
		rachedCity.put(source, Boolean.TRUE);

		if (source.equals(dest)) { 
			finalRoues.add(String.join("-",routePaths));
			rachedCity.put(source, Boolean.FALSE); // if reached then no need to traverse 
			return; 
		} 
		final Set<String> cities = routeMap.get(source);
		if(null!=cities) {
			for (String city : cities) { 
				final boolean isReached = null==rachedCity.get(city)?false:rachedCity.get(city);
				if (!isReached) { 
					routePaths.add(city); 
					traverseRoute(city, dest, rachedCity, routePaths,finalRoues); 
					routePaths.remove(city); 
				} 
			} 
			rachedCity.put(source, Boolean.FALSE);
		}
	}
	 
    
	/**
	 * Calculate and returns the connection city details.
	 * 
	 * @param routes
	 * @return
	 * @throws RouteException
	 */
	private Map<String,Set<String>> getNodeAndEdgesOfCityRoutes(final Map<String,Long> routes) throws RouteException{
		Map<String,Set<String>> connectRoute = null;
		
		if(null!=routes && !routes.isEmpty()) {
			connectRoute = new HashMap<String,Set<String>>();
			for(String str:routes.keySet()) {
				final char[] chArr = str.toCharArray();
				final String key = Character.toString(chArr[0]);
				if(!connectRoute.containsKey(key)) {
					connectRoute.put(key, new HashSet<String>());
				}
				connectRoute.get(key).add(Character.toString(chArr[2]));
			}
		}
		return connectRoute;
	}
	
}

