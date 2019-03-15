
/**
 * Escreva a descrição da classe Stores aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Stores
{
     private static final int MAX_PACKS = 10;//10 tipos
    private static final int MAX_CONTAINERS = 2;//2 contentores
    private static final int MAX_TRUCKS = 10;
    private String name;
    private Coordinates coordinates;
    private WareHouse wareHouse;
    private int quantityContainer;
    private Containers[] containers;
    private int quantityTrucks;
    private Trucks[] trucks;

    public Stores(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        this.quantityContainer = 0;
        this.containers = new Containers[Stores.MAX_CONTAINERS];
        this.wareHouse = new WareHouse(Stores.MAX_PACKS);
        this.quantityTrucks = 0;
        this.trucks = new Trucks[Stores.MAX_TRUCKS];
    }

    public boolean isFullContainer() {
        return quantityContainer == Stores.MAX_CONTAINERS;
    }

    public boolean isEmptyContainer() {
        return quantityContainer == 0;
    }

    public boolean isFullTruck() {
        return quantityTrucks == Stores.MAX_TRUCKS;
    }

    public boolean isEmptyTruck() {
        return quantityTrucks == 0;
    }

    /* Metódo que adiciona o contentor na loja */
    public boolean addTruck(Trucks truck) {
        //não estiver cheia
        if (isFullTruck() != true) {
            //adiciona na loja
            trucks[quantityTrucks] = truck;
            //aumenta a quantidade + 1
            quantityTrucks++;
            //retorna verdadeira
            return true;
        }
        return false;
    }

    /* Metódo que remove o contentor na loja */
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

    /* Metódo para obter  descarregar o contentor do camião e adicionar na loja */
    public boolean getContainerFromTruck(int numberTruck) {
        int idx;
        //se tiver camião
        if (isEmptyTruck() != true) {
            //obter a posição do camião
            idx = indexOfTruckByNumber(numberTruck);
            //se for diferente de - 1
            if (idx != -1) {
                //se tiver algum camião com contentor
                if (trucks[idx].isFull()
                        // com pack 
                        && trucks[idx].getContainers().isEmpty() != true) {
                    //se não estiver cheia
                    if (isFullContainer() != true) {
                        //retira o contentor do camião
                        containers[quantityContainer] = trucks[idx].removeContainer();
                        //incrementa a quantidade
                        quantityContainer++;
                        //retorna verdadeira
                        return true;
                    }
                }
            }
        }//se for o contrário, retorna falso
        return false;
    }

    /* Metódo que remove o contentor da loja */
    public Containers removeContainer(int numberContainer) {
        int idx;
        Containers removeContainers = null;
        //obter a posição do contentor a remover
        idx = indexOfContainerByNumber(numberContainer);
        //se for diferente de -1
        if (idx != -1) {
            //se o contentor estiver vazio e descarregado
            if (containers[idx].isEmpty() == true) {
                //faz uma cópia do contentor removido da loja
                removeContainers = containers[idx];
                //faz o loop
                for (int i = idx; i < quantityContainer - 1; i++) {
                    //elimina do array
                    containers[i] = containers[i + 1];
                }//diminui a quantidade - 1
                quantityContainer--;
            }
        }//retorna o contentor removido
        return removeContainers;
    }

    /* Metódo para obter  retirar os packs do contentor e adiciona no armazem da loja */
    public Packs getPackFromContainter(int numberContainer, int numberPack) {
        int idx;
        Packs pack;
        //obter a posição do contentor
        idx = indexOfContainerByNumber(numberContainer);
        //se for diferente de -1
        if (idx != -1) {
            //obter o pack e remover do contentor
            pack = containers[idx].emptyContainer(numberPack);
            //se for diferente de nulo
            if (pack != null) {
                //adiciona ao armazém da loja
                if (wareHouse.addPack(pack) == true) {
                    //se for inserido, retorna verdadeira
                    return pack;
                }
            }
        }//se for o contrário, retorna nulo
        return null;
    }

    /* Metódo para obter  carregar o camião com o contetor descarregado da loja */
    public boolean putEmptyContainerInTruck(int numberTruck, int numberContainer) {
        int idx;
        Containers container;
        //obter a posição do camião
        idx = indexOfTruckByNumber(numberTruck);
        //se for diferente de -1
        if (idx != -1) {
            //se o camião estiver vazio
            if (trucks[idx].isFull() != true) {
                //obter o contentor removido
                container = removeContainer(numberContainer);
                //adiciona ao camião
                trucks[idx].addContainer(container);
                //retorna verdadeira
                return true;
            }
        }//se for o contrário, retorna falso
        return false;
    }

    /* Metódo para obter  o consumo do disponivel pack no armazem */
    public Packs consomePack(int numberPack, int quantity) {
        Packs pack;
        //obter o pack consumido do armazém por numero e quantidade
        pack = wareHouse.consumePack(numberPack, quantity);
        //se não for nula
        if (pack != null) {
            //retorna o pack
            return pack;
        }//e se for nula, retorna o pack
        return pack;
    }

    /* Metódo que valida o camião na loja */
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

    /* Metódo para obter o camião na loja */
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

    /* Metódo para obter a posição do camião na loja */
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

    /* Metódo para obter o contentor na loja */
    public Containers getContainer(int numberContainer) {
        int idx;
        //se tiver camião
        if (isEmptyTruck() != true) {
            //obter a posição do camião
            idx = indexOfContainerByNumber(numberContainer);
            //se for diferente de - 1
            if (idx != -1) {
                //se existir
                if (existContainer(numberContainer) == true) {
                    //retorna verdadeira
                    return containers[idx];
                }
            }
        }//se for o contrário, retorna falso
        return null;
    }

    /* Metódo para obter o camião na loja */
    public boolean getGarageCoordiantes(int numberTruck, Garages garage) {
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
                distance = coordinates.getDistance(garage.getCoordinates());
                //recebe a distancia entre essas duas posições 60X1852 metros
                totalKM = 60 * distance;
                //atualiza o total KM da viajem
                trucks[idx].setTotalKilometersTravel(totalKM);
                //o camião recebe as coordenadas da loja
                trucks[idx].setCoordinates(garage.getCoordinates());
                //retorna verdadeira
                return true;
            }
        }//se for o contrário, retorna falso
        return false;
    }

    public Trucks moveTruckToGarage(int numberTruck, Garages garage) {
        int idx;
        Trucks truckRemove;
        //se tiver camião
        if (isEmptyTruck() != true) {
            //obter a posição do camião
            idx = indexOfTruckByNumber(numberTruck);
            //se for diferente de - 1
            if (idx != -1) {
                //se já recebeu as coordenadas
                if (getGarageCoordiantes(trucks[idx].getNumber(), garage) == true) {
                    //verifica se as coordenadas são iguais
                    if (trucks[idx].moveTruck(garage.getCoordinates()) == true) {
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

    /* Metódo para validar o contentor na loja */
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

    /* Metódo para obter a posição do contentor na loja */
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

    public String showStoreByNameAndCoordinates() {
        String result = "\n\t--- LOJA [" + name + "] ---";
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
        String result = "\n--- LOJA [" + name + "] ---";
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

}
