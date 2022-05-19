import firebase_admin
from firebase_admin import credentials
from firebase_admin import db
from firebase_admin import messaging
import datetime


#Firebase database 인증 및 앱 초기화
cred = credentials.Certificate('/home/pi/Capstone_EveryKid/Raspberry pi/firebase/image_storage/everykid-86be9-firebase-adminsdk-1xnck-d63d817d09.json')
firebase_admin.initialize_app(cred,{ 'databaseURL' : 'https://everykid-86be9-default-rtdb.firebaseio.com/' })


def mesgSend(token):
    registration_token = token
    
   
    message = messaging.Message (
        notification=messaging.Notification(
            title='알림 Test',
            body='안녕하세요. 아이가 등원했어요!',
            ),
        android=messaging.AndroidConfig(
            ttl=datetime.timedelta(seconds=3600),
            priority='normal',
            notification=messaging.AndroidNotification(
                icon='stock_ticker_update',
                color='#f45342'
            ),
        ),
        apns=messaging.APNSConfig(
            payload=messaging.APNSPayload(
                aps=messaging.Aps(badge=42),
            ),
        ),
        data={
            
        },
        
        token=registration_token,
    )
    
    
    
    response = messaging.send(message)
    print('Successfully sent message:', response)
    
userInfo = db.reference('users')

for val in userInfo.get().values():
    mesgSend(val['token'])


