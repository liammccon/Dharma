package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    private final int width = 600;
    private final int height = 400;
    private final JLabel verseLabel;
    private final JLabel infoLabel;
    private final JFrame frame;
    private final JPanel panel;
    private Verse nextVerse;

    public GUI(){
        //building the frame
        frame = new JFrame();
        frame.setSize(width, height);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screenSize.width - width)/2, (screenSize.height - height)/2);

        JButton button = new JButton("Get quote");
        button.addActionListener(this);
        verseLabel = new JLabel("");
        verseLabel.setBorder(new EmptyBorder(10,0,0,0));
        infoLabel = new JLabel("");
        infoLabel.setFont(infoLabel.getFont().deriveFont(Font.ITALIC));
        infoLabel.setBorder(new EmptyBorder(10,0,0,0));

        //Setting the panel to which you add button and labels
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 15, 30));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(button);
        panel.add(verseLabel);
        panel.add(infoLabel);

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        verseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Dharma Quote of the Day");
        frame.setVisible(true);

        setNextVerse();
    }

    private void setNextVerse(){
        nextVerse = API.getRandomVerse();
    }

    public static void main(String[]args){
        new GUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final String WRAP_FORMAT = "<html><body style=\"text-align: justify;  text-justify: inter-word;\">%s</body></html>";
        verseLabel.setText(String.format(WRAP_FORMAT, "\"" + nextVerse.getVerseText() + "\""));
        infoLabel.setText("~ From " + nextVerse.getBookTitle()
                + ": chapter " + nextVerse.getChapterNumber()
                + ", verse " + nextVerse.getVerseNumber() + ".");
        setNextVerse();
    }
}

