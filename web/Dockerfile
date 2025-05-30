FROM node:20.18-alpine

ENV PROJECT_DIR=/workspace

WORKDIR $PROJECT_DIR


# 安装依赖
# 若网络不通，可以使用淘宝源
RUN npm config set registry https://registry.npmmirror.com
# RUN npm install



EXPOSE 3000

ENTRYPOINT ["sh", "/workspace/docker/entrypoint.sh"]