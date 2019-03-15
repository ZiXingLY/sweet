package io.anshily.ipfs;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class IPFSClient {

//    public static final String FS_ADDR = "/ip4/47.93.226.47/tcp/5432";

    public static IPFS ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");
//    public static IPFS ipfs = new IPFS(FS_ADDR);

    public static String add() throws IOException {
        NamedStreamable.FileWrapper file = new NamedStreamable.FileWrapper(new File("/Users/anshi/Desktop/","keniu.sql"));
        MerkleNode addResult = ipfs.add(file).get(0);
        return addResult.hash.toString();
    }

    public static String cat(String hash) throws IOException {
        byte[] data = ipfs.cat(Multihash.fromBase58(hash));
        return new String(data);
    }


    public static void main(String[] args){
        try {
            System.out.println(add());
//            System.out.println(cat("QmRvXsYnE6hP9sqigeaDZmKexNnAN8n5KQTSgSDpy3svov"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
