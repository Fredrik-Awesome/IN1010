import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import java.io.*;
import java.util.*;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.*;

public class GUI extends Application {

    private Text status;
    private GridPane rutenett = new GridPane();
    private boolean[][] losning = null;
    private Stage stage;

    private final String tittel = "Oblig 7 - Johasko";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage teater) {

        teater.setTitle(tittel);

        Button knapp = new Button("Velg fil");
        knapp.setOnAction(e -> velgFil());

        HBox velgFilKnapp = new HBox(10);
        velgFilKnapp.setAlignment(Pos.CENTER);
        velgFilKnapp.getChildren().add(knapp);

        status = new Text();
        status.setFont(new Font(20));
        status.setFill(Color.BLACK);

        VBox v = new VBox();
        v.setPadding(new Insets(25, 25, 25, 25));
        v.setAlignment(Pos.CENTER);
        v.getChildren().addAll(velgFilKnapp, status, rutenett);

        Scene scene = new Scene(v, 500, 300);
        teater.setScene(scene);
        teater.show();

        stage = teater;

    }

    public void velgFil() {

        // For aa kunne velge andre labyrinter underveis
        rutenett.getChildren().clear();

        FileChooser fileChooser = new FileChooser();
        File valgtFil = fileChooser.showOpenDialog(null);

        if (valgtFil != null) {
            status.setText("Valgt fil: " + valgtFil.getName() + "\n");
        }
        else {
            status.setText("Ingen fil valgt.");
        }

        Labyrint lab = null;
        try {
            lab = Labyrint.lesFraFil(valgtFil);
        } catch (FileNotFoundException fnfe) {
            System.out.println("Kunne ikke lese fra fil");
        }

        int y = lab.hentRader();
        int x = lab.hentKolonner();

        Rute[][] rn = lab.hentRutenett();

        for (int k = 0; k < rn.length; k++) {
            for (int r = 0; r < rn[k].length; r++) {
                if (rn[k][r].tilTegn() == '.') {
                    final int kol = r;
                    final int rad = k;

                    final Labyrint lagLab = lab;

                    Rectangle rute = new Rectangle(28, 28);
                    rute.setFill(Color.WHITE);
                    rute.setStroke(Color.BLACK);
                    rute.setStrokeWidth(0.5);

                    stage.setHeight(x * 28 + 224);
                    stage.setWidth(y * 28 + 112);

                    rute.setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent me) {
                            Liste<String> utveier = lagLab.finnUtveiFra(kol, rad);
                            // for(String s: utveier){
                            //     System.out.println(s);
                            // }

                            if (utveier.stoerrelse() == 0) {
                                status.setText("Ingen utveier fra " + kol + ", " + rad);
                            } else {
                                status.setText("Viser utvei fra " + kol + ", " + rad);

                                boolean[][] losning = losningStringTilTabell(utveier.hent(0), x, y);

                                for (int i = 0; i < losning.length; i++) {
                                    for (int j = 0; j < losning[i].length; j++) {
                                        if (losning[i][j]) {
                                            Rectangle rute = new Rectangle(28, 28);
                                            rute.setFill(Color.GREEN);
                                            rute.setStroke(Color.BLACK);
                                            rute.setStrokeWidth(0.5);
                                            rutenett.add(rute, j, i);
                                        }

                                    }

                                }
                            }
                        }
                    });
                    rutenett.add(rute, r, k);
                } else {
                    Rectangle rute = new Rectangle(0, 0, 28, 28);
                    rute.setStroke(Color.BLACK);
                    rute.setStrokeWidth(0.5);
                    rutenett.add(rute, r, k);
                }

            }
        }

        rutenett.setPadding(new Insets(0, 25, 25, 25));
        rutenett.setAlignment(Pos.BOTTOM_CENTER);
    }



    /**
 * Konverterer losning-String fra oblig 5 til en boolean[][]-representasjon
 * av losningstien.
 * @param losningString String-representasjon av utveien
 * @param bredde        bredde til labyrinten
 * @param hoyde         hoyde til labyrinten
 * @return              2D-representasjon av rutene der true indikerer at
 *                      ruten er en del av utveien.
 */
static boolean[][] losningStringTilTabell(String losningString, int bredde, int hoyde) {
    boolean[][] losning = new boolean[hoyde][bredde];
    java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\(([0-9]+),([0-9]+)\\)");
    java.util.regex.Matcher m = p.matcher(losningString.replaceAll("\\s",""));
    while (m.find()) {
        int x = Integer.parseInt(m.group(1));
        int y = Integer.parseInt(m.group(2));
        losning[y][x] = true;
    }
    return losning;
}
}
