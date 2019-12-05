package cn.mrmj.api.common;


import cn.mrmj.constant.enums.ErrorCodeEnum;
import cn.mrmj.response.Result;
import cn.mrmj.utils.StringUtil;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.UUID;

/**
 * create by: mrmj
 * description: 本地文件上传
 * create time: 2019/12/5 15:06
 */
@Api(tags = "本地文件上传")
@RestController
@RequestMapping("/upload/api")
public class FileUploadImgController {

    private Logger log = LoggerFactory.getLogger(FileUploadImgController.class);

    @Resource
    private UploadProperties uploadProperties;

    /**
     * create by: mrmj
     * description: 文件上传
     * create time: 2019/12/5 16:05
     */
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result upload(@ApiParam(required = true, value = "文件") MultipartFile file, HttpServletRequest request) throws Exception {
        //设置编码格式
        request.setCharacterEncoding("utf-8");
        // 获取文件存放路径，基路径，linux 或者 Windows
        String basePath = uploadProperties.getBasePath();
        //获取当前时间创建文件夹名称
        String location = StringUtil.getTimeFormat(new Date(), "yyyy-MM-dd") + "/";
        // 判断文件夹是否存在，不存在则创建新的文件名称
        File targetFile = new File(basePath + "/" + location);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        //获取文件的名称
        String fileName = file.getOriginalFilename();
        //生成文件的url
        String fileNameUrl = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
        try {
            //上传文件
            Files.copy(file.getInputStream(), Paths.get(uploadProperties.getBasePath() + "/" + location, fileNameUrl), StandardCopyOption.REPLACE_EXISTING);
            return Result.success(location + fileNameUrl);
        } catch (Exception e) {
            log.error("上传文件失败，" + e);
            return Result.fail(ErrorCodeEnum.FILE_UPLOAD_ERROR);
        }
    }

    private static final String HTTP_HOST = "http://qiandian.oss-cn-hangzhou.aliyuncs.com/";
    private static final String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
    private static final String accessKeyId = "BiTAsyfdFmA0z8tY";
    private static final String accessKeySecret = "I6gJTGcUExCse51s1XIU3mlXu1xmQq";
    private static final String bucketName = "qiandian";


    /**
     * create by: mrmj
     * description: 图片上传
     * create time: 2019/12/5 18:09
     */
    @PostMapping("/aliUpload")
    @ApiOperation("文件上传至阿里云")
    public String uploadFileWithoutWatermark(MultipartFile file) throws IOException {
        //MultipartFile转成InputStream 输入流
        InputStream inputStream = file.getInputStream();
        //获取上传文件的名称
        String fileOriginalFilename = file.getOriginalFilename();
        //获取扩展名称
        String extName = UUID.randomUUID().toString() + fileOriginalFilename.substring(fileOriginalFilename.lastIndexOf("."));
        //生成oss连接对象
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        //uuid随机数生成新的文件名
        String fileName= UUID.randomUUID().toString();
        // 根据 存储桶的名称，文件的名称+扩展名，文件的二进制数据 上传文件
        PutObjectResult putObjectResult= ossClient.putObject(bucketName, fileName+"."+extName, inputStream);
        //上传完成后关闭链接
        String ret=putObjectResult.getETag();
        ossClient.shutdown();
        if(ret.equals("")){
            return "";
        }else{
            //上传成功返回这个数据的地址链接
            return HTTP_HOST+"/"+fileName+"."+extName;
        }
    }
}
