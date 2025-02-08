package iPipelines.com.utilities;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PdfToImage {

	public static void convertPdfToImages(String pdfFilePath, String outputDirectory) throws IOException {
		PDDocument document = PDDocument.load(new File(pdfFilePath));
		PDFRenderer pdfRenderer = new PDFRenderer(document);
		int numPages = document.getNumberOfPages();
		for (int page = 0; page < numPages; page++) {
			BufferedImage image = pdfRenderer.renderImageWithDPI(page, 300);
			File outputFile = new File(outputDirectory, "page_" + (page + 1) + ".png");
			ImageIO.write(image, "PNG", outputFile);
		}

		document.close();
	}
}