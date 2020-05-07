package metier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static metier.connexionbdd.connectBD;
import static metier.connexionbdd.connexion;

public class patient{

    private int idPatient;
    private String nom;
    private String prenom;
    private String adresse;
    private int telephone;
    private String mail;
    private String info_medicale;

    //constructeur

    public patient(int idPatient,String nom, String prenom, String adresse, int telephone, String mail, String info_medicale) {
        this.idPatient=idPatient;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.mail = mail;
        this.info_medicale = info_medicale;
    }

    public patient() {
    }

    //getters, setters

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getInfo_medicale() {
        return info_medicale;
    }

    public void setInfo_medicale(String info_medicale) {
        this.info_medicale = info_medicale;
    }

//ajouter toutes les coordonées d'un patient
    public void ajouter_patient () throws Exception {
        try {

            String requete = "INSERT INTO patient VALUES ("+idPatient+", '"+nom+"', '"+prenom+"' , '"+adresse+"' , '"+telephone+"' , '"+mail+"' , '"+info_medicale+"')";
            connexion = connectBD();
            Statement st = connexion.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//supprimer un patient par son idPatient
    public void supprimer_patient() throws Exception {
        try {
            String requete = "DELETE FROM patient WHERE idPatient =" + idPatient;
            connexion = connectBD();
            Statement st = connexion.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

 //modifier un patient par son idPatient
    public void modifier_patient() throws Exception {
        try {
            String requete = "UPDATE patient SET idPatient = "+idPatient+",nom= '"+nom+"',prenom = '"+prenom+"' , adresse= '"+adresse+"' , telephone = '"+telephone+"' , email ='"+mail+"' , information_medicale = '"+info_medicale+"' WHERE id_Patient=" + idPatient;
            connexion = connectBD();
            Statement st = connexion.createStatement();
            st.executeUpdate(requete);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

//chercher un patient par son nom et prenom
    public ArrayList<patient> chercher_patient(String n, String p) throws Exception {

        ArrayList<patient> pat = new ArrayList <patient>();
        try {
            String requete = "SELECT * FROM patient WHERE nom ='"+n+"' and prenom = '"+p+"'";
            connexion = connectBD();
            Statement st = connexion.createStatement();
            ResultSet rst = st.executeQuery(requete);
            while(rst.next()){

                String a = rst.getString("adresse");
                int t = rst.getInt("telephone");
                String m = rst.getString("email");
                String i = rst.getString("information_medicale");

                patient patientElem = new patient(rst.getInt("idPatient"),n,p,a,t,m,i);
                pat.add(patientElem);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } return pat;
    }


    public patient chercher_patientID(int id) throws Exception {

        patient pat = null;
        try {
            String requete = "SELECT * FROM patient WHERE idPatient ="+id;
            connexion = connectBD();
            Statement st = connexion.createStatement();
            ResultSet rst = st.executeQuery(requete);


            rst.last();
            if (rst.getRow()!=0) {
                String n = rst.getString("nom");
                String p = rst.getString("prenom");
                String a = rst.getString("adresse");
                int t = rst.getInt("telephone");
                String m = rst.getString("email");
                String i = rst.getString("information_medicale");
                System.out.println(p + " " + n);
                pat = new patient(id, n, p, a, t, m, i);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } return pat;
    }


}