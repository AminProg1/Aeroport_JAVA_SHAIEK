import javafx.scene.Group;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
//texture
import javafx.scene.paint.PhongMaterial;
import javafx.scene.image.Image;
import java.io.File;
//rotate
import javafx.animation.AnimationTimer;
import javafx.geometry.Point3D;
//sphere colorée
import javafx.scene.paint.Color;




public class Earth extends Group {

    private Sphere sph;
    private Rotate ry;

    Earth()
    {
        String PathImage = new File("").getAbsolutePath();
        PathImage += "./data/earth_lights_4800.png";
        //sphere
        sph = new Sphere(300.0); //sphere 300 pixels de rayon
        this.getChildren().add(sph);

        //texture
        PhongMaterial Phong = new PhongMaterial();
        Image image = new Image(PathImage);
        Phong.setDiffuseMap(image);
        sph.setMaterial(Phong);

        //rotation
        ry = new Rotate(0,new Point3D(0,1,0));
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long time) {
                //System.out.println("Valeur de time : " + time);
                ry.setAngle(0.1); // A compl´eter
                sph.getTransforms().add(ry);

            }
        };
        animationTimer.start();
    }

    public Sphere createSphere(Aeroport a, Color color)
    {
        Sphere sph2 = new Sphere(2);//sphère rayon 2
        PhongMaterial Phong = new PhongMaterial(color);
        sph2.setMaterial(Phong);
        return sph2;
    }
    public void displayRedSphere(Aeroport a)
    {
        Sphere sph2 = createSphere(a,Color.RED);
        this.getChildren().add(sph2);
    }

}

