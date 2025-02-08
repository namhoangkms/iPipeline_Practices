package iPipelines.com.utilities

import com.google.cloud.vision.v1.ImageAnnotatorClient
import com.google.cloud.vision.v1.Image
import com.google.cloud.vision.v1.AnnotateImageRequest
import com.google.cloud.vision.v1.AnnotateImageResponse
import com.google.cloud.vision.v1.Feature
import com.google.cloud.vision.v1.TextAnnotation
import com.google.protobuf.ByteString
import com.kms.katalon.core.annotation.Keyword
import java.nio.file.Files
import java.nio.file.Paths
import iPipelines.com.utilities.PDFUtils

class GoogleVisionUtils {

	/**
	 * Extract text from an image using Google Vision API
	 * @param imagePath Path to the image file (e.g., PNG, JPEG)
	 * @return the extracted text from the image
	 */
	@Keyword
	static String extractTextFromImage(String imagePath) {
		String extractedText = ""

		try {
			byte[] data = Files.readAllBytes(Paths.get(imagePath))
			ByteString imgBytes = ByteString.copyFrom(data)
			Image img = Image.newBuilder().setContent(imgBytes).build()
			Feature feat = Feature.newBuilder().setType(Feature.Type.DOCUMENT_TEXT_DETECTION).build()
			AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
					.addFeatures(feat)
					.setImage(img)
					.build()
			ImageAnnotatorClient client = ImageAnnotatorClient.create()

			def response = client.batchAnnotateImages([request])
			TextAnnotation textAnnotation = response.responses[0].textAnnotations[0]
			extractedText = textAnnotation.getDescription()

			client.close()
		} catch (Exception e) {
			println "Error during OCR: " + e.getMessage()
		}

		return extractedText
	}

	static String extractTextFromImage(File imageFile, String credentialsPath) {
		System.setProperty("GOOGLE_APPLICATION_CREDENTIALS", credentialsPath)
		ByteString imgBytes = ByteString.copyFrom(Files.readAllBytes(imageFile.toPath()))
		Image img = Image.newBuilder().setContent(imgBytes).build()
		Feature feat = Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION).build()
		AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
				.addFeatures(feat)
				.setImage(img)
				.build()
		ImageAnnotatorClient visionClient = ImageAnnotatorClient.create()
		AnnotateImageResponse response = visionClient.batchAnnotateImages([request])
		.getResponses(0)
		visionClient.close()

		if (response.hasError()) {
			throw new RuntimeException("Vision API Error: " + response.getError().getMessage())
		}
		return response.getTextAnnotationsList().get(0).getDescription()
	}

	@Keyword
	static verifyPdfContent(String pdfFilePath, String expectedText, String credentialsPath) {
		File pdfFile = new File(pdfFilePath)
		List<File> imageFiles = PDFUtils.convertPdfToImages(pdfFile)
		StringBuilder extractedText = new StringBuilder()
		imageFiles.each { image ->
			extractedText.append(extractTextFromImage(image, credentialsPath))
		}
		println "Expected Text: '" + expectedText + "'"
		println "Extracted Text: '" + extractedText + "'"
		assert extractedText.toString().contains(expectedText) : "Expected text not found in PDF"
	}
}