## 版权声明
本作品采用<a rel="license" href="http://creativecommons.org/licenses/by/4.0/">知识共享署名 4.0 国际许可协议</a>进行许可。
本文作者：低调小熊猫
文章链接：https://aodeng.cc/archives/springbootqi
转载声明：自由转载-非商用-非衍生-保持署名，非商业转载请注明作者及出处，商业转载请联系作者本人qq:2696284032

## 单纯的广告
**个人博客：https://aodeng.cc**
**微信公众号：低调小熊猫**
**qq交流群：756796932**

## 单一文件上传
```
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
```
## Base64文件上传
可以去这个网站将图片加密成Base64码
http://base64.xpcha.com/pic.html
```
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
```
