import com.google.gson.JsonObject;
//import com.gxb.block.baas.sdk.client.api.BaasApiException;
//import com.gxb.block.baas.sdk.client.api.BaasDefaultClient;
//import com.gxb.block.baas.sdk.client.api.BaasResponse;
//import com.gxb.block.baas.sdk.client.api.request.ProviderReq;
import com.gxchain.client.GXChainClient;
//import com.gxchain.client.domian.KeyPair;
import com.gxchain.client.domian.TransactionResult;
//import io.anshily.shiro.jwt.configuration.JWTShiroRealm;
import io.anshily.base.core.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import java.io.IOException;


public class GxcSaas {

    private static final Logger log = LoggerFactory.getLogger(GxcSaas.class);

    public static void main(String argv[]){
//        try {
//            BaasDefaultClient client = new  BaasDefaultClient("https://baas-developer.gxchain.cn/api/storage/provider");
//            BaasResponse res = client.execute(new ProviderReq());
//            System.out.println(res);
//        } catch (BaasApiException e) {
//            e.printStackTrace();
//        }
        String activePrivateKey = "5KCuWPtZ1jTMNP9G7vETiNJdesA27RzWHmfmXZrkmibfM3WhUJ2";

//        String activePrivateKey = "5J8CYVMcMz2f7vDc9fYcySbVUx7f3JnCJJVBeE6eWJmwZToTSqz";

//        String accountIdOrName = "1.2.521";
        String accountIdOrName = Constants.EXAMPLE_ACCOUNT;
//        String entryPoint = "wss://testnet.gxchain.org";
        String entryPoint = "http://ttq.tiantianquan.xyz:28090";
        String memoPrivateKey = "";

        GXChainClient client = new GXChainClient(activePrivateKey, accountIdOrName, entryPoint);


        JsonObject param = new JsonObject();
        param.addProperty("user", "robin");
//call contract method
        TransactionResult transactionResult = client.callContract("hello22", "hi", param, null, true);
        log.info(transactionResult.getTransaction().toJsonString());
//        KeyPair keyPair = null;
//        try {
//            keyPair = GXChainClient.generateKey();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String result = client.register("lirb-test001", keyPair.getPublicKey());
//        log.info(result);

    }
}
