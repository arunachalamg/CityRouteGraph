package com.train.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.train.constant.City;
import com.train.exception.RouteException;

/**
 * Holds all common Utilities for the application
 * 
 * @author Arun G 
 *
 */
public class CommonUtil {
	private static final Logger LOGGER = Logger.getLogger(CommonUtil.class.getName());

	
	/**
	 * Check String is null or empty.
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		if(null==str || str.trim().length()==0) {
			return true;
		}
		return false;
	}	
	
	/**
	 * Check String is null or empty.
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}  
	
	/**
	 * Check he given city is valid.
	 * 
	 * @param ch
	 * @return
	 * @throws RouteException
	 */
	public static boolean isValidateCity(char ch) throws RouteException{
		try {
			 City.valueOf(Character.toString(ch));
			 return true;
		}catch(Exception e) {
		//log
		}
		return false;
	}
	
	/**
	 * Read files.
	 * @param filePath
	 * @return
	 */
	public static String readFile(String filePath) {
		String line      = null;
		StringBuilder sb = null;
		sb = new StringBuilder();
		BufferedReader bufferedReader = null;
		if(null!=filePath){
			 try {
				  URL urlRes = CommonUtil.class.getClassLoader().getResource(filePath);
			       FileReader fileReader = new FileReader(urlRes.getFile());
	               bufferedReader = new BufferedReader(fileReader);
	             
	               while((line = bufferedReader.readLine()) != null) {
		           		sb.append(line);
		            }   
			} catch (Exception e) {
				LOGGER.log(Level.WARNING,"readFile(): Could not load the File ["+filePath + "] Error occured "+e.getMessage());
			}finally{
				if(null!=bufferedReader){
					try {
						bufferedReader.close();
					} catch (IOException e) {
						LOGGER.log(Level.SEVERE,"readFile(): Could not read the File ["+filePath + "] Error occured "+e.getMessage());;
					}
				}
			}
		}
		return sb.toString();
	}
	
}
