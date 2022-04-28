
### stream集合操作 List<T> list;
###### 集合去掉空数据
```
list.removeAll(Collections.singleton(null));
```
###### 集合转换
```
List<T2> list2 = list.stream().map(e -> new T2(e.get字段, e.get字段)).collect(Collectors.toList());
```  
###### Double类型求和
```
double sum=list.stream().mapToDouble(T::get字段.sum();
```
###### Double类型求平均值
```
double avg = list.stream().mapToDouble(T::get字段).average().getAsDouble();
```
###### 最大
```
int max = list.stream().mapToInt(T::get字段).max().getAsInt();
```
###### 最小
```
int min = list.stream().mapToInt(T::get字段).min().getAsInt();
```
###### distinct去重 需要list2重新接收
```
List<T> list2 = list1.stream().distinct().collect(Collectors.toList());
```
###### 过滤字段为空的
```
List<T> list = list.stream().filter(i -> i.get字段() != null).collect(Collectors.toList());
```
###### BigDecimal类型求和,保留两位小数
```
BigDecimal add=  list.stream().map(T::get字段).reduce(BigDecimal.ZERO,BigDecimal::add).setScale(BigDecimal.ROUND_CEILING,BigDecimal.ROUND_HALF_DOWN);
```
###### BigDecimal类型找最大数,保留两位小数
```
BigDecimal max=  aList.stream().map(T::get字段).reduce(BigDecimal.ZERO,BigDecimal::max).setScale(BigDecimal.ROUND_CEILING,BigDecimal.ROUND_HALF_DOWN);
```
### BigDecimal 精度计算
###### 加减乘除
```
public BigDecimal add(BigDecimal value);                        //加法
public BigDecimal subtract(BigDecimal value);                   //减法 
public BigDecimal multiply(BigDecimal value);                   //乘法
public BigDecimal divide(BigDecimal value);                     //除法
```
###### 比较大小
```
//a.compareTo(b) : 返回 -1 表示小于，0 表示 等于， 1表示 大于
BigDecimal a = new BigDecimal("1.0");
BigDecimal b = new BigDecimal("0.9");
System.out.println(a.compareTo(b));// 1
```
###### 保留小数位：setScale方法
```
BigDecimal m = new BigDecimal("1.2556");
BigDecimal n = m.setScale(3,BigDecimal.ROUND_HALF_DOWN);
System.out.println(n);// 1.256
```

### 线程池异步操作
```
//注入线程池
private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

//创建线程
cachedThreadPool.execute(new Runnable() {
    @Override
    public void run() {
        //执行代码...
    }
});
```
    
### Maps工具创建map
```
Map<String,Object> map= Maps.newLinkedHashMap();
```

### 常用注解
###### 事务，异步处理
```
@Transactional(rollbackFor = Exception.class)
@Async
```
###### Dubbo的service和Reference：
```
服务提供者：
@Component
@Service(version = "1.0.0")

服务消费端
@Reference(timeout = 9000, version = "1.0.0")
```
###### 用于在依赖关系注入完成之后需要执行的方法上，以执行任何初始化
```
@PostConstruct
```
###### 切面类
```
定义一个切面类 ：
放在类的上面配合@Component注解使用
@Component
@Aspect

Around属于环绕增强，能控制切点执行前，执行后，用这个注解后，程序抛异常，会影响@AfterThrowing这个注解
@Around("execution(* com.baidu..controller.CenterController.action(..))")
```


### 时间戳转换成时间
```
SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
String sd = sdf.format(new Date(Long.parseLong(String.valueOf(item.getCreate_time()))));
```

### 逻辑分页
```
    public TableDataInfo list(GovComplaintFeedbackBean bean)
    {
        startPage();
        List<GovComplaintFeedbackBean> list = iGovComplaintFeedbackService.selectGovComplaintFeedbackBeanList(bean);//总数据
        //分页控件失效 采用逻辑分页
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();//第几页
        Integer pageSize = pageDomain.getPageSize();//每页数量
        List<GovComplaintFeedbackBean> returnList=new ArrayList<>();//本页数据

        //获取起点
        int pageStart=(pageNum-1)*pageSize;
        //获取终点
        int pageStop=pageStart+pageSize;
        while (pageStart<pageStop){
            if (pageStart==list.size()){
                break;
            }
            returnList.add(list.get(pageStart++));
        }

        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setRows(returnList);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }
```

### 处理数据 填充没数据的日期数据为0
```

    /**
     * 分拣转化 39:可燃物   40:不可燃物
     * @Author aodeng
     * @param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    private Map<String, Object>  sortingConversion(DecrementQueryWhereDTO queryWhereDTO){
        Map<String, Object> map = Maps.newLinkedHashMap();
        StorageHistoryBean storageHistoryBean=new StorageHistoryBean();
        storageHistoryBean.setUser_id(queryWhereDTO.getUserId());
        storageHistoryBean.setStart_time(queryWhereDTO.getStartTime());
        List<StorageHistoryBean>  storageHistoryBeanList = storageHistoryMapper.selectSumWeightByMonth(storageHistoryBean);
        Set<String> allCategoriesId = Sets.newHashSet("39", "40");
        storageHistoryBeanList.forEach(itemp->allCategoriesId.remove(itemp.getCategory_type_id()));
        allCategoriesId.forEach(item->{
            StorageHistoryBean tempBean=new StorageHistoryBean();
            tempBean.setCategory_type_ids(item);
            tempBean.setMove_weight(0d);
            storageHistoryBeanList.add(tempBean);
        });
        storageHistoryBeanList.forEach(item->{
            if ("39".equals(item.getCategory_type_ids())) {
                map.put("sortingConversion39", BigDecimal.valueOf(item.getMove_weight() / 1000).setScale(2, RoundingMode.HALF_UP).doubleValue());
            } else if ("40".equals(item.getCategory_type_ids())) {
                map.put("sortingConversion40", BigDecimal.valueOf(item.getMove_weight() / 1000).setScale(2, RoundingMode.HALF_UP).doubleValue());
            }
        });
        return map;
    }
```

### list<map<string,object>> 按照某字段排序
```
        List<Map<String, Object>> list = statisticsMapper.spotCheckTrend(startTime, endTime, enterpriseId, taskId);
        Collections.sort(list, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                String name1 = o1.get("days").toString() ;//name1是从你list里面拿出来的一个
                String name2 = o2.get("days").toString() ; //name1是从你list里面拿出来的第二个name
                return name1.compareTo(name2);
            }
        });
```
### 接口List参数校验
```
@PutMapping("/batch")
public List<CategoryDTO> updateBatchBy(@RequestBody List<@Valid CategoryParam> categoryParams) {
}

class CategoryParam{
    @NotBlank(message = "分类名称不能为空")
    @Size(max = 255, message = "分类名称的字符长度不能超过 {max}")
    private String name;

    @Size(max = 1023, message = "封面图链接的字符长度不能超过 {max}")
    private String thumbnail;

    @Size(max = 255, message = "分类密码的字符长度不能超过 {max}")
    @NotAllowSpaceOnly(message = "密码开头和结尾不能包含空字符串")
    private String password;

    @Min(value = 0, message = "排序编号不能低于 {value}")
    private Integer priority;   
}
```
### 接口多个文件上传
```
    @PostMapping(value = "uploads", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation("Uploads multi files (Invalid in Swagger UI)")
    public List<AttachmentDTO> uploadAttachments(@RequestPart("files") MultipartFile[] files) {
        List<AttachmentDTO> result = new LinkedList<>();

        for (MultipartFile file : files) {
            // Upload single file
            Attachment attachment = attachmentService.upload(file);
            // Convert and add
            result.add(attachmentService.convertToDto(attachment));
        }

        return result;
    }
```
