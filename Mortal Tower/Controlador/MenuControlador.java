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

        game.musica.loop(0); // música de fondo
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
                
                // 1. Instancias tu vista de combate
                VistaCombate vistaCombate = new VistaCombate();

                ControladorCombate controladorCombate = new ControladorCombate(vistaCombate);

                // 2. Como 'Game' es un JPanel, le configuramos un Layout que 
                // permita que el combate ocupe toda la pantalla.
                game.setLayout(new java.awt.BorderLayout());
                
                // 3. Limpiamos cualquier otra interfaz que pudiera estar pegada en Game
                game.removeAll(); 

                // 4. Agregamos la vista de combate al panel principal Game
                game.add(vistaCombate, java.awt.BorderLayout.CENTER);

                // 5. ¡Obligamos a Swing a redibujar la pantalla con los nuevos componentes!
                game.revalidate();
                game.repaint();

                // 6. Finalmente, cambias el estado lógico para que tu hilo siga trabajando
                // game.setState(new PlayState(game, teclado)); 
            }

            case 1 -> {
                System.out.println("Opciones");
                // game.setOverlay(new OpcioneState(teclado, game));
            }

            case 2 -> System.exit(0);
        }
    }
}