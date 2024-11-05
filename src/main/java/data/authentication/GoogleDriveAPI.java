package data.authentication;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

/**
 * The {@code GoogleDriveAPI} class provides methods to interact with Google Drive,
 * including authorization, file upload, and file update functionalities.
 *
 * <p>This class is designed to facilitate file operations on Google Drive using the
 * Google Drive API. It handles authentication and manages file upload or update
 * based on whether the file already exists on the drive.
 *
 * <p>Example usage:
 * <pre>
 * {@code
 * public static void main(String... args) throws IOException, GeneralSecurityException {
 *     final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
 *     Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
 *             .setApplicationName(APPLICATION_NAME)
 *             .build();
 *
 *     FileList result = service.files().list()
 *             .setPageSize(10)
 *             .setFields("nextPageToken, files(id, name)")
 *             .execute();
 *     List<File> files = result.getFiles();
 *     if (files == null || files.isEmpty()) {
 *         System.out.println("No files found.");
 *     } else {
 *         System.out.println("Files:");
 *         for (File file : files) {
 *             System.out.printf("%s (%s)\n", file.getName(), file.getId());
 *         }
 *
 *         uploadOrUpdateFile(service, "src/main/data/transaction/userAccountTransactions.csv", "text/csv", "userAccountTransactions.csv");
 *         uploadOrUpdateFile(service, "src/main/data/accounts/sharedAccounts.csv", "text/csv", "sharedAccounts.csv");
 *         uploadOrUpdateFile(service, "src/main/data/transaction/sharedAccountTransactions.csv", "text/csv", "sharedAccountTransactions.csv");
 *     }
 * }
 * }
 * </pre>
 *
 * @see com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
 * @see com.google.api.services.drive.Drive
 * @see com.google.api.client.auth.oauth2.Credential
 * @author dana
 */
public class GoogleDriveAPI {
    private static final String APPLICATION_NAME = "Drive API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    /**
     * Gets the credentials required to access Google Drive.
     *
     * @param HTTP_TRANSPORT the network HTTP transport
     * @return the Credential object containing the authenticated user's credentials
     * @throws IOException if there is an error reading the credentials file
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        InputStream in = GoogleDriveAPI.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    /**
     * Uploads a file to Google Drive or updates it if it already exists.
     *
     * @param service       the Drive service instance
     * @param localFilePath the local file path of the file to be uploaded or updated
     * @param mimeType      the MIME type of the file
     * @param fileName      the name of the file on Google Drive
     * @throws IOException if there is an error during the file operation
     */
    public static void uploadOrUpdateFile(Drive service, String localFilePath, String mimeType, String fileName) throws IOException {
        String fileId = null;
        FileList result = service.files().list()
                .setQ("name='" + fileName + "' and mimeType='" + mimeType + "'")
                .setSpaces("drive")
                .setFields("files(id, name)")
                .execute();
        List<File> files = result.getFiles();
        if (files != null && !files.isEmpty()) {
            fileId = files.get(0).getId();
        }

        java.io.File filePath = new java.io.File(localFilePath);
        FileContent mediaContent = new FileContent(mimeType, filePath);

        if (fileId != null) {
            try {
                File file = service.files().update(fileId, null, mediaContent)
                        .setFields("id")
                        .execute();
                System.out.println("Updated File ID: " + file.getId());
            } catch (GoogleJsonResponseException e) {
                System.err.println("Unable to update file: " + e.getDetails());
                throw e;
            }
        } else {
            File fileMetadata = new File();
            fileMetadata.setName(fileName);
            try {
                File file = service.files().create(fileMetadata, mediaContent)
                        .setFields("id")
                        .execute();
                System.out.println("Created File ID: " + file.getId());
            } catch (GoogleJsonResponseException e) {
                System.err.println("Unable to create file: " + e.getDetails());
                throw e;
            }
        }
    }

    /**
     * The main method to demonstrate Google Drive API usage.
     *
     * @param args the command-line arguments
     * @throws IOException              if there is an error during the API call
     * @throws GeneralSecurityException if there is a security error
     */
    public static void main(String... args) throws IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        FileList result = service.files().list()
                .setPageSize(10)
                .setFields("nextPageToken, files(id, name)")
                .execute();
        List<File> files = result.getFiles();
        if (files == null || files.isEmpty()) {
            System.out.println("No files found.");
        } else {
            System.out.println("Files:");
            for (File file : files) {
                System.out.printf("%s (%s)\n", file.getName(), file.getId());
            }

            uploadOrUpdateFile(service, "src/main/data/transaction/userAccountTransactions.csv", "text/csv", "userAccountTransactions.csv");
            uploadOrUpdateFile(service, "src/main/data/accounts/sharedAccounts.csv", "text/csv", "sharedAccounts.csv");
            uploadOrUpdateFile(service, "src/main/data/transaction/sharedAccountTransactions.csv", "text/csv", "sharedAccountTransactions.csv");
            uploadOrUpdateFile(service, "src/main/data/accounts/userAccounts.csv", "text/csv", "userAccounts.csv");
        }
    }
}
