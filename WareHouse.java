
/**
 * Escreva a descrição da classe WareHouse aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class WareHouse
{
    private int quantPacks;
    private Packs[] packs;

    public WareHouse(int max) {
        this.quantPacks = 0;
        this.packs = new Packs[max];
    }

    @Override
    public String toString() {
        String result = "\n-- ARMAZÉM --\n";
        result += "+-------------+\n";
        result += "| QT PACK: " + String.format("%02d |", quantPacks);
        result += "\n+------+----------+-------+-----------+--------------+\n";
        result += "| ID";
        result += "   |   NOME";
        result += "   | QUANT";
        result += " | PESO [Kg]";
        result += " | VOLUME [Cm3] |";
        result += "\n+------+----------+-------+-----------+--------------+";
        //faz o loop
        for (int i = 0; i < quantPacks; i++) {
            //se não for nula
            if(packs[i] != null){
              //mostra os dados de cada pack no contentor
              result += packs[i].toString();
           }
        }
        return result;
    }

    /* Metódo para adicionar pack no armazém */
    public boolean addPack(Packs pack) {
        //se não for nula
        if (pack != null) {
            //inserir o novo pack no array
            packs[quantPacks] = pack;
            //incrementa a quantidade de pack no armazem
            quantPacks++;
            //retorna verdadeira
            return true;
        }//se for o contrário, retorna falso
        return false;
    }

    /* Metódo para obter o pack pelo codigo */
    public Packs getPackByID(int numberPack) {
        int idx;
        //se tiver packs no armazem
        if (quantPacks > 0) {
            //obter a posição do pack
            idx = indexOfPackByNumber(numberPack);
            //se for diferente de -1
            if (idx != -1) {
                //retorna posição do pack no armazem
                return packs[idx];
            }
        }//se for o contrário, retorna nulo
        return null;
    }

    /* Metódo para verificar se o pack existe no armazém */
    public boolean existPack(int numberPack) {
        int idx;
        //se tiver packs no armazem
        if (quantPacks > 0) {
            //obter a posição do pack
            idx = indexOfPackByNumber(numberPack);
            //se for diferente de -1
            if (idx != -1) {
                //retorna verdadeira
                return true;
            }
        }//se for o contrário, retorna falso
        return false;
    }

    /* Metódo para atualizar a quantidade de pack */
    public boolean updateStock(int numberPack, int quantity) {
        int idx;
        int quant;
        int totalQuantity;
        //se tiver packs no armazem
        if (quantPacks > 0) {
            //obter a posição do pack
            idx = indexOfPackByNumber(numberPack);
            //se for diferente de -1
            if (idx != -1) {
                //quantidade inserida maior que zero
                if ((quantity > 0)) {
                    //obter a quantidade
                    quant = packs[idx].getQuantity();
                    //obter o calculo total
                    totalQuantity = quant + quantity;
                    //atualizar a quantidade
                    packs[idx].setQuantity(totalQuantity);
                    //retorna verdadeira
                    return true;
                }
            }
        }//se for o contrário, retorna falso
        return false;
    }

    /* Metódo para remover a quantidade de pack  e adicionar ao contentor */
    public Packs removeQuantityPack(int numberPack, int quantity) {
        int idx;
        int quant;
        int weight;
        float volume;
        int totalQuantity;
        int totalWeight = 0;
        float totalVolume = 0;
        Packs pack;
        //obter a posição do pack
        idx = indexOfPackByNumber(numberPack);
        //se for diferente de -1
        if (idx != -1) {
            //tem que haver quantidade no stock
            if (packs[idx].getQuantity() > 0) {
                //obter o peso do pack
                weight = packs[idx].getWeight();
                //obter o peso total auxiliar +=  peso do pack * quantidade
                totalWeight += weight * quantity;
                //obter o volume
                volume = packs[idx].getVolume();
                //obter o volume total auxiliar +=  volume do pack * quantidade
                totalVolume += convertVolume(volume * quantity);
                //peso da carga não pode ultrapassar o valor maximo
                if ((totalWeight < Containers.MAX_WEIGTH)
                        //volume da carga não pode ultrapassar o valor maximo
                        && (totalVolume < Containers.MAX_VOLUME)) {
                    //se quantidade pack no stock for menor que quantidade
                    if (quantity <= packs[idx].getQuantity()) {
                        //obter a quantidade
                        quant = packs[idx].getQuantity();
                        //obter o calculo total
                        totalQuantity = quant - quantity;
                        //atualizar a quantidade
                        packs[idx].setQuantity(totalQuantity);
                        //criar um novo pack //duvida
                        pack = new Packs(packs[idx].getName(), quantity, packs[idx].getWeight(), packs[idx].getVolume());
                        //obter o id do pack no stock
                        pack.setNumber(numberPack);
                         //se a quantidade de pack no armazem for zero
                        if(packs[idx].getQuantity() == 0){
                         //elimina do stock
                         packs[idx] = packs[idx+1];
                       }
                        //retorna um novo pack
                        return pack;
                    }
                }
            }
        }
        return null;
    }

    /* Metódo para consumir o Pack */
    public Packs consumePack(int numberPack, int quantity) {
        int idx;
        int quant;
        int totalQuantity;
        Packs pack;
        //se tiver packs no armazem
        if (quantPacks > 0) {
            //obter a posição do pack
            idx = indexOfPackByNumber(numberPack);
            //se for diferente de -1
            if (idx != -1) {
               //obter o pack
               pack = packs[idx];
               //se pack não for nulla
               if(pack != null){
                 //obter a quantidade
                quant = packs[idx].getQuantity();
                //obter o calculo total
                totalQuantity = quant - quantity;
                //atualizar a quantidade
                packs[idx].setQuantity(totalQuantity);
                //se a quantidade de pack no armazem for zero
                if(packs[idx].getQuantity() == 0){
                       //elimina do stock
                       packs[idx] = packs[idx+1];
                }
                //retorna o pack
                return pack;
                }
            }
        }//se for o contrário, retorna nulo
        return null;
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

    /* metódo para obter a posição do pack no array */
    private int indexOfPackByNumber(int numberPack) {
        for (int i = 0; i < quantPacks; i++) {
            //se não for nula
            if ((packs[i] != null)
                    //e tiver o mesmo codigo de produto
                    && (packs[i].getNumber() == numberPack)) {
                //retorna a posição do pack no array
                return i;
            }
        }//se for o contrário, retorna -1
        return -1;
    }

    public int getQuantPacks() {
        return quantPacks;
    }

    public void setQuantPacks(int quantPacks) {
        this.quantPacks = quantPacks;
    }

    public Packs[] getPacks() {
        return packs;
    }

    public void setPacks(Packs[] packs) {
        this.packs = packs;
    }

}
