package almeida.murilo;

import almeida.murilo.Windows.Windows;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ArchitectApp {
    public static void main(String[] args) {

        try(Screen screen = new DefaultTerminalFactory().createScreen()) {
            TextGraphics textgh = screen.newTextGraphics();
            screen.startScreen();

            MultiWindowTextGUI gui = new MultiWindowTextGUI(screen);

            Windows wm = new Windows(gui);

            WindowListenerAdapter listener = new WindowListenerAdapter();

            BasicWindow stUp = wm.startupWindow();
            stUp.setHints(Set.of(
                    Window.Hint.CENTERED,
                    Window.Hint.FIT_TERMINAL_WINDOW
            ));

            stUp.addWindowListener(listener);

            gui.addWindowAndWait(stUp);
            gui.updateScreen();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
