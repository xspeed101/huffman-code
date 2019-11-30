package huffmancode;

import java.util.*;


abstract class HuffmanTree implements Comparable<HuffmanTree> {
    public final int frequency; 
    public HuffmanTree(int freq) { frequency = freq; }
 
    
    public int compareTo(HuffmanTree tree) {
        return frequency - tree.frequency;
    }
}
 
class HuffmanLeaf extends HuffmanTree {
    public final char value; 
 
    public HuffmanLeaf(int freq, char val) {
        super(freq);
        value = val;
    }
}
 
class Huffman extends HuffmanTree {
    public final HuffmanTree left, right; 
 
    public Huffman(HuffmanTree l, HuffmanTree r) {
        super(l.frequency + r.frequency);
        left = l;
        right = r;
    }
}
 
public class HuffmanCode {
    public static HuffmanTree buildTree(int[] charFreqs) {
        PriorityQueue<HuffmanTree> trees = new PriorityQueue<HuffmanTree>();
        
        for (int i = 0; i < charFreqs.length; i++)
            if (charFreqs[i] > 0)
                trees.offer(new HuffmanLeaf(charFreqs[i], (char)i));
 
        assert trees.size() > 0;
        while (trees.size() > 1) {
            HuffmanTree a = trees.poll();
            HuffmanTree b = trees.poll();
 
            trees.offer(new Huffman(a, b));
        }
        return trees.poll();
    }
 
    public static void printCodes(HuffmanTree tree, StringBuffer prefix) {
        assert tree != null;
        if (tree instanceof HuffmanLeaf) {
            HuffmanLeaf leaf = (HuffmanLeaf)tree;
 
            System.out.println(leaf.value + "\t" + leaf.frequency + "\t" + prefix);
 
        } else if (tree instanceof Huffman) {
            Huffman node = (Huffman)tree;
 
           
            prefix.append('0');
            printCodes(node.left, prefix);
            prefix.deleteCharAt(prefix.length()-1);
 
            
            prefix.append('1');
            printCodes(node.right, prefix);
            prefix.deleteCharAt(prefix.length()-1);
        }
    }
 
    public static void main(String[] args) {
 
    	 String test = "Huffman Code";
        int[] charFreqs = new int[256];
        for (char c : test.toCharArray())
            charFreqs[c]++;
 
        HuffmanTree tree = buildTree(charFreqs);
 
        System.out.println("Letter\tFrequency HUFFMAN CODE");
        printCodes(tree, new StringBuffer());
    }
}
