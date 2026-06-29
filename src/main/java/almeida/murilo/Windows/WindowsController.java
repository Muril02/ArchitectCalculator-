package almeida.murilo.Windows;

import com.googlecode.lanterna.gui2.BasicWindow;

public class WindowsController {

    public BasicWindow getStartupPage(){
        WindowsModel wm = new WindowsModel();

        return wm.startupWindow();
    }

    public BasicWindow getCalcTelhado(){
        WindowsModel wm = new WindowsModel();

        return wm.calcTelhadoWindow();
    }

}
