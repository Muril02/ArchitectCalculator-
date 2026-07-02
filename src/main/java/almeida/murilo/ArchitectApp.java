package almeida.murilo;

import almeida.murilo.Windows.Windows;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;

import static com.googlecode.lanterna.TextCharacter.fromCharacter;

public class ArchitectApp {
    public static void main(String[] args) {

        try(Screen screen = new DefaultTerminalFactory().createScreen()) {
            TextGraphics textgh = screen.newTextGraphics();
            screen.startScreen();

            MultiWindowTextGUI gui = new MultiWindowTextGUI(screen);

            Windows wm = new Windows(gui);

            WindowListenerAdapter listener = new WindowListenerAdapter();

            BasicWindow stUp = wm.startupWindow();
            stUp.addWindowListener(listener);

            gui.addWindowAndWait(stUp);
            gui.updateScreen();

            while(gui.getWindows().size() > 1){

            }


        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
