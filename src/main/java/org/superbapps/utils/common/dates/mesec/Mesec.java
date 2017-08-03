package org.superbapps.utils.common.dates.mesec;

/**
 *
 * @author д06ри
 */
public enum Mesec {
    JAN("Januar"),
    FEB("Februar"),
    MAR("Mart"),
    APR("April"),
    MAJ("Maj"),
    JUN("Jun"),
    JUL("Jul"),
    AVG("Avgust"),
    SEP("Septembar"),
    OKT("Oktobar"),
    NOV("Novembar"),
    DEC("Decembar");

    private final String name;

    private Mesec(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Vrati naziv meseca
     *
     * @param indeks Redni broj meseca (januar=1, feb=2,..)
     * @return
     */
    public static Mesec get(int indeks) {
        return Mesec.values()[indeks - 1];
    }

}
