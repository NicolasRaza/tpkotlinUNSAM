interface MailSender {

    fun enviarMail(mail: Mail)

}

data class Mail(val from : String ,val to: String, val subject: String,val cuerpo : String)