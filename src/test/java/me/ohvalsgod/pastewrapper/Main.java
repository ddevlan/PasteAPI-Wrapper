package me.ohvalsgod.pastewrapper;

import com.besaba.revonline.pastebinapi.paste.PasteExpire;
import com.besaba.revonline.pastebinapi.paste.PasteVisiblity;
import me.ohvalsgod.pastewrapper.defaults.PasteDefaults;
import me.ohvalsgod.pastewrapper.format.PasteFormat;
import me.ohvalsgod.pastewrapper.receiver.ConsolePasteReceiverImpl;

public class Main {

    public static void main(String[] args) {
        //  Default constructor for paste wrapper
        PasteWrapper wrapper = new PasteWrapper("test", "test", "test");

        //  Extended constructof ro paste wrapper
        wrapper = new PasteWrapper("test", "test", "test",
                new PasteDefaults("Default title", PasteFormat.TEXT, PasteVisiblity.Unlisted, PasteExpire.OneHour, new ConsolePasteReceiverImpl()));

        wrapper.paste("test paste#0!");
        wrapper.paste("test title?", "test paste#1!");
        wrapper.paste("test title?", "test paste#2!", PasteFormat.TEXT, PasteVisiblity.Unlisted, PasteExpire.OneMonth, wrapper.getDefaults().getReceiver());
    }

}
