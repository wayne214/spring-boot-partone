package com.wayne.partone.fastdfs;

import org.csource.common.NameValuePair;
import org.slf4j.LoggerFactory;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * FastDFS文件系统客户端
 */
public class FastDFSClient {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(FastDFSClient.class);

    static {
        try {
            String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
            // ClientGlobal.init 方法会读取配置文件，并初始化对应的属性。
            ClientGlobal.init(filePath);
        } catch (Exception e) {
            logger.error("FastDFS Client init Fails", e);
        }
    }

    /**
     * 上传文件
     * @param file
     * @return
     */
    public static String[] upload(FastDFSFile file) {
        logger.info("File Name: " + file.getName() + "File Length:" + file.getContent().length);

        // 文件属性信息
        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] = new NameValuePair("author", file.getAuthor());

        long startTime = System.currentTimeMillis();
        String[] uploadResults = null;
        StorageClient storageClient = null;

        try {
            storageClient = getStorageClient();
            storageClient.upload_file(file.getContent(), file.getExt(), meta_list);
        } catch (IOException e) {
            logger.error("IO Exception when uploadind the file:" + file.getName(), e);
        } catch (Exception e) {
            logger.error("IO Exception when uploadind the file:" + file.getName(), e);
        }
        // 验证上传结果
        if (uploadResults == null && storageClient != null) {
            logger.error("upload file fail, error code:"+storageClient.getErrorCode());
        }
        // 上传成功会返回group name
        logger.info("upload file successfully" + "group_name:" + uploadResults[0] + ", remoteFileName:" + " " + uploadResults[1]);
        return uploadResults;
    }

    private static StorageClient getStorageClient() throws IOException{
        TrackerServer trackServer = getTrackServer();
        StorageClient storageClient = new StorageClient(trackServer, null);
        return storageClient;
    }

    private static TrackerServer getTrackServer() throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerServer;
    }
}
