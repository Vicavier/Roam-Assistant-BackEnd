from flask import Flask, request
import json

app = Flask(__name__)


@app.route('/test', methods=['GET', 'POST'])
def test():
    print(request.json)
    return 'python!!'


if __name__ == '__main__':
    app.run()
