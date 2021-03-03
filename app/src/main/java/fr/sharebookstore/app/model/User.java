package fr.sharebookstore.app.model;

import java.util.Date;

/**
 * Created by <VOTRE-NOM> on <DATE-DU-JOUR>.
 */
public class User {

    private static int mId;
    private static String mPseudo;
    private static String mNom;
    private static String mPrenom;
    private static String memail;
    private static String mTel;
    private static Date mDate_de_Naissance;
    private static int mGenre;
    private static String mMdp;
    private static boolean mStatus = Boolean.FALSE;


    public static int getmId() {
        return mId;
    }

    public static void setmId(int mId) {
        mId = mId;
    }

    public static String getmPseudo() {
        return mPseudo;
    }

    public static void setmPseudo(String mPseudo) {
        mPseudo = mPseudo;
    }

    public static String getmNom() {
        return mNom;
    }

    public static void setmNom(String mNom) {
        mNom = mNom;
    }

    public static String getmPrenom() {
        return mPrenom;
    }

    public static void setmPrenom(String mPrenom) {
        mPrenom = mPrenom;
    }

    public static String getMemail() {
        return memail;
    }

    public static void setmemail(String memail) {memail = memail;
    }

    public static String getmTel() {
        return mTel;
    }

    public static void setmTel(String mTel) {
        mTel = mTel;
    }

    public static Date getmDate_de_Naissance() {
        return mDate_de_Naissance;
    }

    public static void setmDate_de_Naissance(Date mDate_de_Naissance) { mDate_de_Naissance = mDate_de_Naissance;
    }

    public static int getmGenre() {
        return mGenre;
    }

    public static void setmGenre(int mGenre) {
        mGenre = mGenre;
    }

    public static String getmMdp() {
        return mMdp;
    }

    public static void setmMdp(String mMdp) { mMdp = mMdp;
    }

    public static boolean ismStatus() {
        return mStatus;
    }

    public static void setmStatus(boolean mStatus) {
        mStatus = mStatus;
    }
}
