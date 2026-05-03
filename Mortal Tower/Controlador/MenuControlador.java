package Controlador;

import Modelo.MenuModelo;
import Vista.VistaCombate;

public class MenuControlador {
    private SonidoControlador musica = new SonidoControlador();
    private SonidoControlador efectos = new SonidoControlador();
    private long ultimoInput = 0;
    private final long cooldown = 120;
    private MenuModelo menuModelo;
    private Teclado teclado;
    private Game game;
    private ControladorCombate controladorCombate;

    public MenuControlador(MenuModelo menuModelo, Teclado teclado, Game game) {
        this.menuModelo = menuModelo;
        this.teclado = teclado;
        this.game = game;

        game.musica.loop(0);
    }

    public void update() {

        long now = System.currentTimeMillis();

        if (teclado.up && now - ultimoInput > cooldown) {
            game.efectos.play(1);
            System.out.println("arriba");
            menuModelo.arriba();
            teclado.up = false;

            ultimoInput = now;
        }

        if (teclado.down && now - ultimoInput > cooldown) {
            game.efectos.play(1);
            System.out.println("abajo");
            menuModelo.abajo();
            teclado.down = false;

            ultimoInput = now;
        }

        if (teclado.select && now - ultimoInput > cooldown) {
            System.out.println("enter");
            ejecutar();
            teclado.select = false;

            ultimoInput = now;
        }
    }

   private void ejecutar() {

        switch (menuModelo.getSeleccion()) {

            case 0 -> {
                game.efectos.play(2);
                game.musica.stop(0);
                System.out.println("Nueva partida");
                VistaCombate vistaCombate = new VistaCombate();
                ControladorCombate controladorCombate = new ControladorCombate(vistaCombate);a.
                game.setLayout(new java.awt.BorderLayout());
                game.removeAll(); 
                game.add(vistaCombate, java.awt.BorderLayout.CENTER);
                game.revalidate();
                game.repaint();
            }

            case 1 -> {
                System.out.println("Opciones");
            }

            case 2 -> System.exit(0);
        }
    }
}

