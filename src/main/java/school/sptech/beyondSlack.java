package school.sptech;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;

public class beyondSlack {
    public static void main(String[] args) {
        beyondSlack bot = new beyondSlack(); //criando o robô
        bot.enviarMensagem("C08SPL9KM3L", "Olá, divos"); //chamando o mêtodo com o robô
    }
    public void enviarMensagem(String canal, String mensagem) {
        try {
            String token = "slack_tocken"; // senha do rôbo

            Slack slack = Slack.getInstance();
            System.out.println("Token: " + token);
            MethodsClient methods = slack.methods(token);

            //criando a mensagem
            ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                    .channel(canal)
                    .text(mensagem)
                    .username("Beyond_bot")
                    .iconEmoji(":robot_face:")
                    .build();

            //mandando a mensagem
            ChatPostMessageResponse response = methods.chatPostMessage(request);

            //valida o resultado
            if (response.isOk()) {
                System.out.println("✅ Mensagem enviada com sucesso!");
            } else {
                System.out.println("❌ Erro: " + response.getError());
            }

            //tratando os erros
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
