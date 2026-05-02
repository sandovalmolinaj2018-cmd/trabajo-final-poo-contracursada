package Controlador;

import javax.swing.JButton;

import Vista.VistaCombate;

public class ControladorCombate {
    
    private VistaCombate vistaCombate;
    private Teclado teclado;

    //VALORES DE LA CLASSE TECLADO
    //private long ultimoInput = 0;
    //private final long cooldown = 120;

    private int indiceSeleccion = 0; 
    private boolean enMenuAtaques = false; 
    private boolean enBotonVolver = false; 

    public ControladorCombate(VistaCombate vistaCombate) {
        this.vistaCombate = vistaCombate;
        this.teclado = teclado;
        this.inicializarEventos();
        this.actualizarGraficos();
    }

    private void actualizarGraficos() {
        if (enBotonVolver) {
            vistaCombate.resaltarBoton(vistaCombate.getBtnVolver());
            return;
        }

        JButton botonActivo = null;
        if (!enMenuAtaques) {
            switch (indiceSeleccion) {
                case 0 -> botonActivo = vistaCombate.getBtnLuchar();
                case 1 -> botonActivo = vistaCombate.getBtnHuir();
            }
        } else {
            switch (indiceSeleccion) {
                case 0 -> botonActivo = vistaCombate.getBtnAtk1();
                case 1 -> botonActivo = vistaCombate.getBtnAtk2();
                case 2 -> botonActivo = vistaCombate.getBtnAtk3();
                case 3 -> botonActivo = vistaCombate.getBtnAtk4();
            }
        }
        vistaCombate.resaltarBoton(botonActivo);
    }

    private void ejecutarAccion() {
        if (enBotonVolver) {
            // Acción Volver
            vistaCombate.mostrarMenuPrincipal();
            vistaCombate.setMensajeCajaTexto("¿Qué debería hacer el JUGADOR?");
            enMenuAtaques = false;
            enBotonVolver = false;
            indiceSeleccion = 0; 
            actualizarGraficos();
            return;
        }

        if (!enMenuAtaques) {
            switch (indiceSeleccion) {
                case 0 -> { // LUCHAR
                    vistaCombate.mostrarMenuAtaques();
                    vistaCombate.setMensajeCajaTexto("¡Elige tu ataque!");
                    enMenuAtaques = true;
                    indiceSeleccion = 0;
                    actualizarGraficos();
                }
                case 1 -> vistaCombate.setMensajeCajaTexto("Mochila vacía.");
                case 2 -> vistaCombate.setMensajeCajaTexto("Equipo listo.");
                case 3 -> vistaCombate.setMensajeCajaTexto("¡No puedes huir!");
            }
        } else {
            // Acciones de Ataque
            switch (indiceSeleccion) {
                case 0 -> realizarAtaque("HABILIDAD 1");
                case 1 -> realizarAtaque("HABILIDAD 2");
                case 2 -> realizarAtaque("HABILIDAD 3");
                case 3 -> realizarAtaque("HABILIDAD 4");
            }
        }
    }
    
    private void inicializarEventos() {
        vistaCombate.getBtnLuchar().addActionListener(e -> {
            vistaCombate.mostrarMenuAtaques();
            vistaCombate.setMensajeCajaTexto("¡Elige tu ataque!");
        });

        vistaCombate.getBtnVolver().addActionListener(e -> {
            vistaCombate.mostrarMenuPrincipal();
            vistaCombate.setMensajeCajaTexto("¿Qué debería hacer tu héroe?");
        });
        vistaCombate.getBtnHuir().addActionListener(e -> {
            vistaCombate.setMensajeCajaTexto("¡No puedes huir de esta batalla de jefe!");
        });
        vistaCombate.getBtnAtk1().addActionListener(e -> realizarAtaque("HABILIDAD 1"));
        vistaCombate.getBtnAtk2().addActionListener(e -> realizarAtaque("HABILIDAD 2"));
        vistaCombate.getBtnAtk3().addActionListener(e -> realizarAtaque("HABILIDAD 3"));
        vistaCombate.getBtnAtk4().addActionListener(e -> realizarAtaque("HABILIDAD 4"));
    }

    private void realizarAtaque(String nombreAtaque) {
        vistaCombate.setMensajeCajaTexto("JUGADOR usó " + nombreAtaque + "!");
        vistaCombate.mostrarMenuPrincipal();
        enMenuAtaques = false;
        indiceSeleccion = 0;
        actualizarGraficos();
    }
    public void update() {}
}