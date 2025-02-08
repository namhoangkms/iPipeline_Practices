package iPipelines.com.api

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper

import iPipelines.com.utilities.Utils

public class BaseAPI {

	/******************************************************************************************************/
	/******************************************      Arrange      *****************************************/
	/******************************************************************************************************/
	public static JsonSlurper jsonSlurper = new JsonSlurper()
	public static Object jsonResponse = new Object()
	public static String slugSuffix = String.valueOf(Utils.generateRandomNumber())

	/******************************************************************************************************/
	/*******************************************      Acts      *******************************************/
	/******************************************************************************************************/


	/******************************************************************************************************/
	/*******************************************     Asserts    *******************************************/
	/******************************************************************************************************/
}
