package almeida.murilo.Windows;

import almeida.murilo.Calculos.Calculos;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.DialogWindow;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogBuilder;
import com.googlecode.lanterna.gui2.menu.MenuItem;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

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
        MessageDialog errWindow = new MessageDialogBuilder().setTitle("Erro").setText("Valores inválidos!").build();
        Panel ph = new Panel();
        InputFilter textFilter = new InputFilter() {
            @Override
            public boolean onInput(Interactable interactable, KeyStroke keyStroke) {
                try{
                    return !keyStroke.getKeyType().equals(KeyType.Character) || keyStroke.getCharacter().equals('.') || Character.isDigit(keyStroke.getCharacter());
                }catch(Exception e){
                    System.out.println("Erro" + e.getMessage());
                }
                return false;
            }
        };
        TextBox inc = new TextBox().setInputFilter(textFilter);
        TextBox larg = new TextBox().setInputFilter(textFilter);
        Label lblResult = new Label("");

        ph.addComponent(new Label("Calculadora de telhados!"));
        ph.addComponent(new Label("Digite a inclinação:"));
        ph.addComponent(inc);
        ph.addComponent(new Label("Digite a largura do telhado:"));
        ph.addComponent(larg);

        ph.addComponent(6, new Label("Resultado:"));
        ph.addComponent(7, lblResult);
        ph.addComponent(5, new MenuItem("Calcular", ()->{
            BigDecimal incVal = new BigDecimal(inc.getTextOrDefault("0"));
            BigDecimal larVal = new BigDecimal(larg.getTextOrDefault("0"));

            try{
                if(incVal.compareTo(BigDecimal.ZERO) == 0 || larVal.compareTo(BigDecimal.ZERO) == 0){
                    gui.addWindow(errWindow).setActiveWindow(errWindow).updateScreen();
                }else {
                    lblResult.setText(Calculos.calcTelhados(incVal, larVal).toString());
                }
            }catch(Exception e){
                System.out.println("Erro" + e.getMessage());
            }
        }));

        ph.addComponent(8, new MenuItem("Fechar", ()->{
            this.gui.getActiveWindow().close();
            this.gui.getWindows().stream().findFirst().ifPresent(w -> w.setVisible(true));
        }));

        window.setComponent(ph);
        return window;

    }



}
