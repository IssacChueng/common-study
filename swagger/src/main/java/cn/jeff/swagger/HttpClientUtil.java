package cn.jeff.swagger;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.Map.Entry;

public class HttpClientUtil {
    private static Log logger = LogFactory.getLog(HttpClientUtil.class);

    private static Logger blog = LoggerFactory.getLogger("business");

    private HttpClientUtil() {
    }

    public static HttpClientUtil getInstance() {
        return HttpClientUtil.SingletonHolder.INSTANCE;
    }

    /**
     * 思必驰请求，返回字节码
     *
     * @param url
     * @param param
     * @param contentType
     * @return
     * @throws IOException
     */
    public static byte[] post(String url, String param, String contentType) throws IOException {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient client = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(url);
        ByteArrayEntity byteArrayEntity = new ByteArrayEntity(param.getBytes("UTF-8"));
        byteArrayEntity.setChunked(true);
        byteArrayEntity.setContentType("Content-Type:audio/mp3");
        httpPost.setEntity(byteArrayEntity);
        if (!StringUtils.isBlank(contentType)) {
            httpPost.setHeader("Content-type", contentType);
        }
        CloseableHttpResponse httpResponse = client.execute(httpPost);
        try {
            HttpEntity entityResponse = httpResponse.getEntity();
            byte[] bytes = EntityUtils.toByteArray(entityResponse);
            return bytes;
        } finally {
            httpPost.releaseConnection();
            client.close();
        }
    }

    public static String postParams(String url, Map<String, String> params) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient client = httpClientBuilder.build();
        HttpPost post = new HttpPost(url);
        CloseableHttpResponse res = null;

        try {
            List<NameValuePair> nvps = new ArrayList();
            Set<String> keySet = params.keySet();
            Iterator var8 = keySet.iterator();

            String key;
            while (var8.hasNext()) {
                key = (String) var8.next();
                nvps.add(new BasicNameValuePair(key, (String) params.get(key)));
            }

            post.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            res = client.execute(post);
            HttpEntity entity = res.getEntity();
            key = EntityUtils.toString(entity, "utf-8");
            return key;
        } catch (Exception var19) {
            var19.printStackTrace();
        } finally {
            try {
                res.close();
                client.close();
            } catch (IOException var18) {
                var18.printStackTrace();
            }

        }

        return "";
    }

    public static String post(String url, String params, String contentType, int readTimeoutMs, int connectTimeoutMs) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient client = httpClientBuilder.build();
        client = (CloseableHttpClient) wrapClient(client);
        HttpPost post = new HttpPost(url);
        CloseableHttpResponse res = null;

        try {
            StringEntity s = new StringEntity(params, "UTF-8");
            if (StringUtils.isBlank(contentType)) {
                s.setContentType("application/json");
            }

            s.setContentEncoding("utf-8");
            s.setContentType(contentType);
            post.setEntity(s);
            res = client.execute(post);
            HttpEntity entity = res.getEntity();
            String var11 = EntityUtils.toString(entity, "utf-8");
            return var11;
        } catch (Exception var21) {
            var21.printStackTrace();
        } finally {
            try {
                res.close();
                client.close();
            } catch (Exception var20) {
                var20.printStackTrace();
            }

        }

        return "";
    }

    public static String post(String urlStr, String xmlInfo) {
        String line1 = "";

        try {
            URL url = new URL(urlStr);
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            con.setRequestProperty("Cache-Control", "no-cache");
            con.setRequestProperty("Content-Type", "text/xml");
            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
            out.write(new String(xmlInfo.getBytes("utf-8")));
            out.flush();
            out.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = "";

            for (line = br.readLine(); line != null; line = br.readLine()) {
                line1 = line1 + line;
            }

            return new String(line1.getBytes(), "utf-8");
        } catch (MalformedURLException var8) {
            var8.printStackTrace();
        } catch (IOException var9) {
            var9.printStackTrace();
        }

        return null;
    }

    public static String get(String urlStr, Map<String, String> param) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;

        try {
            URIBuilder builder = new URIBuilder(urlStr);
            if (param != null) {
                Iterator var6 = param.keySet().iterator();

                while (var6.hasNext()) {
                    String key = (String) var6.next();
                    builder.addParameter(key, (String) param.get(key));
                }
            }

            URI uri = builder.build();
            HttpGet httpGet = new HttpGet(uri);
            response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception var16) {
            var16.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }

                httpclient.close();
            } catch (IOException var15) {
                var15.printStackTrace();
            }

        }

        return resultString;
    }

    private static HttpClient wrapClient(HttpClient base) {
        try {
            SSLContext ctx = SSLContext.getInstance("TLSv1");
            X509TrustManager tm = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            ctx.init((KeyManager[]) null, new TrustManager[]{tm}, (SecureRandom) null);
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(ctx, new String[]{"TLSv1"}, (String[]) null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            return httpclient;
        } catch (Exception var5) {
            return null;
        }
    }

    private static void setRequestConfig(HttpRequestBase request) {
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout('\uea60').setConnectTimeout('\uea60').build();
        request.setConfig(requestConfig);
    }

    private static void addHeaders(HttpRequestBase request, Map<String, String> headers) {
        if (headers != null) {
            Iterator var2 = headers.entrySet().iterator();

            while (var2.hasNext()) {
                Entry<String, String> item = (Entry) var2.next();
                request.addHeader((String) item.getKey(), (String) item.getValue());
            }
        }

    }

    /**
     * 返回响应体为字节数组
     *
     * @param url
     * @return
     */
    public static byte[] getBytes(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            byte[] bytes = EntityUtils.toByteArray(response.getEntity());
            return bytes;
        } catch (Exception e) {
            blog.error(e.getMessage(), e);
            return null;
        }
    }

    public String get(String url) {
        HttpClientUtil.CharsetHandler handler = new HttpClientUtil.CharsetHandler("UTF-8");
        CloseableHttpClient client = null;

        String var5;
        try {
            HttpGet httpget = new HttpGet(new URI(url));
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
            client = httpClientBuilder.build();
            client = (CloseableHttpClient) wrapClient(client);
            String var6 = (String) client.execute(httpget, handler);
            return var6;
        } catch (Exception var16) {
            var5 = "";
        } finally {
            try {
                if (client != null) {
                    client.close();
                }
            } catch (IOException var15) {
                var15.printStackTrace();
            }

        }

        return var5;
    }

    private static class SingletonHolder {
        private static final HttpClientUtil INSTANCE = new HttpClientUtil();

        private SingletonHolder() {
        }
    }

    private class CharsetHandler implements ResponseHandler<String> {
        private String charset;

        public CharsetHandler(String charset) {
            this.charset = charset;
        }

        @Override
        public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() >= 300) {
                throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
            } else {
                HttpEntity entity = response.getEntity();
                return entity != null ? (!StringUtils.isBlank(this.charset) ? EntityUtils.toString(entity, this.charset) : EntityUtils.toString(entity)) : null;
            }
        }
    }
}
