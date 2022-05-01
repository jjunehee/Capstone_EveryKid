from picamera import PiCamera
from time import sleep
import datetime
import sys, os
import requests
import firebase_admin
from firebase_admin import credentials
from firebase_admin import storage
from uuid import uuid4
import schedule
 
PROJECT_ID = "people-6bda1"
#my project id
 
cred = credentials.Certificate("/home/pi/Capstone_EveryKid/Raspberry pi/firebase/image_storage/people-6bda1-firebase-adminsdk-3z19w-1c304b3e10.json") #(키 이름 ) 부분에 본인의 키이름을 적어주세요.
default_app = firebase_admin.initialize_app(cred,{'storageBucket':f"{PROJECT_ID}.appspot.com"})
#버킷은 바이너리 객체의 상위 컨테이너이다. 버킷은 Storage에서 데이터를 보관하는 기본 컨테이너이다.
bucket = storage.bucket()#기본 버킷 사용
 
def fileUpload(file):
    blob = bucket.blob('image_store/'+file) #저장p한 사진을 파이어베이스 storage의 image_store라는 이름의 디렉토리에 저장
    #new token and metadata 설정
    new_token = uuid4()
    metadata = {"firebaseStorageDownloadTokens": new_token} #access token이 필요하다.
    blob.metadata = metadata
 
    #upload file
    blob.upload_from_filename(filename='/home/pi/Capstone_EveryKid/Raspberry pi/firebase/image_storage/'+file, content_type='image/png') #파일이 저장된 주소와 이미지 형식(jpeg도 됨)
    #debugging hello
    print("hello ")
    print(blob.public_url)
 
def execute_camera():
    
    #사진찍기
    #중복없는 파일명 만들기
    basename = "smr"
    suffix = datetime.datetime.now().strftime("%Y%m%d_%H%M%S") + '.png'
    filename = "_".join([basename, suffix])
 
    camera = PiCamera()
    camera.resolution = (640, 480)
    camera.start_preview()
    #이미지에 텍스트를 새겨 넣자.
    camera.annotate_text = "Smart Mirror"
    camera.annotate_text_size = 20
    sleep(5)
    #사진을 찍어서 저장한다. 파일의 중복되지 않도록 날짜시간을 넣어서 만듬
    camera.capture('/home/pi/Capstone_EveryKid/Raspberry pi/firebase/image_storage/' + filename)
    #사진 파일을 파이어베이스에 업로드 한다.
    fileUpload(filename)
    #로컬 하드의 사진을 삭제한다.
    camera.stop_preview()
    camera.close()
 
#메모리 카드의 파일을 정리 해 주자.
def clearAll():
    #제대로 할려면 용량 체크 하고 먼저 촬영된 이미지 부터 지워야 할것 같지만 여기선 폴더안에 파일을 몽땅 지우자.
    path = '/home/pi/Capstone_EveryKid/Raspberry pi/firebase/image_storage'
    os.system('rm -rf %s/*' % path)
 
 
#10초 마다 실행
schedule.every(10).seconds.do(execute_camera)
#10분에 한번씩 실행
#schedule.every(10).minutes.do(execute_camera)
#매 시간 마다 실행
schedule.every().hour.do(clearAll)
#기타 정해진 시간에 실행/매주 월요일에 실행/매주 수요일 몇시에 실행 등의 옵션이 있다.
 
 
while True:
    schedule.run_pending()
    sleep(1)
