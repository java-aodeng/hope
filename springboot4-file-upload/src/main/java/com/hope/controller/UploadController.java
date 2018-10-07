package com.hope.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @program:hope
 * @author:aodeng
 * @create:2018-10-07 18:24
 **/
@Controller
@RequestMapping("/uploads")
public class UploadController {
    //定义上传文件的保存位置，测试就放在c盘了，and该文件夹需要自己手动创建
    public static String UPLOADED_FOLDER="c://temp//";
    @GetMapping
    public String index(){
        return "uploadPage";
    }
    @PostMapping("uploadOne")
    public String uploadOne(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){
        if(file.isEmpty()){
            redirectAttributes.addFlashAttribute("message","请选择上传文件");
            return "redirect:uploadShow";
        }
        try {
            //获取文件并保存
            byte[] bytes = file.getBytes();
            Path path= Paths.get(UPLOADED_FOLDER+file.getOriginalFilename());
            Files.write(path,bytes);
            redirectAttributes.addFlashAttribute("message",file.getOriginalFilename()+"文件上传完成");
        }catch (IOException e){
            e.printStackTrace();
        }
        return "redirect:uploadShow";
    }
    @PostMapping("/uploadBase")
    @ResponseBody
    public void upload2(String base64) throws IOException {
        // TODO BASE64 方式的 格式和名字需要自己控制（如 png 图片编码后前缀就会是 data:image/png;base64,）
        final File tempFile = new File("c:\\temp\\test.jpg");//上传文件保存的路径
        // TODO 防止有的传了 data:image/png;base64, 有的没传的情况
        String[] d = base64.split("base64,");
        final byte[] bytes = Base64Utils.decodeFromString(d.length > 1 ? d[1] : d[0]);
        FileCopyUtils.copy(bytes, tempFile);
    }
    @GetMapping("uploadShow")
    public String uploadShow(){
        return "uploadShowPage";
    }
}