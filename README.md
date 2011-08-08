# Export SVG to various image formats

A small library around the Apache Batik and Apache FOP library that
simplifies the usage.

## Maven repository
A Maven repository has been set up on Github and can be used like every
other Maven repository.

    <!-- ... -->

    <repository>
        <id>svn-export-repo</id>
        <name>svn-export repository on GitHub</name>
        <url>http://bripkens.github.com/svg-export/repository/</url>
    </repository>

    <!-- ... -->

    <dependency>
        <groupId>de.bripkens</groupId>
        <artifactId>svg-export</artifactId>
        <version>0.2</version>
    </dependency>
    
    <!-- ... -->

## Supported formats
* JPEG
* PNG
* TIFF
* EPS
* PDF
* SVG (sometimes required by charting libraries to enable the download of
a diagram which is drawn on the client side)

## Example
The following example shows how the library can be used to convert an SVG to
PDF.

    import java.io.FileOutputStream;
    import java.io.FileInputStream;
    import de.bripkens.svgexport.SVGExport;
    import de.bripkens.svgexport.Format;
    
    // ...
    
    new SVGExport().setInput(new FileInputStream("example.svg"))
                    .setOutput(new FileOutputStream("example.pdf"))
                    .setFormat(Format.PDF)
                    .transcode();
    
    // ...

