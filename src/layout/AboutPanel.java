package layout;

import javax.swing.*;
import java.awt.*;

public class AboutPanel extends JFrame {
    AboutPanel(String AboutTxt) {
        setTitle("A propos");
        setBounds(500, 500, 750, 250);
        JPanel panHaut = new JPanel();
        JPanel panBas = new JPanel();
        panHaut.setLayout(new BorderLayout());
        panBas.setLayout(new FlowLayout());

        JTextArea jtaSortie = new JTextArea(AboutTxt,10,5);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        panHaut.add(jtaSortie);

        contentPane.add(panHaut,"North");
        contentPane.add(panBas, "South");

        this.setVisible(true);
    }
}
