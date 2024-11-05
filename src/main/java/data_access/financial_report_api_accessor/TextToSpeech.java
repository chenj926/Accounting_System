package data_access.financial_report_api_accessor;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * TextToSpeech class provides functionality to convert text to speech using Google Text-to-Speech API.
 * It can send a request to the API, receive the audio content in base64 format, and decode it to an MP3 file.
 *
 * @author Chi Fong Huang, Jialuo Chen
 */

public class TextToSpeech {

    /**
     * Converts the given text to speech and saves the audio as an MP3 file.
     *
     * @param text the text to be converted to speech
     */
    public void speak(String text) {
        String message = String.format("{'input':{'text':'%s'},'voice':{'languageCode':'en-gb','name':'en-GB-Standard-A','ssmlGender':'FEMALE'},'audioConfig':{'audioEncoding':'MP3'}}", text);
        String audioFilePath = "./src/main/data/audio_files/Audio.mp3";
        String audioTextFilePath = "./src/main/data/audio_files/Audio.txt";
        try {
            HttpRequest postRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://texttospeech.googleapis.com/v1/text:synthesize"))
                    .header("X-goog-api-key", "AIzaSyDb_eJPPcBmSHBBxvgzn5xUHsNzSY9bfBk")
                    .header("Content-Type", "application/json; charset=utf-8")
                    .POST(BodyPublishers.ofString(message))
                    .build();
            HttpClient httpclient = HttpClient.newHttpClient();
            HttpResponse<String> postResponse = httpclient.send(postRequest, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            JsonObject audioTextJson = gson.fromJson(postResponse.body(), JsonObject.class);

            // Write the base64 audio content to the text file (for logging or inspection purposes)
            Path path = Paths.get(audioTextFilePath);
            Files.write(path, audioTextJson.get("audioContent").getAsString().getBytes(StandardCharsets.UTF_8));
            System.out.println("Audio text file written successfully.");

//            String decodeCommand = String.format("base64 -D -i %s > %s", audioTextFilePath, audioFilePath);
//            executeShellCommand(decodeCommand);

            // previously we were converting the base64 content using shell (-D) that only suitable for mac and windows
            // Decode the base64 content directly in Java and write it to the MP3 file
            byte[] decodedAudio = Base64.getDecoder().decode(audioTextJson.get("audioContent").getAsString());
            Path audioPath = Paths.get(audioFilePath);
            Files.write(audioPath, decodedAudio);
            System.out.println("Audio MP3 file written successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

//    /**
//     * Executes a shell command and prints the output and error streams.
//     *
//     * @param command the command to be executed
//     * @throws Exception if an error occurs during command execution
//     */
//    public void executeShellCommand(String command) throws Exception {
//        ProcessBuilder processBuilder = new ProcessBuilder();
//
//        // Detect the operating system
//        String osName = System.getProperty("os.name").toLowerCase();
//        if (osName.contains("win")) {
//            processBuilder.command("cmd.exe", "/c", command);
//        } else {
//            processBuilder.command("sh", "-c", command);
//        }
//
//        Process process = processBuilder.start();
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//        String line;
//        while ((line = reader.readLine()) != null) {
//            System.out.println("Output: " + line);
//        }
//
//        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
//        while ((line = errorReader.readLine()) != null) {
//            System.err.println("Error: " + line);
//        }
//
//        int exitCode = process.waitFor();
//        System.out.println("Exited with code: " + exitCode);
//    }
}




