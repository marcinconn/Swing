import java.awt.*;
import javax.swing.*;

public class MyDatePicker {
    int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
    int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
    JLabel l = new JLabel("", JLabel.CENTER);
    String day = "";
    JDialog d;
    JButton[] button = new JButton[49];

    /**
     * Tworzy ramkę z komponentami
     * @param parent JFrame, do której zostanie dopasowana ramka z wyborem daty
     */
    public MyDatePicker(JFrame parent){
        d = new JDialog();
        d.setModal(true);
        String[] header = { "Nd", "Pon", "Wt", "Śr", "Czw", "Pt", "Sob" };
        JPanel p1 = new JPanel(new GridLayout(7, 7));
        p1.setPreferredSize(new Dimension(430, 120));
        for (int x = 0; x < button.length; x++)
        {
            final int selection = x;
            button[x] = new JButton();
            button[x].setFocusPainted(false);
            button[x].setBackground(Color.white);
            if (x > 6)
                button[x].addActionListener(ev ->
                    {
                        day = button[selection].getActionCommand();
                        d.dispose();
                    });
            if (x < 7)
            {
                button[x].setText(header[x]);
                button[x].setForeground(Color.red);
            }
            p1.add(button[x]);
        }
        JPanel p2 = new JPanel(new GridLayout(1, 3));

        JButton previous = new JButton("<< Poprzedni miesiąc");
        previous.addActionListener(ev ->
            {
                month--;
                displayDate();
            });
        p2.add(previous);
        p2.add(l);

        JButton next = new JButton("Następny miesiąc >>");
        next.addActionListener(ev ->
            {
                month++;
                displayDate();
            });
        p2.add(next);

        d.add(p1, BorderLayout.CENTER);
        d.add(p2, BorderLayout.SOUTH);
        ImageIcon img = new ImageIcon("C:\\Users\\Marcin\\Desktop\\uczelnia\\semestr_3\\programowanie_obiektowe\\FormSign.png");
        d.setIconImage(img.getImage());
        d.pack();

        d.setLocationRelativeTo(parent);
        displayDate();
        d.setVisible(true);
    }

    /**
     * Tworzy siatkę przycisków z kalendarzem
     */
    public void displayDate()
    {
        for (int x = 7; x < button.length; x++)
            button[x].setText("");
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(year, month, 1);

        int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
        int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);

        for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++)
            button[x].setText("" + day);
        l.setText(sdf.format(cal.getTime()));
        d.setTitle("Wybierz datę");
    }

    /**
     * Zwraca wybraną datę
     * @return wybrana data
     */
    public String setPickedDate()
    {
        if (day.equals(""))return day;
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(year, month, Integer.parseInt(day));
        return sdf.format(cal.getTime());
    }
}