/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

/**
 *
 * @author Coutso
 */
import net.sf.saxon.TransformerFactoryImpl;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.apache.fop.apps.FOUserAgent;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class XmlToPdfConverter {

    public static void main(String[] args) {
        try {
            // Chemins des fichiers
            String xmlFile = "source.xml";
            String xsltFile = "transform.xsl";
            String foFile = "output.fo";
            String pdfFile = "output1.pdf";

            // Transformer XML en XSL-FO
            transformXmlToFo(xmlFile, xsltFile, foFile);

            // Convertir XSL-FO en PDF
            convertFoToPdf(foFile, pdfFile);

            System.out.println("Transformation et conversion reussies!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Une erreur est survenue: " + e.getMessage());
        }
    }

    public static void transformXmlToFo(String xmlFile, String xsltFile, String foFile) throws TransformerException {
        TransformerFactory factory = new TransformerFactoryImpl();
        Transformer transformer = factory.newTransformer(new StreamSource(new File(xsltFile)));
        transformer.transform(new StreamSource(new File(xmlFile)), new StreamResult(new File(foFile)));
        System.out.println("Transformation XML en XSL-FO reussie.");
    }

    public static void convertFoToPdf(String foFile, String pdfFile) throws Exception {
        // Initialiser FOP
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();

        // Configurer la sortie PDF
        OutputStream out = new FileOutputStream(new File(pdfFile));
        out = new java.io.BufferedOutputStream(out);
        try {
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

            // Charger le fichier XSL-FO
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            StreamSource src = new StreamSource(new File(foFile));
            SAXResult res = new SAXResult(fop.getDefaultHandler());

            // Transformer XSL-FO en PDF
            transformer.transform(src, res);
            System.out.println("Conversion XSL-FO en PDF reussie.");
        } finally {
            out.close();
        }
    }
}

