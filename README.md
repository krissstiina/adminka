# Команды и их краткое описание

## Обновление списка пакетов
Обновляет список доступных пакетов в системе.
```bash
  sudo apt update
```

## Установка необходимых пакетов
Устанавливает Java Development Kit 17, Maven, Git и Nginx.
```bash
  sudo apt install -y openjdk-17-jdk maven git nginx
```

## Проверка версии Java
Проверяет установленную версию Java.
```bash
  java -version
```

## Проверка версии Maven
Проверяет установленную версию Maven.
```bash
  mvn --version
```

## Создание директории для проекта
Создает директорию для приложения на локальной машине.
```bash
  mkdir -p /home/kristina/app
```

## Создание директории для приложения
Создает директорию для развертывания приложения.
```bash
  sudo mkdir -p /var/www/app
```

## Изменение владельца директории
Назначает пользователя `kristina` владельцем директории `/var/www/app`.
```bash
  sudo chown -R kristina:kristina /var/www/app
```

## Переход в рабочую директорию
Переходит в директорию с проектом.
```bash
  cd /home/kristina/app
```

## Клонирование репозитория
Клонирует репозиторий с исходным кодом приложения.
```bash
  git clone https://github.com/krissstiina/adminka.git
```

## Переход в директорию проекта
Переходит в директорию склонированного проекта.
```bash
  cd adminka
```

## Сборка проекта
Выполняет очистку и сборку проекта с помощью Maven.
```bash
  mvn clean package
```

## Копирование JAR-файла
Копирует собранный JAR-файл в директорию развертывания.
```bash
  sudo cp target/adm-0.0.1-SNAPSHOT.jar /var/www/app.jar
sudo cp target/adminka-1.0-SNAPSHOT.jar /var/www/app.jar
  sudo chmod 755 /var/www/app
```

## Создание конфигурации службы
Открывает файл для создания конфигурации службы Spring Boot приложения.
```bash
  sudo nano /etc/systemd/system/springapp.service
```
Впишем:
```bash
  [Unit]
  Description=Spring Boot Adm App Service

  [Service]
  WorkingDirectory=/var/www/
  ExecStart=/usr/bin/java -jar adm-app.jar
  Restart=always
  RestartSec=5
  SyslogIdentifier=springapp
  User=kristina
  Environment=SPRING_PROFILES_ACTIVE=prod

  [Install]
  WantedBy=multi-user.target
```

## Перечитывание конфигурации systemd
Перечитывает конфигурацию systemd для применения изменений.
```bash
  sudo systemctl daemon-reload
sudo chown kristina:kristina /var/www/app.jar
```

## Включение автозапуска службы
Включает автозапуск службы при загрузке системы.
```bash
  sudo systemctl enable springapp
```

## Запуск службы
Запускает службу Spring Boot приложения.
```bash
  sudo systemctl start springapp
```

## Проверка статуса службы
Проверяет текущий статус службы.
```bash
  sudo systemctl status springapp
```

## Проверка прав доступа к JAR-файлу
Проверяет права доступа к JAR-файлу приложения.
```bash
  ls -l /var/www/adm-app.jar
sudo chmod 755 /var/www/app.jar
```

## Создание конфигурации Nginx
Открывает файл для создания конфигурации Nginx.
```bash
  sudo nano /etc/nginx/sites-available/springapp
```
Впишем:
```bash
  server {
      listen 80;
      server_name <ip adress -> ipv4>;

      location / {
         proxy_pass http://localhost:5000;
         proxy_set_header Host $host;
         proxy_set_header X-Real-IP $remote_addr;
         proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
         proxy_set_header X-Forwarded-Proto $scheme;
      }
  }
```

## Создание символической ссылки
Создает символическую ссылку для активации конфигурации Nginx.
```bash
  sudo ln -s /etc/nginx/sites-available/springapp /etc/nginx/sites-enabled
```

## Проверка конфигурации Nginx
Проверяет корректность конфигурации Nginx.
```bash
  sudo nginx -t
```

## Перезапуск Nginx
Перезапускает Nginx для применения изменений.
```bash
  sudo systemctl restart nginx
```

## Проверка статуса Nginx
Проверяет текущий статус службы Nginx.
```bash
  sudo systemctl status nginx
```
