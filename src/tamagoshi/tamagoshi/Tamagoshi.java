package tamagoshi.tamagoshi;

import layout.TamaPanel;

import java.util.Random;

public class Tamagoshi {
    private int age;
    private final int maxEnergy;
    private int energy;
    private final int maxFun;
    private int fun;
    private final String name;
    public static int lifeTime = 10;
    private final java.util.Random random;
    private TamaPanel tamaPanel;

    /**
     *
     * @param name
     * Constructor Tamagoshi
     */
    public Tamagoshi(String name) {
        this.name = name;
        this.random = new Random();
        this.age = 0;
        this.maxEnergy = this.random.nextInt(5) + 5;
        this.maxFun = this.random.nextInt(5) + 5;
        this.energy = this.random.nextInt(5) + 3;
        this.fun = this.random.nextInt(5) + 3;
    }

    /**
     *
     * @return boolean result
     * False si le tamagoshi a besoin d'interaction
     * True si il n'as besoin de rien
     * Set le tamaPanel avec l'etat du tamagoshi
     */
    public boolean parle() {
        String response = "";
        boolean result;
        if(this.energy>4 & this.fun>4) {
            response = (this.name + " : Tout va bien !");
            result = true;
        }
        else if(this.energy<=4 && this.fun>4){
            response = (this.name + " : J'ai faim");
            result =  false;
        } else if(this.energy>4) {
            response = (this.name + " : Je veut jouer");
            result = false;
        } else {
            response = (this.name + " :  Ignore moi");
            result = false;
        }
        tamaPanel.setEtatlabel(response);
        return result;
    }

    /**
     *
     * @return true si le tama avait faim false sinon
     * Set le tamaPanel avec l'etat du tamagoshi
     *
     */
    public boolean mange() {
        String response = "";
        boolean result;
        if (this.energy<this.maxEnergy) {
            this.energy += random.nextInt(2)+1;
            response = ("Merci j'avais faim");
            result =  true;
        } else {
            response = ("J'ai pas faim");
            result =  false;
        }
        tamaPanel.setResponseLabel(response);
        return result;
    }

    /**
     *
     * @return true si le tama avait envie de jouer false sinon
     * Set le tamaPanel avec l'etat du tamagoshi
     */
    public boolean fun() {
        String response = "";
        boolean result;
        if (this.fun<this.maxFun) {
            this.fun += random.nextInt(2)+1;
            response = ("Je m'amuse");
            result =  true;
        } else {
            response = ("Laisse moi tranquille");
            result =  false;
        }
        tamaPanel.setResponseLabel(response);
        return result;
    }

    /**
     *
     * @return false si le tama est mort true sinon
     * et reduit l'energie fun de 1
     * augmente l'age de 1
     */
    public boolean interaction() {
        this.energy--;
        this.fun--;
        this.age++;
        if (this.energy<=0) {
            System.out.println(this.name+" : Je suis KO");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return "\nTamagoshi{" +
                "age=" + age +
                ", maxEnergy=" + maxEnergy +
                ", energy=" + energy +
                ", maxFun=" + maxFun +
                ", fun=" + fun +
                ", name='" + name + '\'' +
                ", random=" + random +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    /**
     *
     * @param t
     * @return true si tama et a l'age limite
     * false sinon
     */
    public static boolean atteintAgeLimite(Tamagoshi t) {
        if (t.age >= Tamagoshi.lifeTime) {
            return true;
        } else {
            return false;
        }
    }

    public void AgePlus1() {
        this.age++;
    }

    public void EnergyMoins1() {
        this.energy--;
    }

    public void FunMoins1() {
        this.fun--;
    }

    public int getEnergy() {
        return energy;
    }

    public void setTamaPanel(TamaPanel tamaPanel) {
        this.tamaPanel = tamaPanel;
    }

    public TamaPanel getTamaPanel() {return tamaPanel;}
}
