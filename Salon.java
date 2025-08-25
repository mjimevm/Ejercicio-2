public class Salon {
    private int numero;
    private int capacidad;
    private boolean disponible;
    private double costoHora;
    private String tipo;

    public Salon(int numero, int capacidad, boolean disponible, double costoHora, String tipo) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.disponible = disponible;
        this.costoHora = costoHora;
        this.tipo = tipo;
    }
    public int getNumero() {
        return numero;
    }
    public int getCapacidad() {
        return capacidad;
    }
    public boolean getDisponible() {
        return disponible;
    }
    public double getCostoHora() {
        return costoHora;
    }
    public String getTipo() {
        return tipo;
    }
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }


}