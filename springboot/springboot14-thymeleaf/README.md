## 版权声明
>* 本文作者：低调小熊猫
* 本文链接：https://aodeng.cc/archives/springbootshi-liu
* 版权声明：本文采用<a rel="license" href="http://creativecommons.org/licenses/by/4.0/">知识共享署名 4.0 国际许可协议</a>进行许可。转载-非商用-非衍生-保持署名，商业转载请联系作者本人qq:2696284032

## 单纯的广告
>个人博客：https://aodeng.cc
微信公众号：低调小熊猫
QQ群：756796932

## 简介
简介懒得打了.....（此处省略1万字）

## 使用
这里举例使用thyme leaf的：赋值，拼接，if判断，unless判断，for 循环，URL，三目运算，switch 选择（后面继续添加）
页面代码
```
<h3>赋值<h3>
    <h3 th:text="${hope1}">Thymeleaf test page</h3>
    <h3>拼接<h3>
    <span th:text="'thymeleaf 普通拼接：'+${hopeName}+'!'"></span>
    <span th:text="|thymeleaf 简洁拼接：${hopeName}!|"></span>
    <h3>if判断<h3>
    true: <a th:if="${hopes=='hopes'} " th:href="@{https://aodeng.cc}">if</a>
    false:<a th:if="${hopes=='hope'} " th:href="@{https://aodeng.cc}">if</a>
    <h3>unless判断<h3>
    true: <a th:unless="${hopes!='hope'} " th:href="@{https://aodeng.cc}">unless</a>
    false:<a th:unless="${hopes=='hope'} " th:href="@{https://aodeng.cc}">unless</a>
    <h3>for 循环</h3>
    <table>
        <tr>
        <tr>
            id:
            name:
            password:
            sex:
            age:
            index:
        </tr>
        </tr>
        <tr th:each="user,iterStat:${userlist}">
            <td th:text="${user.id}"></td>
            <td th:text="${user.name}"></td>
            <td th:text="${user.password}"></td>
            <td th:text="${user.sex}"></td>
            <td th:text="${user.age}"></td>
            <td th:text="${iterStat.index}"></td>
        </tr>
    </table>
    terStat 称作状态变量，属性有：
    index，当前迭代对象的 index（从 0 开始计算）；
    count，当前迭代对象的 index（从 1 开始计算）；
    size，被迭代对象的大小；
    current，当前迭代变量；
    even/odd，布尔值，当前循环是否是偶数/奇数（从 0 开始计算）；
    first，布尔值，当前循环是否是第一个；
    last，布尔值，当前循环是否是最后一个
    <h3>URL</h3>
        <a th:href="@{https://github.com/java-aodeng/{url}(url=${url})}">点击测试</a>
        上例中 URL 最后的(url=${url})表示将括号内的内容作为 URL 参数处理，该语法避免使用字符串拼接，大大提高了可读性
        <h3>三目运算</h3>
        <input th:value="${number gt 2 ? '值为1':'值不为1'}">
        gt：great than（大于）
        ge：great equal（大于等于）
        eq：equal（等于）
        lt：less than（小于）
        le：less equal（小于等于）
        ne：not equal（不等于）
     <h3>switch 选择</h3>
        <div th:switch="${switch}">
            <p th:case="'0'">switch等于0</p>
            <p th:case="'1'">switch等于1</p>
            <p th:case="*">未知</p>
        </div>
```
后台代码
```
@RequestMapping("/hope1")
    public String hope1(ModelMap map){
        map.addAttribute("hope1","Thymeleaf 赋值 start");
        map.addAttribute("hopeName","低调小熊猫很帅");
        map.addAttribute("hopes","hopes");
        List<User> userList=new ArrayList<>();
        User user=new User();
        user.setId(1);
        user.setName("admin");
        user.setPassword("123456");
        user.setSex("男");
        user.setAge(20);
        userList.add(user);
        map.addAttribute("userlist",userList);
        map.addAttribute("url","hope");
        map.addAttribute("number",1);
        map.addAttribute("switch","0");
        return "hope1";
    }
```
运行效果
![file](/upload/2018/10/image-154201755303820181112181235507.png)

学习笔记，仅供参考