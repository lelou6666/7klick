import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.hwpf.HWPFDocumentCore;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.converter.WordToHtmlUtils;
import org.apache.poi.hwpf.model.TextPieceTable;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Test;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

/**
 * Created by pierre.petersson on 04/10/2014.
 */
public class DocumentConverterTest {
    @Test
    public void convertDocxToHTML() throws IOException, ParserConfigurationException {
        InputStream in= new FileInputStream(new File("C:/Privat/x.docx"));

        HWPFDocumentCore hwpfDocumentCore=WordToHtmlUtils.loadDoc(in);


        XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(new File("C:/Privat/out.docx")));

        OutputStream out = new ByteArrayOutputStream();



        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder()
                .newDocument());
        wordToHtmlConverter.processDocument(hwpfDocumentCore);


        //XHTMLConverter.getInstance().convert(document, out, options);
        String html=out.toString();
        System.out.println(html);
    }
}
