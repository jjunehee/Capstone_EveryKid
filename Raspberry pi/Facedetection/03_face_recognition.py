import cv2
import numpy as np
import os
import datetime
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
    blob.upload_from_filename(filename='/home/pi/Capstone_EveryKid/Raspberry pi/firebase/image_storage/'+file, content_type='image/jpeg') #파일이 저장된 주소와 이미지 형식(jpeg도 됨)
    #debugging hello
    print("capture ")
    print(blob.public_url)
    

#메모리 카드의 파일을 정리 해 주자.
def clearAll():
    #제대로 할려면 용량 체크 하고 먼저 촬영된 이미지 부터 지워야 할것 같지만 여기선 폴더안에 파일을 몽땅 지우자.
    path = '/home/pi/Capstone_EveryKid/Raspberry pi/firebase/image_storage'
    os.system('rm -rf %s/*' % path)

def firebase():
    if(now.hour < 12):
        cv2.imwrite( '/home/pi/Capstone_EveryKid/Raspberry pi/firebase/image_storage/' + str(id) + ' ' + nowDate + ' 등원' + '.jpg', img)
        fileUpload(str(id) + ' ' + nowDate + ' 등원' +'.jpg')
    elif(now.hour >12):
        cv2.imwrite( '/home/pi/Capstone_EveryKid/Raspberry pi/firebase/image_storage/' + str(id) + ' '+ nowDate + ' 하원' + '.jpg', img)
        fileUpload(str(id) + ' ' + nowDate + ' 하원' +'.jpg')
        
    
    
    
recognizer = cv2.face.LBPHFaceRecognizer_create()
recognizer.read('trainer/trainer.yml')
cascadePath = "haarcascades/haarcascade_frontalface_default.xml"
faceCascade = cv2.CascadeClassifier(cascadePath);
font = cv2.FONT_HERSHEY_SIMPLEX

#iniciate id counter
id = 0

# names related to ids: example ==> loze: id=1,  etc
# 이런식으로 사용자의 이름을 사용자 수만큼 추가해준다.
names = ['None', 'Junhee', 'Jinsun', 'hyeokjin', 'ksw']

# Initialize and start realtime video capture
cam = cv2.VideoCapture(0)
cam.set(3, 640) # set video widht
cam.set(4, 480) # set video height

# Define min window size to be recognized as a face
minW = 0.1*cam.get(3)
minH = 0.1*cam.get(4)

count = 0
while True:
    ret, img =cam.read()
    img = cv2.flip(img, -1) # Flip vertically
    gray = cv2.cvtColor(img,cv2.COLOR_BGR2GRAY)
    
    faces = faceCascade.detectMultiScale( 
        gray,
        scaleFactor = 1.2,
        minNeighbors = 5,
        minSize = (int(minW), int(minH)),
       )

    for(x,y,w,h) in faces:
        cv2.rectangle(img, (x,y), (x+w,y+h), (0,255,0), 2)
        id, confidence = recognizer.predict(gray[y:y+h,x:x+w])
        # Check if confidence is less them 100 ==> "0" is perfect match
        if (confidence < 100):
            id = names[id]
            #confidence = "  {0}%".format(round(100 - confidence))
            #5초 이상 지속되면 캡쳐해서 파일명을 id(name)으로 저장하고 그 이미지  Firebase로
            now = datetime.datetime.now()
            nowDate = now.strftime('%Y-%m-%d')
            
            
            count+=1
            if(count % 30 == 0 and confidence < 45 ):
                firebase()
                
                #Check image capture
                #cv2.imshow('image', img)
    
        else:
            id = "unknown"
            confidence = "  {0}%".format(round(100 - confidence))
        
        cv2.putText(img, str(id), (x+5,y-5), font, 1, (255,255,255), 2)
        # cv2.putText(img, str(confidence), (x+5,y+h-5), font, 1, (255,255,0), 1)  
    
    cv2.imshow('camera',img) 
    k = cv2.waitKey(10) & 0xff # Press 'ESC' for exiting video
    if k == 27:
        break
# Do a bit of cleanup
print("\n [INFO] Exiting Program and cleanup stuff")
cam.release()
cv2.destroyAllWindows()
