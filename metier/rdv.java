package metier;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static java.sql.Time.valueOf;
import static metier.connexionbdd.connectBD;
import static metier.connexionbdd.connexion;

public class rdv{

    private int idPatient;
    private int idrdv;
    private java.time.LocalDate date;
    private java.time.LocalTime heure;
    private String obj;

    public rdv() {
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public int getIdrdv() {
        return idrdv;
    }

    public void setIdrdv(int idrdv) {
        this.idrdv = idrdv;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHeure() {
        return heure;
    }

    public void setHeure(LocalTime heure) {
        this.heure = heure;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public rdv(int idPatient, int idrdv, LocalDate date, LocalTime heure, String obj) {
        this.idPatient = idPatient;
        this.idrdv = idrdv;
        this.date = date;
        this.heure = heure;
        this.obj = obj;
    }

    public void ajouter_rdv() throws Exception {
        try {
            Date d = Date.valueOf(date);

            java.sql.Date sqldate = new java.sql.Date(d.getTime());

            System.out.println(sqldate);

            Time h = valueOf(heure);
            java.sql.Time sqlheure = new java.sql.Time(h.getTime());
            System.out.println(sqlheure);

            String requete = "INSERT INTO rdv Values (?,?,?,?,?)";
            Connection connexion = connectBD();
            PreparedStatement st = connexion.prepareStatement(requete);

            st.setInt(1, idPatient);
            st.setInt(2, idrdv);
            st.setDate(3, sqldate);
            st.setTime(4, sqlheure);
            st.setString(5, obj);

            st.execute();
            System.out.println("rdv ajoute");
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void modifier_rdv() throws Exception {
        try {
            Date d = Date.valueOf(date);
            java.sql.Date sqldate = new java.sql.Date(d.getTime());
            System.out.println(sqldate);

            Time h = valueOf(heure);
            java.sql.Time sqlheure = new java.sql.Time(h.getTime());
            System.out.println(sqlheure);

            String requete = "UPDATE rdv SET idPatient = ?,idrdv = ?,date = ?, heure = ?,objet=? WHERE idPatient =" + idPatient + " and  idrdv =" + idrdv;

            connexion = connectBD();
            PreparedStatement st = connexion.prepareStatement(requete);

            st.setInt(1, idPatient);
            st.setInt(2, idrdv);
            st.setDate(3, sqldate);
            st.setTime(4, sqlheure);
            st.setString(5, obj);

            st.execute();
            System.out.println("rdv modifie");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimer_rdv() throws Exception {
        try {
            String requete = "DELETE FROM rdv WHERE idPatient =" + idPatient + " and idrdv = " + idrdv;
            connexion = connectBD();
            Statement st = connexion.createStatement();
            st.executeUpdate(requete);
            System.out.println("rdv supprime");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<rdv>  rechercher_date(LocalDate dateR) throws Exception {

        ArrayList<rdv> liste = new ArrayList <rdv>();
        try {

            Date d = Date.valueOf(dateR);
            java.sql.Date sqldate = new java.sql.Date(d.getTime());
            System.out.println("sqldate = "+sqldate);

            String requete = "SELECT * FROM rdv WHERE date = ?";
            connexion = connectBD();

            PreparedStatement st = connexion.prepareStatement(requete);
            st.setDate(1, sqldate);

            ResultSet rst = st.executeQuery();
            System.out.println(rst.getRow());
            while(rst.next()){

                LocalTime h = rst.getTime("heure").toLocalTime();

                rdv r = new rdv(rst.getInt("idPatient"),rst.getInt("idrdv"),LocalDate.now(),h,rst.getString("objet"));
                liste.add(r);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return liste;
    }

    public ArrayList<rdv>  rechercherR() throws Exception {

        ArrayList<rdv> liste = new ArrayList <rdv>();
        try {

            String requete = "SELECT * FROM rdv";
            connexion = connectBD();

            PreparedStatement st = connexion.prepareStatement(requete);
            ResultSet rst = st.executeQuery();

            while(rst.next()){

                LocalTime h = rst.getTime("heure").toLocalTime();
                LocalDate d = rst.getDate("date").toLocalDate();

                rdv r = new rdv(rst.getInt("idPatient"),rst.getInt("idrdv"),d,h,rst.getString("objet"));
                liste.add(r);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return liste;
    }

    public ArrayList<rdv> rechercherNomPrenom(String nom ,String prenom){
        ArrayList<rdv> liste = new ArrayList <rdv>();
        try {

            String requete = "SELECT * FROM patient, rdv where nom='"+nom+"' and prenom ='"+prenom+"' and patient.idPatient = rdv.idPatient";

            connexion = connectBD();

            PreparedStatement st = connexion.prepareStatement(requete);
            ResultSet rst = st.executeQuery();

            while(rst.next()){

                LocalTime h = rst.getTime("heure").toLocalTime();
                LocalDate d = rst.getDate("date").toLocalDate();

                rdv r = new rdv(rst.getInt("idPatient"),rst.getInt("idrdv"),d,h,rst.getString("objet"));
                liste.add(r);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    public int exister_rdv(){
        ArrayList<rdv> liste = new ArrayList <rdv>();
        try {

            Date d = Date.valueOf(this.date);
            java.sql.Date sqldate = new java.sql.Date(d.getTime());
            System.out.println("sqldate = "+sqldate);

            Time h = valueOf(this.heure);
            java.sql.Time sqlheure = new java.sql.Time(h.getTime());
            System.out.println("sqlheure = "+sqlheure);

            Time h2 = valueOf(this.heure.minusMinutes(30));
            java.sql.Time sqlheure2 = new java.sql.Time(h2.getTime());
            System.out.println("sqlheure = "+sqlheure2);


            String requete = "SELECT * FROM rdv WHERE date = ? and heure between ? and ?";
            connexion = connectBD();

            PreparedStatement st = connexion.prepareStatement(requete);
            st.setDate(1, sqldate);
            st.setTime(2, sqlheure2);
            st.setTime(3, sqlheure);

            ResultSet rst = st.executeQuery();
            rst.last();
            return rst.getRow();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }return 0;
    }
}
