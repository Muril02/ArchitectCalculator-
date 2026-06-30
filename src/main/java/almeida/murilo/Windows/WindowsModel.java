package almeida.murilo.Windows;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.menu.MenuItem;

public class WindowsModel {

    public BasicWindow startupWindow(){
        WindowsController wm = new WindowsController();
        BasicWindow window = new BasicWindow();
        Panel ph = new Panel();

        ph.addComponent(new Label("Seja bem vindo ao Architect Calculator!"));
        ph.addComponent(new AnimatedLabel("/").addFrame("|").addFrame("\\").addFrame("-").startAnimation(100));
        ph.addComponent(2, new Label("Escolha a opção que deseja"));
        ph.addComponent(1, new EmptySpace());
        ph.addComponent(new MenuItem("Calcular telhado", wm::getCalcTelhado));
        ph.addComponent(new MenuItem("asdas"));
        ph.addComponent(new MenuItem("asdasdasd"));

        window.setComponent(ph);
        return window;
    }

    public BasicWindow calcTelhadoWindow(){
        BasicWindow window = new BasicWindow();
        Panel ph = new Panel();

        ph.addComponent(new Label("Calculadora de telhados!"));
        ph.addComponent(new Label("Digite qualquer coisa!"));
        ph.addComponent(new TextBox());

        window.setComponent(ph);
        return window;

    }



}
