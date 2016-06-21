package com.RVS.Documentation;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.IOException;

/**
 * Created by Josh on 6/21/16.
 *
 * @author Josh Jennings
 */
public class PDFMaker {

	static public void createPDF() throws IOException {
		PDDocument document = new PDDocument();

		// Create a new blank page and add it to the document
		PDPage blankPage = new PDPage();
		document.addPage( blankPage );

		// Save the newly created document
		document.save("BlankPage.pdf");

		// finally make sure that the document is properly
		// closed.
		document.close();
	}
	
}
