package school.sptech;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;

import java.util.Arrays;
import java.util.List;

public class beyondSlack {
    private MethodsClient metodos;

    public beyondSlack(String token) {
        Slack slack = Slack.getInstance();
        this.metodos = slack.methods(token);
    }

    public void enviarParaVariosCanais(List<String> canais, String mensagem) {
        for (String canal : canais) {
            enviarMensagem(canal, mensagem);
        }
    }

    public void enviarMensagem(String canal, String mensagem) {
        try {
            ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                    .channel(canal)
                    .text(mensagem)
                    .username("Beyond_bot")
                    .iconEmoji(":robot_face:")
                    .build();

            ChatPostMessageResponse response = metodos.chatPostMessage(request);

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
