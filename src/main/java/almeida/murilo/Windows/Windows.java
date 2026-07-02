package almeida.murilo.Windows;

import almeida.murilo.Calculos.Calculos;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.menu.MenuItem;
import org.w3c.dom.Text;

import java.math.BigDecimal;

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
                this.gui.addWindow(w).updateScreen();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }));

        ph.addComponent(new MenuItem("asdas"));
        ph.addComponent(new MenuItem("asdasdasd"));

        window.setComponent(ph);
        return window;
    }

    public BasicWindow calcTelhadoWindow(MultiWindowTextGUI gui){
        BasicWindow window = new BasicWindow();
        Panel ph = new Panel();
        TextBox inc = new TextBox();
        TextBox larg = new TextBox();


        ph.addComponent(new Label("Calculadora de telhados!"));
        ph.addComponent(new Label("Digite a inclinação:"));
        ph.addComponent(inc);
        ph.addComponent(new Label("Digite a largura do telhado:"));
        ph.addComponent(larg);

        BigDecimal incVal = new BigDecimal(inc.getText());
        BigDecimal largVal = new BigDecimal(larg.getText());
        BigDecimal result;



        window.setComponent(ph);
        return window;

    }



}
