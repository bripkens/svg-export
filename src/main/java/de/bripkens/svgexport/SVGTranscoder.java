
package de.bripkens.svgexport;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.batik.transcoder.SVGAbstractTranscoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;

/**
 *
 * @author Ben Ripkens <bripkens.dev@gmail.com>
 */
public class SVGTranscoder extends SVGAbstractTranscoder {

    @Override
    protected void transcode(Document document, String uri,
            TranscoderOutput output) throws TranscoderException {
        try {
            super.transcode(document, uri, output);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Source xmlSource = new DOMSource(document);
            Result outputTarget = new StreamResult(outputStream);
            TransformerFactory.newInstance().newTransformer().
                    transform(xmlSource, outputTarget);
            InputStream inputStream;
            inputStream = new ByteArrayInputStream(outputStream.toByteArray());

            IOUtils.copy(inputStream, output.getOutputStream());
        } catch (IOException ex) {
            throw new SVGExportException(ex);
        } catch (TransformerException ex) {
            throw new SVGExportException(ex);
        }
    }

}
