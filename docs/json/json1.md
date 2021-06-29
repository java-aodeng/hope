## Json返回结果为null属性不显示解决

1.返回时null属性不显示：String str = JSONObject.toJSONString(obj);

2.返回为null属性显示：String str = JSONObject.toJSONString(obj,SerializerFeature.WriteMapNullValue);

3.Fastjson的SerializerFeature序列化属性

    QuoteFieldNames———-输出key时是否使用双引号,默认为true
    WriteMapNullValue——–是否输出值为null的字段,默认为false
    WriteNullNumberAsZero—-数值字段如果为null,输出为0,而非null
    WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null
    WriteNullStringAsEmpty—字符类型字段如果为null,输出为”“,而非null
    WriteNullBooleanAsFalse–Boolean字段如果为null,输出为false,而非null
