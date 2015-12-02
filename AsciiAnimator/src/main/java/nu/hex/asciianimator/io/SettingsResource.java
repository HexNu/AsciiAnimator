package nu.hex.asciianimator.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hl
 */
public class SettingsResource {

    public String getPropertiesContent() {
        try {
            return new String(readFully(getStream()), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            Logger.getLogger(SettingsResource.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private byte[] readFully(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }
        return baos.toByteArray();
    }

    public InputStream getStream() {
        return getClass().getClassLoader().getResourceAsStream("file/asciianimator.properties");
    }
}
