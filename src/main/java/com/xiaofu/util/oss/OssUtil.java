package com.xiaofu.util.oss;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;

/**
 * OssUtil.
 *
 * @author Yang 2019-12-16 10:39
 */
public class OssUtil {

    static OSSClient client = null;

    /**
     * 上传文件.
     *
     * @param ossClient  ossClient
     * @param bucketName bucketName
     * @param folder     文件夹
     * @param file       file
     * @return path
     */
    public static String uploadFile(OSSClient ossClient, String bucketName, String folder,
                                    MultipartFile file) {
        client = ossClient;
        // 文件名称 '原始文件名' + '-' + 'uuid' + '.' + '后缀'
        String originalFilename = file.getOriginalFilename();
        if (StringUtils.isEmpty(originalFilename)) {
            return null;
        }
        int index = originalFilename.lastIndexOf(".");
        String fileName = UUID.randomUUID().toString().replaceAll("-", "")
                + originalFilename.substring(index);
        String filePath = folder + "/" + fileName;
        long fileSize = file.getSize();
        final long partSize = 50 * 1024 * 1024L;

        // 简单上传
        if (fileSize <= partSize) {
            try (InputStream originalIn = file.getInputStream(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
                InputStream in = null;
                byte[] bytes = new byte[1024];
                int len;
                try {
                    while ((len = originalIn.read(bytes)) != -1) {
                        out.write(bytes, 0, len);
                    }
                    in = new ByteArrayInputStream(out.toByteArray());
                    // oss上传对象
                    ObjectMetadata metadata = new ObjectMetadata();
                    metadata.setContentLength(out.toByteArray().length);
                    String contentType = file.getContentType();
                    // oss图片预览问题
                    if (contentType != null && contentType.startsWith("image")) {
                        contentType = "image/jpg";
                    }
                    metadata.setContentType(contentType);
                    OssUtil.client.putObject(bucketName, filePath, in, metadata);

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (in != null) {
                            in.close();
                        }
                        out.close();
                        if (originalIn != null) {
                            originalIn.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else { // 分片上传
            BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(200);
            ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 5, 30, TimeUnit.SECONDS, blockingQueue);
            try {
                String contentType = file.getContentType();
                String uploadId = MultipartUploader.claimUploadId(contentType, bucketName, filePath);
                int partCount = (int) (fileSize / partSize);
                if (fileSize % partSize != 0) {
                    partCount += 1;
                }
                for (int i = 0; i < partCount; i++) {
                    // 起始point
                    long startPos = i * partSize;
                    // 判断当前partSize的长度 是否最后一块
                    long curPartSize = (i + 1 == partCount) ? (fileSize - startPos)
                            : partSize;
                    // 线程执行，将分好的文件块加到list集合中
                    poolExecutor.execute(new MultipartUploader(bucketName, filePath, file,
                            startPos, curPartSize, i + 1, uploadId));
                }

                // 关闭线程池，执行后程序会继续往下进行，线城池会等待所有线程线程执行完毕后关闭
                poolExecutor.shutdown();
                while (!poolExecutor.isTerminated()) {
                    try {
                        // 阻塞程序往下进行，使用之后程序会在所有线程执行完毕，关闭线城池之后才可以继续进行。
                        poolExecutor.awaitTermination(30, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        throw new IllegalStateException("文件上传失败！");
                    }
                }

                if (MultipartUploader.partETags.size() != partCount) {
                    throw new IllegalStateException("文件上传失败");
                }

                MultipartUploader.completeMultipartUpload(uploadId);
            } catch (Exception e) {
                throw new IllegalStateException("文件上传失败！");
            } finally {
                MultipartUploader.partETags.clear();
            }
        }
        return filePath;

    }

    /**
     * 删除文件.
     *
     * @param client     client
     * @param bucketName bucketName
     * @param filePath   filePath
     */
    public static void deleteFile(OSSClient client, String bucketName, String filePath) {
        client.deleteObject(bucketName, filePath);
    }

    /**
     * 获得临时url链接.
     *
     * @param ossClient  创建服务实例
     * @param bucketName bucketName
     * @param path       路径+文件名
     * @return String
     */
    public static String getFileUrl(OSSClient ossClient, String bucketName, String path) {
        Date expiration = new Date(System.currentTimeMillis() + 24 * 3600 * 1000);
        if (!StringUtils.isEmpty(path)) {
            URL url = ossClient.generatePresignedUrl(bucketName, path, expiration);
            return url.toString();
        }
        return null;
    }

}
