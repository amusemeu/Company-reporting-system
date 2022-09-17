package amusemeu.companyReportingSystem.model;

import javax.persistence.*;

@Entity
@Table(name = "Vvod")
public class Vvod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NTE")
    private String nte;

    @Column(name = "kod_proekta")
    private String kodProekta;

    @Column(name = "biznes_kod")
    private String biznesKod;

    @Column(name = "god_postavki")
    private String godPostavki;

    @Column(name = "naimenovanie_oborudovania")
    private String naimenovanieOborudovania;

    @Column(name= "kolichestvo")
    private String kolichestvo;

    @Column(name = "stoimost_oborudovania")
    private String stoimostOborudoivania;

    @Column(name = "fact_vvoda")
    private String factVvoda;

    public Vvod(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNte() {
        return nte;
    }

    public void setNte(String nte) {
        this.nte = nte;
    }

    public String getKodProekta() {
        return kodProekta;
    }

    public void setKodProekta(String kodProekta) {
        this.kodProekta = kodProekta;
    }

    public String getGodPostavki() {
        return godPostavki;
    }

    public void setGodPostavki(String godPostavki) {
        this.godPostavki = godPostavki;
    }

    public String getNaimenovanieOborudovania() {
        return naimenovanieOborudovania;
    }

    public void setNaimenovanieOborudovania(String naimenovanieOborudovania) {
        this.naimenovanieOborudovania = naimenovanieOborudovania;
    }

    public String getKolichestvo() {
        return kolichestvo;
    }

    public void setKolichestvo(String kolichestvo) {
        this.kolichestvo = kolichestvo;
    }

    public String getStoimostOborudoivania() {
        return stoimostOborudoivania;
    }

    public void setStoimostOborudoivania(String stoimostOborudoivania) {
        this.stoimostOborudoivania = stoimostOborudoivania;
    }

    public String getFactVvoda() {
        return factVvoda;
    }

    public void setFactVvoda(String factVvoda) {
        this.factVvoda = factVvoda;
    }

    public String getBiznesKod() {
        return biznesKod;
    }

    public void setBiznesKod(String biznesKod) {
        this.biznesKod = biznesKod;
    }
}
