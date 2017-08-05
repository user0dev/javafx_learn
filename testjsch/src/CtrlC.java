import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 * Created by user0dev on 02.08.17.
 */
public class CtrlC implements SignalHandler {
    @Override
    public void handle(Signal signal) {
        System.out.println("Pressed Ctrl-C");
    }
    public static void main(String[] args) {
        Signal signal = new Signal("INT");
        //System.out.println(signal.getNumber());
        Signal.handle(signal, new CtrlC());
        for(;;);
    }
}
