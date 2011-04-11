package de.bripkens.svgexport;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.w3c.dom.Document;
import org.xml.sax.XMLFilter;
import org.xml.sax.XMLReader;

/**
 *
 * @author Ben Ripkens <bripkens.dev@gmail.com>
 */
public class SVGExport {

    private Transcoder transcoder;

    private TranscoderInput in;

    private TranscoderOutput out;

    public SVGExport() {
    }

    public TranscoderInput getInput() {
        return in;
    }

    public SVGExport setInput(TranscoderInput in) {
        this.in = in;
        return this;
    }

    public SVGExport setInput(Document doc) {
        this.in = new TranscoderInput(doc);
        return this;
    }

    public SVGExport setInput(String uri) {
        this.in = new TranscoderInput(uri);
        return this;
    }

    public SVGExport setInput(InputStream in) {
        this.in = new TranscoderInput(in);
        return this;
    }

    public SVGExport setInput(Reader reader) {
        this.in = new TranscoderInput(reader);
        return this;
    }

    public SVGExport setInput(XMLReader reader) {
        this.in = new TranscoderInput(reader);
        return this;
    }

    public SVGExport setInputAsString(String svg) {
        this.in = new TranscoderInput(new StringReader(svg));
        return this;
    }

    public TranscoderOutput getOutput() {
        return out;
    }

    public SVGExport setOutput(TranscoderOutput out) {
        this.out = out;
        return this;
    }

    public SVGExport setOutput(Document document) {
        this.out = new TranscoderOutput(document);
        return this;
    }

    public SVGExport setOutput(Writer writer) {
        this.out = new TranscoderOutput(writer);
        return this;
    }

    public SVGExport setOutput(OutputStream outputStream) {
        this.out = new TranscoderOutput(outputStream);
        return this;
    }

    public SVGExport setOutput(String uri) {
        this.out = new TranscoderOutput(uri);
        return this;
    }

    public SVGExport setOutput(XMLFilter filter) {
        this.out = new TranscoderOutput(filter);
        return this;
    }

    public Transcoder getTranscoder() {
        return transcoder;
    }

    public SVGExport setTranscoder(Transcoder t) {
        this.transcoder = t;
        return this;
    }

    public SVGExport setTranscoder(Format f) {
        this.transcoder = f.getTranscoderInstance();
        return this;
    }

    public SVGExport transcode() {
        try {
            transcoder.transcode(in, out);
        } catch (TranscoderException ex) {
            throw new SVGExportException(ex);
        }
        return this;
    }
}
