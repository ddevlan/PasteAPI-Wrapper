package me.ohvalsgod.pastewrapper.defaults;

import com.besaba.revonline.pastebinapi.paste.PasteExpire;
import com.besaba.revonline.pastebinapi.paste.PasteVisiblity;
import me.ohvalsgod.pastewrapper.format.PasteFormat;
import me.ohvalsgod.pastewrapper.receiver.ConsolePasteReceiverImpl;
import me.ohvalsgod.pastewrapper.receiver.PasteReceiver;

/**
 * This class contains all the default values {@link me.ohvalsgod.pastewrapper.PasteWrapper} will use when posting your pastes.
 */
public class PasteDefaults {

    private String title;
    private PasteFormat format;
    private PasteVisiblity visiblity;
    private PasteExpire expire;
    private PasteReceiver receiver;

    /**
     * Full constructor for defining paste defaults
     *
     * @param title default title
     * @param format default format
     * @param visiblity default visibility
     * @param expire default expire time
     * @param receiver default paste receiver
     */
    public PasteDefaults(String title, PasteFormat format, PasteVisiblity visiblity, PasteExpire expire, PasteReceiver receiver) {
        this.title = title;
        this.format = format;
        this.visiblity = visiblity;
        this.expire = expire;
        this.receiver = receiver;
    }

    /**
     * Quick constructor for defining paste defaults
     *
     * When using this constructor, the default PasteReceiver will be {@link ConsolePasteReceiverImpl}
     *
     * @param title default title
     * @param format default format
     * @param visiblity default visibility
     * @param expire default expire
     */
    public PasteDefaults(String title, PasteFormat format, PasteVisiblity visiblity, PasteExpire expire) {
        this(title, format, visiblity, expire, new ConsolePasteReceiverImpl());
    }

    public String getTitle() {
        return title;
    }

    public PasteFormat getFormat() {
        return format;
    }

    public PasteVisiblity getVisiblity() {
        return visiblity;
    }

    public PasteExpire getExpire() {
        return expire;
    }

    public PasteReceiver getReceiver() {
        return receiver;
    }

}
