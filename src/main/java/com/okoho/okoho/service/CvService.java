package com.okoho.okoho.service;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.Circle;
import org.springframework.stereotype.Service;
import com.lowagie.text.pdf.PdfWriter;
import com.okoho.okoho.domain.Candidat;
import com.okoho.okoho.domain.FileUrl;
import com.okoho.okoho.repository.CandidatRepository;
import com.okoho.okoho.repository.FileUrlRepository;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class CvService {
    @Value("${base.domain.file}")
    private String domain;
    @Value("${files.path}")
    private String filesPath;
    @Autowired
    private FileService fileService;
    @Autowired
    private CandidatRepository candidatRepository;
    @Autowired
    private FileUrlRepository fileUrlRepository;

    public FileUrl generateCV(String idCandidat) throws DocumentException, MalformedURLException, IOException {
        Candidat candidat = candidatRepository.findById(idCandidat).get();
        Document document = new Document(PageSize.A4, 20, 20, 50, 20);
        
        Path file = Paths.get(filesPath);
        FileOutputStream pdfOutputFile = new FileOutputStream(filesPath + "/" + candidat.getId() + ".pdf");
    
        final PdfWriter pdfWriter = PdfWriter.getInstance(document, pdfOutputFile);
        Font font14 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
        document.open(); // Open the Document
        Phrase unicodes = new Phrase(15, "UNI\n", font14);
        Phrase characters = new Phrase(15, "\n", font14);
        Phrase names = new Phrase(15, "NAME\n", font14);

        for (int i = 0; i < 27; i++) {
            unicodes.add(uni[i] + "\n");
            characters.add(code[i] + "\n");
            names.add(name[i] + "\n");
        }

        // we grab the ContentByte and do some stuff with it
        PdfContentByte cb = pdfWriter.getDirectContent();
        ColumnText ct = new ColumnText(cb);
        cb.rectangle(1, 0, 150, document.getPageSize().getHeight());
        cb.stroke();
        Circle circle=new Circle(2.0,2.0,5);

        cb.setCMYKColorFill(233,25,14,0);
        ct.setSimpleColumn(characters, 1, 1, 1, 300 + 28 * 15, 15, Element.ALIGN_LEFT);
        ct.go();


        //ct.setSimpleColumn(names, 160, 300, 500, 300 + 28 * 15, 15, Element.ALIGN_LEFT);
       // ct.go();
        document.addTitle("CV-OKOHO-"+candidat.getUserAccount().getFirstName());

        //Image jpg = Image.getInstance(new URL("/examples/com/lowagie/examples/html/otsoe.jpg"));
        //document.add(jpg);
        HeaderFooter header = new HeaderFooter(new Phrase("This is a header."), false);
        HeaderFooter footer = new HeaderFooter(new Phrase("This is a footer on page "), new Phrase("."));

        document.setHeader(header);
        document.setFooter(footer);
        document.close(); // 5) Close the Document

            pdfWriter.close();// 6) close the File writer
        var pdf = "";

        var fileurl = new FileUrl();
        fileurl.setName(candidat.getUserAccount().getFirstName());
        fileurl.setDomain(domain + "cvs/");
        fileurl.setUrl(domain + "" + candidat.getId()+".pdf");
        return fileurl;
    }
    /** a string array we want to output. */
    public static String[] uni = new String[27];
    /** a string array we want to output. */
    public static String[] code = new String[27];
    /** a string array we want to output. */
    public static String[] name = new String[27];

    static {
        uni[0]="\\u0152";
        code[0]="\u0152";
        name[0]="LATIN CAPITAL LIGATURE OE";

        uni[1]="\\u0153";
        code[1]="\u0153";
        name[1]="LATIN SMALL LIGATURE OE";

        uni[2]="\\u0160";
        code[2]="\u0160";
        name[2]="LATIN CAPITAL LETTER S WITH CARON";

        uni[3]="\\u0161";
        code[3]="\u0161";
        name[3]="LATIN SMALL LETTER S WITH CARON";

        uni[4]="\\u0178";
        code[4]="\u0178";
        name[4]="LATIN CAPITAL LETTER Y WITH DIAERESIS";

        uni[5]="\\u017D";
        code[5]="\u017D";
        name[5]="LATIN CAPITAL LETTER Z WITH CARON";

        uni[6]="\\u017E";
        code[6]="\u017E";
        name[6]="LATIN SMALL LETTER Z WITH CARON";

        uni[7]="\\u0192";
        code[7]="\u0192";
        name[7]="LATIN SMALL LETTER F WITH HOOK";

        uni[8]="\\u02C6";
        code[8]="\u02C6";
        name[8]="MODIFIER LETTER CIRCUMFLEX ACCENT";

        uni[9]="\\u02DC";
        code[9]="\u02DC";
        name[9]="SMALL TILDE";

        uni[10]="\\u2013";
        code[10]="\u2013";
        name[10]="EN DASH";

        uni[11]="\\u2014";
        code[11]="\u2014";
        name[11]="EM DASH";

        uni[12]="\\u2018";
        code[12]="\u2018";
        name[12]="LEFT SINGLE QUOTATION MARK";

        uni[13]="\\u2019";
        code[13]="\u2019";
        name[13]="RIGHT SINGLE QUOTATION MARK";

        uni[14]="\\u201A";
        code[14]="\u201A";
        name[14]="SINGLE LOW-9 QUOTATION MARK";

        uni[15]="\\u201C";
        code[15]="\u201C";
        name[15]="LEFT DOUBLE QUOTATION MARK";

        uni[16]="\\u201D";
        code[16]="\u201D";
        name[16]="RIGHT DOUBLE QUOTATION MARK";

        uni[17]="\\u201E";
        code[17]="\u201E";
        name[17]="DOUBLE LOW-9 QUOTATION MARK";

        uni[18]="\\u2020";
        code[18]="\u2020";
        name[18]="DAGGER";

        uni[19]="\\u2021";
        code[19]="\u2021";
        name[19]="DOUBLE DAGGER";

        uni[20]="\\u2022";
        code[20]="\u2022";
        name[20]="BULLET";

        uni[21]="\\u2026";
        code[21]="\u2026";
        name[21]="HORIZONTAL ELLIPSIS";

        uni[22]="\\u2030";
        code[22]="\u2030";
        name[22]="PER MILLE SIGN";

        uni[23]="\\u2039";
        code[23]="\u2039";
        name[23]="SINGLE LEFT-POINTING ANGLE QUOTATION MARK";

        uni[24]="\\u203A";
        code[24]="\u203A";
        name[24]="SINGLE RIGHT-POINTING ANGLE QUOTATION MARK";

        uni[25]="\\u20AC";
        code[25]="\u20AC";
        name[25]="EURO SIGN";

        uni[26]="\\u2122";
        code[26]="\u2122";
        name[26]="TRADE MARK SIGN";
    }
}
