package tamagoshi.jeu;

import layout.TamaPanel;
import tamagoshi.tamagoshi.GrosJoueur;
import tamagoshi.tamagoshi.GrosMangeur;
import tamagoshi.tamagoshi.Tamagoshi;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class TamaGame extends JFrame {
    private ArrayList<Tamagoshi> tamagoshiDepart;
    private ArrayList<Tamagoshi> tamagoshiEnCourse;
    private final Random random = new Random();
    protected ArrayList<TamaPanel> panels = new ArrayList<TamaPanel>();
    protected JTextArea console;
    protected int tour = 0;
    private int nbActions;

    public TamaGame() {
        this.initialisation();
    }

    private void initialisation() {
        this.tamagoshiDepart = new ArrayList<Tamagoshi>();
        this.tamagoshiEnCourse = new ArrayList<Tamagoshi>();
        int nbTamagoshi = 0;
        while (nbTamagoshi <= 0 || nbTamagoshi > 8) {
            try {
                nbTamagoshi = Integer.parseInt(JOptionPane.showInputDialog("Nombre de tamagoshi voulu (Max : 8, + de tamagoshi = + de difficultés) :", "1"));
            } catch (NumberFormatException var11) {
                JOptionPane.showMessageDialog((Component) null, "Vous devez choisir le nombre de tamagoshi voulu (Max : 8)", "Erreur", 0);
            }
        }
        for (int i = 1; i < nbTamagoshi + 1; i++) { //Création des tamagoshi
            String name = "Tamagoshi " + i;
            Tamagoshi tama;
            if (random.nextBoolean()) {
                tama = new GrosJoueur(name);
            } else {
                tama = new GrosMangeur(name);
            }
            this.tamagoshiDepart.add(tama);
            this.tamagoshiEnCourse.add(tama);

        }
        this.console = new JTextArea();
        this.setSize(250, 400);
        setLocationRelativeTo(null);
        this.add(new JScrollPane(console));

    }

    private void afficherInfos(String infos) { //
        this.console.append(infos + "\n");
        this.console.setCaretPosition(this.console.getDocument().getLength());
    }

    public void play() { //Initialisation de tous les tama
        this.setVisible(true);
        afficherInfos("------------------Nouvelle Partie------------------");
        int x = 0;
        int y = 0;
        for (int i = 0; i < tamagoshiDepart.size(); i++) {
            TamaPanel tp = new TamaPanel(tamagoshiDepart.get(i), x, y); //Création d'un tama panel par tama
            tp.setTamaGame(this);
            tamagoshiDepart.get(i).setTamaPanel(tp);
            this.panels.add(tp);
            if (x > Toolkit.getDefaultToolkit().getScreenSize().width-2*(tp.getWidth()+30)) {
                y += tp.getSize().height + 30;
                x = 0;
            } else {
                x += tp.getSize().width + 30;
        }
            tamagoshiDepart.get(i).parle();
            afficherInfos(tp.getEtat());
        }

    }

    public void nouveauTour() {
        nbActions++;
        if (nbActions == 2) {
            tour++;
            afficherInfos("------------------Tour n°" + tour + "------------------");
            nbActions = 0;
            ArrayList<TamaPanel> dead = new ArrayList<TamaPanel>();
            for (TamaPanel tp : this.panels
            ) {
                if (tp.getMonTamagoshi().interaction() && !Tamagoshi.atteintAgeLimite(tp.getMonTamagoshi())) {
                    afficherInfos(tp.getEtat());
                    tp.disableNourrir(true);
                    tp.disableJouer(true);
                } else {
                    System.out.println(tp.getMonTamagoshi().toString());
                    tp.setEtatlabel("Je suis KO !"); //Afficher dans la fenetre du tamagoshi
                    afficherInfos(tp.getMonTamagoshi().getName() + " : Je suis KO"); // Afficher dans le suivi global des tamagoshis
                    tp.setResponseLabel("");
                    tp.disableJouer(false);
                    tp.disableNourrir(false);
                    tamagoshiEnCourse.remove(tp.getMonTamagoshi());
                    dead.add(tp);
                }
            }
            panels.removeAll(dead);
        }
        if (tamagoshiEnCourse.size()==0) {
            resultat();
        }
    }

    public void setButton(JButton btn, boolean enable) { //Définition de l'état du boutton en fonction de l'état des tamagoshis
        for (TamaPanel tp : this.panels
        ) {
            if (btn.getText().equals("Nourrir")) {
                tp.disableNourrir(false);
            } else {
                tp.disableJouer(false);
            }
        }

    }

    private double score() {
        double somme = 0;
        for (Tamagoshi t : this.tamagoshiDepart
        ) {
            somme += t.getAge();
        }
        return somme / (Tamagoshi.lifeTime * this.tamagoshiDepart.size());
    }

    private void resultat() {
        this.setSize(750, 400);
        System.out.println("\n------Bilan------");
        for (Tamagoshi t : this.tamagoshiDepart
        ) {
            if (t.getAge() == Tamagoshi.lifeTime) {
                System.out.println(t.getName() + " le " + t.getClass().getSimpleName() + " est vivant");
                afficherInfos(t.getName() + " le " + t.getClass().getSimpleName()  + " est vivant");
            } else {
                System.out.println(t.getName() + " le " + t.getClass().getSimpleName() + " est mort");
                afficherInfos(t.getName() + " le " + t.getClass().getSimpleName() + " est mort");
            }

        }
        System.out.println("\n Score " + this.score() * 100 + "%");
        afficherInfos("\n Score " + this.score() * 100 + "%");
    }

    public static void Start() { //Démarrage de la partie
        TamaGame jeu = new TamaGame(); //Initialisation de la partie
        jeu.play(); //démarrage de la partie
    }

}
