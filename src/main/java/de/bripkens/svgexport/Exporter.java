package de.bripkens.svgexport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 *
 * @author Ben Ripkens <bripkens.dev@gmail.com>
 */
public class Exporter {

    private static final Logger logger = Logger.getLogger(Exporter.class.
            getName());

    private static final String STANDARD_IN = "si";

    private static final String STANDARD_OUT = "so";

    private static final String FILE_IN = "fi";

    private static final String FILE_OUT = "fo";

    private static final String FORMAT = "f";

    private static final String HELP = "h";

    private static final Options OPTIONS;

    static {
        OPTIONS = new Options();

        OptionGroup inOptionGroup = new OptionGroup();
        inOptionGroup.setRequired(true);
        inOptionGroup.addOption(OptionBuilder.withArgName(STANDARD_IN).
                withLongOpt("standardIn").
                withDescription("Use standard in as the input stream.").
                create(STANDARD_IN));
        inOptionGroup.addOption(OptionBuilder.withArgName(FILE_IN).
                withLongOpt("fileIn").
                hasArg().
                withDescription("Use this to retrieve data from a file.").
                create(FILE_IN));

        OptionGroup outOptionGroup = new OptionGroup();
        outOptionGroup.setRequired(true);
        outOptionGroup.addOption(OptionBuilder.withArgName(STANDARD_OUT).
                withLongOpt("standardOut").
                withDescription("Use standard out as the output stream.").
                create(STANDARD_OUT));
        outOptionGroup.addOption(OptionBuilder.withArgName(FILE_OUT).
                withLongOpt("fileOut").
                hasArg().
                withDescription("Use this to write data from a file.").
                create(FILE_OUT));

        OPTIONS.addOptionGroup(inOptionGroup);
        OPTIONS.addOptionGroup(outOptionGroup);

        OPTIONS.addOption(OptionBuilder.withArgName(FORMAT).
                withLongOpt("format").
                hasArg().
                isRequired().
                withDescription("Output format.").
                create(FORMAT));

        OPTIONS.addOption(OptionBuilder.withArgName(HELP).
                withLongOpt("help").
                withDescription("Print the help text").
                create(HELP));
    }

    public static void main(String[] args) {

        CommandLineParser parser = new GnuParser();
        CommandLine line = null;

        try {
            line = parser.parse(OPTIONS, args);
        } catch (ParseException ex) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar svg-export.jar", OPTIONS);
            System.exit(1);
        }

        InputStream in = null;
        if (line.hasOption(STANDARD_IN)) {
            in = System.in;
        } else {
            File source = new File(line.getOptionValue(FILE_IN));
            if (!source.exists() || !source.canRead() || source.isDirectory()) {
                logger.log(Level.SEVERE, "Invalid input file.");
                System.exit(1);
            }

            try {
                in = new FileInputStream(source);
            } catch (FileNotFoundException ex) {
                logger.log(Level.SEVERE, "Invalid input file.", ex);
                System.exit(1);
            }
        }

        OutputStream out = null;
        if (line.hasOption(STANDARD_OUT)) {
            out = System.out;
        } else {
            File target = new File(line.getOptionValue(FILE_OUT));
            if (target.exists()) {
                logger.log(Level.SEVERE, "Invalid output file, "
                        + "file already exists.");
                System.exit(1);
            }
            try {
                target.createNewFile();
            } catch (IOException ex) {
                logger.log(Level.SEVERE, "Invalid output file, "
                        + "can't create empty file.");
                System.exit(1);
            }
            
            try {
                out = new FileOutputStream(target);
            } catch (FileNotFoundException ex) {
                logger.log(Level.SEVERE, "Invalid output file.", ex);
                System.exit(1);
            }
        }

        Format f = null;
        try {
            f = Format.valueOf(line.getOptionValue(FORMAT).
                    toUpperCase());
        } catch (IllegalArgumentException ex) {
            logger.log(Level.SEVERE, "Invalid output format.", ex);
            logger.log(Level.INFO, "Valid formats: {0}",
                    Arrays.toString(Format.values()));
            System.exit(1);
        }

        new SVGExport().setInput(in).
                setOutput(out).
                setTranscoder(f).
                transcode();
    }
}
