package tamagoshi.tamagoshi;

public class GrosMangeur extends Tamagoshi {
    public GrosMangeur(String name) {
        super(name);
    }

    /**
     *
     * @return false si le tama est mort true sinon
     * et reduit le fun de 1 mais l'nergie de 2 car c'est un gros mangeur
     * augmente l'age de 1
     */
    @Override
    public boolean interaction() {
        EnergyMoins1();
        EnergyMoins1();
        FunMoins1();
        AgePlus1();
        if (getEnergy()<=0) {
            System.out.println(getName()+" : J'ai plus d'énergie");
            return false;
        } else {
            return true;
        }
    }
}
