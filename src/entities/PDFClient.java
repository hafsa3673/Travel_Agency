package entities;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.swing.text.Document;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;


public class PDFClient {


	private List<Client> listClient;

	public PDFClient(List<Client> listClt) {
		super();
		this.listClient = listClt;
	}

	public List<Client> getlistClient() {
		return listClient;
	}

	public void setlistClient(List<Client> listClient) {
		this.listClient = listClient;
	}
	private void writeTableHeader1(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.ORANGE);
        cell.setPadding(3);
         
        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Nom&Prenom", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Email", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Tel", font));
        table.addCell(cell);
         
             
    }
	private void writeTableData1(PdfPTable table) {
        for (Client user : listClient) {
            table.addCell(user.getNom());
            table.addCell(user.getEmail());
            table.addCell(user.getTel());
        }
    }
	private void writeTableHeader2(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.ORANGE);
        cell.setPadding(2);
         
        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Nom&Prenom", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Email", font));
        table.addCell(cell);
         
          
    }
	private void writeTableData2(PdfPTable table) {
        for (Client user : listClient) {
            table.addCell(user.getNom());
            table.addCell(user.getEmail());
            
        }
    }
	public void export1(HttpServletResponse response) throws DocumentException, IOException {
      //  Document document = new Document(PageSize.A4);
        com.lowagie.text.Document d = new com.lowagie.text.Document(PageSize.A4);
        PdfWriter.getInstance((com.lowagie.text.Document) d, response.getOutputStream());
         
        d.open();
       // Font f = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        com.lowagie.text.Font f = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        f.setSize(18);
        f.setColor(Color.ORANGE);
         
        Paragraph p = new Paragraph("Liste des clients ", f);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        d.add(p);
         
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3.5f, 3.5f, 3.0f});
        table.setSpacingBefore(10);
         
        writeTableHeader1(table);
        writeTableData1(table);
         
        d.add(table);
         
        d.close();
         
    }
	public void export2(HttpServletResponse response) throws DocumentException, IOException {
	      //  Document document = new Document(PageSize.A4);
	        com.lowagie.text.Document d = new com.lowagie.text.Document(PageSize.A4);
	        PdfWriter.getInstance((com.lowagie.text.Document) d, response.getOutputStream());
	         
	        d.open();
	       // Font f = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	        com.lowagie.text.Font f = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	        f.setSize(1000);
	        f.setColor(Color.ORANGE);
	         
	        Paragraph p = new Paragraph("Liste des clients ", f);
	        p.setAlignment(Paragraph.ALIGN_CENTER);
	         
	        d.add(p);
	         
	        PdfPTable table = new PdfPTable(3);
	        table.setWidthPercentage(100f);
	        table.setWidths(new float[] { 3.5f, 3.0f});
	        table.setSpacingBefore(10);
	         
	        writeTableHeader2(table);
	        writeTableData2(table);
	         
	        d.add(table);
	         
	        d.close();
	         
	    }
	
	
	

}