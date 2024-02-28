import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Scanner;

public class Camera {

    public static void main(String[] args) throws IOException {
        // Definindo as informações do usuário
        final String usuario = "admin";
        final String senha = "Intelbras1";
        final int porta = 80;
        final String ip = "10.1.45.87";

        // Definindo a URL base
        String urlBase = "http://" + ip + ":" + porta + "/cgi-bin/";

        // Configurando o Authenticator para a autenticação Digest
        Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, senha.toCharArray());
            }
        });

        // Definindo as requisições HTTP
        String[] requisicoes = {
                // Funcionando
                // urlBase + "configManager.cgi?action=getConfig&name=General",
                // urlBase + "devAudioInput.cgi?action=getCollect",
                // urlBase + "devAudioOutput.cgi?action=getCollect",

                // em teste
                // urlBase + "snapshot.cgi?channel=1&type=0",
                // urlBase + "mjpg/video.cgi?channel=1&subtype=0",

                // corrigir
                // urlBase +
                // "configManager.cgi?action=setConfig&General.MachineID=20832748927&General.MachineName=DVR001&General.MachineAddress=XXX%20Road",
                // urlBase +
                // "configManager.cgi?action=removeConfig&name=General.MachineAddress",
                // urlBase + "audio.cgi?action=postAudio&httptype=singlepart&channel=1",
                // urlBase + "audio.cgi?action=getAudio&httptype=singlepart&channel=1",
                // urlBase + "configManager.cgi?action=getConfig&name=AudioOutputVolume",
                // urlBase +
                // "configManager.cgi?action=setConfig&AudioOutputVolume[0]=80&AudioOut",
                // urlBase + "configManager.cgi?action=getConfig&name=Snap",
                // urlBase +
                // "configManager.cgi?action=setConfig&Snap{0]HolidayEnable=true&Snap[0]TimeSection[0][0]=6
                // 00:00:00-23:59:59&Snap[0].TimeSection[0][1]=0
                // 00:00:00-23:59:59&Snap[1].HolidayEnable=false&Snap[1].TimeSection[0][1]=0
                // 00:00:00-23:59:59&Snap[1].TimeSection[0][1]=0 00:00:00-23:59:59",
                // urlBase +
                // "snapManager.cgi?action=attachFileProc&channel=1&heartbeat=5&Flags[0]=Event&Events=[VideoMotion%2CVideoLoss]",
                // urlBase + "magicBox.cgi?action=getProductDefinition&name=MaxExtraStream",
                // urlBase + "encode.cgi?action=getCaps",
                // urlBase +
                // "encode.cgi?action=getConfigCaps&channel=1&Encode[0].MainFormat[0].Video.Compression=H_264&Encode[0].MainFormat[0].Video.Width=1920&Encode[0].MainFormat[0].Video.Height=1080",
                // urlBase + "configManager.cgi?action=getConfig&name=ChannelTitle",
                // urlBase +
                // "configManager.cgi?action=setConfig&ChannelTitle[0].Name=CAM1|123&ChannelTitle[1].Name=CAM2|456",
                urlBase + "devVideoInput.cgi?action=getCollect",
                // urlBase + "devVideoOutput.cgi?action=getCollect",
                // urlBase +
                // "magicBox.cgi?action=getProductDefinition&name=MaxRemoteInputChannels",
                // urlBase +
                // "configManager.cgi?action=setConfig&VideoWidget[1].Covers[0].BackColor[0]=128&VideoWidget[1].Covers[0].BackColor[1]=128&VideoWidget[1].Covers[0].BackColor[2]=128&VideoWidget[1].Covers[0].BackColor[3]=0",
                // urlBase + "devVideoInput.cgi?action=getCaps&channel=1",
                // urlBase + "devVideoInput.cgi?action=getCurrentWindow&channel=1",
                // urlBase +
                // "devVideoInput.cgi?action=setCurrentWindow&channel=1&rect[0]=0&rect[1]=0&rect[2]=5000&rect[3]=5000",
                // urlBase + "configManager.cgi?action=getConfig&name=VideoOut"
                // urlBase + "snapshot.cgi?channel=1&type=0" // Nova linha para teste
        };

        // Realizando as requisições e exibindo os resultados
        for (String requisicao : requisicoes) {
            // Realizando a requisição
            HttpURLConnection connection = (HttpURLConnection) new URL(requisicao).openConnection();
            connection.setRequestMethod("GET");

            // Verificando o código de status
            int statusCode = connection.getResponseCode();
            String message = connection.getResponseMessage();

            // Lendo o conteúdo da resposta
            InputStream inputStream;
            if (statusCode >= 400) {
                inputStream = connection.getErrorStream();
            } else {
                inputStream = connection.getInputStream();
            }
            Scanner responseScanner = new Scanner(inputStream);
            String content = responseScanner.useDelimiter("\\A").next();

            // Exibindo o resultado
            System.out.println("-".repeat(40));
            System.out.println("URL: " + requisicao);
            System.out.println("Status: " + statusCode + " " + message);
            // System.out.println("Conteúdo: " + content);
        }
    }
}