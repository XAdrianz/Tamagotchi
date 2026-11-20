package org.uabc2109072.tamagotchi;


public abstract class Tamagotchi implements TamagotchiActions {
    protected String nombre;
    protected int salud;
    protected int energia;
    protected int felicidad;
    protected int hambre;   // 0 = sin hambre, 100 = muriendo de hambre
    protected boolean durmiendo=false;
    protected boolean vivo=true;


    public Tamagotchi(String nombre, int energia, int hambre, int felicidad, int salud) {
        this.energia=energia;
        this.hambre=hambre;
        this.felicidad=felicidad;
        this.nombre=nombre;
        this.salud=salud;
    }

    public Tamagotchi() {
        this.energia=100;
        this.hambre=0;
        this.felicidad=100;
        this.nombre="Miles";
        this.salud=100;
    }

    public abstract String mostrarCaracteristicas();

    public abstract String comportamientoEspecifico();

    public String getNombre() {
        return nombre;
    }

    public int getSalud() {
        return salud;
    }

    public int getEnergia() {
        return energia;
    }

    public int getFelicidad() {
        return felicidad;
    }

    public int getHambre() {
        return hambre;
    }

    public boolean estaVivo() {
        return vivo;
    }

    public boolean estaDurmiendo() {
        return durmiendo;
    }

    public String despertar() {
        durmiendo = false;
        return nombre + " se desperto";
    }

    private int limitar(int valor) {
        return Math.max(0, Math.min(100, valor));
    }

    //mantiene los valores entre 0 y 100
    public void corregirLimites() {
        energia=limitar(energia);
        hambre=limitar(hambre);
        felicidad=limitar(felicidad);
        salud=limitar(salud);
    }

    // regresa un mensaje si el tamagotchi no puede hacer nada, o null si puede
    protected String noPuedeActuar() {
        if (!vivo) return nombre+" ha fallecido";
        if (durmiendo) return nombre+" esta durmiendo";
        return null;
    }

    // igual para todos los tamagotchis, Gato lo sobreescribe
    @Override
    public String dormir(int horasDeSuenio) {
        String bloqueo = noPuedeActuar();
        if (bloqueo != null) return bloqueo;
        durmiendo = true;
        energia += horasDeSuenio * 10;
        hambre += horasDeSuenio * 3;
        corregirLimites();
        return nombre + " dormira una siesta...";
    }

    @Override
    public boolean morir() {
        vivo = false;
        durmiendo = false;
        return vivo;
    }

    @Override
    public String sanar(int medicina) {
        if (!vivo) return nombre+" ha fallecido";
        salud += medicina;
        corregirLimites();
        return "Has sanado a "+nombre;
    }

    // se llama cada cierto tiempo para que cambien las necesidades
    public void pasarTiempo() {
        if (!vivo) return;
        hambre += 2;
        if (!durmiendo) {
            energia -= 2;
            felicidad -= 1;
        }
        if (hambre >= 100 || energia <= 0) salud -= 5;
        corregirLimites();
        if (salud <= 0) morir();
    }

    public Estado getEstado() {
        if (!vivo) return Estado.FALLECIDO;
        if (hambre>75||energia<20||felicidad<25||salud<30) return Estado.MAL;
        if (hambre>50||energia<40||felicidad<50) return Estado.NECESITA_ATENCION;
        return Estado.BIEN;
    }

    public String getEstadoAnimo() {
        return getEstado().getTexto();
    }

    public String mostrarEstado() {
        return "Nombre: " + nombre + " (" + getClass().getSimpleName() + ")\n"
                + "Hambre:    " + hambre + "/100\n"
                + "Energia:   " + energia + "/100\n"
                + "Felicidad: " + felicidad + "/100\n"
                + "Salud:     " + salud + "/100\n"
                + "Durmiendo: " + (durmiendo ? "Si" : "No") + "\n"
                + "Animo: " + nombre + " " + getEstadoAnimo() + "\n"
                + mostrarCaracteristicas();
    }

    @Override
    public String toString() {
        return nombre + " (" + getClass().getSimpleName() + ")";
    }

}
