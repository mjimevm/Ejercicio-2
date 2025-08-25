import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("BIENVENIDO A LA GESTIÓN DE SALONES Y EVENTOS");
        Controlador controlador = new Controlador();
        Scanner teclado = new Scanner(System.in);

        System.out.print("¿Cuántos salones desea agregar? ");
        int respuestaEncargado = teclado.nextInt();
        while (respuestaEncargado < 1) {
            System.out.print("Se requieren al menos 4 salones. Ingrese nuevamente: ");
            respuestaEncargado = teclado.nextInt();
        }

        for (int i = 0; i < respuestaEncargado; i++) {
            System.out.println("\nIngrese los datos del salón " + (i + 1) + ":");
            System.out.print("Número: ");
            int numero = teclado.nextInt();
            boolean numeroRepetido = false;
            for (Salon salon : controlador.getSalones()) {
                if (salon != null && numero == salon.getNumero()) {
                    numeroRepetido = true;
                    break;
                }
            }
            while (numeroRepetido == true) {
                System.out.print("El número de salón ya existe. Ingrese un número diferente: ");
                numero = teclado.nextInt();
                numeroRepetido = false;
                for (Salon salon : controlador.getSalones()) {
                    if (salon != null && numero == salon.getNumero()) {
                        numeroRepetido = true;
                        break;
                    }
                }
            }

            System.out.print("Capacidad: ");
            int capacidad = teclado.nextInt();
            System.out.print("Costo por hora: ");
            double costoHora = teclado.nextDouble();

            System.out.print("Tipo (Grande, Mediano, Pequeño): ");
            String tipo = teclado.next().toLowerCase();

            while (!tipo.equals("grande") && !tipo.equals("mediano") && !tipo.equals("pequeno") && !tipo.equals("pequeño")) {
                System.out.print("Tipo inválido. Ingrese nuevamente (Grande, Mediano, Pequeño): ");
                tipo = teclado.next().toLowerCase();
            }

            Salon salon = new Salon(numero, capacidad, true, costoHora, tipo);
            controlador.agregarSalon(salon);
            System.out.println("Salón agregado exitosamente.\n");
        }

        System.out.println("BIENVENIDO A LA GESTIÓN DE EVENTOS");
        System.out.println("SALONES REGISTRADOS:");
        for (Salon salon : controlador.getSalones()) {
            if (salon != null) {
                System.out.println("Salón " + salon.getNumero() + " - Capacidad: " + salon.getCapacidad() + " - Tipo: " + salon.getTipo() + " - Costo por hora: " + salon.getCostoHora());
            }
        }
        System.out.print("¿Cuántos eventos desea registrar? ");
        int eventosARegistrar = teclado.nextInt();
        while (eventosARegistrar < 1) {
            System.out.print("Debe registrar al menos un evento. Ingrese nuevamente: ");
            eventosARegistrar = teclado.nextInt();
        }
        teclado.nextLine();
        for (int i = 0; i < eventosARegistrar; i++) {
            System.out.println("\nIngrese los datos del evento " + (i + 1) + ":");
            System.out.print("Nombre del evento: ");
            String nombre = teclado.nextLine();
            System.out.print("Encargado: ");
            String encargado = teclado.nextLine();
            System.out.print("Tipo de evento (VIP, Normal): ");
            String tipoEvento = teclado.nextLine().toLowerCase();
            while (!tipoEvento.equals("vip") && !tipoEvento.equals("normal")) {
                System.out.print("Tipo inválido. Ingrese nuevamente (VIP, Normal): ");
                tipoEvento = teclado.nextLine().toLowerCase();
            }
            System.out.print("Fecha (DD/MM/AAAA): ");
            String fecha = teclado.nextLine();
            System.out.print("Horario (HH:MM - HH:MM): ");
            String horario = teclado.nextLine();
            System.out.print("Número de invitados: ");
            int invitados = teclado.nextInt();
            System.out.print("¿Depósito pagado? (true/false): ");
            boolean depositoPagado = teclado.nextBoolean();
            while (depositoPagado != true && depositoPagado != false) {
                System.out.print("Depósito no pagado. Ingrese nuevamente (true/false): ");
                depositoPagado = teclado.nextBoolean();
            }
            teclado.nextLine();

            Evento evento = new Evento(nombre, encargado, tipoEvento, fecha, horario, invitados, depositoPagado);
            controlador.registrarEvento(evento);
            System.out.println("Evento registrado exitosamente.\n");
        }
        System.out.println("EVENTOS REGISTRADOS:");
        for (Evento evento : controlador.getEventos()) {
            if (evento != null) {
                System.out.println("Evento: " + evento.getNombre() + " - Encargado: " + evento.getEncargado() + " - Tipo: " + evento.getTipoEvento() + " - Fecha: " + evento.getFecha() + " - Horario: " + evento.getHorario() + " - Invitados: " + evento.getInvitados() + " - Depósito pagado: " + evento.getDepositoPagado());
            }
        }
        System.out.println("REGLAS DE ASIGNACIÓN DE SALONES:");
        System.out.println("1. El evento debe tener el depósito pagado.");
        System.out.println("2. El salón debe tener capacidad igual o mayor al número de invitados.");
        System.out.println("3. Los eventos VIP solo pueden asignarse a salones grandes.");
        System.out.println("4. Si no hay salones disponibles que cumplan las reglas, el evento se envía a una lista de espera.\n");

        for (Evento evento : controlador.getEventos()) {
            if (evento != null) {
                boolean cumple = false;
                for (Salon salon : controlador.getSalones()) {
                    if (salon != null) {
                        if (evento.getDepositoPagado() && salon.getCapacidad() >= evento.getInvitados()) {
                            if (evento.getTipoEvento().equals("vip") && salon.getTipo().equals("grande")) {
                                cumple = true;
                                break;
                            } else if (evento.getTipoEvento().equals("normal")) {
                                cumple = true;
                                break;
                            }
                        }
                    }
                }

                if (!cumple) {
                    for (int i = 0; i < controlador.getWaitlist().length; i++) {
                        if (controlador.getWaitlist()[i] == null) {
                            controlador.getWaitlist()[i] = evento;
                            break;
                        }
                        else {
                            System.out.println("La lista de espera está llena");
                        }
                    }
                }
            }
        }
        System.out.println("EVENTOS EN LISTA DE ESPERA:");
        for (Evento evento : controlador.getWaitlist()) {
            if (evento != null) {
                System.out.println("Evento: " + evento.getNombre() + " - Encargado: " + evento.getEncargado() + " - Tipo: " + evento.getTipoEvento() + " - Fecha: " + evento.getFecha() + " - Horario: " + evento.getHorario() + " - Invitados: " + evento.getInvitados() + " - Depósito pagado: " + evento.getDepositoPagado());
            }
            if (evento == null) {
                System.out.println("No hay eventos en lista de espera.");
                break;
            }
        }
        System.out.println("\nGracias por usar el sistema de gestión de salones y eventos.");
    }
}
