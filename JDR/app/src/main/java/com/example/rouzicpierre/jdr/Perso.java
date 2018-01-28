package com.example.rouzicpierre.jdr;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.Table;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by rouzic pierre on 04/03/2017.
 */
@Table
public class Perso{
    Long id;

    private static Perso monperso =null ;
    private int CC;
    private int CT;
    private int Ag;
    private int F;
    private int E;
    private int FM;
    private int At;
    private int Int;
    private int Mag;
    private int P;
    private int Soc;
    private int PVmax;
    private int PVactuel;
    private int bonnuspoid;
    private int bonnusCA;
    private int bonnusCD;
    private int bonnusDegat;
    private int bonnusArmure;
    private int bonnusBE;
    private int bonnusPuissSort;
    private int bonnusReussitSort;
    private int pointDeDestin;
    private int PO;
    private int PA;
    private int xpactuel;
    private int xptotal;
    private int resmagique;
    private String nom;
    private String classe;
    private String race;
    private int arme = 0;
    @Ignore
    public Map mapperso;



    public void actualisePerso(){

        CC= (int) mapperso.get("CC");
        CT= (int) mapperso.get("CT");
        Ag= (int) mapperso.get("Ag");
        F= (int) mapperso.get("F");
        E= (int) mapperso.get("E");
        FM= (int) mapperso.get("FM");
        At= (int) mapperso.get("At");
        Int= (int) mapperso.get("Int");
        Mag= (int) mapperso.get("Mag");
        P= (int) mapperso.get("P");
        Soc= (int) mapperso.get("Soc");
        PVmax= (int) mapperso.get("PVmax");
        PVactuel= (int) mapperso.get("PVactuel");
        bonnuspoid= (int) mapperso.get("Bpoid");
        bonnusCA = (int) mapperso.get("BCA");
        bonnusCD = (int) mapperso.get("BCD");
        bonnusDegat = (int) mapperso.get("BD");
        bonnusArmure = (int) mapperso.get("BA");
        bonnusBE = (int) mapperso.get("BBE");
        bonnusPuissSort = (int) mapperso.get("BPS");
        bonnusReussitSort = (int) mapperso.get("BRS");
        pointDeDestin = (int) mapperso.get("PD");
        PO = (int) mapperso.get("PO");
        PA = (int) mapperso.get("PA");
        xpactuel = (int) mapperso.get("XPact");
        xptotal = (int) mapperso.get("XPtot");
        resmagique = (int) mapperso.get("RM");
        classe = (String) mapperso.get("Classe");
        race = (String) mapperso.get("Race");
        nom = (String) mapperso.get("Nom");
        arme = (int) mapperso.get("Arme");

    }

    public void actualiseMap(){

        mapperso.put("CC",CC);
        mapperso.put("CT",CT);
        mapperso.put("Ag",Ag);
        mapperso.put("F",F);
        mapperso.put("E",E);
        mapperso.put("FM",FM);
        mapperso.put("At",At);
        mapperso.put("Int",Int);
        mapperso.put("Mag",Mag);
        mapperso.put("P",P);
        mapperso.put("Soc",Soc);
        mapperso.put("PVmax",PVmax);
        mapperso.put("PVactuel",PVactuel);
        mapperso.put("Bpoid",bonnuspoid);
        mapperso.put("BCA",bonnusCA);
        mapperso.put("BCD",bonnusCD);
        mapperso.put("BD",bonnusDegat);
        mapperso.put("BA",bonnusArmure);
        mapperso.put("BBE",bonnusBE);
        mapperso.put("BPS",bonnusPuissSort);
        mapperso.put("BRS",bonnusReussitSort);
        mapperso.put("PD",pointDeDestin);
        mapperso.put("PO",PO);
        mapperso.put("PA",PA);
        mapperso.put("XPact",xpactuel);
        mapperso.put("XPtot",xptotal);
        mapperso.put("RM",resmagique);
        mapperso.put("Classe",classe);
        mapperso.put("Race",race);
        mapperso.put("Nom",nom);
        mapperso.put("Arme",arme);

    }

    public Perso() {
        mapperso = new HashMap();

    }


    public static Perso getMonperso(){
        if (monperso==null){
            monperso=new Perso();
        }
        return monperso;
    }




    public static void setMonperso(Perso newperso) {
        monperso = newperso;
    }

    public int getCC() {
        return CC;
    }

    public void setCC(int CC) {
        this.CC = CC;
    }

    public int getCT() {
        return CT;
    }

    public void setCT(int CT) {
        this.CT = CT;
    }

    public int getAg() {
        return Ag;
    }

    public void setAg(int ag) {
        Ag = ag;
    }

    public int getF() {
        return F;
    }

    public void setF(int f) {
        F = f;
    }

    public int getE() {
        return E;
    }

    public void setE(int e) {
        E = e;
    }

    public int getFM() {
        return FM;
    }

    public void setFM(int FM) {
        this.FM = FM;
    }

    public int getAt() {
        return At;
    }

    public void setAt(int at) {
        At = at;
    }

    public int getInt() {
        return Int;
    }

    public void setInt(int anInt) {
        Int = anInt;
    }

    public int getMag() {
        return Mag;
    }

    public void setMag(int mag) {
        Mag = mag;
    }

    public int getP() {
        return P;
    }

    public void setP(int p) {
        P = p;
    }

    public int getSoc() {
        return Soc;
    }

    public void setSoc(int soc) {
        Soc = soc;
    }

    public int getPVmax() {
        return PVmax;
    }

    public void setPVmax(int PV) {
        this.PVmax = PV;
    }
    public int getPVactuel() {
        return PVactuel;
    }

    public void setPVactuel(int PVactuel) {
        this.PVactuel = PVactuel;
    }


    public int getBonnuspoid() {
        return bonnuspoid;
    }

    public void setBonnuspoid(int bonnuspoid) {
        this.bonnuspoid = bonnuspoid;
    }

    public int getBonnusCA() {
        return bonnusCA;
    }

    public void setBonnusCA(int bonnusCA) {
        this.bonnusCA = bonnusCA;
    }

    public int getBonnusCD() {
        return bonnusCD;
    }

    public void setBonnusCD(int bonnusCD) {
        this.bonnusCD = bonnusCD;
    }

    public int getBonnusDegat() {
        return bonnusDegat;
    }

    public void setBonnusDegat(int bonnusDegat) {
        this.bonnusDegat = bonnusDegat;
    }

    public int getBonnusArmure() {
        return bonnusArmure;
    }

    public void setBonnusArmure(int bonnusArmure) {
        this.bonnusArmure = bonnusArmure;
    }

    public int getBonnusBE() {
        return bonnusBE;
    }

    public void setBonnusBE(int bonnusBE) {
        this.bonnusBE = bonnusBE;
    }

    public int getBonnusPuissSort() {
        return bonnusPuissSort;
    }

    public void setBonnusPuissSort(int bonnusPuissSort) {
        this.bonnusPuissSort = bonnusPuissSort;
    }

    public int getBonnusReussitSort() {
        return bonnusReussitSort;
    }

    public void setBonnusReussitSort(int bonnusReussitSort) {
        this.bonnusReussitSort = bonnusReussitSort;
    }

    public int getPointDeDestin() {
        return pointDeDestin;
    }

    public void setPointDeDestin(int pointDeDestin) {
        this.pointDeDestin = pointDeDestin;
    }

    public int getPO() {
        return PO;
    }

    public void setPO(int po) {
        PO = po;
    }

    public int getXpactuel() {
        return xpactuel;
    }

    public void setXpactuel(int xpactuel) {
        this.xpactuel = xpactuel;
    }
    public void addxp(int xp){
        this.xpactuel = this.xpactuel +xp;
        this.xptotal = this.xptotal + xp;
    }

    public int getXptotal() {
        return xptotal;
    }

    public void setXptotal(int xptotal) {
        this.xptotal = xptotal;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }


    public int getResmagique() {
        return resmagique;
    }

    public void setResmagique(int resmagique) {
        this.resmagique = resmagique;
    }

    public int getPA() {
        return PA;
    }

    public void setPA(int pa) {
        PA = pa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getArme() {
        return arme;
    }

    public void setArme(int arme) {
        this.arme = arme;
    }

    @Override
    public String toString() {
        return "PersoParse{" +
                "race='" + race + '\'' +
                ", CC=" + CC +
                ", CT=" + CT +
                ", Ag=" + Ag +
                ", F=" + F +
                ", E=" + E +
                ", FM=" + FM +
                ", At=" + At +
                ", Int=" + Int +
                ", Mag=" + Mag +
                ", P=" + P +
                ", Soc=" + Soc +
                ", PVmax=" + PVmax +
                ", PVactuel=" + PVactuel +
                ", bonnuspoid=" + bonnuspoid +
                ", bonnusCA=" + bonnusCA +
                ", bonnusCD=" + bonnusCD +
                ", bonnusDegat=" + bonnusDegat +
                ", bonnusArmure=" + bonnusArmure +
                ", bonnusBE=" + bonnusBE +
                ", bonnusPuissSort=" + bonnusPuissSort +
                ", bonnusReussitSort=" + bonnusReussitSort +
                ", pointDeDestin=" + pointDeDestin +
                ", PO=" + PO +
                ", PA=" + PA +
                ", xpactuel=" + xpactuel +
                ", xptotal=" + xptotal +
                ", resmagique=" + resmagique +
                ", nom='" + nom + '\'' +
                ", classe='" + classe + '\'' +
                '}';
    }
}
