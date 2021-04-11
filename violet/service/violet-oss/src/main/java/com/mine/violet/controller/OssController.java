package com.mine.violet.controller;



import com.mine.violet.commonutils.R;
import com.mine.violet.service.OssService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@Api(description="阿里云文件管理")
@RestController
@RequestMapping("/violetoss/fileoss")
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping("/upload")
    public R uploadOssFile(MultipartFile file){
        String url = ossService.uploadFileAvatar(file);
        return R.ok().data("url",url);
    }

}
