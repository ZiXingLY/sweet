package io.anshily.gxchainbaas;

import com.gxb.block.baas.sdk.client.api.BaasApiException;
import com.gxb.block.baas.sdk.client.api.BaasDefaultClient;
import com.gxb.block.baas.sdk.client.api.client.StoreDataClient;
import com.gxb.block.baas.sdk.client.api.request.ProviderReq;
import com.gxb.block.baas.sdk.client.api.response.StoreDataResp;
import io.anshily.base.core.Constants;

import static com.gxb.block.baas.sdk.client.api.BaasConstants.URL_DEVELOPER_HEADER;

public class TestBaas {


    public static void main(String[] args){

        try {
            System.out.println(new BaasDefaultClient(URL_DEVELOPER_HEADER + "storage/provider").execute(new ProviderReq()));
        } catch (BaasApiException e) {
            e.printStackTrace();
        };

        System.out.println("--------------------------------");

        System.out.println("-                              -");
        System.out.println("-                              -");
        System.out.println("-                              -");
        System.out.println("-                              -");
        System.out.println("-                              -");
        System.out.println("--------------------------------");


        StoreDataClient client = new StoreDataClient(Constants.EXAMPLE_ACCOUNT, Constants.EXAMPLE_PRIVATE_KEY, Constants.EXAMPLE_PUBLIC_KEY,true,"https://baas-developer.gxchain.cn/api/");
        StoreDataResp resp = client.store("Hello GXChain!".getBytes());

        System.out.println("request!");

        System.out.println(resp);
    }
}
