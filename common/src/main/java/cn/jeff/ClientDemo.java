package cn.jeff;

import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.JerseyWebTarget;

import javax.ws.rs.core.Response;

/**
 * @author swzhang
 * @date 2018/10/16
 * @description
 */
public class ClientDemo {
    public static void main(String[] args) {
        System.setProperty("http.proxyHost", "192.168.3.121");
        System.setProperty("https.proxyHost", "192.168.3.121");
        System.setProperty("http.proxyPort", "8888");
        System.setProperty("https.proxyPort", "8888");
        JerseyClient client = JerseyClientBuilder.createClient();
        JerseyWebTarget target = client.target("http://www.baidu.com");
        Response response = target.request().header("charsetEncoding", "utf-8")
                .get();
        System.out.println(response.getStatus());
    }
}
