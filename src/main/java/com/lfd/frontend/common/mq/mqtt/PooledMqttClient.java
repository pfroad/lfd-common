package com.lfd.frontend.common.mq.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Ryan on 2017/5/5.
 */
public class PooledMqttClient {
    private static final Logger LOG = LoggerFactory.getLogger(PooledMqttClient.class);

    private final static Map<String, MqttClient> mqttClientMap = new ConcurrentHashMap<>();

    private List<String> clientKyes;
    private String broker;
    private String accessKey;
    private String secretKey;
    private String filePersistenceDir;
    private String serverUrls;
    private Boolean cleanSession;
    private int keepAliveInterval;
    private int connectionTimeout;
    private String groupId;
    private String certFileName;
    private MqttCallback mqttCallback;

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getFilePersistenceDir() {
        return filePersistenceDir;
    }

    public void setFilePersistenceDir(String filePersistenceDir) {
        this.filePersistenceDir = filePersistenceDir;
    }

    public String getServerUrls() {
        return serverUrls;
    }

    public void setServerUrls(String serverUrls) {
        this.serverUrls = serverUrls;
    }

    public Boolean getCleanSession() {
        return cleanSession;
    }

    public void setCleanSession(Boolean cleanSession) {
        this.cleanSession = cleanSession;
    }

    public int getKeepAliveInterval() {
        return keepAliveInterval;
    }

    public void setKeepAliveInterval(int keepAliveInterval) {
        this.keepAliveInterval = keepAliveInterval;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getCertFileName() {
        return certFileName;
    }

    public void setCertFileName(String certFileName) {
        this.certFileName = certFileName;
    }

    public MqttCallback getMqttCallback() {
        return mqttCallback;
    }

    public void setMqttCallback(MqttCallback mqttCallback) {
        this.mqttCallback = mqttCallback;
    }

    public List<String> getClientKyes() {
        return clientKyes;
    }

    public void setClientKyes(List<String> clientKyes) {
        this.clientKyes = clientKyes;
    }

    public static Map<String, MqttClient> getMqttClientMap() {
        return mqttClientMap;
    }

    public void start() throws Exception {
        if (clientKyes != null) {
            clientKyes.stream().forEach(it -> {
                MqttClientPersistence mqttClientPersistence = new MqttDefaultFilePersistence(filePersistenceDir);
                try {
                    final String clientId = groupId + "@@@" + it;
                    final MqttClient mqttClient = new MqttClient(broker, clientId, mqttClientPersistence);
                    final MqttConnectOptions connectOptions = new MqttConnectOptions();
                    final String sign = MacSignature.macSignature(clientId.split("@@@")[0], secretKey);
                    connectOptions.setUserName(accessKey);
                    connectOptions.setPassword(sign.toCharArray());
                    connectOptions.setCleanSession(cleanSession);
                    connectOptions.setKeepAliveInterval(keepAliveInterval);
                    connectOptions.setConnectionTimeout(connectionTimeout);
                    connectOptions.setServerURIs(new String[] { broker });

                    SocketFactory socketFactory = initSSLSocket(certFileName);
                    connectOptions.setSocketFactory(socketFactory);

                    mqttClient.setCallback(mqttCallback);

                    mqttClient.connect(connectOptions);

                    mqttClientMap.put(it, mqttClient);
                } catch (Exception e) {
                    LOG.error("Connect mqtt broker is failed.", e);
                }
            });
        }
    }

    public void stop() {
        if (mqttClientMap != null) {
            mqttClientMap.entrySet().stream().forEach(it -> {
                final MqttClient mqttClient = it.getValue();
                try {
                    mqttClient.disconnect();
                } catch (MqttException e) {
                    LOG.error("Failed to disconnect mqttClient", e);
                }
            });
        }
    }

    private static SSLSocketFactory initSSLSocket(String certFileName) throws Exception {
        InputStream caInput = new BufferedInputStream(ClassLoader.getSystemResourceAsStream(certFileName));
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        Certificate ca = null;
        try {
            ca = cf.generateCertificate(caInput);
        } catch (CertificateException e) {
            e.printStackTrace();
        } finally {
            caInput.close();
        }
        String keyStoreType = KeyStore.getDefaultType();
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        keyStore.load(null, null);
        keyStore.setCertificateEntry("ca", ca);
        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
        tmf.init(keyStore);
        SSLContext context = SSLContext.getInstance("TLSV1.2");
        context.init(null, tmf.getTrustManagers(), null);
        SSLSocketFactory socketFactory = context.getSocketFactory();
        return socketFactory;
    }
}
