
from flask import Flask, request, jsonify
from flask_cors import CORS

app = Flask(__name__)
CORS(app) # 프론트엔드와의 통신 허용

@app.route('/detect_yawn', methods=['POST'])
def detect_yawn():
    #오디오 데이터를 처리해서 하품 감지 코드
    return 0

@app.route('/api/chatbot', methods=['POST'])
def chatbot():
    data = request.get_json()
    message = data.get('message')

    if message == 'user_is_sleepy':
        reply = "혹시 졸리신가요?"
    
    return jsonify({'reply': reply})

if __name__ == '__main__':
    app.run(debug=True)