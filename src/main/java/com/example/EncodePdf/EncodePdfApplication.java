package com.example.EncodePdf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;



@SpringBootApplication
public class EncodePdfApplication {

    private static final boolean IS_CHUNKED = true;

    public static void main(String args[]) throws Exception {
    	  String[] headers = new String[]{ "id", "Username", "First Name", "Last Name" };
          String[][] rows = new String[][]{
                  {"1", "abc", "Jaa", "dss"},
                  {"2", "def", "Ssas", "rrw"},
                  {"4", "ghi", "fsa", "dcjru"},
                  {"5", "ghi", "fsa", "dcjru"},
                  {"6", "ghi", "fsa", "dcjru"},
                  {"7", "ghi", "fsa", "dcjru"},
                  {"8", "ghi", "fsa", "dcjru"},
                  {"9", "ghi", "fsa", "dcjru"},
                  {"13", "ghi", "fsa", "dcjru"},
                  {"23", "ghi", "fsa", "dcjru"},
                  {"33", "ghi", "fsa", "dcjru"},
                  {"43", "ghi", "fsa", "dcjru"},
                  {"454", "ghi", "fsa", "dcjru"}
          };
          Document document = new Document(PageSize.LETTER.rotate());
         

          try {
              
              PdfWriter.getInstance(document,new FileOutputStream(new File("Tablesa.pdf")));
              document.open();
                 Font fontHeader = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
              Font fontRow = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);

              PdfPTable table = new PdfPTable(headers.length);
              for (String header : headers) {
                  PdfPCell cell = new PdfPCell();
                  cell.setGrayFill(0.9f);
                  cell.setPhrase(new Phrase(header.toUpperCase(), fontHeader));
                  table.addCell(cell);
              }
              table.completeRow();

              for (String[] row : rows) {
                  for (String data : row) {
                      Phrase phrase = new Phrase(data, fontRow);
                      table.addCell(new PdfPCell(phrase));
                  }
                  table.completeRow();
              }

              document.addTitle("Sample Table");
              document.add(table);
              
              String filePath = "C:\\Users\\P.Banushan\\Documents\\workspace-sts-3.9.8.RELEASE\\EncodePdf\\Tables.pdf";
              
              String newFileName = "test.pdf";
              byte[] input_file = Files.readAllBytes(Paths.get(filePath));

              byte[] encodedBytes = Base64.getEncoder().encode(input_file);
              String encodedString =  new String(encodedBytes);
              byte[] decodedBytes = Base64.getDecoder().decode(encodedString.getBytes());
              	System.out.println("code is"+encodedString);
              FileOutputStream fos = new FileOutputStream(filePath+newFileName);
              fos.write(decodedBytes);
              fos.flush();
              fos.close();
              
              
          } catch (DocumentException | FileNotFoundException e) {
              e.printStackTrace();
          } finally {
              document.close();
          }
    

 
}
}
