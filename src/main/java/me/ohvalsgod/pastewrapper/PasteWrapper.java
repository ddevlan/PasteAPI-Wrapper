package me.ohvalsgod.pastewrapper;

import com.besaba.revonline.pastebinapi.Pastebin;
import com.besaba.revonline.pastebinapi.impl.factory.PastebinFactory;
import com.besaba.revonline.pastebinapi.paste.Paste;
import com.besaba.revonline.pastebinapi.paste.PasteBuilder;
import com.besaba.revonline.pastebinapi.paste.PasteExpire;
import com.besaba.revonline.pastebinapi.paste.PasteVisiblity;
import com.besaba.revonline.pastebinapi.response.Response;
import me.ohvalsgod.pastewrapper.defaults.PasteDefaults;
import me.ohvalsgod.pastewrapper.format.PasteFormat;
import me.ohvalsgod.pastewrapper.receiver.PasteReceiver;

import java.util.HashMap;
import java.util.Map;

/**
 * Pastebin API wrapper for {@link PastebinFactory}
 */
public class PasteWrapper {

    private final PastebinFactory factory;
    private final Pastebin pastebin;
    private PasteDefaults defaults;
    private String dev_key, username, password, user_key;
    private Map<Paste, Response<String>> previous_pastes = new HashMap<Paste, Response<String>>();
    private boolean logged_in;

    /**
     * Constructor that uses default {@link PasteDefaults}
     *
     * @param dev_key developer api key
     * @param username username
     * @param password password
     */
    public PasteWrapper(String dev_key, String username, String password) {
        this(dev_key, username, password, new PasteDefaults("Default PasteWrapper title", PasteFormat.TEXT, PasteVisiblity.Unlisted, PasteExpire.OneHour));
    }

    /**
     * Full constructor allowing you to define your own {@link PasteDefaults}
     *
     * @param dev_key developer api key
     * @param username username
     * @param password password
     * @param defaults paste defaults
     */
    public PasteWrapper(String dev_key, String username, String password, PasteDefaults defaults) {
        this.dev_key = dev_key;
        this.username = username;
        this.password = password;
        this.defaults = defaults;

        this.factory = new PastebinFactory();
        this.pastebin = factory.createPastebin(this.dev_key);

        logged_in = login();
        if (logged_in) {
            defaults.getReceiver().notifyReceiver("[ERROR] Could not login to pastebin, any pastes made will be ignored.");
        }
    }

    /**
     * Attempts to login with given credentials
     *
     * @return if login was successful or not
     */
    private boolean login() {
        Response<String> login_response = pastebin.login(username, password);

        if (login_response.hasError()) {
            return false;
        }

        this.user_key = login_response.get();
        return true;
    }

    /**
     * Post a pastebin only with given contents.
     *
     * @param contents paste contents
     */
    public void paste(String contents) {
        paste(defaults.getTitle(), contents, defaults.getFormat(), defaults.getVisiblity(), defaults.getExpire(), defaults.getReceiver());
    }

    /**
     * Post a pastebin only with given title, and contents.
     *
     * @param title paste title
     * @param contents paste contents
     */
    public void paste(String title, String contents) {
        paste(title, contents, defaults.getFormat(), defaults.getVisiblity(), defaults.getExpire(), defaults.getReceiver());
    }

    /**
     * Post a pastebin with given title, contents, and format.
     *
     * @param title paste title
     * @param contents paste contents
     * @param format paste format
     */
    public void paste(String title, String contents, PasteFormat format) {
        paste(title, contents, format, defaults.getVisiblity(), defaults.getExpire(), defaults.getReceiver());
    }

    /**
     * Post a pastebin with given title, contents, format, and visibility.
     *
     * @param title paste title
     * @param contents paste contents
     * @param format paste format
     * @param visiblity paste visibility
     */
    public void paste(String title, String contents, PasteFormat format, PasteVisiblity visiblity) {
        paste(title, contents, format, visiblity, defaults.getExpire(), defaults.getReceiver());
    }

    /**
     * Post a pastebin with given title, contents, format, visibility, and expire time.
     *
     * @param title paste title
     * @param contents paste contents
     * @param format paste format
     * @param visiblity paste visibility
     * @param expire paste expire time
     */
    public void paste(String title, String contents, PasteFormat format, PasteVisiblity visiblity, PasteExpire expire) {
        paste(title, contents, format, visiblity, expire, defaults.getReceiver());
    }

    /**
     * Post a pastebin with given title, contents, format, visibility, expire time, and specified receiver.
     *
     * @param title paste title
     * @param contents paste contents
     * @param format paste format
     * @param visiblity paste visibility
     * @param expire paste expire time
     * @param receiver paste receiver
     */
    public void paste(String title, String contents, PasteFormat format, PasteVisiblity visiblity, PasteExpire expire, PasteReceiver receiver) {
        if (logged_in) {
            PasteBuilder builder = factory.createPaste();
            Paste paste = builder.setTitle(title).setRaw(contents).setMachineFriendlyLanguage(format.getText()).setVisiblity(visiblity).setExpire(expire).build();

            Response<String> response = pastebin.post(paste, user_key);

            if (response.hasError()) {
                receiver.notifyReceiver("[ERROR] There was an error posting your pastebin: " + response.getError());
                return;
            }

            previous_pastes.put(paste, response);

            receiver.notifyReceiver("Your paste has been posted, you can view it here: " + response.get());
        }
    }

    /**
     * Get a previous paste's response.
     *
     * @param paste specified paste
     * @return paste response
     */
    public Response<String> get(Paste paste) {
        return previous_pastes.get(paste);
    }

    /**
     * Get assigned PasteWrapper defaults.
     *
     * @return paste defaults
     */
    public PasteDefaults getDefaults() {
        return defaults;
    }
}
