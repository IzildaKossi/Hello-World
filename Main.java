import java.text.*;
import java.util.*;
/**
 * Escreva a descrição da classe Main aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Main
{
    private Gest gt;
    private Scanner sc;
    private DecimalFormat df;

    public Main() {
        this.gt = new Gest();
        this.sc = new Scanner(System.in);
        this.df = new DecimalFormat("#.##");
    }

    public static void main(String[] args) {
        limparTela();
        Main main = new Main();
        main.interfaceUtilizador();
    }

   
   public void interfaceUtilizador() {
        int opcao;
        char resposta = 'N';

        do {
            System.out.println("");
            System.out.println("   -- MENU PRINCIPAL --");
            System.out.println("1. Estação de Distribuição");
            System.out.println("2. Lojas");
            System.out.println("3. Garagem");
            System.out.println("0. Sair");
            opcao = escolherOpcao(0, 3);
            switch (opcao) {
                case 1:
                    menuEstacao();
                    break;
                case 2:
                    menuLoja();
                    break;
                case 3:
                    menuGaragem();
                    break;
                default:
                    resposta = respostaSN("Confirma saida (S/N)?: ");
                    break;

            }
        } while (resposta == 'N');
    }

    public void menuEstacao() {
        int opcao;
        do {
            System.out.println("\n - MENU ESTAÇÃO -");
            System.out.println("1. Criar Nova Estação");
            System.out.println("2. Mostrar Dados da Estação");
            System.out.println("3. Produzir Pack para Armazem");
            System.out.println("4. Mostrar Stock do Armazém");
            System.out.println("5. Atualizar Stock no Armazém");
            System.out.println("6. Criar Camião");
            System.out.println("7. Carregar Camião");
            System.out.println("8. Mover Camião para Loja");
            System.out.println("0. Voltar");
            opcao = escolherOpcao(0, 8);
            switch (opcao) {
                case 1:
                    criarNovaEstacao();
                    break;
                case 2:
                    mostrarDadosDaEstacao();
                    break;
                case 3:
                    produzirPackNoArmazem();
                    break;
                case 4:
                    mostrarStockNoArmazem();
                    break;
                case 5:
                    atualizarStockNoArmazem();
                    break;
                case 6:
                    criarCamiao();
                    break;
                case 7:
                    carregarCamiao();
                    break;
                case 8:
                    estacaoMoverCamiaoParaLoja();
                    break;
                default:
                    break;
            }
        } while (opcao != 0);
    }

    public void criarNovaEstacao() {
        System.out.println("");
        System.out.println("\t+------------------------+");
        System.out.println("\t| 1 - Criar Nova Estação |");
        System.out.println("\t+------------------------+");

        String nameStation;
        float latitude;
        float longitude;

        sc.nextLine();//nova linha para evitar erro, a seguir

        do {
            System.out.print("\nNOME DA ESTAÇÃO: ");
            nameStation = sc.nextLine();
            //valida o nome
            if (gt.isNameValid(nameStation) == false) {
                System.out.println("\nNome não pode ser vazio.");
            }//valida a estação, se existe ou não
            if (gt.isValidStations(nameStation) == true) {
                System.out.println("\nJá existe Estação de Distribuição com nome " + nameStation + ".");
                System.out.println(gt.getStationByName(nameStation).toString());
            }
        } while ((gt.isNameValid(nameStation) == false) || (gt.isValidStations(nameStation) == true));

        do {
            System.out.print("LATITUDE : ");
            latitude = sc.nextFloat();
            //se a latitude for inválida
            if (Coordinates.isLatitudeValid(latitude) == false) {
                System.out.println("\nLatitude deve estar entre [-90.0º, 90.0º].\n");
            }
        } while (Coordinates.isLatitudeValid(latitude) == false);

        do {
            System.out.print("LONGITUDE: ");
            longitude = sc.nextFloat();
            //se a longitude for inválida
            if (Coordinates.isLongitudeValid(longitude) == false) {
                System.out.println("\nLongitude deve estar entre [-180.0º, 180.0º].\n");
            }
        } while (Coordinates.isLongitudeValid(longitude) == false);

        //insere no gestor de teste
        if (gt.getQuantityStations() > Gest.MAX_STATIONS) {
            //mostra a mensagem
            System.out.println("\nNão existe espaço para inserir esta Estação de Distribuição.");
        }//e se existir espaço
        else {
            if (gt.createNewStation(nameStation, new Coordinates(latitude, longitude)) == true) {
                System.out.println("\nEstação de Distribuição com nome " + nameStation + " foi inserida com sucesso.");
                //mostra a informação sobre a estação
                System.out.println(gt.getStationByName(nameStation).toString());
            } else {
                System.out.println("\nEstação de Distribuição com nome " + nameStation + " não foi inserida.");
            }
        }
    }

    public void mostrarDadosDaEstacao() {
        System.out.println("");
        System.out.println("\t+------------------------------+");
        System.out.println("\t| 2 - Mostrar Dados da Estação |");
        System.out.println("\t+------------------------------+");

        String nameStation;

        sc.nextLine();//nova linha para evitar erro, a seguir

        do {
            System.out.print("\nNOME DA ESTAÇÃO: ");
            nameStation = sc.nextLine();
            //valida o nome
            if (gt.isNameValid(nameStation) == false) {
                System.out.println("\nNome não pode ser vazio.");
            }
        } while ((gt.isNameValid(nameStation) == false));

        //verifica se existe alguma estação com nome
        if (gt.isValidStations(nameStation) == true) {
            //mostra a informação sobre a estação
            System.out.println(gt.getStationByName(nameStation).toString());
        } else {
            System.out.println("\nNão existe nenhuma Estação de Distribuição com nome " + nameStation + ".");
        }
    }

    public void produzirPackNoArmazem() {
        System.out.println("");
        System.out.println("\t+--------------------------------+");
        System.out.println("\t| 3 - Produzir Pack para Armazém |");
        System.out.println("\t+--------------------------------+");

        String nameStation;
        String namePack;
        int quantity;
        int weight;
        float volume;
        DistributionStations station;

        sc.nextLine();//nova linha para evitar erro, a seguir

        do {
            System.out.print("\nNOME DA ESTAÇÃO: ");
            nameStation = sc.nextLine();
            //valida o nome
            if (gt.isNameValid(nameStation) == false) {
                System.out.println("\nNome não pode ser vazio.");
            }
        } while ((gt.isNameValid(nameStation) == false));

        //verifica se existe alguma estação com nome
        if (gt.isValidStations(nameStation) == true) {
            //cria um objeto do tipo Estação de distribuição
            station = gt.getStationByName(nameStation);
            //mostra a inf 
            System.out.println(station.toString());

            do {
                System.out.print("\nNOME DO PACK: ");
                namePack = sc.nextLine();
                //valida o nome
                if (gt.isNameValid(namePack) == false) {
                    System.out.println("\nNome não pode ser vazio.");
                }//se existe pack com mesmo nome
                if (station.existPackByName(namePack) == true) {
                    System.out.println("\nJá existe Pack com nome " + namePack + ".");
                    System.out.println(station.getWareHouse().toString());
                }
            } while ((gt.isNameValid(namePack) == false)
                    || (station.existPackByName(namePack) == true));

            do {
                System.out.print("QUANTIDADE: ");
                quantity = sc.nextInt();
                //quantidade maior que zero
                if (quantity < 0 || quantity > 1000) {
                    System.out.println("\nQuantidade tem de estar entre [1, 1000].\n");
                }
            } while (quantity < 0 || quantity > 1000);

            do {
                System.out.print("PESO: ");
                weight = sc.nextInt();
                //peso maior que zero
                if (weight < 0 || weight > 100) {
                    System.out.println("\nPeso tem de estar entre [1, 100].\n");
                }
            } while (weight < 0 || weight > 100);

            do {
                System.out.print("VOLUME: ");
                volume = sc.nextFloat();
                //volume maior que zero
                if (volume < 0 || volume > 1000) {
                    System.out.println("\nVolume tem de estar entre [1, 1000].\n");
                }
            } while (volume < 0 || volume > 1000);

            //se não existir espaço no armazem
            if (station.getWareHouse().getQuantPacks() > DistributionStations.MAX_PACKS) {
                //mostra a mensagem
                System.out.println("\nNão existe espaço para inserir o Pack no Armazém da Estação " + station.getName() + ".");
            }// e se existir 
            else {
                //cria o pack e insira no armazem
                if (station.createPacks(namePack, quantity, weight, volume) == true) {
                    //mostra a informação
                    System.out.println("\nPack com nome " + namePack + " foi inserido no Armazém da Estação " + station.getName() + ".");
                    //mostra a informação sobre a estação
                    System.out.println(station.toString());
                } else {
                    System.out.println("\nPack não foi criado.");
                }
            }
        } else {
            System.out.println("\nNão existe nenhuma Estação de Distribuição com nome " + nameStation + ".");
        }
    }

    public void mostrarStockNoArmazem() {
        System.out.println("");
        System.out.println("\t+------------------------------+");
        System.out.println("\t| 4 - Mostrar Stock do Armazém |");
        System.out.println("\t+------------------------------+");

        String nameStation;

        sc.nextLine();//nova linha para evitar erro, a seguir

        do {
            System.out.print("\nNOME DA ESTAÇÃO: ");
            nameStation = sc.nextLine();
            //valida o nome
            if (gt.isNameValid(nameStation) == false) {
                System.out.println("\nNome não pode ser vazio.");
            }
        } while ((gt.isNameValid(nameStation) == false));

        //verifica se existe alguma estação com nome
        if (gt.isValidStations(nameStation) == true) {
            //mostra a informação do armazem
            System.out.println(gt.getStationByName(nameStation).getWareHouse().toString());
        } else {
            System.out.println("\nNão existe nenhuma Estação de Distribuição com nome " + nameStation + ".");
        }
    }

    public void atualizarStockNoArmazem() {
        System.out.println("");
        System.out.println("\t+--------------------------------+");
        System.out.println("\t| 5 - Atualizar Stock no Armazém |");
        System.out.println("\t+--------------------------------+");

        String nameStation;
        int numberPack;
        int quantity;
        DistributionStations station;

        sc.nextLine();//nova linha para evitar erro, a seguir

        do {
            System.out.print("\nNOME DA ESTAÇÃO: ");
            nameStation = sc.nextLine();
            //valida o nome
            if (gt.isNameValid(nameStation) == false) {
                System.out.println("\nNome não pode ser vazio.");
            }
        } while ((gt.isNameValid(nameStation) == false));

        //verifica se existe alguma estação com nome
        if (gt.isValidStations(nameStation) == true) {
            //cria um objeto do tipo Estação de distribuição
            station = gt.getStationByName(nameStation);
            //mostra a informação sobre a estação
            System.out.println(station.getWareHouse().toString());

            System.out.print("\nPACK ID: ");
            numberPack = sc.nextInt();
            //se não existir
            if (station.getWareHouse().existPack(numberPack) == false) {
                //mostra a mensagem
                System.out.println("\nNão existe nenhum Pack com ID " + numberPack + ".");
            }//e se existir
            else {
                do {
                    System.out.print("QUANTIDADE: ");
                    quantity = sc.nextInt();
                    //quantidade maior que zero
                    if (quantity < 0 || quantity > 1000) {
                        System.out.println("\nQuantidade tem de estar entre [1, 1000].\n");
                    }
                } while (quantity < 0 || quantity > 1000);

                //tenta atualizar o stock
                if (station.getWareHouse().updateStock(numberPack, quantity) == true) {
                    //mostra a informação
                    System.out.println("\nQuantidade de Pack com ID " + String.format("%03d", numberPack) + " foi atualizado com sucesso.");
                    //mostra a informação sobre a estação
                    System.out.println(station.toString());
                } else {
                    System.out.println("\nQuantide de Pack com ID " + String.format("%03d", numberPack) + " não foi atualizado.");
                }
            }
        } else {
            System.out.println("\nNão existe nenhuma Estação de Distribuição com nome " + nameStation + ".");
        }
    }

    public void criarCamiao() {
        System.out.println("");
        System.out.println("\t+---------------------------------------------+");
        System.out.println("\t| 6 - Criar Camião na Estação de Distribuição |");
        System.out.println("\t+---------------------------------------------+");

        String nameStation;
        String truckName;
        DistributionStations station;

        sc.nextLine();//nova linha para evitar erro, a seguir

        do {
            System.out.print("\nNOME DA ESTAÇÃO: ");
            nameStation = sc.nextLine();
            //valida o nome
            if (gt.isNameValid(nameStation) == false) {
                System.out.println("\nNome não pode ser vazio.");
            }
        } while ((gt.isNameValid(nameStation) == false));

        //verifica se existe alguma estação com nome
        if (gt.isValidStations(nameStation) == true) {
            //cria um objeto do tipo Estação de distribuição
            station = gt.getStationByName(nameStation);
            //mostra a informação sobre a estação
            System.out.println(station.toString());

            do {
                System.out.print("\nNOME DO CAMIÃO: ");
                truckName = sc.nextLine();
                //valida o nome
                if (gt.isNameValid(truckName) == false) {
                    System.out.println("\nNome não pode ser vazio.");
                }
            } while ((gt.isNameValid(truckName) == false));

            //se ultrapassar o limite maximo
            if (station.getQuantityTrucks() > DistributionStations.MAX_TRUCKS) {
                //mostra a mensagem
                System.out.println("\nNão é possível criar mais Camião na Estação " + station.getName() + ".");
            }// e se existir 
            else {
                if (station.createNewTruck(truckName) == true) {
                    //mostra a mensagem
                    System.out.println("\nFoi criado um Camião na Estação " + station.getName() + ".");
                    //mostra a informação sobre a estação
                    System.out.println(station.toString());
                } else {
                    System.out.println("\nFoi criado nenhum Camião na Estação " + station.getName() + ".");
                }
            }
        } else {
            System.out.println("\nNão existe nenhuma Estação de Distribuição com nome " + nameStation + ".");
        }
    }

    public void carregarCamiao() {
        System.out.println("");
        System.out.println("\t+---------------------------------+");
        System.out.println("\t| 7 - Colocar Contentor no Camião |");
        System.out.println("\t+---------------------------------+");

        String nameStation;
        int numberTruck;
        DistributionStations station;
        Trucks truck;
        Containers container;
        int numberPack;
        int quantity;
        Packs pack;
        Packs packInContainer;
        char resposta;

        sc.nextLine();//nova linha para evitar erro, a seguir

        do {
            System.out.print("\nNOME DA ESTAÇÃO: ");
            nameStation = sc.nextLine();
            //valida o nome
            if (gt.isNameValid(nameStation) == false) {
                System.out.println("\nNome não pode ser vazio.");
            }
        } while ((gt.isNameValid(nameStation) == false));

        //verifica se existe alguma estação com nome
        if (gt.isValidStations(nameStation) == true) {
            //cria um objeto do tipo Estação de distribuição
            station = gt.getStationByName(nameStation);
            //mostra a informação sobre a estação
            System.out.println(station.toString());

            System.out.print("\nCAMIÃO ID: ");
            numberTruck = sc.nextInt();

            //se não existir
            if (station.existTruck(numberTruck) == false) {
                //mostra a mensagem
                System.out.println("\nNão existe nenhum Camião com ID " + numberTruck + " na Estação " + station.getName() + ".");
            } //e se existir camião
            else {
                //verifica a quantidade
                if (station.getQuantityContainer() > DistributionStations.MAX_CONTAINERS) {
                    //mostra a mensagem
                    System.out.println("\nNão é possível criar mais contentor na Estação " + station.getName() + ".");
                } else {
                    //obter o camiao
                    truck = gt.getStationByName(nameStation).getTruck(numberTruck);
                    //se o camião estiver vazio
                    if (truck.isFull() != true) {
                        //se não houver stock no armazem
                        if (station.getWareHouse().getQuantPacks() <= 0) {
                            //mostra a mensagem
                            System.out.println("\nNão existe Packs disponível no Armazém.");
                        }//e se houver
                        else {
                            //verifca se ultrapassa o limite
                            if (station.isFullContainer() != true) {
                                //obter a resposta sim ou não
                                resposta = respostaSN("\nCriar Novo Contentor (S/N)?: ");
                                switch (resposta) {
                                    case 'S':
                                    case 's':
                                        //vai ser criado um novo contentor
                                        container = new Containers();
                                        //mostra a mensagem
                                        System.out.println("\nFoi criado um novo contentor vazio com ID "
                                                + container.getNumber() + " na Estação " + station.getName() + ".");
                                        //mostra a informação sobre a estação
                                        System.out.println(station.toString());
                                        //se não for adicionado na estação
                                        if (station.createNewContainer(container) != true) {
                                            //mostra a mensagem
                                            System.out.println("\nNão foi criado nenhum contentor na Estação " + station.getName() + ".");
                                        }//e se for adicionado
                                        else {
                                            System.out.print("\nPACK ID: ");
                                            numberPack = sc.nextInt();
                                            //se não existir
                                            if (station.getWareHouse().existPack(numberPack) == false) {
                                                //mostra a mensagem
                                                System.out.println("\nNão existe nenhum Pack com ID " + numberPack + ".");
                                            } //e se existir
                                            else {
                                                //obter o pack e verificar a quantidade
                                                pack = station.getWareHouse().getPackByID(numberPack);
                                                //se não for nul
                                                if(pack.getQuantity() == 0){
                                                    System.out.println("\nNão existe stock disponivel para requisitar " + pack.getName()
                                                            + " do Armazém da Estação " + station.getName());
                                                }//se tiver quantidade 
                                                else {
                                                    do {
                                                        System.out.print("QUANTIDADE: ");
                                                        quantity = sc.nextInt();
                                                        //quantidade maior que zero
                                                        if (quantity < 0 || quantity > 1000) {
                                                            System.out.println("\nQuantidade tem de estar entre [1, 1000].\n");
                                                        }
                                                    } while ((quantity < 0) || (quantity > 1000) || (pack.getQuantity()) == 0);

                                                    //se quantity requiaitada for maior que quantidade de pack disponivel no armazem
                                                    if (quantity > pack.getQuantity()) {
                                                        System.out.println("\nQuantidade requisitada ultrapassa a quantidade de " + pack.getName()
                                                                + " disponivel no Armazém da Estação " + station.getName());
                                                    }//se não for
                                                    else {
                                                        //obter o pack 
                                                        packInContainer = station.putPackInContainter(container.getNumber(), pack.getNumber(), quantity);
                                                        //se for nula
                                                        if (packInContainer == null) {
                                                            //mostra a mensagem
                                                            System.out.println("\nNa Estação " + station.getName() + ", o Pack não foi adicionado ao Contentor com ID "
                                                                    + container.getNumber());
                                                        }//se não for nula
                                                        else {
                                                                  //se a quantidade requisitada for igual a quantidade de pack no stock
                                                             if(packInContainer.getQuantity() == quantity){
                                                                  //mostra a mensagem
                                                                   System.out.println("\nO Pack com ID "+packInContainer.getName()
                                                                    + " esgotou e não tem stock disponivel no Armazém da Estação " + station.getName());
                                                             } else{
                                                            //mostra a mensagem
                                                            System.out.println("\nNa Estação " + station.getName() + ", foi requisitado "
                                                                    + packInContainer.getQuantity() + " quantidades de " + packInContainer.getName()
                                                                    + " do Armazém e adicionado ao Contentor com ID " + container.getNumber() + ".");
                                                            //mostra a informação sobre a estação
                                                            System.out.println(station.toString());

                                                            //obter a resposta sim ou não
                                                            resposta = respostaSN("\nDeseja Carregar o Contentor no Camião (S/N)?: ");
                                                            switch (resposta) {
                                                                case 'S':
                                                                case 's':
                                                                    //adiciona o contentor ao camião e elimina da estação
                                                                    if (station.putContainerInTruck(truck.getNumber(), container) == true) {
                                                                        //mostra a mensagem
                                                                        System.out.println("\nContentor com ID " + container.getNumber()
                                                                                + " foi adicionado ao Camião " + truck.getName().concat(String.valueOf(truck.getNumber()))
                                                                                + " na Estação " + station.getName() + ".");
                                                                        //mostra as informações da estação
                                                                        System.out.println(station.toString());
                                                                    }//e senão for adicionado ao camião
                                                                    else {
                                                                        System.out.println("\nContentor com ID " + container.getNumber()
                                                                                + " não foi adicionado ao Camião " + truck.getName().concat(String.valueOf(truck.getNumber()))
                                                                                + " na Estação " + station.getName() + ".");
                                                                    }
                                                                    break;
                                                                }
                                                            }

                                                        }
                                                    }

                                                }

                                            }
                                        }

                                        break;
                                }

                            } //e senão for criado o contentor
                            else {
                                //mostra a mensagem
                                System.out.println("\nNão é possivel adicionar mais contentor na Estação " + station.getName() + ".");
                            }

                        }
                    }//e se o camião estiver cheio 
                    else {
                        //mostra a mensagem
                        System.out.println("\nNa Estação " + station.getName() + ", o Camião " + truck.getName().concat(String.valueOf(truck.getNumber()))
                                + " já tem o contentor e está a espera das coordenadas da Loja.");
                    }
                }
            }
        } else {
            System.out.println("\nNão existe nenhuma Estação de Distribuição com nome " + nameStation + ".");
        }
    }

    public void estacaoMoverCamiaoParaLoja() {
        System.out.println("");
        System.out.println("\t+----------------------------+");
        System.out.println("\t| 8 - Mover Camião para Loja |");
        System.out.println("\t+----------------------------+");

        String nameStation;
        int numberTruck;
        String nameStore;
        DistributionStations station;
        Stores store;
        Trucks truck;
        Trucks truckRemove;
        double distance;

        sc.nextLine();//nova linha para evitar erro, a seguir

        do {
            System.out.print("\nNOME DA ESTAÇÃO: ");
            nameStation = sc.nextLine();
            //valida o nome
            if (gt.isNameValid(nameStation) == false) {
                System.out.println("\nNome não pode ser vazio.");
            }
        } while ((gt.isNameValid(nameStation) == false));

        //verifica se existe alguma estação com nome
        if (gt.isValidStations(nameStation) == true) {
            //cria um objeto do tipo Estação de distribuição
            station = gt.getStationByName(nameStation);
            //mostra a informação sobre a estação
            System.out.println(station.toString());

            System.out.print("\nCAMIÃO ID: ");
            numberTruck = sc.nextInt();

            //se não existir
            if (station.existTruck(numberTruck) == false) {
                //mostra a mensagem
                System.out.println("\nNão existe nenhum Camião com ID " + numberTruck + " na Estação " + station.getName() + ".");
            } //e se existir camião
            else {
                //obter o camiao
                truck = station.getTruck(numberTruck);
                //e tem de estar carregado com contentor que contém pack
                if (truck.isFull() == true && truck.getContainers().isEmpty() != true) {
                    //mostra a mensagem
                    System.out.println("\nNa Estação " + station.getName() + ", o Camião " + truck.getName().concat(String.valueOf(truck.getNumber()))
                            + " já tem o contentor e está a espera das coordenadas da Loja.");

                    //tem que haver lojas criadas
                    if (gt.getQuantityStores() > 0) {
                        //mostra a mensagem
                        System.out.println("");
                        System.out.println("\t+------------------+");
                        System.out.println("\t| LOJAS EXISTENTES |");
                        System.out.println("\t+------------------+");
                        //faz o loop
                        for (int i = 0; i < gt.getQuantityStores(); i++) {
                            //se for diferente de nulo
                            if (gt.showStoreByNameAndCoordinates(i) != null) {
                                //mostrar
                                System.out.println(gt.showStoreByNameAndCoordinates(i));
                            }
                        }
                        System.out.println("\n\t+------------------+");

                        sc.nextLine();//nova linha para evitar erro, a seguir

                        do {
                            System.out.print("\nNOME DA LOJA: ");
                            nameStore = sc.nextLine();
                            //valida o nome
                            if (gt.isNameValid(nameStore) == false) {
                                System.out.println("\nNome não pode ser vazio.");
                            }
                        } while ((gt.isNameValid(nameStore) == false));

                        //se a loja existir
                        if (gt.isValidStores(nameStore) == true) {
                            //criar um objeto do tipo loja
                            store = gt.getStoreByName(nameStore);
                            //mostra a mensagem
                            System.out.println("\nMostrar Dados da Lojas com as coordenadas");
                            //mostra os dados das lojas pelo nome e coordenadas
                            System.out.println(store.showStoreByNameAndCoordinates());
                            //receber as coordenadas
                            if (station.getStoreCoordiantes(truck.getNumber(), store) == true) {
                                //obter a distancia
                                distance = station.getCoordinates().getDistance(store.getCoordinates());
                                //mostra a mensagem
                                System.out.println("\nCamião " + truck.getName().concat(String.valueOf(truck.getNumber()))
                                        + " irá percorrer uma distância de " + df.format(distance)
                                        + " metros da Estação " + station.getName() + " até a Loja " + store.getName() + ".");
                                //tenta mover o camião
                                truckRemove = station.moveTruckToStore(truck.getNumber(), store);
                                //se o camião não existir na estação
                                if (station.existTruck(truckRemove.getNumber()) == false) {
                                    //adicionar o camião na loja
                                    store.addTruck(truckRemove);
                                    //mostra a mensagem
                                    System.out.println("\nCamião " + truckRemove.getName().concat(String.valueOf(truckRemove.getNumber()))
                                            + " foi movido da Estação " + station.getName() + " para Loja " + store.getName()
                                            + " viajando " + df.format(station.convertKM(truck.getTotalKilometersTravel())) + "Km.");
                                    //mostra os dados da estação sem camião e contentor
                                    System.out.println(station.toString());
                                }//e se ainda existir na estação
                                else {
                                    //mostra a mensagem 
                                    System.out.println("\nCamião " + truckRemove.getName().concat(String.valueOf(truckRemove.getNumber()))
                                            + " não foi movido da Estação " + station.getName() + " para Loja " + store.getName());
                                }
                            }
                        } else {
                            System.out.println("\nNão existe nenhuma Loja com nome " + nameStore + ".");
                        }
                    } else {
                        System.out.println("\nNão existe nenhuma Loja Criada.");
                    }
                }
            }
            //e se existir estação
        } else {
            System.out.println("\nNão existe nenhuma Estação de Distribuição com nome " + nameStation + ".");
        }
    }

    public void menuLoja() {
        int opcao;
        do {
            System.out.println("\n - MENU LOJA -");
            System.out.println("1. Criar Nova Loja");
            System.out.println("2. Mostrar Dados da Loja");
            System.out.println("3. Receber Contentor do Camião");
            System.out.println("4. Mostrar Stock no Armazém");
            System.out.println("5. Consumir/Vender Pack do Armazém");
            System.out.println("6. Mover Camião para Armazém");
            System.out.println("0. Voltar");
            opcao = escolherOpcao(0, 6);
            switch (opcao) {
                case 1:
                    criarNovaLoja();
                    break;
                case 2:
                    lojaMostrarDados();
                    break;
                case 3:
                    lojaReceberContentorCamiao();
                    break;
                case 4:
                    lojaVerStockNoArmazem();
                    break;
                case 5:
                    lojaConsumirPackNoArmazem();
                    break;
                case 6:
                    lojaMoverCamiaoParaGaragem();
                    break;
                default:
                    break;
            }
        } while (opcao != 0);
    }

    public void criarNovaLoja() {
        System.out.println("");
        System.out.println("\t+---------------------+");
        System.out.println("\t| 1 - Criar Nova Loja |");
        System.out.println("\t+---------------------+");

        String nameStore;
        float latitude;
        float longitude;

        sc.nextLine();//nova linha para evitar erro, a seguir

        do {
            System.out.print("\nNOME DA LOJA: ");
            nameStore = sc.nextLine();
            //valida o nome
            if (gt.isNameValid(nameStore) == false) {
                System.out.println("\nNome não pode ser vazio.");
            }//valida a loja, se existe ou não
            if (gt.isValidStores(nameStore) == true) {
                System.out.println("\nJá existe Loja com nome " + nameStore + ".");
                System.out.println(gt.getStoreByName(nameStore).toString());
            }
        } while ((gt.isNameValid(nameStore) == false) || (gt.isValidStores(nameStore) == true));

        do {
            System.out.print("LATITUDE : ");
            latitude = sc.nextFloat();
            //se a latitude for inválida
            if (Coordinates.isLatitudeValid(latitude) == false) {
                System.out.println("\nLatitude deve estar entre [-90.0º, 90.0º].\n");
            }
        } while (Coordinates.isLatitudeValid(latitude) == false);

        do {
            System.out.print("LONGITUDE: ");
            longitude = sc.nextFloat();
            //se a longitude for inválida
            if (Coordinates.isLongitudeValid(longitude) == false) {
                System.out.println("\nLongitude deve estar entre [-180.0º, 180.0º].\n");
            }
        } while (Coordinates.isLongitudeValid(longitude) == false);

        //insere no gestor de teste
        if (gt.getQuantityStores() > Gest.MAX_STORES) {
            System.out.println("\nNão é possivel criar mais lojas.");
        } else {
            if (gt.createNewStore(nameStore, new Coordinates(latitude, longitude)) == true) {
                System.out.println("\nLoja com nome " + nameStore + " foi inserida com sucesso.");
                //mostra a informação sobre a loja
                System.out.println(gt.getStoreByName(nameStore).toString());
            } else {
                System.out.println("\nLoja com nome " + nameStore + " não foi inserida.");
            }
        }
    }

    public void lojaMostrarDados() {
        System.out.println("");
        System.out.println("\t+---------------------------+");
        System.out.println("\t| 2 - Mostrar Dados da Loja |");
        System.out.println("\t+---------------------------+");

        String nameStore;

        sc.nextLine();//nova linha para evitar erro, a seguir

        do {
            System.out.print("\nNOME DA LOJA: ");
            nameStore = sc.nextLine();
            //valida o nome
            if (gt.isNameValid(nameStore) == false) {
                System.out.println("\nNome não pode ser vazio.");
            }
        } while ((gt.isNameValid(nameStore) == false));

        //verifica se existe alguma loja com nome
        if (gt.isValidStores(nameStore) == true) {
            //mostra a informação sobre a loja
            System.out.println(gt.getStoreByName(nameStore).toString());
        } else {
            System.out.println("\nNão existe nenhuma Loja com nome " + nameStore + ".");
        }
    }

    public void lojaReceberContentorCamiao() {
        System.out.println("");
        System.out.println("\t+---------------------------------+");
        System.out.println("\t| 3 - Receber Contentor do Camião |");
        System.out.println("\t+---------------------------------+");

        String nameStore;
        Stores store;
        int numberTruck;
        Trucks truck;
        Containers container;
        Packs pack;
        int total;

        sc.nextLine();//nova linha para evitar erro, a seguir

        do {
            System.out.print("\nNOME DA LOJA: ");
            nameStore = sc.nextLine();
            //valida o nome
            if (gt.isNameValid(nameStore) == false) {
                System.out.println("\nNome não pode ser vazio.");
            }
        } while ((gt.isNameValid(nameStore) == false));

        //verifica se existe alguma loja com nome
        if (gt.isValidStores(nameStore) == true) {
            //cria um objeto do tipo loja
            store = gt.getStoreByName(nameStore);
            //mostra a informação sobre a loja
            System.out.println(store.toString());

            System.out.print("\nCAMIÃO ID: ");
            numberTruck = sc.nextInt();

            //se não existir
            if (store.existTruck(numberTruck) == false) {
                //mostra a mensagem
                System.out.println("\nNão existe nenhum Camião com ID " + numberTruck + " na Loja " + store.getName() + ".");
            } //e se existir camião
            else {
                //obter o camião
                truck = store.getTruck(numberTruck);
                //obter o contentor do camião
                container = truck.getContainers();
                //obter o contentor do camião
                if (store.getContainerFromTruck(truck.getNumber()) == true) {
                    //mostra a mensagem 
                    System.out.println("\nNa Loja " + store.getName() + " foi descarregado o Contentor com ID "
                            + container.getNumber() + " do Camião " + truck.getName().concat(String.valueOf(truck.getNumber())));
                    //mostra a informação sobre a loja
                    System.out.println(store.toString());

                    //se contentor não estiver vazio e tiver pack
                    if (container.isEmpty() != true) {
                        //obter a quantidade total de pack
                        total = container.getQuantityPacks();
                        //faz o loop
                        for (int i = 0, j = 0; i < total; i++) {
                            //se houver pack no contentor
                            if (container.getPacks()[0] != null) {
                                //retira o primeiro pack no contentor e adiciona no armazém
                                pack = store.getPackFromContainter(container.getNumber(), container.getPacks()[0].getNumber());
                                //se for diferente de nulo
                                if (pack != null) {
                                    //mostra a mensagem
                                    System.out.println("\nFoi retirado " + pack.getQuantity()
                                            + " quantidades de " + pack.getName() + " do Contentor com ID "
                                            + container.getNumber() + " e adicionado ao stock no Armazém da Loja "
                                            + store.getName() + ".");

                                    //incrementa j
                                    j++;
                                    //se j for igual a quantidade total de pack
                                    if (j == total) {
                                        //mostra a informação sobre a estação
                                        System.out.println(store.toString());
                                    }
                                } //se for diferente de nulo
                                else {
                                    System.out.println("\nPack não foi retirado do Contentor com ID " + container.getNumber() + ".");
                                }
                            }
                        }
                        //se contentor  estiver vazio e tiver pack
                        if (container.isEmpty() == true) {
                            //tenta adicionar o contentor vazio ano camião
                            if (store.putEmptyContainerInTruck(truck.getNumber(), container.getNumber()) == true) {
                                System.out.println("\nNa Loja " + store.getName() + ", o Contentor com ID " + container.getNumber()
                                        + " está vazio e foi adicionado ao Camião " + truck.getName().concat(String.valueOf(truck.getNumber())));
                                //mostra a informação sobre a estação
                                System.out.println(store.toString());
                            } else {
                                System.out.println("\nNa Loja " + store.getName() + ", o Contentor com ID " + container.getNumber()
                                        + " está vazio e não foi adicionado ao Camião " + truck.getName().concat(String.valueOf(truck.getNumber())));

                            }
                        }
                    }
                }
            }
            //falta escolher o camiao da loja e enviar
        } else {
            System.out.println("\nNão existe nenhuma Loja com nome " + nameStore + ".");
        }
    }

    public void lojaVerStockNoArmazem() {
        System.out.println("");
        System.out.println("\t+--------------------------+");
        System.out.println("\t| 4 - Ver Stock do Armazém |");
        System.out.println("\t+--------------------------+");

        String nameStore;

        sc.nextLine();//nova linha para evitar erro, a seguir

        do {
            System.out.print("\nNOME DA LOJA: ");
            nameStore = sc.nextLine();
            //valida o nome
            if (gt.isNameValid(nameStore) == false) {
                System.out.println("\nNome não pode ser vazio.");
            }
        } while ((gt.isNameValid(nameStore) == false));

        //verifica se existe alguma loja com nome
        if (gt.isValidStores(nameStore) == true) {
            //mostra a informação sobre a loja
            System.out.println(gt.getStoreByName(nameStore).getWareHouse().toString());
        } else {
            System.out.println("\nNão existe nenhuma Loja com nome " + nameStore + ".");
        }
    }

    public void lojaMoverCamiaoParaGaragem() {
        System.out.println("");
        System.out.println("\t+-------------------------------+");
        System.out.println("\t| 5 - Mover Camião para Garagem |");
        System.out.println("\t+-------------------------------+");

        int numberTruck;
        String nameStore;
        Stores store;
        String nameGarage;
        Garages garage;
        Trucks truck;
        Trucks truckRemove;
        double distance;

        sc.nextLine();//nova linha para evitar erro, a seguir

        do {
            System.out.print("\nNOME DA LOJA: ");
            nameStore = sc.nextLine();
            //valida o nome
            if (gt.isNameValid(nameStore) == false) {
                System.out.println("\nNome não pode ser vazio.");
            }
        } while ((gt.isNameValid(nameStore) == false));

        //verifica se existe alguma estação com nome
        if (gt.isValidStores(nameStore) == true) {
            //obter a estação pelo nome
            store = gt.getStoreByName(nameStore);
            //mostra a informação sobre a estação
            System.out.println(store.toString());

            System.out.print("\nCAMIÃO ID: ");
            numberTruck = sc.nextInt();

            //se não existir
            if (store.existTruck(numberTruck) == false) {
                //mostra a mensagem
                System.out.println("\nNão existe nenhum Camião com ID "
                        + numberTruck + " na Loja " + store.getName() + ".");
            } //e se existir camião
            else {
                //obter o camiao
                truck = store.getTruck(numberTruck);
                //e tem de estar carregado com contentor estiver vazia
                if (truck.isFull() == true && truck.getContainers().isEmpty() == true) {
                    //mostra a mensage m
                    System.out.println("\nNa Loja " + store.getName() + ", o Camião " + truck.getName().concat(String.valueOf(truck.getNumber()))
                            + " já tem o contentor vazio e está a espera das coordenadas da Garagem.");

                    //tem que haver garagem
                    if (gt.getQuantityGarages() > 0) {
                        //mostra a mensagem
                        System.out.println("");
                        System.out.println("\t+---------------------+");
                        System.out.println("\t| GARAGENS EXISTENTES |");
                        System.out.println("\t+---------------------+");
                        //faz o loop
                        for (int i = 0; i < gt.getQuantityGarages(); i++) {
                            //se for diferente de nulo
                            if (gt.showGarageByNameAndCoordinates(i) != null) {
                                //mostrar o nome e as coordenadas da garagem
                                System.out.println(gt.showGarageByNameAndCoordinates(i));
                            }
                        }
                        System.out.println("\n\t+------------------+");

                        sc.nextLine();//nova linha para evitar erro, a seguir

                        do {
                            System.out.print("\nNOME DA GARAGEM: ");
                            nameGarage = sc.nextLine();
                            //valida o nome
                            if (gt.isNameValid(nameGarage) == false) {
                                System.out.println("\nNome não pode ser vazio.");
                            }
                        } while ((gt.isNameValid(nameGarage) == false));

                        //se a garagem existir
                        if (gt.isValidGarages(nameGarage) == true) {
                            //criar um objeto do tipo garagem
                            garage = gt.getGarageByName(nameGarage);
                            //mostra a mensagem
                            System.out.println("\nMostrar Dados da Garagem com as coordenadas");
                            //mostra os dados das lojas pelo nome e coordenadas
                            System.out.println(garage.showGarageByNameAndCoordinates());
                            //receber as coordenadas
                            if (store.getGarageCoordiantes(truck.getNumber(), garage) == true) {
                                //obter a distancia
                                distance = store.getCoordinates().getDistance(garage.getCoordinates());
                                //mostra a mensagem
                                System.out.println("\nCamião " + truck.getName().concat(String.valueOf(truck.getNumber()))
                                        + " irá percorrer uma distância de " + df.format(distance) + " metros da Loja " + store.getName()
                                        + " até a Garagem " + garage.getName() + ".");
                                //tenta mover o camião
                                truckRemove = store.moveTruckToGarage(truck.getNumber(), garage);
                                //se o camião não existir na estação
                                if (store.existTruck(truckRemove.getNumber()) == false) {
                                    //adicionar o camião na loja
                                    garage.addTruck(truckRemove);
                                    //mostra a mensagem
                                    System.out.println("\nCamião " + truckRemove.getName().concat(String.valueOf(truckRemove.getNumber()))
                                            + " foi movido da Loja " + store.getName() + " para Garagem " + garage.getName()
                                            + " viajando " + df.format(truck.getTotalKilometersTravel()) + "Km.");
                                    //mostra os dados da loja sem camião e contentor
                                    System.out.println(store.toString());
                                }//e se ainda existir na estação
                                else {
                                    //mostra a mensagem 
                                    System.out.println("\nCamião " + truckRemove.getName().concat(String.valueOf(truckRemove.getNumber()))
                                            + " não foi movido da Loja " + store.getName() + " para Garagem " + garage.getName());
                                }
                            }
                        } else {
                            System.out.println("\nNão existe nenhuma Garagem com nome " + nameGarage + ".");
                        }

                    } else {
                        System.out.println("\nNão existe nenhuma Garagem Disponivel.");
                    }
                }
            }
            //e se existir estação
        } else {
            System.out.println("\nNão existe nenhuma Loja com nome " + nameStore + ".");
        }
    }

    private void lojaConsumirPackNoArmazem() {
        System.out.println("");
        System.out.println("\t+-------------------------------------+");
        System.out.println("\t| 6 - Consumir/Vender Pack do Armazém |");
        System.out.println("\t+-------------------------------------+");

        String nameStore;
        Stores store;
        int numberPack;
        Packs pack;
        int quantity;
        Packs consomePack;

        sc.nextLine();//nova linha para evitar erro, a seguir

        do {
            System.out.print("\nNOME DA LOJA: ");
            nameStore = sc.nextLine();
            //valida o nome
            if (gt.isNameValid(nameStore) == false) {
                System.out.println("\nNome não pode ser vazio.");
            }
        } while ((gt.isNameValid(nameStore) == false));

        //verifica se existe alguma loja com nome
        if (gt.isValidStores(nameStore) == true) {
            //obter a loja
            store = gt.getStoreByName(nameStore);
            //mostra a informação sobre a loja
            System.out.println(store.toString());

            //falta escolher o pack e vender
            System.out.print("\nPACK ID: ");
            numberPack = sc.nextInt();

            //se não existir
            if (store.getWareHouse().existPack(numberPack) == false) {
                //mostra a mensagem
                System.out.println("\nNão existe nenhum Pack com ID: " + numberPack + ".");
            }//e se existir
            else {
                //obter o pack 
                pack = store.getWareHouse().getPackByID(numberPack);
                //se tiver quantidade no stock
                if (pack.getQuantity() > 0) {

                    do {
                        System.out.print("QUANTIDADE: ");
                        quantity = sc.nextInt();
                        //quantidade maior que zero
                        if (quantity < 0 || quantity > 1000) {
                            System.out.println("\nQuantidade tem de estar entre [1, 1000].\n");
                        }
                    } while ((quantity < 0) || (quantity > 1000) || (pack.getQuantity()) == 0);

                    //se quantity requiaitada for maior que quantidade de pack disponivel no armazem
                    if (quantity > pack.getQuantity()) {
                        System.out.println("\nQuantidade requisitada ultrapassa a quantidade de " + pack.getName()
                                + " disponivel no Armazém da Loja" + store.getName());
                    } else {
                        //obter o pack a consumir
                        consomePack = store.consomePack(pack.getNumber(), quantity);
                        //tenta consumir ou vender pack disponivel no stock
                        if (consomePack != null) {
                            //se quantidade requisitada e quantidade do pack for igual
                             if(consomePack.getQuantity() == quantity){
                               //mostra a mensagem
                                 System.out.println("\nO Pack com ID "+consomePack.getName()
                                 + " esgotou e não tem stock disponivel no Armazém da Loja " + store.getName());
                            }else{
                            //mostra a informação
                            System.out.println("\nFoi consumido " + quantity + " de "
                                    + consomePack.getName() + " do Armazém da Loja " + store.getName() + ".");
                            //mostra a informação sobre a estação
                            System.out.println(store.toString());
                        }
                        }//não for consumida
                        else {
                            System.out.println("\n" + pack.getName() + " não foi consumido do Armazém da Loja " + store.getName() + ".");
                        }
                    }
                }//não existe stock para consumir 
                else {
                    System.out.println("\nNão existe stock de " + pack.getName() + " no Armazém da Loja " + store.getName() + ".");
                }
            }
        } else {
            System.out.println("\nNão existe nenhuma Loja com nome " + nameStore + ".");
        }
    }

    private void menuGaragem() {
        int opcao;
        do {
            System.out.println("\n - MENU GARAGEM -");
            System.out.println("1. Criar Nova Garagem");
            System.out.println("2. Mostrar Dados da Garagem");
            System.out.println("3. Estacionar Camião");
            System.out.println("4. Inspeccionar Camião");
            System.out.println("0. Voltar");
            opcao = escolherOpcao(0, 4);
            switch (opcao) {
                case 1:
                    criarNovaGaragem();
                    break;
                case 2:
                    mostrarDadosDaGaragem();
                    break;
                case 3:

                    break;
                case 4:

                    break;
                default:
                    break;
            }
        } while (opcao != 0);
    }

    public void criarNovaGaragem() {
        System.out.println("");
        System.out.println("\t+------------------------+");
        System.out.println("\t| 1 - Criar Nova Garagem |");
        System.out.println("\t+------------------------+");

        String name;
        float latitude;
        float longitude;

        sc.nextLine();//nova linha para evitar erro, a seguir

        do {
            System.out.print("\nNOME DA GARAGEM: ");
            name = sc.nextLine();
            //valida o nome
            if (gt.isNameValid(name) == false) {
                System.out.println("\nNome não pode ser vazio.");
            }//valida a garagem, se existe ou não
            if (gt.isValidGarages(name) == true) {
                System.out.println("\nJá existe Garagem com nome " + name + ".");
                System.out.println(gt.getGarageByName(name).toString());
            }
        } while ((gt.isNameValid(name) == false) || (gt.isValidGarages(name) == true));

        do {
            System.out.print("LATITUDE : ");
            latitude = sc.nextFloat();
            //se a latitude for inválida
            if (Coordinates.isLatitudeValid(latitude) == false) {
                System.out.println("\nLatitude deve estar entre [-90.0º, 90.0º].\n");
            }
        } while (Coordinates.isLatitudeValid(latitude) == false);

        do {
            System.out.print("LONGITUDE: ");
            longitude = sc.nextFloat();
            //se a longitude for inválida
            if (Coordinates.isLongitudeValid(longitude) == false) {
                System.out.println("\nLongitude deve estar entre [-180.0º, 180.0º].\n");
            }
        } while (Coordinates.isLongitudeValid(longitude) == false);

        //insere no gestor de teste
        if (gt.getQuantityGarages() > Gest.MAX_GARAGES) {
            //mostra a mensagem
            System.out.println("\nNão existe espaço para inserir outra Garagem.");
        }//e se existir espaço
        else {
            if (gt.createNewGarages(name, new Coordinates(latitude, longitude)) == true) {
                System.out.println("\nGaragem com nome " + name + " foi inserida com sucesso.");
                //mostra a informação sobre a garagem
                System.out.println(gt.getGarageByName(name).toString());
            } else {
                System.out.println("\nnGaragem com nome " + name + " não foi inserida.");
            }
        }
    }

    public void mostrarDadosDaGaragem() {
        System.out.println("");
        System.out.println("\t+------------------------------+");
        System.out.println("\t| 2 - Mostrar Dados da Garagem |");
        System.out.println("\t+------------------------------+");

        String name;

        sc.nextLine();//nova linha para evitar erro, a seguir

        do {
            System.out.print("\nNOME DA GARAGEM: ");
            name = sc.nextLine();
            //valida o nome
            if (gt.isNameValid(name) == false) {
                System.out.println("\nNome não pode ser vazio.");
            }
        } while ((gt.isNameValid(name) == false));

        //verifica se existe alguma garagem com nome
        if (gt.isValidGarages(name) == true) {
            //mostra a informação sobre a estação
            System.out.println(gt.getGarageByName(name).toString());
        } else {
            System.out.println("\nNão existe nenhuma Garagem com nome " + name + ".");
        }
    }

    private int escolherOpcao(int minimo, int maximo) {
        int opcao;
        do {
            opcao = Integer.MAX_VALUE;
            System.out.print("Escolha Opção : ");
            if (!sc.hasNextInt()) {
                sc.nextLine();
                continue;
            }
            opcao = sc.nextInt();
        } while (opcao < minimo || opcao > maximo);
        return opcao;
    }

    private char respostaSN(String mensagem) {
        char resposta;
        do {
            System.out.print(mensagem);
            resposta = Character.toUpperCase(sc.next().charAt(0));
        } while (resposta != 'S' && resposta != 'N');

        return resposta;
    }
    
    private static void limparTela(){
      System.out.print('\u000C');
    }

}
