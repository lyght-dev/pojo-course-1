import config.ApplicationFactory;

public class Application {
    public static void main(String[] args) {
        new ApplicationFactory().createMainController().run();
    }
}
