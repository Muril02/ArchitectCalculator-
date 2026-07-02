package almeida.murilo.Windows;

import almeida.murilo.Calculos.Calculos;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.menu.MenuItem;
import jdk.internal.util.xml.impl.Input;
import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Windows {

    private final MultiWindowTextGUI gui;

    public Windows(MultiWindowTextGUI GUI){
        this.gui = GUI;
    }

    public BasicWindow startupWindow(){
        BasicWindow window = new BasicWindow();
        Panel ph = new Panel();

        ph.addComponent(new Label("Seja bem vindo ao Architect Calculator!"));
        ph.addComponent(new AnimatedLabel("/").addFrame("|").addFrame("\\").addFrame("-").startAnimation(100));
        ph.addComponent(2, new Label("Escolha a opção que deseja"));
        ph.addComponent(1, new EmptySpace());
        ph.addComponent(new MenuItem("Calcular telhado", () -> {
            try{
                BasicWindow w = calcTelhadoWindow(this.gui);
                this.gui.getActiveWindow().setVisible(false);
                this.gui.addWindow(w).updateScreen();
            } catch (Exception e) {
                System.out.println("Erro" + e.getMessage());
            }
        }));

        window.setComponent(ph);
        return window;
    }

    public BasicWindow calcTelhadoWindow(MultiWindowTextGUI gui){
        BasicWindow window = new BasicWindow();
        Panel ph = new Panel();
        TextBox inc = new TextBox();
        TextBox larg = new TextBox();
        Label lblResult = new Label("");

        ph.addComponent(new Label("Calculadora de telhados!"));
        ph.addComponent(new Label("Digite a inclinação:"));
        ph.addComponent(inc);
        ph.addComponent(new Label("Digite a largura do telhado:"));
        ph.addComponent(larg);

        ph.addComponent(6, new Label("Resultado:"));
        ph.addComponent(7, lblResult);
        ph.addComponent(5, new MenuItem("Calcular", ()->{
            BigDecimal incVal = new BigDecimal(inc.getText());
            BigDecimal larVal = new BigDecimal(larg.getText());

            lblResult.setText(Calculos.calcTelhados(incVal, larVal).toString());

        }));

        ph.addComponent(8, new MenuItem("Fechar", ()->{
            this.gui.getActiveWindow().close();
            this.gui.getWindows().stream().findFirst().ifPresent(w -> w.setVisible(true));
        }));

        window.setComponent(ph);
        return window;

    }



}
