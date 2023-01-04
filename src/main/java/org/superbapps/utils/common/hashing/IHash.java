package org.superbapps.utils.common.hashing;

public interface IHash {

	int $256 = 256;
	int $384 = 384;
	int $512 = 512;

	/**
	 * @param input String to hash
	 * @param type  Hash type: 256, 384 or 512
	 */
	String hash(String input, int type);

	String hash(String input, int type, byte[] salt, int noOIterations);

}
