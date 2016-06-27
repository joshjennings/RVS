package com.RVS.Documentation;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by manufacturing9 on 6/27/2016.
 *
 * @author Josh Jennings
 */
public class iTextPDFMaker {

	public static final String RESULT = "results/output.pdf";

	public static void createPDF(String filename) throws DocumentException, IOException {
		Document document = new Document(PageSize.LETTER);
		PdfWriter.getInstance(document,new FileOutputStream(filename));

		document.open();
		document.add(new Paragraph("Hello World!"));
		document.close();
	}
}
