package LectorTarjeta.QR;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GeneradorQR {

    /**
     * Genera un código QR a partir de un ID y lo guarda en un archivo.
     *
     * @param id El ID del estudiante o entidad para el cual se genera el QR.
     * @param correo El correo electrónico del destinatario, usado para nombrar el archivo.
     */
    public static void generarQR(String id, String correo, String nombre) {
        QRCodeWriter writer = new QRCodeWriter();
        int width           = 300;
        int height          = 300;

        try {

            BitMatrix matrix = writer.encode(id, BarcodeFormat.QR_CODE, width, height);
            Path path = Paths.get("src/ImagenesQR", "QR_" + correo.split("@")[0] + ".png");
            MatrixToImageWriter.writeToPath(matrix, "PNG", path);

            EnviarCorreoConQR.enviarCorreoConQR(correo, "src/ImagenesQR"+ "/" + "QR_" + correo.split("@")[0] + ".png", nombre);

            System.out.println("QR generado: " + correo + path.toAbsolutePath());

        } catch (WriterException | IOException e) {
            System.err.println("Error al generar QR: " + e.getMessage());
        }
    }
}

