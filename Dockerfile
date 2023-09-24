FROM node:12

# Создание директории приложения
WORKDIR /app

# Установка зависимостей, учитывая package.json и package-lock.json
COPY package*.json ./gate-simulator

RUN npm i

# Копирование исходного кода приложения
COPY . .

EXPOSE 9999
CMD [ "node", "app.js" ]