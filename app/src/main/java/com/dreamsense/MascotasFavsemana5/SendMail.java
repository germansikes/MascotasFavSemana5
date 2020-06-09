package com.dreamsense.MascotasFavsemana5;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by Gabriel on 15/07/2016.
 */
public class SendMail extends AsyncTask<Void, Void, Void> {

    //Declaración de Variables
    private Context context;
    private Session session;

    //Información que se enviará al mensaje
    private String email;
    private String subject;
    private String message;

    //Progressdialog que se mostrará mientras se envia el mensaje
    private ProgressDialog progressDialog;


    public SendMail(Context context, String email, String subject, String message) {
        //Inicializar variables
        this.context = context;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context, "Enviando Mensaje", "Espere por favor...", false, false);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
        Toast.makeText(context, "Mensaje enviado", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        //Creando propiedades
        Properties props = new Properties();

        //Configurando las propiedades para su uso en Gmail
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        //Creando una nueva sesión
        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    //Se autentica el password desde la clase Config
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Config.EMAIL, Config.PASSWORD);
                    }
                });

        try {
            //Creando objeto MimeMessage que servirá para definir los atributos del mensaje
            MimeMessage mm = new MimeMessage(session);

            //Se configura el correo emisor
            mm.setFrom(new InternetAddress(Config.EMAIL));
            //Se configira el correo receptor
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            //Se agrega un "Título" para tu correo
            mm.setSubject(subject);
            //Se agrega el cuerpo del mensaje
            mm.setText(message);

            //Se envía mensaje
            Transport.send(mm);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
