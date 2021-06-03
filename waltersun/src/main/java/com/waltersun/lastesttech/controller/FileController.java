package com.waltersun.lastesttech.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.waltersun.lastesttech.bean.SpbtResponseEntity;
import com.waltersun.lastesttech.service.CommonService;
import com.waltersun.lastesttech.utils.ResponseUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author swh
 * @date 2021-05-25
 */
@CrossOrigin
@RestController
@Api(tags = "文件上传下载相关接口")
@RequestMapping("file")
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FileController {

    @Value("${downloadPath:/home/files/}")
    private String path;

    private final CommonService commonService;

    private static final String FILE_NAME = "fileName";
    private static final String FILE_ADRESS = "fileAddress";

    @GetMapping("fileInfos")
    @ApiOperation(value = "文件下载", response = String.class)
    @ResponseBody
    @SneakyThrows
    public String fileDownload(HttpServletResponse response,
                               @ApiParam(name = "path", value = "文件路径", required = true)
                               @RequestParam String path,
                               @ApiParam(name = "fileName", value = "文件，名称", required = true)
                               @RequestParam String fileName) {
        File file = new File(path);
        if (!file.exists()) {
            log.warn("请检查文件路径配置：{}", path);
            return "请检查文件路径配置" + path;
        }

        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

        try (InputStream inputStream = new FileInputStream(file)) {
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            byte[] buff = new byte[1024];
            int i;
            while ((i = bis.read(buff)) != -1) {
                response.getOutputStream().write(buff, 0, i);
                response.getOutputStream().flush();
            }
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
        return "success";
    }

    @SneakyThrows
    @GetMapping("fileList")
    @ApiOperation(value = "获取文件列表", response = String.class)
    @ResponseBody
    public SpbtResponseEntity getMarkdownFile() {
        File file = new File(path);
        List<File> fileList = Arrays.asList(Objects.requireNonNull(file.listFiles()));
        fileList.sort(Comparator.comparing(File::getName));
        List<Map<String, String>> linkList = new ArrayList<>();
        fileList.forEach(e -> {
            Map<String, String> map = new HashMap<>(16);
            map.put(FILE_NAME, e.getName());
            map.put(FILE_ADRESS, path + e.getName());
            linkList.add(map);
        });
        return ResponseUtils.successResponse(linkList);
    }

    @SneakyThrows
    @GetMapping("queryTest")
    @ApiOperation(value = "查询测试", response = String.class)
    @ResponseBody
    public String queryTest(){
        return commonService.queryTest();
    }
}
