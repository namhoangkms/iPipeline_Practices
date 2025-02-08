import iPipelines.com.utilities.OCRUtils
import iPipelines.com.utilities.PdfToImage

String pdfPath = "D:\\Projects\\iPipeline\\Repos\\iPipeline_Practices\\Data Files\\pdf\\test3.pdf"
String outputDir = "D:\\Projects\\iPipeline\\Repos\\iPipeline_Practices\\Data Files\\images"

PdfToImage.convertPdfToImages(pdfPath, outputDir)
File outputDirectory = new File(outputDir)

outputDirectory.eachFile { imageFile ->
    if (imageFile.name.endsWith(".png")) {
        String extractedText = OCRUtils.extractTextFromImage(imageFile.getAbsolutePath())
        println "Extracted Text from " + imageFile.name + ":\n" + extractedText        
        assert extractedText.contains("This content for testing 1ll0111l, 1OO9001, 3000380800") : "Text not found in the image"
    }
}