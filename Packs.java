import java.text.DecimalFormat;
/**
 * Escreva a descrição da classe Packs aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Packs
{
   // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    private static int newNumber = 0;
    private int number;
    private String name;
    private int quantity;
    private int weight;//Kg
    private float volume;//Cm3

    /**
     * COnstrutor para objetos da classe Products
     */
    public Packs(String name, int quantity, int weight, float volume) {
        this.number = ++Packs.newNumber;
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
        this.volume = volume;
    }

    /**
     * Exemplo de método - substitua este comentário pelo seu próprio
     *
     * @return
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        String result = "\n";
        result += "";
        result += "|   " + String.format("%02d", number) + " ";
        result += "| " + String.format("%8s", name) + " ";
        result += "| " + String.format("%4s", quantity) + "  ";
        result += "| " + String.format("%8s", weight) + "  ";
        result += "|     " + String.format("%4s", df.format(volume)) + "     |";
        result += "\n+------+----------+-------+-----------+--------------+";
        return result;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getWeight() {
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
