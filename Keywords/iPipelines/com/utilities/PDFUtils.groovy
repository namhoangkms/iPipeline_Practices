package iPipelines.com.utilities

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper
import org.apache.pdfbox.rendering.PDFRenderer
import com.kms.katalon.core.annotation.Keyword
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

class PDFUtils {

	@Keyword
	static String extractTextFromPDF(String filePath) {
		PDDocument pdfDocument = null
		String pdfContent = ""
		try {
			pdfDocument = PDDocument.load(new File(filePath))
			PDFTextStripper pdfStripper = new PDFTextStripper()
			pdfContent = pdfStripper.getText(pdfDocument)
		} catch (Exception e) {
			println "Error reading PDF: " + e.getMessage()
		} finally {
			if (pdfDocument != null) {
				pdfDocument.close()
			}
		}
		return pdfContent
	}

	@Keyword
	static boolean verifyPDFContent(String filePath, String expectedContent) {
		String pdfContent = extractTextFromPDF(filePath)
		return pdfContent.contains(expectedContent)
	}

	static List<File> convertPdfToImages(File pdfFile) {
		List<File> imageFiles = []
		PDDocument document = PDDocument.load(pdfFile)
		PDFRenderer pdfRenderer = new PDFRenderer(document)

		for (int page = 0; page < document.getNumberOfPages(); page++) {
			BufferedImage image = pdfRenderer.renderImageWithDPI(page, 300)
			File imageFile = File.createTempFile("page_" + page, ".png")
			ImageIO.write(image, "png", imageFile)
			imageFiles.add(imageFile)
		}

		document.close()
		return imageFiles
	}
}
