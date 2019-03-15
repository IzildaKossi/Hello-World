
/**
 * Escreva a descrição da classe Coordinates aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Coordinates
{
   private static final float MAX_LATITUDE = 90.0f;
    private static final float MIN_LATUTIDE = -90.0f;
    private static final float MAX_LONGITUDE = 180.0f;
    private static final float MIN_LONGITUDE = -180.0f;
    private float latitude;
    private float longitude;

     /* COnstrutor */
    public Coordinates(float latitude, float longitude) {
        //se a latitude e longitude for válida
        if (isValidCoordinates(latitude, longitude) == true) {
            this.latitude = latitude;
            this.longitude = longitude;
        }//se não for válida 
        else {
            this.latitude = 0.0f;
            this.longitude = 0.0f;
        }
    }

    /* Metódo para validar a latitude e longitude */
    private boolean isValidCoordinates(float newLatitude, float newLongitude) {
        //se latitude for válida 
        if ((isLatitudeValid(newLatitude) == true)
                //se longitude for válida 
                && (isLongitudeValid(newLongitude) == true)) {
            //retorna verdadeira
            return true;
        }
        //se for o contrário, retorna falso
        return false;
    }

    /* Metódo para validar latitude */
    public static boolean isLatitudeValid(float newLatitude) {
        //se latitude estiver entre -90º
        if ((newLatitude >= Coordinates.MIN_LATUTIDE)
                // e 90º
                && (newLatitude <= Coordinates.MAX_LATITUDE)) {
            //retorna verdadeira
            return true;
        }
        //se for o contrário, retorna falso
        return false;
    }

    /* Metódo para validar longitude */
    public static boolean isLongitudeValid(float newLongitude) {
        //se longitude estiver entre -180º
        if ((newLongitude >= Coordinates.MIN_LONGITUDE)
                // e 180º
                && (newLongitude <= Coordinates.MAX_LONGITUDE)) {
            //retorna verdadeira
            return true;
        }
        //se for o contrário, retorna falso
        return false;
    }

    /* Metódo que calcula a distancia entre dois posição através da distância euclidiana */
    public double getDistance(Coordinates other) {
        //distância euclidiana
        double distance;
        //d = raiz ( (Xa-Xb)² + (Ya-Yb)² ).
        distance = Math.sqrt(Math.pow((latitude - other.latitude), 2) + Math.pow((longitude - other.longitude), 2));
        //retorna a distância
        return distance;
    }

    @Override
    public String toString() {
        String result = "-- COORDENADAS --";
        if (latitude > 0) {
            result += "\nLatitude         : " + latitude + "º NORTE";
        } else {
            result += "\nLatitude         : " + latitude + "º SUL";
        }
        if (longitude > 0) {
            result += "\nLongitude        : " + longitude + "º ESTE";
        } else {
            result += "\nLongitude        : " + longitude + "º OESTE";
        }
        return result;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

}
