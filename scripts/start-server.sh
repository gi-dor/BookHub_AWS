##!/bin/bash
#
#echo "--------------- 서버 배포 시작 -----------------"
#cd /home/ubuntu/BookHub_AWS
#sudo fuser -k -n tcp 8080 || true
#nohup java -jar project.jar > ./output.log 2>&1 &
#echo "--------------- 서버 배포 끝 -----------------"

#!/bin/bash

echo "--------------- 서버 배포 시작 -----------------"
cd /home/ubuntu/BookHub_AWS || { echo "디렉토리 이동 실패"; exit 1; }
sudo fuser -k -n tcp 8080 || true

# 환경 변수 설정
export IMAGE_UPLOAD_DIR=/home/ubuntu/BookHub_AWS/src/main/resources/static/image/board/

nohup java -jar project.jar --spring.config.location=classpath:/application.properties -Dimage.upload.dir=$IMAGE_UPLOAD_DIR > ./output.log 2>&1 &
if [ $? -eq 0 ]; then
  echo "서버 시작 성공"
else
  echo "서버 시작 실패"
  exit 1
fi
echo "--------------- 서버 배포 끝 -----------------"
