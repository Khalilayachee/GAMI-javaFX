package Outil;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;



public class DataBase {
    private Connection conn;
    final String url = "jdbc:mysql://localhost:3306/gami";
    final String user = "root";
    final String pwd = "";
    static DataBase instance;

    private DataBase() {
        try {
            conn = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connected");
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    /**
     * Returns the singleton instance of the DataBase class.
     * 
     * This method implements the Singleton pattern to ensure only one instance
     * of the DataBase class is created and used throughout the application.
     * If the instance doesn't exist, it creates a new one; otherwise, it returns
     * the existing instance.
     * 
     * @return The singleton instance of the DataBase class
     */
    public static DataBase getInstance() {
        if (instance == null) {
            return instance = new DataBase();
        }
        return instance;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    private static final String APP_DATA_DIR = System.getenv("LOCALAPPDATA") + "\\Programs";
    private static final File DIR = new File(APP_DATA_DIR, "ChaTTY");

    /**
     * Creates a responses file with predefined chat responses in JSON format.
     * 
     * This method performs the following operations:
     * 1. Determines the application data path.
     * 2. Creates a 'ChaTTY' directory if it doesn't exist.
     * 3. Initializes a map with predefined chat responses.
     * 4. Converts the map to JSON format.
     * 5. Writes the JSON data to a 'responses.json' file in the 'ChaTTY' directory.
     * 
     * @throws IOException If an I/O error occurs while writing the file.
     */
    public static void createResponsesFile() throws IOException {
        String appDataPath = System.getenv("LOCALAPPDATA") + "\\Programs";

        File dir = new File(appDataPath, "ChaTTY");
        if (!dir.exists()) System.out.println("Create folder ChaTTY = " + dir.mkdir());

        final Map<String, String> MAP = new HashMap<>();
        MAP.put("bonjour", "bonjour! Comment puis-je vous aider aujourd'hui ?");
        MAP.put("ajouter un post", "Pour ajouter un nouveau post, vous pouvez cliquer sur le bouton 'Ajouter un post' qui s'affiche dans le menu principal de l'application et ensuite remplir le formulaire affiché. Autre question ?");
        MAP.put("supprimer un post", "Pour supprimer un post ajouté, vous pouvez simplement cliquer sur le bouton 'Supprimer un post' dans le menu principal de l'application. Autre question ?");
        MAP.put("modifier un post", "Pour modifier un post déjà ajouté, vous pouvez cliquer sur le bouton 'Modifier un post' qui s'affiche dans le menu principal de l'application et ensuite remplir le formulaire affiché avec les nouvelles données. Autre question ?");
        MAP.put("ajouter un commentaire à un post", "Pour ajouter un commentaire à un post, vous pouvez cliquer sur le bouton 'Ajouter un commentaire' en dessous de chaque post dans le menu principal de l'application. Autre question ?");
        MAP.put("supprimer un commentaire", "Pour supprimer un commentaire ajouté, vous pouvez simplement cliquer sur le bouton 'Supprimer un commentaire' dans le menu principal de l'application. Autre question ?");
        MAP.put("modifier un commentaire", "Pour modifier un commentaire déjà ajouté, vous pouvez cliquer sur le bouton 'Modifier un commentaire' qui s'affiche dans le menu principal de l'application et ensuite remplir le formulaire affiché avec les nouvelles données. Autre question ?");
        MAP.put("commentaire masqué", "Dans notre application, chaque commentaire contient un mot inapproprié, il sera automatiquement changé par '***' et nous serons contents si vous respectez les autres. Merci de votre compréhension. Avez-vous d'autres questions ?");
        MAP.put("merci", "Merci pour votre visite ! N'hésitez pas à revenir si vous avez d'autres questions. Nous sommes toujours là pour vous aider. Avez-vous besoin de quelque chose d'autre ?");
        MAP.put("au revoir", "Au revoir !");
        Gson gson = new Gson();
        String json = gson.toJson(MAP);
        Files.writeString(new File(dir, "responses.json").toPath(), json);
        System.out.println("Create Database: " + (!doesNotExist()));
    }

    /**
     * Retrieves the File object representing the responses.json file.
     * 
     * This method constructs the path to the responses.json file
     * in the ChaTTY program directory within the user's local
     * application data folder.
     * 
     * @return File object pointing to the responses.json file
     */
    public static File getResponsesFile() {
        String appDataPath = System.getenv("LOCALAPPDATA") + "\\Programs";
        File dir = new File(appDataPath, "ChaTTY");
        return new File(dir, "responses.json");
    }

    public static boolean doesNotExist() {
        return !new File(DIR, "responses.json").exists();
    }

}
