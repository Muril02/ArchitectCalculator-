package almeida.murilo;

import almeida.murilo.Windows.Windows;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.SimpleTheme;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
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

            SimpleTheme tema1 = SimpleTheme.makeTheme(
                    true,
                    TextColor.ANSI.BLACK, // baseForeground
                    TextColor.ANSI.WHITE, // baseBackground
                    TextColor.ANSI.BLACK, // editForeground
                    TextColor.ANSI.WHITE, // editBackground
                    TextColor.ANSI.BLACK, // selecForeground
                    TextColor.ANSI.WHITE, // selecBackground
                    TextColor.ANSI.BLUE // guiBackground
            );

            MultiWindowTextGUI gui = new MultiWindowTextGUI(screen);

//            gui.setTheme(tema1);

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
