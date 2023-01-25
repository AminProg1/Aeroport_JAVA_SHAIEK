import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.PerspectiveCamera;
//zoom
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Translate;
//recupérer coodonnées
import javafx.scene.input.PickResult;
import javafx.scene.input.MouseButton;
import javafx.geometry.Point2D;
import java.io.File;


public class Interface extends Application {
    Scene ihm;
    PerspectiveCamera camera;
    double mouseX;
    double mouseY;
    World world;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello world");
        //Group root = new Group();
        Earth earth = new Earth();
        //Pane pane = new Pane(earth);
        camera = new PerspectiveCamera(true); // besoin d'un objet de type "Caméra" en 3D
        camera.setTranslateZ(-1000);
        camera.setNearClip(0.1); // indique les distances auxquels les objets ne sont plus affichés
        camera.setFarClip(2000.0); //indique les distances auxquels les objets ne sont plus affichés
        camera.setFieldOfView(35); //permet de fixer l'angle de vision en degré
        //airport
        String csvChemin = new File("").getAbsolutePath();
        csvChemin += "./data/airport-codes_no_comma.csv";
        System.out.println(csvChemin);
        world = new World(csvChemin);

        ihm = new Scene(earth, 600, 400,true);
        zoom(); //fct zoom
        Coordonnees(); //fct récupération des données
        ihm.setCamera(camera); // ihm= nom de la scene
        primaryStage.setScene(ihm);
        primaryStage.show();
    }

    public void zoom()
    {
        ihm.addEventHandler(MouseEvent.ANY, event -> {
            if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                System.out.println("Clicked on : (" + event.getSceneX() + ", "+ event.getSceneY() + ")");
                mouseX=event.getSceneX();
                mouseY=event.getSceneY();
            }
            if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                if(event.getSceneY() > mouseY)
                {
                    mouseX=event.getSceneX();
                    mouseY=event.getSceneY();
                    camera.getTransforms().add(new Translate(0,0,2));
                }
                else
                {
                    mouseX=event.getSceneX();
                    mouseY=event.getSceneY();
                    camera.getTransforms().add(new Translate(0,0,-2));
                }
            }
        });
    }

    public void Coordonnees()
    {
        ihm.addEventHandler(MouseEvent.ANY, event -> {
            if (event.getButton() == MouseButton.SECONDARY && event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                PickResult pickResult = event.getPickResult();
                if (pickResult.getIntersectedNode() != null) {
                    Point2D point = pickResult.getIntersectedTexCoord(); // on r´ecup`ere le point d'intersection
                    double longitude = 360 * (point.getX() - 0.5); // Conversion en longitude et lattitude avec les formules
                    double latitude = 360 * (point.getY() - 0.5);
                    //double latitude = 2 * Math.toDegrees(Math.atan(Math.exp((0.5 - point.getY()) / 0.2678))) - 90;
                    System.out.println("Clic sur le bouton droit : longitude : " + longitude + ", latitude : " + latitude + ")");
                    Aeroport aeo = world.findNearestAirport(longitude, latitude);// Recherche dans l'objet w de la classe World de l'a´eroport le plus proche.
                    System.out.println(aeo); // Affichage dans la consoles
                }
            }
        });
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
