# Aplikasi Invoice Management #

Aplikasi ini digunakan untuk mengelola invoice dengan berbagai metode pembayaran masa kini, metode pembayaran yang di
support antara lain :

* Virtual Account Bank
    * Bank BNI
    * Bank CIMB
    * Bank BSI


* E-Wallet
    * Ovo
    * GoPay


* QR Payment
    * QRIS

Tagihan yang disupport :

* CLOSED - Pembayaran sesuai nominal
* OPEN - Pembayaran bebas nominal
* INSTALLMENT - Pembayaran selama akumulasi lebih kecil sama dengan nilai tagihan

# Cara Setup Database #

1. Download [Docker](https://hub.docker.com)
   dan [Mysql](https://dev.mysql.com/doc/refman/8.0/en/docker-mysql-getting-started.html) docker image.

  ```shell
   sudo docker run --rm \
    --name=invoice-db \
    -e MYSQL_DATABASE=invoicedb \
    -e MYSQL_ROOT_PASSWORD=JBJwTMMYyZ8yy8eBaY3kPb6p \
    -e TZ=Asia/Jakarta \
    -p 6603:3306 \
    -v "$PWD/docker/invoice-db/conf.d":/etc/mysql/conf.d \
    -v "$PWD/storage/docker/invoicedb-data":/var/lib/mysql \
    mysql
  ```

2. Untuk keamanan yang lebih baik, saran terbaiknya adalah memiliki akun pengguna khusus dengan pengaturan privilese
   yang lebih sempit untuk setiap basis data, terutama jika Anda berencana memiliki beberapa basis data di mana server
   Anda adalah hostnya.

Hubungkan ke konsol *MySQL* menggunakan akun **root** lalu masukan password root yang sudah diisi pada
environment `-e MYSQL_ROOT_PASSWORD` :

  ```shell
    mysql -uroot -p -h127.0.0.1 -P6603 
  ```

setelah berhasil login, sekarang buat pengguna baru dan memberikan privilese penuh pada database khusus yang telah
dibuat saat menjalankan docker `-e MYSQL_DATABASE` :

  ```mysql
    CREATE USER 'invoice'@'%' IDENTIFIED WITH mysql_native_password BY 'JBJwTMMYyZ8yy8eBaY3kPb6p';
  ```

memberikan izin terhadap database **invoicedb** kepada pengguna ini:

  ```mysql
    GRANT ALL ON invoicedb.* TO 'invoice'@'%';
  ```

ini akan meberikan pengguna **invoice** privilese penuh terhadap database **invoicedb**, yang juga mencegah pengguna
dengan user invoice tidak membuaat atau memodifikasi database lainnya di sever Anda.

Sekarang, keluar dari *shell MySQL* lalu test apakah user baru yang sudah dibuat memiliki izin yang tepat dengan log
masuk ke konsol MySQL lagi, kali ini dengan menggunakan kredensial pengguna khusus yaitu user **invoice** :

  ```shell
    mysql -uinvoice -p -h127.0.0.1 -P6603 
  ```

setelah log masuk ke konsol MySQL, pastikan user ini memiliki akses ke database **invoicedb** :

  ```mysql
    SHOW DATABASES; 
  ```

ini akan menampilkan output berikut :

  ```shell
    +--------------------+
    | Database           |
    +--------------------+
    | information_schema |
    | invoicedb          |
    +--------------------+
    2 rows in set (0,00 sec) 
  ```

3. Tambahkan `application.properties` didalam `src/main/resources/` :

  ```properties
    ## update <- from entity class or validate from entity class to schema sql
spring.jpa.hibernate.ddl-auto=validate
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:127.0.0.1}:6603/invoicedb
spring.datasource.username=root
spring.datasource.password=JBJwTMMYyZ8yy8eBaY3kPb6p
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.format_sql=true
## this is default false but in devlopment we need this true
spring.jpa.show-sql=true
  ```

4. kemudian buat `db.migration` menggunakan penulisan [Calver](https://calver.org/) (*Calender Versioning*) untuk
   awalanya harus menggunakan huruf `V` ini wajib karena permintaan dari [Flyaway](https://flywaydb.org/) (*Version
   Control For Your Database*) buat
   file [V20210701__First_Schema.sql](https://github.com/yeahbutstill/invoice-magement/blob/main/src/main/resources/db/migration/V20210701__First_Schema.sql)


5. menjalankan applikasi dan mengecek apakah sudah benar skema db *entitiy* *class* yang sudah dibuat :

```shell
  mvn clean spring-boot:run
```