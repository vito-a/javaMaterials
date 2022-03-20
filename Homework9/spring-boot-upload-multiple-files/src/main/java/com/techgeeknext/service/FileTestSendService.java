package com.micrometerinflux.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Request;
import org.asynchttpclient.Response;
import org.asynchttpclient.request.body.multipart.FilePart;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.asynchttpclient.Dsl.*;

import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

@Service
public class FileTestSendService {
    private int threads;
    private final String url;
    private static final List<String> testFilenames = getPropertyList("test_filenames");

    RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(3000)
            .setConnectTimeout(3000).build();
    CloseableHttpAsyncClient httpApacheAsyncClient = HttpAsyncClients.custom()
            .setDefaultRequestConfig(requestConfig)
            .setMaxConnPerRoute(1000)
            .setMaxConnTotal(1000)
            .build();

    public FileTestSendService(String url, int threads) { this.url = url; this.threads = threads; }

    public FileTestSendService() { this.url = getProperty("url"); }

    static class Control {
        public AtomicInteger filesUploaded = new AtomicInteger(0);
        public AtomicInteger runningThreads = new AtomicInteger(0);
        public Map<String, Map<String, String>> threadsData = new HashMap<>();
    }
    public final Control control = new Control();

    public int getFilesUploaded() {
        return control.filesUploaded.get();
    }

    public Map<String, Map<String, String>> getThreadsData() {
        return control.threadsData;
    }

    public static synchronized String getProperty(String propertyName) {
        String propertyValue = "";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();

        try (InputStream resourceStream = loader.getResourceAsStream("application.properties")) {
            properties.load(resourceStream);
            propertyValue = properties.getProperty("micrometer.influx.metric.application." + propertyName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return propertyValue;
    }

    public static synchronized List<String> getPropertyList(String propertyName) {
        List<String> propertyValues = new ArrayList<>();
        Properties properties = new Properties();

        try {
            InputStream inputStream = new FileInputStream("src/main/resources/application.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Pattern pat = Pattern.compile("^micrometer\\.influx\\.metric\\.application\\.test_filenames\\[[0-9]+\\]");
        Set<String> names = properties.stringPropertyNames();
        for (String name : names) {
            Matcher m = pat.matcher(name);
            if (m.find()) {
                String propertyValue = properties.getProperty(name);
                propertyValues.add(propertyValue);
            }
        }
        Collections.shuffle(propertyValues);

        return propertyValues;
    }

    public synchronized void sendTestFiles(long end) {
        int threadID = 0;

        while (System.currentTimeMillis() < end) {
            while (control.runningThreads.get() < threads) {
                new Thread(new FileTestSendThread(threadID, end)).start();
                control.runningThreads.incrementAndGet();
                threadID = threadID < threads-1 ? threadID+1 : 0;
            }
        }
    }

    class FileTestSendThread implements Runnable {
        long end;
        int threadID;

        public FileTestSendThread(int threadID, long end) {
            this.end = end;
            this.threadID = threadID;
        }

        @Override
        public void run() {
            List<Map<String, String>> responseDataList = new ArrayList<>();
            for (int i = 0; i < threads; i++) {
                if (System.currentTimeMillis() < end) {
                    int startPos = (int) (Math.random() * (testFilenames.size() - 1) + 1);
                    int endPos = startPos + (int) (Math.random() * (testFilenames.size() - startPos - 1) + 1);
                    for (int j = startPos; j < endPos; j++) {
                        Map<String, String> responseData = uploadFileApacheAsync2(url, new ArrayList<>(Collections.singletonList(testFilenames.get(j))));
                        responseDataList.add(responseData);
                    }
                    control.filesUploaded.addAndGet(endPos - startPos);
                } else {
                    break;
                }
            }
            control.runningThreads.decrementAndGet();
            for (Map<String, String> responseData : responseDataList) {
                String resultMessage = "";
                String httpStatus = responseData.get("httpStatus");
                String filenames = responseData.get("filenames");
                String responseMsg = responseData.get("responseMsg");
                try {
                    if ((responseMsg != null) && !responseMsg.isEmpty()) {
                        Class.forName("com.fasterxml.jackson.databind.JsonNode");
                        JsonNode node = new ObjectMapper().readValue(responseMsg, JsonNode.class);
                        if (node.has("message")) {
                            resultMessage = node.get("message").asText();
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("Filenames sent in thread " + threadID + " : " + filenames + ", status: " +
                        httpStatus + " - response message: " + resultMessage);
                Map<String, String> filenameData = control.threadsData.get(filenames);
                if ((filenames == null) || filenameData == null) {
                    filenameData = new HashMap<>();
                    filenameData.put("uploads", "1");
                    filenameData.put("statuses", httpStatus);
                    control.threadsData.put(filenames, filenameData);
                } else {
                    int uploads = Integer.parseInt(filenameData.get("uploads")) + 1;
                    filenameData.put("uploads", Integer.toString(uploads));
                    Set<String> statusesSet = new HashSet<String>(Arrays.asList(filenameData.get("statuses").split(", ")));
                    statusesSet.add(httpStatus);
                    filenameData.put("statuses", String.join(",", statusesSet));
                }
                control.threadsData.put(filenames, filenameData);
            }
        }
    }

    static Map<String, String> uploadFile(String url, List<String> filenames) {
        Map<String, String> responseData = new HashMap<>();
        HttpPost post = new HttpPost(url);
        post.setHeader("Accept", "application/json");
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        for (String filename : filenames) {
            File file = new File(filename);
            builder.addPart("file", new FileBody(file));
        }
        post.setEntity(builder.build());
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = null;
        try {
            response = client.execute(post);
            responseData.put("filenames", String.join(",", filenames));
            responseData.put("httpStatus", Integer.toString(response.getStatusLine().getStatusCode()));
            responseData.put("responseMsg", EntityUtils.toString(response.getEntity(), "UTF-8"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return responseData;
    }

    static Map<String, String> uploadFileAsync(String url, List<String> filenames) {
        Map<String, String> responseData = new HashMap<>();
        for (String filename : filenames) {
            File file = new File(filename);
            try (AsyncHttpClient c = asyncHttpClient(config())) {
                Request r = post(url).addBodyPart(new FilePart("file", file, "application/x-pdf", null)).build();
                Response response = c.executeRequest(r).get();
                responseData.put("filenames", String.join(",", filenames));
                responseData.put("httpStatus", Integer.toString(response.getStatusCode()));
                responseData.put("responseMsg", response.getResponseBody());
            } catch (IOException | ExecutionException | InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        return responseData;
    }


    private Map<String, String> uploadFileApacheAsync(String url, List<String> filenames) {
        Map<String, String> responseData = new HashMap<>();
        final HttpPost post = new HttpPost(url);
        post.setHeader("Accept", "application/json");
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        for (String filename : filenames) {
            File file = new File(filename);
            builder.addPart("file", new FileBody(file));
        }
        post.setEntity(builder.build());
        try {
            httpApacheAsyncClient.start();
            // final CountDownLatch latch = new CountDownLatch(1);
            httpApacheAsyncClient.execute(post, new FutureCallback<HttpResponse>() {
                public void completed(final HttpResponse response) {
                    responseData.put("filenames", String.join(",", filenames));
                    responseData.put("httpStatus", Integer.toString(response.getStatusLine().getStatusCode()));
                    try {
                        responseData.put("responseMsg", EntityUtils.toString(response.getEntity(), "UTF-8"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // latch.countDown();
                    System.out.println(post.getRequestLine() + "->" + response.getStatusLine());
                }

                public void failed(final Exception ex) {
                    // latch.countDown();
                    System.out.println(post.getRequestLine() + "->" + ex);
                }

                public void cancelled() {
                    // latch.countDown();
                    System.out.println(post.getRequestLine() + " cancelled");
                }
            });
            // latch.await();
        } catch (Exception e /*InterruptedException e */) {
            e.printStackTrace();
        } finally {
            try {
                httpApacheAsyncClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return responseData;
    }

    private Map<String, String> uploadFileApacheAsync2(String url, List<String> filenames) {
        Map<String, String> responseData = new HashMap<>();

        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(3000)
                .setConnectTimeout(3000).build();
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setMaxConnPerRoute(1000)
                .setMaxConnTotal(1000)
                .build();

        httpclient.start();

        final HttpPost post = new HttpPost(url);
        post.setHeader("Accept", "application/json");
        //post.setHeader("Content-Type", "multipart/form-data");
        /*
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        for (String filename : filenames) {
            File file = new File(filename);
            builder.addPart("file", new FileBody(file));
        }
        post.setEntity(builder.build());
         */

        BufferedHttpEntity entity = null;
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        try {
            for (String filename : filenames) {
                File file = new File(filename);
                // builder.addPart("file", new FileBody(file));
                Path path = Paths.get(filename);
                String basename = path.getFileName().toString();
                builder.addBinaryBody("file", new FileInputStream(file), ContentType.DEFAULT_BINARY, basename);
            }
            HttpEntity multipartEntity = builder.build();
            entity = new BufferedHttpEntity(multipartEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (entity != null) {
            post.setEntity(entity);
        }

        //post.setHeader("Accept", "application/json");
        //post.setHeader("Content-type", "application/x-pdf");

        httpclient.execute(post, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(final HttpResponse response) {
                try {
                    InputStream responseBody = response.getEntity().getContent();
                    System.out.println("Server response : " + responseBody.toString());
                    System.out.println(post.getRequestLine() + "->" + response.getStatusLine());
                } catch (IOException | UnsupportedOperationException e) {
                    e.printStackTrace();
                }

                responseData.put("filenames", String.join(",", filenames));
                responseData.put("httpStatus", Integer.toString(response.getStatusLine().getStatusCode()));
                try {
                    responseData.put("responseMsg", EntityUtils.toString(response.getEntity(), "UTF-8"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void failed(final Exception ex) {
                System.out.println(post.getRequestLine() + " failed");
            }
            @Override
            public void cancelled() {
                System.out.println(post.getRequestLine() + " cancelled");
            }
        });

        return responseData;
    }

    private Map<String, String> uploadFileAHCAsync(String url, List<String> filenames) {
        Map<String, String> responseData = new HashMap<>();
        for (String filename : filenames) {
            File file = new File(filename);
            try (AsyncHttpClient c = asyncHttpClient(config())) {
                Request r = post(url).addBodyPart(new FilePart("file", file, "application/x-pdf", null)).build();
                Response response = c.executeRequest(r).get();
                responseData.put("filenames", String.join(",", filenames));
                responseData.put("httpStatus", Integer.toString(response.getStatusCode()));
                responseData.put("responseMsg", response.getResponseBody());
            } catch (IOException | ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        return responseData;
    }
}