import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;


public class World
{
    ArrayList<Aeroport> list = new ArrayList<>();
    World(String fileName)
    {
        try{
            BufferedReader buf = new BufferedReader(new FileReader(fileName));
            String s = buf.readLine();
            while(s!=null){
                s=s.replaceAll("\"","");
                String fields[]=s.split(",");
// Une bonne id´ee : placer un point d'arr^et ici pour du debuggage.
                if (fields[1].equals("large_airport"))
                {
                    list.add(new Aeroport(
                            fields[9], //IATA
                            fields[2], //name
                            fields[5], //Country
                            Double.parseDouble(fields[12]),    // latitude
                            Double.parseDouble(fields[11])));   // longitude
                }
                s = buf.readLine();
            }
        }
        catch (Exception e){
            System.out.println("Maybe the file isn't there ?");
            System.out.println(list.get(list.size()-1));
            e.printStackTrace();
        }
    }

    public Aeroport findByCode(String code){
        for (Aeroport a : list){
            if (a.getIATA().equals(code)) return a;
        }
        return null;

    }
    //test
    public static void main (String args[])
    {
        String csvChemin =  "./data/airport-codes_no_comma.csv";
        System.out.println(csvChemin);
        World world = new World(csvChemin);
        System.out.println("I found "+world.list.size()+" aiports");
        System.out.println(world.findByCode("CDG"));

        // test
        System.out.println("Found " + world.getList().size() + " airports");
        Aeroport paris = world.findNearestAirport(2.316,48.866);
        Aeroport cdg = world.findByCode("CDG");
        double distance = world.distance(2.316,48.866,paris);
        System.out.println(paris);
        System.out.println(distance);
        double distanceCDG = world.distance(2.316,48.866,cdg);
        System.out.println(cdg);
        System.out.println(distanceCDG);
    }


    protected double distance(double longitude, double latitude, Aeroport a)
    {
        double apLat = a.getLatitude();
        double apLong = a.getLongitude();
        return Math.pow((apLat - latitude),2) + Math.pow(((apLong - longitude) * Math.cos((apLat + latitude)/2)),2);
    }


    //ajouter la fonction nearest
    public Aeroport findNearestAirport(double longitude, double latitude)
    {
        int index_min = 0; // ca permet de créer l'index à laquelle on a la distance minimal c'est pour la boucle for
        double distance_min = distance(latitude,longitude,list.get(0));
        for(int i=1; i < list.size(); i++)
        {
            if(distance(latitude,longitude,list.get(i)) < distance_min)
            {
                distance_min = distance(latitude,longitude,list.get(i));
                index_min = i;
            }
        }
        return  list.get(index_min);
    }




    //test fct distance


    public ArrayList<Aeroport> getList() {
        return list;
    }
}
