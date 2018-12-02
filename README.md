# supermarket
> 创建一个超集市场,基于spring cloud实现

#### modules
1. 要使用的技术栈
后端技术框架：spring boot、spring cloud、PostgreSQL，jpa
前端技术框架：vue
2. JDK 8
3. 数据库使用postgresql 10
4. 端口分配
    - 8081: 网关api-gateway(对token校验, aip url权限判断, 服务跳转) 
    - 8761: 服务注册中心service-registry-discovery
    - 8888: 中心配置服务器centralized-config-server
    - 9000: 鉴权中心authorization-server
    - 8000: service-admin
    - 8001: service-product
    - 8002: service-order
    - 8003: service-task
    - 8004: service-article
    - 8005: service-upload
    
##### 微服务的规范
service-XXX
