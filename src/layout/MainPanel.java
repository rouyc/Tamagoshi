package layout;

import tamagoshi.jeu.TamaGame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JFrame {

    public static void main(String[] args) {
        new MainPanel().Startmenu();
    }

    public void Startmenu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Home");
        add(displayMenu());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JPanel displayMenu() {
        JPanel MenuPan = new JPanel();
        MenuPan.setBorder(new EmptyBorder(50, 100, 25, 100));
        MenuPan.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        MenuPan.add(new JLabel("<html><h1>TamaGame</h1><hr></html>"), gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel buttons = new JPanel(new GridBagLayout());
        JButton nouvellePartie = new JButton("Nouvelle Partie");
        buttons.add(nouvellePartie, gbc);
        JButton aboutBtn = new JButton("A Propos");
        buttons.add(aboutBtn, gbc);
        JButton helpBtn = new JButton("Aide");
        buttons.add(helpBtn, gbc);
        JButton exitBtn = new JButton("Quiter");
        buttons.add(exitBtn, gbc);

        nouvellePartie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                TamaGame.Start();

            }
        });

        aboutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AboutPanel("Jeu Tamagoshi réalisé dans le cadre du cours de Prog objet & J2E réalisé par ROUY Clément" + "\n" +
                        "Date de dernière mise a jour : 04/04/2021" + "\n" +
                        "Version : 1.0.0");

            }
        });

        helpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HelpPanel("Un Tamagotchi est un animal de compagnie virtuel japonais, créé en 1996." + "\n" +
                        "L'objectif est de le nourir et de jouer avec lui afin d'éviter qu'il ne meurent" + "\n" +
                        "Pour lancer une nouvelle partie sélectionner Nouvelle Partie puis choisissez le nombre de tamagoshi." + "\n" +
                        "attention plus le nombre de tamagoshi est grand plus la difficulté est élever");

            }
        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        gbc.weighty = 1;
        MenuPan.add(buttons, gbc);
        return MenuPan;
    }


}