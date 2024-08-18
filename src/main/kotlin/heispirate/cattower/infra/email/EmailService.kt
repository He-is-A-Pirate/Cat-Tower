package heispirate.cattower.infra.email

interface EmailService {
    fun sendEmail(email:String, subject: String,text:String)
}