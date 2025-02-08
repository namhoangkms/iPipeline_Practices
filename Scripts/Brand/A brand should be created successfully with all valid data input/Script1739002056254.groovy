import iPipelines.com.api.BrandAPIs
import internal.GlobalVariable

'Step 1: Create a new brand'
BrandAPIs.createNewBrand(brandName, slugPrefix)

'Step 2: Get a existing brand by id'
BrandAPIs.getBrandByID(GlobalVariable.brandID)

'Step 3: Verify the provided brand should be created successfully'
BrandAPIs.validateBrandResponse(brandName, slugPrefix)