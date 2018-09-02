package com.mage.gerrit.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SshKeyInfo extends BaseModel {
    /**
     * The sequence number of the SSH key.
     */
    private int seq;

    /**
     * The complete public SSH key.
     */
    @JsonProperty("ssh_public_key")
    private String publicKey;

    /**
     * The encoded key.
     */
    private String encodedKey;

    /**
     * The algorithm of the SSH key.
     */
    private String algorithm;

    /**
     * The comment of the SSH key.
     */
    private String comment;

    /**
     * Whether the SSH key is valid.
     */
    private boolean valid;

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getEncodedKey() {
        return encodedKey;
    }

    public void setEncodedKey(String encodedKey) {
        this.encodedKey = encodedKey;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
