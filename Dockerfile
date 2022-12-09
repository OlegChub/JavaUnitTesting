FROM alpine:3.17.0
RUN apk --update add openjdk7-jre
RUN mkdir -p /home/app
COPY . /home/app