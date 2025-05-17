package school.sptech;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;

import java.util.Arrays;
import java.util.List;

public class beyondSlack {

    public static void main(String[] args) {
        List<String> canais = Arrays.asList(
                "C08SPL9KM3L",
                "C08T3H9B51N"
        );

        String mensagem = "Olá! Mensagem programada pela Beyond Analytics";

        beyondSlack bot = new beyondSlack();
        bot.enviarParaVariosCanais(canais, mensagem);
    }

    public void enviarParaVariosCanais(List<String> canais, String mensagem) {
        for (String canal : canais) {
            enviarMensagem(canal, mensagem);
        }
    }

    public void enviarMensagem(String canal, String mensagem) {
        try {
            String token = "xoxb-xxxxxxxxxx"; //inserir senha do token

            Slack slack = Slack.getInstance();
            MethodsClient methods = slack.methods(token);

            ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                    .channel(canal)
                    .text(mensagem)
                    .username("Beyond_bot")
                    .iconEmoji(":robot_face:")
                    .build();

            ChatPostMessageResponse response = methods.chatPostMessage(request);

            if (response.isOk()) {
                System.out.println("✅ Mensagem enviada para o canal: " + canal);
            } else {
                System.out.println("❌ Erro no canal " + canal + ": " + response.getError());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
