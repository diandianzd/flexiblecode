# 使用Ubuntu 22.04作为基础镜像
FROM ubuntu:jammy

RUN sed -i 's/archive.ubuntu.com/mirrors.tuna.tsinghua.edu.cn/g' /etc/apt/sources.list \
  && sed -i 's/security.ubuntu.com/mirrors.tuna.tsinghua.edu.cn/g' /etc/apt/sources.list \
  && sed -i 's/deb.ubuntu.com/mirrors.tuna.tsinghua.edu.cn/g' /etc/apt/sources.list \
  && rm -rf /var/lib/apt/lists/* \
  && rm -rf /etc/apt/sources.list.d/* \
  && apt-get clean && apt-get update



# 安装 SSH
RUN apt-get install -y openssh-server \
  && mkdir /var/run/sshd \
  && echo 'root:flexible123456' | chpasswd \
  && sed -i 's/#PermitRootLogin prohibit-password/PermitRootLogin yes/' /etc/ssh/sshd_config
EXPOSE 22

# shell颜色
RUN echo "force_color_prompt=yes" >> ~/.bashrc


# 安装常用工具
RUN apt-get install -y vim net-tools iputils-ping curl wget git
# 安装Java和Maven
RUN apt-get install -y openjdk-8-jdk \
  && apt-get install -y maven
# 设置JAVA_HOME环境变量
ENV JAVA_HOME /usr/lib/jvm/java-1.8.0-openjdk-amd64
# 设置PATH环境变量，确保java和javac可执行
ENV PATH $JAVA_HOME/bin:$PATH


# 设置工作目录
WORKDIR /workspace
# 删除目录下的文件

EXPOSE 8080
ENTRYPOINT ["bash", "/data/entrypoint.sh"]
