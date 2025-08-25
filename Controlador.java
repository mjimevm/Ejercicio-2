class Controlador {
    private Salon[] salones;
    private Evento[] eventos;
    private Evento[] waitlist;
    public Controlador() {
        salones = new Salon[20];
        eventos = new Evento[20];
        waitlist = new Evento[20];
    }
    public void agregarSalon(Salon salon) {
        for (int i = 0; i < salones.length; i++) {
            if (salones[i] == null) {
                salones[i] = salon;
                break;
            }
        }
    }
    public void registrarEvento(Evento evento) {
        for (int i = 0; i < eventos.length; i++) {
            if (eventos[i] == null) {
                eventos[i] = evento;
                break;
            }
        }
    }
    
    public Salon[] getSalones() {
        return salones;
    }
    public Evento[] getEventos() {
        return eventos;
    }
    public Evento[] getWaitlist() {
        return waitlist;
    }
}