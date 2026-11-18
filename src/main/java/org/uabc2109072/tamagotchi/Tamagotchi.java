package org.uabc2109072.tamagotchi;


public abstract class Tamagotchi implements TamagotchiActions {
    protected String nombre;
    protected int salud;
    protected int energia;
    protected int felicidad;
    protected int hambre;
    protected boolean durmiendo = false;
    protected boolean vivo = true;
    protected int alimentos=2;
    protected int medicinas=2;
    protected double puntos=10.0;


    public Tamagotchi(String nombre,int energia,int hambre,int felicidad,int salud){
        this.energia=energia;
        this.hambre=hambre;
        this.felicidad=felicidad;
        this.nombre=nombre;
        this.salud=salud;
    }

    public Tamagotchi(){
        this.energia=100;
        this.hambre=100;
        this.felicidad=100;
        this.nombre="Miles";
        this.salud=100;
    }

    public abstract void mostrarCaracteristicas();
    public abstract void comportamientoEspecifico();

    public String getNombre()    { return nombre; }
    public String getTipo()      { return getClass().getSimpleName(); }
    public boolean isVivo()      { return vivo; }
    public boolean isDurmiendo() { return durmiendo; }

    public void despertar() {
        durmiendo=false;
        System.out.println(nombre +" se desperto.");
    }

    private int limitar(int valor) {
        return Math.max(0, Math.min(100, valor));
    }

    //mantiene los valores entre 0 y 100
    public void corregirLimites() {
        energia = limitar(energia);
        hambre = limitar(hambre);
        felicidad = limitar(felicidad);
        salud = limitar(salud);
    }

    //se llama despues de cada accion
    public void pasarTiempo() {
        if (!vivo) return;
        hambre -= 3;
        felicidad -= 2;
        if (durmiendo) {
            energia += 8;
        } else {
            energia -= 2;
        }
        corregirLimites();

        if (hambre == 0 || energia == 0) {
            salud = limitar(salud - 5);
        }
        if (salud == 0) {
            vivo = false;
            morir();
        }
    }

    public void Inventario(int alimentos, int medicinas,double puntos){
        this.alimentos=alimentos;
        this.medicinas=medicinas;
        this.puntos=puntos;
    }

    public String getEstadoAnimo() {
        if (!vivo) return "ha fallecido";
        if (hambre < 25 || energia < 20 || felicidad < 25 || salud < 30) return "se siente mal";
        if (hambre < 50 || energia < 40 || felicidad < 50) return "necesita atencion";
        return "se siente bien";
    }

    public void mostrarEstado() {
        corregirLimites();
        System.out.println("----------------------------------");
        System.out.println(getTipo() + ": " + nombre);
        System.out.println("Hambre): " + hambre);
        System.out.println("Energia:         " + energia);
        System.out.println("Felicidad:       " + felicidad);
        System.out.println("Salud:           " + salud);
        System.out.println("Durmiendo:       " + (durmiendo ? "sí" : "no"));
        System.out.println("Estado de animo: " + nombre + " " + getEstadoAnimo() + ".");
        mostrarCaracteristicas();
        System.out.println("----------------------------------");
    }

}
