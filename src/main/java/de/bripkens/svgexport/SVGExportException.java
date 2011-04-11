
package de.bripkens.svgexport;

/**
 *
 * @author Ben Ripkens <bripkens.dev@gmail.com>
 */
public class SVGExportException extends RuntimeException {

    public SVGExportException(Throwable cause) {
        super(cause);
    }

    public SVGExportException(String message, Throwable cause) {
        super(message, cause);
    }

    public SVGExportException(String message) {
        super(message);
    }

    public SVGExportException() {
    }

}
