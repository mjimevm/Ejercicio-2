public class Evento {
    private String nombre;
    private String encargado;
    private String tipoEvento;
    private String fecha;
    private String horario;
    private int invitados;
    private boolean depositoPagado;
    private Salon salonAsignado;

    public Evento(String nombre, String encargado, String tipoEvento, String fecha, String horario, int invitados, boolean depositoPagado) {
        this.nombre = nombre;
        this.encargado = encargado;
        this.tipoEvento = tipoEvento;
        this.fecha = fecha;
        this.horario = horario;
        this.invitados = invitados;
        this.depositoPagado = depositoPagado;
        this.salonAsignado = null;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEncargado() {
        return encargado;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHorario() {
        return horario;
    }

    public int getInvitados() {
        return invitados;
    }

    public boolean getDepositoPagado() {
        return depositoPagado;
    }

    public Salon getSalonAsignado() {
        return salonAsignado;
    }

    public void reservarSalon(Salon salon) {
        this.salonAsignado = salon;
        salon.setDisponible(false);
    }
}

