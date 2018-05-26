package com.example.android.miwok;

/**
 * create the class w/private vars
 */
public class Word {

    private String mDefaultTranslation;

    private String mMiwokTransition;

    private int mImageResourceId = NO_IMAGE_PROVIDED;

    // -1 will determine this var a out of range of any index provided here
    private static final int NO_IMAGE_PROVIDED = -1;

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
     * Constructor that includes images
     * @param defaultTranslation
     * @param miwokTranslation
     * @param imageResourceId
     */
    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId){

        mDefaultTranslation = defaultTranslation;
        mMiwokTransition = miwokTranslation;
        mImageResourceId = imageResourceId;
    }

    /**
     * English translation
     * @return
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * Miwok translation
     * @return
     */
    public String getMiwokTransition() {
        return mMiwokTransition;
    }

    /**
     *
     * @return
     */
    public int getImageResourceId(){
        return mImageResourceId;
    }

    /**
     *  amethod to determine if we have provided an image for a given item
     * @return
     */
    public boolean hasImage(){
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }
}
