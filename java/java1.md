## java常用方法
>熊猫笔记 https://github.com/java-aodeng/hope

    BigDecimal类：加，减，乘，除：
    public BigDecimal add(BigDecimal value);                        //加法
    public BigDecimal subtract(BigDecimal value);                   //减法 
    public BigDecimal multiply(BigDecimal value);                   //乘法
    public BigDecimal divide(BigDecimal value);                     //除法
    compareTo 比较大小 -1小于  0等于 1大于
    BigDecimal.valueOf(item.getWeight()/1000).setScale(2, RoundingMode.HALF_UP).doubleValue() 计算一个double数，保留两位小数，四舍五入
    
    使用String.format()格式化(四舍五入)
    double dou = 3.1487426;
    String douStr = String.format("%.2f", dou)
    
    Java8 的Stream api ：
    List<CartDTO> cartDTOList = orderDetailList.stream()
                    .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                    .collect(Collectors.toList());
    首先调用stream()方法，将其转换为流
    在流上调用map方法将其转换为另一个对象流
    调用collect方法，将对象流收集起来转化为集合
    
    集合.forEach(item ->{
        item-操作数据对象
    });
