package nu.hex.asciianimator.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *
 * @author hl
 */
public class AazFileReader {

    private final ZipInputStream stream;
    private final Map<String, String> strings = new TreeMap<>();

    public AazFileReader(InputStream stream) {
        this.stream = new ZipInputStream(stream);
    }

    public List<String> read() {
        readZip();
        return new ArrayList<>(strings.values());
    }

    private void readZip() {
        ZipEntry entry;
        try {
            while ((entry = stream.getNextEntry()) != null) {
                byte[] buffer = new byte[0];
                buffer = appendByteArray(buffer, stream);
                if (!entry.isDirectory()) {
                    strings.put(entry.getName(), new String(buffer, StandardCharsets.UTF_8));
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(AazFileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private byte[] appendByteArray(byte[] arrayToAppendTo, InputStream stream) {
        ByteArrayOutputStream streamOut = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[2048];
            streamOut.write(arrayToAppendTo);
            int len;
            while ((len = stream.read(buffer)) > 0) {
                streamOut.write(buffer, 0, len);
            }
            return streamOut.toByteArray();
        } catch (IOException ex) {
            return arrayToAppendTo;
        } finally {
            try {
                streamOut.close();
            } catch (IOException ex) {
                Logger.getLogger(AazFileReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
