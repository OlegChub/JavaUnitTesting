FROM ubuntu:latest

# Install Java.
RUN apk --update --no-cache add openjdk11 curl