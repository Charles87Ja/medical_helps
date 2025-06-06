# 指定基础镜像
FROM java:8
# 维护者信息
MAINTAINER xiaozhi
# 定义匿名卷
VOLUME /tmp
#复制文件或修改名称
ADD land_manage-0.0.1-SNAPSHOT.jar app.jar
# 允许指定的端口
EXPOSE 8081
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]