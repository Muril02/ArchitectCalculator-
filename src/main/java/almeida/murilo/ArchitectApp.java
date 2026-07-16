package almeida.murilo;

import almeida.murilo.Windows.Windows;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.SimpleTheme;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.TerminalEmulatorColorConfiguration;
import com.googlecode.lanterna.terminal.swing.TerminalEmulatorDeviceConfiguration;
import com.googlecode.lanterna.terminal.swing.TerminalEmulatorPalette;

import java.security.Key;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class ArchitectApp {
    public static void main(String[] args) {

        DefaultTerminalFactory facTer = new DefaultTerminalFactory()
                .setTerminalEmulatorColorConfiguration(TerminalEmulatorColorConfiguration.newInstance(TerminalEmulatorPalette.STANDARD_VGA))
                .setTerminalEmulatorDeviceConfiguration(new TerminalEmulatorDeviceConfiguration(2, 500, TerminalEmulatorDeviceConfiguration.CursorStyle.VERTICAL_BAR, TextColor.ANSI.WHITE_BRIGHT, true));

        try(Screen screen = facTer.createScreen()) {

            screen.startScreen();

            SimpleTheme test = SimpleTheme.makeTheme(
                    true,
                    TextColor.ANSI.DEFAULT,
                    TextColor.ANSI.DEFAULT,
                    TextColor.ANSI.DEFAULT,
                    TextColor.ANSI.DEFAULT,
                    TextColor.ANSI.DEFAULT,
                    TextColor.ANSI.DEFAULT,
                    TextColor.ANSI.BLACK
            );

            MultiWindowTextGUI gui = new MultiWindowTextGUI(screen);
//            gui.setTheme(test);

            Windows wm = new Windows(gui);

            WindowListenerAdapter listener = new WindowListenerAdapter(){
                @Override
                public void onUnhandledInput(Window basePane, KeyStroke keyStroke, AtomicBoolean atomicBoolean){
                    if(keyStroke.getKeyType().equals(KeyType.Escape)){
                        gui.getActiveWindow().close();
                        atomicBoolean.set(true);
                    }
                }
            };

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
