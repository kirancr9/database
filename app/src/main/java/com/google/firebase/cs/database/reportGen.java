package com.google.firebase.cs.database;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class reportGen extends AppCompatActivity {

    private String demo="now";
    static EditText edSE;
    static EditText edE;
    static EditText edTO;
    static EditText edEC;
    static EditText edSEC;
    static EditText edTOC;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_gen);



    }


    public void onGen(View v){

        edSE = (EditText) findViewById(R.id.edV1);
        edE = (EditText) findViewById(R.id.edV2);
        edTO = (EditText) findViewById(R.id.edV3);
        edEC = (EditText) findViewById(R.id.edV4);
        edSEC = (EditText) findViewById(R.id.edV5);
        edTOC = (EditText) findViewById(R.id.edV6);





        String FILE = Environment.getExternalStorageDirectory().toString()
                + "/PDF/" + "What.pdf";

        // Add Permission into Manifest.xml
        // <uses-permission
        // android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>

        // Create New Blank Document
        Document document = new Document(PageSize.A4);

        // Create Directory in External Storage
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/PDF");
        myDir.mkdirs();

        // Create Pdf Writer for Writting into New Created Document
        try {
            PdfWriter.getInstance(document, new FileOutputStream(FILE));


            // Open Document for Writting into document
            document.open();

            // User Define Method
            addMetaData(document);
            addTitlePage(document);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Close Document after writting all content
        document.close();

        Toast.makeText(this, "PDF File is Created. Location : " + FILE,
                Toast.LENGTH_LONG).show();

    }


    public void addMetaData(Document document)

    {
        document.addTitle("Calibration Certificate");
        document.addSubject("Person Info");
        document.addKeywords("Personal,	Education, Skills");
        document.addAuthor("TAG");
        document.addCreator("TAG");
    }


    public void addTitlePage(Document document) throws DocumentException {
        // Font Style for Document
        Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD
                | Font.UNDERLINE);
        Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

        // Start New Paragraph
        Paragraph prHead = new Paragraph();
        Paragraph prHead1 = new Paragraph();
        Paragraph prPara1 = new Paragraph();
        Paragraph prPara2 = new Paragraph();
        Paragraph prPara3 = new Paragraph();
        Paragraph prPara4 = new Paragraph();
        // Set Font in this Paragraph
        prHead.setFont(titleFont);
        // Add item into Paragraph
        prHead1.setFont(titleFont);
        prHead1.setAlignment(Paragraph.ALIGN_CENTER);
        prHead1.add("Calibration Certificate\n");


        // Add Cell into Table

        prHead.setFont(smallBold);
        prHead.add("\nCertificate No: MES/PRS/DFB/C1                                                                       ");
        prHead.setAlignment(Element.ALIGN_LEFT);
        prHead.add("\tDate Of Issue: 20/07/2016\n");
        prPara1.setFont(smallBold);
        prPara1.add("Name of Customer: PRS Hospital, Trivandrum.\n");
        prPara2.add("Certified that the above instrument has been calibrated by trained technical personnel of this organization using measuring equipment traceable to national standards\n");


        prPara3.setAlignment(Paragraph.ALIGN_CENTER);
        prPara3.add("Details of Test Equipment Used\n");
        prPara4.setAlignment(Paragraph.ALIGN_CENTER);
        prPara4.add("Test and Calibration Data\n");

        // Add all above details into Document
        document.add(prHead1);
        document.add(prHead);
        document.add(prPara1);
        document.add( new Phrase("\n") );


        PdfPTable table = createTable1();
        document.add(table);
        document.add(prPara2);
        document.add( new Phrase("\n") );
        document.add(prPara3);
        table = createTable2();
        table.setSpacingBefore(5);
        table.setSpacingAfter(5);
        document.add(table);
        document.add( new Phrase("\n") );
        document.add(prPara4);
        table = createTable3();
        document.add(table);



        // Now Start another New Paragraph
        Paragraph prPersinalInfo = new Paragraph();
        prPersinalInfo.setFont(smallBold);
        prPersinalInfo.add("Charge Time Test :  For 200 Joules is 4.3 Secs \n");
        prPersinalInfo.setAlignment(Element.ALIGN_LEFT);
        prPersinalInfo.add("Sync Test : Passed\n");
        prPersinalInfo.setAlignment(Element.ALIGN_RIGHT);
        prPersinalInfo.add("NB: Results reported are valid at the time of and under the stated conditions of measurements. This report shall not be reproduced without written approval by Medical Engineering & Services.\n");
        prPersinalInfo.add("Calibration Done  By: Yadhukrishnan K.V (Service Engineer)\n");
        document.add( new Phrase("\n") );
        prPersinalInfo
                .add("Approved By: Binoj Thomas (Quality Manager \n");

        prPersinalInfo.setAlignment(Element.ALIGN_CENTER);

        document.add(prPersinalInfo);

        Paragraph prProfile = new Paragraph();
        prProfile.setAlignment(Element.ALIGN_RIGHT);
        prProfile.setFont(normal);

        prProfile.add("\n Page 1 of 2 ");
        prProfile.setFont(smallBold);
        document.add( new Phrase("\n") );
        document.add(prProfile);

        // Create new Page in PDF

        document.newPage();
    }

    public static PdfPTable createTable1() throws DocumentException {
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(480 / 5.23f);
        table.setWidths(new int[]{1, 1, 1});
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Equipment"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Make / Model"));
        table.addCell(cell);
        table.addCell("Serial / Id / Location");
        table.addCell("Defibrillator");
        table.addCell("BPL / Relife 700");
        table.addCell("EETA5C1084 / BME 1100 / Emergency Department");
        cell = new PdfPCell(new Phrase("Date Of Calibration"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Calibration Due On"));
        cell.setColspan(2);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("09/07/2016"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("08/07/2017"));
        cell.setColspan(2);
        table.addCell(cell);
        return table;
    }

    /**
     * Creates a table; widths are set with setWidths().
     * @return a PdfPTable
     * @throws DocumentException
     */
    public static PdfPTable createTable2() throws DocumentException {
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(480 / 5.23f);
        table.setWidths(new int[]{1, 1, 1,1,1,1});
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Sl No"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Test Equipment"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Make / Model"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Serial No"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Certificate no"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Due Date"));
        table.addCell(cell);
        table.addCell("1");
        table.addCell("Defibrillator");
        table.addCell("Fluke / Impulse 7000DP");
        table.addCell("2440051");
        table.addCell("1606591-IMPULSE 7000DP-2440051-1");
        table.addCell("22-06-2017");

        table.addCell("2");
        table.addCell("Thermo Hygrometer");
        table.addCell("CTH");
        table.addCell("288");
        table.addCell("ACS/TH/16-17/10510-01");
        table.addCell("14-06-2017");

        table.addCell("3");
        table.addCell("Defibrillator");
        table.addCell("Fluke / Impulse 7000DP");
        table.addCell("2440051");
        table.addCell("1606591-IMPULSE 7000DP-2440051-1");
        table.addCell("22-06-2017");

        return table;
    }

    /**
     * Creates a table; widths are set in the constructor.
     * @return a PdfPTable
     * @throws DocumentException
     */
    public static PdfPTable createTable3() throws DocumentException {

        final String edse = edSE.getText().toString();
        final String ede = edE.getText().toString();
        final String edto = edTO.getText().toString();
        final String edec = edEC.getText().toString();
        final String edsec = edSEC.getText().toString();
        final String edtoc = edTOC.getText().toString();

        PdfPTable table = new PdfPTable(6);
        table.setWidths(new int[]{1, 1, 1,1,1,1});
        table.setWidthPercentage(480 / 5.23f);
        PdfPCell cell;
        cell = new PdfPCell(new Phrase("Set Energy"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Energy"));
        table.addCell(cell);
        table.addCell("Tolerance");
        table.addCell("ECG");
        table.addCell("Set ECG");
        table.addCell("Tolerance");
        table.addCell(edse);
        table.addCell(ede);
        table.addCell(edto);
        table.addCell(edec);
        table.addCell(edsec);
        table.addCell(edtoc);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("09/07/2016"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("09/07/2016"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("09/07/2016"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("09/07/2016"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("09/07/2016"));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("09/07/2016"));
        table.addCell(cell);

        return table;
    }



}
