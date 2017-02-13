package org.superbapps.utils.common.hashing;

/**
 * @author д06ри
 */
public interface IHash {

    static final int $256 = 256;
    static final int $384 = 384;
    static final int $512 = 512;

    /**
     *
     *
     * @param input String to hash
     * @param type Hash type: 256, 384 or 512
     * @return
     */
    String hash(String input, int type);

    String hash(String input, int type, byte[] salt, int noOIterations);

}
