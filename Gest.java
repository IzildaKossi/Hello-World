
/**
 * Escreva a descrição da classe Gest aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Gest
{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    public static final int MAX_STATIONS = 10;//10
    public static final int MAX_STORES = 10;//10
    public static final int MAX_GARAGES = 10;//10
    private int quantityStations;
    private DistributionStations[] stations;
    private int quantityStores;
    private Stores[] stores;
    private int quantityGarages;
    private Garages[] garages;

    public Gest() {
        this.quantityStations = 0;
        this.stations = new DistributionStations[Gest.MAX_STATIONS];
        this.quantityStores = 0;
        this.stores = new Stores[Gest.MAX_STORES];
        this.quantityGarages = 0;
        this.garages = new Garages[Gest.MAX_GARAGES];
    }

    public boolean isNameValid(String nome) {
        return !(nome.isEmpty() || nome.length() == 0);
    }

    private int indexOfStations(String name) {
        for (int i = 0; i < quantityStations; i++) {
            if (stations[i].getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    public boolean isValidStations(String name) {
        int idx;
        //se tiver estação de distribuição
        if (quantityStations > 0) {
            //obter a posição da estação de distribuição
            idx = indexOfStations(name);
            //se for diferente de - 1
            if (idx != -1) {
                //retorna verdadeira
                return true;
            }
        }//se for o contrário, retorna falso
        return false;
    }

    public boolean createNewStation(String name, Coordinates coordinates) {
        if ((isNameValid(name) == true)
                && (isValidStations(name) != true)
                && (indexOfStations(name) == -1)) {
            stations[quantityStations] = new DistributionStations(name, coordinates);
            quantityStations++;
            return true;
        }
        return false;
    }

    public DistributionStations getStationByName(String name) {
        int idx;
        if (quantityStations > 0) {
            idx = indexOfStations(name);
            if (idx != -1) {
                return stations[idx];
            }
        }
        return null;
    }


    /*-----------------------------------------------------------**/
    private int indexOfStores(String name) {
        for (int i = 0; i < quantityStores; i++) {
            if (stores[i].getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    public boolean isValidStores(String name) {
        int idx;
        if (quantityStores > 0) {
            idx = indexOfStores(name);
            if (idx != -1) {
                return true;
            }
        }
        return false;
    }

    public boolean createNewStore(String name, Coordinates coordinates) {
        if ((isNameValid(name) == true)
                && (isValidStores(name) != true)
                && (indexOfStores(name) == -1)) {
            stores[quantityStores] = new Stores(name, coordinates);
            quantityStores++;
            return true;
        }
        return false;
    }

    public Stores getStoreByName(String name) {
        int idx;
        if (quantityStores > 0) {
            idx = indexOfStores(name);
            if (idx != -1) {
                return stores[idx];
            }
        }
        return null;
    }

    public String showStoreByNameAndCoordinates(int i) {
        if (quantityStores > 0) {
            //retorna a loja por nome
            return stores[i].showStoreByNameAndCoordinates();
        }
        return null;
    }

    /*-----------------------------------------------------------**/
    private int indexOfGarages(String name) {
        for (int i = 0; i < quantityGarages; i++) {
            if (garages[i].getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    public boolean isValidGarages(String name) {
        int idx;
        if (quantityGarages > 0) {
            idx = indexOfGarages(name);
            if (idx != -1) {
                return true;
            }
        }
        return false;
    }

    public boolean createNewGarages(String name, Coordinates coordinates) {
        if ((isNameValid(name) == true)
                && (isValidGarages(name) != true)
                && (indexOfGarages(name) == -1)) {
            garages[quantityGarages] = new Garages(name, coordinates);
            quantityGarages++;
            return true;
        }
        return false;
    }

    public Garages getGarageByName(String name) {
        int idx;
        if (quantityGarages > 0) {
            idx = indexOfGarages(name);
            if (idx != -1) {
                return garages[idx];
            }
        }
        return null;
    }
    
      public String showGarageByNameAndCoordinates(int i) {
        if (quantityGarages > 0) {
            //retorna a garagem por nome e coordenadas
            return garages[i].showGarageByNameAndCoordinates();
        }
        return null;
    }

    public int getQuantityStations() {
        return quantityStations;
    }

    public void setQuantityStations(int quantityStations) {
        this.quantityStations = quantityStations;
    }

    public DistributionStations[] getStations() {
        return stations;
    }

    public void setStations(DistributionStations[] stations) {
        this.stations = stations;
    }

    public int getQuantityStores() {
        return quantityStores;
    }

    public void setQuantityStores(int quantityStores) {
        this.quantityStores = quantityStores;
    }

    public Stores[] getStores() {
        return stores;
    }

    public void setStores(Stores[] stores) {
        this.stores = stores;
    }

    public int getQuantityGarages() {
        return quantityGarages;
    }

    public void setQuantityGarages(int quantityGarages) {
        this.quantityGarages = quantityGarages;
    }

    public Garages[] getGarages() {
        return garages;
    }

    public void setGarages(Garages[] garages) {
        this.garages = garages;
    }

}
