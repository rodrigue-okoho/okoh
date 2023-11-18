package com.okoho.okoho.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;
import com.okoho.okoho.domain.Candidat;
import com.okoho.okoho.domain.FileUrl;
import com.okoho.okoho.repository.CandidatRepository;
import com.okoho.okoho.repository.FileUrlRepository;

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
        
        Paragraph p = new Paragraph();
        p.add("Nom: ");
        document.addTitle("Bulletin de paie");
        document.add(p);
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
}
