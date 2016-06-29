package com.RVS.Documentation;

import com.RVS.Quote;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Josh on 6/21/16.
 *
 * @author Josh Jennings
 */
public class PDFBoxPDFMaker {

	static public void createPDF(Quote quote) throws IOException {
		// Calculate dimension constants
		int pageWidth = 612;
		int pageHeight = 792;
		int margin = 20;

		int pageHalfWidth = pageWidth/2;
		int centerMargin = 20;

		int customerAddressBoxX = margin;
		int customerAddressBoxY = 550;
		int customerAddressBoxW = pageHalfWidth-margin-(centerMargin/2);
		int customerAddressBoxH = 100;
		int customerAddressBoxMargin = 0; //5

		int companyAddressBoxX = pageHalfWidth + centerMargin/2;
		int companyAddressBoxY = customerAddressBoxY;
		int companyAddressBoxW = customerAddressBoxW;
		int companyAddressBoxH = customerAddressBoxH;
		int companyAddressBoxMargin = customerAddressBoxMargin;
		int textHeight = 12;
		int spaceBetweenText = 2;

		int customerNameX = margin + customerAddressBoxMargin;
		int customerNameY = customerAddressBoxY + customerAddressBoxH - customerAddressBoxMargin - textHeight;
		int customerStreetX = customerNameX;
		int customerStreetY = customerNameY - textHeight - spaceBetweenText;
		int customerCityX = customerNameX;
		int customerCityY = customerStreetY - textHeight - spaceBetweenText;

		int companyNameX = companyAddressBoxX + companyAddressBoxMargin;
		int companyNameY = companyAddressBoxY + companyAddressBoxH - textHeight - companyAddressBoxMargin;
		int companyStreetX = companyNameX;
		int companyStreetY = companyNameY - textHeight - spaceBetweenText;
		int companyCityX = companyNameX;
		int companyCityY = companyStreetY - textHeight - spaceBetweenText;

		float quotationDetailsW = 150;
		float quotationNumberX = pageWidth - margin - quotationDetailsW;
		float quotationNumberY = pageHeight - margin - 100;
		float salespersonX = pageWidth - margin - quotationDetailsW;
		float salespersonY = quotationNumberY - textHeight - spaceBetweenText;

		// Prepare company logo
		BufferedImage image = ImageIO.read( new File( "images/rvsLogo.png" ) );
		float logoWidth = 200;
		float scale = logoWidth/((float) image.getWidth());
		float logoHeight = scale * image.getHeight();

		float logoX = 20;
		float logoY = pageHeight - margin - logoHeight;

		// Create a document and add a page to it
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage(page);

		// Create a new font object selecting one of the PDF base fonts
//		PDFont font = PDType1Font.HELVETICA_BOLD;

		// Start a new content stream which will "hold" the to be created content
		PDPageContentStream contentStream = new PDPageContentStream(document, page);

		// Add company address
		drawText(contentStream,logoX,logoY-textHeight-spaceBetweenText,"1520 Crosswind Dr.  Bryan, TX 77808");
		drawText(contentStream,logoX,logoY-2*textHeight-2*spaceBetweenText,"Phone: (979) 778-0095");

		// Add customer data
		drawText(contentStream,customerNameX,customerNameY,"Aerospace Incorporated");
		drawText(contentStream,customerStreetX,customerStreetY,"123 Anywhere Street");
		drawText(contentStream,customerCityX,customerCityY,"Willmar, MN 12345");

		// Add our company data
//		drawText(contentStream,companyNameX,companyNameY,"RVS");
//		drawText(contentStream,companyStreetX,companyStreetY,"987 Somewhere Street");
//		drawText(contentStream,companyCityX,companyCityY,"Bryan, TX 54321");
		drawText(contentStream,quotationNumberX,quotationNumberY,"Quotation: " + quote.getQuoteNumber());
		drawText(contentStream,salespersonX,salespersonY,"Prepared by: " + quote.getSalesperson());

		// Draw a rectangle
		// Draw client/company boxes
//		drawRect(contentStream,
//				Color.black,
//				new Rectangle(customerAddressBoxX,
//						customerAddressBoxY,
//						customerAddressBoxW,
//						customerAddressBoxH),
//				false);
//		drawRect(contentStream,
//				Color.black,
//				new Rectangle(companyAddressBoxX,
//						companyAddressBoxY,
//						companyAddressBoxW,
//						companyAddressBoxH),
//				false);
		drawRect(contentStream,
				Color.black,
				new Rectangle(margin,
						40,
						pageWidth - 2*margin,
						500),
				false);

		// Add logo
		//BufferedImage logo = ImageIO.read( new File( "images/planeLogo.png" ) );
		PDImageXObject logo = LosslessFactory.createFromImage(document, image);
		contentStream.drawImage(logo, logoX, logoY, logoWidth, logoHeight);

		// Make sure that the content stream is closed:
		contentStream.close();

		// Save the results and ensure that the document is properly closed:
		document.save("RVS Quote PDFBox.pdf");
		document.close();
	}

	private static void drawRect(PDPageContentStream content, Color color, Rectangle rect, boolean fill) {
		try {
			content.addRect(rect.x, rect.y, rect.width, rect.height);
			if (fill) {
				content.setNonStrokingColor(color);
				content.fill();
			} else {
				content.setStrokingColor(color);
				//content.setLineWidth(float lineWidth);
				content.stroke();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void drawText(PDPageContentStream contentStream, float locX, float locY, String textToAdd) {
		try {
			contentStream.beginText();
			contentStream.moveTextPositionByAmount(locX, locY);
			contentStream.setFont(PDType1Font.HELVETICA, 12);
			contentStream.drawString(textToAdd);
			contentStream.endText();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	private static void addImage() {
//		PDJpeg jpg = new PDJpeg
//	}
	
}
