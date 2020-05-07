package gui;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import metier.patient;
import metier.rdv;
import impression.Impression;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class principale_ctrl implements Initializable {

    @FXML
    private AnchorPane anchorPRINCIPAL;

    @FXML
    private Label labdate;

    @FXML
    private VBox rdvJOUR;

    @FXML
    private AnchorPane anchorAFFICHAGE;

    @FXML
    private JFXButton ajouterrdv;

    @FXML
    private JFXButton ajouterpatient;


    @FXML
    private JFXButton chercherpatient;

    @FXML
    private JFXButton chercherRDV;

    @FXML
    private JFXButton chercher_jour;


    @FXML
    private JFXButton img;

    @FXML
    private Label erreur;

    @FXML
    private Label titre;
/////
    @FXML
    void ajouter_patient() throws Exception {

        titre.setText("Ajouter un patient");
        //les labels
        anchorAFFICHAGE.getChildren().clear();
        erreur.setText("");
        ajouterpatient.setDisable(true);
        ajouterrdv.setDisable(false);
        chercher_jour.setDisable(true);
        chercherpatient.setDisable(false);
        chercherRDV.setDisable(false);


        JFXTextField id = new JFXTextField();
        JFXTextField n = new JFXTextField();
        JFXTextField p = new JFXTextField();
        JFXTextField a = new JFXTextField();
        JFXTextField t = new JFXTextField();
        JFXTextField m = new JFXTextField();
        JFXTextField i = new JFXTextField();

        id.setPromptText("Le numero du dossier");
        n.setPromptText("Le nom du patient");
        p.setPromptText("Le prenom du patient");
        a.setPromptText("L'adresse du patient");
        t.setPromptText("Le numero du téléphone");
        m.setPromptText("L'email du patient");
        i.setPromptText("Des informations médicales");

        id.setLabelFloat(true);
        n.setLabelFloat(true);
        p.setLabelFloat(true);
        a.setLabelFloat(true);
        t.setLabelFloat(true);
        m.setLabelFloat(true);
        i.setLabelFloat(true);


        id.setLayoutX(20);
        n.setLayoutX(20);
        p.setLayoutX(20);
        a.setLayoutX(20);
        t.setLayoutX(20);
        m.setLayoutX(20);
        i.setLayoutX(20);

        id.setLayoutY(20);
        n.setLayoutY(70);
        p.setLayoutY(120);
        a.setLayoutY(170);
        t.setLayoutY(220);
        m.setLayoutY(270);
        i.setLayoutY(320);


        //Bouton ajouter
        JFXButton ajouter = new JFXButton("Ajouter");
        ajouter.setLayoutY(340);
        ajouter.setLayoutX(270);
        ajouter.setStyle("-fx-background-color: #44a6bf; -fx-text-fill: black;");

        anchorAFFICHAGE.getChildren().addAll(id,n,p,a,t,m,i,ajouter);
        ajouter.setOnAction((event)-> {
            if (id.getText().equals("")) {
                erreur.setText("Veuillez introduire le numero du dossier");}
            else if (! id.getText().matches("[0-9]+")){
                    erreur.setText("Le numero du dossier n'est pas valide");
            }else if (n.getText().equals("")) {
                erreur.setText("Veuillez introduire le nom");
            } else if (p.getText().equals("")) {
                erreur.setText("Veuillez introduire le prenom");
            } else if (t.getText().equals("")) {
                erreur.setText("Veuillez introduire le numero de telephone");
            }else if (! t.getText().matches("[0-9]+")){
                erreur.setText("Le numero de téléphone n'est pas valide");
            }else if (i.getText().equals("")) {
                erreur.setText("Veuillez introduire des informations médicales");
            }else{
                patient pat = new patient(Integer.parseInt(id.getText()),n.getText(),p.getText(),a.getText(),Integer.parseInt(t.getText()),m.getText(),i.getText());
                try {
                    pat.ajouter_patient();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Rendez-vous");
                    alert.setHeaderText(null);
                    alert.setContentText("Le patient "+ n.getText() + " "+ p.getText()+" est ajouté avec succés ");
                    alert.showAndWait();


                    etat_nrml();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }
/////
    public void etat_nrml(){

        titre.setText("Gérer vos rendez-vous facilement");
        anchorAFFICHAGE.getChildren().clear();
        erreur.setText("");
        ajouterpatient.setDisable(false);
        ajouterrdv.setDisable(false);
        chercher_jour.setDisable(false);
        chercherpatient.setDisable(false);
        chercherRDV.setDisable(false);
    }

    /////
    @FXML
    void ajouter_rdv() {

        anchorAFFICHAGE.getChildren().clear();
        titre.setText("Ajouter un rendez-vous");
        erreur.setText("");
        ajouterpatient.setDisable(false);
        ajouterrdv.setDisable(true);
        chercher_jour.setDisable(false);
        chercherpatient.setDisable(false);
        chercherRDV.setDisable(false);


        JFXTextField idP = new JFXTextField();
        JFXTextField idR = new JFXTextField();
        JFXDatePicker d = new JFXDatePicker();
        JFXTimePicker h = new JFXTimePicker();
        JFXTextField o = new JFXTextField();

        idP.setPromptText("Le numero du dossier");
        idR.setPromptText("Le numero du rendez-vouz");
        d.setPromptText("La date du rendez-vous");
        h.setPromptText("L'heure du rendez-vous");
        o.setPromptText("Une description");

        idP.setLabelFloat(true);
        idR.setLabelFloat(true);
        o.setLabelFloat(true);


        idP.setLayoutX(20);
        idR.setLayoutX(20);
        d.setLayoutX(20);
        h.setLayoutX(20);
        o.setLayoutX(20);

        idP.setLayoutY(20);
        idR.setLayoutY(70);
        d.setLayoutY(120);
        h.setLayoutY(170);
        o.setLayoutY(220);

        //Bouton ajouter
        JFXButton ajouter = new JFXButton("Ajouter");
        ajouter.setLayoutX(300);
        ajouter.setLayoutY(270);
        ajouter.setStyle("-fx-background-color: #44a6bf; -fx-text-fill: black;");


        patient patient = new patient();
        anchorAFFICHAGE.getChildren().addAll(idP,idR,d,h,o,ajouter);

        ajouter.setOnAction((ActionEvent event) -> {
            if (idP.getText().equals("")) {
                erreur.setText("Veuillez introduire le numero du dossier");
            }else if (! idP.getText().matches("[0-9]+")){
                    erreur.setText("Le numero du dossier n'est pas valide");
            } else if (idR.getText().equals("")) {
                erreur.setText("Veuillez introduire le numero du rendez-vous");
            }else if (! idR.getText().matches("[0-9]+")){
            erreur.setText("Le numero du rendez-vous n'est pas valide");
            } else if (d.getValue() == null) {
                erreur.setText("Veuillez introduire la date");
            } else if (h.getValue() == null){
                erreur.setText("Veuillez introduire l'heure");
            }else {
                try {
                    if(patient.chercher_patientID(Integer.parseInt(idP.getText())).equals(null)){
                        erreur.setText("Vous devez associer le rendez-vous à un patient qui existe deja!");
                    }
                    else{
                        rdv r = new rdv(Integer.parseInt(idP.getText()),Integer.parseInt(idR.getText()),d.getValue(),h.getValue(),o.getText());
                        try {
                            if (r.exister_rdv() != 0){

                                erreur.setText("La date et l'heure sont prises");
                            }else{ r.ajouter_rdv();

                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Rendez-vous");
                                alert.setHeaderText(null);
                                alert.setContentText("Le rendez-vous est ajouté avec succés ");
                                alert.showAndWait();

                                miseAJ();

                            etat_nrml();}

                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    erreur.setText("Vous devez associer le rendez-vous à un patient qui existe deja!");

                    e.printStackTrace();
                }
            }
    });
    }
//////////////
    @FXML
    void chercher_patient() {

        anchorAFFICHAGE.getChildren().clear();
        erreur.setText("");
        titre.setText("Chercher un patient par son nom et son prénom");
        ajouterpatient.setDisable(false);
        ajouterrdv.setDisable(false);
        chercher_jour.setDisable(false);
        chercherpatient.setDisable(true);
        chercherRDV.setDisable(false);

        JFXTextField nom = new JFXTextField();
        nom.setPromptText("nom");
        nom.setLayoutX(20);
        nom.setLayoutY(50);

        JFXTextField prenom = new JFXTextField();
        prenom.setPromptText("prenom");
        prenom.setLayoutX(175);
        prenom.setLayoutY(50);

        JFXButton chercher = new JFXButton("chercher");
        chercher.setLayoutX(350);
        chercher.setLayoutY(50);

        chercher.setStyle("-fx-background-color: #44a6bf; -fx-text-fill: black;");


        nom.setLabelFloat(true);
        prenom.setLabelFloat(true);

        VBox vbox = new VBox();
        vbox.setLayoutX(20);
        vbox.setLayoutY(100);

        anchorAFFICHAGE.getChildren().addAll(nom,prenom,chercher,vbox);

        chercher.setOnAction((event)-> {
            vbox.getChildren().clear();
            erreur.setText("");
            if (nom.getText().equals("")) {
                erreur.setText("Introduire le nom");
            }else if (prenom.getText().equals("")) {
                erreur.setText("Introduire le prenom");
            } else {
                try {
                    patient p = new patient();
                    ArrayList<patient> liste = p.chercher_patient(nom.getText(),prenom.getText());

                    //iterable
                    for (patient p2 : liste) {
                        JFXButton btn = new JFXButton(p2.getIdPatient()+": " + p2.getNom() + " " + p2.getPrenom());
                        vbox.getChildren().add(btn);
                    }

                } catch (Exception e) {
                    erreur.setText("Verifiez si vous avez inséré tous les données");
                    e.printStackTrace();
                }

            }

    });
    }


    @FXML
    void chercher_rdv() {

        titre.setText("Consulter les rendez-vous");
        anchorAFFICHAGE.getChildren().clear();
        erreur.setText("");
        ajouterpatient.setDisable(false);
        ajouterrdv.setDisable(false);
        chercher_jour.setDisable(false);
        chercherpatient.setDisable(false);
        chercherRDV.setDisable(true);

        JFXTabPane tabPane = new JFXTabPane();

        Tab tab1 = new Tab("Tous les rendez-vous");
        Tab tab2 = new Tab("chercher un rendez-vous");

        VBox vBox1 = new VBox();
        AnchorPane anch2 = new AnchorPane();

        ScrollPane s1 = new ScrollPane();
        s1.setMinSize(462,325);


        //tab1: afficher tous les rendez-vous
        try {
            s1.setContent(vBox1);
            vBox1.getChildren().clear();
            rdv r = new rdv();
            ArrayList<rdv> liste_rdv = r.rechercherR();

            //iterable
            for (rdv r2 : liste_rdv) {
                JFXButton btn = new JFXButton("RDV: "+r2.getIdrdv()+" du patient: "+r2.getIdPatient());
                vBox1.getChildren().add(btn);
                btn.setOnAction((ActionEvent event) ->{
                    try {
                        detail_rdv(r2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }catch (Exception e) {
            e.printStackTrace();}

        tab1.setContent(s1);
        //tab2 : chercher un rendez-vous

        ScrollPane s2 = new ScrollPane();
        s2.setMinSize(462,325);

        JFXTextField nom = new JFXTextField();
        nom.setPromptText("Le nom du patient");
        nom.setLayoutX(20);
        nom.setLayoutY(20);

        JFXTextField prenom = new JFXTextField();
        prenom.setPromptText("Le prenom du patient");
        prenom.setLayoutX(175);
        prenom.setLayoutY(20);

        nom.setLabelFloat(true);
        prenom.setLabelFloat(true);

        JFXButton chercher = new JFXButton("chercher");
        chercher.setLayoutX(350);
        chercher.setLayoutY(20);
        chercher.setStyle("-fx-background-color: #44a6bf; -fx-text-fill: black;");


        VBox vbox = new VBox();

        anch2.getChildren().clear();
        anch2.getChildren().addAll(nom,prenom,chercher);
        vbox.getChildren().clear();
        vbox.getChildren().add(anch2);

        chercher.setOnAction((ActionEvent event) -> {
            try {
                anch2.getChildren().clear();
                anch2.getChildren().addAll(nom,prenom,chercher);
                vbox.getChildren().clear();
                vbox.getChildren().add(anch2);
                rdv r = new rdv();
                ArrayList<rdv> liste_rdv = r.rechercherNomPrenom(nom.getText() , prenom.getText());

                //iterable
                for (rdv r2 : liste_rdv) {
                    JFXButton btn = new JFXButton("RDV: "+r2.getIdrdv()+" du patient: "+r2.getIdPatient());
                    vbox.getChildren().add(btn);
                    btn.setOnAction((ActionEvent event2) ->{
                        try {
                            detail_rdv(r2);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }} catch (Exception e) {
                e.printStackTrace();
            }
        });
        s2.setContent(vbox);
        tab2.setContent(s2);

        tabPane.getTabs().add(tab1);
        tabPane.getTabs().add(tab2);

        anchorAFFICHAGE.getChildren().add(tabPane);
    }

    //////
    @FXML
    void chercher_rdv_jour() throws Exception {

        anchorAFFICHAGE.getChildren().clear();
        erreur.setText("");
        titre.setText("Chercher un rendez-vous par sa date");
        ajouterpatient.setDisable(false);
        ajouterrdv.setDisable(false);
        chercher_jour.setDisable(true);
        chercherpatient.setDisable(false);
        chercherRDV.setDisable(false);

        JFXDatePicker datePicker = new JFXDatePicker();
        datePicker.setPromptText("Selectionner une date");
        datePicker.setLayoutX(20);
        datePicker.setLayoutY(50);

        JFXButton chercher = new JFXButton("chercher");
        chercher.setLayoutX(200);
        chercher.setLayoutY(50);

        chercher.setStyle("-fx-background-color: #44a6bf; -fx-text-fill: black;");


        VBox vbox = new VBox();
        vbox.setLayoutX(20);
        vbox.setLayoutY(100);

        anchorAFFICHAGE.getChildren().addAll(datePicker,chercher,vbox);
        chercher.setOnAction((event)-> {

            vbox.getChildren().clear();

            if (datePicker.getValue() == null) {
                erreur.setText("Sélectionnez une date");
            } else {
                try {
                    rdv r = new rdv();
                    ArrayList<rdv> liste_rdv = r.rechercher_date(datePicker.getValue());

                    //iterable
                    for (rdv r2 : liste_rdv) {
                        System.out.println(r2.toString());
                        JFXButton btn = new JFXButton("RDV: " + r2.getIdrdv() + " du patient: " + r2.getIdPatient());
                        vbox.getChildren().add(btn);
                        btn.setOnAction((ActionEvent event2) ->{
                            try {
                                detail_rdv(r2);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


        });

    }

    void detail_rdv(rdv r) throws Exception {

        etat_nrml();

        titre.setText("les détails du rendez-vous");

        Label idP = new Label();
        Label idR = new Label();
        Label pnom = new Label();
        JFXDatePicker d = new JFXDatePicker();
        JFXTimePicker h = new JFXTimePicker();
        JFXTextField o = new JFXTextField();

        idP.setLayoutX(20);
        idR.setLayoutX(20);
        pnom.setLayoutX(20);
        d.setLayoutX(20);
        h.setLayoutX(20);
        o.setLayoutX(20);

        idP.setLayoutY(50);
        pnom.setLayoutY(80);
        idR.setLayoutY(110);
        d.setLayoutY(140);
        h.setLayoutY(170);
        o.setLayoutY(200);

        patient pat = new patient();
        pat = pat.chercher_patientID(r.getIdPatient());

        idP.setText("Numero du dossier :"+String.valueOf(r.getIdPatient()));
        idR.setText("Numero du rendez-vous :"+String.valueOf(r.getIdrdv()));
        pnom.setText("Mr(Mme): "+pat.getNom()+" "+pat.getPrenom());
        d.setValue(r.getDate());
        h.setValue(r.getHeure());
        o.setText("Une description"+r.getObj());

        JFXButton modifier = new JFXButton("Modifier");
        JFXButton  supprimer= new JFXButton("Supprimer");
        JFXButton  imprimer= new JFXButton("Imprimer");

        JFXButton  retour= new JFXButton("Retourner");

        modifier.setLayoutX(350);
        modifier.setLayoutY(100);

        supprimer.setLayoutX(350);
        supprimer.setLayoutY(150);

        imprimer.setLayoutX(350);
        imprimer.setLayoutY(200);

        retour.setLayoutX(350);
        retour.setLayoutY(250);

        modifier.setStyle("-fx-background-color: #44a6bf; -fx-text-fill: black;");
        supprimer.setStyle("-fx-background-color: #44a6bf; -fx-text-fill: black;");
        imprimer.setStyle("-fx-background-color: #44a6bf; -fx-text-fill: black;");
        retour.setStyle("-fx-background-color: #44a6bf; -fx-text-fill: black;");



        anchorAFFICHAGE.getChildren().addAll(idP,idR,pnom,d,h,o,modifier,supprimer,imprimer, retour);

/////////////////////////////////////////////////////////////////////////////////////////////
        modifier.setOnAction((event)-> {
            try {
                r.setDate(d.getValue());
                r.setHeure(h.getValue());
                r.setObj(o.getText());
                if (r.exister_rdv() != 0){
                    erreur.setText("L'heure et la date sont  prises");
                }else{
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Rendez-vous");
                    alert.setHeaderText(null);
                    alert.setContentText("Vous voulez modifier ce rendez vous?");
                    alert.showAndWait();

                    r.modifier_rdv();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Rendez-vous");
                    alert.setHeaderText(null);
                    alert.setContentText("Le rendez-vous est modifié");
                    alert.showAndWait();



                    etat_nrml();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

///////////////////////////////////////////////////////////////////////////////////////////////
        supprimer.setOnAction((event)-> {
            try {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Rendez-vous");
                alert.setHeaderText(null);
                alert.setContentText("Vous voulez supprimer ce rendez vous?");
                alert.showAndWait();

                r.supprimer_rdv();


                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Rendez-vous");
                alert.setHeaderText(null);
                alert.setContentText("Le rendez-vous est supprimé");
                alert.showAndWait();

                miseAJ();

                etat_nrml();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////
        imprimer.setOnAction((event)-> {
            String document = null;
            try {
                document = document_a_imprimer(r);
                etat_nrml();
            } catch (Exception e) {
                e.printStackTrace();
            }
            printToPrinter(document);
        });
///////////////////////////////////////////////////////////////////////////////////////////////

        retour.setOnAction((event -> {
               chercher_rdv();
        }));
    }

    private String document_a_imprimer(rdv r) throws Exception {

        String doc=null;

        doc ="\t\t\t\t\t\t\t Rendez-vous"+"\n";
        doc ="\t le : "+LocalDate.now()+"\n";

        patient pat = new patient();

        pat = pat.chercher_patientID(r.getIdPatient());
        
        doc += "le numéro du dossier:        "+r.getIdPatient()+"\n";
        doc += "Mr (Mme) :        "+pat.getNom()+" "+ pat.getPrenom()+"\n";
        doc += "le numéro du rendez-vous:    "+r.getIdrdv()+"\n";
        doc += "la date du rendez-vous :     "+r.getDate()+"\n";
        doc += "l'heure du rendez-vous :     "+r.getHeure()+"\n";
        doc += "Informations supplémentaire: "+r.getObj()+"\n";

        return doc;
    }


    private void printToPrinter(String printData)
    {

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new Impression.OutputPrinter(printData));
        boolean doPrint = job.printDialog();
        if (doPrint)
        {
            try
            {
                job.print();
            }
            catch (PrinterException e)
            {
                // Print job did not complete.
            }
        }

    }

    @FXML
    void miseAJ() {
        try {

            rdvJOUR.getChildren().clear();
            rdv r = new rdv();
            ArrayList<rdv> liste_rdv = r.rechercher_date(LocalDate.now());

            //iterable
            for (rdv r2 : liste_rdv) {
                System.out.println(r2.toString());
                System.out.println("RDV: "+r2.getIdrdv()+" du patient: "+r2.getIdPatient());
                JFXButton btn = new JFXButton("RDV: "+r2.getIdrdv()+" du patient: "+r2.getIdPatient());
                btn.setOnAction((ActionEvent event) ->{
                    try {
                        detail_rdv(r2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                rdvJOUR.getChildren().add(btn);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //////
    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chercher_rdv();


       // ImageView quitter = new ImageView(new Image(getClass().getResourceAsStream("index.png")));
       // quitter.setFitHeight(40);
       // quitter.setFitWidth(50);
       // img.setGraphic(quitter);
        //afficher les rdv d'aujourd'hui dans le scrollpane rdvJOUR

        //afficher la date
        labdate.setText(LocalDate.now().toString());

        //recuperer les rendez-vous d'aujourd'hui et les afficher

        try {
            rdv r = new rdv();
            ArrayList<rdv> liste_rdv = r.rechercher_date(LocalDate.now());

            //iterable
            for (rdv r2 : liste_rdv) {
                JFXButton btn = new JFXButton("RDV: "+r2.getIdrdv()+" du patient: "+r2.getIdPatient());
                btn.setOnAction((ActionEvent event) ->{
                    try {
                        detail_rdv(r2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                rdvJOUR.getChildren().add(btn);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
