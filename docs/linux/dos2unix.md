## dos2unix的使用:对文件进行转换

由于在DOS（windows系统）下，文本文件的换行符为CRLF，而在Linux下换行符为LF，使用git进行代码管理时，git会自动进行CRLF和LF之间的转换，这个我们不用操心。而有时候，我们需要将windows下的文件上传到linux上，例如shell脚本，执行的时候有时会出现奇怪的问题，这时候，就需要
安装dos2unix软件，centos下：
```
yum install -y dos2unix
```

安装完成后，对文件进行转换
```
dos2unix abc.sh
```
现在执行就不会出问题了。
