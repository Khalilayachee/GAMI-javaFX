package Outil;

import com.google.gson.Gson;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DataBase {
    private static final Logger logger = LoggerFactory.getLogger(DataBase.class);
    private static DataBase instance;
    private final HikariDataSource dataSource;

    private DataBase() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(DatabaseConfig.getUrl());
        config.setUsername(DatabaseConfig.getUser());
        config.setPassword(DatabaseConfig.getPassword());
        config.setMaximumPoolSize(DatabaseConfig.getMaximumPoolSize());
        config.setMinimumIdle(DatabaseConfig.getMinimumIdle());
        config.setConnectionTimeout(DatabaseConfig.getConnectionTimeout());
        
        // Connection test query
        config.setConnectionTestQuery("SELECT 1");
        
        // Enable auto-commit
        config.setAutoCommit(true);
        
        // Connection timeout
        config.setConnectionTimeout(30000);
        
        // Idle timeout
        config.setIdleTimeout(600000);
        
        // Max lifetime of connection
        config.setMaxLifetime(1800000);
        
        try {
            dataSource = new HikariDataSource(config);
            logger.info("Database connection pool initialized successfully");
        } catch (Exception e) {
            logger.error("Failed to initialize database connection pool", e);
            throw new RuntimeException("Failed to initialize database connection pool", e);
        }
    }

    public static DataBase getInstance() {
        if (instance == null) {
            synchronized (DataBase.class) {
                if (instance == null) {
                    instance = new DataBase();
                }
            }
        }
        return instance;
    }

    public Connection getConn() {
        try {
            Connection conn = dataSource.getConnection();
            if (conn == null || conn.isClosed()) {
                logger.error("Failed to obtain database connection");
                throw new SQLException("Could not obtain database connection");
            }
            return conn;
        } catch (SQLException e) {
            logger.error("Error getting database connection", e);
            throw new RuntimeException("Error getting database connection", e);
        }
    }

    private static final String APP_DATA_DIR = System.getenv("LOCALAPPDATA") + "\\Programs";
    private static final File DIR = new File(APP_DATA_DIR, "ChaTTY");

    public static void createResponsesFile() throws IOException {
        String appDataPath = System.getenv("LOCALAPPDATA") + "\\Programs";

        File dir = new File(appDataPath, "ChaTTY");
        if (!dir.exists()) {
            logger.info("Creating ChaTTY directory");
            System.out.println("Create folder ChaTTY = " + dir.mkdir());
        }

        final Map<String, String> MAP = new HashMap<>();
        MAP.put("bonjour", "bonjour! Comment puis-je vous aider aujourd'hui ?");
        MAP.put("ajouter un post", "Pour ajouter un nouveau post, vous pouvez cliquer sur le bouton 'Ajouter un post' qui s'affiche dans le menu principal de l'application et ensuite remplir le formulaire affiché. Autre question ?");
        MAP.put("supprimer un post", "Pour supprimer un post ajouté, vous pouvez simplement cliquer sur le bouton 'Supprimer un post' dans le menu principal de l'application. Autre question ?");
        MAP.put("modifier un post", "Pour modifier un post déjà ajouté, vous pouvez cliquer sur le bouton 'Modifier un post' qui s'affiche dans le menu principal de l'application et ensuite remplir le formulaire affiché avec les nouvelles données. Autre question ?");
        MAP.put("ajouter un commentaire à un post", "Pour ajouter un commentaire à un post, vous pouvez cliquer sur le bouton 'Ajouter un commentaire' en dessous de chaque post dans le menu principal de l'application. Autre question ?");
        MAP.put("supprimer un commentaire", "Pour supprimer un commentaire ajouté, vous pouvez simplement cliquer sur le bouton 'Supprimer un commentaire' dans le menu principal de l'application. Autre question ?");
        MAP.put("modifier un commentaire", "Pour modifier un commentaire déjà ajouté, vous pouvez cliquer sur le bouton 'Modifier un commentaire' qui s'affiche dans le menu principal de l'application et ensuite remplir le formulaire affiché avec les nouvelles données. Autre question ?");
        MAP.put("merci", "Je vous en prie! N'hésitez pas si vous avez d'autres questions.");
        MAP.put("au revoir", "Au revoir! Bonne journée!");

        File responsesFile = new File(dir, "responses.json");
        if (!responsesFile.exists()) {
            logger.info("Creating responses.json file");
            String json = new Gson().toJson(MAP);
            Files.write(responsesFile.toPath(), json.getBytes());
        }
    }}

    private static final String APP_DATA_DIR = System.getenv("LOCALAPPDATA") + "\\Programs";
    private static final File DIR = new File(APP_DATA_DIR, "ChaTTY");

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

    public static File getResponsesFile() {
        String appDataPath = System.getenv("LOCALAPPDATA") + "\\Programs";
        File dir = new File(appDataPath, "ChaTTY");
        return new File(dir, "responses.json");
    }

    public static boolean doesNotExist() {
        return !new File(DIR, "responses.json").exists();
    }

}
