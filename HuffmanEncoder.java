import jdk.jfr.Frequency;

import java.io.*;
import java.util.*;

public class HuffmanEncoder {

    static HashMap<Character,String>map;
    static HashMap<Integer,String>idxtostr;
    static HashMap<String,Character>revmap;
    static int idx;
    static int max_len;
    static class HuffmanNode{
        int data ;
        char ch;
        HuffmanNode left;
        HuffmanNode right;
        public HuffmanNode(){}

        public HuffmanNode(int data){
            this.data=data;
        }
        public HuffmanNode(int data,char ch){
            this.data=data;
            this.ch=ch;
        }
    }
    public static void print(HuffmanNode root,String s){
        if(root.left==null && root.right==null){
            map.put(root.ch,s);
            return ;
        }
        print(root.left,s+'0');
        print(root.right,s+'1');

    }
    public static void main(String[] args) throws IOException {


        HashMap<Character, Integer>freq=new HashMap<>();
        File file = new File("/Users/kartikaypandey/IdeaProjects/HuffmanEncoder/src/textfile");
        BufferedReader br= new BufferedReader(new FileReader(file));

        String st;

        idxtostr=new HashMap<>();
        idx=0;
        while ((st = br.readLine()) != null)

            if(st!=null){
                System.out.println(st);
                char[]arr=st.toCharArray();
                for(char ch:arr)freq.put(ch,freq.getOrDefault(ch,0)+1);
                idxtostr.put(idx,String.valueOf(arr));
                idx+=1;
            }


        map=new HashMap<>();
        revmap=new HashMap<>();
        PriorityQueue<HuffmanNode>pq=new PriorityQueue<>((a,b)->a.data-b.data);

        for(char ch:freq.keySet()){
            HuffmanNode hn=new HuffmanNode(freq.get(ch),ch);
            pq.add(hn);
        }
        HuffmanNode root=new HuffmanNode();
        while(pq.size()!=1){
            HuffmanNode min1=pq.poll();
            HuffmanNode min2=pq.poll();
            HuffmanNode node =new HuffmanNode(min1.data+min2.data);
            node.left=min1;
            node.right=min2;
            root=node;
            pq.add(node);
        }
        print(root,"");
        for(Character ch:map.keySet()) {
            System.out.println(ch + " " + map.get(ch));
        }
        max_len=0;
        for(char ch:map.keySet()){
            max_len=Math.max(max_len,map.get(ch).length());
            revmap.put(map.get(ch),ch);
        }
        List<String>encode=encode();
        List<String>decode=decode(encode);
        for(String s:decode){
            System.out.println(s);
        }

    }
    public static List<String> encode() throws IOException {
        List<String>encode=new ArrayList<>();
        FileWriter fw=new FileWriter("output");
        for(int i=0;i<idx;i++){
            char[]a=idxtostr.get(i).toCharArray();
            StringBuilder sb=new StringBuilder();
            for(char ch:a){
                sb.append(map.get(ch));
            }
            fw.write(sb.toString());
            encode.add(sb.toString());
        }
        fw.close();
        System.out.println("Writing successful");
        return encode;
    }
    public static List<String> decode(List<String>encode){
        List<String>decode=new ArrayList<>();
        for(String s:encode){
            int i=0;
            StringBuilder sb=new StringBuilder();
            while(i<s.length()) {
                for (int j = 1; j <= max_len; j++) {
                    String sub = s.substring(i, i + j);
                    if (revmap.containsKey(sub)) {
                        i = i + j;
                        sb.append(revmap.get(sub));
                        break;
                    }
                }
            }
            decode.add(sb.toString());
        }
        return decode;
    }
}
