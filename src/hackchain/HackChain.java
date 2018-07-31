package hackchain;

import java.util.ArrayList;
import hackchain.Block;
import hackchain.StringUtil;


public class HackChain {
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 5;

	
	public static void main(String[] args) {
		System.out.println("Mining block 1... ");
		addBlock(new Block("First block", "0"));
		
		System.out.println("Mining block 2... ");
		addBlock(new Block("Second block",blockchain.get(blockchain.size()-1).hash));
		
		System.out.println("Mining block 3... ");
		addBlock(new Block("Third block",blockchain.get(blockchain.size()-1).hash));	
		
		System.out.println("\nBlockchain Valid: " + isChainValid());
		
		String blockchainJson = StringUtil.getJson(blockchain);
		System.out.println("\nBlock chain: ");
		System.out.println(blockchainJson);
	}
	
	public static Boolean isChainValid() {
		Block currentBlock; 
		Block previousBlock;
		
		for(int i=1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			//compare registered hash and calculated hash:
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("Current Hashes not equal");			
				return false;
			}
			//compare previous hash and registered previous hash
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
		}
		return true;
	}
	
	public static void addBlock(Block newBlock) {
		newBlock.mineBlock(difficulty);
		blockchain.add(newBlock);
	}
}
