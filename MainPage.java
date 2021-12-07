

public class MainPage {
    private FrameHelper frame;

    public MainPage(){
        frame = new FrameHelper();

    }

    public void run(){

        frame.createGreetingPanel();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    
}

