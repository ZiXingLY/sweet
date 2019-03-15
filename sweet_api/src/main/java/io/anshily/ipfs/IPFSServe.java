package io.anshily.ipfs;

import io.anshily.base.utils.UploadFile;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class IPFSServe {

    @Autowired
    @Qualifier("ipfsClient")
    public IPFS ipfs;

    public String add(String data) throws IOException {
        File file = new File(UploadFile.uploadSingleBase64(data));
        NamedStreamable.FileWrapper fileWrapper = new NamedStreamable.FileWrapper(file);
        MerkleNode addResult = ipfs.add(fileWrapper).get(0);
        return addResult.hash.toString();
    }

    public String cat(String hash) throws IOException {
        byte[] data = ipfs.cat(Multihash.fromBase58(hash));
        return new String(data);
    }
}
