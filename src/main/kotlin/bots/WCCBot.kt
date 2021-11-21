package bots

import com.vdurmont.emoji.EmojiParser
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendDocument
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.send.SendVideo
import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import java.io.File

class WCCBot : TelegramLongPollingBot() {

    override fun getBotUsername(): String {
        //return bot username
        return "WCC6-Kotlin-Bot"
    }

    override fun getBotToken(): String {
        // Return bot token from BotFather
         return "TKN_TELEGRAM"
    }

    override fun onUpdateReceived(update: Update?) {
        // We check if the update has a message and the message has text
        val nameSender = update?.message?.from?.firstName ?: "Dev" .uppercase()
        val chatId = update?.message?.chatId.toString()
        val messageCommand = update?.message?.text?.lowercase()

        try {
            if (messageCommand == "/start") {
                val sendDocument = SendDocument().apply {
                    this.chatId = chatId
                    this.caption = getMessage(messageCommand, nameSender)
                    this.document =
                        InputFile().setMedia(
                            "https://media3.giphy.com/media/3oKIPnAiaMCws8nOsE/giphy.gif"
                        )
                    this.parseMode = "MarkdownV2"
                }
                execute(sendDocument)

            } else
                if (messageCommand == "/info") {
                    val sendDocument = SendDocument().apply {
                        this.chatId = chatId
                        this.caption =
                            EmojiParser.parseToUnicode(
                               "Este bot é um projeto realizado através do programa _*Woman Can Code* :womens: 6ª Edição_ \\- trilha Kotlin \n"
                            )
                        this.document =
                            InputFile().setMedia("" +
                                    "https://media4.giphy.com/media/LMcB8XospGZO8UQq87/giphy.gif"
                            )
                        this.parseMode = "MarkdownV2"
                    }
                    execute(sendDocument)

            } else
                if (messageCommand == "/video1") {
                    val sendDocument = SendMessage().apply {
                         this.chatId = chatId
                         this.text = "https://www.youtube.com/watch?v=ew3QqUN5aF8"
                    }
                    execute(sendDocument)

            } else
                if (messageCommand == "/video2") {
                   val sendDocument = SendMessage().apply {
                        this.chatId = chatId
                        this.text = "https://www.youtube.com/watch?v=nV1FVTcKKYQ"
                   }
                   execute(sendDocument)

            } else
                if (messageCommand == "/site") {
                   val sendDocument = SendMessage().apply {
                        this.chatId = chatId
                        this.text = "https://kotlinlang.org/"
                    }
                    execute(sendDocument)

            } else {
                   val sendDocument = SendMessage().apply {
                        this.chatId = chatId
                        this.text =
                            EmojiParser.parseToUnicode(
                                ":x: Informação ou comando não encontrado :weary: "
                            )
                        this.parseMode = "MarkdownV2"
                        }
                        execute(sendDocument)
                    }

            } catch (e: TelegramApiException) {
                e.printStackTrace()
                }
    }

    private fun getMessage(command: String?, name: String?) = when (command) {
        "/start" -> Welcome(name)

        else -> EmojiParser.parseToUnicode("Informação não encontrada :weary:")
    }

    private fun Welcome(nameSender: String?) = """
       Olá *$nameSender*

       ESCOLHA ALGUM COMANDO PARA INTERAGIR

            \/start : Iniciar
            \/info : Informação sobre o bot
          
       SOBRE *KOTLIN*
         
            \/video1 : "_Conheça a Linguagem Kotlin_"
            \/video2 : "_A História da Linguagem Kotlin_"
            \/site : Site Oficial
   
    """.trimMargin()

}