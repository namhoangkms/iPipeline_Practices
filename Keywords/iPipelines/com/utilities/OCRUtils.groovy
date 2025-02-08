package iPipelines.com.utilities

import net.sourceforge.tess4j.Tesseract
import net.sourceforge.tess4j.TesseractException
import com.kms.katalon.core.annotation.Keyword
import java.io.File

class TesseractUtils {

	/**
	 * Extract text from an image using Tesseract OCR
	 * @param imagePath Path to the image file
	 * @return the extracted text from the image
	 */
	@Keyword
	static String extractTextFromImage(String imagePath) {
		String extractedText = ""

		try {
			Tesseract tesseract = new Tesseract()
			tesseract.setLanguage("eng")
			File imgFile = new File(imagePath)
			extractedText = tesseract.doOCR(imgFile)
		} catch (TesseractException e) {
			e.printStackTrace()
		}

		return extractedText
	}
}
