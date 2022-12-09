FROM alpine:3.17.0
RUN apk --update add openjdk11-jdk
RUN mkdir -p /home/app
COPY . /home/app