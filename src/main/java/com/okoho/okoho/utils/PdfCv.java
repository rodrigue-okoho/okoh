package com.okoho.okoho.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;



@Component
public class PdfCv {

    private static final Logger log = Logger.getLogger("");
    /* Fonts for lines */
    private static final Font nameStyle = new Font(Font.HELVETICA, 12f, Font.BOLD);
    private static final Font emptyLineStyle = new Font(Font.HELVETICA, 4f, Font.BOLD);
    private static final Font headingStyle = new Font(Font.HELVETICA, 11f, Font.BOLD);
    private static final Font contactStyle = new Font(Font.HELVETICA, 11f, Font.ITALIC);
    private static final Font contentStyle = new Font(Font.HELVETICA, 10f);

    
}
