package edu.xpu.buckmoo.controller;

import edu.xpu.buckmoo.VO.ResultVO;
import edu.xpu.buckmoo.config.ProjectUrlConfig;
import edu.xpu.buckmoo.enums.ResultEnum;
import edu.xpu.buckmoo.exception.BuckMooException;
import edu.xpu.buckmoo.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * 图片上传控制器
 */
@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/fileDownload")
    public ResponseEntity<FileSystemResource> file(@RequestParam("fileUrl") String fileName) {
        return export(new File(projectUrlConfig.getImgPath()+fileName));
    }

    @ResponseBody
    @PostMapping(value = "/fileUpload")
    public ResultVO fileUpload(MultipartFile file) {
        if (file.isEmpty()) {
            System.out.println("文件为空");
            throw new BuckMooException(ResultEnum.NULL_FILE);
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = projectUrlConfig.getImgPath(); // 上传后的路径
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            boolean mkdirs = dest.getParentFile().mkdirs();
            assert mkdirs;
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String imgUrl = projectUrlConfig.getBuckmoo() + "/file/fileDownload?fileUrl="+fileName;
        return ResultVOUtil.success(imgUrl);
    }


    private ResponseEntity<FileSystemResource> export(File file) {
        if (file == null) {
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("accept-ranges", "bytes");
        headers.add("cache-control", "max-age=2592000");
        headers.add("expires", new Date().toString());
        headers.add("last-modified", new Date().toString());
        headers.add("etag", String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("image/png")) .body(new FileSystemResource(file));
    }
}
