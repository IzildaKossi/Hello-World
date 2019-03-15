
/**
 * Escreva a descrição da classe Garages aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Garages
{
   // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    private static final int MAX_CONTAINERS = 10;//2 contentores
    private static final int MAX_TRUCKS = 10;
    private int quantityTruck;
    private Trucks[] trucks;
    private int quantityContainer;
    private Containers[] containers;
    private Coordinates coordinates;
    private String name;

    /**
     * COnstrutor para objetos da classe Garages
     */
    public Garages(String name, Coordinates coordinates) {
        // inicializa variáveis de instância
        this.name = name;
        this.coordinates = coordinates;
        this.quantityTruck = 0;
        this.quantityContainer = 0;
        this.trucks = new Trucks[Garages.MAX_TRUCKS];
        this.containers = new Containers[Garages.MAX_CONTAINERS];
    }

    public boolean isFullTruck() {
        return quantityTruck == Garages.MAX_TRUCKS;
    }

    public boolean isEmptyTruck() {
        return quantityTruck == 0;
    }

    public boolean addTruck(Trucks truck) {
        //não estiver cheia
        if (isFullTruck() != true) {
            //adiciona na loja
            trucks[quantityTruck] = truck;
            //aumenta a quantidade + 1
            quantityTruck++;
            //retorna verdadeira
            return true;
        }
        return false;
    }

    public String showGarageByNameAndCoordinates() {
        String result = "\n\t--- GARAGEM [" + name + "] ---";
        result += "\n\tName     : " + name;
        if (coordinates.getLatitude() > 0) {
            result += "\n\tLatitude : " + coordinates.getLatitude() + "º NORTE";
        } else {
            result += "\n\tLatitude : " + coordinates.getLatitude() + "º SUL";
        }
        if (coordinates.getLongitude() > 0) {
            result += "\n\tLongitude: " + coordinates.getLongitude() + "º ESTE";
        } else {
            result += "\n\tLongitude: " + coordinates.getLongitude() + "º OESTE";
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "\n--- GARAGEM [" + name + "] ---";
        result += "\nNome             : " + name;
        result += "\n" + coordinates.toString();
        result += "\nTotal Contentores: " + quantityContainer;
        for (int i = 0; i < quantityContainer; i++) {
            if (containers[i] != null) {
                result += containers[i].toString();
            }
        }
        result += "\nTotal Camiões    : " + quantityTruck;
        for (int i = 0; i < quantityTruck; i++) {
            if (trucks[i] != null) {
                result += trucks[i].toString();
            }
        }
        return result;
    }

    public int getQuantityTruck() {
        return quantityTruck;
    }

    public void setQuantityTruck(int quantityTruck) {
        this.quantityTruck = quantityTruck;
    }

    public Trucks[] getTrucks() {
        return trucks;
    }

    public void setTrucks(Trucks[] trucks) {
        this.trucks = trucks;
    }

    public int getQuantityContainer() {
        return quantityContainer;
    }

    public void setQuantityContainer(int quantityContainer) {
        this.quantityContainer = quantityContainer;
    }

    public Containers[] getContainers() {
        return containers;
    }

    public void setContainers(Containers[] containers) {
        this.containers = containers;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
