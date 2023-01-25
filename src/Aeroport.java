public class Aeroport {
    private String IATA;
    private String Name;
    private String country;
    private double latitude;
    private double longitude;

    public Aeroport(String IATA, String name, String country, double latitude, double longitude) {
        this.IATA = IATA;       //accéder à la variable
        this.Name = name;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    Aeroport() {}
    public String getIATA()
    {
        return IATA;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    //test
    public static void main(String[] args)

    {
        Aeroport a1= new Aeroport("OR", "ORLY", "FRANCE", 50.2, 130.3);
        System.out.println(a1);
    }

    @Override
    public String toString() {
        return "Aeroport{" +
                "IATA='" + IATA + '\'' +
                ", Name='" + Name + '\'' +
                ", country='" + country + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
