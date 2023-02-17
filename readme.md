# 技術課題(gaudiy)


### 要件

  - 独自コインの残高を管理するシステムになります。
  - 事前にユーザー情報を登録することによって、そのユーザーは独自コインを管理できるようになります。
      - 登録したときに、ユーザーを一意に表すユーザーIDが生成されます。
  - ユーザーIDを指定することによって、ユーザーの残高を確認することができます。
  - ユーザーIDと増加量を指定することによって、残高に独自コインを追加することができます。
  - ユーザーIDと消費量を指定することによって、残高から独自コインを指定した分、減らすことができます。
  - Aさん → Bさんへの独自コインの送金も行うことができます。
  - ユーザーIDを指定することによって、今までどんな増加・消費がされたか日時付きで取得することができます。

### テック

* [markdown-it](https://github.com/markdown-it/markdown-it)
* [java](https://www.java.com/)
* [maven](https://maven.apache.org/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [docker](https://www.docker.com/)
* [docker-compose](https://github.com/docker/compose)
* [MySQL](https://www.mysql.com/)

### データベース

DOCKERを使わないと自分で新規作成してください

```sh
CREATE SCHEMA `gaudiy` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;
CREATE TABLE `gaudiy`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `coin` INT NOT NULL DEFAULT 0,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC));
CREATE TABLE `gaudiy`.`coin_history` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `coin` INT NOT NULL,
  `remark` VARCHAR(255) NOT NULL,
  `create_time` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `INDEX` (`user_id` ASC, `create_time` ASC));


```

### インストール

docker(https://www.docker.com/)とdocker-compose(https://github.com/docker/compose)が必要です。
dockerをインストールして、以下のコードで動かして環境はセットアップされます。

```sh
$ cd gaudiy
$ docker-compose up -d --build
```

#### コンフィグレーション

サーバーのコンフィグファイルは
```sh
server\src\main\resources\application.properties
```

データベースを設定できます。

ライセンス
----

MIT
