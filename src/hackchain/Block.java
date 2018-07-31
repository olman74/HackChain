package hackchain;

import java.util.Date;

import hackchain.StringUtil;

public class Block {
	public String hash;
	public String previousHash;
	private String data;
	private Long timestamp;
	private int nonce;

	public Block(String previousHash, String data) {
		this.previousHash = previousHash;
		this.data = data;
		this.timestamp = new Date().getTime();
		this.hash = calculateHash();
	}

	public String calculateHash() {
		return StringUtil.applySha256( 
				previousHash +
				Long.toString(timestamp) +
				Integer.toString(nonce) + 
				data 
				);
	}
	
	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0'); 
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
	}
}
