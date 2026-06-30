package almeida.murilo;

import almeida.murilo.Windows.WindowsController;
import almeida.murilo.Windows.WindowsModel;
import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.*;
import com.googlecode.lanterna.gui2.menu.Menu;import com.googlecode.lanterna.gui2.menu.MenuBar;import com.googlecode.lanterna.gui2.menu.MenuItem;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.awt.event.WindowAdapter;
import java.io.IOException;import java.sql.SQLOutput;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.googlecode.lanterna.TextCharacter.fromCharacter;

public class ArchitectApp {
    public static void main(String[] args) {

        try(Screen screen = new DefaultTerminalFactory().createScreen()) {
            TextGraphics textgh = screen.newTextGraphics();
            WindowsController wm = new WindowsController();
            screen.startScreen();

            MultiWindowTextGUI gui = new MultiWindowTextGUI(screen);

            WindowListenerAdapter listener = new WindowListenerAdapter();

            BasicWindow stUp = wm.getStartupPage();
            stUp.addWindowListener(listener);

            gui.addWindowAndWait(stUp);


            gui.updateScreen();



        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
