package cn.mrmj.api.common;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * create by: mrmj
 * description: 删除oss图片
 * create time: 2019/12/13 18:03
 */
@Api(tags = "删除oss图片")
@RestController
@RequestMapping("/delete/api")
public class FileDeleteImgController {

    private Logger log = LoggerFactory.getLogger(FileDeleteImgController.class);

    @Resource
    private UploadProperties uploadProperties;

    //配置静态信息
    private static final String HTTP_HOST = "http://projectlogic.oss-cn-hangzhou.aliyuncs.com";
    private static final String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
    private static final String accessKeyId = "LTAITlDyhupiOKN2";
    private static final String accessKeySecret = "ViwO8yMYAb7SgvhmOeFHu5EZ7ZthFE";
    private static final String bucketName = "projectlogic";


    /**
     * create by: mrmj
     * description: 删除单个文件
     * create time: 2019/12/5 18:09
     */
    @PostMapping("/aliDelete")
    @ApiOperation("从阿里云删除文件")
    public void uploadFileWithoutWatermark(MultipartFile file) throws IOException {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。
        // 如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        String objectName = "9c10cc09-f202-42a9-8157-869141b605f4.2fc63d16-1a39-4ad9-b4ef-e96dd30f0fda.jpg";
        ossClient.deleteObject(bucketName, objectName);
        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
