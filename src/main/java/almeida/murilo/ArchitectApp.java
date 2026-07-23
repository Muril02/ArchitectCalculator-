package almeida.murilo;

import almeida.murilo.Windows.Windows;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.SimpleTheme;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogBuilder;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.*;

import java.io.IOException;
import java.security.Key;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class ArchitectApp {

    private static Terminal createTerminal(DefaultTerminalFactory factory) throws IOException{

        Terminal term = factory.createTerminal();
        if(term != null){
            return term;
        }else {
            return factory.setPreferTerminalEmulator(true).createTerminal();
        }
    }

    public static void main(String[] args) {

        DefaultTerminalFactory facTer = new DefaultTerminalFactory()
                .setTerminalEmulatorColorConfiguration(TerminalEmulatorColorConfiguration.newInstance(TerminalEmulatorPalette.STANDARD_VGA))
                .setTerminalEmulatorDeviceConfiguration(new TerminalEmulatorDeviceConfiguration(2, 500, TerminalEmulatorDeviceConfiguration.CursorStyle.VERTICAL_BAR, TextColor.ANSI.WHITE_BRIGHT, true));

        try(Screen screen = new TerminalScreen(createTerminal(facTer))) {
            screen.startScreen();

            MultiWindowTextGUI gui = new MultiWindowTextGUI(screen);

            WindowListenerAdapter listener = new WindowListenerAdapter(){
                @Override
                public void onUnhandledInput(Window basePane, KeyStroke keyStroke, AtomicBoolean atomicBoolean){
                    if(keyStroke.getKeyType().equals(KeyType.Escape)){
                        gui.getActiveWindow().close();
                        gui.getWindows().stream().findFirst().ifPresent(w -> w.setVisible(true));
                        atomicBoolean.set(true);
                        System.out.println(keyStroke.getCharacter());
                    }else if(keyStroke.isAltDown() && keyStroke.getCharacter().equals('g')){
                        gui.addWindow(new MessageDialogBuilder().setTitle("").setText("Te amo para sempre Gabi meu amor!").build());
                    }
                }
            };

            Windows wm = new Windows(gui, listener);


            BasicWindow stUp = wm.startupWindow();
            stUp.setHints(Set.of(
                    Window.Hint.CENTERED
            ));

            stUp.addWindowListener(listener);

            System.out.println();


            gui.addWindowAndWait(stUp);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
