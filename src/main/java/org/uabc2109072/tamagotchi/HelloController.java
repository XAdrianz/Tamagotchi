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
    @FXML private TextArea txtInventario;
    @FXML private TextArea txtColeccion;
    @FXML private Button btnDormir;

    private Tamagotchi actual;
    private Coleccion coleccion = new Coleccion();
    private Inventario inventario = new Inventario();

    @FXML
    public void initialize() {
        cmbTipo.getItems().addAll("Perro", "Gato", "Cuyo", "Erizo");
        cmbTipo.getSelectionModel().selectFirst();
        cmbAlimento.getItems().addAll(Alimento.values());
        cmbAlimento.getSelectionModel().selectFirst();
        cmbJuego.getItems().addAll(Juego.values());
        cmbJuego.getSelectionModel().selectFirst();

        // cada 5 segundos pasa el tiempo para todas las mascotas
        Timeline reloj = new Timeline(new KeyFrame(Duration.seconds(5), e -> {
            coleccion.pasarTiempo();
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
            default -> nueva = new Erizo(nombre);
        }
        coleccion.agregar(nueva);
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
        if (!hayMascota()) return;
        Alimento a = cmbAlimento.getValue();
        if (!actual.estaVivo() || actual.estaDurmiendo()) {
            mostrarMensaje(actual.comer(a));
            return;
        }
        if (inventario.usarAlimento(a)) {
            mostrarMensaje(actual.comer(a));
        } else {
            mostrarMensaje("No te queda " + a);
        }
    }

    @FXML
    protected void onJugarClick() {
        if (!hayMascota()) return;
        String mensaje = actual.jugar(cmbJuego.getValue());
        int ganado = actual.cobrarRecompensa();
        if (ganado > 0) {
            inventario.ganarPuntos(ganado);
            mensaje += " (+" + ganado + " " + actual.nombreMoneda() + ")";
        }
        mostrarMensaje(mensaje);
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
        if (!hayMascota()) return;
        if (!actual.estaVivo()) {
            mostrarMensaje(actual.getNombre() + " ha fallecido");
            return;
        }
        if (inventario.usarMedicina()) {
            mostrarMensaje(actual.sanar(20));
        } else {
            mostrarMensaje("No te quedan medicinas");
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

    @FXML
    protected void onComprarAlimentoClick() {
        Alimento a = cmbAlimento.getValue();
        if (inventario.comprarAlimento(a)) {
            mostrarMensaje("Compraste " + a + " (-" + inventario.getPrecio(a) + ")");
        } else {
            mostrarMensaje("No te alcanza, " + a + " cuesta " + inventario.getPrecio(a));
        }
    }

    @FXML
    protected void onComprarMedicinaClick() {
        if (inventario.comprarMedicina()) {
            mostrarMensaje("Compraste una medicina (-" + inventario.getPrecioMedicina() + ")");
        } else {
            mostrarMensaje("No te alcanza, la medicina cuesta " + inventario.getPrecioMedicina());
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
        String moneda = (actual == null) ? "puntos" : actual.nombreMoneda();
        txtInventario.setText(inventario.mostrar(moneda) + "\n\n" + inventario.mostrarTienda(moneda));
        txtColeccion.setText(coleccion.listaJuguetes());

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