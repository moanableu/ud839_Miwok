package com.example.android.miwok;

/**
 * create the class w/private vars
 */
public class Word {

    private String mDefaultTranslation;

    private String mMiwokTransition;

    /**
     * Constructor
     * @param defaultTranslation - English
     * @param miwokTranslation - Miwok
     */
    public Word(String defaultTranslation, String miwokTranslation){
        mDefaultTranslation = defaultTranslation;
        mMiwokTransition = miwokTranslation;
    }

    /**
     * English translation
     * @return
     */
    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * Miwok translation
     * @return
     */
    public String getmMiwokTransition() {
        return mMiwokTransition;
    }
}
