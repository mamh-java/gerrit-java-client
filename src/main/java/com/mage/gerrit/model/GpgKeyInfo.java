package com.mage.gerrit.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * {
 * "AFC8A49B": {
 * "fingerprint": "0192 723D 42D1 0C5B 32A6  E1E0 9350 9E4B AFC8 A49B",
 * "user_ids": [
 * "John Doe \u003cjohn.doe@example.com\u003e"
 * ],
 * "key": "-----BEGIN PGP PUBLIC KEY BLOCK-----\nVersion: BCPG v1.52\n\nmQENBFXUpNcBCACv4paCiyKxZ0EcKy8VaWVNkJlNebRBiyw9WxU85wPOq5Gz/3GT\nRQwKqeY0SxVdQT8VNBw2sBe2m6eqcfZ2iKmesSlbXMe15DA7k8Bg4zEpQ0tXNG1L\nhceZDVQ1Xk06T2sgkunaiPsXi82nwN3UWYtDXxX4is5e6xBNL48Jgz4lbqo6+8D5\nvsVYiYMx4AwRkJyt/oA3IZAtSlY8Yd445nY14VPcnsGRwGWTLyZv9gxKHRUppVhQ\nE3o6ePXKEVgmONnQ4CjqmkGwWZvjMF2EPtAxvQLAuFa8Hqtkq5cgfgVkv/Vrcln4\nnQZVoMm3a3f5ODii2tQzNh6+7LL1bpqAmVEtABEBAAG0H0pvaG4gRG9lIDxqb2hu\nLmRvZUBleGFtcGxlLmNvbT6JATgEEwECACIFAlXUpNcCGwMGCwkIBwMCBhUIAgkK\nCwQWAgMBAh4BAheAAAoJEJNQnkuvyKSbfjoH/2OcSQOu1kJ20ndjhgY2yNChm7gd\ntU7TEBbB0TsLeazkrrLtKvrpW5+CRe07ZAG9HOtp3DikwAyrhSxhlYgVsQDhgB8q\nG0tYiZtQ88YyYrncCQ4hwknrcWXVW9bK3V4ZauxzPv3ADSloyR9tMURw5iHCIeL5\nfIw/pLvA3RjPMx4Sfow/bqRCUELua39prGw5Tv8a2ZRFbj2sgP5j8lUFegyJPQ4z\ntJhe6zZvKOzvIyxHO8llLmdrImsXRL9eqroWGs0VYqe6baQpY6xpSjbYK0J5HYcg\nTO+/u80JI+ROTMHE6unGp5Pgh/xIz6Wd34E0lWL1eOyNfGiPLyRWn1d0",
 * "status": "TRUSTED",
 * "problems": [],
 * },
 * }
 */
public class GpgKeyInfo extends BaseModel {


    /**
     * The 8-char hex GPG key ID.
     */
    private String id;

    /**
     * The 40-char (plus spaces) hex GPG key fingerprint.
     */
    private String fingerprint;

    /**
     * OpenPGP User IDs associated with the public key.
     */
    @JsonProperty("user_ids")
    private List<String> userIds;

    /**
     * ASCII armored public key material.
     */
    private String key;

    /**
     * The result of server-side checks on the key; one of BAD, OK, or TRUSTED.
     * BAD keys have serious problems and should not be used. If a key is OK,
     * inspecting only that key found no problems, but the system does not fully
     * trust the key’s origin. A `TRUSTED key is valid, and the system knows
     * enough about the key and its origin to trust it.
     */
    private String status;

    /**
     * A list of human-readable problem strings found in the course of checking
     * whether the key is valid and trusted.
     */
    private List<String> problems;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getProblems() {
        return problems;
    }

    public void setProblems(List<String> problems) {
        this.problems = problems;
    }
}
