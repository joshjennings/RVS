package com.RVS.Documentation;

//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
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
public class PDFMaker {

	static public void createPDF() throws IOException {
		int pageWidth = 612;
		int pageHeight = 792;
		int margin = 20;

		int pageHalfWidth = pageWidth/2;
		int centerMargin = 20;

		int logoX = 10;
		int logoY = 670;

		int customerAddressBoxX = margin;
		int customerAddressBoxY = 550;
		int customerAddressBoxW = pageHalfWidth-margin-(centerMargin/2);
		int customerAddressBoxH = 100;
		int customerAddressBoxMargin = 5;

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

		// Create a document and add a page to it
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage(page);

		// Create a new font object selecting one of the PDF base fonts
		PDFont font = PDType1Font.HELVETICA_BOLD;

		// Start a new content stream which will "hold" the to be created content
		PDPageContentStream contentStream = new PDPageContentStream(document, page);

		// Define a text content stream using the selected font, moving the cursor and drawing the text "Hello World"
//		contentStream.beginText();
//		contentStream.setFont(font, 12);
//		contentStream.moveTextPositionByAmount(100, 700);
//		contentStream.drawString("Hello World");
//		contentStream.endText();
		drawText(contentStream,customerNameX,customerNameY,"Aerospace Incorporated");
		drawText(contentStream,customerStreetX,customerStreetY,"123 Anywhere Street");
		drawText(contentStream,customerCityX,customerCityY,"Willmar, MN 12345");

		drawText(contentStream,companyNameX,companyNameY,"RVS");
		drawText(contentStream,companyStreetX,companyStreetY,"987 Somewhere Street");
		drawText(contentStream,companyCityX,companyCityY,"Bryan, TX 54321");

		// Draw a rectangle
		// Draw client/company boxes
		drawRect(contentStream,
				Color.black,
				new Rectangle(customerAddressBoxX,
						customerAddressBoxY,
						customerAddressBoxW,
						customerAddressBoxH),
				false);
		drawRect(contentStream,
				Color.black,
				new Rectangle(companyAddressBoxX,
						companyAddressBoxY,
						companyAddressBoxW,
						companyAddressBoxH),
				false);
		drawRect(contentStream,
				Color.black,
				new Rectangle(margin,
						40,
						pageWidth - 2*margin,
						500),
				false);

		// Add image
		BufferedImage logo = ImageIO.read( new File( "images/planeLogo.png" ) );
		PDImageXObject image = LosslessFactory.createFromImage(document, logo);
		//PDPageContentStream contentStream = new PDPageContentStream(document, new PDPage(), true, false);
		contentStream.drawImage(image, logoX, logoY, logo.getWidth() / 2, logo.getHeight() / 2);

		// Make sure that the content stream is closed:
		contentStream.close();

		// Save the results and ensure that the document is properly closed:
		document.save("Hello World.pdf");
		document.close();
	}

	static void drawRect(PDPageContentStream content, Color color, Rectangle rect, boolean fill) {
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

	static void drawText(PDPageContentStream contentStream, float locX, float locY, String textToAdd) {
		try {
//			contentStream.moveTo(locX, locY);

			contentStream.beginText();
			contentStream.moveTextPositionByAmount(locX, locY);
//			contentStream.moveTo(locX, locY);
			contentStream.setFont(PDType1Font.HELVETICA, 12);
//			contentStream.moveTextPositionByAmount(100, 700);
			contentStream.drawString(textToAdd);
//			contentStream.
			contentStream.endText();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	static void addImage() {
//		PDJpeg jpg = new PDJpeg
//	}
	
}
