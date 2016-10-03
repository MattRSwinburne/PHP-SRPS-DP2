package php;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JTable;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFOperations {

	private String filepath;

	public PDFOperations(String filepath) {
		this.filepath = filepath;		
	}	 

	public void createPdf(String filename)
			throws DocumentException, IOException {
		// step 1
		Document document = new Document();
		// step 2
		PdfWriter.getInstance(document, new FileOutputStream(filename));
		// step 3
		document.open();
		// step 4
		document.add(new Paragraph("Hello World!"));
		// step 5
		document.close();
	}

	public void createMonthlySalesPDF(JTable table) {

		try {
			Document doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream(filepath + ".pdf"));
			doc.open();
			doc.addTitle("People Health Pharmacy Monthly Sales Report");

			Paragraph preface = new Paragraph("People Health Pharmacy Monthly Sales Report"); 
			preface.setAlignment(Element.ALIGN_CENTER);
			doc.add(preface);

			doc.add(Chunk.NEWLINE);

			PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
			//adding table headers
			for (int i = 0; i < table.getColumnCount(); i++) {
				pdfTable.addCell(table.getColumnName(i));
			}
			//extracting data from the JTable and inserting it to PdfPTable
			for (int rows = 0; rows < table.getRowCount() - 1; rows++) {
				for (int cols = 0; cols < table.getColumnCount(); cols++) {
					pdfTable.addCell(table.getModel().getValueAt(rows, cols).toString());

				}
			}
			doc.add(pdfTable);
			doc.close();
		} catch (DocumentException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}

	}

	public void createWeeklySalesPDF(JTable table) {

		try {
			Document doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream(filepath + ".pdf"));
			doc.open();
			doc.addTitle("People Health Pharmacy Weekly Sales Report");

			Paragraph preface = new Paragraph("People Health Pharmacy Weekly Sales Report"); 
			preface.setAlignment(Element.ALIGN_CENTER);
			doc.add(preface);

			doc.add(Chunk.NEWLINE);

			PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
			//adding table headers
			for (int i = 0; i < table.getColumnCount(); i++) {
				pdfTable.addCell(table.getColumnName(i));
			}
			//extracting data from the JTable and inserting it to PdfPTable
			for (int rows = 0; rows < table.getRowCount() - 1; rows++) {
				for (int cols = 0; cols < table.getColumnCount(); cols++) {
					pdfTable.addCell(table.getModel().getValueAt(rows, cols).toString());

				}
			}
			doc.add(pdfTable);
			doc.close();
		} catch (DocumentException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}

	}

	public void createPredictionPDF(BufferedImage image)
	{
		try {
			Document doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream(filepath + ".pdf"));
			doc.open();
			doc.addTitle("People Health Pharmacy Sales Prediction Report");
			
			Paragraph preface = new Paragraph("People Health Pharmacy Sales Prediction Report"); 
			preface.setAlignment(Element.ALIGN_CENTER);
			doc.add(preface);
			
			doc.add(Chunk.NEWLINE);
			
			// convert the buffered image to an iText image
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(image, "png", baos);
				Image iTextImage = Image.getInstance(baos.toByteArray());
				iTextImage.setAlignment(Image.ALIGN_CENTER);
				doc.add(iTextImage);
			} catch (IOException ImageEx) {
				ImageEx.printStackTrace();
			}
			doc.close();
			
		} catch (DocumentException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
	}
}
