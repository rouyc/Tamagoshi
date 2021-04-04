package tamagoshi.tamagoshi;

public class GrosJoueur extends Tamagoshi {
    public GrosJoueur(String name) {
        super(name);
    }

    /**
     *
     * @return false si le tama est mort true sinon
     * et reduit l'energie de 1 mais le fun de 2 car c'est un gros joueur
     * augmente l'age de 1
     */
    @Override
    public boolean interaction() {
        EnergyMoins1();
        FunMoins1();
        FunMoins1();
        AgePlus1();
        if (getEnergy()<=0) {
            System.out.println(getName()+" : Je suis mort");
            return false;
        } else {
            return true;
        }
    }
}
