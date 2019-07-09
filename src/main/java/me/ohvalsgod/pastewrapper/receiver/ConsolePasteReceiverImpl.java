package me.ohvalsgod.pastewrapper.receiver;

/**
 * Default PasteReceiver in {@link me.ohvalsgod.pastewrapper.defaults.PasteDefaults}
 */
public class ConsolePasteReceiverImpl implements PasteReceiver {

    public ConsolePasteReceiverImpl() {}

    public void notifyReceiver(String string) {
        System.out.println(string);
    }

}
