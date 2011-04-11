
package de.bripkens.svgexport;

import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.batik.transcoder.image.TIFFTranscoder;
import org.apache.batik.transcoder.wmf.tosvg.WMFTranscoder;
import org.apache.fop.render.ps.EPSTranscoder;
import org.apache.fop.svg.PDFTranscoder;

/**
 *
 * @author Ben Ripkens <bripkens.dev@gmail.com>
 */
public enum Format {
    JPEG("image/jpeg", "jpg", JPEGTranscoder.class),
    PNG("image/png", "png", PNGTranscoder.class),
    TIFF("image/tiff", "tiff", TIFFTranscoder.class),
    EPS("application/postscript", "eps", EPSTranscoder.class),
//    WMF("application/wmf", "wmf", WMFTranscoder.class),
    PDF("application/pdf", "pdf", PDFTranscoder.class);

    private final String contentType, fileNameExtension;
    private final Class<? extends Transcoder> transcoder;

    private Format(String contentType, String fileNameExtension,
            Class<? extends Transcoder> transcoder) {
        this.contentType = contentType;
        this.fileNameExtension = fileNameExtension;
        this.transcoder = transcoder;
    }

    public String getContentType() {
        return contentType;
    }

    public String getFileNameExtension() {
        return fileNameExtension;
    }

    public Class<? extends Transcoder> getTranscoder() {
        return transcoder;
    }

    public Transcoder getTranscoderInstance() {
        try {
            return transcoder.newInstance();
        } catch (InstantiationException ex) {
            throw new SVGExportException(ex);
        } catch (IllegalAccessException ex) {
            throw new SVGExportException(ex);
        }
    }
}
