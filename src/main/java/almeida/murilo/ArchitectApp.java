package almeida.murilo;

import almeida.murilo.Windows.Windows;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalFactory;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.SwingTerminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFrame;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ArchitectApp {
    public static void main(String[] args) {

        try(Screen screen = new DefaultTerminalFactory().createScreen()) {

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

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
