
package de.bripkens.svgexport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ben Ripkens <bripkens.dev@gmail.com>
 */
public class Exporter {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("At least three parameters are requirered.");
            System.err.println("source target format");
            System.exit(1);
        }

        File source = new File(args[0]);
        if (!source.exists() || !source.canRead() || source.isDirectory()) {
            System.err.println("Invalid source. ");
            System.exit(1);
        }

        File target = new File(args[1]);
        if (target.exists()) {
            System.err.println("Invalid target.");
            System.exit(1);
        }

        Format f = null;
        try {
            f = Format.valueOf(args[2]);
        } catch (IllegalArgumentException ex) {
            System.err.println("Invalid format.");
            System.err.println("Valid formats are: " +
                    Arrays.toString(Format.values()));
            System.exit(1);
        }
        try {
            new SVGExport().setInput(new FileInputStream(source)).
                    setOutput(new FileOutputStream(target)).
                    setTranscoder(f).
                    transcode();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Exporter.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
    }

}
