# AWS Homework

Це домашнє завдання містить дві частини:
1. Статичний сайт для S3
2. Spring Boot застосунок для EC2

## Частина 1: Розгортання статичного сайту на S3

Сайт "Cosmic Explorer" розташований у папці `s3-static-site`. Це єдиний файл `index.html`, який містить весь необхідний код (HTML/CSS/JS).

### Інструкція з деплою:
1. Зайдіть у консоль AWS і відкрийте сервіс **S3**.
2. Натисніть **Create bucket**. Вкажіть глобально унікальне ім'я (наприклад, `cosmic-explorer-site-123`).
3. У секції **Block Public Access settings for this bucket** зніміть галочку з "Block *all* public access", щоб дозволити публічний доступ.
4. Натисніть **Create bucket**.
5. Відкрийте створений bucket, перейдіть у вкладку **Properties**, прокрутіть вниз до **Static website hosting** і натисніть **Edit**.
6. Виберіть **Enable**, потім **Host a static website**, вкажіть **Index document** як `index.html` та збережіть зміни.
7. Перейдіть у вкладку **Permissions** та додайте наступну **Bucket policy** (обов'язково замініть `YOUR_BUCKET_NAME` на ім'я вашого бакету):
```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Sid": "PublicReadGetObject",
            "Effect": "Allow",
            "Principal": "*",
            "Action": "s3:GetObject",
            "Resource": "arn:aws:s3:::YOUR_BUCKET_NAME/*"
        }
    ]
}
```
8. Перейдіть у вкладку **Objects**, натисніть **Upload** та завантажте файл `s3-static-site/index.html`.
9. Готово! Поверніться у **Properties** -> **Static website hosting** та скопіюйте **Bucket website endpoint** — це і є URL вашого сайту, який потрібно надіслати.

---

## Частина 2: Розгортання Spring Boot на EC2 через Docker

Проєкт Spring Boot "Quote of the Day" знаходиться у папці `spring-boot-app`.

### Інструкція з деплою:
1. Зайдіть у консоль AWS і відкрийте сервіс **EC2**.
2. Натисніть **Launch instance**.
3. Виберіть Amazon Machine Image (AMI) — **Amazon Linux 2023** (або Ubuntu).
4. Виберіть Instance type — **t2.micro** (Free tier eligible).
5. Налаштуйте **Key pair** для SSH доступу (створіть новий, якщо його немає, та збережіть `.pem` файл).
6. У налаштуваннях **Network settings** створіть нову Security Group (або оберіть існуючу) і обов'язково додайте правила (Inbound rules):
    - **SSH** (порт 22) — дозволити з вашого IP або Anywhere.
    - **Custom TCP** (порт 8080) — Anywhere (0.0.0.0/0).
7. Запустіть інстанс. Дочекайтеся, поки він перейде у стан "Running".
8. Підключіться до інстансу через SSH (можна використати EC2 Instance Connect прямо з браузера в консолі AWS).
9. Встановіть Docker на EC2:
    ```bash
    sudo yum update -y
    sudo yum install docker -y
    sudo systemctl start docker
    sudo systemctl enable docker
    ```
10. Скопіюйте папку `spring-boot-app` на EC2 інстанс. Для цього зручно створити репозиторій на GitHub, запушити код туди, а потім зробити `git clone` на сервері:
    ```bash
    sudo yum install git -y
    git clone <URL_ВАШОГО_РЕПОЗИТОРІЮ>
    cd aws-homework/spring-boot-app
    ```
11. Зберіть Docker image (команда виконається згідно з інструкціями у `Dockerfile`):
    ```bash
    sudo docker build -t quote-api .
    ```
12. Запустіть контейнер:
    ```bash
    sudo docker run -d -p 8080:8080 quote-api
    ```
13. Готово! Знайдіть **Public IPv4 address** вашого інстансу EC2 та відкрийте у браузері: 
    - Головна: `http://<YOUR_EC2_PUBLIC_IP>:8080/`
    - Цитата дня: `http://<YOUR_EC2_PUBLIC_IP>:8080/api/quote`
