## 设置ie导出Excel乱码问题
```    
String userAgent = request.getHeader("user-agent");
if (userAgent != null && userAgent.indexOf("Firefox") >= 0 || userAgent.indexOf("Chrome") >= 0
        || userAgent.indexOf("Safari") >= 0) {
    fileName= new String((fileName).getBytes(), "ISO8859-1");
} else {
    fileName= URLEncoder.encode(fileName,"UTF8"); //其他浏览器
}
```    
