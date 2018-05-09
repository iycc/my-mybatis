## 手写 mybatis
用来理解mybatis实现原理

### v1
* 使用Jdk动态代理实现通过Mapper接口找sql
* 使用jdbc执行sql，硬编码处理解析结果
* 仅实现对test表的query操作

### v2
* 升级Configuration，增加实体类的配置（在MapperRegistry中加入class的配置）
* 升级Configuration，支持注解配置sql
* 升级SimpleExecutor，增加ResultSetHandler处理结果集
* 增加CachingExecutor
### v2.1
* 增加plugin功能