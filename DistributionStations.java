import java.util.*;
/**
 * Escreva a descrição da classe DistributionStations aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class DistributionStations
{
    public static final int MAX_CONTAINERS = 10;//10
    public static final int MAX_TRUCKS = 10;//10
    public static final int MAX_PACKS = 10;//10 
    private String name;
    private Coordinates coordinates;
    private WareHouse wareHouse;
    private int quantityContainer;
    private Containers[] containers;
    private int quantityTrucks;
    private Trucks[] trucks;
    private int quantityStores;

    public DistributionStations(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        this.quantityContainer = 0;
        this.containers = new Containers[DistributionStations.MAX_CONTAINERS];
        this.quantityTrucks = 0;
        this.trucks = new Trucks[DistributionStations.MAX_TRUCKS];
        this.quantityStores = 0;
        this.wareHouse = new WareHouse(DistributionStations.MAX_PACKS);
    }

    public boolean createPacks(String namePack, int quantity, int weight, float volume) {
        //faz um loop
        for (int i = 0; i < DistributionStations.MAX_PACKS; i++) {
            //cria um objeto do pack
            Packs newPack = new Packs(namePack, quantity, weight, volume);
            //insira no armazem
            wareHouse.addPack(newPack);
            //retorna verdadeira
            return true;
        }//se for o contrário, retorna falso
        return false;
    }

    public boolean existPackByName(String namePack) {
        //faz um loop
        for (int i = 0; i < wareHouse.getQuantPacks(); i++) {
            //se o nome for igual
            if (wareHouse.getPacks()[i].getName().equalsIgnoreCase(namePack)) {
                //retorna verdadeira
                return true;
            }
        }//se for o contrário, retorna falso
        return false;
    }

    public boolean existPackByNumber(int numberPack) {
        //faz um loop
        for (int i = 0; i < wareHouse.getQuantPacks(); i++) {
            //se o numero for igual
            if (wareHouse.getPacks()[i].getNumber() == numberPack) {
                //retorna verdadeira
                return true;
            }
        }//se for o contrário, retorna falso
        return false;
    }

    private boolean isEmptyTruck() {
        return quantityTrucks == 0;
    }

    public boolean createNewTruck(String truckName) {
        //cria um novo
        Trucks truck = new Trucks(truckName, coordinates);
        //adiciona no estação
        trucks[quantityTrucks] = truck;
        //aumenta a quantidade + 1
        quantityTrucks++;
        //retorna verdadeira
        return true;
    }

    public Trucks removeTruck(int numberTruck) {
        int idx;
        Trucks truckRemove = null;
        //se tiver camião
        if (isEmptyTruck() != true) {
            //obter a posição do camião
            idx = indexOfTruckByNumber(numberTruck);
            //se for diferente de - 1
            if (idx != -1) {
                //cria uma cópia
                truckRemove = trucks[idx];
                //faz o loop
                for (int i = idx; i < quantityTrucks - 1; i++) {
                    //elimina da posição
                    trucks[idx] = trucks[idx + 1];
                }//diminui a quantidade - 1
                quantityTrucks--;
            }
        }//retorna o camião removido
        return truckRemove;
    }

    public boolean existTruck(int numberTruck) {
        int idx;
        //se tiver camião
        if (isEmptyTruck() != true) {
            //obter a posição do camião
            idx = indexOfTruckByNumber(numberTruck);
            //se for diferente de - 1
            if (idx != -1) {
                //retorna verdadeira
                return true;
            }
        }//se for o contrário, retorna falso
        return false;
    }

    public Trucks getTruck(int numberTruck) {
        int idx;
        //se tiver camião
        if (isEmptyTruck() != true) {
            //obter a posição do camião
            idx = indexOfTruckByNumber(numberTruck);
            //se for diferente de - 1
            if (idx != -1) {
                //se existir
                if (existTruck(numberTruck) == true) {
                    //retorna verdadeira
                    return trucks[idx];
                }
            }
        }//se for o contrário, retorna falso
        return null;
    }

    public boolean putContainerInTruck(int numberTruck, Containers container) {
        int idx;
        //se tiver camião
        if (isEmptyTruck() != true) {
            //obter a posição do camião
            idx = indexOfTruckByNumber(numberTruck);
            //se for diferente de - 1
            if (idx != -1) {
                //se for não for nula
                if ((container != null)
                        //e se existir
                        && (existContainer(container.getNumber()) == true)) {
                    //será colocado no camião
                    trucks[idx].addContainer(container);
                    //e removido da estação
                    if (removeContainer(container.getNumber()) == true) {
                        //retorna verdadeira
                        return true;
                    }
                }
            }
        }//se for o contrário, retorna falso
        return false;
    }

    public boolean getStoreCoordiantes(int numberTruck, Stores store) {
        int idx;
        double totalKM;
        double distance;
        //se tiver camião
        if (isEmptyTruck() != true) {
            //obter a posição do camião
            idx = indexOfTruckByNumber(numberTruck);
            //se for diferente de - 1
            if (idx != -1) {
                //faz o calculo da distancia 
                distance = coordinates.getDistance(store.getCoordinates());
                //recebe a distancia entre essas duas posições 60X1852 metros
                totalKM = 60 * distance;
                //atualiza o total KM da viajem
                trucks[idx].setTotalKilometersTravel(totalKM);
                //o camião recebe as coordenadas da loja
                trucks[idx].setCoordinates(store.getCoordinates());
                //retorna verdadeira
                return true;
            }
        }//se for o contrário, retorna falso
        return false;
    }

    public Trucks moveTruckToStore(int numberTruck, Stores store) {
        int idx;
        Trucks truckRemove;
        //se tiver camião
        if (isEmptyTruck() != true) {
            //obter a posição do camião
            idx = indexOfTruckByNumber(numberTruck);
            //se for diferente de - 1
            if (idx != -1) {
                //se já recebeu as coordenadas
                if (getStoreCoordiantes(trucks[idx].getNumber(), store) == true) {
                    //verifica se as coordenadas são iguais
                    if (trucks[idx].moveTruck(store.getCoordinates()) == true) {
                        //remove da estação
                        truckRemove = removeTruck(numberTruck);
                        //retorna o camião
                        return truckRemove;
                    }
                }
            }
        }//se for o contrário, retorna nulo
        return null;
    }

    public boolean isEmptyContainer() {
        return quantityContainer == 0;
    }

    public boolean isFullContainer() {
        return quantityContainer == DistributionStations.MAX_CONTAINERS;
    }

    public boolean createNewContainer(Containers container) {
        //se tiver contentor cheia
        if (isFullContainer() != true) {
            //adiciona no estação
            containers[quantityContainer] = container;
            //aumenta a quantidade + 1
            quantityContainer++;
            //retorna verdadeira
            return true;
        }//se for o contrário, retorna falso
        return false;
    }

    public boolean removeContainer(int numberContainer) {
        int idx;
        //obter a posição do contentor a remover
        idx = indexOfContainerByNumber(numberContainer);
        //se for diferente de -1
        if (idx != -1) {
            //faz o loop
            for (int i = idx; i < quantityContainer - 1; i++) {
                //elimina do array
                containers[i] = containers[i + 1];
            }//diminui a quantidade - 1
            quantityContainer--;
            //retorna verdadeira
            return true;
        }//se for o contrário, retorna falso
        return false;
    }

    public Packs putPackInContainter(int numberContainer, int numberPack, int quantity) {
        int idx;
        Packs pack;
        //se tiver contentor
        if (isEmptyContainer() != true) {
            //obter a posição do contentor
            idx = indexOfContainerByNumber(numberContainer);
            //se for diferente de - 1
            if (idx != -1) {
                //valida o contentor
                if (existContainer(containers[idx].getNumber()) == true) {
                    //retira o pack do armazem
                    pack = wareHouse.removeQuantityPack(numberPack, quantity);
                    //se não for nula
                    if (pack != null) {
                        //tenta adicionar o pack no contentor
                        if (containers[idx].addPack(pack) == true) {
                            //se for inserido, retorna verdadeira
                            return pack;
                        }
                    }
                }
            }
        }//se for o contrário, retorna nulo
        return null;
    }

    public boolean existContainer(int numberContainer) {
        int idx;
        //se tiver contentor
        if (isEmptyContainer() != true) {
            //obter a posição do contentor
            idx = indexOfContainerByNumber(numberContainer);
            //se for diferente de - 1
            if (idx != -1) {
                //retorna verdadeira
                return true;
            }
        }//se for o contrário, retorna falso
        return false;
    }

    public Containers existContainerWithPack() {
        //se tiver contentor
        if (isEmptyContainer() != true) {
            //faz o loop
            for (int i = 0; i < quantityContainer; i++) {
                // se for diferente de nulo
                if ((containers[i] != null)
                        //e estiver for diferente de vazio 
                        && (containers[i].isEmpty() != true)) {
                    //retorna o contentor
                    return containers[i];
                }
            }
        }//se for o contrário, retorna nulo
        return null;
    }

    @Override
    public String toString() {
        String result = "\n--- ESTAÇÃO DE DISTRIBUIÇÃO [" + name + "] ---";
        result += "\nNome             : " + name;
        result += "\n" + coordinates.toString();
        result += wareHouse.toString();
        result += "\nTotal Contentores: " + quantityContainer;
        for (int i = 0; i < quantityContainer; i++) {
            if (containers[i] != null) {
                result += containers[i].toString();
            }
        }
        result += "\nTotal Camiões    : " + quantityTrucks;
        for (int i = 0; i < quantityTrucks; i++) {
            if (trucks[i] != null) {
                result += trucks[i].toString();
            }
        }

        return result;
    }

    private int indexOfTruckByNumber(int numberTruck) {
        //se tiver algum camiao criado
        if (isEmptyTruck() != true) {
            //faz o loop
            for (int i = 0; i < quantityTrucks; i++) {
                //se não for nula
                if ((trucks[i] != null)
                        //e tiver o mesmo codigo
                        && (trucks[i].getNumber() == numberTruck)) {
                    //retorna a posição no array
                    return i;
                }
            }
        }//se for o contrário, retorna -1
        return -1;
    }

    private int indexOfContainerByNumber(int number) {
        //se tiver algum camiao criado
        if (isEmptyContainer() != true) {
            //faz o loop
            for (int i = 0; i < quantityContainer; i++) {
                //se não for nula
                if ((containers[i] != null)
                        //e tiver o mesmo codigo
                        && (containers[i].getNumber() == number)) {
                    //retorna a posição no array
                    return i;
                }
            }
        }//se for o contrário, retorna -1
        return -1;
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

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public WareHouse getWareHouse() {
        return wareHouse;
    }

    public void setWareHouse(WareHouse wareHouse) {
        this.wareHouse = wareHouse;
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

    public int getQuantityTrucks() {
        return quantityTrucks;
    }

    public void setQuantityTrucks(int quantityTrucks) {
        this.quantityTrucks = quantityTrucks;
    }

    public Trucks[] getTrucks() {
        return trucks;
    }

    public void setTrucks(Trucks[] trucks) {
        this.trucks = trucks;
    }

    public int getQuantityStores() {
        return quantityStores;
    }

    public void setQuantityStores(int quantityStores) {
        this.quantityStores = quantityStores;
    }

}
