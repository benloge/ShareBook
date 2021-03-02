package fr.sharebookstore.app.model;

import java.util.Date;

/**
 * Created by <VOTRE-NOM> on <DATE-DU-JOUR>.
 */
public class User {

    private int mId;
    private String mPseudo;
    private String mNom;
    private String mPrenom;
    private String memail;
    private String mTel;
    private Date mDate_de_Naissance;
    private int mGenre;
    private String mMdp;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmPseudo() {
        return mPseudo;
    }

    public void setmPseudo(String mPseudo) {
        this.mPseudo = mPseudo;
    }

    public String getmNom() {
        return mNom;
    }

    public void setmNom(String mNom) {
        this.mNom = mNom;
    }

    public String getmPrenom() {
        return mPrenom;
    }

    public void setmPrenom(String mPrenom) {
        this.mPrenom = mPrenom;
    }

    public String getMemail() {
        return memail;
    }

    public void setMemail(String memail) {
        this.memail = memail;
    }

    public String getmTel() {
        return mTel;
    }

    public void setmTel(String mTel) {
        this.mTel = mTel;
    }

    public Date getmDate_de_Naissance() {
        return mDate_de_Naissance;
    }

    public void setmDate_de_Naissance(Date mDate_de_Naissance) {
        this.mDate_de_Naissance = mDate_de_Naissance;
    }

    public int getmGenre() {
        return mGenre;
    }

    public void setmGenre(int mGenre) {
        this.mGenre = mGenre;
    }

    public String getmMdp() {
        return mMdp;
    }

    public void setmMdp(String mMdp) {
        this.mMdp = mMdp;
    }
}
