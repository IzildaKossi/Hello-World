import java.text.*;
/**
 * Escreva a descrição da classe Trucks aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Trucks
{
    private static int nextNumber = 0;
    private String name;
    private int number;
    private Coordinates coordinates;
    private double totalKilometersTravel;
    private double totalKilometersLastInspection;
    private double totalInspections;
    private Containers containers;
    private int quantityContainer;
    private boolean full;
    private boolean travel;

    public Trucks(String name, Coordinates coordinates) {
        this.name = name;
        this.number = ++Trucks.nextNumber;
        this.coordinates = coordinates;
        this.totalKilometersTravel = 0;
        this.totalKilometersLastInspection = 0;
        this.totalInspections = 0;
        this.full = false;
        this.travel = false;
        this.containers = null;
        this.quantityContainer = 0;
    }

    public boolean addContainer(Containers container) {
        //contentor não for nula
        if ((container != null)
                //e não tiver contentor
                && (quantityContainer == 0)
                //e estiver vazio
                && (full == false)) {
            //recebe o contentor
            setContainers(container);
            //incrementa a quantidade
            quantityContainer += 1;
            //fica cheia
            full = true;
            //retorna verdadeira
            return true;
        }//se for o contrário, retorna falso
        return false;
    }

    public Containers removeContainer() {
        //e tiver um contentor
        if ((quantityContainer == 1)
                //e estiver cheio
                && (full == true)) {
            //cria  uma cópia do contentor
            Containers containerRemove = containers;
            //camião fica descarrega o contentor
            setContainers(null);
            //decrementa a quantidade
            quantityContainer -= 1;
            //fica vazia
            full = false;
            //retorna o contentor removido 
            return containerRemove;
        }//se for o contrário, retorna nulo
        return null;
    }

    /* Metódo que verifica se o camião foi movido para loja e/ou garagem */
    public boolean moveTruck(Coordinates coordinate) {
        //se a latitude do camião for igual a latitude recebida
        if ((coordinates.getLatitude() == coordinate.getLatitude())
                //e se a longitude do camião for igual a longitude recebida
                && (coordinates.getLongitude() == coordinate.getLongitude())) {
            //atualiza a viagem
            travel = true;
            //retorna verdadeira
            return true;
        }//se for o contrário, retorna falso
        return false;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        String result = "\n-- CAMIÃO [" + String.format("%02d", number) + "] --";
        result += "\nID                 : " + number;
        result += "\nNome               : " + name.concat(String.valueOf(number));
        result += "\nTotal KM da Viagem : " + df.format(convertKM(totalKilometersTravel)) + "Km";
        result += "\nKM última Inspecção: " + df.format(convertKM(totalKilometersLastInspection)) + "Km";
        result += "\nInspecção Total    : " + df.format(totalInspections);
        result += "\n" + coordinates.toString();
        if (quantityContainer > 0) {
            result += "\nTotal Contentores  : " + quantityContainer;
            result += containers.toString();
        } else {
            result += "\nTotal Contentores  : " + quantityContainer;
        }
        return result;
    }
    
     /* metódo para converter m em Km */
    public double convertKM(double totalKM) {
        //volume a obter em m3
        double KMConvert;
        //Valor em metro = valor metro / 1000
        KMConvert = totalKM / 1000;
        //retorna o volume em metros
        return KMConvert;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public double getTotalKilometersTravel() {
        return totalKilometersTravel;
    }

    public void setTotalKilometersTravel(double totalKilometersTravel) {
        this.totalKilometersTravel = totalKilometersTravel;
    }

    public double getTotalKilometersLastInspection() {
        return totalKilometersLastInspection;
    }

    public void setTotalKilometersLastInspection(double totalKilometersLastInspection) {
        this.totalKilometersLastInspection = totalKilometersLastInspection;
    }

    public double getTotalInspections() {
        return totalInspections;
    }

    public void setTotalInspections(double totalInspections) {
        this.totalInspections = totalInspections;
    }

    public Containers getContainers() {
        return containers;
    }

    public void setContainers(Containers containers) {
        this.containers = containers;
    }

    public int getQuantityContainer() {
        return quantityContainer;
    }

    public void setQuantityContainer(int quantityContainer) {
        this.quantityContainer = quantityContainer;
    }

    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    public boolean isTravel() {
        return travel;
    }

    public void setTravel(boolean travel) {
        this.travel = travel;
    }

}
