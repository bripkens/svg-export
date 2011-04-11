# Export SVG to various image formats

A small library around the Apache Batik and Apache FOP library that
simplifies the usage.

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
    import de.bripkens.svgexport.SVGExport;
    import de.bripkens.svgexport.Format;
    
    // ...
    
    new SVGExport().setOutput(new FileOutputStream("example.pdf")).
                    setInput(new FileInputStream("example.svg")).
                    setFormat(Format.PDF).
                    transcode();
    
    // ...

