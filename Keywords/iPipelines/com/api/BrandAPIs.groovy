package iPipelines.com.api

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.logging.KeywordLogger

import internal.GlobalVariable

class BrandAPIs extends BaseAPI {

	/******************************************************************************************************/
	/******************************************      Arrange      *****************************************/
	/******************************************************************************************************/
	private static final KeywordLogger logger = KeywordLogger.getInstance(BrandAPIs)

	/******************************************************************************************************/
	/*******************************************      Acts      *******************************************/
	/******************************************************************************************************/

	/**
	 * Create a new brand by sending a POST request to the brands endpoint.
	 * @param brandName - The name of the brand to be created.
	 * @param slugPrefix - The slug prefix for the brand.
	 * @return Object - The parsed JSON response containing the created brand.
	 * 
	 * @author namhoang
	 */
	@Keyword
	def static Object createNewBrand(String brandName, String slugPrefix) {
		def slug = slugPrefix.concat(slugSuffix)
		def response = WS.sendRequest(findTestObject('Object Repository/Brand/CREATE a new brand', ["brandName": brandName, "slug": slug]))
		jsonResponse = jsonSlurper.parseText(response.getResponseText())
		GlobalVariable.brandID = jsonResponse.id
		return jsonResponse
	}

	/**
	 * Retrieves all existing brands by sending a GET request to the brands endpoint.
	 * @return Object - The parsed JSON response containing all existing brands.
	 * 
	 * @author namhoang
	 */
	@Keyword
	def static Object getAllExistingBrands() {
		def response = WS.sendRequest(findTestObject('Object Repository/Brand/GET all existing brands'))
		jsonResponse = jsonSlurper.parseText(response.getResponseText())
		return jsonResponse
	}

	/**
	 * Retrieves a existing brand by sending a GET request to the brands endpoint with brand id.
	 * @param brandName - The id of the created brand.
	 * @return Object - The parsed JSON response containing all existing brands.
	 * 
	 * @author namhoang
	 */
	@Keyword
	def static Object getBrandByID(String brandID) {
		def response = WS.sendRequest(findTestObject('Object Repository/Brand/GET a brand by id', ["brandID": brandID]))
		jsonResponse = jsonSlurper.parseText(response.getResponseText())
		return jsonResponse
	}

	/******************************************************************************************************/
	/*******************************************     Asserts    *******************************************/
	/******************************************************************************************************/
	
	/**
	 * Validate Brand response
	 * @param expectedBrandName - The name of the brand to be created.
	 * @param expectedSlugPrefix - The slug prefix for the brand.
	 *
	 * @author namhoang
	 */
	@Keyword
	def static validateBrandResponse(String expectedBrandName, String expectedSlugPrefix) {
		if (jsonResponse.size() == 0) {
			logger.logError("Response is empty")
			throw new Exception("Response is empty")
		}
		def expectedSlug = expectedSlugPrefix.concat(slugSuffix)
		assert jsonResponse.id != "" : "Brand ID is empty"
		assert jsonResponse.name == expectedBrandName : "Brand name does not match"
		assert jsonResponse.slug == expectedSlug : "Slug does not match"
		logger.logInfo("Response validated successfully.")
	}
}