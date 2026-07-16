package almeida.murilo.Windows;

import almeida.murilo.Calculos.Calculos;
import almeida.murilo.Enums.InclinacaoTelha;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.ListSelectDialog;
import com.googlecode.lanterna.gui2.dialogs.ListSelectDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogBuilder;
import com.googlecode.lanterna.gui2.menu.MenuItem;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

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
        ph.addComponent(new MenuItem("1 - Calcular telhado", () -> {
            try{
                BasicWindow win = calcTelhadoWindow(this.gui);
                this.gui.getActiveWindow().setVisible(false);
                this.gui.addWindow(win).updateScreen();
            } catch (Exception e) {
                System.out.println("Erro" + e.getMessage());
            }
        }));
        ph.addComponent(new MenuItem("2 - Em construção"));
        ph.addComponent(new MenuItem("3 - Em construção"));
        ph.addComponent(new MenuItem("4 - Em construção"));

        window.setComponent(ph);
        return window;
    }


    public BasicWindow calcTelhadoWindow(MultiWindowTextGUI gui){
        BasicWindow window = new BasicWindow();

        MessageDialog errWindow = new MessageDialogBuilder().setTitle("Erro").setText("Valores inválidos!").build();
        errWindow.setHints(Set.of(
                Window.Hint.MENU_POPUP,
                Window.Hint.CENTERED
        ));

        Panel ph = new Panel(new GridLayout(2).setBottomMarginSize(1).setTopMarginSize(1).setHorizontalSpacing(6));
        Panel phL = new Panel();
        Panel phR = new Panel();

        ListSelectDialog<InclinacaoTelha> lstTelhas = new ListSelectDialogBuilder<InclinacaoTelha>().setTitle("Teste").addListItems(
                InclinacaoTelha.values()
        ).setCanCancel(true)
                .setListBoxSize(new TerminalSize(20, 6))
                .setExtraWindowHints(Set.of(
                        Window.Hint.CENTERED
                ))
                .build();


        ph.addComponent(phL);
        ph.addComponent(phR);
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

        TextBox larg = new TextBox().setInputFilter(textFilter);
        TextBox inc = new TextBox().setInputFilter(textFilter);

        AtomicReference<InclinacaoTelha> incTelha = new AtomicReference<>();

        Label lblResult = new Label("").addStyle(SGR.BOLD);
        Label lblTelha = new Label("").addStyle(SGR.ITALIC);
        EmptySpace eptSp = new EmptySpace();

        phL.addComponent(new Label("Calculadora de telhados!"));
        phL.addComponent(new EmptySpace());

        phL.addComponent(new Label("Selecione a sua telha"));
        phL.addComponent(new MenuItem("Selecionar", ()->{
            incTelha.set((lstTelhas.showDialog(gui)));
            if(incTelha.get() != null){
                lblTelha.setText("Selecionado " + incTelha.get().toString() + "\ninclinação de " + incTelha.get().getMin() + " até " + incTelha.get().getMax());
                phL.addComponent(eptSp);
            }
        }));

        phL.addComponent(lblTelha);


        phL.addComponent(new Label("Digite a inclinação"));
        phL.addComponent(inc);

        phL.addComponent(new EmptySpace());
        phL.addComponent(new Label("Digite a largura do telhado"));
        phL.addComponent(larg);

        phL.addComponent(new EmptySpace());
        phL.addComponent(new MenuItem("Calcular", ()->{

            BigDecimal incVal = new BigDecimal(inc.getTextOrDefault("0"));
            BigDecimal larVal = new BigDecimal(larg.getTextOrDefault("0"));

            if(incTelha.get() == null){
                try{
                    gui.addWindow(errWindow).setActiveWindow(errWindow).updateScreen();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else {
                BigDecimal min = new BigDecimal(incTelha.get().getMin());
                BigDecimal max = new BigDecimal(incTelha.get().getMax());

                try{
                    if(incVal.compareTo(BigDecimal.ZERO) == 0 || larVal.compareTo(BigDecimal.ZERO) == 0){
                        gui.addWindow(errWindow).setActiveWindow(errWindow).updateScreen();
                    }else if(incVal.compareTo(min) < 0 || incVal.compareTo(max) > 0){
                        gui.addWindow(errWindow).setActiveWindow(errWindow).updateScreen();
                    }else{
                        lblResult.setText(Calculos.calcTelhados(incVal, larVal).toString());
                        inc.setText("");
                        larg.setText("");
                    }
                }catch(Exception e){
                    System.out.println("Erro" + e.getMessage());
                }
            }
        }));

        phL.addComponent(new EmptySpace());
        phL.addComponent( new MenuItem("Voltar", ()->{
            this.gui.getActiveWindow().close();
            this.gui.getWindows().stream().findFirst().ifPresent(w -> w.setVisible(true));
        }));

        phR.addComponent(new Label("Resultado"));
        phR.addComponent(lblResult);

        window.setComponent(ph);
        return window;
    }

    public BasicWindow calcDeEscadas(MultiWindowTextGUI gui){
        BasicWindow window = new BasicWindow();

        window.setHints(Set.of(
                Window.Hint.CENTERED
        ));

        MessageDialog errWindow = new MessageDialogBuilder().setTitle("Erro").setText("Valores inválidos!").build();
        errWindow.setHints(Set.of(
                Window.Hint.MENU_POPUP,
                Window.Hint.CENTERED
        ));

        Panel ph = new Panel(new GridLayout(2).setBottomMarginSize(1).setTopMarginSize(1).setHorizontalSpacing(6));
        Panel phL = new Panel();
        Panel phR = new Panel();

        ListSelectDialog<InclinacaoTelha> lstTelhas = new ListSelectDialogBuilder<InclinacaoTelha>().setTitle("Teste").addListItems(
                        InclinacaoTelha.values()
                ).setCanCancel(true)
                .setListBoxSize(new TerminalSize(20, 6))
                .setExtraWindowHints(Set.of(
                        Window.Hint.CENTERED
                ))
                .build();


        ph.addComponent(phL);
        ph.addComponent(phR);
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

        TextBox larg = new TextBox().setInputFilter(textFilter);
        TextBox inc = new TextBox().setInputFilter(textFilter);

        AtomicReference<InclinacaoTelha> incTelha = new AtomicReference<>();

        Label lblResult = new Label("").addStyle(SGR.BOLD);
        Label lblTelha = new Label("").addStyle(SGR.ITALIC);

        phL.addComponent(new Label("Calculadora de telhados!"));
        phL.addComponent(new EmptySpace());

        phL.addComponent(new Label("Selecione a sua telha"));
        phL.addComponent(new MenuItem("Selecionar", ()->{
            incTelha.set((lstTelhas.showDialog(gui)));
            if(incTelha.get() != null){
                lblTelha.setText("Selecionado " + incTelha.get().toString() + "\ninclinação de " + incTelha.get().getMin() + " até " + incTelha.get().getMax());
            }
        }));

        phL.addComponent(lblTelha);
        phL.addComponent(new EmptySpace());

        phL.addComponent(new Label("Digite a inclinação"));
        phL.addComponent(inc);

        phL.addComponent(new EmptySpace());
        phL.addComponent(new Label("Digite a largura do telhado"));
        phL.addComponent(larg);

        phL.addComponent(new EmptySpace());
        phL.addComponent(new MenuItem("Calcular", ()->{
            BigDecimal incVal = new BigDecimal(inc.getTextOrDefault("0"));
            BigDecimal larVal = new BigDecimal(larg.getTextOrDefault("0"));
            BigDecimal min = new BigDecimal(incTelha.get().getMin());
            BigDecimal max = new BigDecimal(incTelha.get().getMax());

            try{
                if(incVal.compareTo(BigDecimal.ZERO) == 0 || larVal.compareTo(BigDecimal.ZERO) == 0){
                    gui.addWindow(errWindow).setActiveWindow(errWindow).updateScreen();
                }else if(incVal.compareTo(min) < 0 || incVal.compareTo(max) > 0){
                    gui.addWindow(errWindow).setActiveWindow(errWindow).updateScreen();
                }else{
                    lblResult.setText(Calculos.calcTelhados(incVal, larVal).toString());
                    inc.setText("");

                    larg.setText("");
                }
            }catch(Exception e){
                System.out.println("Erro" + e.getMessage());
            }
        }));

//        phR.addComponent(new MenuItem("Teste", ()->{
//            gui.addWindow(new ListSelectDialogBuilder<String>().setTitle("Teste").addListItem("sasda").build());
//        }));

        phL.addComponent(new EmptySpace());
        phL.addComponent( new MenuItem("Voltar", ()->{
            this.gui.getActiveWindow().close();
            this.gui.getWindows().stream().findFirst().ifPresent(w -> w.setVisible(true));
        }));

        phR.addComponent(new Label("Resultado"));
        phR.addComponent(lblResult);

        window.setComponent(ph);
        return window;
    }



}
