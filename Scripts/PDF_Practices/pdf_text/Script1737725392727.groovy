import iPipelines.com.utilities.PDFUtils

String pdfPath = "D:\\Projects\\iPipeline\\Repos\\iPipeline_Practices\\Data Files\\pdf\\test3.pdf"
String expectedText = "This content for testing 1ll0111l, 1OO9001, 3000380800"


String pdfContent = PDFUtils.extractTextFromPDF(pdfPath)
println "PDF Content: \n" + pdfContent
boolean isTextPresent = PDFUtils.verifyPDFContent(pdfPath, expectedText)
assert isTextPresent : "Expected text not found in the PDF."