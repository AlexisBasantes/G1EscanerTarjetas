package LectorTarjeta.QR;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.io.File;
import java.util.Properties;

public class EnviarCorreoConQR {

    /**
     * Envía un correo electrónico con un archivo QR adjunto.
     *
     * @param destinatario El correo electrónico del destinatario.
     * @param rutaQR La ruta del archivo QR a adjuntar.
     * @param nombreEstudiante El nombre del estudiante, usado en el asunto del correo.
     */
    public static void enviarCorreoConQR(String destinatario, String rutaQR, String         nombreEstudiante) {
        String remitente = "generadorqr1017@gmail.com"; 
        String clave     = "bbsbeuglacxidjce"; 
        String asunto    = "Código QR para " + nombreEstudiante;
        String mensaje   = "Hola " + nombreEstudiante + ",\n\nAdjunto encontrarás tu código QR para el sistema.";

        // Configurar propiedades del servidor SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, clave);
            }
        });

        try {
            // Crear mensaje de correo
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(remitente));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            msg.setSubject(asunto);

            // Cuerpo del mensaje
            MimeBodyPart cuerpo = new MimeBodyPart();
            cuerpo.setText(mensaje);

            // Adjuntar la imagen QR
            MimeBodyPart adjuntoQR = new MimeBodyPart();
            adjuntoQR.attachFile(new File(rutaQR)); // ruta del archivo QR generado

            // Unir las partes
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(cuerpo);
            multipart.addBodyPart(adjuntoQR);

            msg.setContent(multipart);

            // Enviar
            Transport.send(msg);
            System.out.println("Correo enviado con éxito a " + destinatario);

        } catch (Exception e) {
            System.out.println("Error al enviar correo:");
            e.printStackTrace();
        }
    }
}
