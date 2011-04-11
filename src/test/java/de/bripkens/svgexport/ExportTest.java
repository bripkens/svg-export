package de.bripkens.svgexport;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Ben Ripkens <bripkens.dev@gmail.com>
 */
public class ExportTest {

    private SVGExport e;

    @Before
    public void setUp() {
        e = new SVGExport();
    }

    @Test
    public void testExport() {
        e.setInput("http://upload.wikimedia.org/wikipedia/en/c/ce/SVG-logo.svg");

        for (Format f : Format.values()) {
            try {
                e.setOutput(new FileOutputStream("target/test.".concat(f.
                        getFileNameExtension()))).
                        setTranscoder(f).transcode();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ExportTest.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        }

    }
}
