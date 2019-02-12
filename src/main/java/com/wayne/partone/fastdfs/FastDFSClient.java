package com.wayne.partone.fastdfs;

import org.csource.common.NameValuePair;
import org.slf4j.LoggerFactory;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

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

        // 文件属性信息, NameValuePair，主要存储文件的一些基础属性，如作者信息、创建时间等；
        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] = new NameValuePair("author", file.getAuthor());

        long startTime = System.currentTimeMillis();
        String[] uploadResults = null;
        StorageClient storageClient = null;

        try {
            // 获取存储客户端
            storageClient = getStorageClient();
            // 文件上传
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

    /**
     * 获取文件
     * @param groupName
     * @param remoteFileName
     * @return
     */
    public static FileInfo getFile(String groupName, String remoteFileName) {
        try {
            StorageClient storageClient = getStorageClient();
            return storageClient.get_file_info(groupName,remoteFileName);
        }catch (IOException e) {
            logger.error("IO Exception: get File form fast dfs failed", e);
        }catch (Exception e) {
            logger.error("Non IO Exception: Get File from Fast DFS failed", e);
        }



        return null;
    }

    /**
     * 下载文件
     * @param groupName
     * @param remoteFileName
     * @return
     */
    public static InputStream downFile(String groupName, String remoteFileName) {
        try {
            StorageClient storageClient = getStorageClient();
            byte[] fileByte = storageClient.download_file(groupName, remoteFileName);
            InputStream ins = new ByteArrayInputStream(fileByte);

            return ins;
        } catch (IOException e) {
            logger.error("IO Exception: get File form fast dfs failed", e);
        } catch (Exception e) {
            logger.error("Non IO Exception: Get File from Fast DFS failed", e);
        }
        return null;
    }

    /**
     * 删除文件
     * @param groupName
     * @param remoteFileName
     */
    public static void deleteFile(String groupName, String remoteFileName) throws Exception{
        StorageClient storageClient = getStorageClient();
        int i = storageClient.delete_file(groupName, remoteFileName);
        logger.info("delete file successfully" + i);
    }

    /**
     * 获取StorageClient客户端
     * @return
     * @throws IOException
     */
    private static StorageClient getStorageClient() throws IOException{
        TrackerServer trackServer = getTrackerServer();
        StorageClient storageClient = new StorageClient(trackServer, null);
        return storageClient;
    }

    /**
     * 获取追踪服务器TrackServer
     * @return
     * @throws IOException
     */
    private static TrackerServer getTrackerServer() throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerServer;
    }

    public static String getTrackerUrl() throws IOException {
        return "http://"+getTrackerServer().getInetSocketAddress().getHostString()+":"+ClientGlobal.getG_tracker_http_port()+"/";
    }
}
