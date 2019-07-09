package me.ohvalsgod.pastewrapper.receiver;

public interface PasteReceiver {

    /**
     * Will notify the receiver when a paste has been made or an error has occurred.
     *
     * @param string message
     */
    void notifyReceiver(String string);

}
