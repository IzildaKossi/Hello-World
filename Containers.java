import java.text.*;
/**
 * Escreva a descrição da classe Containers aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Containers
{
    public static final int MAX_WEIGTH = 22000;//até 22000Kg de carga
    public static final int MAX_VOLUME = 33;//até 33 m3
    public static final int MAX_PACKS = 10;//10 no maximo
    private static int nextNumber = 0;
    private Packs[] packs;
    private int number;
    private int quantityPacks;
    private float weight;
    private float volume;

    public Containers() {
        this.number = ++Containers.nextNumber;
        this.quantityPacks = 0;
        this.weight = 0;
        this.volume = 0;
        this.packs = new Packs[Containers.MAX_PACKS];
    }

    /* metódo para mostrar informações do contentor */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        String result = "\n-- CONTENTOR [" + String.format("%02d", number) + "] --";
        result += "\nID            : " + number;
        result += "\nPeso Máximo   : " + Containers.MAX_WEIGTH + "Kg";
        result += "\nPeso Ocupado  : " + df.format(weight) + "Kg";
        result += "\nPeso Livre    : " + df.format(weightFree()) + "Kg";
        result += "\nEspaço Máximo : " + Containers.MAX_VOLUME + "m3";
        result += "\nEspaço Ocupado: " + df.format(volume) + "m3";
        result += "\nEspaço Livre  : " + df.format(volumeFree()) + "m3";
        result += "\n+-------------+\n";
        result += "| QT PACK: ".concat(String.format("%02d", quantityPacks)) + " |";
        result += "\n+------+----------+-------+-----------+--------------+\n";
        result += "| ID";
        result += "   |   NOME";
        result += "   | QUANT";
        result += " | PESO [Kg]";
        result += " | VOLUME [Cm3] |";
        result += "\n+------+----------+-------+-----------+--------------+";
        for (int i = 0; i < quantityPacks; i++) {
            result += packs[i].toString();
        }
        return result;
    }

    /* metódo para verificar se o contentor está cheio */
    public boolean isFull() {
        //se a quantidade de pack for maxima
        if ((quantityPacks == Containers.MAX_PACKS)
                //ou se peso da carga igual ao peso maximo
                || (weight == Containers.MAX_WEIGTH)
                //ou ainda se o volume da carga igual volume maximo
                || (volume == Containers.MAX_VOLUME)) {
            //retorna verdadeira
            return true;
        } //se for o contrário, retorna falso
        return false;
    }

    /* metódo para verificar se o contentor está vazio */
    public boolean isEmpty() {
        return quantityPacks == 0;
    }

    /* metódo para converter cm3 em m3 */
    private float convertVolume(float newVolume) {
        //volume a obter em m3
        float volumeConvert;
        //Valor em metro = valor centímetro x 0.01
        volumeConvert = newVolume * 0.01f;
        //retorna o volume em metros
        return volumeConvert;
    }

    //* metódo para adicionar pack no contentor */
    public boolean addPack(Packs pack) {
        int totalWeight;
        float totalVolume;
        //se não for nula
        if (pack != null) {
            //se não estiver cheia
            if (isFull() != true) {
                //obter o peso total auxiliar =  peso do pack * quantidade
                totalWeight = pack.getWeight() * pack.getQuantity();
                //obter o volume total auxiliar =  peso do pack * quantidade
                totalVolume = convertVolume(pack.getVolume() * pack.getQuantity());
                //peso da carga não pode ultrapassar o valor maximo
                if ((totalWeight < Containers.MAX_WEIGTH)
                        //volume da carga não pode ultrapassar o valor maximo
                        && (totalVolume < Containers.MAX_VOLUME)) {
                    //obter o peso total
                    weight += totalWeight;
                    //obter o volume total
                    volume += totalVolume;
                    //peso da carga não pode ultrapassar o valor maximo
                    if ((weight < Containers.MAX_WEIGTH)
                            //volume da carga não pode ultrapassar o valor maximo
                            && (volume < Containers.MAX_VOLUME)) {
                        //inserir o pack no array
                        packs[quantityPacks] = pack;
                        //incrementa a quantidade de pack no armazem
                        quantityPacks++;
                        //retorna verdadeira
                        return true;
                    }//se ultrapassar 
                    else {
                        //retira o peso adicional
                        weight -= totalWeight;
                        //retira o volume adicional
                        volume -= totalVolume;
                        //se for o contrário, retorna falso
                        return false;
                    }
                }//peso e o volume ultrapassar o valor maximo 
            }
        }
        //se for o contrário, retorna falso
        return false;
    }

    private int indexOfPackByID(int numberPack) {
        for (int i = 0; i < quantityPacks; i++) {
            //se não for nula
            if ((packs[i] != null)
                    //e tiver o mesmo numero de produto
                    && (packs[i].getNumber() == numberPack)) {
                //retorna a posição do pack no array
                return i;
            }
        }//se for o contrário, retorna -1
        return -1;
    }


    /* metódo para descarregar pack */
    public Packs emptyContainer(int numberPack) {
        int idx;
        int totalWeight;
        float totalVolume;
        Packs pack;
        //obter a posição do pack no contentor
        idx = indexOfPackByID(numberPack);
        //se não estiver vazio
       if (isEmpty() != true) {
            //se for diferente de - 1
            if (idx != -1) {
                //obter uma cópia do pack a ser descarregado
                pack = packs[idx];
                //se não for nula
                if (pack != null) {
                    //obter o peso total auxiliar =  peso do pack * quantidade
                    totalWeight = pack.getWeight() * pack.getQuantity();
                    //subtrair o peso do pack do contentor
                    weight -= totalWeight;
                    //obter o volume total auxiliar =  peso do pack * quantidade
                    totalVolume = convertVolume(pack.getVolume() * pack.getQuantity());
                    //subtrair o volume do pack do contentor
                    volume -= totalVolume;
                    //faz o loop
                    for (int i = idx; i < quantityPacks - 1; i++) {
                        //elimina o pack do contentor
                        packs[i] = packs[i + 1];
                    }//decrementa a quantidade de pack
                    quantityPacks--;
                    //se for o contrário, retorna nulo
                    return pack;
                }
            }
        }//se for o contrário, retorna nulo
        return null;
    }

    /* metódo para obter o peso disponivel */
    private float weightFree() {
        //peso a obter em m3
        float weightFree;
        //peso disponivel = peso maximo - peso ocupado
        weightFree = Containers.MAX_WEIGTH - weight;
        //retorna o volume em metros
        return weightFree;
    }

    /* metódo para obter o espaço livre */
    private float volumeFree() {
        //volume a obter em m3
        float volumeFree;
        //espaço disponivel = volume maximo - volume ocupado
        volumeFree = Containers.MAX_VOLUME - volume;
        //retorna o volume livre
        return volumeFree;
    }

    public Packs[] getPacks() {
        return packs;
    }

    public void setPacks(Packs[] packs) {
        this.packs = packs;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getQuantityPacks() {
        return quantityPacks;
    }

    public void setQuantityPacks(int quantityPacks) {
        this.quantityPacks = quantityPacks;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }
}
