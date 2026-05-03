package Vista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class VistaCombate extends JPanel {
    private JPanel panelCombate;
    private JPanel panelControles;
    private JPanel panelMenuInferior;
    private CardLayout cardLayout;
    private JLabel lblCajaTexto;  
    private JButton btnVolver;
    // Botones del menú principal
    private JButton btnBasico;
    private JButton btnLuchar;
    private JButton btnHuir;
    // Botones de habilidades
    private JButton btnAtk1;
    private JButton btnAtk2;
    private JButton btnAtk3;
    private JButton btnAtk4;

    //TODO: Faltaria Agregar sprites a los personajes
    //Hacer un fondo y guardarlo en assets.

    public VistaCombate() {
        this.setLayout(new BorderLayout());
        crearPanelCombate();
        crearPanelControles();
        this.add(panelCombate, BorderLayout.CENTER);
        this.add(panelControles, BorderLayout.SOUTH);
    }

    private void crearPanelCombate() {
        panelCombate = new JPanel();
        panelCombate.setLayout(null); 
        panelCombate.setBackground(new Color(60, 60, 65)); 

        JPanel infoJugador = crearCajaInfo("JUGADOR",1, 100, true);
        JPanel infoRival = crearCajaInfo("ENEMIGO", 2, 100, false);

        JLabel spriteJugador = new JLabel("JUGADOR (DER)", SwingConstants.CENTER);
        spriteJugador.setOpaque(true);
        spriteJugador.setBackground(new Color(173, 216, 230)); 

        JLabel spriteRival = new JLabel("RIVAL (IZQ)", SwingConstants.CENTER);
        spriteRival.setOpaque(true);
        spriteRival.setBackground(new Color(255, 182, 193)); 

        panelCombate.add(infoJugador);
        panelCombate.add(infoRival);
        panelCombate.add(spriteJugador);
        panelCombate.add(spriteRival);
        panelCombate.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent evt) {
                int width = panelCombate.getWidth();
                int height = panelCombate.getHeight();

                int anchoSprite = (int)(width * 0.20);
                int altoSprite = (int)(height * 0.40);
                int posY = (int)(height * 0.50); 
                
                int posXJugador = (int)(width * 0.15);
                int posXRival = (int)(width * 0.65);

                spriteJugador.setBounds(posXJugador, posY, anchoSprite, altoSprite);
                spriteRival.setBounds(posXRival, posY, anchoSprite, altoSprite);
                
                int anchoCaja = (int)(width * 0.35);
                int altoCaja = (int)(height * 0.15);
                int posYHUD = (int)(height * 0.05); 

                infoJugador.setBounds((int)(width * 0.05), posYHUD, anchoCaja, altoCaja);
                infoRival.setBounds((int)(width * 0.60), posYHUD, anchoCaja, altoCaja);
            }
        });
    }

    private JPanel crearCajaInfo(String nombre, int nivel, int hpPorcentaje, boolean esJugador) {
        JPanel caja = new JPanel();
        caja.setLayout(null);
        caja.setBackground(new Color(30, 30, 30));
        caja.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        JLabel lblNombre = new JLabel(nombre + " (Lv" + nivel + ")");
        lblNombre.setForeground(Color.WHITE);
        
        JProgressBar barra = new JProgressBar(0, 100);
        barra.setValue(hpPorcentaje);
        barra.setForeground(new Color(50, 205, 50));
        barra.setBackground(Color.DARK_GRAY);
        barra.setBorderPainted(false);

        caja.add(lblNombre);
        caja.add(barra);
        caja.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent evt) {
                int width = caja.getWidth();
                int height = caja.getHeight();

                int barraWidth = (int)(width * 0.90);
                int barraHeight = (int)(height * 0.20);
                int barraX = (int)(width * 0.05); 
                int barraY = (int)(height * 0.55); 
                
                barra.setBounds(barraX, barraY, barraWidth, barraHeight);

                int labelWidth = (int)(width * 0.80);
                int labelHeight = (int)(height * 0.30);
                int labelY = (int)(height * 0.15); 

                if (esJugador) {
                    lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
                    lblNombre.setBounds((int)(width * 0.05), labelY, labelWidth, labelHeight);
                } else {
                    lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
                    lblNombre.setBounds((int)(width * 0.15), labelY, labelWidth, labelHeight);
                }
                
                int fontSize = Math.max(12, height / 4); 
                lblNombre.setFont(new Font("Monospaced", Font.BOLD, fontSize));
            }
        });
        return caja;
    }

    private void crearPanelControles() {
        panelControles = new JPanel(new BorderLayout());
        panelControles.setPreferredSize(new Dimension(1080, 216));
        panelControles.setBackground(Color.BLACK);

        JPanel panelTopControles = new JPanel(new BorderLayout());
        panelTopControles.setBackground(Color.BLACK);
        panelTopControles.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

        JPanel panelTexto = new JPanel(new BorderLayout());
        panelTexto.setBackground(Color.DARK_GRAY);
        panelTexto.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE, 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        lblCajaTexto = new JLabel("¿Qué debería hacer el JUGADOR?");
        lblCajaTexto.setFont(new Font("Monospaced", Font.BOLD, 28));
        lblCajaTexto.setForeground(Color.WHITE);
        panelTexto.add(lblCajaTexto, BorderLayout.CENTER);

        JPanel panelVolver = new JPanel(new BorderLayout());
        panelVolver.setBackground(Color.BLACK);
        panelVolver.setPreferredSize(new Dimension(270, 0)); 
        panelVolver.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); 

        btnVolver = estilizarBoton("VOLVER");
        btnVolver.setForeground(new Color(255, 100, 100)); 
        btnVolver.setVisible(false); 

        panelVolver.add(btnVolver, BorderLayout.CENTER);

        panelTopControles.add(panelTexto, BorderLayout.CENTER);
        panelTopControles.add(panelVolver, BorderLayout.EAST);

        cardLayout = new CardLayout();
        panelMenuInferior = new JPanel(cardLayout);
        panelMenuInferior.setPreferredSize(new Dimension(1080, 80)); 

        JPanel menuPrincipal = new JPanel(new GridLayout(1, 3, 10, 0)); 
        menuPrincipal.setBackground(Color.BLACK);
        menuPrincipal.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        btnBasico = estilizarBoton("LUCHAR");
        btnLuchar = estilizarBoton("HABILIDAD");
        btnHuir = estilizarBoton("HUIR");

        menuPrincipal.add(btnBasico);
        menuPrincipal.add(btnLuchar);
        menuPrincipal.add(btnHuir);

        JPanel menuAtaques = new JPanel(new GridLayout(1, 4, 10, 0));
        menuAtaques.setBackground(Color.BLACK);
        menuAtaques.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        btnAtk1 = estilizarBoton("HABILIDAD 1");
        btnAtk2 = estilizarBoton("HABILIDAD 2");
        btnAtk3 = estilizarBoton("HABILIDAD 3");
        btnAtk4 = estilizarBoton("HABILIDAD 4");
        
        menuAtaques.add(btnAtk1);
        menuAtaques.add(btnAtk2);
        menuAtaques.add(btnAtk3);
        menuAtaques.add(btnAtk4); 
        
        panelMenuInferior.add(menuPrincipal, "PRINCIPAL");
        panelMenuInferior.add(menuAtaques, "ATAQUES");
        panelControles.add(panelTopControles, BorderLayout.CENTER);
        panelControles.add(panelMenuInferior, BorderLayout.SOUTH);
    }

    private JButton estilizarBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Monospaced", Font.BOLD, 22));
        btn.setBackground(new Color(40, 40, 40));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(80, 80, 80));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(40, 40, 40));
            }
        });
        return btn;
    }

    public void resaltarBoton(JButton botonSeleccionado) {
        Color colorNormal = new Color(40, 40, 40);
        btnBasico.setBackground(colorNormal);
        btnLuchar.setBackground(colorNormal);
        btnHuir.setBackground(colorNormal);
        btnAtk1.setBackground(colorNormal);
        btnAtk2.setBackground(colorNormal);
        btnAtk3.setBackground(colorNormal);
        btnAtk4.setBackground(colorNormal);
        btnVolver.setBackground(colorNormal);
        
        if (botonSeleccionado != null) {
            botonSeleccionado.setBackground(new Color(100, 100, 100));
        }
    }
    
    public void setMensajeCajaTexto(String mensaje) {
        lblCajaTexto.setText(mensaje);
    }

    public void mostrarMenuAtaques() {
        cardLayout.show(panelMenuInferior, "ATAQUES");
        btnVolver.setVisible(true);
    }

    public void mostrarMenuPrincipal() {
        cardLayout.show(panelMenuInferior, "PRINCIPAL");
        btnVolver.setVisible(false);
    }
    public JButton getBtnVolver() { return btnVolver; }
    public JButton getBtnBasico() { return btnBasico; }
    public JButton getBtnLuchar() { return btnLuchar; }  
    public JButton getBtnHuir() { return btnHuir; }
    public JButton getBtnAtk1() { return btnAtk1; }
    public JButton getBtnAtk2() { return btnAtk2; }
    public JButton getBtnAtk3() { return btnAtk3; }
    public JButton getBtnAtk4() { return btnAtk4; }
}