package nmm.main;

import java.awt.EventQueue;

import nmm.view.MainWindow;


public class NineMensMorris {
    public static void main(String[] args) {
    	
        EventQueue.invokeLater(new Runnable()
        {
           @Override
            public void run()
            {
                new MainWindow();         
            }
        });
    }

}
