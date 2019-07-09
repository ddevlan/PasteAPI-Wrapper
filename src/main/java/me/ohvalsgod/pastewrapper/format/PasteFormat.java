package me.ohvalsgod.pastewrapper.format;

/**
 * There are way, way more than the preset paste formats that I have listed here.
 *
 * If you would like to view the available formats visit https://pastebin.com/api and CTRL+F "We have over 200"
 */
public enum PasteFormat {
    TEXT("text"),
    JAVA("java"),
    CPP("cpp"),
    C("c"),
    PHP("php"),
    CSS("css"),
    HTML("html5"),
    GO("go"),
    HASKELL("haskell"),
    JSON("json"),
    JAVASCRIPT("javascript");

    String text;

    PasteFormat(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
