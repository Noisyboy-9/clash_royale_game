package controllers.menus;

import controllers.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * The type Scene controller.
 */
public class SceneController {
    private final HashMap<String, Scene> menus;
    private final ArrayList<String> cardsUrls;
    private final HashMap<String, Image> gifs;
    private final HashMap<String, Image> battleCardsBoxImages;
    private final HashMap<String, Image> comingCardsBoxImages;
    private final HashMap<String, Image> nextCardImages;

    /**
     * Instantiates a new Scene controller.
     */
    public SceneController() {
        this.menus = new HashMap<>();
        this.cardsUrls = new ArrayList<>();
        this.gifs = new HashMap<>();
        this.battleCardsBoxImages = new HashMap<>();
        this.comingCardsBoxImages = new HashMap<>();
        this.nextCardImages = new HashMap<>();

    }

    /**
     * Remove scene.
     *
     * @param sceneName the scene name
     */
    public void removeScene(String sceneName) {
        this.menus.remove(sceneName);
    }

    /**
     * Show scene.
     *
     * @param sceneName the scene name
     */
    public void showScene(String sceneName) {
        try {
            Scene scene;
            if (menus.containsKey(sceneName)) {
                scene = menus.get(sceneName);
            } else {
                URL url = new URL("file:/" + Controller.VIEW_PATH + sceneName);
                Parent root = FXMLLoader.load(url);
                scene = new Scene(root, 528, 946);
                this.menus.put(sceneName, scene);
            }

            Controller.STAGE.setTitle("Clash Royale");
            Controller.STAGE.setScene(scene);
            Controller.STAGE.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Load all menu scenes.
     */
    public void loadAllMenuScenes() {
        try {
            DirectoryStream<Path> directoryStream = this.getDirectoryStreamBasedOnOs(Controller.VIEW_PATH + "Menu");
            String protocol = this.getFileProtocol();
            for (Path path : directoryStream) {
                URL url = new URL(protocol + path);
                Parent menuRoot = FXMLLoader.load(Objects.requireNonNull(url));
                menus.put("Menu/" + path.getFileName(), new Scene(menuRoot, 528, 946));
            }
        } catch (IOException e) {
            System.err.println("there was a problem related to IO");
        }
    }


    /**
     * Load all cards urls.
     */
    public void loadAllCardsUrls()
    {
        try {
            DirectoryStream<Path> directoryStream = this.getDirectoryStreamBasedOnOs(Controller.RESOURCE_PATH + "photos");
            String protocol = this.getFileProtocol();
            for (Path path : directoryStream)
            {
                if (path.toString().contains("Card"))
                {
                    URL url = new URL(protocol + path);
                    this.cardsUrls.add(url.toString());

                }

            }
        } catch (IOException e) {
            System.err.println("there was a problem related to IO");
        }

    }


    /**
     * Load card box images.
     */
    public void loadCardBoxImages() {
        for (String url : this.cardsUrls) {
            String className = getClassName(url);
            Image battleBoxImage = new Image(url, 59, 73, true, true);
            Image comingBoxImage = new Image(url, 34, 41, true, true);
            Image nextCardImage = new Image(url, 50, 62, true, true);

            this.battleCardsBoxImages.put(className, battleBoxImage);
            this.comingCardsBoxImages.put(className, comingBoxImage);
            this.nextCardImages.put(className, nextCardImage);

        }

    }

    private String getClassName(String url) {
        String[] parts = url.split("/");
        int lastIndex = parts.length - 1;
        String className = parts[lastIndex].replace("Card.png", "");
        className = switch (className) {
            case "Archers" -> "Archer";
            case "Barbarians" -> "Barbarian";
            case "MiniPEKKA" -> "MiniPekka";
            case "Fireball" -> "FireBall";
            default -> className;
        };

        return className;
    }


    /**
     * Load gifs.
     */
    public void loadGifs()
    {
        try {
            DirectoryStream<Path> directoryStream = this.getDirectoryStreamBasedOnOs(Controller.RESOURCE_PATH + "gifs");
            String protocol = this.getFileProtocol();
            for (Path path : directoryStream)
            {
                String name = path.getFileName().toString();
                name = name.replace(".gif", "");

                if (name.equals("splash_screen_time_line"))
                    continue;

                ArrayList<Integer> size = getSizeFromName(name);
                String address = protocol + path;
                Image gif = new Image(address, size.get(0), size.get(1), true, true);

                String key = getKeyFromName(name);

                this.gifs.put(key, gif);

            }
        } catch (IOException e) {
            System.err.println("there was a problem related to IO");
        }

    }

    private ArrayList<Integer> getSizeFromName(String name) {
        ArrayList<Integer> size = new ArrayList<>();

        String[] elements = name.split("_");
        String sizePart = elements[3];
        String[] widthHeight = sizePart.split("-");

        int width = Integer.parseInt(widthHeight[0]);
        int height = Integer.parseInt(widthHeight[1]);

        size.add(width);
        size.add(height);

        return size;

    }

    private String getKeyFromName(String name) {
        String[] elements = name.split("_");
        return elements[0] + "_" + elements[1] + "_" + elements[2];
    }


    private String getFileProtocol() {
        if (!System.getProperty("os.name").contains("Windows")) {
//            for mac and linux file system
            return "file://";
        }

//        for windows file system
        return "file:/";
    }

    private DirectoryStream<Path> getDirectoryStreamBasedOnOs(String exclusivePath) throws IOException {
        if (!System.getProperty("os.name").contains("Windows")) {
//                create a directory stream on mac or linux
            return Files.newDirectoryStream(Paths.get("/" + exclusivePath + "/"));
        }

//                create a directory stream for windows
        return Files.newDirectoryStream(Paths.get(exclusivePath));
    }


    /**
     * Convert to black and white.
     *
     * @param imageView the image view
     */
    public void convertToBlackAndWhite(ImageView imageView)
    {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setSaturation(-1);
        colorAdjust.setBrightness(-0.2);

        imageView.setEffect(colorAdjust);
        imageView.setCursor(Cursor.DEFAULT);

    }

    /**
     * Convert to colorful.
     *
     * @param imageView the image view
     */
    public void convertToColorful(ImageView imageView)
    {
        imageView.setEffect(null);
        imageView.setCursor(Cursor.HAND);

    }


    /**
     * Gets cards urls.
     *
     * @return the cards urls
     */
    public ArrayList<String> getCardsUrls()
    {
        return cardsUrls;
    }


    /**
     * Gets gif.
     *
     * @param key the key
     * @return the gif
     */
    public Image getGif(String key) {
        if (!gifs.containsKey(key)) {
            key = key.replace("walk", "fight");
        }
        return gifs.get(key);

    }


    /**
     * Gets battle box img.
     *
     * @param className the class name
     * @return the battle box img
     */
    public Image getBattleBoxImg(String className) {
        return this.battleCardsBoxImages.get(className);
    }


    /**
     * Gets coming box img.
     *
     * @param className the class name
     * @return the coming box img
     */
    public Image getComingBoxImg(String className) {
        return this.comingCardsBoxImages.get(className);
    }


    /**
     * Gets next card img.
     *
     * @param className the class name
     * @return the next card img
     */
    public Image getNextCardImg(String className) {
        return this.nextCardImages.get(className);
    }

}
