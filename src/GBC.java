import java.awt.*;

public class GBC extends GridBagConstraints {
    /**
     * Tworzy obiekt typu GBC z podanymi wartościami gridx i gridy oraz wszystkimi pozostałymi parametrami ustawionymi
     * na wartości domyślne.
     * @param gridx współrzędna gridx
     * @param gridy współrzędna gridy
     */
    public GBC(int gridx, int gridy){
        this.gridx=gridx;
        this.gridy=gridy;
    }

    /**Tworzy obiekt GBC z podanymi wartościami gridx, gridy, gridwidth, gridheight oraz wszystkimi pozostałymi
     * parametrami ustawionymi na wartości domyślne.
     * @param gridx współrzędna gridx
     * @param gridy współrzędna gridy
     * @param gridwidth liczba zajmowanych komórek w poziomie
     * @param gridheight liczba zajmowanych komórek w pionie
     */
    public GBC(int gridx, int gridy, int gridwidth, int gridheight){
        this.gridx=gridx;
        this.gridy=gridy;
        this.gridwidth=gridwidth;
        this.gridheight=gridheight;
    }

    /**
     * Ustawia parametr anchor.
     * @param anchor wartość parametru anchor
     * @return this obiekt do dalszej modyfikacji
     */
    public GBC setAnchor(int anchor){
        this.anchor=anchor;
        return this;
    }

    /**
     * Ustawia kierunek zapełniania.
     * @param fill kierunek zapełniania
     * @return this obiekt do dalszej modyfikacji
     */
    public GBC setFill(int fill){
        this.fill=fill;
        return this;
    }

    /**
     * Ustawia parametry wight komórek.
     * @param weightx parametr weight w poziomie
     * @param weighty parametr weight w pionie
     * @return this obiekt do dalszej modyfikacji
     */
    public GBC setWeight(double weightx, double weighty){
        this.weightx=weightx;
        this.weighty=weighty;
        return this;
    }

    /**
     * Ustawia dodatkową przestrzeń w komórce.
     * @param distance dopełnienie we wszystkich komórkach
     * @return this obiekt do dalszej modyfikacji
     */
    public GBC setInsets(int distance){
        this.insets=new Insets(distance, distance, distance, distance);
        return this;
    }

    /**
     * Ustawia dopełnienia w komórce.
     * @param top odstęp od górnej krawędzi
     * @param left odstęp od lewej krawędzi
     * @param bottom odstęp od dolnej krawędzi
     * @param right odstęp od prawej krawędzi
     * @return this obiekt do dalszej modyfikacji
     */
    public GBC setInsets(int top, int left, int bottom, int right){
        this.insets=new Insets(top, left, bottom, right);
        return this;
    }

    /**
     * Ustawia dopełnienie wewnętrzne.
     * @param ipadx dopełnienie wewnętrzne poziome
     * @param ipady dopełnienie wewnętrzne pionowe
     * @return this obiekt do dalszej modyfikacji
     */
    public GBC setIpad(int ipadx, int ipady){
        this.ipadx=ipadx;
        this.ipady=ipady;
        return this;
    }

}
