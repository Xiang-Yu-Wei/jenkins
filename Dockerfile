# 使用官方 Jenkins LTS 作为基础镜像
FROM jenkins/jenkins:lts

# 切换为 root 用户进行系统级安装
USER root

# 安装必要的系统依赖
RUN apt-get update && apt-get install -y \
    apt-transport-https \
    ca-certificates \
    curl \
    gnupg2 \
    software-properties-common \
    && rm -rf /var/lib/apt/lists/*

# 添加 Docker 官方 GPG 密钥并安装 Docker CLI
#RUN curl -fsSL https://download.docker.com/linux/debian/gpg | apt-key add - \
#    && add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/debian $(lsb_release -cs) stable" \
#    && apt-get update \
#    && apt-get install -y docker-ce-cli \
 #   && rm -rf /var/lib/apt/lists/*

# 安装 Docker Compose
#RUN curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose \
 #   && chmod +x /usr/local/bin/docker-compose

# 安装其他常用工具
RUN apt-get update && apt-get install -y \
    git \
    openssh-client \
    wget \
    zip \
    unzip \
    && rm -rf /var/lib/apt/lists/*

# 切换回 jenkins 用户
USER jenkins

# 安装 Jenkins 插件
#COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
#RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

# 配置 Jenkins
#ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"
#COPY jenkins.yaml /var/jenkins_home/casc_configs/jenkins.yaml
#ENV CASC_JENKINS_CONFIG=/var/jenkins_home/casc_configs

# 预定义 Jenkins 管理员用户
ENV JENKINS_ADMIN_ID=admin
ENV JENKINS_ADMIN_PASSWORD=admin

# 暴露端口
EXPOSE 8055
EXPOSE 50000
