package org.uabc2109072.tamagotchi;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

public class HelloController {

    @FXML private TextField txtNombre;
    @FXML private ComboBox<String> cmbTipo;
    @FXML private ComboBox<Tamagotchi> cmbMascotas;
    @FXML private ComboBox<Alimento> cmbAlimento;
    @FXML private ComboBox<Juego> cmbJuego;
    @FXML private ProgressBar barHambre;
    @FXML private ProgressBar barEnergia;
    @FXML private ProgressBar barFelicidad;
    @FXML private ProgressBar barSalud;
    @FXML private Label lblEstado;
    @FXML private Label lblMensaje;
    @FXML private TextArea txtInfo;
    @FXML private Button btnDormir;

    private Tamagotchi actual;

    @FXML
    public void initialize() {
        cmbTipo.getItems().addAll("Perro", "Gato", "Cuyo", "Muñeca");
        cmbTipo.getSelectionModel().selectFirst();
        cmbAlimento.getItems().addAll(Alimento.values());
        cmbAlimento.getSelectionModel().selectFirst();
        cmbJuego.getItems().addAll(Juego.values());
        cmbJuego.getSelectionModel().selectFirst();

        // cada 5 segundos pasa el tiempo para todas las mascotas
        Timeline reloj = new Timeline(new KeyFrame(Duration.seconds(5), e -> {
            for (Tamagotchi t : cmbMascotas.getItems()) {
                t.pasarTiempo();
            }
            actualizarVista();
        }));
        reloj.setCycleCount(Animation.INDEFINITE);
        reloj.play();

        actualizarVista();
    }

    @FXML
    protected void onCrearClick() {
        String nombre = txtNombre.getText().trim();
        if (nombre.isEmpty()) {
            lblMensaje.setText("Escribe un nombre para tu mascota");
            return;
        }
        Tamagotchi nueva;
        switch (cmbTipo.getValue()) {
            case "Perro" -> nueva = new Perro(nombre);
            case "Gato" -> nueva = new Gato(nombre);
            case "Cuyo" -> nueva = new Cuyo(nombre);
            default -> nueva = new Muñeca(nombre);
        }
        cmbMascotas.getItems().add(nueva);
        cmbMascotas.setValue(nueva);   // esto llama a onSeleccionarClick
        txtNombre.clear();
        lblMensaje.setText("Has creado a " + nueva);
    }

    @FXML
    protected void onSeleccionarClick() {
        actual = cmbMascotas.getValue();
        actualizarVista();
    }

    @FXML
    protected void onAlimentarClick() {
        if (hayMascota()) {
            mostrarMensaje(actual.comer(cmbAlimento.getValue()));
        }
    }

    @FXML
    protected void onJugarClick() {
        if (hayMascota()) {
            mostrarMensaje(actual.jugar(cmbJuego.getValue()));
        }
    }

    @FXML
    protected void onDormirClick() {
        if (hayMascota()) {
            if (actual.estaDurmiendo()) {
                mostrarMensaje(actual.despertar());
            } else {
                mostrarMensaje(actual.dormir(4));
            }
        }
    }

    @FXML
    protected void onSanarClick() {
        if (hayMascota()) {
            mostrarMensaje(actual.sanar(20));
        }
    }

    @FXML
    protected void onEspecialClick() {
        if (hayMascota()) {
            if (actual.estaVivo() && !actual.estaDurmiendo()) {
                mostrarMensaje(actual.comportamientoEspecifico());
            } else {
                mostrarMensaje(actual.getNombre() + " no puede hacerlo ahora");
            }
        }
    }

    private boolean hayMascota() {
        if (actual == null) {
            lblMensaje.setText("Primero crea o selecciona una mascota");
            return false;
        }
        return true;
    }

    private void mostrarMensaje(String mensaje) {
        lblMensaje.setText(mensaje);
        actualizarVista();
    }

    private void actualizarVista() {
        if (actual == null) {
            lblEstado.setText("Crea una mascota para empezar");
            barHambre.setProgress(0);
            barEnergia.setProgress(0);
            barFelicidad.setProgress(0);
            barSalud.setProgress(0);
            txtInfo.clear();
            return;
        }
        barHambre.setProgress(actual.getHambre() / 100.0);
        barEnergia.setProgress(actual.getEnergia() / 100.0);
        barFelicidad.setProgress(actual.getFelicidad() / 100.0);
        barSalud.setProgress(actual.getSalud() / 100.0);
        lblEstado.setText(actual.getNombre() + " " + actual.getEstadoAnimo());
        txtInfo.setText(actual.mostrarEstado());
        if (actual.estaDurmiendo()) {
            btnDormir.setText("Despertar");
        } else {
            btnDormir.setText("Dormir");
        }
    }
}