package persistencia;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AppDataPaths {

    private static final String APP_NAME = "Pierak";

    public static Path getAppDataDir() {
        String os = System.getProperty("os.name").toLowerCase();
        String userHome = System.getProperty("user.home");

        if (os.contains("win")) {
            String localAppData = System.getenv("LOCALAPPDATA");
            return Paths.get(localAppData, APP_NAME);
        }

        if (os.contains("mac")) {
            return Paths.get(
                userHome,
                "Library",
                "Application Support",
                APP_NAME
            );
        }

        // Linux y otros
        return Paths.get(userHome, "." + APP_NAME.toLowerCase());
    }

    public static Path getDataDir() {
        return getAppDataDir().resolve("data");
    }
}
